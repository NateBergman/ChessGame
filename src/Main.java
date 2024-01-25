import java.util.*;
public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        board.resetBoard();
        System.out.print(board);
        System.out.print("\n" + board.atCoordinate(new int[] {0,1}).getMoves().get(0)[0] + board.atCoordinate(new int[] {0,1}).getMoves().get(0)[1]);
    }

}