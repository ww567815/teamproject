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
}







