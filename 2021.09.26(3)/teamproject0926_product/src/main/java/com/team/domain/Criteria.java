package com.team.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria {

   private int pageNum;   //������ ��ȣ
   private int amount;      //�� ������ �� �� ����
   
   private int startRow;   //������ ��ȣ
   
   private String type ="";    //�˻�����
   private String keyword =""; // �˻���
   
   
   public Criteria() {
      this(1,12);   //�⺻���� 1������, ���������� �� 10���� ������
   }

   public Criteria(int pageNum, int amount) {
      this.pageNum = pageNum;
      this.amount = amount;
      
      
   
   }
   
   
}