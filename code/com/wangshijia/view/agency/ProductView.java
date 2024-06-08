package com.wangshijia.view.agency;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionListener;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;

import com.wangshijia.model.Product;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

//经销商产品信息界面
public class ProductView extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField name;
	private JButton queryButton;
	private JButton resetButton;
	private static ProductView instance;    //单例对象的实例
	private JTree tree;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductView frame = new ProductView();
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
	public ProductView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 593, 375);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tree = new JTree();
		//设置树形结构
		tree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("经销商") {
				{
					add(new DefaultMutableTreeNode("产品信息"));
					add(new DefaultMutableTreeNode("订购订单"));
				}
			}
		));
		tree.setBounds(0, 0, 98, 80);
		contentPane.add(tree);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(44, 123, 502, 207);
		contentPane.add(scrollPane);
		
		table = new JTable();
		//设置表格内容
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"", "ID", "\u4EA7\u54C1\u7F16\u53F7", "\u4EA7\u54C1\u540D\u79F0", "\u4EA7\u54C1\u7C7B\u522B", "\u4EA7\u54C1\u89C4\u683C", "\u4EA7\u54C1\u63CF\u8FF0"
			}
		) {
			Class[] columnTypes = new Class[] {
				Boolean.class, String.class, String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
	});
		scrollPane.setViewportView(table);
		
		name = new JTextField();
		name.setBounds(149, 48, 129, 21);
		contentPane.add(name);
		name.setColumns(10);
		
		queryButton = new JButton("类别名称查询");
		queryButton.setIcon(new ImageIcon(ProductView.class.getResource("/icon/-search.png")));
		queryButton.setBounds(292, 37, 153, 33);
		contentPane.add(queryButton);
		
		resetButton = new JButton("重置");
		resetButton.setIcon(new ImageIcon(ProductView.class.getResource("/icon/刷新.png")));
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		resetButton.setBounds(453, 90, 93, 23);
		contentPane.add(resetButton);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(ProductView.class.getResource("/icon/微信图片_20230614154154.jpg")));
		lblNewLabel.setBounds(-32, 0, 692, 465);
		contentPane.add(lblNewLabel);
	}
	
	//查询、重置监听器
	public void queryButtonListener(ActionListener listener) {
        queryButton.addActionListener(listener);
    }
	public void resetButtonListener(ActionListener listener) {
        resetButton.addActionListener(listener);
    }
	
	//返回名字
	public String getName() {
		return name.getText();
	}
	
	//设置表格内容
	public void setTable(List<Product> products) {
		if(products != null) {
			DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
			defaultTableModel.setRowCount(0);	
			for (Product product : products) {
				Object[] record = new Object[7];
				record[0]=false;
				record[1]=String.valueOf(product.getId());
				record[2]=product.getProductID();
				record[3]=product.getProductName();
				record[4]=product.getCateName();
				record[5]=product.getCateSize();
				record[6]=product.getDescription();
				defaultTableModel.addRow(record);
			}
		}else {
			DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
			defaultTableModel.setRowCount(0);
		}
	}
	
	//返回表格对象
	public JTable getTable() {
		return table;
	}
	
	//树形菜单监听器
	public void treeListener(TreeSelectionListener listener) {
        tree.addTreeSelectionListener(listener);
    }
	
	//单例函数
	public static ProductView getInstance() {
    	if (instance == null) {
            instance = new ProductView();
        }
        return instance;
    }
}
