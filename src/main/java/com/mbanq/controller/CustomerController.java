package com.mbanq.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/api")
public class CustomerController {
	
	public String showHome() {
		return "hello";
	}
	@GetMapping("/list-customer")
	ResponseEntity<Map<String, String>> list_customer(){
		Map<String, String> maps=new HashMap<String, String>();
		maps.put("S1", "Hello");
		maps.put("S2", "Hello2");
		return new ResponseEntity<Map<String,String>>(maps, HttpStatus.OK);
	}
	
	@GetMapping("/list-master")
	ResponseEntity<Map<String, String>> list_master(){
		Map<String, String> maps=new HashMap<String, String>();
		maps.put("S1", "Hello");
		maps.put("S2", "Hello2");
		return new ResponseEntity<Map<String,String>>(maps, HttpStatus.OK);
	}
}
