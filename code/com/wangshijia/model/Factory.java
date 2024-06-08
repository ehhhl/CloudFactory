package com.wangshijia.model;

public class Factory {
    private int id;                   //ID
    private String name;              //工厂名
    private FactoryStatus status;     //工厂状态：正常、关停

    //构造函数
    public Factory(int id, String name, FactoryStatus status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    //get和set方法
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

	public FactoryStatus getStatus() {
		return status;
	}

	public void setStatus(FactoryStatus status) {
		this.status=status;
	}
}
