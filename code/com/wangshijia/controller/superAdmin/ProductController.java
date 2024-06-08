package com.wangshijia.controller.superAdmin;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;

import com.wangshijia.model.Factory;
import com.wangshijia.model.FactoryStatus;
import com.wangshijia.model.Product;
import com.wangshijia.model.ProductCate;
import com.wangshijia.model.User;
import com.wangshijia.model.UserRole;
import com.wangshijia.service.impl.superAmdin.FactoryServiceImpl;
import com.wangshijia.service.impl.superAmdin.ProductCateServiceImpl;
import com.wangshijia.service.impl.superAmdin.ProductServiceImpl;
import com.wangshijia.service.impl.superAmdin.UserServiceImpl;
import com.wangshijia.view.superAdmin.CapacityFocusCateManagementView;
import com.wangshijia.view.superAdmin.CapacityFocusManagementView;
import com.wangshijia.view.superAdmin.FactoryManagementView;
import com.wangshijia.view.superAdmin.ProductCateManagementView;
import com.wangshijia.view.superAdmin.ProductManagementView;
import com.wangshijia.view.superAdmin.UserManagementView;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter; 

public class ProductController {
	private ProductManagementView view;
	//构造函数
	public ProductController(ProductManagementView view) {
        this.view = view;
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
        	ProductServiceImpl productService = ProductServiceImpl.getInstance();
        	List<Product> fiterProducts=new ArrayList<>();
        	for(Product product : productService.getProducts()) {
        		if(product.getCateName().equals(view.getName())) {
        			fiterProducts.add(product);
        		}
        	}
        	view.setTable(fiterProducts);
        }
    }
	
	/*重置按钮执行
	 *重新对表格内容进行设置
	 *展示所有信息
	 */
	private class ResetButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        	ProductServiceImpl productService = ProductServiceImpl.getInstance();
        	view.setTable(productService.getProducts());
        }
    }
	
	//新增一行设备信息，自动生成产品编号
	private class AddButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
           JTable table = view.getTable();
           ProductServiceImpl productService = ProductServiceImpl.getInstance();
           DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
           Object[] record = new Object[7];
           record[0]=false;
           record[1]=productService.getMaxID()+1;
           // 获取当前的年月日时分秒毫秒
           LocalDateTime now = LocalDateTime.now();
           // 定义日期时间格式
           DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
           // 将日期时间格式化为字符串
           String formattedDateTime = now.format(formatter);
           record[2]="PNO"+formattedDateTime;
           record[3]="";
           record[4]="";
           record[5]="";
           record[6]="";
           defaultTableModel.addRow(record);
        }
    }
	
	//删除被选择的产品
	private class DeleteButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        	JTable table = view.getTable();
        	DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
        	ProductServiceImpl productService = ProductServiceImpl.getInstance();
        	int rowCount = defaultTableModel.getRowCount(); // 获取行数
        	List<Product> products = productService.getProducts();
        	int delete=0;	//已经删除的行数
        	for (int row = 0; row < rowCount; row++) {
        	    Object value = defaultTableModel.getValueAt(row, 0); // 获取指定单元格的值
        	    if((boolean)value==true) {
        	    	products.remove(row-delete);
        	    	delete++;
        	    }
        	}
        	productService.saveProducts(products);
        }
    }
	
	/*修改产品信息
	 *如果修改后有新类别出现
	 *则弹出报错提示框
	 */
	private class ModifyButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        	JTable table = view.getTable();
        	DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
        	ProductServiceImpl productService = ProductServiceImpl.getInstance();
        	ProductCateServiceImpl productCateService = ProductCateServiceImpl.getInstance();
        	int rowCount = defaultTableModel.getRowCount(); // 获取行数
        	int columnCount = defaultTableModel.getColumnCount(); // 获取列数
        	List<Product> products = productService.getProducts();
        	List<ProductCate> productCates = productCateService.getProductCates();
        	boolean newCate=false;	//修改后是否有新出现的类别
        	for (int row = 0; row < rowCount; row++) {
        		Object value = defaultTableModel.getValueAt(row, 4); // 获取指定单元格的值
        		boolean match=false;	//一个记录的产品类别是否已经存在
        		for(ProductCate productCate:productCates) {
        			if(productCate.getCateName().equals(String.valueOf(value))) {
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
        	products.clear();
        	for (int row = 0; row < rowCount; row++) {
        		Product product=new Product();
        	    for (int column = 1; column < columnCount; column++) {
        	        Object value = defaultTableModel.getValueAt(row, column); // 获取指定单元格的值
        	        if(column==1) {
        	        	product.setId(Integer.parseInt(String.valueOf(value)));
        	        }else if(column==2) {
        	        	product.setProductID(String.valueOf(value));
        	        }else if(column==3){
        	        	product.setProductName(String.valueOf(value));
        	        }else if(column==4) {
        	        	product.setCateName(String.valueOf(value));
        	        }else if(column==5) {
        	        	product.setCateSize(String.valueOf(value));
        	        }else if(column==6) {
        	        	product.setDescription(String.valueOf(value));
        	        }
        	    }
        	    products.add(product);
        	}
        	productService.saveProducts(products);
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
