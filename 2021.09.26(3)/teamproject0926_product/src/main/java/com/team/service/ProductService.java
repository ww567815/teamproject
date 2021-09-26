package com.team.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.team.domain.AttachVO;
import com.team.domain.Criteria;
import com.team.domain.ProductVO;
import com.team.mapper.AttachMapper;
import com.team.mapper.ProductMapper;

@Service
public class ProductService {
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private AttachMapper attachMapper;
	
	public int register(ProductVO productVO) {
		return productMapper.insert(productVO);
	}
	
	@Transactional
	public void registerProductsAndAttaches(ProductVO productVO, List<AttachVO> attachList) {
		// attach 테이블의 bno 컬럼이 외래키로서
		// products 테이블의 pnum 컬럼을 참조하므로
		// products 레코드가 먼저 insert된 후 attach 레코드가 insert되야 함.
		productMapper.insert(productVO);
		
		if (attachList != null && attachList.size() > 0) {
			attachMapper.insertAttaches(attachList);
		}
	} // registerProductsAndAttaches
	
	public int nextNum() {
		return productMapper.nextNum();
	}

	//전부 지울 때 쓰는거 이건 구현해야함
//	public int deleteAll() {
//		return boardMapper.deleteAll();
//	}
	
	//전부 지울 때 쓰는 트랜잭션(attach까지 지우기)
//	@Transactional
//	public void deleteBoardAndAttaches(int num) {
//		attachMapper.deleteAttachesByBno(num);
//		boardMapper.deleteBoardByNum(num);
//	}
	
	
	
	
	public List<ProductVO> getProducts(){
		return productMapper.getProducts();
	}
	
	// 페이징으로 글 가져오기
	public List<ProductVO> getProducts(Criteria cri){
		
		int startRow = (cri.getPageNum() - 1) * cri.getAmount();
		cri.setStartRow(startRow); // 시작행번호 설정
		
		return productMapper.getProductsWithPaging(cri);
	}
	
	public int getTotalCount() {
		return productMapper.getTotalCount();
	}
	
	public int getTotalCountBySearch(Criteria cri) {
		return productMapper.getTotalCountBySearch(cri);
	}

	
	public ProductVO getProductAndAttaches(int pnum) {
//		BoardVO boardVO = boardMapper.getBoard(num);
//		List<AttachVO> attachList = attachMapper.getAttachesByBno(num);
//		boardVO.setAttachList(attachList);
//		return boardVO;
		
		return productMapper.getProductAndAttaches(pnum); // join 쿼리로 데이터 가져오기
	}
	
	@Transactional
	public void updateProductsAndInsertAttachesAndDeleteAttaches(ProductVO productVO, List<AttachVO> newAttachList, List<String> delUuidList) {
		
		if (newAttachList != null && newAttachList.size() > 0) {
			attachMapper.insertAttaches(newAttachList);
		}
		
		if (delUuidList != null && delUuidList.size() > 0) {
			attachMapper.deleteAttachesByUuids(delUuidList);
		}
	}	
	
}