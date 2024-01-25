import java.util.*;
public class Board {
    Piece[][] board;
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
            board[4][i] = new King(white, this);
            white = false;
        }
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
