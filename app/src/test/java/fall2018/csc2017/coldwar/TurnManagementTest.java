package fall2018.csc2017.coldwar;

import org.junit.Assert;
import org.junit.Test;

public class TurnManagementTest {
    private ColdWarGameInfo info; // create a new ColdWarGameInfo variable

    @Test
    public void testSwitchToOtherPlayerAfterEndTurn() {
        info = new ColdWarGameInfo("testUser");
        String previousPlayer = info.getCurrentPlayer();
        TurnManagementUtility.endTurn(info);

        Assert.assertNotEquals(previousPlayer, info.getCurrentPlayer());

        TurnManagementUtility.endTurn(info);

        Assert.assertEquals(previousPlayer, info.getCurrentPlayer());
    }

    @Test
    public void testChangeVisibilityWhenBeginningTurn() {
        info = BoardSetupTest.setUpTestBoard();
        // All pieces invisible

        TurnManagementUtility.beginTurn(info); // makes pieces visible
        Assert.assertTrue(info.getBoard().get(3).getAgent().isVisible());
    }
}
