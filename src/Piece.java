import java.util.*;
public abstract class Piece {
    protected int value;
    protected boolean white;
    private boolean hasMoved;
    Board board;
    public Piece (boolean white, Board board) {
        this.white = white;
        this.board = board;
        hasMoved = false;
    }
    public abstract ArrayList<int[]> getMoves();
    public int[] getPosition() {
        return board.piecePosition(this, false);
    }
    protected ArrayList<int[]> eliminateSelfChecks (ArrayList<int[]> suggestedMoves) {
        ArrayList<int[]> legalMoves = new ArrayList<int[]>();
        for (int[] x : suggestedMoves) {
            if (!board.causesCheck(getPosition(),x,white)) {
                legalMoves.add(x);
            }
        }
        return legalMoves;
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