package com.book45.domain;

import java.util.List;

import lombok.Data;

@Data
public class BookVO {

	private int num;
	private Long isbn;
	
	private String category;
	private String title;
	private int price;
	
	private String summary;
	private String author;
	private String pub;
	private String grade;
	private String pictureUrl;
	private int stock;
	
	private double ratingAvg;
	
	
}
