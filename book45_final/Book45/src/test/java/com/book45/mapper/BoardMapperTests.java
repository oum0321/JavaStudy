package com.book45.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.book45.domain.BoardVO;
import com.book45.domain.Criteria;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {
	
	@Autowired
	private BoardMapper mapper;
	
	@Test
	public void testGetList() {
		
		mapper.getList().forEach(board -> log.info(board));
	}
	
	@Test
	public void testInsert() {
		
		BoardVO board = new BoardVO();
		
		board.setPass("1234");
		board.setTitle("새로 작성하는 제목");
		board.setContent("새로 작성하는 문장");
		board.setMemberId("user");
		
		
		mapper.insert(board);
		
		log.info(board);
	}
	
	@Test
	public void testInsertSelectKey() {
		
		BoardVO board = new BoardVO();
		
		board.setPass("1234");
		board.setTitle("새로 작성하는 제목 Select Key");
		board.setContent("새로 작성하는 문장 Select Key");
		board.setMemberId("user");
		
		
		mapper.insertSelectKey(board);
		
		log.info(board);
	}
	
	@Test
	public void testRead() {
		
		BoardVO board = mapper.read(1);
		
		log.info(board);
	}
	
	@Test
	public void testDelete() {
		
		log.info("delete count : " + mapper.delete(1));
	}
	
	@Test
	public void testUpdate() {
		
		BoardVO board = new BoardVO();
		
		board.setNum(3);
		board.setTitle("수정된 제목");
		board.setContent("수정된 문장");
		
		int count = mapper.update(board);
		log.info("update count : " + board);
	}
	
	@Test
	public void testSearch() {
		
		Criteria cri = new Criteria();
		
		cri.setKeyword("테스트");
		cri.setType("TC");
		
		List<BoardVO> list = mapper.getListWithPaging(cri);
		
		list.forEach(board -> log.info(board));
	}
	
	@Test
	public void testPaging() {
		
		Criteria cri = new Criteria();
		
		cri.setPageNum(1);
		cri.setAmount(10);
		
		List<BoardVO> list = mapper.getListWithPaging(cri);
		
		list.forEach(board -> log.info(board));
	}
}
