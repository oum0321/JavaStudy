package com.book45.service;

import java.util.List;

import com.book45.domain.Criteria;
import com.book45.domain.QnaVO;


public interface QnaService {

	public void register(QnaVO qVo);
	
	public QnaVO get(int qnum);
	
	public boolean modify(QnaVO qVo);
	
	public boolean remove(int qnum);
	
	public List<QnaVO> getList(Criteria cri);
	
	public int getTotalCount(Criteria cri);
	
//	추가! Qna title 
	public QnaVO getQnaTitle(int qnum);
	
	
	
}
