package com.wangshijia.model;

public class User {
	private int id;	            //ID
    private String userName;	//用户名
    private String password;	//用户密码
    private String name;	    //姓名
    private String contact;	    //联系方式
    private UserRole role;	    //用户类型
    private String factoryName;	//工厂名
    private String factoryDescription;	//工厂描述
    public static String staticFactoryName="";	//云工厂管理员登录后赋值工厂名
    public static String staticAgencyUserNameString="";	//经销商登录后赋值用户名
    
	//构造函数
    public User(int id, String userName,String password, String name, String contact, UserRole role,String factoryName,String factoryDescription) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.contact = contact;
        this.role = role;
        this.factoryName = factoryName;
        this.factoryDescription = factoryDescription;
    }

    //构造空参函数
    public User() {
		// TODO Auto-generated constructor stub
	}

    //get和set方法
	public int getId() {
        return id;
    }

    public void setId(int id) {
		this.id = id;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public String getUserName() {
        return userName;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public UserRole getRole() {
        return role;
    }

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFactoryName() {
		return factoryName;
	}

	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}

	public String getFactoryDescription() {
		return factoryDescription;
	}

	public void setFactoryDescription(String factoryDescription) {
		this.factoryDescription = factoryDescription;
	}
}
