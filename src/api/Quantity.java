package api;

import java.io.Serializable;
import java.math.BigDecimal;

public class Quantity implements Serializable {
    private int pieces;
    private double kgs;

    Quantity(int pieces){
        this.pieces = pieces;
        kgs = 0;
    }

    Quantity(double kgs){
        this.kgs = kgs;
        pieces = 0;
    }


    protected int getPieces(){
        return pieces;
    }

    protected double getKgs(){
        return kgs;
    }

    public void addQuantity(int pieces){
        this.pieces += pieces;
    }

    public void addQuantity(double kgs){
        BigDecimal dec1 = new BigDecimal(String.valueOf(kgs));
        BigDecimal dec2 = new BigDecimal(String.valueOf(this.kgs));
        BigDecimal sum = dec1.add(dec2);
        this.kgs = sum.doubleValue();
    }
}
