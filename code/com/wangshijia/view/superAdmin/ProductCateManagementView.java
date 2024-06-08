package com.wangshijia.view.superAdmin;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.event.TreeSelectionListener;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.wangshijia.model.ProductCate;
import com.wangshijia.model.User;

import javax.swing.JScrollPane;
import javax.swing.JTree;
import java.awt.SystemColor;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ProductCateManagementView extends JFrame {

	private JPanel contentPane;
	private JTextField name;      //接收按名字查询
	private static ProductCateManagementView instance;        //单例对象的实例
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
					ProductCateManagementView frame = new ProductCateManagementView();
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
	public ProductCateManagementView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 734, 480);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		addButton = new JButton("\u65B0\u5EFA");
		addButton.setIcon(new ImageIcon(ProductCateManagementView.class.getResource("/icon/添加+.png")));
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		addButton.setBounds(373, 191, 93, 23);
		contentPane.add(addButton);
		
		deleteButton = new JButton("\u5220\u9664");
		deleteButton.setIcon(new ImageIcon(ProductCateManagementView.class.getResource("/icon/删除.png")));
		deleteButton.setBounds(476, 191, 93, 23);
		contentPane.add(deleteButton);
		
		modifyButton = new JButton("\u4FEE\u6539");
		modifyButton.setIcon(new ImageIcon(ProductCateManagementView.class.getResource("/icon/修改.png")));
		modifyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		modifyButton.setBounds(584, 191, 93, 23);
		contentPane.add(modifyButton);
		
		name = new JTextField();
		name.setBounds(154, 54, 192, 23);
		contentPane.add(name);
		name.setColumns(10);
		
		queryButton = new JButton("类别名称查找");
		queryButton.setIcon(new ImageIcon(ProductCateManagementView.class.getResource("/icon/-search.png")));
		queryButton.setBounds(372, 44, 140, 33);
		contentPane.add(queryButton);
		
		resetButton = new JButton("\u91CD\u7F6E");
		resetButton.setIcon(new ImageIcon(ProductCateManagementView.class.getResource("/icon/刷新.png")));
		resetButton.setBounds(584, 158, 93, 23);
		contentPane.add(resetButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(51, 224, 626, 211);
		contentPane.add(scrollPane);
		
		table = new JTable();
		//设置表格
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"", "ID", "\u7C7B\u522B\u540D\u79F0"
			}
		) {
			Class[] columnTypes = new Class[] {
				Boolean.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(table);
		
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
		lblNewLabel.setIcon(new ImageIcon(ProductCateManagementView.class.getResource("/icon/微信图片_20230614154154.jpg")));
		lblNewLabel.setBounds(0, 1, 712, 551);
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
	public void setTable(List<ProductCate> productCates) {
		if(productCates != null) {
			DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
			defaultTableModel.setRowCount(0);	
			for (ProductCate productCate : productCates) {
				Object[] record = new Object[3];
				record[0]=false;
				record[1]=String.valueOf(productCate.getId());
				record[2]=productCate.getCateName();
				defaultTableModel.addRow(record);
			}
		}else {
			DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
			defaultTableModel.setRowCount(0);
		}
	}
	
	//单例函数
	public static ProductCateManagementView getInstance() {
    	if (instance == null) {
            instance = new ProductCateManagementView();
        }
        return instance;
    }
	
	//得到表格对象
	public JTable getTable() {
		return table;
	}
	
}
