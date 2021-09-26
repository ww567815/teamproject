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
	private int sell_price;
	private String description;
	private String seller_id;
	
	private List<AttachVO> attachList;
	
}
