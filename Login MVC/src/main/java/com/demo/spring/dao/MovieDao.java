package com.demo.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.demo.spring.entites.MovieModel;

public interface MovieDao extends JpaRepository<MovieModel, Integer> {

}
