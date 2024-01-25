import java.util.*;
public class Pawn extends Piece {
    public Pawn(boolean white, Board board) {
        super(white, board);
        value = 1;
    }
    public ArrayList<int[]> getMoves() {
        int[] location = getPosition();
        ArrayList<int[]> moves = new ArrayList<int[]>();
        int y = location[1] - 1;
        if (white) {
            y = location[1] + 1;
        }
        int[] move = new int[] {location[0], y};
        if (board.atCoordinate(move) == null) {
            moves.add(move);
        }
        if (location[0] != 0) {
            move[0] = location[0] - 1;
            if (board.atCoordinate(move) != null && board.atCoordinate(move).getWhitePiece() != white) {
                moves.add(move);
            }
        }
        if (location[0] != 7) {
            move[0] = location[0] + 1;
            if (board.atCoordinate(move) != null && board.atCoordinate(move).getWhitePiece() != white) {
                moves.add(move);
            }
        }
        return moves;
    }
    public String toString() {
        if (getWhitePiece()) {
            return "\u265F";
        } else {
            return "\u2659";
        }
    }
}
