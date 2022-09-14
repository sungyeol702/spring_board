package com.myspring.www.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.myspring.www.domain.PagingVO;
import com.myspring.www.domain.RepVO;

public interface RepService {
	int post(RepVO rvo);
	List<RepVO> getList(@Param("bno")long bno,
			@Param("pgvo") PagingVO pgvo);
	int modify(RepVO rvo);
}
