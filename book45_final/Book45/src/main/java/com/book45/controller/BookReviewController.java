package com.book45.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.book45.domain.BookReviewDTO;
import com.book45.domain.BookReviewPageDTO;
import com.book45.domain.Criteria;
import com.book45.service.BookReviewService;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/bookReview")
@Log4j
public class BookReviewController {

	@Autowired
	private BookReviewService service;
	
	/* 댓글 등록 */
	@PostMapping("/enroll")
	public void enrollBookReviewPOST(BookReviewDTO dto) {
		
		service.enrollBookReview(dto);
	}
	
	/* 댓글 체크 */
	/* memberId, bookId 파라미터 */
	/* 존재 : 1 / 존재x : 0 */
	@PostMapping("/check")
	public String bookReviewCheckPOST(BookReviewDTO dto) {
		
		return service.checkBookReview(dto);
	}
	
	/* 댓글 페이징 */
	@GetMapping(value="/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public BookReviewPageDTO bookReviewListPOST(Criteria cri) {
		return service.bookReviewList(cri);
	}
	
	/* 댓글 수정 */
	@PostMapping("/update")
	public void bookReviewModifyPOST(BookReviewDTO dto) {
		service.updateBookReview(dto);
	}
	
	/* 댓글 삭제 */
	@PostMapping("/delete")
	public void bookReviewDeletePOST(BookReviewDTO dto) {
		service.deleteBookReview(dto);
	}

}
