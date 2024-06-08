package com.wangshijia;
import com.wangshijia.view.agency.OrderSubmissionView;
import com.wangshijia.view.agency.ProductView;
import com.wangshijia.view.common.LoginView;
import com.wangshijia.view.common.RegistrationView;
import com.wangshijia.view.factoryAdmin.MyDeviceView;
import com.wangshijia.view.factoryAdmin.MyOrderView;
import com.wangshijia.view.factoryAdmin.NewOrderView;
import com.wangshijia.view.factoryAdmin.RentDeviceView;
import com.wangshijia.controller.agency.AgencyProductController;
import com.wangshijia.controller.agency.OrderSubmissionController;
import com.wangshijia.controller.common.*;
import com.wangshijia.controller.factoryAdmin.MyDeviceController;
import com.wangshijia.controller.factoryAdmin.MyOrderController;
import com.wangshijia.controller.factoryAdmin.NewOrderController;
import com.wangshijia.controller.factoryAdmin.RentDeviceConrtoller;
import com.wangshijia.view.superAdmin.*;
import com.wangshijia.controller.superAdmin.*;

//主类，用于定义视图、控制器，把视图传给控制器进行监听，并展示Login界面
public class Main {
    public static void main(String[] args) {
        
        // 创建视图对象
        LoginView loginView = LoginView.getInstance();
        UserManagementView userManagementView = UserManagementView.getInstance();
        FactoryManagementView factoryManagementView = FactoryManagementView.getInstance();
        ProductManagementView productManagementView = ProductManagementView.getInstance();
        ProductCateManagementView productCateManagementView = ProductCateManagementView.getInstance();
        CapacityFocusManagementView capacityFocusManagementView = CapacityFocusManagementView.getInstance();
        CapacityFocusCateManagementView capacityFocusCateManagementView = CapacityFocusCateManagementView.getInstance();
        RegistrationView registrationView = RegistrationView.getInstance();
        MyDeviceView myDeviceView = MyDeviceView.getInstance();
        OrderSubmissionView orderSubmissionView = OrderSubmissionView.getInstance();
        ProductView productView = ProductView.getInstance();
        MyOrderView myOrderView = MyOrderView.getInstance();
        NewOrderView newOrderView = NewOrderView.getInstance();
        RentDeviceView rentDeviceView = RentDeviceView.getInstance();
        
        // 创建控制器对象
        LoginController logonController = new LoginController(loginView);
        UserController userController = new UserController(userManagementView);
        FactoryController factoryController = new FactoryController(factoryManagementView);
        ProductController productController = new ProductController(productManagementView);
        ProductCateController productCateController = new ProductCateController(productCateManagementView);
        CapacityFocusController CapacityFocusController = new CapacityFocusController(capacityFocusManagementView);
        CapacityFocusCateController CapacityFocusCateController = new CapacityFocusCateController(capacityFocusCateManagementView);
        RegisterController registerController = new RegisterController(registrationView);
        MyDeviceController myDeviceController = new MyDeviceController(myDeviceView);
        OrderSubmissionController orderSubmissionController = new OrderSubmissionController(orderSubmissionView);
        AgencyProductController agencyProductController = new AgencyProductController(productView);
        MyOrderController myOrderController = new MyOrderController(myOrderView);
        NewOrderController newOrderController = new NewOrderController(newOrderView);  
        RentDeviceConrtoller rentDeviceConrtoller = new RentDeviceConrtoller(rentDeviceView);
        
        // 设置视图的可见性
        loginView.setVisible(true);

    }
}
