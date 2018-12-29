package fall2018.csc2017.coldwar;

/**
 * A utilities package for handling methods related to handling game over.
 */
public class GameOverUtility {

    final static private String player1 = ColdWarGameInfo.PLAYER1;
    final static private String player2 = ColdWarGameInfo.PLAYER2;

    /**
     * Message for scenario where Player 2 victory due to Player 1 losing all reputation.
     */
    final static private String player2WinReputation =
            "Due to the murder of multiple legitimate diplomats by User's country, public " +
                    "opinion and UN sanctions have led to the toppling of User's government, " +
                    "ending a long and bitter cold war between the world's two superpowers. " +
                    "Guest wins!";

    /**
     * Message for scenario where Player 1 victory due to Player 1 losing all reputation.
     */
    final static private String player1WinReputation =
            "Due to the murder of multiple legitimate diplomats by Guest's country, " +
                    "public opinion and UN sanctions have led to the toppling of Guest's " +
                    "government, ending a long and bitter cold war between the world's two " +
                    "superpowers. User wins!";

    /**
     * Message for scenario where Player 2 victory due to Player 1 losing all spies.
     */
    final static private String player2WinSpies =
            "All spies in User's country have been eradicated, allowing Guest's country's " +
                    "technological prowess to quickly dwarf that of User's. There now remains" +
                    " one sole superpower. Guest wins!";

    /**
     * Message for scenario where Player 1 victory due to Player 2 losing all spies.
     */
    final static private String player1WinSpies =
            "All spies in Guest's country have been eradicated, allowing User's country's " +
                    "technological prowess to quickly dwarf that of Guest's. There now remains" +
                    " one sole superpower. User wins!";

    /**
     * Message for scenario where Player 2 victory due to Player 1 base being infiltrated.
     */
    final static private String player2WinInfiltration =
            "User's capital has been infiltrated by Guest's spies. All secrets have been " +
                    "stolen, rendering User defenseless against Guest. Guest wins!";

    /**
     * Message for scenario where Player 1 victory due to Player 2 base being infiltrated.
     */
    final static private String player1WinInfiltration =
            "Guest's capital has been infiltrated by User's spies. All secrets have been " +
                    "stolen, rendering Guest defenseless against User. User wins!";

    /**
     * Determines whether the game in its current state is over.
     *
     * @return whether the game is over.
     */
    public static boolean isOver(ColdWarGameInfo info) {
        return (info.getPlayer1Reputation().equals(0) | info.getPlayer2Reputation().equals(0) |
                info.isPlayer1BaseInfiltrated() | info.isPlayer2BaseInfiltrated() |
                info.getPlayer1NumSpies().equals(0) | info.getPlayer2NumSpies().equals(0));
    }

    /**
     * Determine and return the win text when game is ended. Win text is determined by the how the
     * game was won.
     *
     * @param info Information about the current game
     * @return A String of text that describes the end game scenario.
     */
    static String getWinText(ColdWarGameInfo info) {
        if (info.getPlayer1Reputation().equals(0)) {
            return player2WinReputation;
        } else if (info.getPlayer2Reputation().equals(0)) {
            return player1WinReputation;
        } else if (info.getPlayer1NumSpies().equals(0)) {
            return player2WinSpies;
        } else if (info.getPlayer2NumSpies().equals(0)) {
            return player1WinSpies;
        } else if (info.isPlayer1BaseInfiltrated()) {
            return player2WinInfiltration;
        } else {
            return player1WinInfiltration;
        }
    }

    /**
     * Return the winner of the game, assuming the game is over.
     *
     * @param info Information about the current game
     * @return The winner of the current game as a string.
     */
    static String getWinner(ColdWarGameInfo info) {
        if (info.getPlayer1Reputation().equals(0) | info.getPlayer1NumSpies().equals(0) |
                info.isPlayer1BaseInfiltrated()) {
            return player2;
        } else {
            return player1;
        }
    }
}
