package com.myspring.www.service;

import java.util.List;

import com.myspring.www.domain.UserVO;

public interface UserService {
	int register(UserVO uvo);
	List<UserVO> getList();
	UserVO getDitail(String id);
	UserVO login(UserVO uvo);
	int modify(UserVO uvo);
	int delete(String id);
	int dupleCheck(String id);
	int remove(String id);
}
