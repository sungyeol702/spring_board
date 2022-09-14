package com.myspring.www;

import java.util.Iterator;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.myspring.www.domain.BoardVO;
import com.myspring.www.repository.BoardDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { com.myspring.www.config.RootConfig.class })
public class BoardReposTest {
	private static Logger log = LoggerFactory.getLogger(BoardReposTest.class);
	@Inject
	BoardDAO bdao;
	
	@Test
	public void BoardInsertTest() throws Exception{
		int isUp =1;
		for (int i = 0; i < 10; i++) {
			isUp *= bdao.insert(new BoardVO("title", "content","writer"));
		}
		log.info(">>> Test of insert board : {}",isUp >0 ? "OK": "fail");
	}
	@Test
	public void insertProductDummyTest() throws Exception {
		for (int i = 1; i <= 50; i++) {
			BoardVO bvo = new BoardVO();
			bvo.setCategory("spring");
			bvo.setTitle("감사해요");
			bvo.setContent("테스트입니다 ");
			bvo.setWriter("tester");
			bdao.insert(bvo);
					
		}
	}

}
