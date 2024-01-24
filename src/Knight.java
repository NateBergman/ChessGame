public class Knight extends Piece {
    public Knight(boolean white, Board board) {
        super(white, board);
    }
    public boolean testMove(int[] location) {
        return true;
    }
    public String toString() {
        if (getWhitePiece()) {
            return "\u2658";
        } else {
            return "\u265E";
        }
    }
}
