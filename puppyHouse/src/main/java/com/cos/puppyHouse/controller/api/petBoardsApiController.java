package com.cos.puppyHouse.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.puppyHouse.Service.PetService;
import com.cos.puppyHouse.Service.petBoardService;
import com.cos.puppyHouse.config.auth.PrincipalDetail;
import com.cos.puppyHouse.dto.ResponseDto;
import com.cos.puppyHouse.model.Pet;
import com.cos.puppyHouse.model.PetBoard;

@RestController
public class petBoardsApiController {
	@Autowired
	private PetService petService;
	
	@Autowired
	private petBoardService petBoardService;
	
	@PostMapping("/api/petboard/{petid}")
	public ResponseDto<Integer> svae(@RequestBody PetBoard petBoard,@PathVariable int petid, @AuthenticationPrincipal PrincipalDetail principal){
		System.out.println("petBoardApiController 출력");
		Pet pet = petService.강아지수첩상세보기(petid);
		System.out.println(petBoard);
		
		petBoardService.글쓰기(petBoard,pet, principal.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	@DeleteMapping("/api/petboard/{petBoardId}")
	public ResponseDto<Integer> deleteById(@PathVariable int petBoardId) {
		petBoardService.글삭제하기(petBoardId);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
}
