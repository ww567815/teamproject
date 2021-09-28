package com.team.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProductVO {
	
	private int pnum;
	private String productName;
	private String title;
	private String status;
	private int sellprice;
	private String description;
	private String sellerid;
	
	private List<AttachVO> attachList;
	
}
