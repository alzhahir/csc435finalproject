package com.alzhahir.cscapp;

import javax.swing.*;

public class MainFrame {
    private JFrame MainWindow;
    private JTabbedPane mainPane;
    private JPanel homeTab;
    private JPanel MainPanel;
    private JLabel welcomeText;
    private JPanel orderTab;

    public MainFrame(){
    }

    public static void main(String[] Args){
        JFrame mainWindow = new JFrame("9Air Ticket Management System");
        mainWindow.setContentPane(new MainFrame().MainPanel);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setSize(800,600);
        mainWindow.pack();
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setVisible(true);
    }
}
