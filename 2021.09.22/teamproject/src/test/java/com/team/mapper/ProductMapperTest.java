package com.team.mapper;

import java.util.Date;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.team.domain.ProductVO;

@RunWith(SpringJUnit4ClassRunner.class) // @Component �迭 �ֳ����̼ǿ� �ش���
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class ProductMapperTest {
	
	@Autowired
	private ProductMapper productMapper;

	
	@Test
	public void testInsert() {
		// �ֱ� 127�� �߰��ϱ� �׽�Ʈ
		productMapper.deleteAll();
		
		Random random = new Random();
		
		for (int i=1; i<=127; i++) {
			ProductVO productVO = new ProductVO();
			
			int pnum = productMapper.nextNum(); // insert�� ���۹�ȣ ��������
			
			productVO.setPnum(pnum);
			productVO.setProductName("��ȣ���ڻ�");
			productVO.setSeller_id("1111");
			productVO.setTitle("������" + i + " �Դϴ�.");
			productVO.setDescription("�۳���" + i + " �Դϴ�.\n�۳��� �׽�Ʈ");
			productVO.setSell_price(random.nextInt(1000000)); // ��ȸ�� 0~999999 ������ ��
			productVO.setStatus("�Ǹ���");
			
			productMapper.insert(productVO);
		} // for
		
	} // testInsert
	
	
	
	
}







