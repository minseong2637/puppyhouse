package com.cos.puppyHouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cos.puppyHouse.Service.PetService;
import com.cos.puppyHouse.Service.petBoardService;
import com.cos.puppyHouse.model.PetBoardRoleType;

@Controller
public class petController {
	
	@Autowired
	private PetService petService;
	
	@Autowired
	private petBoardService petBoardService;

	@GetMapping("/petNote/petJoinForm")
	public String petJoinForm() {
		return "pet/petJoinForm";
	}
	
	@GetMapping ("/petNote/petUpdateForm/{petId}")
	public String petUpdateForm(@PathVariable int petId, Model model) {
		model.addAttribute("pet", petService.강아지수첩상세보기(petId));
		return "pet/petUpdateForm";
	}
	
	@GetMapping ("/petNote/activity")
	public String activityDetail() {
		return "petNote/activityDetail";
	}
	
	@GetMapping ("/petNote/food")
	public String foodDetail() {
		return "petNote/foodDetail";
	}
	
	@GetMapping ("/petNote/health")
	public String healthDetail() {
		return "petNote/healthDetail";
	}
	
	@GetMapping ("/petNote/notice")
	public String noticeDetail() {
		return "petNote/petNoticeDetail";
	}
	
	
	@GetMapping ("/petNote/petJoinBtn")
	public String petJoinBtn() {
		return "petNote/petJoinBtn";
	}
	
	@GetMapping ("/petNote/petImg")
	public String petImg() {
		return "petNote/petImg";
	}
	
	@GetMapping("/petNote/diary/{petId}")
	 public String diaryDetail(@PathVariable int petId, Model model) {
		System.out.println("diaryDetail 호출");
		 model.addAttribute("petId", petId);
	     model.addAttribute("diary", petBoardService.글목록2(PetBoardRoleType.DIARY));
	     System.out.println("글목록: "+petBoardService.글목록2(PetBoardRoleType.DIARY));
	     
	     model.addAttribute("pets", petService.강아지수첩상세보기(petId));
	     System.out.println("$$%$#petidd: "+petService.강아지수첩상세보기(petId));
	     
	     System.out.println(model.getAttribute("diary"));
	     System.out.println(model.getAttribute("pets"));
	     
	     return "petNote/diaryDetail";
	}
	
}