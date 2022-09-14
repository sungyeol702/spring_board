package com.myspring.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.myspring.www.domain.BoardVO;
import com.myspring.www.domain.PagingVO;

public interface BoardDAO {
	int insert(BoardVO bvo);
	List<BoardVO> selectList(PagingVO pgvo);
	BoardVO selectOne(long bno);
	int update(BoardVO bvo);
	int delete(long bno);
	int selectTotalCount(PagingVO pgvo);
	int updateReadCount(@Param("bno") long bno, @Param("i") int i);
	int updateCmtQty(@Param("bno") long bno, @Param("i") int i);
	long selectLastbno();
	int deleteFileCount(@Param("bno") long bno,@Param("i") int i);
}
