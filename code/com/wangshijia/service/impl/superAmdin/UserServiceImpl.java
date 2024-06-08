package com.wangshijia.service.impl.superAmdin;

import com.wangshijia.model.User;
import com.wangshijia.service.superAdmin.UserService;
import com.wangshijia.utils.JsonFileStorage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//用户服务（登录时用）
public class UserServiceImpl implements UserService{
    private static final String FILE_PATH = "src/users.json";
    private static List<User> users;
    private JsonFileStorage<User> jsonFileStorage;  //存储用户信息
    private static UserServiceImpl instance;   //单例对象的实例
    
    //构造函数，读取users.json文件内容，并赋值给users变量
    public UserServiceImpl() {
        this.jsonFileStorage = new JsonFileStorage<>(FILE_PATH);
        try {
			users = jsonFileStorage.readData(User.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    //单例函数
    public static UserServiceImpl getInstance() {
    	if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }
    
    //返回users变量
    public List<User> getUsers() {
        return users;
    }

    //将users变量保存到json文件中
    public void saveUsers(List<User> users) {
        try {
            jsonFileStorage.writeData(users);
        } catch (IOException e) {
            // Handle exception
            e.printStackTrace();
        }
    }
    
    //返回最大的ID
    public int getMaxID() {
    	int max=0;
    	for (User user : users) {
    		if(user.getId()>max) {
    			max=user.getId();
    		}
    	}
    	return max;
    }
}
