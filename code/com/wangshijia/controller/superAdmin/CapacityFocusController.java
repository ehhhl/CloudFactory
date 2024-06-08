package com.wangshijia.controller.superAdmin;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;

import com.wangshijia.model.CapacityFocus;
import com.wangshijia.model.CapacityFocusCate;
import com.wangshijia.model.CateStatus;
import com.wangshijia.model.FactoryStatus;
import com.wangshijia.service.impl.superAmdin.CapacityFocusCateServiceImpl;
import com.wangshijia.service.impl.superAmdin.CapacityFocusServiceImpl;
import com.wangshijia.view.superAdmin.CapacityFocusCateManagementView;
import com.wangshijia.view.superAdmin.CapacityFocusManagementView;
import com.wangshijia.view.superAdmin.FactoryManagementView;
import com.wangshijia.view.superAdmin.ProductCateManagementView;
import com.wangshijia.view.superAdmin.ProductManagementView;
import com.wangshijia.view.superAdmin.UserManagementView;

public class CapacityFocusController {
	private CapacityFocusManagementView view;
	
	//构造函数
	public  CapacityFocusController(CapacityFocusManagementView view) {
        this.view = view;
        view.queryButtonListener(new QueryButtonListener());
        view.resetButtonListener(new ResetButtonListener());
        view.addButtonListener(new AddButtonListener());
        view.deleteButtonListener(new DeleteButtonListener());
        view.modifyButtonListener(new ModifyButtonListener());
        view.changeButtonListener(new ChangeButtonListener());
        view.treeListener(new TreeListener());
    }
	
	//根据输入名称进行查询并展示
	private class QueryButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        	CapacityFocusServiceImpl capacityFocusService = CapacityFocusServiceImpl.getInstance();
        	List<CapacityFocus> fiterCapacityFocus=new ArrayList<>();
        	for(CapacityFocus capacityFocus : capacityFocusService.getCapacityFocuses()) {
        		if(capacityFocus.getCapacityFocusName().equals(view.getName())) {
        			fiterCapacityFocus.add(capacityFocus);
        		}
        	}
        	view.setTable(fiterCapacityFocus);
        }
    }
	
	/*重置按钮执行
	 *重新对表格内容进行设置
	 *展示所有信息
	 */
	private class ResetButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        	CapacityFocusServiceImpl capacityFocusService = CapacityFocusServiceImpl.getInstance();
        	view.setTable(capacityFocusService.getCapacityFocuses());
        }
    }
	
	//新增一行设备信息，自动生成设备编号
	private class AddButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
           JTable table = view.getTable();
           CapacityFocusServiceImpl capacityFocusService = CapacityFocusServiceImpl.getInstance();
           DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
           Object[] record = new Object[10];
           record[0]=false;
           record[1]=capacityFocusService.getMaxID()+1;
           record[2]="";
           // 获取当前的年月日时分秒毫秒
           LocalDateTime now = LocalDateTime.now();
           // 定义日期时间格式
           DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
           // 将日期时间格式化为字符串
           String formattedDateTime = now.format(formatter);
           record[3]="DNO"+formattedDateTime;
           record[4]="";
           record[5]="";
           record[6]="";
           record[7]="";
           record[8]="";
           record[9]="";
           defaultTableModel.addRow(record);
        }
    }
	
	//删除设备，只能删除未被租用的设备
	private class DeleteButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        	JTable table = view.getTable();
        	DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
        	CapacityFocusServiceImpl capacityFocusService = CapacityFocusServiceImpl.getInstance();
        	int rowCount = defaultTableModel.getRowCount(); // 获取行数
        	List<CapacityFocus> capacityFocus = capacityFocusService.getCapacityFocuses();
        	int delete=0;	//已经删除的行数
        	for (int row = 0; row < rowCount; row++) {
        	    Object value = defaultTableModel.getValueAt(row, 0); // 获取指定单元格的值
        	    if((boolean)value==true && String.valueOf(defaultTableModel.getValueAt(row, 8)).equals("未被租用")) {
        	    	capacityFocus.remove(row-delete);
        	    	delete++;
        	    }
        	}
        	capacityFocusService.saveCapacityFocuses(capacityFocus);
        }
    }
	
	/*修改设备信息
	 *如果修改后有新类别出现
	 *则弹出报错提示框
	 */
	private class ModifyButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        	JTable table = view.getTable();
        	DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
        	CapacityFocusServiceImpl capacityFocusService = CapacityFocusServiceImpl.getInstance();
        	CapacityFocusCateServiceImpl capacityFocusCateService = CapacityFocusCateServiceImpl.getInstance();
        	int rowCount = defaultTableModel.getRowCount(); // 获取行数
        	int columnCount = defaultTableModel.getColumnCount(); // 获取列数
        	List<CapacityFocus> capacityFocuses = capacityFocusService.getCapacityFocuses();
        	List<CapacityFocusCate> capacityFocusCates = capacityFocusCateService.getCapacityFocusCates();
        	boolean newCate=false;	//修改后是否有新出现的类别
        	for (int row = 0; row < rowCount; row++) {
        		Object value = defaultTableModel.getValueAt(row, 4); // 获取指定单元格的值
        		boolean match=false;	//一个记录的产品类别是否已经存在
        		for(CapacityFocusCate capacityFocusCate:capacityFocusCates) {
        			if(capacityFocusCate.getCateName().equals(String.valueOf(value))) {
        				match=true;
        				break;
        			}
        		}
        		if(match==false) {
        			newCate=true;
        			break;
        		}
        	}
        	if(newCate==true) {
        		JOptionPane.showMessageDialog(null, "新数据有未出现的类别，请重新输入！！！");
        		return;
        	}
        	capacityFocuses.clear();
        	for (int row = 0; row < rowCount; row++) {
        		CapacityFocus capacityFocus=new CapacityFocus();
        	    for (int column = 1; column < columnCount; column++) {
        	        Object value = defaultTableModel.getValueAt(row, column); // 获取指定单元格的值
        	        if(column==1) {
        	        	capacityFocus.setId(Integer.parseInt(String.valueOf(value)));
        	        }else if(column==2) {
        	        	capacityFocus.setCapacityFocusName(String.valueOf(value));
        	        }else if(column==3){
        	        	capacityFocus.setCapacityFocusID(String.valueOf(value));
        	        }else if(column==4) {
        	        	capacityFocus.setCateName(String.valueOf(value));
        	        }else if(column==5) {
        	        	capacityFocus.setCateSize(String.valueOf(value));
        	        }else if(column==6) {
        	        	capacityFocus.setCateStatus(CateStatus.valueOf(String.valueOf(value)));
        	        }else if(column==7) {
        	        	capacityFocus.setDescription(String.valueOf(value));
        	        }
        	        else if(column==8) {
        	        	capacityFocus.setRentStatus(String.valueOf(value));
        	        }else if(column==9) {
        	        	capacityFocus.setFactoryName(String.valueOf(value));
        	        }
        	    }
        	    capacityFocuses.add(capacityFocus);
        	}
        	capacityFocusService.saveCapacityFocuses(capacityFocuses);
        }
    }
	
	//切换设备状态，只能切换未被租用的设备
	private class ChangeButtonListener implements ActionListener {
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
        	    	if(capacityFocuses.get(row).getCateStatus()==CateStatus.开机 && capacityFocuses.get(row).getRentStatus().equals("未被租用")) {
        	    		capacityFocuses.get(row).setCateStatus(CateStatus.关机);
        	    	}else {
        	    		capacityFocuses.get(row).setCateStatus(CateStatus.开机);
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
	        if(nodeName.equals("用户管理")) {
	        	view.dispose();
	        	UserManagementView userManagementView = UserManagementView.getInstance();
	        	userManagementView.setVisible(true);
	        }else if(nodeName.equals("工厂管理")) {
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
				
			}
		}
    }
}
