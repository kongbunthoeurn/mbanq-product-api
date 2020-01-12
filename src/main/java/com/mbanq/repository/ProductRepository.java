package com.mbanq.repository;

import java.util.List;
import com.mbanq.entity.Product;

public interface ProductRepository {
	public void createProduct(Product product);

	public boolean checkExistName(String name);

	public List<Product> listProduct();

	public void updateProduct(Product product);

	public boolean checkExistNameUpdate(Product product);

	public void deleteProduct(Product product);

	public boolean checkExistUpdateId(Product product);

	public boolean checkExistUpdateIdFullUser(Product product);

	public boolean checkExistDeleteIdFullUser(Product product);

	public boolean checkExistDeleteId(Product product);

}
