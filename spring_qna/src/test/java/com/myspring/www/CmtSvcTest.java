package com.myspring.www;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.myspring.www.service.CmtService;
import com.myspring.www.domain.CmtVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { com.myspring.www.config.RootConfig.class })
public class CmtSvcTest {
	@Inject
	private CmtService csv;
	@Test
	public void insertCommentDummiesTest() throws Exception {
		for (int j = 1; j <= 256; j++) {
			int x = (int) (Math.random() * 253);
			for (int i = 0; i < x; i++) {
				csv.post(new CmtVO((long)j,
						"tester" + (int)(Math.random() * 253),
						"Comment Dummy Content for" + j));
			}
		}
	}
	
		
}
