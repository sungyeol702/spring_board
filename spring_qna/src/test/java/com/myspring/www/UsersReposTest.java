package com.myspring.www;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.myspring.www.domain.UserVO;
import com.myspring.www.repository.UserDAO;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { com.myspring.www.config.RootConfig.class })
public class UsersReposTest {

	@Inject
	private UserDAO udao;
	
	@Test
	public void insertUsersTest() throws Exception{
		int isOk = 1;
		for (int i = 0; i < 150 ; i++) {
			UserVO uvo = new UserVO();
			uvo.setEmail("users"+i+"@users.com");
			uvo.setPwd("1234");
			uvo.setNickName("users"+i);
			isOk *=udao.insert(uvo);
		}
		log.info(">>>> insert test{}", isOk >0? "ok":"fail");
	}
	@Test
	public void selectListUsersTest() throws Exception{
		List<UserVO> list = udao.selectList();
		for (UserVO uvo : list) {
			log.info(">>>>usersList{},",uvo);
		}
	}
	
	@Test
	public void selectDetailUsersTest() throws Exception{
		UserVO uvo = udao.selectDetail("users2@users.com");
		log.info(">>>>usersDetail -{},",uvo);
		
	}
	@Test
	public void updateUsersTest() throws Exception{
		UserVO uvo = new UserVO();
		uvo.setEmail("users1@users.com");
		uvo.setGrade(11);
		uvo.setNickName("modtest");
		uvo.setPwd("1111");
		 int isOk = udao.update(uvo);
		log.info(">>>> update test{}", isOk >0? "ok":"fail");
	}
	@Test
	public void deleteUsersTest() throws Exception{
		int isOk = udao.delete("users1@users.com");
		
		log.info(">>>> delete test{}", isOk >0? "ok":"fail");
	}
	@Test
	public void lastLoginUsersTest() throws Exception{
		int isOk = udao.lastLogin("users2@users.com");
		log.info(">>>> lastLogin test-{}", isOk >0? "ok":"fail");
	}
	
	
}
