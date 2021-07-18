package com.alzhahir.cscapp;

public class BookingManager {
    private String customerNRIC;
    private String customerName;
    private String customerPhone;
    private String flightID;
    private String destination;
    private double totalPrice;

    BookingManager(){
        FlightManager fMan = new FlightManager();
        CustomerManager cMan = new CustomerManager();
        this.customerNRIC = cMan.getCustomerNRIC();
        this.customerName = cMan.getCustomerName();
        this.customerPhone = cMan.getCustomerPhone();
        this.flightID = fMan.getFlightID();
        this.destination = fMan.getFlightDestination();
        this.totalPrice = fMan.getTotalPrice();
    }

    BookingManager(String customerNRIC, String customerName, String customerPhone, String flightID, String destination, double totalPrice){
        this.customerNRIC = customerNRIC;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.flightID = flightID;
        this.destination = destination;
        this.totalPrice = totalPrice;
    }

    void printOutput(){
        IOManager ioMan = new IOManager("/flights.txt", "/bookings.txt");

        //build data
        String[][] buildData = new String[][]{
                new String[]{this.customerNRIC, this.customerName, this.customerPhone,this.flightID, this.destination, String.valueOf(this.totalPrice)}
        };
        ioMan.outputFlight(buildData);
    }
}
