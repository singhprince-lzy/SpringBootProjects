package com.demo.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.demo.spring.dao.MovieDao;
import com.demo.spring.entites.AddMovie;
import com.demo.spring.entites.Signup;
import com.demo.spring.service.MovieService;

@RestController
public class MovieController {

	@Autowired
	public MovieService mc;
	
	@PostMapping("movie")
	public ModelAndView home() {
		ModelAndView mav= new ModelAndView("AddMovie");
		return mav;
	}
	
	@PostMapping("final")
	public ModelAndView signup(@RequestParam("moviename") String name,@RequestParam("price") Integer price,@RequestParam("id") Integer id)
	{
		
		AddMovie am = new AddMovie();
		am.setId(id);
		am.setName(name);
		am.setPrice(price);
		mc.addMovie(am);
		ModelAndView mv= new ModelAndView("aftersignup");
		mv.addObject("Message","Congratulations "+name+" ,is added sucessfully");
		return mv;
		
	}
	
}
