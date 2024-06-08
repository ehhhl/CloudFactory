package com.wangshijia.controller.common;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import com.wangshijia.model.User;
import com.wangshijia.model.UserRole;
import com.wangshijia.service.common.UserService;
import com.wangshijia.service.impl.common.UserServiceImpl;
import com.wangshijia.view.common.LoginView;
import com.wangshijia.view.common.RegistrationView;

public class RegisterController {
	private  RegistrationView view;
	//构造函数
	public RegisterController(RegistrationView view) {
		this.view = view;
		view.registerButtonListener(new RegisterButtonListener());
		view.returnButtonListener(new ReturnButtonListener());
		view.radioButton0ButtonListener(new RadioButton0ButtonListener());
		view.radioButton1ButtonListener(new RadioButton1ButtonListener());
	}
	
	/*注册按钮执行
	 * 将新的用户信息写入的json文件中
	 */
	private class RegisterButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			UserServiceImpl userService = UserServiceImpl.getInstance();
			List<User> users=userService.getUsers();
			users.add(new User(userService.getMaxID()+1,view.getUserName(),view.getPassword(),view.getName(),view.getContact(),UserRole.valueOf(view.getRole()),view.getFactoryName(),view.getFactoryDescription()));
			userService.saveUsers(users);
		}
	}
	
	//返回按钮，跳转界面
	private class ReturnButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			view.dispose();
			LoginView loginView = LoginView.getInstance();
			loginView.setVisible(true);
		}
	}
	
	private class RadioButton0ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			view.getJFactorName().setEditable(true);
			view.getJFactorDescription().setEditable(true);
		}
	}
	
	private class RadioButton1ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			view.getJFactorName().setEditable(false);
			view.getJFactorDescription().setEditable(false);
		}
	}
}
