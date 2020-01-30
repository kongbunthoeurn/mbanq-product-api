package com.mbanq.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/api")
public class CustomerController {
	@GetMapping("/list-customer")
	public String showHome() {
		return "hello";
	}
}
