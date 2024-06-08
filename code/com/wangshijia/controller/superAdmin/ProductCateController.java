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

public class ProductCateController {
	private ProductCateManagementView view;
	
	//构造函数
	public ProductCateController(ProductCateManagementView view) {
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
        	ProductCateServiceImpl productCateService = ProductCateServiceImpl.getInstance();
        	List<ProductCate> fiterProductCates=new ArrayList<>();
        	for(ProductCate productCate : productCateService.getProductCates()) {
        		if(productCate.getCateName().equals(view.getName())) {
        			fiterProductCates.add(productCate);
        		}
        	}
        	view.setTable(fiterProductCates);
        }
    }
	
	/*重置按钮执行
	 *重新对表格内容进行设置
	 *展示所有信息
	 */
    private class ResetButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        	ProductCateServiceImpl productCateService = ProductCateServiceImpl.getInstance();
        	view.setTable(productCateService.getProductCates());
        }
    }
    
    //增加新一行
    private class AddButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
           JTable table = view.getTable();
           ProductCateServiceImpl productCateService = ProductCateServiceImpl.getInstance();
           DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
           Object[] record = new Object[3];
           record[0]=false;
           record[1]=productCateService.getMaxID()+1;
           record[2]="";
           defaultTableModel.addRow(record);
        }
    }

    /*删除产品类别
     *如果已经有产品是此类别
     *则无法删除
     */
    private class DeleteButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        	JTable table = view.getTable();
        	DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
        	ProductServiceImpl productService = ProductServiceImpl.getInstance();
        	ProductCateServiceImpl productCateService = ProductCateServiceImpl.getInstance();
        	int rowCount = defaultTableModel.getRowCount(); // 获取行数
        	List<Product> products = productService.getProducts();
        	List<ProductCate> productCates = productCateService.getProductCates();
        	int delete=0;	//已经删除的行数
        	boolean exist=false;		//要删除的类别是否已经有产品
        	for (int row = 0; row < rowCount; row++) {
        	    Object value = defaultTableModel.getValueAt(row, 0); // 获取指定单元格的值
        	    if((boolean)value==true) {
        	    	for(Product product:products) {
        	    		if(product.getCateName().equals(String.valueOf(defaultTableModel.getValueAt(row, 2)))) {
        	    			exist=true;
        	    			break;
        	    		}
        	    	}
        	    }
        	}
        	if(exist==true) {
        		JOptionPane.showMessageDialog(null, "要删除的类别已经有产品！！！");
        		return;
        	}
        	for (int row = 0; row < rowCount; row++) {
        	    Object value = defaultTableModel.getValueAt(row, 0); // 获取指定单元格的值
        	    if((boolean)value==true) {
        	    	productCates.remove(row-delete);
        	    	delete++;
        	    }
        	}
        	productCateService.saveProductCates(productCates);
        }
    }

   /*将修改后的产品类别保存
    *如果修改的类别已经有产品是此类别
    *则无法修改
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
        	boolean exist=true;		//判断产品信息中的类别是否存在在表中
        	for (int row = 0; row < rowCount; row++) {
        	    Object value = defaultTableModel.getValueAt(row, 2); // 获取指定单元格的值
    	    	for(Product product:products) {
    	    		if(product.getCateName().equals(String.valueOf(value))) {
    	    			exist=true;
    	    			break;
    	    		}
        	    }
        	}
        	for(Product product:products) {
        		boolean cexist=false;	//一个产品的类别是否存在在表中
        		for (int row = 0; row < rowCount; row++) {
        			 Object value = defaultTableModel.getValueAt(row, 2); // 获取指定单元格的值
        			 if(product.getCateName().equals(String.valueOf(value))) {
     	    			cexist=true;
     	    			break;
     	    		}
        		}
        		if(cexist==false) {
        			exist = false;
        		}
    	    }
        	if(exist==false) {
        		JOptionPane.showMessageDialog(null, "要修改的类别已经有产品！！！");
        		return;
        	}
        	productCates.clear();
        	for (int row = 0; row < rowCount; row++) {
        		ProductCate productCate=new ProductCate();
        	    for (int column = 1; column < columnCount; column++) {
        	        Object value = defaultTableModel.getValueAt(row, column); // 获取指定单元格的值
        	        if(column==1) {
        	        	productCate.setId(Integer.parseInt(String.valueOf(value)));
        	        }else if(column==2) {
        	        	productCate.setCateName(String.valueOf(value));
        	        }
        	    }
        	    productCates.add(productCate);
        	productCateService.saveProductCates(productCates);
        	}
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
