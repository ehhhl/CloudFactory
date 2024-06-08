package com.wangshijia.model;

public class CapacityFocus {
	private int id;
    private String capacityFocusID;      //设备编号
    private String capacityFocusName;    //设备名称
    private String cateName;             //设备类别
    private String cateSize;             //设备规格
    private CateStatus cateStatus;       //设备状态 开机、关机
    private String description;          //设备描述
    private String rentStatus;           //租用状态 已被租用、未被租用、工厂设备
    private String factoryName;          //工厂名字

    //get和set方法
    public CateStatus getCateStatus() {
		return cateStatus;
	}

	public void setCateStatus(CateStatus cateStatus) {
		this.cateStatus = cateStatus;
	}

	public String getRentStatus() {
		return rentStatus;
	}

	public void setRentStatus(String rentStatus) {
		this.rentStatus = rentStatus;
	}

	public String getFactoryName() {
		return factoryName;
	}

	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}

	public CapacityFocus(int id, String capacityFocusName, String capacityFocusID,  String cateName, String cateSize, CateStatus cateStatus,String description,String rentStatus,String factoryName) {
        this.id = id;
        this.capacityFocusID = capacityFocusID;
        this.capacityFocusName = capacityFocusName;
        this.cateName = cateName;
        this.cateSize = cateSize;
        this.cateStatus = cateStatus;
        this.description = description;
        this.rentStatus=rentStatus;
        this.factoryName = factoryName;
    }

    public CapacityFocus() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
        return id;
    }

    public String getCapacityFocusID() {
        return capacityFocusID;
    }

    public String getCapacityFocusName() {
        return capacityFocusName;
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

	public void setCapacityFocusID(String capacityFocusID) {
		this.capacityFocusID = capacityFocusID;
	}

	public void setCapacityFocusName(String capacityFocusName) {
		this.capacityFocusName = capacityFocusName;
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
