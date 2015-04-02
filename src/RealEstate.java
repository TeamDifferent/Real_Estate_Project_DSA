/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.io.*;
import houseDetails.*;

public class RealEstate {
 
private static CommonList.SortedList list = new CommonList.SortedList();

private static JTextField txt_lot; 
private static JTextField txt_first; 
private static JTextField txt_last; 
private static JTextField txt_price; 
private static JTextField txt_feet; 
private static JTextField txt_bed; 

private static JLabel lbl_show;

private static void showHouse(ListHouse house) {
    txt_lot.setText(Integer.toString(house.lotNumber()));
    txt_first.setText(house.firstName());
    txt_last.setText(house.lastName());
    txt_price.setText(Integer.toString(house.price()));
    txt_feet.setText(Integer.toString(house.squareFeet()));
    txt_bed.setText(Integer.toString(house.bedRooms()));
}

private static ListHouse getHouse() {
    String lastName;
    String firstName;
    int lotNumber;
    int price;
    int squareFeet;
    int bedRooms;
    lotNumber = Integer.parseInt(txt_lot.getText());
    firstName = txt_first.getText();
    lastName = txt_last.getText();
    price = Integer.parseInt(txt_price.getText());
    squareFeet = Integer.parseInt(txt_feet.getText());
    bedRooms = Integer.parseInt(txt_bed.getText());
    ListHouse house = new ListHouse(lastName, firstName, lotNumber, price,
    squareFeet, bedRooms);
    return house;
}

private static void clearHouse() {
    txt_lot.setText("");
    txt_first.setText("");
    txt_last.setText("");
    txt_price.setText("");
    txt_feet.setText("");
    txt_bed.setText("");
}

private static class ActionHandler implements ActionListener{
    public void actionPerformed(ActionEvent event) {
        ListHouse house;
        if (event.getActionCommand().equals("Reset")) { 
            list.reset();
            if (list.lengthIs() == 0)
            clearHouse();
            else {
                house = (ListHouse)list.getNextItem();
                showHouse(house);
            }
            lbl_show.setText("List reset");
            clearHouse();
        }
        else
            if (event.getActionCommand().equals("Next")) { 
                if (list.lengthIs() == 0){
                    lbl_show.setForeground(Color.red);
                    lbl_show.setText("list is empty");
                }
                else {
                    house = (ListHouse)list.getNextItem();
                    showHouse(house);
                    lbl_show.setForeground(Color.blue);
                    lbl_show.setText("Next house displayed");
                }
            }
            else
                if (event.getActionCommand().equals("Add")) {
                try {
                        house = getHouse();
                        if (list.isThere(house)) {
                            lbl_show.setText("Lot number already exists");
                            lbl_show.setForeground(Color.red);
                        }
                        else {
                            lbl_show.setForeground(Color.blue);
                            list.insert(house);
                            lbl_show.setText("Successfully added");
                        }
                    }
                    catch (NumberFormatException badHouseData) {
                        lbl_show.setForeground(Color.red);
                        lbl_show.setText("Data not in a correct format");
                    }
                }
        else
            if (event.getActionCommand().equals("Delete")) {
                try {
                    house = getHouse();
                    if (list.isThere(house)) {
                        list.delete(house);
                        lbl_show.setForeground(Color.black);
                        lbl_show.setText("House successfully deleted");
                    }
                    else {
                        lbl_show.setForeground(Color.red);
                        lbl_show.setText("Lot number not on list");
                    }
                }
                catch (NumberFormatException badHouseData) {
                    lbl_show.setForeground(Color.red);
                    lbl_show.setText("Datas not in a correct format");
                }
            }
        else
            if (event.getActionCommand().equals("Clear")) { 
                clearHouse();
                lbl_show.setForeground(Color.black);
                lbl_show.setText(list.lengthIs() + " Houses on the list");
            }
        else
            if (event.getActionCommand().equals("Find")) { 
                int lotNumber;
                try {
                    lotNumber = Integer.parseInt(txt_lot.getText());
                    house = new ListHouse("", "", lotNumber, 0, 0, 0);
                    if (list.isThere(house)) {
                        house = (ListHouse)list.retrieve(house);
                        showHouse(house);
                        lbl_show.setForeground(Color.red);
                        lbl_show.setText("House founded");
                    }
                    else{
                        lbl_show.setForeground(Color.red);
                        lbl_show.setText("House not found");
                    }
                }
                catch (NumberFormatException badHouseData) {
                    lbl_show.setForeground(Color.red);
                    lbl_show.setText(" Data not in a correct format");
                }
            }
}
}
    public static void main(String args[]) throws IOException {
        ListHouse house;
        char command;
        int length;
        JLabel blankLabel; 
        JLabel lotLabel; 
        JLabel firstLabel;
        JLabel lastLabel;
        JLabel priceLabel;
        JLabel feetLabel;
        JLabel bedLabel;

        JButton reset;
        JButton next; 
        JButton add; 
        JButton delete; 
        JButton clear; 
        JButton find; 
        ActionHandler action; 

        JFrame displayFrame = new JFrame();
        displayFrame.setTitle("Real Estate Program");
        displayFrame.setSize(450,500);
        displayFrame.setResizable(false);
        displayFrame.setLocationRelativeTo(null);

        displayFrame.addWindowListener(new WindowAdapter() {   

            public void windowClosing(WindowEvent event) {
                JFrame displayFrame = new JFrame();
                ListHouse house;
                displayFrame.dispose(); 

                try {
                    //To store info from house file
                    HouseFile.rewrite();
                    list.reset();
                    int length = list.lengthIs();
                    for (int counter = 1; counter <= length; counter++) {
                        house = (ListHouse)list.getNextItem();
                        HouseFile.putToFile(house);
                    }
                    HouseFile.close();
                }
                catch (IOException fileCloseProblem) {
                    System.out.println("Exception raised concerning the house info file "
                    + "upon program termination");
                }
                System.exit(0); 
                
            }
        });

        Container contentPane = displayFrame.getContentPane();
        JPanel infoPanel = new JPanel();

        lbl_show= new JLabel("", JLabel.LEFT);
        lbl_show.setBorder(new LineBorder(Color.black));
        blankLabel = new JLabel("");
        lotLabel = new JLabel("Lot Number ", JLabel.LEFT);
        txt_lot= new JTextField("", 15);
        firstLabel = new JLabel("First Name ", JLabel.LEFT);
        txt_first = new JTextField("", 15);
        lastLabel = new JLabel("Last Name ", JLabel.LEFT);
        txt_last = new JTextField("", 15);
        priceLabel = new JLabel("Price ", JLabel.LEFT);
        txt_price = new JTextField("", 15);
        feetLabel = new JLabel("Square Feet ", JLabel.LEFT);
        txt_feet = new JTextField("", 15);
        bedLabel = new JLabel("Number of Bedrooms ", JLabel.LEFT);
        txt_bed = new JTextField("", 15);
        reset = new JButton("Reset");
        next = new JButton("Next");
        add = new JButton("Add");
        delete = new JButton("Delete");
        clear = new JButton("Clear");
        find = new JButton("Find");

        action = new ActionHandler();
        reset.addActionListener(action);
        next.addActionListener(action);
        add.addActionListener(action);
        delete.addActionListener(action);
        clear.addActionListener(action);
        find.addActionListener(action);

        HouseFile.reset();

        while (HouseFile.moreHouses()) {
            house = HouseFile.getNextHouse();
            list.insert(house);
        }
        list.reset();
        if (list.lengthIs() != 0) {
            house = (ListHouse)list.getNextItem();
            showHouse(house);
        }

        lbl_show.setText(list.lengthIs() + " Houses are available");

        infoPanel.setLayout(new GridLayout(10,2,5,5));
        infoPanel.add(lbl_show);
        lbl_show.setForeground(Color.blue);
        infoPanel.add(blankLabel);
        infoPanel.add(lotLabel);
        infoPanel.add(txt_lot);
        lotLabel.setForeground(Color.blue);
        infoPanel.add(firstLabel);
        infoPanel.add(txt_first);
        firstLabel.setForeground(Color.blue);
        infoPanel.add(lastLabel);
        infoPanel.add(txt_last);
        lastLabel.setForeground(Color.blue);
        infoPanel.add(priceLabel);
        infoPanel.add(txt_price);
        priceLabel.setForeground(Color.blue);
        infoPanel.add(feetLabel);
        infoPanel.add(txt_feet);
        feetLabel.setForeground(Color.blue);
        infoPanel.add(bedLabel);
        infoPanel.add(txt_bed);
        bedLabel.setForeground(Color.blue);
        infoPanel.add(reset);
        infoPanel.add(next);
        infoPanel.add(add);
        infoPanel.add(delete);
        infoPanel.add(clear);
        infoPanel.add(find);
        contentPane.add(infoPanel);
        displayFrame.show();

    }    
}
