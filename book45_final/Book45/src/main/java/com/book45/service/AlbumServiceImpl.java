package com.book45.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.book45.domain.AlbumVO;
import com.book45.domain.Criteria;

import com.book45.mapper.AlbumMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class AlbumServiceImpl implements AlbumService {

	@Autowired
	private AlbumMapper mapper;
	
//	@Autowired
//	private AlbumImageMapper imageMapper;

	@Transactional
	@Override
	public void register(AlbumVO album) {
		
		log.info("register.. " + album);
		
		mapper.insertSelectKey(album);
		
//		if (album.getImageList() == null || album.getImageList().size() <= 0) {
//			return;
//		}
//		
//		album.getImageList().forEach(image -> {
//			
//			image.setProductNum(album.getProductNum());
//			mapper.imageEnroll(image);
//			
//		});
	}

	@Override
	public AlbumVO get(Long productNum) {
		
		log.info("get...." + productNum);
		return mapper.read(productNum);
	}

	
	@Override
	public boolean modify(AlbumVO album) {
		
		log.info("modify...." +album);
		
		
		
//		if(result == 1 && album.getImageList() != null && album.getImageList().size() > 0) {
//			
//			mapper.deleteImageAll(album.getProductNum());
//			
//			album.getImageList().forEach(image -> {
//				
//				image.setProductNum(album.getProductNum());
//				mapper.imageEnroll(image);
//			});
//		}
		
		return mapper.update(album) == 1;
	}

	@Override
	public boolean remove(Long productNum) {
		
		log.info("remove..." +productNum);
		return mapper.delete(productNum) == 1;
	}

	
	@Override
	public List<AlbumVO> getList(Criteria cri) {
		
		log.info("get List with criteria : " +cri);
		
		List<AlbumVO> list = mapper.getListWithPaging(cri);
		
//		list.forEach(album-> {
//			
//			Long productNum = album.getProductNum();
//			
//			List<AlbumImageVO> imageList = imageMapper.getAlbumImageList(productNum);
//			
//			album.setImageList(imageList);
//			
//		});
		
		return mapper.getListWithPaging(cri);
	}
	
	@Override
	public int getTotal(Criteria cri) {
		
		log.info("get total count");
		return mapper.getTotalCount(cri);
	}

	@Override
	public AlbumVO getProductNumName(Long productNum) {

		return mapper.getProductNumName(productNum);
	}

	@Override
	public List<AlbumVO> getCategory(String category) {
		return mapper.getCategory(category);
	}

//	@Override
//	public List<AlbumImageVO> getAlbumImageInfo(Long productNum) {
//		
//		log.info("getAlbumImageInfo...serviceImpl..");
//		
//		return mapper.getAlbumImageInfo(productNum);
//	}

	
	
	
}
