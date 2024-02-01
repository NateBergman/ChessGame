import java.lang.reflect.Array;
import java.util.*;
public class Main { //Chess by Nate Bergman
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
            ArrayList<Move> moves = board.getLegalMoves(whiteMove);
            if (moves.isEmpty()) {
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
            Move move = null;
            if ((whiteMove && whiteBot) || (!whiteMove && blackBot)) { //randomly picks a legal move
                Random rand = new Random();

            } else { //get player move
                int[] from = new int[2];
                int[] to = new int[2];
                boolean repeat = true;
                do {
                    System.out.print("Enter coordinates of the piece you would like to move\nX : ");
                    from[0] = ((int) console.next().charAt(0)) - 'a';
                    System.out.print("Y : ");
                    from[1] = console.nextInt() - 1;
                    System.out.print("Enter coordinates of where you would like to move it to\nX : ");
                    to[0] = ((int) console.next().charAt(0)) - 'a';
                    System.out.print("Y : ");
                    to[1] = console.nextInt() - 1;
                    for (Move m : moves) {
                        if (Arrays.equals(m.to,to) && Arrays.equals(m.from,from)) {
                            move = m;
                        }
                    }
                } while (move == null);
            }

            if (board.atCoordinate(from).getClass() == King.class && from[0] == 4 && to[0] == 2) {//moves rook along with king when castling
                board.move(new int[] {0,from[1]}, new int[] {3,from[1]}, false);
            } else if (board.atCoordinate(from).getClass() == King.class && from[0] == 4 && to[0] == 6) {
                board.move(new int[] {7,from[1]}, new int[] {5,from[1]}, false);
            }
            board.move(move);
            whiteMove = !whiteMove;
        }
    }
}