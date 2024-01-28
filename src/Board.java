import java.util.*;
public class Board {
    Piece[][] board;
    Piece[][] testBoard;
    King whiteKing;
    King blackKing;
    public Board() {
        board = new Piece[8][8];
        testBoard = new Piece[8][8];
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
    public void alignTestBoard() {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                testBoard[x][y] = board[x][y];
            }
        }
    }
    public Piece atCoordinate (int[] position, boolean test) {
        if (test) {
            return testBoard[position[0]][position[1]];
        } else {
            return board[position[0]][position[1]];
        }
    }
    public Piece atCoordinate (int[] position) {
        return board[position[0]][position[1]];
    }
    public int[] piecePosition (Piece piece, boolean test) {
        Piece[][] functionalBoard;
        if (test) {
            functionalBoard = testBoard;
        } else {
            functionalBoard = board;
        }
        int[] position = new int[2];
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (functionalBoard[x][y] == piece) {
                    position[0] = x;
                    position[1] = y;
                    x = 9;
                    y = 9;
                }
            }
        }
        return position;
    }
    public boolean causesCheck (int[] from, int[] to, boolean whiteMove) {
        alignTestBoard();
        move(from, to, true);

        King k;
        if (whiteMove) {
            k = whiteKing;
        } else {
            k = blackKing;
        }
        int [] location = piecePosition(k, true);

        //check for knights
        int[][] testLocations = new int[][] {{location[0] + 1, location[1] + 2},{location[0] + 2, location[1] + 1},{location[0] + 2, location[1] - 1},{location[0] + 1, location[1] - 2},
                {location[0] - 1, location[1] - 2},{location[0] - 2, location[1] - 1},{location[0] - 2, location[1] + 1},{location[0] - 1, location[1] + 2}};
        for (int i = 0; i < 8; i++) {
            if (testLocations[i][0] < 8 && testLocations[i][0] > -1 && testLocations[i][1] < 8 && testLocations[i][1] > -1 && testBoard[testLocations[i][0]][testLocations[i][1]] != null
                    && testBoard[testLocations[i][0]][testLocations[i][1]].getWhitePiece() != whiteMove && testBoard[testLocations[i][0]][testLocations[i][1]].getClass() == Knight.class) {
                return true;
            }
        }

        //check straight lines
        int x = location[0] + 1;
        int y = location[1];
        while (x < 8 && testBoard[x][y] == null) {
            x++;
        }
        if (x < 8 && testBoard[x][y].getWhitePiece() != whiteMove && (testBoard[x][y].getClass() == Rook.class || testBoard[x][y].getClass() == Queen.class)) {
            return true;
        }

        x = location[0] - 1;
        while (x > -1 && testBoard[x][y] == null) {
            x--;
        }
        if (x > -1 && testBoard[x][y].getWhitePiece() != whiteMove && (testBoard[x][y].getClass() == Rook.class || testBoard[x][y].getClass() == Queen.class)) {
            return true;
        }

        x = location[0];
        y = location[1] + 1;
        while (y < 8 && testBoard[x][y] == null) {
            y++;
        }
        if (y < 8 && testBoard[x][y].getWhitePiece() != whiteMove && (testBoard[x][y].getClass() == Rook.class || testBoard[x][y].getClass() == Queen.class)) {
            return true;
        }

        y = location[1] - 1;
        while (y > -1 && testBoard[x][y] == null) {
            y--;
        }
        if (y > -1 && testBoard[x][y].getWhitePiece() != whiteMove && (testBoard[x][y].getClass() == Rook.class || testBoard[x][y].getClass() == Queen.class)) {
            return true;
        }

        //check pawns
        if (whiteMove) {
            if (location[0]-1 < 8 && location[0]-1 > -1 && location[1]+1 < 8 && location[1]+1 > -1 &&
                    testBoard[location[0]-1][location[1]+1] != null && testBoard[location[0]-1][location[1]+1].getWhitePiece() == false && testBoard[location[0]-1][location[1]+1].getClass() == Pawn.class) {
                return true;
            }
            if (location[0]+1 < 8 && location[0]+1 > -1 && location[1]+1 < 8 && location[1]+1 > -1 &&
                    testBoard[location[0]+1][location[1]+1] != null && testBoard[location[0]+1][location[1]+1].getWhitePiece() == false && testBoard[location[0]+1][location[1]+1].getClass() == Pawn.class) {
                return true;
            }
        } else {
            if (location[0]-1 < 8 && location[0]-1 > -1 && location[1]-1 < 8 && location[1]-1 > -1 &&
                    testBoard[location[0]-1][location[1]-1] != null && testBoard[location[0]-1][location[1]-1].getWhitePiece() == true && testBoard[location[0]-1][location[1]-1].getClass() == Pawn.class) {
                return true;
            }
            if (location[0]+1 < 8 && location[0]+1 > -1 && location[1]-1 < 8 && location[1]-1 > -1 &&
                    testBoard[location[0]+1][location[1]-1] != null && testBoard[location[0]+1][location[1]-1].getWhitePiece() == true && testBoard[location[0]+1][location[1]-1].getClass() == Pawn.class) {
                return true;
            }
        }

        //check diagonals
        x = location[0] + 1;
        y = location[1] + 1;
        while (x < 8 && y < 8 && testBoard[x][y] == null) {
            x++;
            y++;
        }
        if (x < 8 && y < 8 && testBoard[x][y].getWhitePiece() != whiteMove && (testBoard[x][y].getClass() == Bishop.class || testBoard[x][y].getClass() == Queen.class)) {
            return true;
        }

        x = location[0] - 1;
        y = location[1] - 1;
        while (x > -1 && y > -1 && testBoard[x][y] == null) {
            x--;
            y--;
        }
        if (x > -1 && y > -1 && testBoard[x][y].getWhitePiece() != whiteMove && (testBoard[x][y].getClass() == Bishop.class || testBoard[x][y].getClass() == Queen.class)) {
            return true;
        }

        x = location[0] + 1;
        y = location[1] - 1;
        while (x < 8 && y > -1 && testBoard[x][y] == null) {
            x++;
            y--;
        }
        if (x < 8 && y > -1 && testBoard[x][y].getWhitePiece() != whiteMove && (testBoard[x][y].getClass() == Bishop.class || testBoard[x][y].getClass() == Queen.class)) {
            return true;
        }

        x = location[0] - 1;
        y = location[1] + 1;
        while (x > -1 && y < 8 && testBoard[x][y] == null) {
            x--;
            y++;
        }
        if (x > -1 && y < 8 && testBoard[x][y].getWhitePiece() != whiteMove && (testBoard[x][y].getClass() == Bishop.class || testBoard[x][y].getClass() == Queen.class)) {
            return true;
        }

        return false;
    }
    public void move (int[] from, int[] to, boolean test) {
        Piece[][] movingBoard;
        if (test) {
            movingBoard = testBoard;
        } else {
            movingBoard = board;
        }
        movingBoard[to[0]][to[1]] = movingBoard[from[0]][from[1]];
        movingBoard[from[0]][from[1]] = null;
        //promote
        if ((to[1] == 7 || to[1] == 0) && atCoordinate(to,test).getClass() == Pawn.class) {
            System.out.print("What should the pawn promote to? Q for queen, N for knight, R for rook, B for bishop : ");
            Scanner console = new Scanner(System.in);
            char c = console.next().charAt(0);
            if (c == 'N') {
                movingBoard[to[0]][to[1]] = new Knight(atCoordinate(to,test).getWhitePiece(), this);
            } else if (c == 'R') {
                movingBoard[to[0]][to[1]] = new Rook(atCoordinate(to,test).getWhitePiece(), this);
            } else if (c == 'B') {
                movingBoard[to[0]][to[1]] = new Bishop(atCoordinate(to,test).getWhitePiece(), this);
            } else {
                movingBoard[to[0]][to[1]] = new Queen(atCoordinate(to,test).getWhitePiece(), this);
            }
        }
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
