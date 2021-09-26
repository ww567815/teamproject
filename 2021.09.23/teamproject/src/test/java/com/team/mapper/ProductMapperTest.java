package com.team.mapper;

import java.util.Date;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.team.domain.ProductVO;

@RunWith(SpringJUnit4ClassRunner.class) // @Component 계열 애노테이션에 해당함
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class ProductMapperTest {
	
	@Autowired
	private ProductMapper productMapper;

	
	@Test
	public void testInsert() {
		// 주글 127개 추가하기 테스트
		productMapper.deleteAll();
		
		Random random = new Random();
		
		for (int i=1; i<=127; i++) {
			ProductVO productVO = new ProductVO();
			
			int pnum = productMapper.nextNum(); // insert할 새글번호 가져오기
			
			productVO.setPnum(pnum);
			productVO.setProductName("단호초코빵");
			productVO.setSeller_id("1111");
			productVO.setTitle("글제목" + i + " 입니다.");
			productVO.setDescription("글내용" + i + " 입니다.\n글내용 테스트");
			productVO.setSell_price(random.nextInt(1000000)); // 조회수 0~999999 임의의 값
			productVO.setStatus("판매중");
			
			productMapper.insert(productVO);
		} // for
		
	} // testInsert
	
	
	
	
}







