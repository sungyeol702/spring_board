package com.myspring.www.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardVO {
	private long bno;
	private String title;
	private String category;
	private String content;
	private String writer;
	private String regAt;
	private String modAt;
	private int readCount;
	private int cmtQty;
	private int fileCount;
	public int updateCmtQty(long bno2, int i) {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
