package com.demo.spring.dao;

import org.springframework.data.repository.CrudRepository;

import com.demo.spring.entites.Login;

public interface LoginUserDao extends CrudRepository<Login, Integer>{

}
