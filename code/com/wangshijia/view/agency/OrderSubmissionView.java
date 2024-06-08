package com.wangshijia.view.agency;

import java.awt.EventQueue;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionListener;
import javax.swing.JTree;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

//经销商提交订单界面
public class OrderSubmissionView extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTree tree;
	private JButton addButton;
	private static OrderSubmissionView instance;    //单例对象的实例
	private JButton addRowButton;
	private JLabel lblNewLabel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderSubmissionView frame = new OrderSubmissionView();
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
	public OrderSubmissionView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 605, 399);
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
		scrollPane.setBounds(41, 136, 506, 218);
		contentPane.add(scrollPane);
		
		table = new JTable();
		//设置表格
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "\u4EA7\u54C1\u7F16\u53F7"
			}
		));
		scrollPane.setViewportView(table);
		addButton = new JButton("保存");
		addButton.setIcon(new ImageIcon(OrderSubmissionView.class.getResource("/icon/保存.png")));
		addButton.setBounds(454, 103, 93, 23);
		contentPane.add(addButton);
		
		addRowButton = new JButton("新建");
		addRowButton.setIcon(new ImageIcon(OrderSubmissionView.class.getResource("/icon/添加+.png")));
		addRowButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		addRowButton.setBounds(351, 103, 93, 23);
		contentPane.add(addRowButton);
		
		lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(OrderSubmissionView.class.getResource("/icon/微信图片_20230614154154.jpg")));
		lblNewLabel.setBounds(-38, 0, 720, 463);
		contentPane.add(lblNewLabel);
	}
	public static OrderSubmissionView getInstance() {
    	if (instance == null) {
            instance = new OrderSubmissionView();
        }
        return instance;
    }
	
	//新建、保存、树形菜单监听器
	public void addButtonListener(ActionListener listener) {
        addButton.addActionListener(listener);
    }
	public void addRowButtonListener(ActionListener listener) {
        addRowButton.addActionListener(listener);
    }
	public void treeListener(TreeSelectionListener listener) {
        tree.addTreeSelectionListener(listener);
    }
	
	//返回表格对象
	public JTable getTable() {
		return table;
	}
}
