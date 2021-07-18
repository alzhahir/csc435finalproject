package com.alzhahir.cscapp;

import javax.swing.*;
import java.awt.*;

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
    private JTextField textField1;
    private JButton cancelButton;
    private JButton submitButton;

    public MainFrame(){
    }

    public static void main(String[] Args){
        JFrame mainWindow = new JFrame("9Air Ticket Management System");
        mainWindow.setContentPane(new MainFrame().MainPanel);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setSize(800,600);
        mainWindow.pack();
        mainWindow.setSize(900,600);
        mainWindow.setLocationRelativeTo(null);
        System.out.println(mainWindow.getSize());
        mainWindow.setMinimumSize(mainWindow.getSize());
        mainWindow.setVisible(true);
    }
}
