package api;

public class Order {
    private ShoppingCart Cart;
    private double OrderPrice;
    private int NumberOfProductsBought;
    private String OrderDate;

    public void setCart(ShoppingCart cart) {
        Cart = cart;
    }

    public void setOrderPrice(double price) {
        OrderPrice = price;
    }
}
