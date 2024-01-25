import java.util.ArrayList;
public class Queen extends Piece {
    public Queen(boolean white, Board board) {
        super(white, board);
        value = 9;
    }
    public ArrayList<int[]> getMoves() {
        int[] location = getPosition();
        ArrayList<int[]> moves = new ArrayList<int[]>();
        return moves;
    }
    public String toString() {
        if (getWhitePiece()) {
            return "\u2655";
        } else {
            return "\u265B";
        }
    }
}
