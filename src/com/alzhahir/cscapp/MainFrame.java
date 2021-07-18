package com.alzhahir.cscapp;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.net.http.HttpConnectTimeoutException;

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
        //flightTable = createTable();
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    throw new HttpConnectTimeoutException(null);
                }catch (Exception error){
                    error.printStackTrace();
                    JOptionPane.showMessageDialog(null, error.toString(), "Error", 2);
                }
            }
        });
    }

    public static void main(String[] Args){
        MainFrame frame = new MainFrame();
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

    JTable createTable(){
        String[] column = {"Flight Number", "Flight Date", "Flight Time"};
        Object[][] data = {{"MH1022", "10/07/2021", "10:20"}, {"MH3022", "15/07/2021", "13:40"}};
        JTable table = new JTable(data, column);
        return table;
    }
}
