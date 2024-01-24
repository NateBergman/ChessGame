public class Queen extends Piece {
    public Queen(boolean white, Board board) {
        super(white, board);
    }
    public boolean testMove(int[] location) {
        return true;
    }
    public String toString() {
        if (getWhitePiece()) {
            return "\u2655";
        } else {
            return "\u265B";
        }
    }
}
