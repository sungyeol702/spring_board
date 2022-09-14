package com.myspring.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.myspring.www.domain.BoardDTO;
import com.myspring.www.domain.BoardVO;
import com.myspring.www.domain.ImageVO;
import com.myspring.www.domain.PagingVO;
import com.myspring.www.repository.BoardDAO;
import com.myspring.www.repository.CmtDAO;
import com.myspring.www.repository.ImageDAO;

@Service
public class BoardServeicImpl implements BoardService {

	@Inject
	BoardDAO bdao;
	@Inject
	CmtDAO cdao;
	@Inject
	ImageDAO idao;

	@Override
	public int register(BoardDTO bdto) {
		
			int isUp = bdao.insert(bdto.getBvo());
			if(bdto.getImageList()!= null) {
			if (isUp > 0) {
				long bno = bdao.selectLastbno();
				for(ImageVO ivo : bdto.getImageList()) {
					ivo.setBno(bno);
					isUp*=idao.insertImage(ivo);
				}
			}
			}
			return isUp;
		
	}

	@Override
	public List<BoardVO> getList(PagingVO pgvo) {
		return bdao.selectList(pgvo);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public BoardDTO getDetail(long bno) {
		bdao.updateReadCount(bno, 1);
		return new BoardDTO(bdao.selectOne(bno),idao.selectListImage(bno));
	}

	@Override
	public int modify(BoardDTO bdto) {
		int isUp = bdao.updateReadCount(bdto.getBvo().getBno(), -2);
		isUp = bdao.update(bdto.getBvo());
		if(bdto.getImageList()!= null) {
		if (isUp > 0) {
			for(ImageVO ivo : bdto.getImageList()) {
				ivo.setBno(bdto.getBvo().getBno());
				isUp*=idao.insertImage(ivo);
			}
		}
		}
		return isUp;
	}

	@Override
	public int remove(long bno) {
		int isUp = cdao.deleteAll(bno);
		if (isUp > 0) {
			isUp = idao.deleteAllImage(bno);
			isUp = bdao.delete(bno);
		}
		return isUp;
	}

	@Override
	public int getTotalCount(PagingVO pgvo) {
		return bdao.selectTotalCount(pgvo);
	}
	@Override
	public int removeIamge(String uuid) {
		long bno = idao.selectBno(uuid);
		int isUp = bdao.deleteFileCount(bno,1);
		if(isUp>0) {
			isUp = idao.deleteImage(uuid);
		}
		return isUp;
	}

}
