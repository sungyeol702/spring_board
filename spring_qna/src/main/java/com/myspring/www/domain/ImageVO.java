package com.myspring.www.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageVO {
	private String uuid;
	private String saveDir;
	private String fileName;
	private long bno;
	private long fileSize;
	private String regAt;
}
