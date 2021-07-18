package com.alzhahir.cscapp;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Scanner;
import java.io.*;

public class IOManager {
    private String workingDir = System.getenv("userprofile")+"/Documents/alzhahir/cscapp";
    private String outputFilename = "/output.txt";
    private String inputFilename = "/input.txt";
    private File inputFile = new File(workingDir+inputFilename);
    private File outputFile = new File(workingDir+outputFilename);

    IOManager(boolean isInput, String workingDir, String filename){
        try {
            if(isInput){
                this.workingDir = workingDir;
                this.inputFilename = filename;
                inputFile = new File(workingDir+inputFilename);
            } else{
                this.workingDir = workingDir;
                this.outputFilename = filename;
                outputFile = new File(workingDir+outputFilename);
            }
        } catch (Exception error){
            error.printStackTrace();
        }
    }

    public static void main(String[] Args){
        try {
            String workingDir = System.getenv("userprofile")+"/Documents/alzhahir/cscapp";
            String inputFilename = "/input.txt";
            String outputFilename = "/output.txt";

            File inputFile = new File(workingDir+inputFilename);
            File outputFile = new File(workingDir+outputFilename);
            PrintWriter fileWriter = new PrintWriter(outputFile);
            Scanner fileReader = new Scanner(inputFile);

            String data;
            while(fileReader.hasNext()){
                data = fileReader.nextLine();
                StringTokenizer parseData = new StringTokenizer(data, ";");
            }
        } catch (Exception error){
            error.printStackTrace();
            System.exit(1);
        }
    }

    void outputData(String[] flightNumber, String[] flightDate, String[] flightTime){
        try {
            PrintWriter fileWriter = new PrintWriter(outputFile);

            for(int i = 0; i < flightNumber.length; i++){
                fileWriter.println(flightNumber[i]+flightDate[i]+flightTime[i]);
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
            int i = 0;
            ArrayList<ArrayList> buildList = new ArrayList<>();
            ArrayList<String> flightNumber = new ArrayList<>();
            ArrayList<String> flightDate = new ArrayList<>();
            ArrayList<String> flightTime = new ArrayList<>();
            while(fileReader.hasNext()){
                data = fileReader.nextLine();
                StringTokenizer parseData = new StringTokenizer(data, ";");
                flightNumber.add(parseData.nextToken());
                flightDate.add(parseData.nextToken());
                flightTime.add(parseData.nextToken());
                i++;
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
