import java.util.*;
public abstract class Piece {
    protected int value;
    protected boolean white;
    private boolean hasMoved;
    boolean enPassantAble;
    Board board;
    public Piece (boolean white, Board board) {
        this.white = white;
        this.board = board;
        hasMoved = false;
        enPassantAble = false;
    }
    public abstract ArrayList<Move> getMoves();
    public int[] getPosition() {
        return board.piecePosition(this, false);
    }
    protected ArrayList<Move> eliminateSelfChecks (ArrayList<Move> suggestedMoves) {
        enPassantAble = false;
        ArrayList<Move> legalMoves = new ArrayList<Move>();
        for (Move m : suggestedMoves) {
            if (!board.causesCheck(m,white)) {
                legalMoves.add(m);
            }
        }
        return legalMoves;
    }
    public void setEnPassantAble() {
        enPassantAble = true;
    }
    public boolean isEnPassantAble() {
        return enPassantAble;
    }
    public boolean getWhitePiece() {
        return white;
    }
    public void updateHasMoved() {
        hasMoved = true;
    }
    public boolean isHasMoved() {
        return hasMoved;
    }
}