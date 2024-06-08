package com.wangshijia.controller.agency;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;

import com.wangshijia.model.Order;
import com.wangshijia.model.User;
import com.wangshijia.service.impl.agency.OrderServiceImpl;
import com.wangshijia.view.agency.OrderSubmissionView;
import com.wangshijia.view.agency.ProductView;

public class OrderSubmissionController {
	private OrderSubmissionView view;
	
	//构造函数
	public OrderSubmissionController(OrderSubmissionView view) {
        this.view = view;
        view.addButtonListener(new AddButtonListener());
        view.addRowButtonListener(new AddRowButtonListener());
        view.treeListener(new TreeListener());
    }
	
	/*
	 * 新增订单按钮执行
	 * 读取表格的最后一行
	 * 然后输入到orders.json中
	 */
	public class AddButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			OrderServiceImpl orderService = OrderServiceImpl.getInstance();
        	List<Order> orders=orderService.getOrders();
        	JTable table = view.getTable();
        	DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
        	int rowCount = defaultTableModel.getRowCount(); // 获取行数
        	orders.add(new Order(orderService.getMaxID()+1, defaultTableModel.getValueAt(rowCount-1, 1).toString(), User.staticAgencyUserNameString, ""));
        	orderService.saveOrders(orders);
		}
		
	}
	
	//表格新建一行
	public class AddRowButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JTable table = view.getTable();
			DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
			OrderServiceImpl orderService = OrderServiceImpl.getInstance();
			Object[] record = new Object[2];
			record[0]=orderService .getMaxID()+1;
			record[1]="";
			defaultTableModel.addRow(record);
		}
	}
	
	//树形结构监听器
	private class TreeListener implements TreeSelectionListener {
		@Override
		public void valueChanged(TreeSelectionEvent e) {
			JTree tree = (JTree) e.getSource();
	        DefaultMutableTreeNode selectionNode =(DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
	        String nodeName = selectionNode.toString();
	        if(nodeName.equals("产品信息")) {
	        	ProductView productView = ProductView.getInstance();
	        	view.dispose();
	        	productView.setVisible(true);
	        }else if(nodeName.equals("订购订单")) {
				
			}
		}
    }
}
