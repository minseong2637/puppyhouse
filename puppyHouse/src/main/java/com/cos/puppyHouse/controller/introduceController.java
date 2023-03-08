package com.cos.puppyHouse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class introduceController {
	@GetMapping("/auth/introduce")
	public String introduce() {
		return "introduce";
	}
}