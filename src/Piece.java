public abstract class Piece {
    protected int[] position;
    protected int value;
    protected boolean whitePiece;
    public Piece (boolean white, Board board) {
        this.whitePiece = white;
    }
    public abstract boolean testMove(int[] location);
    public int[] getPosition() {
        return position;
    }
    public boolean getWhitePiece() {
        return whitePiece;
    }
}