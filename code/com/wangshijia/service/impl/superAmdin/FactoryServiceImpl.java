package com.wangshijia.service.impl.superAmdin;

import java.io.IOException;
import java.util.List;

import com.wangshijia.model.Factory;
import com.wangshijia.model.User;
import com.wangshijia.service.superAdmin.FactoryService;
import com.wangshijia.utils.JsonFileStorage;

//工厂服务
public class FactoryServiceImpl implements FactoryService{
	private static final String FILE_PATH = "src/factory.json";
    private static List<Factory> factories;   //存储工厂
    private JsonFileStorage<Factory> jsonFileStorage;  //单例对象的实例
    private static FactoryServiceImpl instance;
    
   //构造函数，读取factories.json文件内容，并赋值给factories变量
    public FactoryServiceImpl() {
        this.jsonFileStorage = new JsonFileStorage<>(FILE_PATH);
        try {
        	factories = jsonFileStorage.readData(Factory.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
   //单例函数
    public static FactoryServiceImpl getInstance() {
    	if (instance == null) {
            instance = new FactoryServiceImpl();
        }
        return instance;
    }
    
	@Override
	//返回factories变量
	public List<Factory> getFactories() {
		return factories;
	}

	@Override
	//将factories变量保存到json文件中
	public void saveFactories(List<Factory> factories) {
		try {
            jsonFileStorage.writeData(factories);
        } catch (IOException e) {
            // Handle exception
            e.printStackTrace();
        }
		
	}
	
	//返回最大的ID
	public int getMaxID() {
    	int max=0;
    	for (Factory factory : factories) {
    		if(factory.getId()>max) {
    			max=factory.getId();
    		}
    	}
    	return max;
    }

}
