package com.nw.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
	
	@GetMapping("/")
	public String hello(Model model) {
		model.addAttribute("hello", "Hello Thymeleaf.");
		return "index";
	}

}