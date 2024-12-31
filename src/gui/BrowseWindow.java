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
        }

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
        new BrowseWindow(new Data());
    }
}
