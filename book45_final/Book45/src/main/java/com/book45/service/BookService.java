package com.book45.service;

import java.util.List;

import com.book45.domain.BookVO;
import com.book45.domain.Criteria;


public interface BookService {

	public void register(BookVO book);
	
	public BookVO get(Long isbn);
	
	public boolean modify(BookVO book);
	
	public boolean remove(Long isbn);
	
	//public List<BookVO> getList();
	
	public List<BookVO> getList(Criteria cri);
	
	public int getTotal(Criteria cri);
	
	public BookVO getIsbnName(Long isbn); //리뷰 추가부분
	
	public List<BookVO> getCategory(String Category);
}
