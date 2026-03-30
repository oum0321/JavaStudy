//package com.book45.service;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.book45.domain.AlbumImageVO;
//import com.book45.mapper.AlbumImageMapper;
//
//import lombok.extern.log4j.Log4j;
//
//@Service
//@Log4j
//public class AlbumImageServiceImpl implements AlbumImageService{
//	
//	@Autowired
//	private AlbumImageMapper imageMapper;
//
//	@Override
//	public List<AlbumImageVO> getAlbumImageList(Long productNum) {
//
//		log.info("getAlbumImageList.....");
//		
//		return imageMapper.getAlbumImageList(productNum);
//	}
//	
//	
//
//}
