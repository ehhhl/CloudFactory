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
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;

import com.wangshijia.model.CapacityFocus;
import com.wangshijia.model.User;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

//云工厂租用设备界面
public class RentDeviceView extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTree tree;
	private JButton rentButton,resetButton;
	private static RentDeviceView instance;    //单例对象的实例
	private JLabel lblNewLabel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RentDeviceView frame = new RentDeviceView();
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
	public RentDeviceView() {
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(47, 172, 617, 263);
		contentPane_1.add(scrollPane);
		
		table = new JTable();
		//设置表格
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"", "ID", "\u8BBE\u5907\u540D\u79F0", "\u8BBE\u5907\u7F16\u53F7", "\u8BBE\u5907\u7C7B\u522B", "\u8BBE\u5907\u89C4\u683C", "\u8BBE\u5907\u72B6\u6001", "\u8BBE\u5907\u63CF\u8FF0", "\u79DF\u7528\u72B6\u6001", "\u6240\u5C5E\u5DE5\u5382"
			}
		) {
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
					add(new DefaultMutableTreeNode("订单"));
				}
			}
		));
		tree.setBounds(0, 0, 100, 95);
		contentPane_1.add(tree);
		
		rentButton = new JButton("租用");
		rentButton.setIcon(new ImageIcon(RentDeviceView.class.getResource("/icon/复制.png")));
		rentButton.setBounds(524, 125, 127, 23);
		contentPane_1.add(rentButton);
		
		resetButton = new JButton("重置");
		resetButton.setIcon(new ImageIcon(RentDeviceView.class.getResource("/icon/刷新.png")));
		resetButton.setBounds(348, 125, 127, 23);
		contentPane_1.add(resetButton);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(RentDeviceView.class.getResource("/icon/微信图片_20230614154154.jpg")));
		lblNewLabel.setBounds(0, 1, 712, 513);
		contentPane_1.add(lblNewLabel);
	}
	
	//单例函数
	public static RentDeviceView getInstance() {
    	if (instance == null) {
            instance = new RentDeviceView();
        }
        return instance;
    }
	

	//重置、租用、树形菜单监听器
	public void resetButtonListener(ActionListener listener) {
        resetButton.addActionListener(listener);
    }
	public void rentButtonListener(ActionListener listener) {
        rentButton.addActionListener(listener);
    }
	public void treeListener(TreeSelectionListener listener) {
        tree.addTreeSelectionListener(listener);
    }
	
	//得到表格对象
	public JTable getTable() {
		return table;
	}
	
	//设置表格内容
	public void setTable(List<CapacityFocus> capacityFocuses) {
		if(capacityFocuses != null) {
			DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
			defaultTableModel.setRowCount(0);	
			for (CapacityFocus capacityFocus : capacityFocuses) {
				if(capacityFocus.getRentStatus().equals("未被租用")) {
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
}
