package com.wangshijia.view.superAdmin;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.wangshijia.model.Factory;
import com.wangshijia.model.User;

import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

//超级管理员工厂管理界面
public class FactoryManagementView extends JFrame {

	private JPanel contentPane;
	private JTextField name;       //接受按名字查询
	private JTable table;
	private static FactoryManagementView instance;     //单例对象的实例
	private JButton queryButton,reflashButton,changeButton;
	private JTree tree;
	private JLabel lblNewLabel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FactoryManagementView frame = new FactoryManagementView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//得到按名字查询输入的字符串
	public String getName() {
		return name.getText();
	}

	/**
	 * Create the frame.
	 */
	
	//构造函数
	public FactoryManagementView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 741, 442);
		contentPane = new JPanel();

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		changeButton = new JButton("\u5207\u6362");
		changeButton.setIcon(new ImageIcon(FactoryManagementView.class.getResource("/icon/双向.png")));
		changeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		changeButton.setBounds(488, 146, 93, 23);
		contentPane.add(changeButton);
		
		name = new JTextField();
		name.setBounds(176, 54, 200, 23);
		contentPane.add(name);
		name.setColumns(10);
		
		queryButton = new JButton("工厂名称查询");
		queryButton.setIcon(new ImageIcon(FactoryManagementView.class.getResource("/icon/-search.png")));
		queryButton.setBounds(386, 49, 140, 33);
		contentPane.add(queryButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(35, 201, 649, 196);
		contentPane.add(scrollPane);
		
		table = new JTable();
		//设置表格
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"", "ID", "\u5DE5\u5382\u540D\u79F0", "\u5DE5\u5382\u7B80\u4ECB", "\u8D1F\u8D23\u4EBA", "\u8054\u7CFB\u65B9\u5F0F", "\u767B\u5F55\u8D26\u53F7", "\u5DE5\u5382\u72B6\u6001"
			}
		){
			Class[] columnTypes = new Class[] {
					Boolean.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
		});
		scrollPane.setViewportView(table);
		
		reflashButton = new JButton("刷新");
		reflashButton.setIcon(new ImageIcon(FactoryManagementView.class.getResource("/icon/刷新.png")));
		reflashButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		reflashButton.setBounds(591, 146, 93, 23);
		contentPane.add(reflashButton);
		
		tree = new JTree();
		//设置树形结构
		tree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("管理员") {
				{
					DefaultMutableTreeNode node_1;
					add(new DefaultMutableTreeNode("用户管理"));
					add(new DefaultMutableTreeNode("工厂管理"));
					node_1 = new DefaultMutableTreeNode("产品管理");
						node_1.add(new DefaultMutableTreeNode("产品类别管理"));
						node_1.add(new DefaultMutableTreeNode("产品信息管理"));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("产能中心");
						node_1.add(new DefaultMutableTreeNode("设备类别管理"));
						node_1.add(new DefaultMutableTreeNode("设备信息管理"));
					add(node_1);
				}
			}
		));
		tree.setBackground(SystemColor.menu);
		tree.setBounds(0, 0, 140, 191);
		contentPane.add(tree);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(FactoryManagementView.class.getResource("/icon/微信图片_20230614154154.jpg")));
		lblNewLabel.setBounds(0, 1, 719, 440);
		contentPane.add(lblNewLabel);
	}
	
	//设置表格内容
	public void setTable(List<User> users,List<Factory> factories) {
		DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
		defaultTableModel.setRowCount(0);
		if(factories!=null) {
			for(Factory factory : factories) {
				Object[] record = new Object[9];
				User temp = new User();
				record[0]=false;
				record[1]=factory.getId();
				record[2]=factory.getName();
				for(User user : users) {
					if(user.getFactoryName().equals(factory.getName())) {
						temp=user;
						break;
					}
				}
				record[3]=temp.getFactoryDescription();
				record[4]=temp.getName();
				record[5]=temp.getContact();
				record[6]=temp.getUserName();
				record[7]=String.valueOf(factory.getStatus());
				defaultTableModel.addRow(record);
			}
		}else {
			defaultTableModel.setRowCount(0);
		}
		
	}
	
	//单例函数
	public static FactoryManagementView getInstance() {
    	if (instance == null) {
            instance = new FactoryManagementView();
        }
        return instance;
    }
	
	//查询、刷新、切换、树形菜单监听对象
	public void queryButtonListener(ActionListener listener) {
        queryButton.addActionListener(listener);
    }
	
	public void reflashButtonListener(ActionListener listener) {
        reflashButton.addActionListener(listener);
    }
	
	public void changeButtonListener(ActionListener listener) {
        changeButton.addActionListener(listener);
    }
	
	public void treeListener(TreeSelectionListener listener) {
        tree.addTreeSelectionListener(listener);
    }
	
	//得到表格对象
	public JTable getTable() {
		return table;
	}
}
