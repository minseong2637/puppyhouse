package com.cos.puppyHouse.controller.api;


import java.io.File;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cos.puppyHouse.Service.commuService;
import com.cos.puppyHouse.config.auth.PrincipalDetail;
import com.cos.puppyHouse.dto.ResponseDto;
import com.cos.puppyHouse.model.Community;
import com.cos.puppyHouse.model.Reply;
import com.cos.puppyHouse.repository.commuRepository;

@RestController
public class commuApiController {
	
	@Autowired
	private commuService commuService;
	
	@Autowired
	private commuRepository commuRepository;
	
	//게시글 작성
	@PostMapping("/api/commu")
	public ResponseDto<Integer> save(@RequestBody Community commu, @AuthenticationPrincipal PrincipalDetail principal) {
		commuService.write(commu, principal.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	//게시글 삭제
	@DeleteMapping("/api/commu/{id}")
	public ResponseDto<Integer> deleteById(@PathVariable int id) {
		commuService.delete(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	//댓글 삭제
	@DeleteMapping("/api/commu/{commuid}/reply/{replyid}")
	public ResponseDto<Integer> replyDelete(@PathVariable int commuid, @PathVariable int replyid) {
		commuService.replyDelete(commuid ,replyid);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	//게시글 업데이트
	@PutMapping("/api/commu/{id}")
	public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Community commu) {
		commuService.update(id,commu);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	//댓글 작성
	@PostMapping("/api/commu/{id}/reply")
	public ResponseDto<Integer> replySave(@PathVariable int id, @RequestBody Reply reply, @AuthenticationPrincipal PrincipalDetail principal) {
		System.out.println("replySave "+id);
		commuService.writeReply(id, reply, principal.getUser());
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	
	
	
}