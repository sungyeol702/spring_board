package com.myspring.www.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BoardDTO  {
	private BoardVO bvo;
	private List<ImageVO> imageList;
}
