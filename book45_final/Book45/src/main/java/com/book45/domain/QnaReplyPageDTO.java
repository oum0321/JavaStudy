package com.book45.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class QnaReplyPageDTO {

	//qna 답변 처리 상태를 위한 cnt 
	private int replyCnt;
	
	List<QnaReplyDTO> list;
	
	PageDTO PageInfo;
}
