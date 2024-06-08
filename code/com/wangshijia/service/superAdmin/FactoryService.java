package com.wangshijia.service.superAdmin;

import java.util.List;

import com.wangshijia.model.Factory;


public interface FactoryService {
	List<Factory> getFactories();
	void saveFactories(List<Factory> factories);
}
