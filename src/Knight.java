import java.util.ArrayList;
public class Knight extends Piece {
    public Knight(boolean white, Board board) {
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
            return "\u2658";
        } else {
            return "\u265E";
        }
    }
}
