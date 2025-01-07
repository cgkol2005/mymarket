package gui;

import api.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

public class ViewProductWindow{

    public ViewProductWindow(Product product, Data data){
        BasicFrame viewProductWindow = new BasicFrame(new BorderLayout());
        viewProductWindow.setSize(800,720);
        viewProductWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        /*
        {JPanel redRight = new JPanel();
        redRight.setPreferredSize(new Dimension(100,0));
        redRight.setBackground(Color.red);
        viewProductWindow.add(redRight, BorderLayout.EAST);}*/

        /*
        {JPanel blueLeft = new JPanel();
        blueLeft.setPreferredSize(new Dimension(100,0));
        blueLeft.setBackground(Color.blue);
        viewProductWindow.add(blueLeft, BorderLayout.WEST);}*/

        JPanel southButtonsPanel = new JPanel();
        southButtonsPanel.setPreferredSize(new Dimension(0,120));
        viewProductWindow.add(southButtonsPanel,BorderLayout.SOUTH);
        southButtonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        Dimension buttonSize = new Dimension(200,100);
        Font buttonFont = new Font("Courier New", Font.PLAIN, 25);

        if (data.getCurrentUser() instanceof Customer){
            JButton addToCartButton = new JButton("Add to Cart");
            addToCartButton.setPreferredSize(buttonSize);
            addToCartButton.setFont(buttonFont);
            southButtonsPanel.add(addToCartButton);

            addToCartButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    addToCartDialog(product, data);
                }
            });
        }else {
            JButton editButton = new JButton("Edit Product");
            editButton.setPreferredSize(new Dimension(400,100));
            editButton.setFont(buttonFont);
            southButtonsPanel.add(editButton);

            editButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new EditWindow(data, product);
                }
            });
        }


        JButton closeButton = new JButton("Close");
        closeButton.setPreferredSize(buttonSize);
        closeButton.setFont(buttonFont);


        southButtonsPanel.add(closeButton);

        JPanel centerPanel = new JPanel();
        centerPanel.setPreferredSize(new Dimension(0,0));
        viewProductWindow.add(centerPanel,BorderLayout.CENTER);

        JPanel northPanel = new JPanel();{
            northPanel.setPreferredSize(new Dimension(0,160));
            //northPanel.setBackground(Color.magenta);
            JLabel productTitle = new JLabel(product.getTitle());
            productTitle.setHorizontalAlignment(SwingConstants.CENTER);
            productTitle.setVerticalAlignment(SwingConstants.CENTER);
            productTitle.setFont(new Font("Courier New",Font.ITALIC | Font.BOLD,35));
            productTitle.setBorder(new EmptyBorder(60,0,0,0));
            northPanel.add(productTitle);
            viewProductWindow.add(northPanel,BorderLayout.NORTH);}


        JPanel infoPanel = new JPanel();
        BoxLayout box = new BoxLayout(infoPanel, BoxLayout.Y_AXIS);
        infoPanel.setLayout(box);
        Font labelsFont = new Font("Times New Roman", Font.ITALIC | Font.BOLD, 30);
        JLabel description = new JLabel("Description: "+product.getDescription());
        description.setVerticalAlignment(SwingConstants.CENTER);
        description.setVerticalAlignment(SwingConstants.CENTER);
        description.setFont(labelsFont);
        JLabel category = new JLabel("Category: "+ product.getCategory());
        category.setVerticalAlignment(SwingConstants.CENTER);
        category.setVerticalAlignment(SwingConstants.CENTER);
        category.setFont(labelsFont);
        JLabel subcategory = new JLabel("Subcategory: "+product.getSub_category());
        subcategory.setVerticalAlignment(SwingConstants.CENTER);
        subcategory.setVerticalAlignment(SwingConstants.CENTER);
        subcategory.setFont(labelsFont);
        JLabel price;

        if (product instanceof WeightedProduct){
            //String[] words = product.getTitle().split("\\s+");
            //String lastWord = words[words.length - 1];
            price = new JLabel("Price: "+ String.valueOf(product.getPrice())+"€ per kg");//+lastWord);
        }else{
            price = new JLabel("Price: "+ String.valueOf(product.getPrice())+"€ per piece");
        }


        price.setVerticalAlignment(SwingConstants.CENTER);
        price.setVerticalAlignment(SwingConstants.CENTER);
        price.setFont(labelsFont);
        JLabel availability;
        if (product.getAvailability()){
            availability = new JLabel("Availability: Available");
        }else {
            availability = new JLabel("Availability: Out of stock. :(");
        }
        availability.setVerticalAlignment(SwingConstants.CENTER);
        availability.setVerticalAlignment(SwingConstants.CENTER);
        availability.setFont(labelsFont);



        infoPanel.add(Box.createRigidArea(new Dimension(0,20)));
        infoPanel.add(description);
        infoPanel.add(Box.createRigidArea(new Dimension(0,20)));
        infoPanel.add(Box.createVerticalGlue());
        infoPanel.add(category);
        infoPanel.add(Box.createRigidArea(new Dimension(0,20)));
        infoPanel.add(Box.createVerticalGlue());
        infoPanel.add(subcategory);
        infoPanel.add(Box.createRigidArea(new Dimension(0,20)));
        infoPanel.add(Box.createVerticalGlue());
        infoPanel.add(price);
        infoPanel.add(Box.createRigidArea(new Dimension(0,20)));
        infoPanel.add(Box.createVerticalGlue());
        infoPanel.add(availability);
        infoPanel.add(Box.createRigidArea(new Dimension(0,20)));
        infoPanel.add(Box.createVerticalGlue());

        JScrollPane scroll = new JScrollPane(infoPanel);

        viewProductWindow.add(scroll, BorderLayout.CENTER);


        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewProductWindow.dispose();
            }
        });


        viewProductWindow.setVisible(true);
    }

    public static void addToCartDialog(Product product,Data data){
        Customer customer = (Customer) data.getCurrentUser();
        if (product.getAvailability()) {
            JFrame sliderFrame = new JFrame("Quantity to be put in Shopping Cart");
            sliderFrame.setLayout(new BorderLayout());
            sliderFrame.setSize(800, 300);
            sliderFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            sliderFrame.setLocationRelativeTo(null);

            JLabel question = new JLabel("How much do you want to put in your shopping cart?");
            question.setFont(new Font("Courier New", Font.ITALIC, 17));
            question.setHorizontalAlignment(SwingConstants.CENTER);
            question.setVerticalAlignment(SwingConstants.CENTER);
            JPanel questionPanel = new JPanel(new BorderLayout());
            questionPanel.setPreferredSize(new Dimension(0, 80));
            questionPanel.add(question, BorderLayout.CENTER);

            sliderFrame.add(questionPanel, BorderLayout.NORTH);


            JPanel sliderPanel = new JPanel(new BorderLayout());
            sliderPanel.setPreferredSize(new Dimension(100, 50));
            JSlider slider;
            if (product instanceof WeightedProduct) {
                int max = (int) (((WeightedProduct) product).getStock()) * 1000;
                int quantityInCart = data.getCurrentCustomerUser().getShoppingCart().getProductQuantityInShoppingCart(product.getTitle());
                if (quantityInCart > 0){
                    max = max - quantityInCart;
                }
                slider = new JSlider(0, max, max / 2);
            } else if(product instanceof PackagedProduct){
                int max = ((PackagedProduct) product).getStock();
                int quantityInCart = customer.getShoppingCart().getProductQuantityInShoppingCart(product.getTitle());
                if (quantityInCart > 0){
                    max = max - quantityInCart;
                }
                slider = new JSlider(0, max, max/ 2);
            }else{
                slider = new JSlider(0, 0, 0);
            }

            slider.setSize(100, 50);
            slider.setPaintTicks(true);
            if (product instanceof PackagedProduct){
                slider.setMinorTickSpacing(1);
            }

            if (product instanceof PackagedProduct){
                slider.setMajorTickSpacing(((PackagedProduct) product).getStock() / 10);
            }
            slider.setPaintTrack(true);
            slider.setPaintLabels(true);


            JLabel quantity = new JLabel();
            quantity.setFont(new Font("MV Boli", Font.BOLD, 18));
            quantity.setVerticalAlignment(SwingConstants.CENTER);
            quantity.setHorizontalAlignment(SwingConstants.CENTER);
            sliderPanel.add(quantity, BorderLayout.NORTH);
            sliderPanel.add(slider, BorderLayout.CENTER);

            if (product instanceof WeightedProduct) {
                quantity.setText(String.valueOf(slider.getValue()) + " grams");
            } else {
                quantity.setText(String.valueOf(slider.getValue()) + " pieces");
            }


            sliderFrame.add(sliderPanel, BorderLayout.CENTER);

            JPanel buttonPanel = new JPanel(new FlowLayout());
            buttonPanel.setPreferredSize(new Dimension(0, 38));
            JButton addButton = new JButton("Add");
            addButton.setPreferredSize(new Dimension(60, 30));
            Font buttonFont = new Font("Cascadia mono", Font.ITALIC | Font.BOLD, 13);
            addButton.setFont(buttonFont);

            JButton cancelButton = new JButton("Cancel");
            cancelButton.setPreferredSize(new Dimension(100, 30));
            cancelButton.setFont(buttonFont);

            JButton left = new JButton("<");
            left.setPreferredSize(new Dimension(40,30));
            left.setFont(new Font("Cascadia mono", Font.ITALIC,11));
            JButton right = new JButton(">");
            right.setPreferredSize(new Dimension(40,30));
            right.setFont(new Font("Cascadia mono", Font.ITALIC,11));

            //Buttons for weighted:
            JButton fifty = new JButton("+50");
            fifty.setPreferredSize(new Dimension(55,30));
            fifty.setFont(new Font("Cascadia mono", Font.ITALIC,11));
            JButton minusFifty = new JButton("-50");
            minusFifty.setPreferredSize(new Dimension(55,30));
            minusFifty.setFont(new Font("Cascadia mono", Font.ITALIC,11));
            JButton hundred = new JButton("+100");
            hundred.setPreferredSize(new Dimension(60,30));
            hundred.setFont(new Font("Cascadia mono", Font.ITALIC,11));
            JButton minusHundred = new JButton("-100");
            minusHundred.setPreferredSize(new Dimension(60,30));
            minusHundred.setFont(new Font("Cascadia mono", Font.ITALIC,11));
            JButton thousand = new JButton("+1000");
            thousand.setPreferredSize(new Dimension(70,30));
            thousand.setFont(new Font("Cascadia mono", Font.ITALIC,11));
            JButton minusthousand = new JButton("-1000");
            minusthousand.setPreferredSize(new Dimension(70,30));
            minusthousand.setFont(new Font("Cascadia mono", Font.ITALIC,11));

            //Buttons for packaged:
            JButton five = new JButton("+5");
            five.setPreferredSize(new Dimension(46,30));
            five.setFont(new Font("Cascadia mono", Font.ITALIC,11));
            JButton minusFive = new JButton("-5");
            minusFive.setPreferredSize(new Dimension(46,30));
            minusFive.setFont(new Font("Cascadia mono", Font.ITALIC,11));
            JButton ten = new JButton("+10");
            ten.setPreferredSize(new Dimension(55,30));
            ten.setFont(new Font("Cascadia mono", Font.ITALIC,11));
            JButton minusTen = new JButton("-10");
            minusTen.setPreferredSize(new Dimension(55,30));
            minusTen.setFont(new Font("Cascadia mono", Font.ITALIC,11));

            addButton.setBackground(Color.green);
            cancelButton.setBackground(Color.red);

            if (product instanceof WeightedProduct){
                buttonPanel.add(minusthousand);
                buttonPanel.add(minusHundred);
                buttonPanel.add(minusFifty);
            }else{
                buttonPanel.add(minusTen);
                buttonPanel.add(minusFive);
            }
            buttonPanel.add(left);
            buttonPanel.add(addButton);
            buttonPanel.add(cancelButton);
            buttonPanel.add(right);
            if (product instanceof WeightedProduct){
                buttonPanel.add(fifty);
                buttonPanel.add(hundred);
                buttonPanel.add(thousand);
            }else{
                buttonPanel.add(five);
                buttonPanel.add(ten);
            }

            sliderFrame.add(buttonPanel, BorderLayout.SOUTH);


            slider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    if(product instanceof WeightedProduct) {
                        BigDecimal sliderValueinKgs = BigDecimal.valueOf(slider.getValue());
                        sliderValueinKgs = sliderValueinKgs.divide(BigDecimal.valueOf(1000));
                        quantity.setText(String.valueOf(slider.getValue()) + " grams("+ sliderValueinKgs.doubleValue()+"kgs)");
                    }else{
                        quantity.setText(String.valueOf(slider.getValue()) + " pieces");
                    }
                    addButton.setEnabled(slider.getValue() != 0);
                }
            });

            minusHundred.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    slider.setValue(slider.getValue() - 100);
                }
            });
            thousand.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    slider.setValue(slider.getValue() + 1000);
                }
            });
            minusthousand.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    slider.setValue(slider.getValue() - 1000);
                }
            });

            minusFifty.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    slider.setValue(slider.getValue() - 50);
                }
            });

            hundred.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    slider.setValue(slider.getValue() + 100);
                }
            });

            fifty.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    slider.setValue(slider.getValue() + 50);
                }
            });

            minusTen.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    slider.setValue(slider.getValue() - 10);
                }
            });

            minusFive.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    slider.setValue(slider.getValue() - 5);
                }
            });

            ten.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    slider.setValue(slider.getValue() + 10);
                }
            });

            five.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    slider.setValue(slider.getValue() + 5);
                }
            });

            left.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    slider.setValue(slider.getValue() - 1);
                }
            });

            right.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    slider.setValue(slider.getValue() + 1);
                }
            });

            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    sliderFrame.dispose();
                }
            });
            if(slider.getValue() == 0){
                question.setText("All of the shop's stock of this product is already in your cart!");
                addButton.setEnabled(false);
            }

            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int choice = 0;
                    if (product instanceof WeightedProduct){
                        String weightedPrice = String.format("Are you sure that you want to add %d grams of %s in your cart? Cost: %.2f€",slider.getValue(),product.getTitle(),calculateTotalPrice(product,slider.getValue()));
                        if (slider.getValue() >= 1000){
                            weightedPrice = String.format("Are you sure that you want to add %.2f Kgs of %s in your cart? Cost: %.2f€",BigDecimal.valueOf(slider.getValue()).divide(BigDecimal.valueOf(1000)),product.getTitle(),calculateTotalPrice(product,slider.getValue()));
                        }
                        choice = JOptionPane.showConfirmDialog(null,weightedPrice , "Confirmation", JOptionPane.YES_NO_OPTION);
                    }else{
                        String weightedPrice = String.format("Are you sure that you want to add %d pieces of %s in your cart? Cost: %.2f€",slider.getValue(),product.getTitle(),calculateTotalPrice(product,slider.getValue()));
                        choice = JOptionPane.showConfirmDialog(null,weightedPrice, "Confirmation", JOptionPane.YES_NO_OPTION);
                    }
                    if (choice == JOptionPane.YES_OPTION) {
                        System.out.println("User chose YES");
                        //if (product instanceof WeightedProduct){
                        customer.getShoppingCart().addProduct(product,slider.getValue());
                        JOptionPane.showMessageDialog(null, "Product added to your shopping Cart!", "Product added!", JOptionPane.INFORMATION_MESSAGE);
                        //}else if(product instanceof PackagedProduct){
                        //data.getCurrentCustomerUser().getShoppingCart().addProduct((PackagedProduct) product,slider.getValue());
                        //JOptionPane.showMessageDialog(null, "Product added to your shopping Cart!", "Product added!", JOptionPane.INFORMATION_MESSAGE);
                        // }

                        sliderFrame.dispose();
                    } else {
                        System.out.println("User chose NO");
                    }

                }
            });
            sliderFrame.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null, "We're sorry. "+product.getTitle()+" is currently out of stock!", "Out of stock. :(", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static double calculateTotalPrice(Product product,int quantity){
        BigDecimal totalPrice;
        BigDecimal dec1 = new BigDecimal(quantity);
        BigDecimal dec2 = BigDecimal.valueOf(product.getPrice());
        if (product instanceof WeightedProduct){
            totalPrice = dec1.multiply(dec2);
            totalPrice = totalPrice.divide(BigDecimal.valueOf(1000));
            return totalPrice.doubleValue();
        }
        totalPrice = dec1.multiply(dec2);
        return totalPrice.doubleValue();
    }
}
