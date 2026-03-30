package com.book45.domain;

import java.util.Date;

import lombok.Data;

/*
CREATE TABLE NOTICE (
	NUM	NUMBER(10,0)	NOT NULL PRIMARY KEY,
	TITLE	VARCHAR2(1000)	NULL,
	CONTENT	VARCHAR2(4000)	NULL,
    ID	VARCHAR2(20)	NOT NULL CONSTRAINT NOTICE_ID_FK REFERENCES MEMBER(ID) ON DELETE CASCADE,
	WRITEDATE	DATE	default sysdate,
	UPDATEDATE	DATE	default sysdate,
    READCOUNT	NUMBER(10)	DEFAULT 0
);
*/

@Data
public class NoticeVO {
	
	private Long num;
	private String title;
	private String content;
	private Date writeDate;
	private Date updateDate;
	private String id;
	private Long readCount;
		
	

}
