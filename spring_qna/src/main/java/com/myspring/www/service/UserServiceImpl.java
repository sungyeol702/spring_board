package com.myspring.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.myspring.www.domain.UserVO;
import com.myspring.www.repository.UserDAO;
@Service
public class UserServiceImpl implements UserService {

	@Inject
	private UserDAO udao;
	
	@Override
	public int register(UserVO uvo) {
		return udao.insert(uvo);
	}

	@Override
	public List<UserVO> getList() {
		return udao.selectList();
	}

	@Override
	public UserVO getDitail(String id) {
		return udao.selectDetail(id);
	}

	@Override
	public UserVO login(UserVO uvo) {
		UserVO sesuvo = udao.selectLogin(uvo);
		if(sesuvo != null) {
			udao.lastLogin(uvo.getId());
		}
		return sesuvo;
	}

	@Override
	public int modify(UserVO uvo) {
		return udao.update(uvo);
	}

	@Override
	public int delete(String id) {
		return udao.delete(id);
	}

	@Override
	public int dupleCheck(String id) {
		return udao.selectId(id);
	}

	@Override
	public int remove(String id) {
		return udao.delete(id);
	}

}
