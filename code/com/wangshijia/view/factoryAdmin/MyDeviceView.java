package com.wangshijia.view.factoryAdmin;

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
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;

import com.wangshijia.model.CapacityFocus;
import com.wangshijia.model.User;
import com.wangshijia.view.superAdmin.CapacityFocusCateManagementView;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

//云工厂，我的设备界面
public class MyDeviceView extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private static MyDeviceView instance;    //单例对象的实例
	private JButton addButton,deleteButton,changeButton;
	private JTree tree;
	private JButton resetButton;
	private JButton modifyButton;
	private JLabel lblNewLabel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyDeviceView frame = new MyDeviceView();
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
	public MyDeviceView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 734, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel contentPane_1 = new JPanel();
		contentPane_1.setLayout(null);
		contentPane_1.setBounds(0, 0, 718, 445);
		contentPane.add(contentPane_1);
		
		addButton = new JButton("\u65B0\u5EFA");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		addButton.setIcon(new ImageIcon(MyDeviceView.class.getResource("/icon/添加+.png")));
		addButton.setBounds(205, 146, 105, 23);
		contentPane_1.add(addButton);
		
		changeButton = new JButton("\u8BBE\u5907\u72B6\u6001");
		changeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		changeButton.setIcon(new ImageIcon(MyDeviceView.class.getResource("/icon/查看.png")));
		changeButton.setBounds(482, 80, 173, 29);
		contentPane_1.add(changeButton);
		
		deleteButton = new JButton("\u5220\u9664");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		deleteButton.setIcon(new ImageIcon(MyDeviceView.class.getResource("/icon/删除.png")));
		deleteButton.setBounds(550, 146, 105, 23);
		contentPane_1.add(deleteButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 195, 681, 240);
		contentPane_1.add(scrollPane);
		
		table = new JTable();
		//设置表格
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"", "ID", "\u8BBE\u5907\u540D\u79F0", "\u8BBE\u5907\u7F16\u53F7", "\u8BBE\u5907\u7C7B\u522B", "\u8BBE\u5907\u89C4\u683C", "\u8BBE\u5907\u72B6\u6001", "\u8BBE\u5907\u63CF\u8FF0", "\u79DF\u7528\u72B6\u6001", "\u6240\u5C5E\u5DE5\u5382"
			}
		){
			Class[] columnTypes = new Class[] {
				Boolean.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(table);
		
		tree = new JTree();
		//设置树形结构
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
		contentPane_1.add(tree);
		
		resetButton = new JButton("重置");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		resetButton.setIcon(new ImageIcon(MyDeviceView.class.getResource("/icon/刷新.png")));
		resetButton.setBounds(320, 146, 105, 23);
		contentPane_1.add(resetButton);
		
		modifyButton = new JButton("保存");
		modifyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		modifyButton.setIcon(new ImageIcon(MyDeviceView.class.getResource("/icon/保存.png")));
		modifyButton.setBounds(435, 146, 105, 23);
		contentPane_1.add(modifyButton);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(MyDeviceView.class.getResource("/icon/微信图片_20230614154154.jpg")));
		lblNewLabel.setBounds(0, 1, 712, 501);
		contentPane_1.add(lblNewLabel);
	}
	
	//单例函数
	public static MyDeviceView getInstance() {
    	if (instance == null) {
            instance = new MyDeviceView();
        }
        return instance;
    }
	
	//设置重置、新建、删除、切换状态、修改、树形菜单按钮监听器
	public void resetButtonListener(ActionListener listener) {
        resetButton.addActionListener(listener);
    }
	public void addButtonListener(ActionListener listener) {
        addButton.addActionListener(listener);
    }
	public void deleteButtonListener(ActionListener listener) {
        deleteButton.addActionListener(listener);
    }
	public void changeButtonListener(ActionListener listener) {
        changeButton.addActionListener(listener);
    }
	public void modifyButtonListener(ActionListener listener) {
        modifyButton.addActionListener(listener);
    }
	public void treeListener(TreeSelectionListener listener) {
        tree.addTreeSelectionListener(listener);
    }
	
	//设置表格内容
	public void setTable(List<CapacityFocus> capacityFocuses) {
		if(capacityFocuses != null) {
			DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
			defaultTableModel.setRowCount(0);	
			for (CapacityFocus capacityFocus : capacityFocuses) {
				if(capacityFocus.getFactoryName().equals(User.staticFactoryName)) {
					Object[] record = new Object[10];
					record[0]=false;
					record[1]=String.valueOf(capacityFocus.getId());
					record[2]=capacityFocus.getCapacityFocusName();
					record[3]=capacityFocus.getCapacityFocusID();
					record[4]=capacityFocus.getCateName();
					record[5]=capacityFocus.getCateSize();
					record[6]=String.valueOf(capacityFocus.getCateStatus());
					record[7]=capacityFocus.getDescription();
					record[8]=capacityFocus.getRentStatus();
					record[9]=capacityFocus.getFactoryName();
					defaultTableModel.addRow(record);
				}
			}
		}else {
			DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
			defaultTableModel.setRowCount(0);
		}
	}
	
	//得到表格对象
	public JTable getTable() {
		return table;
	}
}
