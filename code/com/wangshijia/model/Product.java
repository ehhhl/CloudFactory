package com.wangshijia.model;

public class Product {
	private int id;	            //ID
	private String productID;	//产品编号
	private String productName;	//产品名
	private String cateName;	//产品类别名
	private String cateSize;	//产品规格
	private String description;	//产品描述

	//构造函数
    public Product(int id, String productID, String productName, String cateName, String cateSize, String description) {
        this.id = id;
        this.productID = productID;
        this.productName = productName;
        this.cateName = cateName;
        this.cateSize = cateSize;
        this.description = description;
    }

    //构造空参函数
    public Product() {
		// TODO Auto-generated constructor stub
	}

    //get和set方法
	public int getId() {
        return id;
    }

    public String getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public String getCateName() {
        return cateName;
    }
    
    public String getCateSize() {
        return cateSize;
    }

    public String getDescription() {
        return description;
    }

	public void setId(int id) {
		this.id = id;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public void setCateSize(String cateSize) {
		this.cateSize = cateSize;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
