public class Rook extends Piece {
    public Rook(boolean white, Board board) {
        super(white, board);
    }
    public boolean testMove(int[] location) {
        return true;
    }
    public String toString() {
        if (getWhitePiece()) {
            return "\u2656";
        } else {
            return "\u265C";
        }
    }
}
