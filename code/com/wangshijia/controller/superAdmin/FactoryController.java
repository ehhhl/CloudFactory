package com.wangshijia.controller.superAdmin;


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

import com.wangshijia.model.Factory;
import com.wangshijia.model.FactoryStatus;
import com.wangshijia.model.User;
import com.wangshijia.service.impl.superAmdin.FactoryServiceImpl;
import com.wangshijia.service.impl.superAmdin.UserServiceImpl;
import com.wangshijia.view.superAdmin.CapacityFocusCateManagementView;
import com.wangshijia.view.superAdmin.CapacityFocusManagementView;
import com.wangshijia.view.superAdmin.FactoryManagementView;
import com.wangshijia.view.superAdmin.ProductCateManagementView;
import com.wangshijia.view.superAdmin.ProductManagementView;
import com.wangshijia.view.superAdmin.UserManagementView;

public class FactoryController {
	private FactoryManagementView view;
	
	//构造函数
	public FactoryController(FactoryManagementView view) {
        this.view = view;
        UserServiceImpl userService = UserServiceImpl.getInstance();
        FactoryServiceImpl factoryService = FactoryServiceImpl.getInstance();  
        view.setTable(userService.getUsers(), factoryService.getFactories());
        view.queryButtonListener(new QueryButtonListener());
        view.reflashButtonListener(new ReflashButtonListener());
        view.changeButtonListener(new ChangeButtonListener());
        view.treeListener(new TreeListener());
    }
	
	//根据输入名称进行查询并展示
	private class QueryButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        	UserServiceImpl userService = UserServiceImpl.getInstance();
        	FactoryServiceImpl factoryService = FactoryServiceImpl.getInstance(); 
        	List<Factory> fiterFactories=new ArrayList<>();
        	for(Factory factory : factoryService.getFactories()) {
        		if(factory.getName().equals(view.getName())) {
        			fiterFactories.add(factory);
        		}
        	}
        	view.setTable(userService.getUsers(),fiterFactories);
        }
    }
	
	/*重置按钮执行
	 *重新对表格内容进行设置
	 *展示所有信息
	 */
	private class ReflashButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        	UserServiceImpl userService = UserServiceImpl.getInstance();
        	FactoryServiceImpl factoryService = FactoryServiceImpl.getInstance(); 
        	view.setTable(userService.getUsers(),factoryService.getFactories());
        }
    }
	
	//切换工厂状态
	private class ChangeButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        	JTable table = view.getTable();
        	DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
        	FactoryServiceImpl factoryService = FactoryServiceImpl.getInstance();
        	int rowCount = defaultTableModel.getRowCount(); // 获取行数
        	List<Factory> factories = factoryService.getFactories();
        	for (int row = 0; row < rowCount; row++) {
        	    Object value = defaultTableModel.getValueAt(row, 0); // 获取指定单元格的值
        	    if((boolean)value==true) {
        	    	if(factories.get(row).getStatus()==FactoryStatus.正常) {
        	    		factories.get(row).setStatus(FactoryStatus.关停);
        	    	}else {
        	    		factories.get(row).setStatus(FactoryStatus.正常);
        	    	}
        	    }
        	}
        }
    }
	
	//树形结果监听器
	private class TreeListener implements TreeSelectionListener {
		@Override
		public void valueChanged(TreeSelectionEvent e) {
			JTree tree = (JTree) e.getSource();
	        DefaultMutableTreeNode selectionNode =(DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
	        String nodeName = selectionNode.toString();
	        if(nodeName.equals("用户管理")) {
	        	view.dispose();
	        	UserManagementView userManagementView = UserManagementView.getInstance();
	        	userManagementView.setVisible(true);
	        }else if(nodeName.equals("工厂管理")) {
				
			}else if(nodeName.equals("产品类别管理")) {
				view.dispose();
				ProductCateManagementView productCateManagementView = ProductCateManagementView.getInstance();
				productCateManagementView.setVisible(true);
			}else if(nodeName.equals("产品信息管理")) {
				view.dispose();
				ProductManagementView productManagementView = ProductManagementView.getInstance();
				productManagementView.setVisible(true);
			}else if(nodeName.equals("设备类别管理")) {
				view.dispose();
				CapacityFocusCateManagementView capacityFocusCateManagementView = CapacityFocusCateManagementView.getInstance();
				capacityFocusCateManagementView.setVisible(true);
			}else if(nodeName.equals("设备信息管理")) {
				view.dispose();
				CapacityFocusManagementView capacityFocusManagementView = CapacityFocusManagementView.getInstance();
				capacityFocusManagementView.setVisible(true);
			}
		}
    }
}
