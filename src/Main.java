import java.lang.reflect.Array;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        board.resetBoard();

        Scanner console = new Scanner(System.in);
        boolean whiteMove = true;

        while (true) {
            System.out.println(board + "\n");

            boolean stuck = true;
            ArrayList<Piece> pieces = board.getAllPiecesOfColor(whiteMove);
            for (Piece p : pieces) {
                if (p.getMoves().size() > 0) {
                    stuck = false;
                }
            }
            if (stuck) {
                if (board.causesCheck(false, pieces.get(0).getPosition(), pieces.get(0).getPosition(), whiteMove)) {
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
            boolean repeat = true;
            do {
                do {
                    System.out.print("Enter coordinates of the piece you would like to move\nX : ");
                    from[0] = ((int)console.next().charAt(0)) - 'a';
                    System.out.print("Y : ");
                    from[1] = console.nextInt() - 1;
                } while (!(from[0] > -1 && from[0] < 8 && from[1] > -1 && from[1] < 8 && board.atCoordinate(from) != null && board.atCoordinate(from).getWhitePiece() == whiteMove));
                System.out.print("Enter coordinates of where you would like to move it to\nX : ");
                to[0] = ((int)console.next().charAt(0)) - 'a';
                System.out.print("Y : ");
                to[1] = console.nextInt() - 1;
                for (int[] m : board.atCoordinate(from).getMoves()) {
                    if (Arrays.equals(m,to)) {
                        repeat = false;
                    }
                }
            } while (repeat);
            board.move(from, to, false);
            whiteMove = !whiteMove;
        }
    }
}