package com.myspring.www.service;

import java.util.List;

import com.myspring.www.domain.BoardDTO;
import com.myspring.www.domain.BoardVO;
import com.myspring.www.domain.PagingVO;

public interface BoardService {
	int register(BoardDTO bdto);
	List<BoardVO> getList(PagingVO pgvo);
	BoardDTO getDetail(long bno);
	int modify(BoardDTO bdto);
	int remove(long bno);
	int getTotalCount(PagingVO pgvo);
	int removeIamge(String uuid);
}
