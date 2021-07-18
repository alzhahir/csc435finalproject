package com.alzhahir.cscapp;

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

    void outputData(String[] CustomerNRIC, String[] flightNumber){
        try {
            PrintWriter fileWriter = new PrintWriter(outputFile);

            for(int i = 0; i < flightNumber.length; i++){
                fileWriter.println(CustomerNRIC[i]+flightNumber[i]);
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
            ArrayList<String> flightNumber = new ArrayList<>();
            ArrayList<String> flightDate = new ArrayList<>();
            ArrayList<String> flightTime = new ArrayList<>();
            while(fileReader.hasNextLine()){
                data = fileReader.nextLine();
                System.out.println(data);
                StringTokenizer parseData = new StringTokenizer(data, ";");
                String fn = parseData.nextToken();
                String fd = parseData.nextToken();
                String ft = parseData.nextToken();
                flightNumber.add(fn);
                flightDate.add(fd);
                flightTime.add(ft);
            }
            buildList.add(flightNumber);
            buildList.add(flightDate);
            buildList.add(flightTime);
            return buildList;
        } catch (Exception error){
            error.printStackTrace();
            System.exit(1);
            return null;
        }
    }
}
