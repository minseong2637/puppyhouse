package com.cos.puppyHouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.puppyHouse.Service.MailSendService;

@Controller
public class UserController {
	@Autowired
	private MailSendService mailService;
	
	@GetMapping("/auth/loginForm")
	public String loginForm() {
		return "user/loginForm";
	}
	
	@GetMapping("/auth/joinForm")
	public String joinForm() {
		return "user/joinForm";
	}
	
	@GetMapping("/user/updateForm")
	public String updateForm() {
		return "user/updateForm";
	}
	
	@GetMapping("/auth/findUserId")
	public String findUserIdForm() {
		return "findUser/findUserId";
	}
	@GetMapping("/auth/findUserPassword")
	public String findUserPasswordForm() {
		return "findUser/findPassword";
	}
	@GetMapping("/auth/resetUserPassword/{id}")
	public String resetUserPasswordForm(@PathVariable int id, Model model){
		System.out.println("selectedId"+id);
		model.addAttribute("selectedId",id);
		System.out.println(model.getAttribute("selectedId"));
		return "findUser/resetPassword";
	}
	
	//이메일 인증
	@GetMapping("/auth/mailCheck/{email}")
	@ResponseBody
	public String mailCheck(@PathVariable String email) {
		System.out.println("이메일 인증 요청이 들어옴!");
		System.out.println("이메일 인증 이메일 : " + email);
		return mailService.joinEmail(email);
	}
	
}
