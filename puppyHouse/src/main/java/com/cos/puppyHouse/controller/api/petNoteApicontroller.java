package com.cos.puppyHouse.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cos.puppyHouse.Service.petBoardService;
import com.cos.puppyHouse.dto.ResponseDto;

@RestController
public class petNoteApicontroller {
	
	@Autowired
	private petBoardService petBoardService;
	
	@DeleteMapping("/petNote/diary/delete/{petBoardId}")
	public ResponseDto<Integer> deleteById(@PathVariable int petBoardId) {
		System.out.println("@#$%^$#@컨트롤러 호ㅜㅊㄹ");
		petBoardService.글삭제하기(petBoardId);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
}
