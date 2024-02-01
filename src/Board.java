import java.util.*;
public class Board {
    Piece[][] board;
    King whiteKing;
    King blackKing;
    public Board() {
        board = new Piece[8][8];
    }
    public void resetBoard() {
        board = new Piece[8][8];
        boolean white = true;
        for (int i = 0; i < 8; i += 7) {
            board[0][i] = new Rook(white, this);
            board[7][i] = new Rook(white, this);
            board[1][i] = new Knight(white, this);
            board[6][i] = new Knight(white, this);
            board[2][i] = new Bishop(white, this);
            board[5][i] = new Bishop(white, this);
            board[3][i] = new Queen(white, this);
            white = false;
        }
        whiteKing = new King(true, this);
        blackKing = new King(false, this);
        board[4][0] = whiteKing;
        board[4][7] = blackKing;
        white = true;
        for (int i = 1; i < 7; i += 5) {
            for (int j = 0; j < 8; j++) {
                board[j][i] = new Pawn(white, this);
            }
            white = false;
        }
    }
    public Piece atCoordinate (int[] position) {
        return board[position[0]][position[1]];
    }
    public int[] piecePosition (Piece piece) {
        int[] position = new int[2];
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (board[x][y] == piece) {
                    position[0] = x;
                    position[1] = y;
                    x = 9;
                    y = 9;
                }
            }
        }
        return position;
    }
    public void move (Move m) {
        int[] to = m.getTo();
        int[] from = m.getFrom();
        board[to[0]][to[1]] = board[from[0]][from[1]];
        board[from[0]][to[1]] = null;
        if (m.isPromotion()) {
            promote (to);
        }
        if (m.isEnPassant()) {
            board[to[0]][from[1]] = null;
        }
        //make everything not en passantable
        //make pawns moved 2 en passantable
        board[to[0]][to[1]].incrementMoveCount(true);
    }
    public void undoMove (Move m) {
        int[] to = m.getTo();
        int[] from = m.getFrom();
        if (m.isPromotion()) {
            board[from[0]][from[1]] = new Pawn(board[to[0]][to[1]].getWhitePiece(),this);
        } else {
            board[from[0]][from[1]] = board[to[0]][to[1]];
        }
        board[to[0]][to[1]] = m.getCaptured();
        if (m.isEnPassant()) {
            board[to[0]][from[1]] = new Pawn(!board[from[0]][from[1]].getWhitePiece(), this);
        }
        if (m.isCastle()) {

        }
        //change en passant flags
        board[from[0]][from[1]].incrementMoveCount(false);
    }
    public void promote (int[] square) {
        System.out.print("What should the pawn promote to? Q for queen, N for knight, R for rook, B for bishop : ");
        Scanner console = new Scanner(System.in);
        char c = console.next().charAt(0);
        if (c == 'N') {
            board[square[0]][square[1]] = new Knight(board[square[0]][square[1]].getWhitePiece(), this);
        } else if (c == 'R') {
            board[square[0]][square[1]] = new Rook(board[square[0]][square[1]].getWhitePiece(), this);
        } else if (c == 'B') {
            board[square[0]][square[1]] = new Bishop(board[square[0]][square[1]].getWhitePiece(), this);
        } else {
            board[square[0]][square[1]] = new Queen(board[square[0]][square[1]].getWhitePiece(), this);
        }
    }
    public ArrayList<Piece> getAllPiecesOfColor (boolean white) {
        ArrayList<Piece> pieces = new ArrayList<Piece>();
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (board[x][y] != null && board[x][y].getWhitePiece() == white) {
                    pieces.add(board[x][y]);
                }
            }
        }
        return pieces;
    }
    public ArrayList<Move> getLegalMoves (boolean white) {
        ArrayList<Piece> pieces = getAllPiecesOfColor(white);
        ArrayList<Move> moves = new ArrayList<Move>();
        for (Piece p : pieces) {
            ArrayList<Move> pieceMoves = p.getMoves();
            for (Move m : pieceMoves) {
                moves.add(m);
            }
        }
        return moves;
    }
    public String toString() {
        String output = "    a   b   c   d   e   f   g   h\n  ---------------------------------";
        for (int i = 7; i > -1; i--) {
            output += "\n" + (i + 1) + " |";
            for (int j = 0; j < 8; j++) {
                if (board[j][i] != null) {
                    output += " " + board[j][i] + " |";
                }
                else {
                    output += "   |";
                }
            }
            output += "\n  ---------------------------------";
        }
        return output;
    }
}
