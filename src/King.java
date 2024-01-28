import java.util.ArrayList;
public class King extends Piece {
    public King(boolean white, Board board) {
        super(white, board);
        value = 9999;
    }
    public ArrayList<int[]> getMoves() {
        int[] location = getPosition();
        ArrayList<int[]> moves = new ArrayList<int[]>();

        moves.add(new int[] {location[0] + 1, location[1] + 1});
        moves.add(new int[] {location[0] + 1, location[1]});
        moves.add(new int[] {location[0] + 1, location[1] - 1});
        moves.add(new int[] {location[0], location[1] + 1});
        moves.add(new int[] {location[0], location[1] - 1});
        moves.add(new int[] {location[0] - 1, location[1] + 1});
        moves.add(new int[] {location[0] - 1, location[1]});
        moves.add(new int[] {location[0] - 1, location[1] - 1});

        for (int i = 7; i > -1; i--) {
            if (moves.get(i)[0] > 7 || moves.get(i)[0] < 0 || moves.get(i)[1] > 7 || moves.get(i)[1] < 0 || (board.atCoordinate(moves.get(i)) != null && board.atCoordinate(moves.get(i)).getWhitePiece() == white)) {
                moves.remove(i);
            }
        }

        if (isHasMoved() == false && !board.inCheck(white, true)) { //castling
            int i = 7;
            if (white) {
                i = 0;
            }
            if (board.atCoordinate(new int[] {7,i}) != null && !board.atCoordinate(new int[] {7,i}).isHasMoved()) {
                if (board.atCoordinate(new int[] {6,i}) == null && board.atCoordinate(new int[] {5,i}) == null) {
                    if (!board.causesCheck(location,new int[] {5,i}, white)){
                        moves.add(new int[] {6,i});
                    }
                }
            }
            if (board.atCoordinate(new int[] {0,i}) != null && !board.atCoordinate(new int[] {0,i}).isHasMoved()) {
                if (board.atCoordinate(new int[] {1,i}) == null && board.atCoordinate(new int[] {2,i}) == null && board.atCoordinate(new int[] {3,i}) == null) {
                    if (!board.causesCheck(location,new int[] {3,i}, white)){
                        moves.add(new int[] {2,i});
                    }
                }
            }
        }

        return eliminateSelfChecks(moves);
    }
    public String toString() {
        if (getWhitePiece()) {
            return "\u265A";
        } else {
            return "\u2654";
        }
    }
}
