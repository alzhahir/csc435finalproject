package com.alzhahir.cscapp;

import com.sun.tools.javac.Main;

public class CustomerManager {
    private String customerNRIC;
    private String customerName;
    private String customerPhone;

    CustomerManager(){
        MainFrame mf = new MainFrame();
        String[] customerInfo = mf.getCustomerForm();
        System.out.println(customerInfo[0]+customerInfo[1]+customerInfo[2]);
        this.customerNRIC = customerInfo[0];
        this.customerName = customerInfo[1];
        this.customerPhone = customerInfo[2];
    }
    CustomerManager(String customerNRIC, String customerName, String customerPhone){
        this.customerNRIC = customerNRIC;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
    }

    String getCustomerNRIC(){
        return customerNRIC;
    }

    String getCustomerName(){
        return customerName;
    }

    String getCustomerPhone(){
        return customerPhone;
    }
}
