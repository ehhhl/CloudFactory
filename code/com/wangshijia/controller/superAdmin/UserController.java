package com.wangshijia.controller.superAdmin;

import com.wangshijia.model.Factory;
import com.wangshijia.model.FactoryStatus;
import com.wangshijia.model.User;
import com.wangshijia.model.UserRole;
import com.wangshijia.service.impl.superAmdin.FactoryServiceImpl;
import com.wangshijia.service.impl.superAmdin.UserServiceImpl;
import com.wangshijia.view.superAdmin.CapacityFocusCateManagementView;
import com.wangshijia.view.superAdmin.CapacityFocusManagementView;
import com.wangshijia.view.superAdmin.FactoryManagementView;
import com.wangshijia.view.superAdmin.ProductCateManagementView;
import com.wangshijia.view.superAdmin.ProductManagementView;
import com.wangshijia.view.superAdmin.UserManagementView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class UserController {
    private UserManagementView view;

    public UserController(UserManagementView view) {
        this.view = view;
        //构造函数
        view.queryButtonListener(new QueryButtonListener());
        view.resetButtonListener(new ResetButtonListener());
        view.addButtonListener(new AddButtonListener());
        view.deleteButtonListener(new DeleteButtonListener());
        view.modifyButtonListener(new ModifyButtonListener());
        view.treeListener(new TreeListener());
    }
    
    //根据输入名称进行查询并展示
    private class QueryButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        	UserServiceImpl userService = UserServiceImpl.getInstance();
        	List<User> fiterUsers=new ArrayList<>();
        	for(User user : userService.getUsers()) {
        		if(user.getName().equals(view.getName())) {
        			fiterUsers.add(user);
        		}
        	}
        	view.setTable(fiterUsers);
        }
    }
    
    /*重置按钮执行
     *重新对表格内容进行设置
     *展示所有信息
     */
    private class ResetButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        	UserServiceImpl userService = UserServiceImpl.getInstance();
        	view.setTable(userService.getUsers());
        }
    }
    
    //新增一行用户信息,真正的增加在ModifyButtonListener方法中实现
    private class AddButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
           JTable table = view.getTable();
           UserServiceImpl userService = UserServiceImpl.getInstance();
           DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
           Object[] record = new Object[6];
           record[0]=false;
           record[1]=userService.getMaxID()+1;
           record[2]="";
           record[3]="";
           record[4]="";
           record[5]="";
           defaultTableModel.addRow(record);
        }
    }

    //删除被选择的用户
    private class DeleteButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        	JTable table = view.getTable();
        	DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
        	UserServiceImpl userService = UserServiceImpl.getInstance();
        	FactoryServiceImpl factoryService = FactoryServiceImpl.getInstance();
        	int rowCount = defaultTableModel.getRowCount(); // 获取行数
        	List<User> users = userService.getUsers();
        	List<Factory> factories = factoryService.getFactories();
        	int delete=0;	//已经删除的行数
        	for (int row = 0; row < rowCount; row++) {
        	    Object value = defaultTableModel.getValueAt(row, 0); // 获取指定单元格的值
        	    if((boolean)value==true) {
        	    	users.remove(row-delete);
        	    	delete++;
        	    	String factoryName = String.valueOf(defaultTableModel.getValueAt(row, 7));
        	    	for(Factory factory : factories) {
        	    		if(factory.getName().equals(factoryName)) {
        	    			factories.remove(factory);
        	    			break;
        	    		}
        	    	}
        	    }
        	}
        	userService.saveUsers(users);
        	factoryService.saveFactories(factories);
        }
    }

    //就修改后的用户信息进行保存
    private class ModifyButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        	JTable table = view.getTable();
        	DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
        	UserServiceImpl userService = UserServiceImpl.getInstance();
        	FactoryServiceImpl factoryService = FactoryServiceImpl.getInstance();
        	int rowCount = defaultTableModel.getRowCount(); // 获取行数
        	int columnCount = defaultTableModel.getColumnCount(); // 获取列数
        	List<User> users = userService.getUsers();
        	users.clear();
        	for (int row = 0; row < rowCount; row++) {
        		User user=new User();
        	    for (int column = 1; column < columnCount; column++) {
        	        Object value = defaultTableModel.getValueAt(row, column); // 获取指定单元格的值
        	        if(column==1) {
        	        	user.setId(Integer.parseInt(String.valueOf(value)));
        	        }else if(column==2) {
        	        	user.setUserName(String.valueOf(value));
        	        }else if(column==3){
        	        	user.setPassword(String.valueOf(value));
        	        }else if(column==4) {
        	        	user.setName(String.valueOf(value));
        	        }else if(column==5) {
        	        	user.setContact(String.valueOf(value));
        	        }else if(column==6) {
        	        	user.setRole(UserRole.valueOf(String.valueOf(value)));
        	        }else if(column==7) {
        	        	user.setFactoryName(value!=null?String.valueOf(value):"");
        	        }else if(column==8) {
        	        	user.setFactoryDescription(value!=null?String.valueOf(value):"");
        	        }
        	    }
        	    users.add(user);
        	    if(user.getRole()==UserRole.云工厂) {
	        		boolean exist=false;
	        		for(Factory factory : factoryService.getFactories()) {
	        			if(factory.getName().equals(user.getFactoryName())) {
	        				exist=true;
	        				break;
	        			}
	        		}
	        		if(exist==false) {
	        			factoryService.getFactories().add(new Factory(factoryService.getMaxID()+1,user.getFactoryName(),FactoryStatus.正常));
	        		}
	        	}
        	}
        	userService.saveUsers(users);
        	factoryService.saveFactories(factoryService.getFactories());
        }
    }
    
    //树形结构监听器
    private class TreeListener implements TreeSelectionListener {
		@Override
		public void valueChanged(TreeSelectionEvent e) {
			JTree tree = (JTree) e.getSource();
	        DefaultMutableTreeNode selectionNode =(DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
	        String nodeName = selectionNode.toString();
			if(nodeName.equals("工厂管理")) {
				view.dispose();
				FactoryManagementView factoryManagementView = FactoryManagementView.getInstance();
				factoryManagementView.setVisible(true);
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
