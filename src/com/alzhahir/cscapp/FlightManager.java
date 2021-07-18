package com.alzhahir.cscapp;

import java.util.ArrayList;

public class FlightManager {
    private String flightID;
    private String flightDate;
    private String flightTime;
    private String flightDestination;
    private String flightPrice;
    private double totalPrice;
    private String[] chosenFlight;
    private String[] column = {"Number","Flight Number", "Flight Date", "Flight Time", "Destination", "Price"};

    FlightManager(){
    }

    FlightManager(int flightIndex){
        this.chosenFlight = new String[6];
        this.chosenFlight = getFlightInfo(flightIndex);
        System.out.println("Setting Flight...");
        setFlightInfo(this.chosenFlight);
        this.totalPrice = calculateTotalPrice();
        System.out.println("Done Setting Flight.");
    }

    FlightManager(String flightID, String flightDate, String flightTime, String flightDestination, String flightPrice){
        this.flightID = flightID;
        this.flightDate = flightDate;
        this.flightTime = flightTime;
        this.flightDestination = flightDestination;
        this.flightPrice = flightPrice;
    }

    String[][] getAllFlightInfo(){
        IOManager io = new IOManager("/flights.txt", "/bookings.txt");
        ArrayList<ArrayList> flightImportData = io.inputData();
        ArrayList flightID = flightImportData.get(0);
        ArrayList flightDate = flightImportData.get(1);
        ArrayList flightTime = flightImportData.get(2);
        ArrayList flightDestination = flightImportData.get(3);
        ArrayList flightPrice = flightImportData.get(4);
        String[][] flightData = new String[flightID.size()][column.length];
        for(int i = 0; i < flightID.size(); i++){
            flightData[i][0] = String.valueOf(i+1);
            flightData[i][1] = String.valueOf(flightID.get(i));
            flightData[i][2] = String.valueOf(flightDate.get(i));
            flightData[i][3] = String.valueOf(flightTime.get(i));
            flightData[i][4] = String.valueOf(flightDestination.get(i));
            flightData[i][5] = String.valueOf(flightPrice.get(i));
        }
        return flightData;
    }

    String[] getFlightInfo(int flightIndex){
        String[][] allFlights = getAllFlightInfo();
        String[] chosenFlight = new String[column.length];
        for(int i = 0; i < column.length; i++){
            chosenFlight[i] = allFlights[flightIndex][i].toString();
        }
        return chosenFlight;
    }

    String[] getColumnInfo(){
        return column;
    }
    
    void setFlightInfo(String[] chosenFlight){
        this.flightID = chosenFlight[1];
        this.flightDate = chosenFlight[2];
        this.flightTime = chosenFlight[3];
        this.flightDestination = chosenFlight[4];
        this.flightPrice = chosenFlight[5];
        System.out.println(this.flightID);
        System.out.println(this.flightDate);
        System.out.println(this.flightTime);
        System.out.println(this.flightDestination);
        System.out.println(this.flightPrice);
    }

    String getFlightID(){
        return flightID;
    }

    String getFlightDate(){
        return flightDate;
    }

    String getFlightTime(){
        return flightTime;
    }

    String getFlightDestination(){
        return flightDestination;
    }

    double getFlightPrice(){
        return Double.parseDouble(this.flightPrice);
    }

    double getTotalPrice(){
        return totalPrice;
    }

    double calculateTotalPrice(){
        System.out.println(this.flightPrice);
        double flightPriceParsed = Double.parseDouble(this.flightPrice);
        double tax = 0.06;
        return flightPriceParsed + flightPriceParsed * tax;
    }
}
