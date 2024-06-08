package com.wangshijia.model;

public class Order {
    private int id;                  //ID
    private String productID;        //产品编号
    private String agencyUserName;   //经销商用户名
    private String factoryName;      //工厂名
    
    //构造函数
    public Order(int id, String productID, String agencyUserName, String factoryName) {
        this.id = id;
        this.productID = productID;
        this.agencyUserName = agencyUserName;
        this.factoryName = factoryName;
    }
    
    //get和set方法
    public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getAgencyUserName() {
		return agencyUserName;
	}

	public void setAgencyUserName(String agencyUserName) {
		this.agencyUserName = agencyUserName;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}
    public int getId() {
        return id;
    }

    public String getFactoryName() {
        return factoryName;
    }
}
