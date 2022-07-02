package com.demo.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.spring.entites.AddMovie;

public interface AddMovieDao extends JpaRepository<AddMovie, Integer> {

}
