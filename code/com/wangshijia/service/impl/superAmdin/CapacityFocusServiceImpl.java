package com.wangshijia.service.impl.superAmdin;

import java.io.IOException;
import java.util.List;

import com.wangshijia.model.CapacityFocus;
import com.wangshijia.service.superAdmin.CapacityFocusService;
import com.wangshijia.utils.JsonFileStorage;

//产能中心
public class CapacityFocusServiceImpl implements CapacityFocusService{
	private static final String FILE_PATH = "src/capacityFocuses.json";
    private static List<CapacityFocus> capacityFocuses;  //存储设备信息 
    private JsonFileStorage<CapacityFocus> jsonFileStorage;
    private static CapacityFocusServiceImpl instance;   //单例对象的实例
    
   //构造函数，读取capacityFocuses.json文件内容，并赋值给capacityFocuses变量
    public CapacityFocusServiceImpl() {
        this.jsonFileStorage = new JsonFileStorage<>(FILE_PATH);
        try {
        	capacityFocuses = jsonFileStorage.readData(CapacityFocus.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    //单例函数
    public static CapacityFocusServiceImpl getInstance() {
    	if (instance == null) {
            instance = new CapacityFocusServiceImpl();
        }
        return instance;
    }
    
    //返回capacityFocuse变量
	public List<CapacityFocus> getCapacityFocuses() {
		// TODO Auto-generated method stub
		return capacityFocuses;
	}

	//将capacityFocuse变量保存到json文件中
	@Override
	public void saveCapacityFocuses(List<CapacityFocus> capacityFocuses) {
		// TODO Auto-generated method stub
		try {
            jsonFileStorage.writeData(capacityFocuses);
        } catch (IOException e) {
            // Handle exception
            e.printStackTrace();
        }
	}
	
	//返回最大的ID
	public int getMaxID() {
    	int max=0;
    	for (CapacityFocus capacityFocus : capacityFocuses) {
    		if(capacityFocus.getId()>max) {
    			max=capacityFocus.getId();
    		}
    	}
    	return max;
    }

}
