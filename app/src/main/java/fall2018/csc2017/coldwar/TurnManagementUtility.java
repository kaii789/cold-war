package fall2018.csc2017.coldwar;

import java.util.List;

/**
 * A utilities package for handling tasks related to beginning, executing, and ending turns.
 */
class TurnManagementUtility {
    /**
     * Makes all pieces in info movable and makes visibility anonymous for all. Switch current player
     * to other player.
     *
     * @param info The game info of the current game
     */
    static void endTurn(ColdWarGameInfo info) {
        String currentPlayer = info.getCurrentPlayer();

        makeInvisible(info);

        if (currentPlayer.equals(ColdWarGameInfo.PLAYER1)) {
            info.setCurrentPlayer(ColdWarGameInfo.PLAYER2);
        } else {
            info.setCurrentPlayer(ColdWarGameInfo.PLAYER1);
        }
    }

    /**
     * Make visibility visible if piece is owned by current player.
     *
     * @param info The game info of the current game
     */
    static void beginTurn(ColdWarGameInfo info) {

        // Allow pieces to be movable
        MovementUtility.toggleMovability(info);
        makeVisible(info);
    }


    /**
     * Make all pieces visible.
     *
     * @param info The game info of the current game
     */
    static private void makeVisible(ColdWarGameInfo info) {
        List<Tile> board = info.getBoard();
        for (int i = 0; i < board.size(); i++) {
            Agent occupant = board.get(i).getAgent();
            if (!(occupant == null)) {
                occupant.setVisible(true);
            }
        }
    }

    /**
     * Make all pieces invisible.
     *
     * @param info The game info of the current game
     */
    static private void makeInvisible(ColdWarGameInfo info) {
        List<Tile> board = info.getBoard();
        for (int i = 0; i < board.size(); i++) {
            Agent occupant = board.get(i).getAgent();
            if (!(occupant == null)) {
                occupant.setVisible(false);
            }
        }
    }
}
