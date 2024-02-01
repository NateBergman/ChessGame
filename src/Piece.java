import java.util.*;
public abstract class Piece {
    protected int value;
    protected boolean white;
    private int moveCount;
    Board board;
    public Piece (boolean white, Board board) {
        this.white = white;
        this.board = board;
        moveCount = 0;
    }
    public abstract ArrayList<Move> getMoves();
    public int[] getPosition() {
        return board.piecePosition(this, false);
    }
    protected ArrayList<Move> eliminateSelfChecks (ArrayList<Move> suggestedMoves) {
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
    public void incrementMoveCount(boolean positive) {
        if (positive) {
            moveCount++;
        } else {
            moveCount--;
        }
    }
    public boolean hasMoved() {
        if (moveCount > 0) {
            return true;
        } else {
            return false;
        }
    }
}