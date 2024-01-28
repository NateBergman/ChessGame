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
            moves.add(new int[] {move[0], move[1]});
            if (location[1] == 1 && white) {
                move[1] += 1;
                if (board.atCoordinate(move) == null) {
                    moves.add(new int[]{move[0], move[1]});
                }
            }
            if (location[1] == 6 && !white) {
                move[1] -= 1;
                if (board.atCoordinate(move) == null) {
                    moves.add(new int[]{move[0], move[1]});
                }
            }
        }
        move[1] = y;
        if (location[0] != 0) {
            move[0] = location[0] - 1;
            if (board.atCoordinate(move) != null && board.atCoordinate(move).getWhitePiece() != white) {
                moves.add(new int[] {move[0], move[1]});
            }
        }
        if (location[0] != 7) {
            move[0] = location[0] + 1;
            if (board.atCoordinate(move) != null && board.atCoordinate(move).getWhitePiece() != white) {
                moves.add(new int[] {move[0], move[1]});
            }
        }
        return eliminateSelfChecks(moves);
        //add en passant
    }
    public String toString() {
        if (getWhitePiece()) {
            return "\u265F";
        } else {
            return "\u2659";
        }
    }
}
