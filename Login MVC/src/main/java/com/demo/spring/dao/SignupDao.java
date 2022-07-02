package com.demo.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.spring.entites.Signup;

public interface SignupDao extends JpaRepository<Signup, Integer>{

}
