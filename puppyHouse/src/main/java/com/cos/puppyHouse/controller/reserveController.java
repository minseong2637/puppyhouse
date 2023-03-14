package com.cos.puppyHouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cos.puppyHouse.Service.reserveService;
import com.cos.puppyHouse.config.auth.PrincipalDetail;

@Controller
public class reserveController {
	
	
	@Autowired
	private reserveService reserveService;
	
	@GetMapping("/auth/reserveMain")
	public String reserveMain() {
		return "reserve/reserveMain";
	}
	
	@GetMapping("/reserve/myReserve")
	public String myReserve() {
		return "reserve/myReserve";
	}
	

	@GetMapping("/reserve/reserve")
	public String teacher(Model model) {
		System.out.println("강아지 트레이너 Controller 호출");
		model.addAttribute("user", reserveService.선생님정보());
		return "reserve/reserveForm";
		
	}
	
	@GetMapping("/reserve/reserveInfo")
	public String reserveInfo(Model model) {
		model.addAttribute("reserve", reserveService.예약정보보기());
		return "reserve/myReserve";
	}
	
	@GetMapping("/reserve/petReserveInfo")
	public String petReserveInfo(Model model,@AuthenticationPrincipal PrincipalDetail principal) {

		model.addAttribute("loginuserreserv",principal.getUser().getReserv());
		model.addAttribute("reserve", reserveService.예약정보보기());
		model.addAttribute("reservdate", reserveService.예약정보보기중복제거());
		return "petNote/PetNoteWrite";
	}
	
	@GetMapping("/reserve/{id}/updateForm")
	public String 예약수정하기(@PathVariable int id, Model model) {
		model.addAttribute("reserve",reserveService.예약강아지목록상세보기(id));
		model.addAttribute("user", reserveService.선생님정보());
		return "reserve/reserveUpdateForm";
		
	}

}