package com.wangshijia.service.impl.agency;

import java.io.IOException;
import java.util.List;

import com.wangshijia.model.Order;
import com.wangshijia.service.agency.OrderService;
import com.wangshijia.utils.JsonFileStorage;

//订单服务
public class OrderServiceImpl implements OrderService{
	private static final String FILE_PATH = "src/orders.json";
    private static List<Order> orders;   //存储订单
    private JsonFileStorage<Order> jsonFileStorage;
    private static OrderServiceImpl instance;   //单例对象的实例
    
    //构造函数，读取orders.json文件内容，并赋值给orders变量
    public OrderServiceImpl() {
        this.jsonFileStorage = new JsonFileStorage<>(FILE_PATH);
        try {
			orders = jsonFileStorage.readData(Order.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    //单例函数
    public static OrderServiceImpl getInstance() {
    	if (instance == null) {
            instance = new OrderServiceImpl();
        }
        return instance;
    }
    
	@Override
	//返回orders变量
	public List<Order> getOrders() {
		// TODO Auto-generated method stub
		return orders;
	}

	@Override
	//将orders变量保存到json文件中
	public void saveOrders(List<Order> orders) {
		// TODO Auto-generated method stub
		try {
            jsonFileStorage.writeData(orders);
        } catch (IOException e) {
            // Handle exception
            e.printStackTrace();
        }
	}
	
	//返回最大的ID
	public int getMaxID() {
    	int max=0;
    	for (Order order : orders) {
    		if(order.getId()>max) {
    			max=order.getId();
    		}
    	}
    	return max;
    }
}
