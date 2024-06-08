package com.wangshijia.controller.common;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.wangshijia.model.User;
import com.wangshijia.model.UserRole;
import com.wangshijia.service.impl.common.UserServiceImpl;
import com.wangshijia.view.agency.ProductView;
import com.wangshijia.view.common.LoginView;
import com.wangshijia.view.common.RegistrationView;
import com.wangshijia.view.factoryAdmin.MyDeviceView;
import com.wangshijia.view.superAdmin.*;

public class LoginController {
	private  LoginView view;
	
	//构造函数
	public LoginController(LoginView view) {
		this.view = view;
		view.loginButtonListener(new LoginButtonListener());
		view.registerButtonListener(new RegisterButtonListener());
	}
	
	/*登录执行
	 * 先判断用户名和密码是否匹配
	 * 匹配的话就根据用户角色跳转
	 * 不匹配弹出提示框
	 */
	private class LoginButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			boolean label = false;
			User userTmp=null;
			UserServiceImpl userService = UserServiceImpl.getInstance();
			for (User user:userService.getUsers()) {
				if(user.getUserName().equals(view.getUserName()) && user.getPassword().equals(view.getPassword())) {
					label = true;
					userTmp=user;
					break;
				}
			}
			if (label == true){
				if(userTmp.getRole()==UserRole.超级管理员) {
					UserManagementView userManagementView = UserManagementView.getInstance();
					userManagementView.setVisible(true);
					view.dispose();
				}else if(userTmp.getRole()==UserRole.云工厂) {
					User.staticFactoryName=userTmp.getFactoryName();
					MyDeviceView myDeviceView = MyDeviceView.getInstance();
					myDeviceView.setVisible(true);
					view.dispose();
				}else {
					User.staticAgencyUserNameString=userTmp.getUserName();
					ProductView productView = ProductView.getInstance();
					productView.setVisible(true);
					view.dispose();
				}
			}else {
				JOptionPane.showMessageDialog(null, "用户名或密码错误！！！");
			}
		}
		
	}
	
	//注册按钮，界面跳转
	private class RegisterButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			view.dispose();
			RegistrationView registrationView = RegistrationView.getInstance();
			registrationView.setVisible(true);
		}
		
	}
}
