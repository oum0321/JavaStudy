package com.book45.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.book45.domain.BookReviewDTO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BookReviewMapperTests {

	@Autowired
	private BookReviewMapper mapper;
	
	@Test
	public void testBookReviewEnrollTest() {
		
		Long isbn = 9788954699914L;
		String id="admin";
		String nickname = "가상닉네임";
		double rating = 3.5;
		String content = "리뷰 테스트";
		
		BookReviewDTO dto = new BookReviewDTO();
		
		dto.setIsbn(isbn);
		dto.setId(id);
		dto.setNickname(nickname);
		dto.setRating(rating);
		dto.setContent(content);
		
		mapper.enrollBookReview(dto);
		
	}
	
	@Test
	public void testexist() {
		
		
		BookReviewDTO dto = new BookReviewDTO();
		
		dto.setId("admin");
		dto.setIsbn(9788901260716L);
		
		mapper.checkBookReview(dto);
		
	}
	
	@Test
	public void testUpdate() {
		
		int num = 25;
		String content = "수정됨";
		double rating = 5.5;
		
		
		BookReviewDTO dto = new BookReviewDTO();
		
		
		dto.setNum(num);
		dto.setContent(content);
		dto.setRating(rating);
		
		mapper.updateBookReview(dto);
		
	}
	
	
	
	
}
