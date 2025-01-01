package gui;

import api.Data;
import api.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BrowseWindow {
    private Data data;

    private JPanel centerPanel;

    private JFrame customerWindowOptions;

    public BrowseWindow(Data data,JFrame customerWindowOptions){
        this.data = data;
        this.customerWindowOptions = customerWindowOptions;
        buildUI();



    }

    private void buildUI(){
        BasicFrame browseWindow = new BasicFrame(new BorderLayout(3,3));

        //PANELS

        JPanel redRight = new JPanel();{
        redRight.setPreferredSize(new Dimension(280,0));
        redRight.setBackground(Color.red);
        browseWindow.add(redRight,BorderLayout.EAST);}

        JPanel blueLeft = new JPanel();{
        blueLeft.setPreferredSize(new Dimension(280,0));
        blueLeft.setBackground(Color.blue);
        browseWindow.add(blueLeft, BorderLayout.WEST);}

        JPanel yellowSouth = new JPanel();
        yellowSouth.setPreferredSize(new Dimension(0,80));
        //yellowSouth.setBackground(Color.yellow); //mainBottom Panel color commented out
        //browseWindow.add(yellowSouth,BorderLayout.SOUTH); //adding it some lines down when adding the little lines first

        centerPanel = new JPanel(new GridLayout(0,4,5,5));{
            for (int i = 0; i < data.getProductsList().size(); i++){
                JButton productButton = new JButton(data.getProductsList().get(i).getTitle());
                productButton.setActionCommand(data.getProductsList().get(i).getTitle());
                productButton.setFont(new Font("Courier New", Font.PLAIN, 15));
                productButton.setPreferredSize(new Dimension(155,111));
                productButton.addActionListener(new ButtonClickListener());
                centerPanel.add(productButton);
            }
            JScrollPane scroll = new JScrollPane(centerPanel);
            browseWindow.add(scroll,BorderLayout.CENTER);
        }

        JPanel magentaNorth = new JPanel();{
            magentaNorth.setPreferredSize(new Dimension(0,80));
            magentaNorth.setBackground(Color.magenta);
            browseWindow.add(magentaNorth,BorderLayout.NORTH);}

        //Let's divide the bottom panel into three different boxes. Use a grid maybe? A box?
        GridLayout bottomPanelGrid = new GridLayout(1,3,2,0); //creates a grid layout with 3 boxes horizontally.
        yellowSouth.setLayout(bottomPanelGrid); //adds this layout as the manager of the yellow bottom panel

        //creating 3 different panels to represent our little boxes.
        JPanel pinkPanel_BottomLeft = new JPanel();
        JPanel yellowPanel_BottomMid = new JPanel();
        JPanel greenPanel_BottomRight = new JPanel(null);

        //adding colour to the 3 panels
        pinkPanel_BottomLeft.setBackground(Color.pink);
        yellowPanel_BottomMid.setBackground(Color.yellow);
        greenPanel_BottomRight.setBackground(Color.green);

        //assigning to the mainBottom panel each little panel one by one.
        yellowSouth.add(pinkPanel_BottomLeft);
        yellowSouth.add(yellowPanel_BottomMid);
        //yellowSouth.add(greenPanel_BottomRight);

        //finally, adding the mainBottom panel that holds the other 3 to the main Frame
        //browseWindow.add(yellowSouth,BorderLayout.SOUTH);

        Font buttonFont = new Font("Inconsolata",Font.BOLD,13);
        JButton backButton = new JButton("Back");
        backButton.setFont(buttonFont);
        Dimension buttonDimension = new Dimension(150,40);
        backButton.setPreferredSize(buttonDimension);
        System.out.println(greenPanel_BottomRight.getSize());;
        backButton.setBounds(230,20,150,40);
        greenPanel_BottomRight.add(backButton);

        yellowSouth.add(greenPanel_BottomRight);

        //finally, adding the mainBottom panel that holds the other 3 to the main Frame
        browseWindow.add(yellowSouth,BorderLayout.SOUTH);
        //yellowSouth.add(backButton);


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                data.DataSave();
                browseWindow.dispose();
                customerWindowOptions.setVisible(true);
            }
        });

        browseWindow.setVisible(true);

    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command_ProductTitle = e.getActionCommand();

            System.out.println("Button Pressed: " + command_ProductTitle);

            String[] options = {"View Product", "Add to Cart", "Cancel"};
            int choice = JOptionPane.showOptionDialog(null, "What would you like to do?", command_ProductTitle,
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, options, options[0]);

            if (choice >= 0){
                System.out.println("User chose: " + options[choice]+", choice= "+choice);
                if (choice == 0){
                    //view product
                    for (Product product: data.getProductsList()){
                        if (product.getTitle().equals(command_ProductTitle)){
                            new ViewProductWindow(product,data);
                        }
                    }
                } else if (choice == 1) {
                    //add product to cart
                }
            }else{
                System.out.println("User closed the window with X, choice="+choice);
            }
        }
    }

    public static void main(String[] args) {
        new BrowseWindow(new Data(),null);
    }
}
