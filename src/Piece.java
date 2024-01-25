import java.util.*;
public abstract class Piece {
    protected int value;
    protected boolean white;
    Board board;
    public Piece (boolean white, Board board) {
        this.white = white;
        this.board = board;
    }
    public abstract ArrayList<int[]> getMoves();
    public int[] getPosition() {
        return board.piecePosition(this);
    }
    public boolean getWhitePiece() {
        return white;
    }
}