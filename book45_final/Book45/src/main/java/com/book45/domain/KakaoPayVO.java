package com.book45.domain;

import java.util.Date;

import lombok.Data;

@Data
public class KakaoPayVO {
	private String tid, next_redirect_pc_url;
	private Date created_at;
}
