package fall2018.csc2017.coldwar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

class BoardSetupTest {

    /**
     * Set up a sample board for testing purposes.
     */
    static ColdWarGameInfo setUpTestBoard() {
        return setUpBoardOptions("option1");
    }

    static  ColdWarGameInfo setUpTestBoard2() {
        return setUpBoardOptions("option2");
    }


    static ColdWarGameInfo setUpBoardOptions(String option) {
        PiecesSelectionManager selectionManager = new PiecesSelectionManager();
        ColdWarGameInfo info = new ColdWarGameInfo("testUser"); // create a new ColdWarGameInfo object

        // create a new agent to positions hash maps for each player
        HashMap<String, ArrayList<String>> player1AgentToPositionList = new HashMap<>();
        HashMap<String, ArrayList<String>> player2AgentToPositionList = new HashMap<>();

        ArrayList<String> player1SpyPositions = new ArrayList<>(); // String list of player 1's spy positions
        ArrayList<String> player1DiplomatPositions = new ArrayList<>(); // String list of player 1's diplomat positions
        ArrayList<String> player2SpyPositions = new ArrayList<>(); // String list of player 2's spy positions
        ArrayList<String> player2DiplomatPositions = new ArrayList<>(); // String list of player 2's diplomat positions

        if (option.equals("option1")) {
            // choose the positions
            Collections.addAll(player1SpyPositions, "E3", "A3", "A4", "A5", "B1");
            Collections.addAll(player1DiplomatPositions, "B2", "B3", "C5", "B6");
            Collections.addAll(player2SpyPositions, "F2", "F3", "F4", "F5");
            Collections.addAll(player2DiplomatPositions, "E2", "E4", "D5");
        } else {
            // choose the positions
            Collections.addAll(player1SpyPositions, "A2", "A3", "A4", "A5");
            Collections.addAll(player1DiplomatPositions, "B1", "B2", "B3", "B4");
            Collections.addAll(player2SpyPositions, "B5", "B6", "C1", "C2");
            Collections.addAll(player2DiplomatPositions, "C3", "C4", "C5", "C6");
        }


        // put the positions in hash map
        player1AgentToPositionList.put("spy", player1SpyPositions);
        player1AgentToPositionList.put("diplomat", player1DiplomatPositions);
        player2AgentToPositionList.put("spy", player2SpyPositions);
        player2AgentToPositionList.put("diplomat", player2DiplomatPositions);

        // add the positions to the board
        selectionManager.addAgents("spy", player1AgentToPositionList, info, ColdWarGameInfo.PLAYER1);
        selectionManager.addAgents("diplomat", player1AgentToPositionList, info, ColdWarGameInfo.PLAYER1);
        selectionManager.addAgents("spy", player2AgentToPositionList, info, ColdWarGameInfo.PLAYER2);
        selectionManager.addAgents("diplomat", player2AgentToPositionList, info, ColdWarGameInfo.PLAYER2);

        return info;
    }
}
