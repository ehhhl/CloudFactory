package com.wangshijia.service.impl.superAmdin;

import java.io.IOException;
import java.util.List;

import com.wangshijia.model.Product;
import com.wangshijia.model.ProductCate;
import com.wangshijia.service.superAdmin.ProductService;
import com.wangshijia.utils.JsonFileStorage;

//产品服务
public class ProductServiceImpl implements ProductService{
	private static final String FILE_PATH = "src/products.json";
    private static List<Product> products;    //存储产品信息
    private JsonFileStorage<Product> jsonFileStorage;
    private static ProductServiceImpl instance;   //单例对象的实例
    
    //构造函数，读取products.json文件内容，并赋值给products变量
    public ProductServiceImpl() {
        this.jsonFileStorage = new JsonFileStorage<>(FILE_PATH);
        try {
        	products = jsonFileStorage.readData(Product.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    //单例函数
    public static ProductServiceImpl getInstance() {
    	if (instance == null) {
            instance = new ProductServiceImpl();
        }
        return instance;
    }
    
	@Override
	//返回products变量
	public List<Product> getProducts() {
		// TODO Auto-generated method stub
		return products;
	}

	@Override
	//将products变量保存到json文件中
	public void saveProducts(List<Product> products) {
		// TODO Auto-generated method stub
		try {
            jsonFileStorage.writeData(products);
        } catch (IOException e) {
            // Handle exception
            e.printStackTrace();
        }
	}
	
	//返回最大的ID
	public int getMaxID() {
    	int max=0;
    	for (Product product : products) {
    		if(product.getId()>max) {
    			max=product.getId();
    		}
    	}
    	return max;
    }

}
