package com.cos.puppyHouse.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.puppyHouse.Service.NoticeService;
import com.cos.puppyHouse.config.auth.PrincipalDetail;
import com.cos.puppyHouse.dto.ResponseDto;
import com.cos.puppyHouse.model.NoticeFAQ;

@RestController
public class NoticeApiController {
	
	@Autowired
	private NoticeService noticeService;
	
	@PostMapping("/api/notice")
	public ResponseDto<Integer> save(@RequestBody NoticeFAQ noticeFAQ, @AuthenticationPrincipal PrincipalDetail principal) {
		noticeService.글쓰기(noticeFAQ, principal.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	@DeleteMapping("/api/notice/{id}")
	public ResponseDto<Integer> deleteById(@PathVariable int id) {
		noticeService.글삭제하기(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	@PutMapping("/api/notice/{id}")
	public ResponseDto<Integer> deleteById(@PathVariable int id, @RequestBody NoticeFAQ noticeFAQ) {
		noticeService.글수정하기(id, noticeFAQ);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
}