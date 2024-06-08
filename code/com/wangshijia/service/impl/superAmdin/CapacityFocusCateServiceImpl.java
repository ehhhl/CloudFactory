package com.wangshijia.service.impl.superAmdin;

import java.io.IOException;
import java.util.List;

import com.wangshijia.model.CapacityFocusCate;
import com.wangshijia.model.ProductCate;
import com.wangshijia.service.superAdmin.CapacityFocusCateService;
import com.wangshijia.utils.JsonFileStorage;

//产能中心类别
public class CapacityFocusCateServiceImpl implements CapacityFocusCateService{
	private static final String FILE_PATH = "src/capacityFocusCate.json";
    private static List<CapacityFocusCate> capacityFocusCates;    //存储设备类别
    private JsonFileStorage<CapacityFocusCate> jsonFileStorage;
    private static CapacityFocusCateServiceImpl instance;   //单例对象的实例
    
   //构造函数，读取capacityFocusCates.json文件内容，并赋值给capacityFocusCates变量
    public CapacityFocusCateServiceImpl() {
        this.jsonFileStorage = new JsonFileStorage<>(FILE_PATH);
        try {
        	capacityFocusCates = jsonFileStorage.readData(CapacityFocusCate.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
   //单例函数
    public static CapacityFocusCateServiceImpl getInstance() {
    	if (instance == null) {
            instance = new CapacityFocusCateServiceImpl();
        }
        return instance;
    }
    
    
   //返回capacityFocusCates变量
	@Override
	public List<CapacityFocusCate> getCapacityFocusCates() {
		// TODO Auto-generated method stub
		return capacityFocusCates;
	}

	//将capacityFocusCates变量保存到json文件中
	@Override
	public void saveCapacityFocusCates(List<CapacityFocusCate> CapacityFocusCates) {
		// TODO Auto-generated method stub
		try {
            jsonFileStorage.writeData(capacityFocusCates);
        } catch (IOException e) {
            // Handle exception
            e.printStackTrace();
        }
	}
	
	//返回最大的ID
	public int getMaxID() {
    	int max=0;
    	for (CapacityFocusCate capacityFocusCate : capacityFocusCates) {
    		if(capacityFocusCate.getId()>max) {
    			max=capacityFocusCate.getId();
    		}
    	}
    	return max;
    }

}
