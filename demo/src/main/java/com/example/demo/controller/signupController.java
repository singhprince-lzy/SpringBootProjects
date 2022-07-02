package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class signupController {

	@GetMapping("/signup")
	public ModelAndView signpuData() {
		ModelAndView mv= new ModelAndView("Signup");
		return mv;
	}
	
}
