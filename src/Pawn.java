import java.util.*;
public class Pawn extends Piece {
    boolean enPassantAble;
    public Pawn(boolean white, Board board) {
        super(white, board);
        value = 1;
        enPassantAble = false;
    }
    public ArrayList<Move> getMoves() {
        int[] location = getPosition();
        ArrayList<Move> moves = new ArrayList<Move>();
        int y = -1;
        if (white) {
            y = 1;
        }
        int specialMove = 0;
        if (location[1] + y == 7 || location[1] + y == 0) { //if one rank away from promotion, all legal moves will lead to promotion
            specialMove = 1;
        }
        if (board.atCoordinate(new int[] {location[0],location[1] + y}) == null) { //basic move forward
            moves.add(new Move(location,new int[] {location[0], location[1] + y},board,specialMove));
            if (board.atCoordinate(new int[] {location[0],location[1] + y + y}) == null && !hasMoved()) { //double move at start
                moves.add(new Move(location,new int[] {location[0], location[1] + y + y},board));
            }
        }
        if (location[0] != 0) {
            int[] move1 = new int[] {location[0] - 1,location[1] + y};
            if (board.atCoordinate(move1) != null && board.atCoordinate(move1).getWhitePiece() != white) {
                moves.add(new Move(location,move1,board,specialMove));
            }
            int[] move2 = new int[] {location[0] - 1, location[1]};
            if (board.atCoordinate(move2) != null && board.atCoordinate(move2).getWhitePiece() != white && board.atCoordinate(move2).isEnPassantAble()) {
                moves.add(new Move(location,move1,board,2));
            }
        }
        if (location[0] != 7) {
            int[] move1 = new int[] {location[0] + 1,location[1] + y};
            if (board.atCoordinate(move1) != null && board.atCoordinate(move1).getWhitePiece() != white) {
                moves.add(new Move(location,move1,board,specialMove));
            }
            int[] move2 = new int[] {location[0] + 1, location[1]};
            if (board.atCoordinate(move2) != null && board.atCoordinate(move2).getWhitePiece() != white && board.atCoordinate(move2).isEnPassantAble()) {
                moves.add(new Move(location,move1,board,2));
            }
        }
        return eliminateSelfChecks(moves);
    }
    public void setEnPassantAble() {
        enPassantAble = true;
    }
    public boolean isEnPassantAble() {
        return enPassantAble;
    }
    public String toString() {
        if (getWhitePiece()) {
            return "\u265F";
        } else {
            return "\u2659";
        }
    }
}
