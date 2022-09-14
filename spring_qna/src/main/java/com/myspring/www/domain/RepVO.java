package com.myspring.www.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RepVO {
	
	private long rno;
	@NonNull
	private long cno;
	@NonNull
	private long bno;
	private String writer;
	private String content;
	private String regAt;
	private String modAt;
}
