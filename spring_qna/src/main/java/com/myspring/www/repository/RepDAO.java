package com.myspring.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.myspring.www.domain.PagingVO;
import com.myspring.www.domain.RepVO;

@Repository
public interface RepDAO {
	int insert(RepVO rvo);
	List<RepVO> selectList(@Param("bno")long bno,
			@Param("pgvo") PagingVO pgvo);
	int update(RepVO rvo);

}
