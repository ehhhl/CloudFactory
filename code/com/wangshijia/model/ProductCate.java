package com.wangshijia.model;

public class ProductCate {
	private int id;              //ID
    private String cateName;     //类别名称
    
    //构造函数
    public ProductCate(int id, String cateName) {
    	this.id=id;
    	this.cateName=cateName;
    }
    
    //get和set方法
	public ProductCate() {
		// TODO Auto-generated constructor stub
	}
	public String getCateName() {
		return cateName;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setCateName(String cateName) {
		this.cateName = cateName;
	}
	public int getId() {
		return id;
	}
}
