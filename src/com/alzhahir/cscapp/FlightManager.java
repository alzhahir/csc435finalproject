package com.alzhahir.cscapp;

import java.util.ArrayList;
import java.util.Arrays;

public class FlightManager {
    private String flightID;
    private String flightDate;
    private String flightTime;
    private String flightDestination;
    private String flightPrice;
    private String[] chosenFlight;

    FlightManager(int flightIndex){
        this.chosenFlight = new String[6];
        this.chosenFlight = getFlightInfo(flightIndex);
        setFlightInfo(this.chosenFlight);
    }

    FlightManager(String flightID, String flightDate, String flightTime, String flightDestination, String flightPrice){
        this.flightID = flightID;
        this.flightDate = flightDate;
        this.flightTime = flightTime;
        this.flightDestination = flightDestination;
        this.flightPrice = flightPrice;
    }
    
    public static void main(String[] Args){
        //main method
    }

    String[] getFlightInfo(int flightIndex){
        IOManager io = new IOManager("/flights.txt", "/bookings.txt");
        String[] column = {"Number","Flight Number", "Flight Date", "Flight Time", "Destination", "Price"};
        ArrayList<ArrayList> flightImportData = io.inputData();
        ArrayList flightID = flightImportData.get(0);
        ArrayList flightDate = flightImportData.get(1);
        ArrayList flightTime = flightImportData.get(2);
        ArrayList flightDestination = flightImportData.get(3);
        ArrayList flightPrice = flightImportData.get(4);
        Object[][] flightData = new String[flightID.size()][column.length];
        for(int i = 0; i < flightID.size(); i++){
            flightData[i][0] = String.valueOf(i);
            flightData[i][1] = flightID.get(i);
            flightData[i][2] = flightDate.get(i);
            flightData[i][3] = flightTime.get(i);
            flightData[i][4] = flightDestination.get(i);
            flightData[i][5] = flightPrice.get(i);
        }
        String[] chosenFlight = new String[column.length];
        for(int i = 0; i < column.length; i++){
            chosenFlight[i] = flightData[flightIndex][i].toString();
        }
        return chosenFlight;
    }
    
    void setFlightInfo(String[] chosenFlight){
        this.flightID = chosenFlight[1];
        this.flightDate = chosenFlight[2];
        this.flightTime = chosenFlight[3];
        this.flightDestination = chosenFlight[4];
        this.flightPrice = chosenFlight[5];
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
        return Double.parseDouble(flightPrice);
    }

    double calculateTotalPrice(){
        double flightPriceParsed = Double.parseDouble(flightPrice);
        double tax = 0.06;
        return flightPriceParsed + flightPriceParsed * tax;
    }
}
