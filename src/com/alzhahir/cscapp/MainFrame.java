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
        ArrayList<ArrayList> flightImportData = io.inputData();
        ArrayList flightNumber = flightImportData.get(0);
        ArrayList flightDate = flightImportData.get(1);
        ArrayList flightTime = flightImportData.get(2);
        Object[][] tableData = new String[flightNumber.size()][3];
        for(int i = 0; i < flightNumber.size(); i++){
            tableData[i][0] = flightNumber.get(i);
            tableData[i][1] = flightDate.get(i);
            tableData[i][2] = flightTime.get(i);
        }
        String[] column = {"Flight Number", "Flight Date", "Flight Time"};
        return new JTable(tableData, column);
    }

    JComboBox createCombo(){
        IOManager io = new IOManager("/flights.txt", "/bookings.txt");
        ArrayList<ArrayList> flightImportData = io.inputData();
        ArrayList<String> flightNumber = flightImportData.get(0);
        String[] comboData = new String[flightNumber.size()];
        for(int i = 0; i < flightNumber.size(); i++){
            comboData[i] = flightNumber.get(i);
        }
        return new JComboBox(comboData);
    }

    private void createUIComponents() {
        flightTable = createTable();
        availableFlights = createCombo();
    }
}
