/*
* (c) 2021 Joel Hammer
* Friends School Baltimore
*
*
* Use subject to terms of GNU Public License 3.0.
*
*
*/

/**
* A game which can be played by two players alternately making moves on
* a shared playing "board." The board need not be a rectangular plane,
* but some shared playing arena must exist between the two players.
* <br>
* <br>
* A {@code TwoPlayerGame} is always played in alternate turns where
* victory may be achieved after either player's move. It is possible to
* track moves and mandate that both players have the same number of
* moves before allowing a winner to be declared, but care should be
* taken that in such cases the {@code gameIsWon() } or 
* {@code getWinner()} methods do not prematurely return an incorrect
* value.
* <br>
* <br>
* Each player move should be exhaustive of his turn. The 
* {@code TwoPlayerGame } is an alternating game (chess for example)
*  as opposed to a sort of "freestyle" game where turn sharing does not
* occur, as in soccer or a competitive sports. A players move may have
* multiple components or inputs, but this must be entirely contained
* within a single invocation of {@code playerMove() } and no reliance
* may be made upon a reinvocation of this method.
* <br>
* Thus for any instation of the {@code TwoPlayerGame}, calls to
* {@code playerMove(Player.ONE} and {@code playerMove(Player.TWO} may
* be made alternately without concern that one player's turn will not
* have ended.
* <br>
* <br>
* For methods which override this method, it is possible to override
* <i> either </i> the {@code playerOneMove()} and 
* {@code playerTwoMove()} methods <i> or </i> just the
* {@code playerMove(Player p) } method, utilizing the 
* {@code enum Player} in this package. Note, however, that the
* methods for individual player moves have been deprecated so that the
* latter option (overriding {@code playerMove(Player P) } is strongly
* preferred.
* <br>
* <br>
* In either case, it is entirely possible to invoke {@code playerMove()}
* as by default the {@code playerMove() } method invokes the two
* deprecated methods for individual players.
*
* @see Player
* @author Joel Hammer
*/
public interface TwoPlayerGame {
    /**
    * The number of players playing a two player game.
    */
    public static final int NUMBER_OF_PLAYERS = 2;
    
    /** 
    * Draws the board to the screen. This could be done using pretty 
    * {@code println()} statements or JavaFX (wow!).
    */ 
    void drawBoard();
    
   /**
    * Requests a move from the first player and makes the move.
    *
    * @throws UnsupportedOperationException if this method is not
    * overriden by the implementing class.
    * @deprecated Replaced by a call to {@code playerMove(Player.ONE) }
    */
    @Deprecated
    default void playerOneMove() throws UnsupportedOperationException { 
        throw new UnsupportedOperationException(); 
    }
    
    /**
    * Requests a move from the second player and makes the move.
    *
    * @throws UnsupportedOperationException if this method is not
    * overriden by the implementing class.
    * @deprecated Replaced by a call to {@code playerMove(Player.TWO) }
    */
    @Deprecated
    default void playerTwoMove() throws UnsupportedOperationException { 
        throw new UnsupportedOperationException(); 
    }
    
    /**
    * Requests a move from the specified player and performs the move.
    * @param p a {@code Player}, either one or two who's move it is.
    * @throws IllegalArgumentException if a player other than 1 or 2 is
    * passed.
    */
    default void playerMove(Player p) throws IllegalArgumentException {
        
        switch (p) {
            case ONE:    playerOneMove();
                                break;
            case TWO:    playerTwoMove();
                                break;
            default:            throw new IllegalArgumentException();
        }
    }
    
    /**
    * Checks to see if one of the players has won.
    * @return {@code true } if one player has won the game, 
    * {@code false} otherwise.
    */
    boolean gameIsWon();
    
    /**
    * Displays the winner of the game. This could be done by printing to the 
    * command line or in some other way.
    */
    void announceWinner();
    
    /**
    * An enumeration of all possible players in a two player game
    * (there are only two).
    */
    public enum Player {
        /**
        * The player taking the first turn.
        */
        ONE (1, "Player One"),
        /**
        * The player taking the second turn.
        */
        TWO (2, "Player Two");
        
        private int id;
        private String playerName;
        
        Player(int i, String playerName) { 
            id = i; 
            this.playerName = playerName; 
        }
        
        /**
        * The id (either 1 or 2) of a given player.
        * @return the id of the given {@code Player}
        */
        public int id() { return this.id; }
        
        /**
        * The username of a given player.
        * @return the name of the given {@code Player}
        */
        public String playerName() { return this.playerName; }
        
        /**
        * Sets the name/username of a given player. By default,
        * simply "Player One" or "Player Two".
        * @param playerName the name of the player to be assigned.
        */
        public void setName(String playerName) {
            this.playerName = playerName;
        }
    }
}

