package com.team.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	public List<ProductVO> getProducts(){
		return productMapper.getProducts();
	}
	
	public List<ProductVO> getProducts(Criteria cri){
		return productMapper.getProductsWithPaging(cri);
	}
	
	public int getTotalCount() {
		return productMapper.getTotalCount();
	}
	
	public int getTotalCountBySearch(Criteria cri) {
		return productMapper.getTotalCountBySearch(cri);
	}
	
	
}
