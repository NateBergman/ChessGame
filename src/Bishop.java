import java.util.ArrayList;
public class Bishop extends Piece {
    public Bishop(boolean white, Board board) {
        super(white, board);
        value = 3;
    }
    public ArrayList<int[]> getMoves() {
        int[] location = getPosition();
        ArrayList<int[]> moves = new ArrayList<int[]>();
        return moves;
    }
    public String toString() {
        if (getWhitePiece()) {
            return "\u2657";
        } else {
            return "\u265D";
        }
    }
}
