package gui;

import api.Customer;
import api.Data;
import api.Order;
import api.OrderHistory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class OrderHistoryWindow {
    private JFrame historyFrame;
    private Data data;
    Customer customer;
    OrderHistory orderHistory;
    List<Order> orderList;
    public OrderHistoryWindow(Data data) {
        customer = data.getCurrentCustomerUser();
        orderHistory = customer.getOrderHistory();
        orderList = orderHistory.getOrdersList();
        this.data = data;
        historyFrame = new JFrame("MyMarketCustomer");
        historyFrame.setSize(1280, 720);
        historyFrame.setLayout(new BorderLayout());
        historyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        historyFrame.setLocationRelativeTo(null);
        JLabel historyMessage = new JLabel("Order History");
        historyMessage.setPreferredSize(new Dimension(0,80));
        historyMessage.setFont(new Font("Courier New",Font.BOLD,50));
        historyMessage.setHorizontalAlignment(SwingConstants.CENTER);
        historyMessage.setVerticalAlignment(SwingConstants.CENTER);
        historyFrame.add(historyMessage, BorderLayout.NORTH);



        JPanel rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(280,0));
        rightPanel.setBackground(Color.red);
        historyFrame.add(rightPanel,BorderLayout.EAST);


        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(280,0));
        leftPanel.setBackground(Color.blue);

        historyFrame.add(leftPanel, BorderLayout.WEST);



        JPanel southPanel = new JPanel();
        southPanel.setPreferredSize(new Dimension(0,80));
        southPanel.setBackground(Color.yellow);
        southPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        historyFrame.add(southPanel,BorderLayout.SOUTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setPreferredSize(new Dimension(50,50));
        centerPanel.setBackground(Color.green);
        historyFrame.add(centerPanel,BorderLayout.CENTER);

        JButton viewOrderButton = new JButton("View Order");
        southPanel.add(viewOrderButton);

        JButton refreshButton = new JButton("Refresh");
        southPanel.add(refreshButton);

        JButton backButton = new JButton("Back");
        southPanel.add(backButton);

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                historyFrame.dispose();
                new OrderHistoryWindow(data);
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                data.DataSave();
                historyFrame.dispose();
                data.getFrameFromActiveFramesList("CustomerWindowOptions").setVisible(true);
            }
        });





        String[] columnsNames = new String[]{"Order Date", "Number Of Products Bought","Total Price Of Order"};
        Object[][] orders = create2DArrayForOrders();
        DefaultTableModel model = new DefaultTableModel(orders, columnsNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Makes all cells non-editable
            }
        };
        JTable orderHistoryTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(orderHistoryTable);

        historyFrame.add(scrollPane);

        viewOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (orderHistoryTable.getSelectedRow() != -1){

                }
            }
        });

        historyFrame.setVisible(true);

    }

    private Object[][] create2DArrayForOrders(){
        customer = data.getCurrentCustomerUser();
        orderHistory = customer.getOrderHistory();
        orderList = orderHistory.getOrdersList();
        Object[][] table = new Object[orderHistory.getTotalNumberOfOrders()][3];

        for (int i = 0; i < orderHistory.getTotalNumberOfOrders(); i++){
            table[i][0] = orderList.get(i).getOrderDate();
            table[i][1] = orderList.get(i).getNumberOfProductsBought();
            table[i][2] = orderList.get(i).getOrderPrice();
        }

        return table;
    }

}