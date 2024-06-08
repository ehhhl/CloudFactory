package com.wangshijia.service.superAdmin;

import java.util.List;

import com.wangshijia.model.ProductCate;


public interface ProductCateService {
	List<ProductCate> getProductCates();
	void saveProductCates(List<ProductCate> ProductCates);
}
