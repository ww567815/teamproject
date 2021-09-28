package com.team.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cart/*")
public class CartController {
	@GetMapping("/cart") // /products/cart
	public String join() {
		System.out.println("cart »£√‚µ ...");
		return "products/cart";
	}
}
