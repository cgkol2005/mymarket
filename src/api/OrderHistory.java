package api;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
/**
 * The  OrderHistory class manages a collection of ordder objects.
 * It tracks the total cost of all orders and the total number of orders in the history.
 */
public class OrderHistory implements Serializable {
    private List<Order> OrdersList;
    private double TotalOrdersCost;
    private int TotalNumberOfOrders;

    /**
     * Constructs a new orderhistory object.
     * Initializes an empty list of orders.
     */
    public OrderHistory(){
        OrdersList = new ArrayList<>();
    }

    /**
     * Adds an order to the order history
     * @param order the order to be added to the history
     */
    public void addOrderToHistoy(Order order){
        OrdersList.add(order);
    }
    /**
     * Calculates and updates the total cost of all orders in the history.
     */
    public void CalculateAndUpdateTotalOrdersCost(){
        BigDecimal sum = BigDecimal.ZERO;

        for (Order order : OrdersList){
            sum = sum.add(new BigDecimal(String.valueOf(order.getOrderPrice())));
        }
        TotalOrdersCost = sum.doubleValue();
    }
    /**
     * Returns the total cost of all orders in the history
     * This method first ensures the total cost is up-to-date by calling
     * @return the total price of all orders
     */

    public List<Order> getOrdersList(){
        return OrdersList;
    }

    public int getTotalNumberOfOrders(){
        return OrdersList.size();
    }
    public double getTotalPriceOfAllOrders(){
        CalculateAndUpdateTotalOrdersCost();
        return TotalOrdersCost;
    }
}
