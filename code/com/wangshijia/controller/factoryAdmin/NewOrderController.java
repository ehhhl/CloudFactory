package com.wangshijia.controller.factoryAdmin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;

import com.wangshijia.model.Factory;
import com.wangshijia.model.FactoryStatus;
import com.wangshijia.model.Order;
import com.wangshijia.model.User;
import com.wangshijia.service.impl.agency.OrderServiceImpl;
import com.wangshijia.view.factoryAdmin.MyDeviceView;
import com.wangshijia.view.factoryAdmin.MyOrderView;
import com.wangshijia.view.factoryAdmin.NewOrderView;
import com.wangshijia.view.factoryAdmin.RentDeviceView;

public class NewOrderController {
	private NewOrderView view;
	
	//构造函数
	public  NewOrderController(NewOrderView view) {
        this.view = view;
        view.resetButtonListener(new ResetButtonListener());
        view.addButtonListener(new AddButtonListener());
        view.treeListener(new TreeListener());
    }
	
	/*重置按钮执行
	 *重新对表格内容进行设置
	 *展示所有信息
	 */
	private class ResetButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			OrderServiceImpl orderService = OrderServiceImpl.getInstance();
        	view.setTable(orderService.getOrders());
		}
		
	}
	
	//根据被选择的订单，增加自己的订单
	private class AddButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JTable table = view.getTable();
        	DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
        	OrderServiceImpl orderService = OrderServiceImpl.getInstance();
        	int rowCount = defaultTableModel.getRowCount(); // 获取行数
        	List<Order> orders = orderService.getOrders();
        	for (int row = 0; row < rowCount; row++) {
        	    Object value = defaultTableModel.getValueAt(row, 0); // 获取指定单元格的值
        	    if((boolean)value==true) {
        	    	for(Order order:orders) {
        	    		if(order.getId()==Integer.parseInt(defaultTableModel.getValueAt(row, 1).toString())) {
        	    			order.setFactoryName(User.staticFactoryName);
        	    		}
        	    	}
        	    }
        	}
        	orderService.saveOrders(orders);
		}
	}
	
	//树形结构监听器
	private class TreeListener implements TreeSelectionListener {
		@Override
		public void valueChanged(TreeSelectionEvent e) {
			JTree tree = (JTree) e.getSource();
	        DefaultMutableTreeNode selectionNode =(DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
	        String nodeName = selectionNode.toString();
	        if(nodeName.equals("我的设备")) {
	        	MyDeviceView myDeviceView = MyDeviceView.getInstance();
	        	view.dispose();
	        	myDeviceView.setVisible(true);
	        }else if(nodeName.equals("租用设备")) {
				view.dispose();
				RentDeviceView rentDeviceView = RentDeviceView.getInstance();
				rentDeviceView.setVisible(true);
			}else if(nodeName.equals("我的订单")) {
				view.dispose();
				MyOrderView myOrderView = MyOrderView.getInstance();
				myOrderView.setVisible(true);
			}else if(nodeName.equals("接单")) {
				
			}
		}
	}
}
