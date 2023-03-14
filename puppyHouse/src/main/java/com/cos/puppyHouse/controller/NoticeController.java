package com.cos.puppyHouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cos.puppyHouse.Service.NoticeService;
import com.cos.puppyHouse.model.NoticeRoleType;

@Controller
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	@GetMapping("/auth/news/{roles}")
	public String newsMain(@PageableDefault(size=5, sort="noticeId", direction=Sort.Direction.DESC) Pageable pageable, @PathVariable NoticeRoleType roles, Model model) {
		System.out.println("news롤 호출");
		int pageNumber = noticeService.글목록(pageable,roles).getPageable().getPageNumber();  //현재페이지
		int totalPages = noticeService.글목록(pageable,roles).getTotalPages(); //총 페이지 수
		int pageBlock = 5;  //숫자 블럭 수 1부터 10
		int startBlockPage = ((pageNumber)/pageBlock)*pageBlock+1;
		// pageNumber:0~4 => startBlockPage:1  endBlockPage:5
		// pageNumber:5~9 => startBlockPage:6  endBlockPage:10 
		int endBlockPage = startBlockPage+pageBlock-1;
		int orgend = endBlockPage;
		endBlockPage = totalPages<endBlockPage? totalPages:endBlockPage;
		System.out.println("@#$%$#$"+totalPages);
		
		model.addAttribute("startBlockPage", startBlockPage);
		model.addAttribute("endBlockPage", endBlockPage);
		model.addAttribute("notices", noticeService.글목록(pageable,roles));
		model.addAttribute("orgend", orgend);
		return "news/newsMain";
	}
	

	@GetMapping("/news/saveForm")
	public String saveForm() {
			return "news/newsSaveForm";
	}
	
	@GetMapping("/news/{id}")
	public String findById(@PathVariable int id, Model model) {
		model.addAttribute("notice", noticeService.글상세보기(id));
		return "news/newsDetail";
	}
	
	@GetMapping("/news/{id}/updateForm")
	public String updateForm(@PathVariable int id, Model model) {
		model.addAttribute("notice", noticeService.글상세보기(id));
		return "news/newsUpdateForm";
	}
	
}