package com.cos.puppyHouse.controller;

import java.io.File;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cos.puppyHouse.Service.UserService;
import com.cos.puppyHouse.Service.commuService;
import com.cos.puppyHouse.config.auth.PrincipalDetail;
import com.cos.puppyHouse.model.Community;

@Controller
public class CommuController {
	@Autowired
	private UserService userService;

	@Autowired
	private commuService commuService;

	// index
	@GetMapping({ "/", "" })
	public String index(HttpSession session, Model model, @AuthenticationPrincipal PrincipalDetail principal) {
		if (principal != null) {
			session.setAttribute("loginUser", userService.oneUser(principal.getUser()));
			System.out.println("login유저 강아지 "+userService.oneUser(principal.getUser()).getPet());
			session.setAttribute("loginUserPet", userService.oneUser(principal.getUser()).getPet());
			session.setAttribute("loginUserReserv", userService.oneUser(principal.getUser()).getReserv()); 
		}
		return "index";
	}

	// 커뮤니티 메인
	@GetMapping({ "/auth/commuBoard/commuMain" })
	public String commuMain(Model model,
			@PageableDefault(size = 6, sort = "commuId", direction = Sort.Direction.DESC) Pageable pageable) {
		model.addAttribute("commu", commuService.list(pageable));
		return "commuBoard/commuMain";
	}

	// 커뮤니티 게시글 작성
	@GetMapping("/commuBoard/commuSaveForm")
	public String commuSaveForm() {
		return "commuBoard/commuSaveForm";
	}

	// 커뮤니티 게시글 자세히 보기
	@GetMapping("/auth/commuBoard/{id}")
	public String findById(@PathVariable int id, Model model) {
		model.addAttribute("commu", commuService.detail(id));
		return "commuBoard/commuDetail";
	}

	// 커뮤니티 게시글 수정
	@GetMapping("/commuBoard/{id}/commuUpdate")
	public String updateForm(@PathVariable int id, Model model) {
		model.addAttribute("commu", commuService.detail(id));
		return "commuBoard/commuUpdate";
	}

	@GetMapping("/commuBoard/{id}")
	public String reply(@PathVariable int id, Model model) {
		model.addAttribute("commu", commuService.detail(id));
		return "commuBoard/commuDetail";
	}

	// 이미지 업로드
	@RequestMapping(value = "/commu/saveImg", method = { RequestMethod.POST })
	// @PostMapping("/commu/saveImg")
	public String saveImg(Community commu, @RequestParam(value = "file", required = false) MultipartFile file,
			@AuthenticationPrincipal PrincipalDetail principal) throws Exception {
		System.out.println("api/board" + commu.getTitle() + ", " + file);
		String sourceFileName = file.getOriginalFilename(); // getOriginalFilename() 업로드되는 파일에서 확장자를 포함한 파일의 이름을 반환
		String sourceFileNameException = FilenameUtils.getExtension(sourceFileName).toLowerCase(); // getExtensio():
																									// 파일명이 test.png 라면
																									// "png" 가 리턴되고
																									// "a/b/c.png" 의
																									// 경우에도 png만 리턴.
		File destinationFile; // toLowerCase(): 문자열을 소문자로 변환해서 반환.
		String destinationFileName;
		String fileUrl = "C:\\image\\"; // 서버 이미지 받기.(윈도우)

		do {
			destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + sourceFileNameException; // 랜덤 문자열 생성
			destinationFile = new File(fileUrl + destinationFileName); // (파일 위치 + 랜덤 문자열) 객체생성
		} while (destinationFile.exists());

		destinationFile.getParentFile().mkdirs(); // 부모 폴더 생성이 되지 않으면 파일 생성 오류 발생. 재귀적 부모 폴더 생성 코드 추가해준다.

		file.transferTo(destinationFile); // 원하는 위치에 저장

		commu.setImgName(destinationFileName);
		commu.setImgOriName(sourceFileName);
		commu.setImgUrl(fileUrl);
		commuService.write(commu, principal.getUser());

		return "redirect:/auth/commuBoard/commuMain";
	}

	// 이미지 출력
	@GetMapping("/auth/image")
	public ResponseEntity<Resource> display(@Param("imgName") String imgName) {
		String path = "C:\\image\\";
		Resource resource = new FileSystemResource(path + imgName);

		if (!resource.exists()) {
			return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);
		}

		HttpHeaders header = new HttpHeaders();

		Path filePath = null;
		try {
			filePath = (Path) Paths.get(path + imgName);
			header.add("Content-Type", Files.probeContentType(filePath));

		} catch (IOException e) {
			e.printStackTrace();

		}
		return new ResponseEntity<Resource>(resource, header, HttpStatus.OK);
	}

	// 이미지 수정
		@RequestMapping(value = "/commu/updateImg/{id}", method = { RequestMethod.POST })
		public String updateImg(@PathVariable int id, Community commu, @RequestParam(value = "file", required = false) MultipartFile file,
				@AuthenticationPrincipal PrincipalDetail principal) throws Exception {
			
			System.out.println("api/board" + commu.getTitle() + ", " + file);
			String sourceFileName = file.getOriginalFilename(); // getOriginalFilename() 업로드되는 파일에서 확장자를 포함한 파일의 이름을 반환
			String sourceFileNameException = FilenameUtils.getExtension(sourceFileName).toLowerCase(); // getExtensio():
																										// 파일명이 test.png 라면
																										// "png" 가 리턴되고
																										// "a/b/c.png" 의
																										// 경우에도 png만 리턴.
			File destinationFile; // toLowerCase(): 문자열을 소문자로 변환해서 반환.
			String destinationFileName;
			String fileUrl = "C:\\image\\"; // 서버 이미지 받기.(윈도우)

			do {
				destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + sourceFileNameException; // 랜덤 문자열 생성
				destinationFile = new File(fileUrl + destinationFileName); // (파일 위치 + 랜덤 문자열) 객체생성
			} while (destinationFile.exists());

			destinationFile.getParentFile().mkdirs(); // 부모 폴더 생성이 되지 않으면 파일 생성 오류 발생. 재귀적 부모 폴더 생성 코드 추가해준다.

			file.transferTo(destinationFile); // 원하는 위치에 저장

			commu.setImgName(destinationFileName);
			commu.setImgOriName(sourceFileName);
			commu.setImgUrl(fileUrl);
			commuService.update(id, commu);

			return "redirect:/auth/commuBoard/commuMain";
		}
}