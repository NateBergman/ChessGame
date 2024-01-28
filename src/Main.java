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
                    /*while (!console.hasNext()) {
                        System.out.print("X : ");
                    }*/
                    from[0] = ((int)console.next().charAt(0)) - 'a';
                    //while (!console.hasNextInt()) {
                        System.out.print("Y : ");
                    //}
                    from[1] = console.nextInt() - 1;
                } while (!(from[0] > -1 && from[0] < 8 && from[1] > -1 && from[1] < 8 && board.atCoordinate(from) != null && board.atCoordinate(from).getWhitePiece() == whiteMove));
                System.out.print("Enter coordinates of where you would like to move it to\nX : ");
                //while (!console.hasNext()) {
                //    System.out.print("X : ");
                //}
                to[0] = ((int)console.next().charAt(0)) - 'a';
                //while (!console.hasNextInt()) {
                    System.out.print("Y : ");
                //}
                to[1] = console.nextInt() - 1;
                for (int[] m : board.atCoordinate(from).getMoves()) {
                    if (Arrays.equals(m,to)) {
                        repeat = false;
                    }
                }
            } while (repeat);
            System.out.println(board.causesCheck(from,to,whiteMove));
            board.move(from, to, false);
            whiteMove = !whiteMove;
        }
    }
}