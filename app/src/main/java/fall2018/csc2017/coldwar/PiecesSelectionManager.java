package fall2018.csc2017.coldwar;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;


public class PiecesSelectionManager {
    /**
     * Add agents of type agent to the positions dictated by agentToPositionList.
     *
     * @param agent               the type of agent
     * @param agentToPositionList a hash map that maps an agent type to the positions
     *                            that will contain this agent
     */
    public void addAgents(String agent, HashMap<String, ArrayList<String>> agentToPositionList, ColdWarGameInfo gameInfo, String player) {
        // get position data >> then translate to array coordinates
        List<String> agentPositions = agentToPositionList.get(agent);
        for (String agentPosition : agentPositions) {
            // make a new spy object >> then insert it to gameinfo, passing in the spy and the coordinates
            int position = getPosition(agentPosition);
            if (agent.equals("spy")) {
                Spy spy = new Spy(player);
                gameInfo.setTile(spy, position);
            } else {
                Diplomat diplomat = new Diplomat(player);
                gameInfo.setTile(diplomat, position);
            }
        }
    }

    /**
     * @param agentPosition the position that the user inputs, e.g A4
     * @return the corresponding in "array terms"
     */
    private int getPosition(String agentPosition) {
        int position;
        int coord1 = (int) agentPosition.charAt(0) - 65; // want to start the coord at A
        int coord2 = Integer.parseInt(String.valueOf(agentPosition.charAt(1))) - 1;
        position = coord1 * 6 + coord2;

        return position;
    }

    protected HashMap<String, ArrayList<String>> getAgentPositions(String player,
                                                                   ArrayList<String> spyPositions,
                                                                   ArrayList<String> diplomatPositions) {
        boolean spyValid = checkValid(spyPositions, player);
        boolean diplomatValid = checkValid(diplomatPositions, player);

        if (spyValid && diplomatValid) { // valid
            HashMap<String, ArrayList<String>> result = new HashMap<>();
            result.put("spy", spyPositions);
            result.put("diplomat", diplomatPositions);

            return result;
        }
        return null;
    }

    /**
     * @param positions list of position inputs
     * @param player    representation of player in string format
     * @return whether or not positions is valid with respect to player
     */
    private boolean checkValid(ArrayList<String> positions, String player) {
        boolean valid = checkNoDuplicatesExist(positions) && individualElementsValid(positions)
                && checkCorrectOrientation(positions, player) && !OccupyingBases(positions, player);
        return valid;
    }

    /**
     * @param positions list of position inputs
     * @return whether each individual element in positions is valid, i.e. is of length 2 and
     * contains the proper format
     */
    private boolean individualElementsValid(ArrayList<String> positions) {
        Character[] validLetters = {'A', 'B', 'C', 'D', 'E', 'F'};
        Integer[] validNumbers = {1, 2, 3, 4, 5, 6};

        // check for correct length
        for (String position : positions) {
            if (position.length() != 2) {
                return false;
            }

            // thus, length of string is 2
            boolean firstCharValid = Arrays.asList(validLetters).contains(position.charAt(0));
            boolean secondCharValid = Arrays.asList(validNumbers).contains(Integer.parseInt(String.valueOf(position.charAt(1))));
            boolean notValid = !firstCharValid || !secondCharValid;
            if (notValid) {
                return false;
            }
        }

        return true;
    }

    /**
     * @param positions list of position inputs
     * @param player    representation of player
     * @return whether each position in positions is in the correct side of the board with respect
     * to player
     */
    private boolean checkCorrectOrientation(ArrayList<String> positions, String player) {
        // if player1, need to check all frontal char for positions either E or F >> A and B for player 2
        ArrayList<String> validRowLetters = new ArrayList<>();
        if (player.equals(ColdWarGameInfo.PLAYER1)) {
            validRowLetters.add("E");
            validRowLetters.add("F");
        } else {
            validRowLetters.add("A");
            validRowLetters.add("B");
        }

        // loop though each elem in positions and make sure row letter for position is inside validRowLetters
        for (String position : positions) {
            String positionRowLetter = String.valueOf(position.charAt(0));
            if (!validRowLetters.contains(positionRowLetter)) {
                return false;
            }
        }

        return true;
    }

    /**
     * @param positions list of position inputs
     * @param player    representation of player
     * @return whether each position in input does not occupy a base for player
     */
    private boolean OccupyingBases(ArrayList<String> positions, String player) {
        String[] player1BasePositions = {"F1", "F6"};
        String[] player2BasePositions = {"A1", "A6"};
        boolean occupyFirstBase, occupySecondBase;

        if (player.equals(ColdWarGameInfo.PLAYER1)) { // player 1
            occupyFirstBase = positions.contains(player1BasePositions[0]);
            occupySecondBase = positions.contains(player1BasePositions[1]);
        } else {
            occupyFirstBase = positions.contains(player2BasePositions[0]);
            occupySecondBase = positions.contains(player2BasePositions[1]);
        }

        return occupyFirstBase || occupySecondBase;
    }

    /**
     * @param positions list of position inputs
     * @return whether there are no duplicates in positions
     */
    private boolean checkNoDuplicatesExist(ArrayList<String> positions) {
        // also need to check for repeats
        HashSet<String> noDuplicatePositions = new HashSet<>(positions);
        boolean noDuplicatesExist = noDuplicatePositions.size() == positions.size();
        return noDuplicatesExist;
    }


}
