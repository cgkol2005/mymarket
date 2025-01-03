package gui;

import api.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;



public class BasicFrame extends JFrame{
    public JPanel north,south,east,west,center;
    public BasicFrame(LayoutManager LM){
        this.getContentPane().setLayout(LM);
        this.setSize(1280,720);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
       // createPanels();
       // setVisible(true);
    }

    public void createPanels(){
        north = new JPanel();
        south = new JPanel();
        east = new JPanel();
        west = new JPanel();
        center = new JPanel();

        Dimension panelsSize = new Dimension(250,100);
        north.setPreferredSize(panelsSize);
        south.setPreferredSize(panelsSize);
        east.setPreferredSize(panelsSize);
        west.setPreferredSize(panelsSize);
        center.setPreferredSize(panelsSize);


        north.setBackground(Color.white);
        south.setBackground(Color.yellow);
        east.setBackground(Color.red);
        west.setBackground(Color.blue);
        center.setBackground(Color.green);

        add(north,BorderLayout.NORTH);
        add(south,BorderLayout.SOUTH);
        add(east,BorderLayout.EAST);
        add(west,BorderLayout.WEST);
        add(center,BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        new BasicFrame(new BorderLayout());
    }
}
