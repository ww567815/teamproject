package com.team.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team.domain.Criteria;
import com.team.domain.MemberVO;
import com.team.domain.PageDTO;
import com.team.domain.ProductVO;
import com.team.service.AttachService;
import com.team.service.ProductService;
import com.team.util.Script;

@Controller
@RequestMapping("/products/*")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@Autowired
	private AttachService attachService;
	
	
	@GetMapping("/productsList") //물품조회관련 GetMapping 
	public String products(Model model) {
		System.out.println("productsList 호출됨...");
		List<ProductVO> proList = productService.getProducts();
		
		int totalCount = productService.getTotalCount();
		
		
		model.addAttribute("proList", proList);
		model.addAttribute("totalCount", totalCount);
		return "products/productsList_";
	}
	
	@PostMapping("/products") //물품조회관련 PostMapping
	public ResponseEntity<String> products(MemberVO memberVO){
		
		String str = Script.href("물품등록!", "/products/productsList");
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "text/html; charset=UTF-8");
		return new ResponseEntity<String>(str, headers, HttpStatus.OK);
	}
	
//   //************************productDetails*********************************
//   @GetMapping("/productDetails") //물품 상세정보관련 GetMapping
//   public String productDetails(int pnum, Model model) {
//      ProductVO productVO = productService.getProductDetails(pnum);
//      return "products/productDetail";
//   }
}
