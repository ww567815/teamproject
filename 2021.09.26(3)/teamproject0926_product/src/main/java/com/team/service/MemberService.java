package com.team.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.team.domain.MemberVO;
import com.team.domain.ProfilePicVO;
import com.team.mapper.MemberMapper;

@Service // @Component 계열 애노테이션
//@Transactional
public class MemberService {
	
	private MemberMapper memberMapper;

	// @Autowired 애노테이션이 생성자에서는 생략가능 
	public MemberService(MemberMapper memberMapper) {
		this.memberMapper = memberMapper;
	}
	
	// 회원가입하기
	public void register(MemberVO memberVO) {
		
//		long beginTime = System.currentTimeMillis();
		
		memberMapper.insert(memberVO);
		
//		long endTime = System.currentTimeMillis();
		
//		long diff = endTime - beginTime;
//		System.out.println("메소드 실행시간 : " + diff + "ms");
	}
	
	public MemberVO getMemberById(String id) {
		MemberVO memberVO = memberMapper.getMemberById(id);
		return memberVO;
	}
	
	public List<MemberVO> getMembers() {
		return memberMapper.getMembers();
	}
	
	public int getCountById(String id) {
		return memberMapper.getCountById(id);
	}
	
	public int deleteMemberById(String id) {
		int rowCount = memberMapper.deleteMemberById(id);
		return rowCount;
	}
	
	//내 정보 수정하기
		public void updateMember(MemberVO memberVO) {
			memberMapper.updateMember(memberVO);
		}
		//비밀번호 변경하기
		public void updatePasswd(MemberVO memberVO) {
			memberMapper.updatePasswd(memberVO);
		}
		/* 나의 정보 업데이트 */
		public void updateMyInfo(MemberVO memberVO, ProfilePicVO profilePicVO) {
			memberMapper.updateMember(memberVO);

		}

}





