package com.team.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDTO {

	private int startPage; //���� ������
	private int endPage; //�� ������
	private boolean prev; //����
	private boolean next; //����
	private final int PAGE_BLOCK = 5; //��������� �����ϴ� �ִ� ������ ����
	
	
	
	private int totalCount; //��ü �� ����
	private Criteria cri; //��û ��������ȣ, �� �������� �� ����
	
	
	public PageDTO(int totalCount, Criteria cri) {
		this.totalCount = totalCount;
		this.cri = cri;
		
		// 1������ ��û -> ���������� 5
		// 2������ ��û -> ���������� 5
		// 3������ ��û -> ���������� 5
		// 4������ ��û -> ���������� 5
		// 5������ ��û -> ���������� 5
		
		// 6������ ��û -> ���������� 10
		// 7������ ��û -> ���������� 10
		// 8������ ��û -> ���������� 10
		// 9������ ��û -> ���������� 10
		// 10������ ��û -> ���������� 10
		
		//11������ ��û ->���������� 15
		// ...
		
		// �� ������ ��ȣ
		endPage = (int) Math.ceil((double) cri.getPageNum() / PAGE_BLOCK) * PAGE_BLOCK ;
		
		//���������� ��ȣ
		startPage = endPage - (PAGE_BLOCK - 1);
		
		//���� �������� ��ȣ
		int realEnd = (int) Math.ceil( (double) totalCount / cri.getAmount() );
		
		//���� �������� ��ȣ�� endPage���� ������ 
		if(realEnd <endPage) {
			endPage = realEnd; //endPage�� ���� �������� ��ȣ�� ����
		}
		
		// ����
		prev = startPage > 1;
		
		
		// ����
		next = endPage < realEnd;
		
	} //������
	
}