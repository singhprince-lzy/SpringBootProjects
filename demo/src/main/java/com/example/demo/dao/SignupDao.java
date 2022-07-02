package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Signup;

public interface SignupDao extends JpaRepository<Signup, Integer> {

}
