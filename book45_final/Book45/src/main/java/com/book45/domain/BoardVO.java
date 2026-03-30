package com.book45.domain;

import java.util.Date;

import lombok.Data;
/*
 create table board (
    NUM NUMBER(10) primary key,
    MEMBERID VARCHAR2(20) constraint fk_member_id REFERENCES member(id) on delete cascade,
    PASS VARCHAR2(20) NOT NULL,
    TITLE VARCHAR2(1000) NOT NULL,
    CONTENT VARCHAR2(4000) NOT NULL,
    WRITEDATE DATE default sysdate,
    UPDATEDATE DATE default sysdate,
    READCOUNT NUMBER(30) default 0,
    replyCnt number(30) default 0,
    blind char(10) default 0
);
 */
@Data
public class BoardVO {
	private int num;
	private String memberId;
	private String pass;
	private String title;
	private String content;
	private Date writeDate;
	private Date updateDate;
	private int readCount;
	private int replyCnt;
	private boolean blind;
}
