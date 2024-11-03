import java.util.Scanner;

public class TicTacToe implements TwoPlayerGame {

    Scanner scan = new Scanner(System.in);

    String[][] moves = {{" ", " ", " "}, {" ", " ", " "}, {" ", " ", " "}}; /* declares and initializes the array of
                                                                            moves made in the game to an array of spaces */
    boolean isWon = false; // declares and initializes a boolean to track whether or not the game is won
    String winner = ""; // declares and initializes an empty string to display the winner of the game

    @Override
    // draws the tic-tac-toe board
    public void drawBoard() {
        for (int i = 0; i < 11; i++) { // loops through the lines of the board to print them
            if (i == 3 || i == 7) // prints the horizontal line of the board at the appropriate places
                System.out.println("----------------------------");
            else if (i == 1) // prints the moves and necessary padding around the moves in the first populated row
                System.out.println("    "+moves[0][0]+"    |    "+moves[0][1]+"    |    "+moves[0][2]+"   ");
            else if (i == 5) // prints the moves and necessary padding around the moves in the second populated row
                System.out.println("    "+moves[1][0]+"    |    "+moves[1][1]+"    |    "+moves[1][2]+"   ");
            else if (i == 9) // prints the moves and necessary padding around the moves in the third populated row
                System.out.println("    "+moves[2][0]+"    |    "+moves[2][1]+"    |    "+moves[2][2]+"   ");
            else // prints necessary padding above and below the moves
                System.out.println("         |         |        ");
        }
    }

    @Override
    // requests moves from the players and edits moves[][] accordingly
    public void playerMove(TwoPlayerGame.Player p) {
        System.out.println("Where would you like to play? Please answer in the format \"y x\", where y is the row and " +
                "x is the column (the first being 0, the second being 1, and the third being 2. Please do not select a box " +
                "already filled by another player's move.");

        if (p.equals(TwoPlayerGame.Player.ONE))
        {
            moves[scan.nextInt()][scan.nextInt()] = "X"; // changes the array at the given index to X when player 1 moves
        }
        else if (p.equals(TwoPlayerGame.Player.TWO))
        {
            moves[scan.nextInt()][scan.nextInt()] = "O"; // changes the array at the given index to O when player 2 moves
        }
    }

    @Override
    // checks if the game is won
    public boolean gameIsWon() {
        for (int i = 0; i < 3; i++) { // "i" used to loop through rows of the move array

            // checks if the rows are equal
            if (!moves[i][0].equals(" ") && moves[i][0].equals(moves[i][1])) { /* makes sure the row is filled and if
                                                                               the first two indices are equal moves onto the third */
                if (moves[i][1].equals(moves[i][2])) { // checks if the second and third indices are equal
                    isWon = true;
                    winner = moves[i][1]; // stores the winning player
                }
            }
        }
        for (int i = 0; i < 3; i++) { // "i" used to loop through columns of the move array

            // checks if the columns are equal
            if (!moves[0][i].equals(" ") && moves[0][i].equals(moves[1][i])) { /* makes sure the row is filled and if
                                                                               the first two indices are equal moves onto the third */
                if (moves[1][i].equals(moves[2][i])) { // checks if the second and third indices are equal
                    isWon = true;
                    winner = moves[1][i]; // stores the winning player
                }
            }
        }

        // checks if the top left to bottom right diagonal is equal
        if (!moves[0][0].equals(" ") && moves[0][0].equals(moves[1][1])) { /* makes sure the diagonal is filled and if
                                                                           the first two indices are equal moves onto the third */
            if (moves[1][1].equals(moves[2][2])) { // checks if the second and third indices are equal
                isWon = true;
                winner = moves[1][1]; // stores the winning player
            }
        }
        // checks if the top right to bottom left diagonal is equal
        else if (!moves[0][2].equals(" ") && moves[0][2].equals(moves[1][1])) { /* makes sure the diagonal is filled and if
                                                                                the first two indices are equal moves onto the third */
            if (moves[1][1].equals(moves[2][0])) { // checks if the second and third indices are equal
                isWon = true;
                winner = moves[1][1]; // stores the winning player
            }
        }

        boolean full = true;
        // loops through the array to check if all indices are filled
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (moves[i][j].equals(" "))
                    full = false; // if an index is empty "full" is set to false
            }
        }
        // if all indices are full without a winner the game ends
        if (!isWon && full) {
            isWon = true;
            winner = "Game Over";
        }
        return isWon; // returns true if the game is won
    }

    @Override
    // prints the winner of the game
    public void announceWinner() {
        if (winner.equals("X"))  // checks if player 1 won the game and announces it if so
            System.out.println("Player One (" + winner + ") has won the game!");
        else if (winner.equals("O")) // checks if player 2 won the game and announces it if so
            System.out.println("Player Two (" + winner + ") has won the game!");
        else if (winner.equals("Game Over")) // checks if the game ended at a stalemate and prints it if so
            System.out.println(winner + "! No one won");
    }
}