package com.wangshijia.view.common;

import java.awt.EventQueue;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;

//注册界面
public class RegistrationView extends JFrame {

	private JPanel contentPane;
	private JTextField userName;    //用户名
	private JTextField password;    //密码
	private JTextField name;        //姓名
	private JTextField contact;     //联系方式
	private JTextField factoryName; //工厂名
	private JTextField factoryDescription;    //工厂描述
	private ButtonGroup buttonGroup;          //单选按钮组
	private JRadioButton radioButton0, radioButton1;    //云工厂、经销商单选按钮
	private JButton registerButton,returnButton;
	private static RegistrationView instance;    //单例对象的实例
	private JLabel lblNewLabel_7;
	
	//获得用户名
	public String getUserName() {
		return userName.getText();
	}
	//获得密码
	public String getPassword() {
		return password.getText();
	}
	//获得姓名
	public String getName() {
		return name.getText();
	}
	//获得联系方式
	public String getContact() {
		return contact.getText();
	}
	//获得工厂名
	public String getFactoryName() {
		return factoryName.getText();
	}
	//获得工厂描述
	public String getFactoryDescription() {
		return factoryDescription.getText();
	}
	//获得用户类型
	public String getRole() {
		if(radioButton0.isSelected()) {
			return new String("云工厂");
		}else if(radioButton1.isSelected()) {
			return new String("经销商");
		}else {
			return new String("未选择");
		}
	}
	
	public JTextField getJFactorName() {
		return factoryName;
	}
	public JTextField getJFactorDescription() {
		return factoryDescription;
	}
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrationView frame = new RegistrationView();
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
	public RegistrationView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 386, 464);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("登录账号");
		lblNewLabel.setIcon(new ImageIcon(RegistrationView.class.getResource("/icon/-folders-.png")));
		lblNewLabel.setBounds(39, 31, 118, 27);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("登录密码");
		lblNewLabel_1.setIcon(new ImageIcon(RegistrationView.class.getResource("/icon/-computer.png")));
		lblNewLabel_1.setBounds(39, 68, 118, 31);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("真实姓名");
		lblNewLabel_2.setIcon(new ImageIcon(RegistrationView.class.getResource("/icon/-student-.png")));
		lblNewLabel_2.setBounds(39, 104, 103, 32);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("联系方式");
		lblNewLabel_3.setIcon(new ImageIcon(RegistrationView.class.getResource("/icon/-school-.png")));
		lblNewLabel_3.setBounds(39, 146, 103, 37);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("注册方式");
		lblNewLabel_4.setIcon(new ImageIcon(RegistrationView.class.getResource("/icon/-online-education-_2.png")));
		lblNewLabel_4.setBounds(39, 195, 103, 26);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("工厂名称");
		lblNewLabel_5.setIcon(new ImageIcon(RegistrationView.class.getResource("/icon/-pencils.png")));
		lblNewLabel_5.setBounds(39, 231, 109, 32);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("工厂简介");
		lblNewLabel_6.setIcon(new ImageIcon(RegistrationView.class.getResource("/icon/-notes.png")));
		lblNewLabel_6.setBounds(39, 277, 103, 27);
		contentPane.add(lblNewLabel_6);
		
		userName = new JTextField();
		userName.setBounds(157, 34, 131, 21);
		contentPane.add(userName);
		userName.setColumns(10);
		
		password = new JTextField();
		password.setBounds(157, 73, 131, 21);
		contentPane.add(password);
		password.setColumns(10);
		
		name = new JTextField();
		name.setBounds(157, 112, 131, 21);
		contentPane.add(name);
		name.setColumns(10);
		
		contact = new JTextField();
		contact.setBounds(157, 154, 131, 21);
		contentPane.add(contact);
		contact.setColumns(10);
		buttonGroup = new ButtonGroup();
		radioButton0 = new JRadioButton("云工厂");
		radioButton0.setBounds(157, 195, 66, 23);
		contentPane.add(radioButton0);
		buttonGroup.add(radioButton0);
		radioButton1 = new JRadioButton("经销商");
		radioButton1.setBounds(227, 195, 71, 23);
		contentPane.add(radioButton1);
		buttonGroup.add(radioButton1);
		factoryName = new JTextField();
		factoryName.setEditable(false);
		factoryName.setBounds(157, 237, 131, 21);
		contentPane.add(factoryName);
		factoryName.setColumns(10);
		
		factoryDescription = new JTextField();
		factoryDescription.setEditable(false);
		factoryDescription.setBounds(157, 280, 131, 21);
		contentPane.add(factoryDescription);
		factoryDescription.setColumns(10);
		
		registerButton = new JButton("确定");
		registerButton.setBounds(157, 375, 93, 23);
		contentPane.add(registerButton);
		
		returnButton = new JButton("返回");
		returnButton.setBounds(257, 375, 93, 23);
		contentPane.add(returnButton);
		
		lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setIcon(new ImageIcon(RegistrationView.class.getResource("/icon/微信图片_20230614154154.jpg")));
		lblNewLabel_7.setBounds(-333, 0, 707, 598);
		contentPane.add(lblNewLabel_7);
	}
	
	//单例函数
	public static RegistrationView getInstance() {
    	if (instance == null) {
            instance = new RegistrationView();
        }
        return instance;
    }
	
	//注册、返回监听器
	public void registerButtonListener(ActionListener listener) {
        registerButton.addActionListener(listener);
    }
	public void returnButtonListener(ActionListener listener) {
        returnButton.addActionListener(listener);
    }
	public void radioButton0ButtonListener(ActionListener listener) {
		radioButton0.addActionListener(listener);
	}
	public void radioButton1ButtonListener(ActionListener listener) {
		radioButton1.addActionListener(listener);
	}
}
