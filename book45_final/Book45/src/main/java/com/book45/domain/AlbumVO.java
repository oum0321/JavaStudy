package com.book45.domain;

import java.util.List;

import lombok.Data;

@Data
public class AlbumVO {

	private int num;
	private Long productNum;
	
	private String category;
	private String albumTitle;
	private int albumPrice;
	
	private String singer;
	private String ent;
	private String releaseDate;
	private String albumPictureUrl;
	private int stock;
	
	private double ratingAvg;
	
//	이미지 등록 
//	private String uploadPath;
//	private String uuid;
//	private String fileName;
	
//	private List<AlbumImageVO> imageList;
}
