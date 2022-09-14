package com.myspring.www;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.myspring.www.domain.UserVO;
import com.myspring.www.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { com.myspring.www.config.RootConfig.class })
public class UsersSvcTest {
	
	@Inject
	private UserService usv;
	
	@Test
	public void loginTest() throws Exception{
		UserVO uvo = new UserVO();
		uvo.setEmail("users10@users.com");
		uvo.setPwd("1234");
		UserVO suvo = usv.login(uvo);
		log.info(">>>> login test {}",suvo);
		
	}
	
	@Test
	public void registerTest() throws Exception{
		UserVO uvo = new UserVO();
		uvo.setEmail("usersvc@users.com");
		uvo.setPwd("1234");
		uvo.setNickName("users");
		int isOk =usv.register(uvo);
		log.info(">>>> register test, {}",isOk > 0 ? "ok":"fail");
		
	}
	
	@Test
	public void selectListUsersTest() throws Exception{
		List<UserVO> list = usv.getList();
		for (UserVO uvo : list) {
			log.info(">>>>usersList{},",uvo);
		}
	}
}
