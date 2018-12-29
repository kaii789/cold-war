package fall2018.csc2017.coldwar;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.zip.CheckedOutputStream;

import generalclasses.GameInfo;
import generalclasses.ScoreBoard;

/**
 * The model under the MVC model for the Cold War game. It contains all relevant information regarding
 * the current state of the game, including the location of each piece, the number of remaining pieces
 * owned by each owner, etc.
 */
public class ColdWarGameInfo extends GameInfo {

    private Integer STARTING_REPUTATION = 4;

    static final String PLAYER1 = "p1";
    static final String PLAYER2 = "p2";

    private Integer userScore = 0;

    /**
     * A list of two integers used to store the last move made.
     * For example, if player moved a piece from 5 to 6, then the list would contain the integers
     * 5 and 6.
     */
    private List<Integer> lastMove;

    void setLastMove(List<Integer> lastMove) {
        this.lastMove = lastMove;
    }

    /**
     * @return A human-readable string that informs the current player about the last player's move.
     */
     String getLastMoveString() {
        if (lastMove != null) {
            String fromCoordinate = MovementUtility.positionToCoordinates(lastMove.get(0));
            String toCoordinate = MovementUtility.positionToCoordinates(lastMove.get(1));
            return "Your opponent moved a piece from " + fromCoordinate
                    + " to " + toCoordinate;
        }
        return "";
    }

    /**
     * Used to determine whether the game is over.
     */
    private boolean isPlayer1BaseInfiltrated = false;

    /**
     * Used to determine whether the game is over.
     */
    private boolean isPlayer2BaseInfiltrated = false;

    boolean isPlayer1BaseInfiltrated() {
        return isPlayer1BaseInfiltrated;
    }

    void setPlayer1BaseInfiltrated(boolean player1BaseInfiltrated) { // intelliJ warning incorrect
        isPlayer1BaseInfiltrated = player1BaseInfiltrated;
    }

    boolean isPlayer2BaseInfiltrated() {
        return isPlayer2BaseInfiltrated;
    }

    void setPlayer2BaseInfiltrated(boolean player2BaseInfiltrated) { // intelliJ warning incorrect
        isPlayer2BaseInfiltrated = player2BaseInfiltrated;
    }

    /**
     * A list of tiles to represent the current state of the game board.
     */
    private List<Tile> board;

    /**
     * The "International Reputation" of the signed in user. Used by the win/lose condition.
     */
    private Integer Player1Reputation = STARTING_REPUTATION;

    /**
     * The "International Reputation" of the guest user. Used by the win/lose condition.
     */
    private Integer Player2Reputation = STARTING_REPUTATION;

    /**
     * The number of spies of the signed in user. Used by the win/lose condition.
     */
    private Integer Player1NumSpies = 4;

    /**
     * The number of spies of the guest user. Used by the win/los condition.
     */
    private Integer Player2NumSpies = 4;

    Integer getPlayer1NumSpies() {
        return Player1NumSpies;
    }

    void setPlayer1NumSpies(Integer player1NumSpies) {
        Player1NumSpies = player1NumSpies;
    }

    Integer getPlayer2NumSpies() {
        return Player2NumSpies;
    }

    void setPlayer2NumSpies(Integer player2NumSpies) {
        Player2NumSpies = player2NumSpies;
    }


    /**
     * The username of the User that owns this ColdWarGameInfo
     */
    private String userName;

    /**
     * The current player.
     */
    private String currentPlayer = PLAYER1; // set to PLAYER1 by default.

    String getCurrentPlayer() {
        return currentPlayer;
    }

    void setCurrentPlayer(String currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public List<Tile> getBoard() {
        return this.board;
    }

    Integer getPlayer1Reputation() {
        return this.Player1Reputation;
    }

    Integer getPlayer2Reputation() {
        return this.Player2Reputation;
    }

    void setPlayer1Reputation(int player1Reputation) {
        this.Player1Reputation = player1Reputation;
    }

    void setPlayer2Reputation(int player2Reputation) {
        this.Player2Reputation = player2Reputation;
    }

    public ColdWarGameInfo(String userName) {
        this.userName = userName;
        board = new ArrayList<>();
        setUpBlankBoard();
    }

    public String getUserName() {
        return userName;
    }

    /**
     * Set up a blank board with bases positioned correctly and no pieces.
     */
    private void setUpBlankBoard() {
        for (int i = 0; i < 36; i++) {
            Tile newTile = new Tile(null);
            board.add(newTile);
        }
        // set up the bases
        board.get(0).setAgent(new SUBase(PLAYER2));
        board.get(5).setAgent(new SUBase(PLAYER2));
        board.get(30).setAgent(new USBase(PLAYER1));
        board.get(35).setAgent(new USBase(PLAYER1));
    }

    /**
     * Set agent to the tile at position.
     *
     * @param agent    The agent to set
     * @param position The position to set
     */
    void setTile(Agent agent, int position) {
        Tile tileToSet = this.board.get(position);
        tileToSet.setAgent(agent);
    }

    public int getScore(ScoreBoard scoreBoard) {
        // we get to assume game is over
        LinkedHashMap<String, ArrayList<Integer>> scoreMap = scoreBoard.getScoreMap();
        if (scoreMap.containsKey(userName)) {
            userScore = scoreMap.get(userName).get(0);
        }

        updateScore();
        return getScore();
    }

    @Override
    public int getScore() {
        return userScore;
    }

    @Override
    public void updateScore() {
        if (GameOverUtility.isOver(this)) {
            if (GameOverUtility.getWinner(this).equals(PLAYER1)) {
                userScore += 2;
            } else if (userScore > 0) {
                userScore -= 1;
            }
        }
    }

    @Override
    public String getGame() {
        return "Cold War";
    }
}