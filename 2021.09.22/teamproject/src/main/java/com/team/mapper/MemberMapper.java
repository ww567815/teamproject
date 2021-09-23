package com.team.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.team.domain.MemberVO;

public interface MemberMapper {

	@Insert("INSERT INTO member (id, passwd, name, birth, gender, phonenumber, email, address1, address2, address3, create_at) "
			+ " VALUES (#{id}, #{passwd}, #{name}, #{birth}, #{gender}, #{phonenumber}, #{email}, #{address1}, #{address2}, #{address3}, #{create_at}) ")
	int insert(MemberVO memberVO);
	
	@Select("SELECT * FROM member WHERE id = #{id}")
	MemberVO getMemberById(String id);
	
	@Select("SELECT * FROM member ORDER BY id")
	List<MemberVO> getMembers();
	
	@Select("SELECT COUNT(*) FROM member WHERE id = #{id}")
	int getCountById(String id);
	
	@Delete("DELETE FROM member WHERE id = #{id}")
	int deleteMemberById(String id);
	
	
	@Update(" UPDATE member "
			+ " SET  name = #{name}, birth = #{birth}, gender = #{gender}, phonenumber = #{phonenumber}, "
			+ "email = #{email}, address1 = #{address1}, address2 = #{address2}, address3 = #{address3}, create_at = #{create_at} "
			+ " WHERE id = #{id} ")
	void updateMember(MemberVO memberVO);

	@Update(" UPDATE member "
	         + " SET passwd = #{passwd}" + "WHERE id = #{id} ")
	   void updatePasswd(MemberVO memberVO);

	
}







