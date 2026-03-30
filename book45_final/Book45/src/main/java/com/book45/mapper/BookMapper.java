package com.book45.mapper;

import java.util.List;

import com.book45.domain.BookVO;
import com.book45.domain.Criteria;


public interface BookMapper {


	//public List<BookVO> getList();

	public void insert(BookVO book);
	
	public void insertSelectKey(BookVO book);    
	
	public BookVO read(Long isbn);
	
	public int delete(Long isbn);
	
	public int update(BookVO book);
	
	public List<BookVO> getListWithPaging(Criteria cri); //페이징부분 우리언니파일에는 없음 이유는 내일 물어보기?
	
	public int getTotalCount(Criteria cri);
	
	public BookVO getIsbnName(Long isbn); //댓글- 추가 
	
	public List<BookVO> getCategory(String Category);
	
//	public void imageRegister(ImageVO image);
}
