package com.wangshijia.view.common;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.wangshijia.service.impl.superAmdin.UserServiceImpl;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;

//用户登录界面
public class LoginView extends JFrame {

	private JPanel contentPane;
	private JTextField textField;   //用户名
	private JPasswordField passwordField;    //密码
	private JButton loginButton;
	private JButton registerButton;
	private static LoginView instance;    //单例对象的实例
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView frame = new LoginView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the frame.
	 */
	
	//构造函数
	public LoginView() {
		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("云平台制造");
		lblNewLabel.setIcon(new ImageIcon(LoginView.class.getResource("/icon/-mission-.png")));
		lblNewLabel.setFont(new Font("华文行楷", Font.BOLD, 30));
		lblNewLabel.setBounds(128, 40, 206, 37);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("账 号");
		lblNewLabel_1.setIcon(new ImageIcon(LoginView.class.getResource("/icon/用户.png")));
		lblNewLabel_1.setBounds(98, 99, 92, 37);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("密 码");
		lblNewLabel_2.setIcon(new ImageIcon(LoginView.class.getResource("/icon/用户.png")));
		lblNewLabel_2.setBounds(98, 142, 92, 28);
		contentPane.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(200, 107, 129, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(200, 146, 129, 21);
		contentPane.add(passwordField);
		
		loginButton = new JButton("登录");
		loginButton.setBounds(88, 202, 93, 23);
		contentPane.add(loginButton);
		
		registerButton = new JButton("没有账户？点我注册");
		registerButton.setBounds(224, 202, 172, 23);
		contentPane.add(registerButton);
		
		JLabel lblNewLabel_3 = new JLabel(" ");
		lblNewLabel_3.setIcon(new ImageIcon(LoginView.class.getResource("/icon/微信图片_20230614154154.jpg")));
		lblNewLabel_3.setBounds(-281, 0, 861, 551);
		contentPane.add(lblNewLabel_3);
	}
	
	//单例函数
	public static LoginView getInstance() {
    	if (instance == null) {
            instance = new LoginView();
        }
        return instance;
    }
	
	//登录、注册监听器
	public void loginButtonListener(ActionListener listener) {
        loginButton.addActionListener(listener);
    }
	public void registerButtonListener(ActionListener listener) {
        registerButton.addActionListener(listener);
    }
	
	//获得用户名
	public String getUserName() {
		return textField.getText();
	}
	
	//获得密码
	public String getPassword() {
		return new String(passwordField.getPassword());
	}
}
