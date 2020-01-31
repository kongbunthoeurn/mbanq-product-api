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
public class KongController {
	@GetMapping(name="/list-kong")
	public ResponseEntity<Map<Object, String>> showHome() {
		Map<Object, String> map=new HashMap<Object, String>();
		map.put(1, "Kong");
		map.put(2,"Bunthoeurn");
		return new ResponseEntity<Map<Object,String>>(map, HttpStatus.OK);
	}
}
