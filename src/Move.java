public class Move {
    int[] to;
    int[] from;
    Piece captured;
    int specialMove;
    Move(int[] to, int[] from, Board board, int specialMove) {
        this.to = to;
        this.from = from;
        captured = board.atCoordinate(to);
        this.specialMove = specialMove;
    }
    Move(int[] to, int[] from, Board board) {
        this.to = to;
        this.from = from;
        captured = board.atCoordinate(to);
        this.specialMove = 0;
    }
    public int[] getTo() {
        return to;
    }
    public int[] getFrom() {
        return from;
    }
    public Piece getCaptured() {
        return captured;
    }
    public boolean isPromotion() {
        return specialMove == 1;
    }
    public boolean isEnPassant() {
        return specialMove == 2;
    }
    public boolean isCastle() {
        return specialMove == 3;
    }
}
