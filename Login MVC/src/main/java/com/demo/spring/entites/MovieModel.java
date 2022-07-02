package com.demo.spring.entites;

import javax.persistence.*;

@Entity
public class MovieModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int mid;
	String MovieName;
	int price;
	
	
	public MovieModel(int mid, String movieName, int price) {
		super();
		this.mid = mid;
		MovieName = movieName;
		this.price = price;
	}
	public MovieModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	//getter and setters method.
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getMovieName() {
		return MovieName;
	}
	public void setMovieName(String movieName) {
		MovieName = movieName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	

}
