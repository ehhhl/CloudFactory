package com.wangshijia.model;

public class CapacityFocusCate {
	private int id;                //ID
    private String cateName;       //类别名称
    
    //构造函数
    public CapacityFocusCate(int id, String cateName) {
    	this.id=id;
    	this.cateName=cateName;
    }
    //构造空参函数
	public CapacityFocusCate() {
		// TODO Auto-generated constructor stub
	}
	
	//get和set方法
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
