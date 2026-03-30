package com.book45.service;

import static org.junit.Assert.assertNotNull;

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
public class BoardServiceTests {
	
	@Autowired
	private BoardService service;
	
	@Test
	public void testExist() {
		
		log.info(service);
		assertNotNull(service);
	}
	
	@Test
	public void testRegister() {
		
		BoardVO board = new BoardVO();
		board.setPass("1234");
		board.setTitle("새로 작성하는 제목");
		board.setContent("새로 작성하는 내용");
		board.setMemberId("user");
		
		
		service.register(board);
		
		log.info("생성된 게시물의 번호 : " + board.getNum());
	}
	
	@Test
	public void testGetList() {
		
//		service.getList().forEach(board -> log.info(board));
		service.getList(new Criteria(1, 10)).forEach(board -> log.info(board));
	}
	
	@Test
	public void testGet() {
		
		log.info(service.get(1));
	}
	
	@Test
	public void testDelete() {
		
		log.info("REMOVE RESULT : " + service.remove(1));
	}
	
	@Test
	public void testUpdate() {
		
		BoardVO board = service.get(3);
		
		if (board == null) {
			return;
		}
		
		board.setTitle("제목 수정");
		board.setContent("내용 수정");
		
		log.info("MODIFY RESULT : " + service.modify(board));
	}
	
}
