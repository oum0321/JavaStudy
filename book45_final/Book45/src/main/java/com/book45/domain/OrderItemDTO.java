package com.book45.domain;

import lombok.Data;

@Data
public class OrderItemDTO {
	//orderItem테이블의 기본키
	private int orderItemNum;
	//orders테이블의 기본키. 주문번호
	private String orderNum;  
	
	private String id;
	private Long isbn;
	private Long productNum;
	private int price;
	private int albumPrice;
	private int amount;
	//DB테이블 존재하지 않는 데이터
	private int totalPrice; //나중에 빠질 수도 있음
	
	public void initTotal() {
		this.totalPrice = this.price * this.amount + this.albumPrice * this.amount;
	}
}
