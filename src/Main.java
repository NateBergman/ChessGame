import java.lang.reflect.Array;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        board.resetBoard();

        Scanner console = new Scanner(System.in);
        boolean whiteMove = true;
        System.out.print("Enter 1 for white player, 2 for white bot");
        boolean whiteBot = console.nextInt() == 2;
        System.out.print("Enter 1 for black player, 2 for black bot");
        boolean blackBot = console.nextInt() == 2;

        while (true) {
            System.out.println(board + "\n");
            boolean stuck = true;
            //ArrayList<int[][]> moves =
            ArrayList<Piece> pieces = board.getAllPiecesOfColor(whiteMove);
            for (Piece p : pieces) {
                if (p.getMoves().size() > 0) {
                    stuck = false;
                }
            }
            if (stuck) {
                if (board.inCheck(whiteMove, true)) {
                    if (whiteMove) {
                        System.out.print("Black wins by checkmate!");
                    } else {
                        System.out.print("White wins by checkmate!");
                    }
                } else {
                    System.out.print("Draw by stalemate!");
                }
                break;
            }

            if (whiteMove) {
                System.out.println("White's move");
            } else {
                System.out.println("Black's move");
            }
            int[] from = new int[2];
            int[] to = new int[2];
            if ((whiteMove && whiteBot) || (!whiteMove && blackBot)) { //randomly picks a legal move
                Random rand = new Random();
                Piece p = pieces.get(rand.nextInt(pieces.size() - 1));
                from = p.getPosition();
                to = p.getMoves().get(rand.nextInt(p.getMoves().size() - 1));
            } else { //get player move
                boolean repeat = true;
                do {
                    do {
                        System.out.print("Enter coordinates of the piece you would like to move\nX : ");
                        from[0] = ((int) console.next().charAt(0)) - 'a';
                        System.out.print("Y : ");
                        from[1] = console.nextInt() - 1;
                    } while (!(from[0] > -1 && from[0] < 8 && from[1] > -1 && from[1] < 8 && board.atCoordinate(from) != null && board.atCoordinate(from).getWhitePiece() == whiteMove));
                    System.out.print("Enter coordinates of where you would like to move it to\nX : ");
                    to[0] = ((int) console.next().charAt(0)) - 'a';
                    System.out.print("Y : ");
                    to[1] = console.nextInt() - 1;
                    for (int[] m : board.atCoordinate(from).getMoves()) {
                        if (Arrays.equals(m, to)) {
                            repeat = false;
                        }
                    }
                } while (repeat);
                //while ()
            }

            if (board.atCoordinate(from).getClass() == King.class && from[0] == 4 && to[0] == 2) {//moves rook along with king when castling
                board.move(new int[] {0,from[1]}, new int[] {3,from[1]}, false);
            } else if (board.atCoordinate(from).getClass() == King.class && from[0] == 4 && to[0] == 6) {
                board.move(new int[] {7,from[1]}, new int[] {5,from[1]}, false);
            }

            if (board.atCoordinate(from).getClass() == Pawn.class && ((from[1] == 1 && to[1] == 3) || (from[1] == 6 && to[1] == 4))) { //if a pawn moves 2
                board.atCoordinate(from).setEnPassantAble(); //it can be en passanted next turn
            }

            if (board.atCoordinate(from).getClass() == Pawn.class && from[0] != to[0] && board.atCoordinate(to) == null) { //if en passanting
                board.move(new int[] {to[0],from[1]}, to, false); //capture the pawn by moving it into the path
            }

            board.move(from, to, false);
            board.atCoordinate(to).updateHasMoved();
            whiteMove = !whiteMove;
        }
    }
}