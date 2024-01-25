import java.util.ArrayList;
public class Rook extends Piece {
    public Rook(boolean white, Board board) {
        super(white, board);
        value = 5;
    }
    public ArrayList<int[]> getMoves() {
        int[] location = getPosition();
        ArrayList<int[]> moves = new ArrayList<int[]>();
        return moves;
    }
    public String toString() {
        if (getWhitePiece()) {
            return "\u2656";
        } else {
            return "\u265C";
        }
    }
}
