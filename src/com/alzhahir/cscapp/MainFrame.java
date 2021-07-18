package com.alzhahir.cscapp;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.net.http.HttpConnectTimeoutException;
import java.util.ArrayList;

public class MainFrame {
    private JFrame MainWindow;
    private JTabbedPane mainPane;
    private JPanel homeTab;
    private JPanel MainPanel;
    private JLabel welcomeText;
    private JPanel orderTab;
    private JTextField custName;
    private JTextField custPhone;
    private JTextField custNRIC;
    private JComboBox availableFlights;
    private JTable flightTable;
    private JButton cancelButton;
    private JButton submitButton;

    public MainFrame(){
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    throw new HttpConnectTimeoutException(null);
                } catch (Exception error){
                    error.printStackTrace();
                    JOptionPane.showMessageDialog(null, error.toString(), "Error", JOptionPane.ERROR_MESSAGE);
                    System.exit(0);
                }
            }
        });
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
    }

    public static void main(String[] Args){
        JFrame mainWindow = new JFrame("9Air Ticket Management System");
        mainWindow.setContentPane(new MainFrame().MainPanel);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.pack();
        mainWindow.setSize(1000,600);
        mainWindow.setMinimumSize(mainWindow.getSize());
        mainWindow.setLocationRelativeTo(null);
        System.out.println(mainWindow.getMinimumSize());
        mainWindow.setMinimumSize(mainWindow.getSize());
        mainWindow.setResizable(false);
        mainWindow.setVisible(true);
    }

    JTable createTable(){
        IOManager io = new IOManager("/flights.txt", "/bookings.txt");
        String[] column = {"Number", "Flight Number", "Flight Date", "Flight Time", "Destination", "Price"};
        ArrayList<ArrayList> flightImportData = io.inputData();
        ArrayList flightID = flightImportData.get(0);
        ArrayList flightDate = flightImportData.get(1);
        ArrayList flightTime = flightImportData.get(2);
        ArrayList flightDestination = flightImportData.get(3);
        ArrayList flightPrice = flightImportData.get(4);
        Object[][] tableData = new String[flightID.size()][column.length];
        for(int i = 0; i < flightID.size(); i++){
            String n = String.valueOf(i+1);
            tableData[i][0] = n;
            tableData[i][1] = flightID.get(i);
            tableData[i][2] = flightDate.get(i);
            tableData[i][3] = flightTime.get(i);
            tableData[i][4] = flightDestination.get(i);
            tableData[i][5] = flightPrice.get(i);
        }
        return new JTable(tableData, column);
    }

    JComboBox createCombo(){
        IOManager io = new IOManager("/flights.txt", "/bookings.txt");
        ArrayList<ArrayList> flightImportData = io.inputData();
        ArrayList<String> flightID = flightImportData.get(0);
        String[] comboData = new String[flightID.size()];
        for(int i = 0; i < flightID.size(); i++){
            comboData[i] = flightID.get(i);
        }
        return new JComboBox(comboData);
    }

    void registerCustomer(){
        CustomerManager csMan = new CustomerManager(this.custNRIC.toString(), this.custName.toString(), this.custPhone.toString());
    }

    void registerFlight(){
        FlightManager flMan = new FlightManager(availableFlights.getSelectedIndex());
    }

    private void createUIComponents() {
        flightTable = createTable();
        availableFlights = createCombo();
    }
}
