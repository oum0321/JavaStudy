package com.book45.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.book45.domain.Criteria;
import com.book45.domain.NoticeVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class NoticeMapperTest {
	@Autowired
	NoticeMapper mapper;
	
	@Test
	public void testGetList() {
		mapper.getList().forEach(n -> log.info(n));
	}
	
	@Test
	public void testInsert() {
		NoticeVO nVo = new NoticeVO();
		nVo.setTitle("New 공지글");
		nVo.setContent("New 공지게시글");
		nVo.setId("admin00");
		mapper.insert(nVo);
	}
	
	@Test
	public void testInsertSelectKey() {
		NoticeVO nVo = new NoticeVO();
		nVo.setTitle("공지글11111111");
		nVo.setContent("공지게시글1111");
		nVo.setId("admin00");
		mapper.insertSelectKey(nVo);
		log.info(nVo);
	}
	
	@Test
	public void testRead() {
		NoticeVO nVo = mapper.read(1L);
		log.info(nVo);
	}
	
	@Test
	public void testDelete() {
		log.info(mapper.delete(43L));
	}
	
	@Test
	public void testUpdate() {
		NoticeVO nVo = new NoticeVO();
		nVo.setNum(2L);
		nVo.setTitle("수정한 게시글입니다.");
		nVo.setContent("수정됨 아놔");
		mapper.update(nVo);
		log.info(nVo);
	}
	
	@Test
	public void testGetListWithPaging() {
		Criteria cri = new Criteria();
		cri.setPageNum(3);
		cri.setAmount(10);
		
		List<NoticeVO> list = mapper.getListWithPaging(cri);
		
		list.forEach(n -> log.info(n.getNum()));
	}
	
	@Test
	public void testSearch() {
		Criteria cri = new Criteria();
		cri.setKeyword("수정");
		cri.setType("TC");
		
		List<NoticeVO> list = mapper.getListWithPaging(cri);
		
		list.forEach(n -> log.info(n));
	}
}
