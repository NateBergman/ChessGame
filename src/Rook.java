import java.util.ArrayList;
public class Rook extends Piece {
    public Rook(boolean white, Board board) {
        super(white, board);
        value = 5;
    }
    public ArrayList<int[]> getMoves() {
        int[] location = getPosition();
        ArrayList<int[]> moves = new ArrayList<int[]>();
        int x = location[0] + 1;
        int y = location[1];

        while (x < 8 && board.atCoordinate(new int[] {x,y}) == null) {
            moves.add(new int[] {x,y});
            x += 1;
        }
        if (x < 8 && board.atCoordinate(new int[] {x,y}).getWhitePiece() != white) {
            moves.add(new int[] {x,y});
        }

        x = location[0] - 1;
        while (x > -1 && board.atCoordinate(new int[] {x,y}) == null) {
            moves.add(new int[] {x,y});
            x -= 1;
        }
        if (x > -1 && board.atCoordinate(new int[] {x,y}).getWhitePiece() != white) {
            moves.add(new int[] {x,y});
        }

        x = location[0];
        y = location[1] + 1;
        while (y < 8 && board.atCoordinate(new int[] {x,y}) == null) {
            moves.add(new int[] {x,y});
            y += 1;
        }
        if (y < 8 && board.atCoordinate(new int[] {x,y}).getWhitePiece() != white) {
            moves.add(new int[] {x,y});
        }

        y = location[1] - 1;
        while (y > -1 && board.atCoordinate(new int[] {x,y}) == null) {
            moves.add(new int[] {x,y});
            y -= 1;
        }
        if (y > -1 && board.atCoordinate(new int[] {x,y}).getWhitePiece() != white) {
            moves.add(new int[] {x,y});
        }

        return eliminateSelfChecks(moves);
    }
    public String toString() {
        if (getWhitePiece()) {
            return "\u265C";
        } else {
            return "\u2656";
        }
    }
}
