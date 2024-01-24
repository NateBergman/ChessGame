public class King extends Piece {
    public King(boolean white, Board board) {
        super(white, board);
    }
    public boolean testMove(int[] location) {
        return true;
    }
    public String toString() {
        if (getWhitePiece()) {
            return "\u2654";
        } else {
            return "\u265A";
        }
    }
}
