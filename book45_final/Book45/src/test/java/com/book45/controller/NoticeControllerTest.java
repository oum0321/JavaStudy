package com.book45.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.extern.log4j.Log4j;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	               "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
@Log4j
public class NoticeControllerTest {

	@Autowired
	private WebApplicationContext ctx;
	
	private MockMvc  mockMvc;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	@Test
	public void testList() throws Exception {
		log.info(mockMvc.perform(MockMvcRequestBuilders.get("/notice/list")).andReturn().getModelAndView().getModelMap());
	}
	
	@Test
	public void testRegister() throws Exception {
		log.info(mockMvc.perform(MockMvcRequestBuilders
				.post("/notice/register")
				.param("title", "제목 1111")
				.param("content", "내용ㅇ22222")
				.param("id", "관리자1"))
				.andReturn()
				.getModelAndView()
				.getModelMap());
	}
	
	@Test
	public void testGet() throws Exception {
		log.info(mockMvc.perform(MockMvcRequestBuilders
				.get("/notice/get")
				.param("num", "1"))
				.andReturn().getModelAndView().getModelMap());
	}
	
	@Test
	public void testModify() throws Exception {
		log.info(mockMvc.perform(MockMvcRequestBuilders
				.post("/notice/modify")
				.param("num", "1")
				.param("title", "수정된 제목111")
				.param("content", "수정된 내용11122123"))
				.andReturn().getModelAndView().getModelMap());
	}
	
	@Test
	public void testRemove() throws Exception {
		log.info(mockMvc.perform(MockMvcRequestBuilders.post("/notice/remove").param("num", "1")).andReturn().getModelAndView().getModelMap());
	}
	
	@Test
	public void testListPaging() throws Exception {
		log.info(mockMvc.perform(MockMvcRequestBuilders.get("/notice/list").param("pageNum", "2").param("amount", "50")).andReturn().getModelAndView().getModelMap());
	}
}


	
