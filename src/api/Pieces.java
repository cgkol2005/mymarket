package api;

import java.io.Serializable;

public class Pieces extends Quantity implements Serializable {
    Pieces(int pieces) {
        super(pieces);
    }
    public int getQuantity(){
        return super.getPieces();
    }
}
