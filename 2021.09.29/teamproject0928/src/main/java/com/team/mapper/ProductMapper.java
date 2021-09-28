package com.team.mapper;


import java.util.List;

import com.team.domain.Criteria;
import com.team.domain.ProductVO;

public interface ProductMapper {

//	@Insert("INSERT INTO products (pid, productName, title, status, sell_price, description, seller_id) "
//			+ " VALUES (#{pid}, #{productName}, #{title}, #{status}, #{sell_price}, #{description}, #{seller_id}) ")
	int insert(ProductVO productVO);
	
	
	int nextNum();
	
	int deleteAll();
	
	int deleteProductByNum(int num);
	
	List<ProductVO> getProducts();
	
	List<ProductVO> getProductsWithPaging(Criteria cri);
	
	int getTotalCount();
	
	int getTotalCountBySearch(Criteria cri);
	
	int deleteBoardByNum(int num); // 글번호에 해당하는 글 한개 삭제하기
	
	List<ProductVO> getBoards();  // 전체 게시글 내용 가져오기

	ProductVO getBoard(int num); // 글번호에 해당하는 글 한개 가져오기
	
	ProductVO getProduct(int pnum);
	
	ProductVO getProductAndAttaches(int pnum);  // 글번호에 해당하는 글 한개와 첨부파일 모두 가져오기
	
	void updateReadcount(int num); // 글번호에 해당하는 글의 조회수를 1 증가시키기
	
	void updateBoard(ProductVO productVO); // 글번호에 해당하는 글의 글제목, 글내용, 날짜, IP주소 수정하기
	

}







