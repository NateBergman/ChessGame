public class Move {
    int[] to;
    int[] from;
    Piece captured;
    boolean promotion;
    boolean enPassant;
    boolean castle;
    Move(int[] to, int[] from, Board board) {
        this.to = to;
        this.from = from;
        captured = board.atCoordinate(to);
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
}
