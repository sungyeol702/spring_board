package com.myspring.www.service;

import java.util.List;

import com.myspring.www.domain.CmtVO;
import com.myspring.www.domain.PagingVO;
import com.myspring.www.handler.PagingHandler;

public interface CmtService {
	int post(CmtVO cvo);
	PagingHandler spread(long bon, PagingVO pgvo);
	int modify(CmtVO cvo);
	int remove(long cno, long bno);
	int removeAll(long bno);
	

}
