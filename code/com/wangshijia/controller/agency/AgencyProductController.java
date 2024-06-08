package com.wangshijia.controller.agency;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import com.wangshijia.model.Product;
import com.wangshijia.service.impl.superAmdin.ProductServiceImpl;
import com.wangshijia.view.agency.OrderSubmissionView;
import com.wangshijia.view.agency.ProductView;

public class AgencyProductController {
	private ProductView view;
	
	//构造函数，添加监听器
	public AgencyProductController(ProductView view) {
        this.view = view;
        view.queryButtonListener(new QueryButtonListener());
        view.resetButtonListener(new ResetButtonListener());
        view.treeListener(new TreeListener());
    }
	
	//查询按钮执行，根据名称进行过滤，然后展示到表格中
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
	
	//重置按钮执行，重新对表格内容进行设置，展示所有信息
	private class ResetButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        	ProductServiceImpl productService = ProductServiceImpl.getInstance();
        	view.setTable(productService.getProducts());
        }
    }
	
	//树形菜单监听器
	private class TreeListener implements TreeSelectionListener {
		@Override
		public void valueChanged(TreeSelectionEvent e) {
			JTree tree = (JTree) e.getSource();
	        DefaultMutableTreeNode selectionNode =(DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
	        String nodeName = selectionNode.toString();
	        if(nodeName.equals("产品信息")) {
	        }else if(nodeName.equals("订购订单")) {
				view.dispose();
				OrderSubmissionView orderSubmissionView = OrderSubmissionView.getInstance();
				orderSubmissionView.setVisible(true);
			}
		}
    }
}
