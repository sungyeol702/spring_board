package com.myspring.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.myspring.www.domain.PagingVO;
import com.myspring.www.domain.RepVO;
import com.myspring.www.repository.RepDAO;
@Service
public class RepServiceImpl implements RepService {

	@Inject
	RepDAO rdao;
	
	@Override
	public int post(RepVO rvo) {
		return rdao.insert(rvo);
	}

	@Override
	public List<RepVO> getList(long bno, PagingVO pgvo) {
		return rdao.selectList(bno,  pgvo);
	}

	@Override
	public int modify(RepVO rvo) {
		return rdao.update(rvo);
	}

}
