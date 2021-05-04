import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TTT {

    static ArrayList<Integer> playerList = new ArrayList<Integer>();
    static ArrayList<Integer> cpuList = new ArrayList<Integer>();

    public static void main(String[] args) throws Exception {
        char[][] Board = { { ' ', '|', ' ', '|', ' ' }, { '-', '+', '-', '+', '-' }, { ' ', '|', ' ', '|', ' ' },
                { '-', '+', '-', '+', '-' }, { ' ', '|', ' ', '|', ' ' } };
        printBoard(Board);

        while (true) {
            Scanner scan = new Scanner(System.in);
            System.out.println("input Position:");
            int inputPos = scan.nextInt();
            while (playerList.contains(inputPos) || cpuList.contains(inputPos)) {
                System.out.println("occupy");
                inputPos = scan.nextInt();
            }
            layMark(Board, inputPos, "player");

            String winnerMsg = checkWinner();
            if (winnerMsg.length() > 0) {
                System.out.println(winnerMsg);
                break;
            }
            Random rand = new Random();
            int randPos = rand.nextInt(9) + 1;
            while (playerList.contains(randPos) || cpuList.contains(randPos)) {
                randPos = rand.nextInt(9) + 1;
            }
            layMark(Board, randPos, "cpu");
            printBoard(Board);
            winnerMsg = checkWinner();
            if (winnerMsg.length() > 0) {
                System.out.println(winnerMsg);
                break;
            }
        }
    }

    public static void printBoard(char[][] Board) {
        for (char[] row : Board) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void layMark(char[][] Board, int pos, String user) {
        char mark = ' ';
        if (user.equals("player")) {
            mark = 'X';
            playerList.add(pos);
        } else if (user.equals("cpu")) {
            mark = 'O';
            cpuList.add(pos);
        }

        switch (pos) {
            case 1:
                Board[0][0] = mark;
                break;
            case 2:
                Board[0][2] = mark;
                break;
            case 3:
                Board[0][4] = mark;
                break;
            case 4:
                Board[2][0] = mark;
                break;
            case 5:
                Board[2][2] = mark;
                break;
            case 6:
                Board[2][4] = mark;
                break;
            case 7:
                Board[4][0] = mark;
                break;
            case 8:
                Board[4][2] = mark;
                break;
            case 9:
                Board[4][4] = mark;
                break;
            default:
                break;
        }
    }

    public static String checkWinner(){
        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);
        List leftCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);
        List cross1 = Arrays.asList(1, 5, 9);
        List cross2 = Arrays.asList(3, 5, 7);

        List<List> winned = new ArrayList<List>();
        winned.add(topRow);
        winned.add(midRow);
        winned.add(botRow);
        winned.add(leftCol);
        winned.add(midCol);
        winned.add(rightCol);
        winned.add(cross1);
        winned.add(cross2);

        for (List<List> l :winned){
            if (playerList.containsAll(l)){
                return "you WIN!";
            } else if (cpuList.containsAll(l)){
                return "cpu WIN!";
            } else if (playerList.size()+cpuList.size()==9){
                return "Tie!";
            }
        }
        return "";

    }

}
