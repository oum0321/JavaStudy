package com.book45.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/*
create table reply (
    replyNum number(10) primary key,
    boardNum number(10) constraint fk_board_num REFERENCES board(num) on delete cascade,
    memberId VARCHAR2(20) constraint fk_member REFERENCES member(id) on delete cascade,
    nickname varchar2(30) not null,
    content varchar2(4000) not null,
    replyDate DATE default sysdate,
    secret char(10) DEFAULT 0 NOT NULL
);

create sequence reply_seq;

drop table reply;

drop sequence reply_seq;
commit;
 */

@Data
public class ReplyDTO {
   
   private int replyNum;
   private int boardNum;
   private String memberId;
   private String nickname;
   private String content;
   
   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
   private Date replyDate;
   
   private boolean secret;
   
   

}