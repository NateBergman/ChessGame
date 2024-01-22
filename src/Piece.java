public abstract class Piece {
    protected int[] position;
    protected boolean whitePiece;
    public Piece (int[] position, boolean white) {
        this.position = position;
        this.whitePiece = white;
    }
    public abstract boolean TestMove(int[] location);
    public int[] getPosition() {
        return position;
    }
    public boolean getWhitePiece() {
        return whitePiece;
    }
}
