package com.alzhahir.cscapp;

public class CustomerManager {
    private String customerNRIC;
    private String customerName;
    private String customerPhone;

    CustomerManager(String customerNRIC, String customerName, String customerPhone){
        this.customerNRIC = customerNRIC;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
    }

    public static void main(String[] Args){
        //
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
