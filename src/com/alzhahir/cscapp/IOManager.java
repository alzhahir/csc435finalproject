package com.alzhahir.cscapp;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Scanner;
import java.io.*;

public class IOManager {
    private String workingDir = System.getenv("userprofile")+"/Documents/alzhahirappio";
    private String outputFilename = "/output.txt";
    private String inputFilename = "/input.txt";
    private File inputFile = new File(workingDir+inputFilename);
    private File outputFile = new File(workingDir+outputFilename);

    IOManager(){
    }

    IOManager(String inputFilename, String outputFilename){
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

    public static void main(String[] Args){
        try {
            //
        } catch (Exception error){
            error.printStackTrace();
            System.exit(1);
        }
    }

    void outputFlight(String[] CustomerNRIC, String[] flightID){
        try {
            PrintWriter fileWriter = new PrintWriter(outputFile);

            for(int i = 0; i < flightID.length; i++){
                fileWriter.println(CustomerNRIC[i]+flightID[i]);
            }
            fileWriter.close();
        } catch (Exception error){
            error.printStackTrace();
            System.exit(1);
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
                System.out.println(data);
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
            System.exit(1);
            return null;
        }
    }
}
