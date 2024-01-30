public class Move {
    int[] to;
    int[] from;
    Piece captured;
    Move(int[] to, int[] from) {
        this.to = to;
        this.from = from;
        captured = null;
    }
    Move(int[] to, int[] from, Piece captured) {
        this.to = to;
        this.from = from;
        this.captured = captured;
    }
}
