package com.mbanq.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mbanq.entity.Product;
import com.mbanq.repository.ProductRepository;
import com.mbanq.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository productRepository;

	@Override
	@Transactional
	public List<Product> listProduct() {
		return productRepository.listProduct();
	}

	@Override
	@Transactional
	public void createProduct(Product product) {
		productRepository.createProduct(product);
	}

	@Override
	@Transactional
	public boolean checkExistName(String name) {
		// TODO Auto-generated method stub
		return productRepository.checkExistName(name);

	}

	@Override
	@Transactional
	public boolean checkExistNameUpdate(Product product) {
		return productRepository.checkExistNameUpdate(product);
	}

	@Override
	@Transactional
	public void updateProduct(Product product) {
		productRepository.updateProduct(product);

	}

	@Override
	@Transactional
	public void deleteProduct(Product product) {
		productRepository.deleteProduct(product);

	}

	@Override
	@Transactional
	public boolean checkExistUpdateId(Product product) {
		return productRepository.checkExistUpdateId(product);
	}

	@Override
	@Transactional
	public boolean checkExistUpdateIdFullUser(Product product) {
		return productRepository.checkExistUpdateIdFullUser(product);
	}

	@Override
	@Transactional
	public boolean checkExistDeleteIdFullUser(Product product) {
		return productRepository.checkExistDeleteIdFullUser(product);
	}

	@Override
	@Transactional
	public boolean checkExistDeleteId(Product product) {
		return productRepository.checkExistDeleteId(product);
	}

}
