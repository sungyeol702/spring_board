package com.myspring.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.myspring.www.domain.CmtVO;
import com.myspring.www.domain.PagingVO;

public interface CmtDAO {
	int insert(CmtVO cvo);
	List<CmtVO> selectList(@Param("bno")long bno, @Param("pgvo") PagingVO pgvo);
	int update(CmtVO cvo);
	int delete(long cno);
	int deleteAll(long bno);
	int selectTotalCount(long bno);
	

}
