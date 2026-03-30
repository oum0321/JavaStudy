package com.book45.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book45.domain.Criteria;
import com.book45.domain.PageDTO;
import com.book45.domain.QnaReplyPageDTO;
import com.book45.domain.QnaReplyDTO;
import com.book45.mapper.QnaMapper;
import com.book45.mapper.QnaReplyMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@AllArgsConstructor
@Service
public class QnaReplyServiceImpl implements QnaReplyService {
	
	@Autowired
	private QnaReplyMapper mapper;
	
	@Autowired
	private QnaMapper qnaMapper;

	@Override
	public int enrollReply(QnaReplyDTO dto) {
		
		log.info("enrollQnaReply 서비스 실행" + dto);
		
		qnaMapper.updateReplyCnt(dto.getQnum(), 1); //상태 처리를 위한 count 추가
		
		int result = mapper.enrollReply(dto);
		
		return result;
	}
	
	@Override
	public QnaReplyPageDTO replyList(Criteria cri) {
		
		QnaReplyPageDTO dto = new QnaReplyPageDTO();
		log.info("QnareplyList 서비스 실행 -> " + dto);
		dto.setList(mapper.getReplyList(cri));
		dto.setPageInfo(new PageDTO(cri, mapper.getReplyTotal(cri.getQnum())));
		
		return dto;
	}	
	
	@Override
	public int updateReply(QnaReplyDTO dto) {
		int result = mapper.updateReply(dto); 
		log.info("updateReply 서비스 실행 -> " + result);
		return result;
	}	
	
	@Override
	public QnaReplyDTO getUpdateReply(int renum) {
		log.info("getUpdateReply 서비스 실행 -> " + renum);
		return mapper.getUpdateReply(renum);
	}	
	
	@Override
	public int deleteReply(QnaReplyDTO dto) {
		log.info("문의글 답변 삭제 --> "+dto);
		
		int result = mapper.deleteReply(dto.getRenum()); 
		qnaMapper.updateReplyCnt(dto.getQnum(), -1);//상태 처리를 위한 count 추가
		return result;
	}		
	
	
//	댓글체크 부분 주석
//	@Override
//	public String checkQnaReply(QnaReplyVO qrVo) {
//		
//		Integer result = mapper.checkQnaReply(qrVo);
//		
//		if(result == null) {
//			return "0";
//		} else {
//			return "1";
//		}
//		
//	}	
	
	/* 구버전
	 * @Override public int register(QnaReplyVO qrVo) {
	 * 
	 * qnaMapper.updateReplyCnt(qrVo.getQNum(), 1); return mapper.insert(qrVo); }
	 * 
	 * @Override public QnaReplyVO get(int num) { return mapper.read(num); }
	 * 
	 * @Override public int modify(QnaReplyVO qrVo) { return mapper.update(qrVo); }
	 * 
	 * 
	 * @Override public List<QnaReplyVO> getList(Criteria cri, int qNum) { return
	 * mapper.getListWithPaging(cri, qNum); }
	 * 
	 * 
	 * 
	 * @Override public int remove(int num) { QnaReplyVO qrVo = mapper.read(num);
	 * return mapper.delete(num); }
	 * 
	 * @Override public QnaReplyPageDTO getListPage(Criteria cri, int qNum) { return
	 * new QnaReplyPageDTO( mapper.getCountByQnum(qNum),
	 * mapper.getListWithPaging(cri, qNum) ); }
	 */


	
}
