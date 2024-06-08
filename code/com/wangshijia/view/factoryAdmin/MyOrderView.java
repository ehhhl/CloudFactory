package com.wangshijia.view.factoryAdmin;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionListener;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;

import com.wangshijia.model.CapacityFocus;
import com.wangshijia.model.Order;
import com.wangshijia.model.User;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

//云工厂我的订单界面
public class MyOrderView extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private static MyOrderView instance;   //单例对象的实例
	private JTree tree;
	private JButton resetButton;
	private JLabel lblNewLabel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyOrderView frame = new MyOrderView();
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
	public MyOrderView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 624, 401);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tree = new JTree();
		tree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("云工厂") {
				{
					add(new DefaultMutableTreeNode("我的设备"));
					add(new DefaultMutableTreeNode("租用设备"));
					add(new DefaultMutableTreeNode("我的订单"));
					add(new DefaultMutableTreeNode("接单"));
				}
			}
		));
		tree.setBounds(0, 0, 100, 95);
		contentPane.add(tree);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 105, 578, 247);
		contentPane.add(scrollPane);
		
		table = new JTable();
		//设置表格
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "\u4EA7\u54C1\u7F16\u53F7", "\u7ECF\u9500\u5546\u7528\u6237\u540D"
			}
		));
		scrollPane.setViewportView(table);
		resetButton = new JButton("重置");
		resetButton.setIcon(new ImageIcon(MyOrderView.class.getResource("/icon/刷新.png")));
		resetButton.setBounds(402, 72, 196, 23);
		contentPane.add(resetButton);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(MyOrderView.class.getResource("/icon/微信图片_20230614154154.jpg")));
		lblNewLabel.setBounds(0, 1, 612, 365);
		contentPane.add(lblNewLabel);
	}
	
	//单例函数
	public static MyOrderView getInstance() {
    	if (instance == null) {
            instance = new MyOrderView();
        }
        return instance;
    }
	
	//重置、树形菜单监听器
	public void resetButtonListener(ActionListener listener) {
        resetButton.addActionListener(listener);
    }
	public void treeListener(TreeSelectionListener listener) {
        tree.addTreeSelectionListener(listener);
    }
	
	//设置表格内容
	public void setTable(List<Order> orders) {
		if(orders != null) {
			DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
			defaultTableModel.setRowCount(0);	
			for (Order order : orders) {
				if(order.getFactoryName().equals(User.staticFactoryName)) {
					Object[] record = new Object[3];
					record[0]=String.valueOf(order.getId());
					record[1]=order.getProductID();
					record[2]=order.getAgencyUserName();
					defaultTableModel.addRow(record);
				}
			}
		}else {
			DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
			defaultTableModel.setRowCount(0);
		}
	}
}
