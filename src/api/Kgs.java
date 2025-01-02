package api;

import java.io.Serializable;

public class Kgs extends Quantity implements Serializable {
    Kgs(double kgs) {
        super(kgs);
    }
    public double getQuantity(){
        return super.getKgs();
    }
}
