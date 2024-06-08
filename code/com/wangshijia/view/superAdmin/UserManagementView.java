package com.wangshijia.view.superAdmin;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.wangshijia.model.User;
import com.wangshijia.view.common.LoginView;

import javax.swing.JTextField;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;

//超级管理员进行用户管理的视图
public class UserManagementView extends JFrame {

	private JPanel contentPane;
	private JTextField name;       //接收按名字查询
	private static UserManagementView instance;       //单例对象的实例
	private JTable table;
	private JButton queryButton,resetButton,addButton,deleteButton,modifyButton;
	private JTree tree;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserManagementView frame = new UserManagementView();
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
	public UserManagementView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 726, 401);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		name = new JTextField();
		name.setBounds(181, 53, 177, 21);
		contentPane.add(name);
		name.setColumns(10);
		
		queryButton = new JButton("用户姓名查询");
		queryButton.setIcon(new ImageIcon(UserManagementView.class.getResource("/icon/-search.png")));
		queryButton.setBounds(379, 47, 159, 33);
		contentPane.add(queryButton);
		
		resetButton = new JButton("重置");
		resetButton.setIcon(new ImageIcon(UserManagementView.class.getResource("/icon/刷新.png")));
		resetButton.setBounds(623, 101, 93, 23);
		contentPane.add(resetButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 167, 687, 188);
		contentPane.add(scrollPane);
		
		table = new JTable();
		//设置表格
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"", "ID", "\u767B\u5F55\u8D26\u53F7", "\u5BC6\u7801", "\u59D3\u540D", "\u8054\u7CFB\u65B9\u5F0F", "\u89D2\u8272\u540D\u79F0", "\u5DE5\u5382\u540D", "\u5DE5\u5382\u63CF\u8FF0"
			}
		) {
			Class[] columnTypes = new Class[] {
				Boolean.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(table);
		addButton = new JButton("新建");
		addButton.setIcon(new ImageIcon(UserManagementView.class.getResource("/icon/添加+.png")));
		addButton.setBounds(417, 134, 93, 23);
		contentPane.add(addButton);
		
		deleteButton = new JButton("删除");
		deleteButton.setIcon(new ImageIcon(UserManagementView.class.getResource("/icon/删除.png")));
		deleteButton.setBounds(520, 134, 93, 23);
		contentPane.add(deleteButton);
		
		modifyButton = new JButton("修改");
		modifyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		modifyButton.setIcon(new ImageIcon(UserManagementView.class.getResource("/icon/修改.png")));
		modifyButton.setBounds(623, 134, 93, 23);
		contentPane.add(modifyButton);
		
		//设置树形结构
		tree = new JTree();
		tree.setBackground(new Color(240, 240, 240));
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
		tree.setBounds(0, 0, 131, 156);
		contentPane.add(tree);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(UserManagementView.class.getResource("/icon/微信图片_20230614154154.jpg")));
		lblNewLabel.setBounds(-138, 0, 855, 718);
		contentPane.add(lblNewLabel);
	}
	
	//设置查询、重置、新建、删除、修改、树形菜单按钮监听器
	public void queryButtonListener(ActionListener listener) {
        queryButton.addActionListener(listener);
    }
	public void resetButtonListener(ActionListener listener) {
        resetButton.addActionListener(listener);
    }
	public void addButtonListener(ActionListener listener) {
        addButton.addActionListener(listener);
    }
	public void deleteButtonListener(ActionListener listener) {
        deleteButton.addActionListener(listener);
    }
	public void modifyButtonListener(ActionListener listener) {
        modifyButton.addActionListener(listener);
    }
	public void treeListener(TreeSelectionListener listener) {
        tree.addTreeSelectionListener(listener);
    }
	
	//设置表格内容
	public void setTable(List<User> users) {
		if(users != null) {
			DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
			defaultTableModel.setRowCount(0);	   //表格清空
			for (User user : users) {
				Object[] record = new Object[9];
				record[0]=false;
				record[1]=String.valueOf(user.getId());
				record[2]=user.getUserName();
				record[3]=user.getPassword();
				record[4]=user.getName();
				record[5]=user.getContact();
				record[6]=String.valueOf(user.getRole());
				record[7]=user.getFactoryName();
				record[8]=user.getFactoryDescription();
				defaultTableModel.addRow(record);
			}
		}else {
			DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
			defaultTableModel.setRowCount(0);
		}
	}
	
	//单例函数
	public static UserManagementView getInstance() {
    	if (instance == null) {
            instance = new UserManagementView();
        }
        return instance;
    }
	//得到表格对象
	public JTable getTable() {
		return table;
	}
}
