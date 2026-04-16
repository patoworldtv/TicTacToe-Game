import java.util.Scanner;

public class Main {

    private static final char EMPTY = ' ';
    private static final char PLAYER_X = 'X';
    private static final char PLAYER_O = 'O';

    public static void main(String[] args) {
        char[][] board = {
                {EMPTY, EMPTY, EMPTY},
                {EMPTY, EMPTY, EMPTY},
                {EMPTY, EMPTY, EMPTY}
        };

        char currentPlayer = PLAYER_X;
        Scanner scanner = new Scanner(System.in);
        boolean gameRunning = true;

        System.out.println("=== Tic-Tac-Toe ===");
        System.out.println("Player X vs Player O");
        System.out.println("Enter row and column (1-3) when it's your turn.\n");

        while (gameRunning) {
            printBoard(board);
            System.out.println("Player " + currentPlayer + ", it's your turn.");

            int row = -1;
            int col = -1;

            // Read valid move
            while (true) {
                System.out.print("Enter row (1-3): ");
                if (!scanner.hasNextInt()) {
                    System.out.println("Please enter a number between 1 and 3.");
                    scanner.next(); // clear invalid input
                    continue;
                }
                row = scanner.nextInt() - 1;

                System.out.print("Enter column (1-3): ");
                if (!scanner.hasNextInt()) {
                    System.out.println("Please enter a number between 1 and 3.");
                    scanner.next(); // clear invalid input
                    continue;
                }
                col = scanner.nextInt() - 1;

                if (isValidMove(board, row, col)) {
                    break;
                } else {
                    System.out.println("Invalid move. Try again.");
                }
            }

            board[row][col] = currentPlayer;

            if (hasWon(board, currentPlayer)) {
                printBoard(board);
                System.out.println("Player " + currentPlayer + " wins! 🎉");
                gameRunning = false;
            } else if (isBoardFull(board)) {
                printBoard(board);
                System.out.println("It's a draw!");
                gameRunning = false;
            } else {
                currentPlayer = (currentPlayer == PLAYER_X) ? PLAYER_O : PLAYER_X;
            }
        }

        System.out.println("Game over. Thanks for playing!");
        scanner.close();
    }

    private static void printBoard(char[][] board) {
        System.out.println();
        System.out.println("  1   2   3");
        for (int i = 0; i < 3; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] == EMPTY ? " " : board[i][j]);
                if (j < 2) System.out.print(" | ");
            }
            System.out.println();
            if (i < 2) {
                System.out.println("  ---------");
            }
        }
        System.out.println();
    }

    private static boolean isValidMove(char[][] board, int row, int col) {
        if (row < 0 || row > 2 || col < 0 || col > 2) {
            return false;
        }
        return board[row][col] == EMPTY;
    }

    private static boolean hasWon(char[][] board, char player) {
        // Rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player &&
                    board[i][1] == player &&
                    board[i][2] == player) {
                return true;
            }
        }

        // Columns
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == player &&
                    board[1][j] == player &&
                    board[2][j] == player) {
                return true;
            }
        }

        // Diagonals
        if (board[0][0] == player &&
                board[1][1] == player &&
                board[2][2] == player) {
            return true;
        }

        if (board[0][2] == player &&
                board[1][1] == player &&
                board[2][0] == player) {
            return true;
        }

        return false;
    }

    private static boolean isBoardFull(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }
}
