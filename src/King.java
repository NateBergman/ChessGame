import java.util.ArrayList;
public class King extends Piece {
    public King(boolean white, Board board) {
        super(white, board);
        value = 9999;
    }
    public ArrayList<int[]> getMoves() {
        int[] location = getPosition();
        ArrayList<int[]> moves = new ArrayList<int[]>();
        return moves;
    }
    public boolean inCheck() {
        return false;
    }
    public String toString() {
        if (getWhitePiece()) {
            return "\u265A";
        } else {
            return "\u2654";
        }
    }
}
