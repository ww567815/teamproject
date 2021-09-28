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
	
	int deleteBoardByNum(int num); // �۹�ȣ�� �ش��ϴ� �� �Ѱ� �����ϱ�
	
	List<ProductVO> getBoards();  // ��ü �Խñ� ���� ��������

	ProductVO getBoard(int num); // �۹�ȣ�� �ش��ϴ� �� �Ѱ� ��������
	
	ProductVO getProduct(int pnum);
	
	ProductVO getProductAndAttaches(int pnum);  // �۹�ȣ�� �ش��ϴ� �� �Ѱ��� ÷������ ��� ��������
	
	void updateReadcount(int num); // �۹�ȣ�� �ش��ϴ� ���� ��ȸ���� 1 ������Ű��
	
	void updateBoard(ProductVO productVO); // �۹�ȣ�� �ش��ϴ� ���� ������, �۳���, ��¥, IP�ּ� �����ϱ�
	

}







