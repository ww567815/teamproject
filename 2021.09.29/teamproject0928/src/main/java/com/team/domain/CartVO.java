package com.team.domain;

import java.util.Date;

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
public class CartVO {
	private int CartNum;
	private int productCount;
	private Date dateAndTime;
	private int totalPrice;
	private String cmid;
	private String cpid;
}
