package com.wangshijia.view.superAdmin;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.TreeSelectionListener;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.wangshijia.model.CapacityFocusCate;
import com.wangshijia.model.ProductCate;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import java.awt.SystemColor;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class CapacityFocusCateManagementView extends JFrame {

	private JPanel contentPane;
	private JTextField name;      //接收按名字查询
	private static CapacityFocusCateManagementView instance;     //单例对象的实例
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
					CapacityFocusCateManagementView frame = new CapacityFocusCateManagementView();
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
	public CapacityFocusCateManagementView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 734, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel contentPane_1 = new JPanel();
		contentPane_1.setLayout(null);
		contentPane_1.setBounds(0, 0, 722, 445);
		contentPane.add(contentPane_1);
		
		addButton = new JButton("\u65B0\u5EFA");
		addButton.setIcon(new ImageIcon(CapacityFocusCateManagementView.class.getResource("/icon/添加+.png")));
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		addButton.setBounds(266, 168, 93, 23);
		contentPane_1.add(addButton);
		
		deleteButton = new JButton("\u5220\u9664");
		deleteButton.setIcon(new ImageIcon(CapacityFocusCateManagementView.class.getResource("/icon/删除.png")));
		deleteButton.setBounds(369, 168, 93, 23);
		contentPane_1.add(deleteButton);
		
		modifyButton = new JButton("\u4FEE\u6539");
		modifyButton.setIcon(new ImageIcon(CapacityFocusCateManagementView.class.getResource("/icon/修改.png")));
		modifyButton.setBounds(548, 168, 116, 23);
		contentPane_1.add(modifyButton);
		
		name = new JTextField();
		name.setColumns(10);
		name.setBounds(150, 69, 193, 23);
		contentPane_1.add(name);
		
		queryButton = new JButton("类别名称查找");
		queryButton.setIcon(new ImageIcon(CapacityFocusCateManagementView.class.getResource("/icon/-search.png")));
		queryButton.setBounds(369, 58, 160, 44);
		contentPane_1.add(queryButton);
		
		resetButton = new JButton("\u91CD\u7F6E");
		resetButton.setIcon(new ImageIcon(CapacityFocusCateManagementView.class.getResource("/icon/刷新.png")));
		resetButton.setBounds(548, 135, 116, 23);
		contentPane_1.add(resetButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 201, 636, 234);
		contentPane_1.add(scrollPane);
		
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
		contentPane_1.add(tree);
		
		lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(CapacityFocusCateManagementView.class.getResource("/icon/微信图片_20230614154154.jpg")));
		lblNewLabel.setBounds(0, 1, 722, 444);
		contentPane_1.add(lblNewLabel);
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
	public void setTable(List<CapacityFocusCate> deviceCates) {
		if(deviceCates != null) {
			DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
			defaultTableModel.setRowCount(0);	
			for (CapacityFocusCate deviceCate : deviceCates) {
				Object[] record = new Object[3];
				record[0]=false;
				record[1]=String.valueOf(deviceCate.getId());
				record[2]=deviceCate.getCateName();
				defaultTableModel.addRow(record);
			}
		}else {
			DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
			defaultTableModel.setRowCount(0);
		}
	}
	
	//单例函数
	public static CapacityFocusCateManagementView getInstance() {
    	if (instance == null) {
            instance = new CapacityFocusCateManagementView();
        }
        return instance;
    }
	
	//得到表格对象
	public JTable getTable() {
		return table;
	}
	
}
