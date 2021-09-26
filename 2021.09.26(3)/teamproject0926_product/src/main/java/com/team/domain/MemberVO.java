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
public class MemberVO {

	private String id;
	private String passwd;
	private String name;
	private String gender;
	private String phonenumber;
	private String email;
	private String address1;
	private String address2;
	private String address3;
	private String birth;
	private Date create_at;
	private ProfilePicVO profilePicVO;
	private int temperature;
}







