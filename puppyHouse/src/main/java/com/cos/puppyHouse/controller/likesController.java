package com.cos.puppyHouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.puppyHouse.Service.likeService;
import com.cos.puppyHouse.config.auth.PrincipalDetail;
import com.cos.puppyHouse.dto.ResponseDto;

@RestController
public class likesController {
	

	@Autowired
	private likeService likeService;
	
	//좋아요 누르기
	@PostMapping("/api/like/{id}")
	public ResponseDto<Integer> addLike(@PathVariable int id,@AuthenticationPrincipal PrincipalDetail principal) {
	System.out.println("api/like/ : "+ id +"," + principal.getUser());
		likeService.insertLikes(id, principal.getUser().getId());
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	//좋아요 취소
	@DeleteMapping("/api/like/{id}/delete/{commuid}")
	public ResponseDto<Integer> deleteLike(@PathVariable int id, @PathVariable int commuid) {
		System.out.println("deletelikecontroller"+commuid+", "+id);
		likeService.deleteLikes(id, commuid);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
}
