package com.myspring.www.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagingVO {
	private int pageNo; 
	private int qty; 
	private String scope;
	private String kwd;
	public PagingVO() {
		this(1,20); 
	}
	
	public PagingVO(int pageNo, int qty) {
		this.pageNo = pageNo; 
		this.qty = qty; 
	}
	public int getPageStart() {
		return (this.pageNo - 1) * qty;
	}
public String[] getScopeToArray() {
		return this.scope == null? new String[] {} : this.scope.split("");
	}
}
