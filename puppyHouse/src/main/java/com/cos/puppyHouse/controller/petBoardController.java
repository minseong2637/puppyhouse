package com.cos.puppyHouse.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cos.puppyHouse.Service.PetService;
import com.cos.puppyHouse.Service.petBoardService;
import com.cos.puppyHouse.config.auth.PrincipalDetail;
import com.cos.puppyHouse.model.PetBoard;
import com.cos.puppyHouse.model.PetBoardRoleType;

@Controller
public class petBoardController {
	
	@Autowired
	private petBoardService petBoardService;
	
	@Autowired
	private PetService petService;
	 
	
	@GetMapping("/petNote/diarySaveForm/{petId}")
	 public String diarySaveForm(@PathVariable int petId, Model model) {
		 model.addAttribute("petId",petId);
		 return "petNote/diarySaveForm";
	}
	
	@RequestMapping(value="/petNote/diary/saveImg/{petId}", method = {RequestMethod.POST})
	public String diarySaveImg(@PathVariable int petId, PetBoard petBoard, @RequestParam(value = "file", required = false)MultipartFile file, 
			@AuthenticationPrincipal PrincipalDetail principal) throws Exception {
		System.out.println("diarySaveImg 호출");
		String sourceFileName = file.getOriginalFilename(); //getOriginalFilename() 업로드되는 파일에서 확장자를 포함한 파일의 이름을 반환
		String sourceFileNameException = FilenameUtils.getExtension(sourceFileName).toLowerCase(); //getExtensio(): 파일명이 test.png 라면 "png" 가 리턴되고 "a/b/c.png" 의 경우에도 png만 리턴. 
		File destinationFile;																	//toLowerCase(): 문자열을 소문자로 변환해서 반환.
		String destinationFileName;
		String fileUrl = "C:\\image\\";  //서버 이미지 받기.(윈도우)
		
		do {
			destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + sourceFileNameException;  //랜덤 문자열 생성
			destinationFile = new File(fileUrl + destinationFileName); //(파일 위치 + 랜덤 문자열) 객체생성
		} while (destinationFile.exists());
		
		destinationFile.getParentFile().mkdirs();  //부모 폴더 생성이 되지 않으면 파일 생성 오류 발생. 재귀적 부모 폴더 생성 코드 추가해준다.
		
		file.transferTo(destinationFile); //원하는 위치에 저장
		
		petBoard.setImgName(destinationFileName);
		petBoard.setImgOriName(sourceFileName);
		petBoard.setImgUrl(fileUrl);
		petBoard.setRoles(PetBoardRoleType.DIARY);
		System.out.println("이미지 정보: "+petBoard.getImgName()+", "+petBoard.getImgOriName()+", "+petBoard.getImgUrl());
		petBoardService.diaryWrite(petId, petBoard, principal.getUser());
		
		return "redirect:/petNote/diary/{petId}";
	}
		
		
		
	@GetMapping("/auth/images")
	public ResponseEntity<Resource> display(@Param("imgName") String imgName) {
		String path = "C:\\image\\";
		Resource resource = new FileSystemResource(path+imgName);
		
		if(!resource.exists()) {
			return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);
		}
		
		HttpHeaders header = new HttpHeaders();

		Path filePath = null;
		try {
			filePath=(Path) Paths.get(path+imgName);
			header.add("Content-Type", Files.probeContentType(filePath));
			
		}catch(IOException e) {
			e.printStackTrace();
			
		}
		return new ResponseEntity<Resource>(resource, header, HttpStatus.OK);
	}
	

	//
	@GetMapping("/petNote/petNotice/FOOD/{petId}")
	public String petBoardFOOD(@PageableDefault(size = 4, sort = "petBoardId", direction = Sort.Direction.DESC) Pageable pageable,@PathVariable int petId, Model model) {
		System.out.println("petBoard 호출");
		int pageNumber = petBoardService.글목록(pageable, PetBoardRoleType.FOOD).getPageable().getPageNumber(); // 현재페이지
		int totalPages = petBoardService.글목록(pageable, PetBoardRoleType.FOOD).getTotalPages(); // 총 페이지 수
		int pageBlock = 4; // 숫자 블럭 수 1부터 10
		int startBlockPage = ((pageNumber) / pageBlock) * pageBlock + 1;
		int endBlockPage = startBlockPage + pageBlock - 1;
		endBlockPage = totalPages < endBlockPage ? totalPages : endBlockPage;
		
		model.addAttribute("pets", petService.강아지수첩상세보기(petId));
		model.addAttribute("startBlockPage", startBlockPage);
		model.addAttribute("endBlockPage", endBlockPage);
		model.addAttribute("petnotices", petBoardService.글목록(pageable, PetBoardRoleType.FOOD));
		

		return "petNote/foodDetail";
	}
	
	@GetMapping("/petNote/petNotice/NOTE/{petId}")
	public String petBoardNOTE(@PageableDefault(size = 4, sort = "petBoardId", direction = Sort.Direction.DESC) Pageable pageable,@PathVariable int petId, Model model) {
		System.out.println("petBoard 호출");
		int pageNumber = petBoardService.글목록(pageable, PetBoardRoleType.NOTE).getPageable().getPageNumber(); // 현재페이지
		int totalPages = petBoardService.글목록(pageable, PetBoardRoleType.NOTE).getTotalPages(); // 총 페이지 수
		int pageBlock = 4; // 숫자 블럭 수 1부터 10
		int startBlockPage = ((pageNumber) / pageBlock) * pageBlock + 1;
		int endBlockPage = startBlockPage + pageBlock - 1;
		endBlockPage = totalPages < endBlockPage ? totalPages : endBlockPage;

		model.addAttribute("pets",petService.강아지수첩상세보기(petId));
		model.addAttribute("startBlockPage", startBlockPage);
		model.addAttribute("endBlockPage", endBlockPage);
		model.addAttribute("petnotices", petBoardService.글목록(pageable, PetBoardRoleType.NOTE));

		return "petNote/petNoticeDetail";
	}
	
	@GetMapping("/petNote/petNotice/ACTIVITY/{petId}")
	public String petBoardACTIVITY(@PageableDefault(size = 4, sort = "petBoardId", direction = Sort.Direction.DESC) Pageable pageable,@PathVariable int petId, Model model) {
		System.out.println("petBoard 호출");
		int pageNumber = petBoardService.글목록(pageable, PetBoardRoleType.ACTIVITY).getPageable().getPageNumber(); // 현재페이지
		int totalPages = petBoardService.글목록(pageable, PetBoardRoleType.ACTIVITY).getTotalPages(); // 총 페이지 수
		int pageBlock = 4; // 숫자 블럭 수 1부터 10
		int startBlockPage = ((pageNumber) / pageBlock) * pageBlock + 1;
		int endBlockPage = startBlockPage + pageBlock - 1;
		endBlockPage = totalPages < endBlockPage ? totalPages : endBlockPage;

		model.addAttribute("pets",petService.강아지수첩상세보기(petId));
		model.addAttribute("startBlockPage", startBlockPage);
		model.addAttribute("endBlockPage", endBlockPage);
		model.addAttribute("petnotices", petBoardService.글목록(pageable, PetBoardRoleType.ACTIVITY));

		return "petNote/activityDetail";
	}
	
	@GetMapping("/petNote/petNotice/HEALTH/{petId}")
	public String petBoardHEALTH(@PageableDefault(size = 4, sort = "petBoardId", direction = Sort.Direction.DESC) Pageable pageable,@PathVariable int petId, Model model) {
		System.out.println("petBoard 호출");
		int pageNumber = petBoardService.글목록(pageable, PetBoardRoleType.HEALTH).getPageable().getPageNumber(); // 현재페이지
		int totalPages = petBoardService.글목록(pageable, PetBoardRoleType.HEALTH).getTotalPages(); // 총 페이지 수
		int pageBlock = 4; // 숫자 블럭 수 1부터 10
		int startBlockPage = ((pageNumber) / pageBlock) * pageBlock + 1;
		int endBlockPage = startBlockPage + pageBlock - 1;
		endBlockPage = totalPages < endBlockPage ? totalPages : endBlockPage;

		model.addAttribute("pets",petService.강아지수첩상세보기(petId));
		model.addAttribute("startBlockPage", startBlockPage);
		model.addAttribute("endBlockPage", endBlockPage);
		model.addAttribute("petnotices", petBoardService.글목록(pageable, PetBoardRoleType.HEALTH));

		return "petNote/healthDetail";
	}
	

}