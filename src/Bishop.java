public class Bishop extends Piece {
    public Bishop(boolean white, Board board) {
        super(white, board);
    }
    public boolean testMove(int[] location) {
        return true;
    }
    public String toString() {
        if (getWhitePiece()) {
            return "\u2657";
        } else {
            return "\u265D";
        }
    }
}
