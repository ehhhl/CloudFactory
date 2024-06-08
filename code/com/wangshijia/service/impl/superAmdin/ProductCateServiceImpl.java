package com.wangshijia.service.impl.superAmdin;

import java.io.IOException;
import java.util.List;

import com.wangshijia.model.ProductCate;
import com.wangshijia.model.User;
import com.wangshijia.service.superAdmin.ProductCateService;
import com.wangshijia.utils.JsonFileStorage;

//产品种类服务
public class ProductCateServiceImpl implements ProductCateService{
	private static final String FILE_PATH = "src/productCate.json";
    private static List<ProductCate> productCates;   //存储产品种类
    private JsonFileStorage<ProductCate> jsonFileStorage;
    private static ProductCateServiceImpl instance;    //单例对象的实例
    
    //构造函数，读取productCates.json文件内容，并赋值给productCates变量
    public ProductCateServiceImpl() {
        this.jsonFileStorage = new JsonFileStorage<>(FILE_PATH);
        try {
        	productCates = jsonFileStorage.readData(ProductCate.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    //单例函数
    public static ProductCateServiceImpl getInstance() {
    	if (instance == null) {
            instance = new ProductCateServiceImpl();
        }
        return instance;
    }
    
	@Override
	//返回productCates变量
	public List<ProductCate> getProductCates() {
		// TODO Auto-generated method stub
		return productCates;
	}

	@Override
	//将productCates变量保存到json文件中
	public void saveProductCates(List<ProductCate> productCates) {
		// TODO Auto-generated method stub
		try {
            jsonFileStorage.writeData(productCates);
        } catch (IOException e) {
            // Handle exception
            e.printStackTrace();
        }
	}
	
	//返回最大的ID
	public int getMaxID() {
    	int max=0;
    	for (ProductCate productCate : productCates) {
    		if(productCate.getId()>max) {
    			max=productCate.getId();
    		}
    	}
    	return max;
    }

}
