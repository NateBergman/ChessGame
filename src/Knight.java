import java.util.ArrayList;
public class Knight extends Piece {
    public Knight(boolean white, Board board) {
        super(white, board);
        value = 3;
    }
    public ArrayList<int[]> getMoves() {
        int[] location = getPosition();
        ArrayList<int[]> moves = new ArrayList<int[]>();

        moves.add(new int[] {location[0] + 1, location[1] + 2});
        moves.add(new int[] {location[0] + 2, location[1] + 1});
        moves.add(new int[] {location[0] + 2, location[1] - 1});
        moves.add(new int[] {location[0] + 1, location[1] - 2});
        moves.add(new int[] {location[0] - 1, location[1] - 2});
        moves.add(new int[] {location[0] - 2, location[1] - 1});
        moves.add(new int[] {location[0] - 2, location[1] + 1});
        moves.add(new int[] {location[0] - 1, location[1] + 2});

        for (int i = 7; i > -1; i--) {
            if (moves.get(i)[0] > 7 || moves.get(i)[0] < 0 || moves.get(i)[1] > 7 || moves.get(i)[1] < 0 || (board.atCoordinate(moves.get(i)) != null && board.atCoordinate(moves.get(i)).getWhitePiece() != white)) {
                moves.remove(i);
            }
        }

        return moves;
    }
    public String toString() {
        if (getWhitePiece()) {
            return "\u265E";
        } else {
            return "\u2658";
        }
    }
}
