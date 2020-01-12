package com.mbanq.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mbanq.entity.Product;
import com.mbanq.service.ProductService;

@RestController
@RequestMapping("admin/api")
public class ProductRestController {
	@Autowired
	private ProductService productService;
	private HttpStatus httpStatus = HttpStatus.OK;
	@PostMapping("/create-product")
	public ResponseEntity<Map<String, Object>> createProduct(@RequestBody Product product) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (!product.checkValidate()) {
				httpStatus = HttpStatus.BAD_REQUEST;
				map.put("data", "null");
				map.put("code", "001");
				map.put("message", "input data invalid!!");
			} else if (!productService.checkExistName(product.getName())) {
				System.out.println(product.getName());
				httpStatus = HttpStatus.BAD_REQUEST;
				map.put("data", "null");
				map.put("code", "001");
				map.put("message", "input data invalid!! existing name!!");
			} else {
				httpStatus = HttpStatus.OK;
				productService.createProduct(product);
				map.put("data", "success");
				map.put("code", "001");
				map.put("message", "Create product Success");
			}
		} catch (Exception e) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			map.put("data", null);
			map.put("code", "003");
			map.put("message", "Something went wrong please connect Administrator !!");
		}
		return new ResponseEntity<Map<String, Object>>(map, httpStatus);
	}

	@GetMapping("/list-product")
	public ResponseEntity<Map<String, Object>> listProduct() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Product> theListProduct = null;
		try {
			theListProduct = productService.listProduct();
			if (!theListProduct.isEmpty()) {
				httpStatus = HttpStatus.OK;
				map.put("data", theListProduct);
				map.put("code", "001");
				map.put("message", "Success");
			} else {
				httpStatus = HttpStatus.BAD_REQUEST;
				map.put("data", null);
				map.put("code", "002");
				map.put("message", "Empty Data!!");
			}
		} catch (Exception e) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			map.put("data", null);
			map.put("code", "003");
			map.put("message", "Something went wrong please connect Administrator !!");
		}
		return new ResponseEntity<Map<String, Object>>(map, httpStatus);
	}

	@PutMapping("/update-product")
	public ResponseEntity<Map<String, Object>> updateProduct(@RequestBody Product product) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (!product.checkValidateUpdate()) {
				httpStatus = HttpStatus.BAD_REQUEST;
				map.put("data", "null");
				map.put("code", "002");
				map.put("message", "update data invalid!!");
			} else {
				if (productService.checkExistUpdateIdFullUser(product)) {
					if (productService.checkExistUpdateId(product)) {
						if (productService.checkExistNameUpdate(product)) {
							httpStatus = HttpStatus.OK;
							productService.updateProduct(product);
							map.put("data", "success");
							map.put("code", "001");
							map.put("message", "update product Success");
						} else {
							httpStatus = HttpStatus.BAD_REQUEST;
							map.put("data", "null");
							map.put("code", "002");
							map.put("message", "update data invalid!! existing name!! ");
						}
					} else {
						httpStatus = HttpStatus.BAD_REQUEST;
						map.put("data", "null");
						map.put("code", "002");
						map.put("message", "update data invalid!! id belong to another user!!");
					}
				} else {
					httpStatus = HttpStatus.BAD_REQUEST;
					map.put("data", "null");
					map.put("code", "002");
					map.put("message", "update data invalid!! id not existing!!");
				}
			}
		} catch (Exception e) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			map.put("data", null);
			map.put("code", "003");
			map.put("message", "Something went wrong please connect Administrator !!");
		}
		return new ResponseEntity<Map<String, Object>>(map, httpStatus);
	}

	@DeleteMapping("/delete-product")
	public ResponseEntity<Map<String, Object>> deleteProduct(@RequestBody Product product) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (productService.checkExistDeleteIdFullUser(product)) {
				if (productService.checkExistDeleteId(product)) {
					httpStatus = HttpStatus.OK;
					productService.deleteProduct(product);
					map.put("data", "success");
					map.put("code", "001");
					map.put("message", "delete product Success");
				} else {
					httpStatus = HttpStatus.BAD_REQUEST;
					map.put("data", "null");
					map.put("code", "002");
					map.put("message", "delete data invalid!! id belong to another user!!");
				}
			} else {
				httpStatus = HttpStatus.BAD_REQUEST;
				map.put("data", "null");
				map.put("code", "002");
				map.put("message", "delete data invalid!! id not existing!!");
			}

		} catch (Exception e) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			map.put("data", null);
			map.put("code", "003");
			map.put("message", "Something went wrong please connect Administrator !!");
		}
		return new ResponseEntity<Map<String, Object>>(map, httpStatus);
	}

}
