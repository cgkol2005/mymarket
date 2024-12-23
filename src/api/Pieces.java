package api;

public class Pieces extends Quantity{
    Pieces(int pieces) {
        super(pieces);
    }
    public int getQuantity(){
        return super.getPieces();
    }
}
