package com.book45.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.book45.domain.BookVO;
import com.book45.domain.Criteria;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BookMapperTests {

	@Autowired
	private BookMapper mapper;
	
//	@Test
//	public void testGetList() {
//		
//		mapper.getList().forEach(book -> log.info(book));	
//	
//	}
	
	@Test
	public void testInsert() {
		
		BookVO book = new BookVO();
		
		book.setIsbn(12336333L);
		book.setCategory("도서 추가 카테고리");
		book.setTitle("도서 추가 제목");
		book.setPrice(300);
		book.setSummary("재시도");
		book.setAuthor("재시도");
		book.setPub("230202");
		book.setGrade("1");
		book.setPictureUrl("");
		book.setStock(10);
		
		mapper.insert(book);
		
		log.info(book);
		
	}
	
	@Test
	public void testInsertSelectKey() {
		
		BookVO book = new BookVO();
		
		book.setIsbn(1233347L);
		book.setCategory("select 도서 추가 카테고리");
		book.setTitle("select 도서 추가 제목");
		book.setPrice(300);
		book.setSummary("select재시도");
		book.setAuthor("select재시도");
		book.setPub("select230202");
		book.setGrade("1");
		book.setPictureUrl("");
		book.setStock(10);
		
		mapper.insertSelectKey(book);
		
		log.info(book);
	}
	
	@Test
	public void testRead() {
		
		BookVO book = mapper.read(20L);
		
		log.info(book);
	}
	
	@Test
	public void testDelete() {
		
		log.info("DELETE : " +mapper.delete(1233347L));
	}
	
	//테스트 시에는 mapper.xml과 값이 같게 만들어야 함..
	@Test
	public void testUpdate() {
		
		BookVO book = new BookVO();
		
		book.setIsbn(12336333L);
		book.setTitle("수정된 제목");
		book.setCategory("수정된 카테고리");
		book.setAuthor("수정된 저자");
		
		int count = mapper.update(book);
		log.info("update count: " +count);
	}
	
	@Test
	public void testPaging() {
		
		Criteria cri = new Criteria();
		
		cri.setPageNum(4);
		cri.setAmount(10);
		
		List<BookVO> list = mapper.getListWithPaging(cri);
		
		list.forEach(book -> log.info(book.getNum()));
	}
	
	@Test
	public void testSearch() {
		
		Criteria cri = new Criteria();
		cri.setKeyword("편의점");
		cri.setType("CT");
		
		List<BookVO> list = mapper.getListWithPaging(cri);
		
		list.forEach(book -> log.info(book));
	}
}
