import java.util.*;
public class Pawn extends Piece {
    public Pawn(boolean white, Board board) {
        super(white, board);
        value = 1;
    }
    public ArrayList<Move> getMoves() {
        int[] location = getPosition();
        ArrayList<Move> moves = new ArrayList<Move>();
        int y = location[1] - 1;
        if (white) {
            y = location[1] + 1;
        }
        int[] move = new int[] {location[0], y};
        if (board.atCoordinate(move) == null) {
            moves.add(new Move(location,new int[] {move[0], move[1]},board));
            if (location[1] == 1 && white) {
                move[1] += 1;
                if (board.atCoordinate(move) == null) {
                    moves.add(new Move(location,new int[] {move[0], move[1]},board));
                }
            }
            if (location[1] == 6 && !white) {
                move[1] -= 1;
                if (board.atCoordinate(move) == null) {
                    moves.add(new Move(location,new int[] {move[0], move[1]},board));
                }
            }
        }
        move[1] = y;
        if (location[0] != 0) {
            move[0] = location[0] - 1;
            if ((board.atCoordinate(move) != null && board.atCoordinate(move).getWhitePiece() != white) || (board.atCoordinate(new int[] {location[0]-1,location[1]}) != null
                    && board.atCoordinate(new int[] {location[0]-1,location[1]}).getWhitePiece() != white && board.atCoordinate(new int[] {location[0]-1,location[1]}).isEnPassantAble())) {
                moves.add(new Move(location,new int[] {move[0], move[1]},board));
            }
        }
        if (location[0] != 7) {
            move[0] = location[0] + 1;
            if ((board.atCoordinate(move) != null && board.atCoordinate(move).getWhitePiece() != white) || (board.atCoordinate(new int[] {location[0]+1,location[1]}) != null
                    && board.atCoordinate(new int[] {location[0]+1,location[1]}).getWhitePiece() != white && board.atCoordinate(new int[] {location[0]+1,location[1]}).isEnPassantAble())) {
                moves.add(new Move(location,new int[] {move[0], move[1]},board));
            }
        }
        return eliminateSelfChecks(moves);
    }
    public String toString() {
        if (getWhitePiece()) {
            return "\u265F";
        } else {
            return "\u2659";
        }
    }
}
