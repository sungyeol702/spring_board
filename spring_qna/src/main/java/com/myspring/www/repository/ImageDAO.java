package com.myspring.www.repository;

import java.util.List;

import com.myspring.www.domain.ImageVO;

public interface ImageDAO {

	int insertImage(ImageVO ivo);
	List<ImageVO> selectListImage(long pno);
	int deleteImage(String uuid);
	int deleteAllImage(long pno);
	long selectBno(String uuid);
}
