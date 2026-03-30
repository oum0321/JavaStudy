package com.book45.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.book45.domain.BookVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BookServiceTests {

	@Autowired
	private BookService service;
	
	@Test
	public void testExist() {
		
		log.info(service);
		
	}
	
	@Test
	public void testRegister() {
		
		BookVO book = new BookVO();
		
		book.setIsbn(2222L);
		book.setCategory("서비스 테스트");
		book.setTitle("서비스 테스트");
		book.setPrice(34);
		book.setSummary("서비스 테스트");
		book.setAuthor("서비스 테스트");
		book.setPub("서비스 테스트");
		book.setGrade("1");
		book.setPictureUrl("");
		book.setStock(4);
		
		service.register(book);
	}
	
	@Test
	public void testGet() {
		
		log.info(service.get(187L));
	}
	
	@Test
	public void testDelete() {
		
		log.info(service.remove(12336333L));
	}
	
	@Test
	public void testUpdate() {
		
		BookVO book = service.get(187L);
		
		if(book == null) {
			return;
		}
		
		book.setNum(183);
		book.setCategory("서비스 수정");
		book.setTitle("서비스 수정");
		book.setPrice(3);
		book.setSummary("서비스 수정");
		book.setAuthor("서비스 수정");
		book.setPub("서비스 수정");
		book.setGrade("2");
		book.setPictureUrl("");
		book.setStock(4);
		
		log.info("modify result: " +service.modify(book));
		
	}
}
