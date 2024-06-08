package com.wangshijia.controller.factoryAdmin;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import com.wangshijia.service.impl.superAmdin.CapacityFocusCateServiceImpl;
import com.wangshijia.service.impl.superAmdin.CapacityFocusServiceImpl;
import com.wangshijia.view.factoryAdmin.MyDeviceView;
import com.wangshijia.view.factoryAdmin.MyOrderView;
import com.wangshijia.view.factoryAdmin.NewOrderView;
import com.wangshijia.view.factoryAdmin.RentDeviceView;

public class MyDeviceController {
	private MyDeviceView view;
	
	//构造函数
	public  MyDeviceController(MyDeviceView view) {
        this.view = view;
        view.resetButtonListener(new ResetButtonListener());
        view.addButtonListener(new AddButtonListener());
        view.deleteButtonListener(new DeleteButtonListener());
        view.changeButtonListener(new ChangeButtonListener());
        view.modifyButtonListener(new ModifyButtonListener());
        view.treeListener(new TreeListener());
    }
	
	/*重置按钮执行
	 * 重新对表格内容进行设置
	 * 展示所有信息
	 */
	private class ResetButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        	CapacityFocusServiceImpl capacityFocusService = CapacityFocusServiceImpl.getInstance();
        	view.setTable(capacityFocusService.getCapacityFocuses());
        }
    }
	
	//新建设备，自动生成设备编号
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
	
	//删除被选择的设备，只能删除工厂设备
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
        	    if((boolean)value==true && String.valueOf(defaultTableModel.getValueAt(row, 8)).equals("工厂设备")) {
        	    	capacityFocus.remove(row-delete);
        	    	delete++;
        	    }
        	}
        	capacityFocusService.saveCapacityFocuses(capacityFocus);
        }
    }
	
	//切换被选择的设备状态
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
        	    	CapacityFocus capacityFocusTmp = null;
        	    	for(CapacityFocus capacityFocus:capacityFocuses) {
        	    		if(capacityFocus.getId()==Integer.parseInt(defaultTableModel.getValueAt(row, 1).toString())) {
        	    			capacityFocusTmp=capacityFocus;
        	    		}
        	    	}
        	    	if(capacityFocusTmp.getCateStatus()==CateStatus.开机) {
        	    		capacityFocusTmp.setCateStatus(CateStatus.关机);
        	    	}else {
        	    		capacityFocusTmp.setCateStatus(CateStatus.开机);
        	    	}
        	    }
        	}
        	capacityFocusService.saveCapacityFocuses(capacityFocuses);
		}
	}
	
	/*保存修改的设备信息到json文件中
	 *判断修改后的数据有没有新的类别出现
	 */
	private class ModifyButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JTable table = view.getTable();
        	DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
        	CapacityFocusServiceImpl capacityFocusService = CapacityFocusServiceImpl.getInstance();
        	CapacityFocusCateServiceImpl capacityFocusCateService = CapacityFocusCateServiceImpl.getInstance();
        	int rowCount = defaultTableModel.getRowCount(); // 获取行数
        	boolean match=false;
        	List<CapacityFocus> capacityFocuses = capacityFocusService.getCapacityFocuses();
        	List<CapacityFocusCate> capacityFocusCates = capacityFocusCateService.getCapacityFocusCates();
        	Object value = defaultTableModel.getValueAt(rowCount-1, 4);
        	for(CapacityFocusCate capacityFocusCate:capacityFocusCates) {
    			if(capacityFocusCate.getCateName().equals(String.valueOf(value))) {
    				match=true;
    				break;
    			}
    		}
        	if(match==false) {
        		JOptionPane.showMessageDialog(null, "新数据有未出现的类别，请重新输入！！！");
        		return;
        	}
        	capacityFocuses.add(new CapacityFocus(Integer.parseInt(defaultTableModel.getValueAt(rowCount-1, 1).toString()), defaultTableModel.getValueAt(rowCount-1, 2).toString(), defaultTableModel.getValueAt(rowCount-1, 3).toString(), defaultTableModel.getValueAt(rowCount-1, 4).toString(), defaultTableModel.getValueAt(rowCount-1, 5).toString(), CateStatus.valueOf(defaultTableModel.getValueAt(rowCount-1, 6).toString()) , defaultTableModel.getValueAt(rowCount-1, 7).toString(), defaultTableModel.getValueAt(rowCount-1, 8).toString(), defaultTableModel.getValueAt(rowCount-1, 9).toString()));
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
	        	
	        }else if(nodeName.equals("租用设备")) {
				view.dispose();
				RentDeviceView rentDeviceView = RentDeviceView.getInstance();
				rentDeviceView.setVisible(true);
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
