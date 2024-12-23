package api;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderHistory implements Serializable {
    private List<Order> OrdersList;
    private double TotalOrdersCost;
    private int TotalNumberOfOrders;

    public OrderHistory(){
        OrdersList = new ArrayList<>();
    }

    public void addOrderToHistoy(Order order){
        OrdersList.add(order);
    }
    
    public void CalculateAndUpdateTotalOrdersCost(){
        BigDecimal sum = BigDecimal.ZERO;

        for (Order order : OrdersList){
            sum = sum.add(new BigDecimal(String.valueOf(order.getOrderPrice())));
        }
        TotalOrdersCost = sum.doubleValue();
    }
    
    public double getTotalPriceOfAllOrders(){
        CalculateAndUpdateTotalOrdersCost();
        return TotalOrdersCost;
    }
}
