package com.team.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import com.team.domain.AttachVO;
import com.team.domain.ProductVO;

public interface AttachMapper {

	int insertAttach(AttachVO attachVO);
	
	int insertAttaches(List<AttachVO> attachList);
	
	List<AttachVO> getAttachesByBno(int bno);
	
	
	List<AttachVO> getAttachesByUuids(List<String> uuidList);
	
	
	@Select("SELECT * FROM attach WHERE uploadpath = #{uploadpath}")
	List<AttachVO> getAttachesByUploadpath(String uploadpath);
	
	
	@Delete("DELETE FROM attach WHERE bno = #{bno}")
	int deleteAttachesByBno(int bno);
	
	
	int deleteAttachesByUuids(List<String> uuidList);
}
