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

import com.wangshijia.model.CapacityFocus;
import com.wangshijia.model.CateStatus;
import com.wangshijia.model.User;
import com.wangshijia.service.impl.superAmdin.CapacityFocusServiceImpl;
import com.wangshijia.view.factoryAdmin.MyDeviceView;
import com.wangshijia.view.factoryAdmin.MyOrderView;
import com.wangshijia.view.factoryAdmin.NewOrderView;
import com.wangshijia.view.factoryAdmin.RentDeviceView;

public class RentDeviceConrtoller {
	private RentDeviceView view;
	
	//构造函数
	public  RentDeviceConrtoller(RentDeviceView view) {
        this.view = view;
        view.resetButtonListener(new ResetButtonListener());
        view.rentButtonListener(new RentButtonListener());
        view.treeListener(new TreeListener());
    }
	
	//重置按钮执行，重新对表格内容进行设置，展示所有信息
	private class ResetButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        	CapacityFocusServiceImpl capacityFocusService = CapacityFocusServiceImpl.getInstance();
        	view.setTable(capacityFocusService.getCapacityFocuses());
        }
    }
	
	//根据被选择的设备进行租用
	private class RentButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JTable table = view.getTable();
        	DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
        	CapacityFocusServiceImpl capacityFocusService = CapacityFocusServiceImpl.getInstance();
        	int rowCount = defaultTableModel.getRowCount(); // 获取行数
        	List<CapacityFocus> capacityFocuses = capacityFocusService.getCapacityFocuses();
        	for (int row = 0; row < rowCount; row++) {
        	    Object value = defaultTableModel.getValueAt(row, 0); // 获取指定单元格的值
        	    if((boolean)value==true) {
        	    	for(CapacityFocus capacityFocus : capacityFocuses) {
        	    		if(capacityFocus.getId()==Integer.parseInt(defaultTableModel.getValueAt(row, 1).toString())) {
        	    			capacityFocus.setRentStatus("已被租用");
        	    			capacityFocus.setFactoryName(User.staticFactoryName);
        	    		}
        	    	}
        	    }
        	}
        	capacityFocusService.saveCapacityFocuses(capacityFocuses);
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
	        	view.dispose();
	        	MyDeviceView myDeviceView = MyDeviceView.getInstance();
	        	myDeviceView.setVisible(true);
	        }else if(nodeName.equals("租用设备")) {
				
			}else if(nodeName.equals("我的订单")) {
				view.dispose();
				MyOrderView myOrderView = MyOrderView.getInstance();
				myOrderView.setVisible(true);
			}else if(nodeName.equals("接单")) {
				view.dispose();
				NewOrderView newOrderView = NewOrderView.getInstance();
				newOrderView.setVisible(true);
			}
		}
    }
}
