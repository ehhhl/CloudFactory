package com.wangshijia.service.superAdmin;

import java.util.List;

import com.wangshijia.model.Product;

public interface ProductService {
	List<Product> getProducts();
	void saveProducts(List<Product> products);
}
