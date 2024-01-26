import java.util.ArrayList;
public class Bishop extends Piece {
    public Bishop(boolean white, Board board) {
        super(white, board);
        value = 3;
    }
    public ArrayList<int[]> getMoves() {
        int[] location = getPosition();
        ArrayList<int[]> moves = new ArrayList<int[]>();

        int x = location[0] + 1;
        int y = location[1] + 1;
        while (x < 8 && y < 8 && board.atCoordinate(new int[] {x,y}) == null) {
            moves.add(new int[] {x,y});
            x += 1;
            y += 1;
        }
        if (x < 8 && y < 8 && board.atCoordinate(new int[] {x,y}).getWhitePiece() != white) {
            moves.add(new int[] {x,y});
        }

        x = location[0] - 1;
        y = location[1] - 1;
        while (y > -1 && x > -1 && board.atCoordinate(new int[] {x,y}) == null) {
            moves.add(new int[] {x,y});
            x -= 1;
            y -= 1;
        }
        if (x > -1 && y > -1 && board.atCoordinate(new int[] {x,y}).getWhitePiece() != white) {
            moves.add(new int[] {x,y});
        }

        x = location[0] - 1;
        y = location[1] + 1;
        while (x > -1 && y < 8 && board.atCoordinate(new int[] {x,y}) == null) {
            moves.add(new int[] {x,y});
            y += 1;
            x -= 1;
        }
        if (x > -1 && y < 8 && board.atCoordinate(new int[] {x,y}).getWhitePiece() != white) {
            moves.add(new int[] {x,y});
        }

        x = location[0] + 1;
        y = location[1] - 1;
        while (x < 8 && y > -1 && board.atCoordinate(new int[] {x,y}) == null) {
            moves.add(new int[] {x,y});
            y -= 1;
            x += 1;
        }
        if (x < 8 && y > -1 && board.atCoordinate(new int[] {x,y}).getWhitePiece() != white) {
            moves.add(new int[] {x,y});
        }
        return moves;
    }
    public String toString() {
        if (getWhitePiece()) {
            return "\u265D";
        } else {
            return "\u2657";
        }
    }
}
