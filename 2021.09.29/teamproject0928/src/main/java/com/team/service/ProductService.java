package com.team.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
   public ProductVO getProduct(int pnum){
      return productMapper.getProduct(pnum);
   }

   
   @Transactional
   public void registerProductsAndAttaches(ProductVO productVO, List<AttachVO> attachList) {
      // attach    ̺    bno  ÷     ܷ Ű μ 
      // products    ̺    pnum  ÷         ϹǷ 
      // products    ڵ尡      insert      attach    ڵ尡 insert Ǿ    .
      productMapper.insert(productVO);
      
      if (attachList != null && attachList.size() > 0) {
         attachMapper.insertAttaches(attachList);
      }
   } // registerProductsAndAttaches
   
   public int nextNum() {
      return productMapper.nextNum();
   }

   //                °   ̰       ؾ   
//   public int deleteAll() {
//      return boardMapper.deleteAll();
//   }
   
   //                  Ʈ     (attach          )
//   @Transactional
//   public void deleteBoardAndAttaches(int num) {
//      attachMapper.deleteAttachesByBno(num);
//      boardMapper.deleteBoardByNum(num);
//   }
   
   
   
   
   public List<ProductVO> getProducts(){
      return productMapper.getProducts();
   }
   
   //     ¡                
   public List<ProductVO> getProducts(Criteria cri){
      
      int startRow = (cri.getPageNum() - 1) * cri.getAmount();
      cri.setStartRow(startRow); //        ȣ     
      
      return productMapper.getProductsWithPaging(cri);
   }
   
   public int getTotalCount() {
      return productMapper.getTotalCount();
   }
   
   public int getTotalCountBySearch(Criteria cri) {
      return productMapper.getTotalCountBySearch(cri);
   }

   
   public Map<String, Object> getProductAndAttaches(int pnum) {
      ProductVO productVO = productMapper.getProduct(pnum);
      List<AttachVO> attachList = attachList = attachMapper.getAttachesByBno(pnum);
      
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("boardVO", productVO);
      map.put("attachList", attachList);
      
      return map; // join                       
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