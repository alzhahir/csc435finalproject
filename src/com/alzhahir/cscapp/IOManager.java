package com.alzhahir.cscapp;

import javax.swing.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.util.Scanner;
import java.io.*;

public class IOManager {
    private String workingDir = System.getenv("userprofile")+"/Documents/9AirTicketManagement";
    private String outputFilename = "/output.txt";
    private String inputFilename = "/input.txt";
    private File inputFile = new File(workingDir+inputFilename);
    private File outputFile = new File(workingDir+outputFilename);

    IOManager(){
        try{
            File config = new File("./config.txt");
            Scanner scanConfig = new Scanner(config);
            String data;
            while(scanConfig.hasNextLine()){
                data = scanConfig.nextLine();
                StringTokenizer parseData = new StringTokenizer(data, ";");
                this.workingDir = System.getenv("userprofile")+parseData.nextToken();
            }
            scanConfig.close();
        } catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage(), "Fatal Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    IOManager(String inputFilename, String outputFilename){
        try {
            File config = new File("./config.txt");
            Scanner scanConfig = new Scanner(config);
            String data;
            while(scanConfig.hasNextLine()){
                data = scanConfig.nextLine();
                StringTokenizer parseData = new StringTokenizer(data, ";");
                this.workingDir = System.getenv("userprofile")+parseData.nextToken();
            }
            scanConfig.close();
            this.inputFilename = inputFilename;
            this.outputFilename = outputFilename;
            inputFile = new File(this.workingDir+this.inputFilename);
            outputFile = new File(this.workingDir+this.outputFilename);
        } catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage(), "Fatal Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    IOManager(String workingDir, String inputFilename, String outputFilename){
        try {
            this.workingDir = workingDir;
            this.inputFilename = inputFilename;
            this.outputFilename = outputFilename;
            inputFile = new File(this.workingDir+this.inputFilename);
            outputFile = new File(this.workingDir+this.outputFilename);
        } catch (Exception error){
            error.printStackTrace();
        }
    }

    void outputFlight(String[][] rawData){
        try {
            PrintWriter fileWriter = new PrintWriter(new BufferedWriter(new FileWriter(outputFile, true)));;

            int n = 0;
            StringBuilder assembledData;
            while (n < rawData.length){
                assembledData = new StringBuilder(rawData[n][0]);
                for(int i = 1; i < rawData[n].length; i++){
                    assembledData.append(";").append(rawData[n][i]);
                }
                System.out.println(assembledData);
                fileWriter.println(assembledData);
                n++;
            }
            fileWriter.close();
        } catch (Exception error){
            error.printStackTrace();
            JOptionPane.showMessageDialog(null, error.getLocalizedMessage(), "Fatal Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    String[][] queryBookings(String customerNRIC){
        try{
            Scanner fileReader = new Scanner(inputFile);

            String[] queryData;
            String[][] outputQuery;
            String bookCustomerNRIC = "0";
            String bookCustomerName = null;
            String bookCustomerPhone = null;
            String bookFlightID = null;
            String bookFlightDestination = null;
            String bookTotalPrice = null;
            String data;
            int foundData = 0;
            int totalLines = 0;
            int currentLine = 0;

            while(fileReader.hasNextLine()){
                totalLines++; //determine total lines
                System.out.println("Currently on line "+totalLines);
                fileReader.nextLine();
            }

            fileReader.close();
            fileReader = new Scanner(inputFile);
            outputQuery = new String[totalLines][];

            while(fileReader.hasNextLine()){
                System.out.println("Searching...");
                data = fileReader.nextLine();
                StringTokenizer parseData = new StringTokenizer(data, ";");
                bookCustomerNRIC = parseData.nextToken();
                if(bookCustomerNRIC.equals(customerNRIC)){
                    bookCustomerName = parseData.nextToken();
                    bookCustomerPhone = parseData.nextToken();
                    bookFlightID = parseData.nextToken();
                    bookFlightDestination = parseData.nextToken();
                    bookTotalPrice = parseData.nextToken();
                    queryData = new String[]{bookCustomerNRIC, bookCustomerName, bookCustomerPhone, bookFlightID, bookFlightDestination, bookTotalPrice};
                    outputQuery[currentLine] = queryData;
                    System.out.println("Found data at line "+currentLine);
                    foundData++;
                    currentLine++;
                } else {
                    //do nothing
                }
            }
            fileReader.close();

            if (foundData < 1){
                JOptionPane.showMessageDialog(null, "No bookings made by "+customerNRIC+" found.", "Error", JOptionPane.ERROR_MESSAGE);
                return null;
            } else {
                return outputQuery;
            }
        } catch (FileNotFoundException notFoundException){
            notFoundException.printStackTrace();
            JOptionPane.showMessageDialog(null, "Please make sure that the booking file exists.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        } catch (NoSuchElementException noBooking){
            noBooking.printStackTrace();
            JOptionPane.showMessageDialog(null, "Bookings file is empty. Please create a new booking first.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        } catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage(), "Fatal Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
            return null;
        }
    }

    ArrayList<ArrayList> inputData(){
        try{
            Scanner fileReader = new Scanner(inputFile);

            String data;
            ArrayList<ArrayList> buildList = new ArrayList<>();
            ArrayList<String> flightID = new ArrayList<>();
            ArrayList<String> flightDate = new ArrayList<>();
            ArrayList<String> flightTime = new ArrayList<>();
            ArrayList<String> flightDestination = new ArrayList<>();
            ArrayList<String> flightPrice = new ArrayList<>();
            while(fileReader.hasNextLine()){
                data = fileReader.nextLine();
                StringTokenizer parseData = new StringTokenizer(data, ";");
                String fn = parseData.nextToken();
                String fd = parseData.nextToken();
                String ft = parseData.nextToken();
                String fde = parseData.nextToken();
                String fp = parseData.nextToken();
                flightID.add(fn);
                flightDate.add(fd);
                flightTime.add(ft);
                flightDestination.add(fde);
                flightPrice.add(fp);
            }
            buildList.add(flightID);
            buildList.add(flightDate);
            buildList.add(flightTime);
            buildList.add(flightDestination);
            buildList.add(flightPrice);
            return buildList;
        } catch (Exception error){
            error.printStackTrace();
            JOptionPane.showMessageDialog(null, error.getLocalizedMessage(), "Fatal Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
            return null;
        }
    }
}
