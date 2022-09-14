package com.myspring.www.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CmtVO {
	
	private long cno;
	private long bno;
	private String writer;
	private String content; 
	private String regAt; 
	private String modAt;
	
	public CmtVO() {}

	public CmtVO(long bno, String writer, String content) {
		this.bno = bno;
		this.writer = writer;
		this.content = content;
	}
	public CmtVO(long cno, long bno, String writer, String content) {
		this.cno = cno;
		this.writer = writer;
		this.content = content;
	}  
	
	
	
}
 

