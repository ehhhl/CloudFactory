package com.wangshijia.service.agency;

import java.util.ArrayList;
import java.util.List;
import com.wangshijia.model.Order;

public interface OrderService {
	List<Order> getOrders();
	void saveOrders(List<Order> orders);
}
