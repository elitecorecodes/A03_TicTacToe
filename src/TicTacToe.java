
import java.util.Scanner;

public class TicTacToe {

    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static final String[][] board = new String[ROWS][COLS];

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        boolean playAgain;

        // Overall game loop: continue until players choose to quit
        do {
            // Start a new game with an empty board
            clearBoard();

            String currentPlayer = "X";
            boolean gameOver = false;

            System.out.println("\nWelcome to Tic Tac Toe!");

            // Turn loop: continue until a win or tie
            while (!gameOver) {

                // Show the current board
                display();

                // Get and validate the current player's move
                playerMove(in, currentPlayer);

                // Check for a winner before checking for a tie
                if (isWin(currentPlayer)) {
                    display();
                    System.out.println(
                            "\nPlayer " + currentPlayer + " wins!");
                    gameOver = true;

                } else if (isTie()) {
                    display();
                    System.out.println("\nIt's a tie!");
                    gameOver = true;

                } else {
                    // Alternate between X and O
                    if (currentPlayer.equals("X")) {
                        currentPlayer = "O";
                    } else {
                        currentPlayer = "X";
                    }
                }
            }

            // Ask whether the players want another game
            playAgain = SafeInput.getYNConfirm(
                    in, "Would you like to play again?");

        } while (playAgain);

        System.out.println("\nThanks for playing!");
        in.close();
    }

    // Clear the board by filling every cell with a space
    private static void clearBoard() {

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                board[row][col] = " ";
            }
        }
    }

    // Display the Tic Tac Toe board with row and column numbers
    private static void display() {

        System.out.println("\n    1   2   3");

        for (int row = 0; row < ROWS; row++) {

            System.out.print((row + 1) + " ");

            for (int col = 0; col < COLS; col++) {

                System.out.print(" " + board[row][col] + " ");

                if (col < COLS - 1) {
                    System.out.print("|");
                }
            }

            System.out.println();

            if (row < ROWS - 1) {
                System.out.println("  ---+---+---");
            }
        }
    }

    // Return true if the proposed square is empty
    private static boolean isValidMove(int row, int col) {

        return row >= 0 && row < ROWS &&
                col >= 0 && col < COLS &&
                board[row][col].equals(" ");
    }

    // Check whether the specified player has won
    private static boolean isWin(String player) {

        return isRowWin(player) ||
                isColWin(player) ||
                isDiagWin(player);
    }

    // Check for three matching marks in any row
    private static boolean isRowWin(String player) {

        for (int row = 0; row < ROWS; row++) {

            if (board[row][0].equals(player) &&
                    board[row][1].equals(player) &&
                    board[row][2].equals(player)) {

                return true;
            }
        }

        return false;
    }

    // Check for three matching marks in any column
    private static boolean isColWin(String player) {

        for (int col = 0; col < COLS; col++) {

            if (board[0][col].equals(player) &&
                    board[1][col].equals(player) &&
                    board[2][col].equals(player)) {

                return true;
            }
        }

        return false;
    }

    // Check both possible diagonal wins
    private static boolean isDiagWin(String player) {

        // Top-left to bottom-right
        if (board[0][0].equals(player) &&
                board[1][1].equals(player) &&
                board[2][2].equals(player)) {

            return true;
        }

        // Top-right to bottom-left
        if (board[0][2].equals(player) &&
                board[1][1].equals(player) &&
                board[2][0].equals(player)) {

            return true;
        }

        return false;
    }

    // Check for a tie:
    // The board is full, OR every possible winning line
    // is blocked by containing both X and O.
    private static boolean isTie() {

        boolean boardFull = true;

        // First, check whether any empty squares remain
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {

                if (board[row][col].equals(" ")) {
                    boardFull = false;
                }
            }
        }

        if (boardFull) {
            return true;
        }

        // Check whether all eight winning lines are blocked

        // Three rows
        for (int row = 0; row < ROWS; row++) {

            if (!lineBlocked(
                    board[row][0],
                    board[row][1],
                    board[row][2])) {

                return false;
            }
        }

        // Three columns
        for (int col = 0; col < COLS; col++) {

            if (!lineBlocked(
                    board[0][col],
                    board[1][col],
                    board[2][col])) {

                return false;
            }
        }

        // First diagonal
        if (!lineBlocked(
                board[0][0],
                board[1][1],
                board[2][2])) {

            return false;
        }

        // Second diagonal
        if (!lineBlocked(
                board[0][2],
                board[1][1],
                board[2][0])) {

            return false;
        }

        return true;
    }

    // A winning line is blocked when it contains
    // at least one X and at least one O.
    private static boolean lineBlocked(
            String a, String b, String c) {

        boolean hasX =
                a.equals("X") ||
                        b.equals("X") ||
                        c.equals("X");

        boolean hasO =
                a.equals("O") ||
                        b.equals("O") ||
                        c.equals("O");

        return hasX && hasO;
    }

    // Prompt for a move until a legal square is selected
    private static void playerMove(
            Scanner in, String player) {

        int row;
        int col;

        while (true) {

            System.out.println(
                    "\nPlayer " + player + "'s turn");

            // SafeInput rejects non-integers and
            // values outside the range 1 through 3
            row = SafeInput.getRangedInt(
                    in, "Enter row", 1, 3);

            col = SafeInput.getRangedInt(
                    in, "Enter column", 1, 3);

            // Convert user coordinates to array indexes
            row--;
            col--;

            // Accept only an unoccupied square
            if (isValidMove(row, col)) {

                board[row][col] = player;
                break;

            } else {

                System.out.println(
                        "That square is already occupied. Try again.");
            }
        }
    }
}

