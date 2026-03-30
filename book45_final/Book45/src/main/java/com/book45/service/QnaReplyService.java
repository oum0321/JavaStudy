package com.book45.service;

import java.util.List;

import com.book45.domain.Criteria;
import com.book45.domain.PageDTO;
import com.book45.domain.QnaReplyPageDTO;
import com.book45.domain.QnaReplyDTO;

public interface QnaReplyService {

	//등록
	public int enrollReply(QnaReplyDTO dto);

	//페이징
	public QnaReplyPageDTO replyList(Criteria cri);	

	//수정
	public int updateReply(QnaReplyDTO dto);	

	//한개 선택 수정
	public QnaReplyDTO getUpdateReply(int renum);	

	//삭제
	public int deleteReply(QnaReplyDTO dto);	
}
