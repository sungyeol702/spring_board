package com.myspring.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.myspring.www.domain.BoardVO;
import com.myspring.www.domain.CmtVO;
import com.myspring.www.domain.PagingVO;
import com.myspring.www.handler.PagingHandler;
import com.myspring.www.repository.BoardDAO;
import com.myspring.www.repository.CmtDAO;
@Service
public class CmtServiceIpml implements CmtService {

	@Inject
	private CmtDAO cdao;
	
	@Inject
	private BoardDAO bdao;
	
	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public int post(CmtVO cvo) {
		int isUp = cdao.insert(cvo);
		if(isUp>0) {
			isUp = bdao.updateCmtQty(cvo.getBno(),1);
			
		}
		return isUp;
	}

	@Override
	public PagingHandler spread(long bno, PagingVO pgvo) {
		return new PagingHandler(cdao.selectList(bno,pgvo),
				pgvo,
				cdao.selectTotalCount(bno));
				
	}

	@Override
	public int modify(CmtVO cvo) {
		return cdao.update(cvo);
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public int remove(long cno, long bno) {
		int isUp =  cdao.delete(cno);
		if(isUp>0) {
			isUp = bdao.updateCmtQty(bno,-1);
		}
		return isUp;
	}

	@Override
	public int removeAll(long bno) {
		return cdao.deleteAll(bno);
	}

}
