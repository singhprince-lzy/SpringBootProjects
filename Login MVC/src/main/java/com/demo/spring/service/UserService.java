package com.demo.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.spring.dao.LoginDao;
import com.demo.spring.dao.LoginUserDao;
import com.demo.spring.dao.SignupDao;
import com.demo.spring.entites.Login;
import com.demo.spring.entites.Signup;

@Service
public class UserService {

	@Autowired
	private LoginDao logindao;
	@Autowired
	private SignupDao signupdao;
	@Autowired
	private LoginUserDao loginuserdao;
	public void signup(Signup signup) {
		// TODO Auto-generated method stub
		Login login = new Login();
		signupdao.save(signup);
		login.setId(signup.getId());
		login.setUsername(signup.getUsername());
		login.setPassword(signup.getPassword());
		logindao.save(login);
		
	}
	
	public Login getuserbyid(int id) {
		// TODO Auto-generated method stub
		return loginuserdao.findById(id).get();
		
	}

}
