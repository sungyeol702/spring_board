package com.myspring.www.handler;

import java.util.List;

import com.myspring.www.domain.CmtVO;
import com.myspring.www.domain.PagingVO;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Getter
@Setter
public class PagingHandler {
	private int startPage;
	private int endPage; 
	private boolean prev, next; 

	private int totalCount; 
	private PagingVO pgvo; 
	private List<CmtVO> list;
	public PagingHandler() {}
	
	public PagingHandler(PagingVO pgvo, int totalCount) {
		this.pgvo = pgvo;
		this.totalCount = totalCount;

		this.endPage = (int) (Math.ceil(pgvo.getPageNo() / (10 * 1.0))) * 10;
		this.startPage = endPage -9;
		int realEndPage = (int) (Math.ceil((totalCount * 1.0) / 20));
		log.info(">>>endPage{}",endPage);
		log.info(">>>realEndPage{}",realEndPage);
		if (realEndPage < this.endPage) {
			this.endPage = realEndPage;
		}
		this.prev = startPage > 1;
		this.next = endPage < realEndPage;
	}
	
	public PagingHandler(List<CmtVO> cmtList, PagingVO pgvo, int totalCount) {
			this( pgvo, totalCount);
			this.list = cmtList;
			
	}
}
