package fall2018.csc2017.coldwar;

import java.util.ArrayList;
import java.util.List;

/**
 * A utilities package for handling move-making.
 */
class MovementUtility {
    /**
     * Takes in an Integer from and Integer to and decide if they are neighbouring each other on the
     * board.
     *
     * @param from one of the positions to be compared
     * @param to   the other position to be compared
     * @return whether they are neighbouring orthogonally
     */
    private static boolean isNeighbouring(Integer from, Integer to) {

        // List of known neighbours
        List<Integer> neighbours = new ArrayList<>();

        // Add the left neighbour to neighbours, if there is one.
        if (from % 6 != 0) {
            neighbours.add(from - 1);
        }

        // Add right neighbour to neighbours, if there is one.
        if (from % 6 != 5) {
            neighbours.add(from + 1);
        }

        // Add top neighbour to neighbours, if there is one.
        if (from / 6 != 0) {
            neighbours.add(from - 6);
        }

        // Add bottom neighbour to neighbours, if there is one.
        if (from / 6 != 5) {
            neighbours.add(from + 6);
        }

        // Check if to is a neighbour
        return neighbours.contains(to);
    }

    /**
     * Takes in an Integer move and relevant information about the state of the current game and
     * decide whether move is valid.
     *
     * @param info The information about the current state of the game
     * @param from Position to move from
     * @param to   Position to move to
     * @return A boolean indicating whether the move is valid
     */
    private static boolean isValidMove(ColdWarGameInfo info, Integer from, Integer to) {

        List<Tile> board = info.getBoard();
        Agent fromOccupant = board.get(from).getAgent();
        Agent toOccupant = board.get(to).getAgent();

        // check if from is a not null
        if (fromOccupant == null) {
            return false;
        }

        // check if from piece can move
        if (!fromOccupant.isCanMove()) {
            return false;
        }

        // check if from and to are neighbouring
        if (!isNeighbouring(from, to)) {
            return false;
        }

        // check if from is owned by current player
        if (!fromOccupant.getOwner().equals(info.getCurrentPlayer())) {
            return false;
        }

        // check if diplomat is trying to steal information from base (this action is not allowed)
        if (fromOccupant instanceof Diplomat &&
                ((toOccupant instanceof SUBase | toOccupant instanceof USBase))) {
            return false;
        }

        // check if to is owned by the other player
        return (!(toOccupant != null && toOccupant.getOwner().equals(info.getCurrentPlayer())));
    }

    /**
     * Perform action on receiver, assuming receiver are not null and action is legal.
     *
     * @param info     Information about the current state of the game
     * @param receiver The Agent receiving the action
     */
    private static void performAction(ColdWarGameInfo info, Agent receiver) {
        String currentPlayer = info.getCurrentPlayer();

        // performing player loses reputation if action is performed on an enemy diplomat
        if (receiver instanceof Diplomat) {
            if (currentPlayer.equals(ColdWarGameInfo.PLAYER2)) {
                info.setPlayer2Reputation(info.getPlayer2Reputation() - 1);
            } else {
                info.setPlayer1Reputation(info.getPlayer1Reputation() - 1);
            }
        }

        // update the number of spies if a spy has been performed on
        else if (receiver instanceof Spy) {
            if (currentPlayer.equals(ColdWarGameInfo.PLAYER2)) {
                info.setPlayer1NumSpies(info.getPlayer1NumSpies() - 1);
            } else {
                info.setPlayer2NumSpies(info.getPlayer2NumSpies() - 1);
            }
        }

        // update variable isBaseInfiltrated in info if a base has been infiltrated (by a spy)
        else if (receiver instanceof USBase) {
            info.setPlayer1BaseInfiltrated(true);
        } else if (receiver instanceof SUBase) {
            info.setPlayer2BaseInfiltrated(true);
        }
    }

    /**
     * Move the agent at selectedPosition to positionToMove.
     *
     * @param info             Information about the current state of the game
     * @param selectedPosition The position of the agent to move
     * @param positionToMove   The position of where we want the given agent to move to
     * @return Whether a move was successfully made.
     */
    static boolean makeMove(ColdWarGameInfo info, int selectedPosition, int positionToMove) {
        List<Tile> board = info.getBoard();

        Agent fromOccupant = board.get(selectedPosition).getAgent();
        Agent toOccupant = board.get(positionToMove).getAgent();

        if (isValidMove(info, selectedPosition, positionToMove)) {

            // move to positionToMove
            board.get(positionToMove).setAgent(fromOccupant);
            board.get(selectedPosition).setAgent(null);

            // perform action on occupant at positionToMove if possible
            if (toOccupant != null) {
                performAction(info, toOccupant);
            }

            // set all pieces to be unmovable
            toggleMovability(info);

            // record the move made
            List<Integer> movedPositions = new ArrayList<>();
            movedPositions.add(selectedPosition);
            movedPositions.add(positionToMove);
            info.setLastMove(movedPositions);

            return true;
        }

        // return false if no valid move was made
        return false;
    }

    /**
     * Make all playable Agent pieces in info unmovable if movable and vice versa.
     *
     * @param info The game info of the current game
     */
    static void toggleMovability(ColdWarGameInfo info) {
        List<Tile> board = info.getBoard();
        for (int i = 0; i < board.size(); i++) {
            Agent occupant = board.get(i).getAgent();
            if (occupant instanceof Spy | occupant instanceof Diplomat) {
                if (occupant.isCanMove()) {
                    occupant.setCanMove(false);
                } else {
                    occupant.setCanMove(true);
                }
            }
        }
    }

    /**
     * Translates position format to coordinate format.
     * @param position
     * @return
     */
    static String positionToCoordinates(int position) {
        String row = String.valueOf(((char) (position / 6 + 65))) ;
        String col = Integer.toString(1 + (position % 6));

        return row + col;
    }

}
