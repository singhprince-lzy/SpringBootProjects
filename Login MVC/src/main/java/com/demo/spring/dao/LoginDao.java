package com.demo.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.spring.entites.Login;

public interface LoginDao extends JpaRepository<Login, Integer>{

}
