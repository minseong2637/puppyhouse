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
		System.out.println("diarySaveImg ??????");
		String sourceFileName = file.getOriginalFilename(); //getOriginalFilename() ??????????????? ???????????? ???????????? ????????? ????????? ????????? ??????
		String sourceFileNameException = FilenameUtils.getExtension(sourceFileName).toLowerCase(); //getExtensio(): ???????????? test.png ?????? "png" ??? ???????????? "a/b/c.png" ??? ???????????? png??? ??????. 
		File destinationFile;																	//toLowerCase(): ???????????? ???????????? ???????????? ??????.
		String destinationFileName;
		String fileUrl = "C:\\image\\";  //?????? ????????? ??????.(?????????)
		
		do {
			destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + sourceFileNameException;  //?????? ????????? ??????
			destinationFile = new File(fileUrl + destinationFileName); //(?????? ?????? + ?????? ?????????) ????????????
		} while (destinationFile.exists());
		
		destinationFile.getParentFile().mkdirs();  //?????? ?????? ????????? ?????? ????????? ?????? ?????? ?????? ??????. ????????? ?????? ?????? ?????? ?????? ???????????????.
		
		file.transferTo(destinationFile); //????????? ????????? ??????
		
		petBoard.setImgName(destinationFileName);
		petBoard.setImgOriName(sourceFileName);
		petBoard.setImgUrl(fileUrl);
		petBoard.setRoles(PetBoardRoleType.DIARY);
		System.out.println("????????? ??????: "+petBoard.getImgName()+", "+petBoard.getImgOriName()+", "+petBoard.getImgUrl());
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
		System.out.println("petBoard ??????");
		int pageNumber = petBoardService.?????????(pageable, PetBoardRoleType.FOOD).getPageable().getPageNumber(); // ???????????????
		int totalPages = petBoardService.?????????(pageable, PetBoardRoleType.FOOD).getTotalPages(); // ??? ????????? ???
		int pageBlock = 4; // ?????? ?????? ??? 1?????? 10
		int startBlockPage = ((pageNumber) / pageBlock) * pageBlock + 1;
		int endBlockPage = startBlockPage + pageBlock - 1;
		endBlockPage = totalPages < endBlockPage ? totalPages : endBlockPage;
		
		model.addAttribute("pets", petService.???????????????????????????(petId));
		model.addAttribute("startBlockPage", startBlockPage);
		model.addAttribute("endBlockPage", endBlockPage);
		model.addAttribute("petnotices", petBoardService.?????????(pageable, PetBoardRoleType.FOOD));
		

		return "petNote/foodDetail";
	}
	
	@GetMapping("/petNote/petNotice/NOTE/{petId}")
	public String petBoardNOTE(@PageableDefault(size = 4, sort = "petBoardId", direction = Sort.Direction.DESC) Pageable pageable,@PathVariable int petId, Model model) {
		System.out.println("petBoard ??????");
		int pageNumber = petBoardService.?????????(pageable, PetBoardRoleType.NOTE).getPageable().getPageNumber(); // ???????????????
		int totalPages = petBoardService.?????????(pageable, PetBoardRoleType.NOTE).getTotalPages(); // ??? ????????? ???
		int pageBlock = 4; // ?????? ?????? ??? 1?????? 10
		int startBlockPage = ((pageNumber) / pageBlock) * pageBlock + 1;
		int endBlockPage = startBlockPage + pageBlock - 1;
		endBlockPage = totalPages < endBlockPage ? totalPages : endBlockPage;

		model.addAttribute("pets",petService.???????????????????????????(petId));
		model.addAttribute("startBlockPage", startBlockPage);
		model.addAttribute("endBlockPage", endBlockPage);
		model.addAttribute("petnotices", petBoardService.?????????(pageable, PetBoardRoleType.NOTE));

		return "petNote/petNoticeDetail";
	}
	
	@GetMapping("/petNote/petNotice/ACTIVITY/{petId}")
	public String petBoardACTIVITY(@PageableDefault(size = 4, sort = "petBoardId", direction = Sort.Direction.DESC) Pageable pageable,@PathVariable int petId, Model model) {
		System.out.println("petBoard ??????");
		int pageNumber = petBoardService.?????????(pageable, PetBoardRoleType.ACTIVITY).getPageable().getPageNumber(); // ???????????????
		int totalPages = petBoardService.?????????(pageable, PetBoardRoleType.ACTIVITY).getTotalPages(); // ??? ????????? ???
		int pageBlock = 4; // ?????? ?????? ??? 1?????? 10
		int startBlockPage = ((pageNumber) / pageBlock) * pageBlock + 1;
		int endBlockPage = startBlockPage + pageBlock - 1;
		endBlockPage = totalPages < endBlockPage ? totalPages : endBlockPage;

		model.addAttribute("pets",petService.???????????????????????????(petId));
		model.addAttribute("startBlockPage", startBlockPage);
		model.addAttribute("endBlockPage", endBlockPage);
		model.addAttribute("petnotices", petBoardService.?????????(pageable, PetBoardRoleType.ACTIVITY));

		return "petNote/activityDetail";
	}
	
	@GetMapping("/petNote/petNotice/HEALTH/{petId}")
	public String petBoardHEALTH(@PageableDefault(size = 4, sort = "petBoardId", direction = Sort.Direction.DESC) Pageable pageable,@PathVariable int petId, Model model) {
		System.out.println("petBoard ??????");
		int pageNumber = petBoardService.?????????(pageable, PetBoardRoleType.HEALTH).getPageable().getPageNumber(); // ???????????????
		int totalPages = petBoardService.?????????(pageable, PetBoardRoleType.HEALTH).getTotalPages(); // ??? ????????? ???
		int pageBlock = 4; // ?????? ?????? ??? 1?????? 10
		int startBlockPage = ((pageNumber) / pageBlock) * pageBlock + 1;
		int endBlockPage = startBlockPage + pageBlock - 1;
		endBlockPage = totalPages < endBlockPage ? totalPages : endBlockPage;

		model.addAttribute("pets",petService.???????????????????????????(petId));
		model.addAttribute("startBlockPage", startBlockPage);
		model.addAttribute("endBlockPage", endBlockPage);
		model.addAttribute("petnotices", petBoardService.?????????(pageable, PetBoardRoleType.HEALTH));

		return "petNote/healthDetail";
	}
	

}