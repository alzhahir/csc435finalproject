package com.alzhahir.cscapp;

/*
*   9Air Ticket Management System
*
*   Copyright (C) Megat Al Zhahir Daniel, Muhammad Adib, Mohamad Izzat
*
*   All rights reserved.
*
*   You may not copy this code into your assignments or educational projects,
*   but you can refer to the code in case you need help.
*/

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JPanel aboutTab;
    private JPanel reprintTab;
    private JTextField searchNRIC;
    private JButton cancelSearchButton;
    private JButton searchTicketButton;
    private JComboBox searchCombo;
    private JButton reprintTicketButton;
    private JLabel totalPriceLabel;
    private JButton calcPrice;
    private String setCustName;
    private String setCustPhone;
    private  String setCustNRIC;

    public MainFrame(){
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int a = JOptionPane.showConfirmDialog(null,"Are you sure you want to cancel? Any unsaved changes will be lost.", "Confirm Cancel", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if(a == JOptionPane.YES_OPTION){
                        custName.setText(null);
                        custNRIC.setText(null);
                        custPhone.setText(null);
                        availableFlights.setSelectedIndex(0);
                        mainPane.setSelectedIndex(0);
                        totalPriceLabel.setText("RM 0.00");
                    }
                } catch (Exception error){
                    error.printStackTrace();
                    JOptionPane.showMessageDialog(null, error.toString(), "Error", JOptionPane.ERROR_MESSAGE);
                    System.exit(1);
                }
            }
        });
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(custNRIC.getText().isEmpty()){
                    System.out.println("It's empty!!!!!");
                    JOptionPane.showMessageDialog(null, "NRIC is a required field.", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if(custName.getText().isEmpty()){
                    System.out.println("It's empty!!!!!");
                    JOptionPane.showMessageDialog(null, "Customer Name is a required field.", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if(custPhone.getText().isEmpty()){
                    System.out.println("It's empty!!!!!");
                    JOptionPane.showMessageDialog(null, "Phone number is a required field.", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                setCustNRIC = custNRIC.getText();
                setCustName = custName.getText();
                setCustPhone = custPhone.getText();
                CustomerManager csMan = new CustomerManager(setCustNRIC, setCustName,setCustPhone);
                FlightManager flMan = new FlightManager(availableFlights.getSelectedIndex());
                BookingManager boMan = new BookingManager(csMan.getCustomerNRIC(), csMan.getCustomerName(), csMan.getCustomerPhone(), flMan.getFlightID(), flMan.getFlightDestination(), flMan.getTotalPrice());

                boMan.printOutput();

                JOptionPane.showMessageDialog(null, "Customer booking has been saved successfully.", "Booking Success", JOptionPane.INFORMATION_MESSAGE);

                boMan.ticketOutput();

                JOptionPane.showMessageDialog(null, "Ticket printed.", "Ticket Printed", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        searchTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(searchNRIC.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Please enter an NRIC number.", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                IOManager ioMan = new IOManager("/bookings.txt", "/bookings.txt");
                String[][] chosenCustomer = ioMan.queryBookings(searchNRIC.getText());
                if(chosenCustomer == null){
                    return;
                }

                JOptionPane.showMessageDialog(null,"Tickets found for Customer "+searchNRIC.getText()+".", "Search Done", JOptionPane.INFORMATION_MESSAGE);

                searchCombo.removeAllItems();
                for (String[] strings : chosenCustomer) {
                    System.out.println(strings[3]);
                    searchCombo.setEnabled(true);
                    searchCombo.addItem(strings[3]);
                }
            }
        });
        reprintTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!searchCombo.isEnabled()||searchCombo == null){
                    JOptionPane.showMessageDialog(null, "No flights selected.", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                IOManager ioMan = new IOManager("/bookings.txt", "/ticket.txt");
                String[][] chosenCustomer = ioMan.queryBookings(searchNRIC.getText());
                String[] chosenFlight = chosenCustomer[searchCombo.getSelectedIndex()];
                BookingManager boMan = new BookingManager(chosenFlight[0], chosenFlight[1], chosenFlight[2], chosenFlight[3], chosenFlight[4], Double.parseDouble(chosenFlight[5]));
                boMan.ticketOutput();
                JOptionPane.showMessageDialog(null,"Successfully printed tickets for chosen flight "+chosenFlight[3]+".", "Reprint Succeeded", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        cancelSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int a = JOptionPane.showConfirmDialog(null,"Are you sure you want to cancel? Any changes will be lost.", "Confirm Canel", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if(a == JOptionPane.YES_OPTION){
                        searchNRIC.setText(null);
                        searchCombo.removeAllItems();
                        mainPane.setSelectedIndex(0);
                    }
                } catch (Exception error){
                    error.printStackTrace();
                    JOptionPane.showMessageDialog(null, error.toString(), "Error", JOptionPane.ERROR_MESSAGE);
                    System.exit(1);
                }
            }
        });
        calcPrice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double priceTotal = 0;
                FlightManager flMan = new FlightManager(availableFlights.getSelectedIndex());
                priceTotal = flMan.calculateTotalPrice();
                totalPriceLabel.setText("RM "+priceTotal);
            }
        });
    }

    public static void main(String[] Args){
        try{
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
        } catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage(), "Fatal Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    JTable createTable(){
        try{
            FlightManager fMan = new FlightManager();
            return new JTable(fMan.getAllFlightInfo(), fMan.getColumnInfo());
        } catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage(), "Fatal Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
            return null;
        }
    }

    JComboBox createCombo(){
        try{
            IOManager io = new IOManager("/flights.txt", "/bookings.txt");
            ArrayList<ArrayList> flightImportData = io.inputData();
            ArrayList<String> flightID = flightImportData.get(0);
            String[] comboData = new String[flightID.size()];
            for(int i = 0; i < flightID.size(); i++){
                comboData[i] = flightID.get(i);
            }
            return new JComboBox(comboData);
        } catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage(), "Fatal Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
            return null;
        }
    }

    void registerCustomer(){
        CustomerManager csMan = new CustomerManager(this.custNRIC.toString(), this.custName.toString(), this.custPhone.toString());
    }

    void registerFlight(){
        FlightManager flMan = new FlightManager(availableFlights.getSelectedIndex());
    }

    String[] getCustomerForm(){
        String[] customerInfo = new String[3];
        customerInfo[0] = setCustNRIC;
        customerInfo[1] = setCustName;
        customerInfo[2] = setCustPhone;

        return customerInfo;
    }

    private void createUIComponents() {
        flightTable = createTable();
        availableFlights = createCombo();
    }
}
