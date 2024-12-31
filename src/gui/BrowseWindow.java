package gui;

import api.Application_Controller;
import api.Data;
import api.Product;

import javax.swing.*;
import javax.swing.plaf.basic.BasicOptionPaneUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BrowseWindow {
    private JTable productsTable;

    private Data data;

    private JPanel greenCenter;

    public BrowseWindow(Data data){
        this.data = data;
        buildUI();



    }

    private void buildUI(){
        BasicFrame browseWindow = new BasicFrame(new BorderLayout(3,3));

        //PANELS
        {
        JPanel redRight = new JPanel();{
        redRight.setPreferredSize(new Dimension(280,0));
        redRight.setBackground(Color.red);
        browseWindow.add(redRight,BorderLayout.EAST);}

        JPanel blueLeft = new JPanel();{
        blueLeft.setPreferredSize(new Dimension(280,0));
        blueLeft.setBackground(Color.blue);
        browseWindow.add(blueLeft, BorderLayout.WEST);}

        JPanel yellowSouth = new JPanel();{
        yellowSouth.setPreferredSize(new Dimension(0,80));
        yellowSouth.setBackground(Color.yellow);
        browseWindow.add(yellowSouth,BorderLayout.SOUTH);}

        greenCenter = new JPanel(new GridLayout(0,4,5,5));{
            for (int i = 0; i < data.getProductsList().size(); i++){
                JButton productButton = new JButton(data.getProductsList().get(i).getTitle());
                productButton.setActionCommand(data.getProductsList().get(i).getTitle());
                productButton.setFont(new Font("Courier New", Font.PLAIN, 15));
                productButton.setPreferredSize(new Dimension(155,111));
                productButton.addActionListener(new ButtonClickListener());
                greenCenter.add(productButton);
            }
            JScrollPane scroll = new JScrollPane(greenCenter);
            browseWindow.add(scroll,BorderLayout.CENTER);
        }

        JPanel magentaNorth = new JPanel();{
            magentaNorth.setPreferredSize(new Dimension(0,80));
            magentaNorth.setBackground(Color.magenta);
            browseWindow.add(magentaNorth,BorderLayout.NORTH);}
        }



        productsTable = new JTable();
    }

    static class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            System.out.println("Button Pressed: " + command);

        }
    }

    public static void main(String[] args) {
        new BrowseWindow(new Data());
    }
}
