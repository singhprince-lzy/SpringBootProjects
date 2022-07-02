package com.demo.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.spring.dao.AddMovieDao;
import com.demo.spring.dao.MovieDao;
import com.demo.spring.entites.AddMovie;
import com.demo.spring.entites.MovieModel;

@Service
public class MovieService {
	
	@Autowired
	public MovieDao md;
	
	@Autowired
	public AddMovieDao amd;
	
	public void addMovie(AddMovie a) {
		MovieModel movie=new MovieModel();
		movie.setMid(a.getId());
		movie.setMovieName(a.getName());
		movie.setPrice(a.getPrice());
		md.save(movie);
	}
	
	public List<MovieModel>getMovie(){
		return (List<MovieModel>) md.findAll();
	}

	

}
