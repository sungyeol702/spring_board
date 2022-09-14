package com.myspring.www.repository;

import java.util.List;

import com.myspring.www.domain.UserVO;

public interface UserDAO {
	int insert(UserVO uvo);
	List<UserVO> selectList();
	UserVO selectDetail(String email);
	UserVO selectLogin(UserVO uvo);
	int lastLogin(String id);
	int update(UserVO uvo);
	int delete(String id);
	int selectId(String id);
}
