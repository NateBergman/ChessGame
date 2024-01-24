public class Pawn extends Piece {
    public Pawn(boolean white, Board board) {
        super(white, board);
    }
    public boolean testMove(int[] location) {
        return true;
    }
    public String toString() {
        if (getWhitePiece()) {
            return "\u2659";
        } else {
            return "\u265F";
        }
    }
}
