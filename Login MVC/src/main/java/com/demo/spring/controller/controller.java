package com.demo.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.demo.spring.entites.Login;
import com.demo.spring.entites.Signup;
import com.demo.spring.service.UserService;

@RestController
public class controller {

	@Autowired
	private UserService userservice;
	
	//homepage
	@GetMapping("/home")
	public ModelAndView home()
	{
		ModelAndView mav= new ModelAndView("Login");
		return mav;
	}
	
	
	//After login form  is submitting
	@GetMapping("validation")
	public ModelAndView validate(@RequestParam("userid") int userid,@RequestParam("username") String name,@RequestParam("password") String password)
	{
		Login login = userservice.getuserbyid(userid);
		if(login.getUsername().equals(name)&&login.getPassword().equals(password))
		{
			ModelAndView mv= new ModelAndView("sucess");
			mv.addObject("Message","Welcome "+name);
			return mv;
		}else
		{
			ModelAndView mv1= new ModelAndView("Login");
			mv1.addObject("Message","Enter Valid Credentials");
			return mv1;
		}
		//ModelAndView mv= new ModelAndView("sucess");
		//mv.addObject("Message","Welcome "+name);
		//return mv;
	}
	
	
	//After signupform submition
	@GetMapping("final")
	public ModelAndView signup(@RequestParam("name") String name,@RequestParam("email") String email,@RequestParam("pno") long pno,
			@RequestParam("dob") String dob,@RequestParam("username") String username,@RequestParam("password") String password)
	{
		
		Signup signup = new Signup();
		signup.setName(name);
		signup.setEmail(email);
		signup.setPno(pno);
		signup.setDob(dob);
		signup.setUsername(username);
		signup.setPassword(password);
		userservice.signup(signup);
		ModelAndView mv= new ModelAndView("aftersignup");
		mv.addObject("Message","Congratulations "+name+" ,you have signed up sucessfully");
		return mv;
		
	}
	
	@GetMapping("signup")
	public ModelAndView register()
	{
		ModelAndView mv= new ModelAndView("Signup");
		return mv;
	}
	
	
	/*@PostMapping("/login")
	public void login(@RequestBody Login login)
	{
		userservice.login(login);
		
	}*/
	
	
}
