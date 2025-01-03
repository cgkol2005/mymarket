package gui;

import api.Data;
import api.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static gui.ViewProductWindow.addToCartDialog;

public class BrowseWindow {
    private Data data;

    private JPanel centerPanel;


    public BrowseWindow(Data data){
        this.data = data;
        buildUI();
    }

    private void buildUI(){
        BasicFrame browseWindow = new BasicFrame(new BorderLayout(3,3));

        browseWindow.setName("BrowseWindow");
        data.addActiveFrame(browseWindow);

        //PANELS

        JPanel rightPanel = new JPanel();{
        rightPanel.setPreferredSize(new Dimension(280,0));
        rightPanel.setBackground(Color.red);
        browseWindow.add(rightPanel,BorderLayout.EAST);}

        JPanel leftPanel = new JPanel();{
        leftPanel.setPreferredSize(new Dimension(280,0));
        leftPanel.setBackground(Color.blue);
        browseWindow.add(leftPanel, BorderLayout.WEST);}

        JPanel southPanel = new JPanel();
        southPanel.setPreferredSize(new Dimension(0,80));
        //southPanel.setBackground(Color.yellow); //mainBottom Panel color commented out
        //browseWindow.add(southPanel,BorderLayout.SOUTH); //adding it some lines down when adding the little lines first

        centerPanel = new JPanel(new GridLayout(0,4,5,5));
            for (int i = 0; i < data.getProductsList().size(); i++){
                JButton productButton = new JButton(data.getProductsList().get(i).getTitle());
                productButton.setActionCommand(data.getProductsList().get(i).getTitle());
                productButton.setFont(new Font("Courier New", Font.PLAIN, 15));
                productButton.setPreferredSize(new Dimension(155,111));
                productButton.addActionListener(new ButtonClickListener());
                if (centerPanel != null) {
                    centerPanel.add(productButton);
                }
            }
            JScrollPane scroll = new JScrollPane(centerPanel);
            browseWindow.add(scroll,BorderLayout.CENTER);


        JPanel northPanel = new JPanel();
            northPanel.setPreferredSize(new Dimension(0,80));
            northPanel.setBackground(Color.magenta);
            browseWindow.add(northPanel,BorderLayout.NORTH);

        //Let's divide the bottom panel into three different boxes. Use a grid maybe? A box?
        GridLayout bottomPanelGrid = new GridLayout(1,3,2,0); //creates a grid layout with 3 boxes horizontally.
        southPanel.setLayout(bottomPanelGrid); //adds this layout as the manager of the yellow bottom panel

        //creating 3 different panels to represent our little boxes.
        JPanel pinkPanel_BottomLeft = new JPanel();
        JPanel yellowPanel_BottomMid = new JPanel();
        JPanel greenPanel_BottomRight = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        //adding colour to the 3 panels
        pinkPanel_BottomLeft.setBackground(Color.pink);
        yellowPanel_BottomMid.setBackground(Color.yellow);
        greenPanel_BottomRight.setBackground(Color.green);

        //assigning to the mainBottom panel each little panel one by one.
        southPanel.add(pinkPanel_BottomLeft);
        southPanel.add(yellowPanel_BottomMid);
        //southPanel.add(greenPanel_BottomRight);

        //finally, adding the mainBottom panel that holds the other 3 to the main Frame
        //browseWindow.add(southPanel,BorderLayout.SOUTH);

        Font buttonFont = new Font("Inconsolata",Font.BOLD,13);
        JButton backButton = new JButton("Back");
        backButton.setFont(buttonFont);
        Dimension buttonDimension = new Dimension(150,40);
        backButton.setPreferredSize(buttonDimension);
        greenPanel_BottomRight.add(backButton);
        backButton.setVerticalAlignment(SwingConstants.CENTER);


        southPanel.add(greenPanel_BottomRight);

        //finally, adding the mainBottom panel that holds the other 3 to the main Frame
        browseWindow.add(southPanel,BorderLayout.SOUTH);
        //southPanel.add(backButton);


        browseWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                data.DataSave();
                System.exit(0);
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                data.removeActiveFrame("BrowseWindow");
                browseWindow.dispose();
                data.getFrameFromActiveFramesList("CustomerWindowOptions").setVisible(true);
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
                    addToCartDialog(data.getProductFromProductsList(command_ProductTitle),data);
                }
            }else{
                System.out.println("User closed the window with X, choice="+choice);
            }
        }
    }


}
