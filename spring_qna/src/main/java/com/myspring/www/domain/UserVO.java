package com.myspring.www.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@ToString
@Getter
@Setter
public class UserVO {
	private String id;
	private String pwd;
	private String nickName;
	private String regAt;
	private String lastLogin;
	private int grade;
}
