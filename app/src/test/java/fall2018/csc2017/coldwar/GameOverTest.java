package fall2018.csc2017.coldwar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GameOverTest {
    private ColdWarGameInfo info; // Create a new ColdWarGameInfo variable

    @Before
    public void setUpBoard(){
        info = BoardSetupTest.setUpTestBoard();
    }

    @Test
    public void testIsGameOver() {
        Assert.assertFalse(GameOverUtility.isOver(info));

        info.setPlayer2BaseInfiltrated(true);
        Assert.assertTrue(GameOverUtility.isOver(info));
        info = BoardSetupTest.setUpTestBoard(); // reset board

        info.setPlayer1BaseInfiltrated(true);
        Assert.assertTrue(GameOverUtility.isOver(info));
        info = BoardSetupTest.setUpTestBoard(); // reset board

        info.setPlayer2NumSpies(0);
        Assert.assertTrue(GameOverUtility.isOver(info));
        info = BoardSetupTest.setUpTestBoard(); // reset board

        info.setPlayer1NumSpies(0);
        Assert.assertTrue(GameOverUtility.isOver(info));
        info = BoardSetupTest.setUpTestBoard(); // reset board

        info.setPlayer1Reputation(0);
        Assert.assertTrue(GameOverUtility.isOver(info));
        info = BoardSetupTest.setUpTestBoard(); // reset board

        info.setPlayer2Reputation(0);
        Assert.assertTrue(GameOverUtility.isOver(info));
        info = BoardSetupTest.setUpTestBoard(); // reset board
    }

    @Test
    public void testWinText(){
        info.setPlayer2BaseInfiltrated(true);
        String scenario1Text = GameOverUtility.getWinText(info);
        info = BoardSetupTest.setUpTestBoard(); // reset board

        info.setPlayer1BaseInfiltrated(true);
        String scenario2Text = GameOverUtility.getWinText(info);
        info = BoardSetupTest.setUpTestBoard(); // reset board

        info.setPlayer2NumSpies(0);
        String scenario3Text = GameOverUtility.getWinText(info);
        info = BoardSetupTest.setUpTestBoard(); // reset board

        info.setPlayer1NumSpies(0);
        String scenario4Text = GameOverUtility.getWinText(info);
        info = BoardSetupTest.setUpTestBoard(); // reset board

        info.setPlayer1Reputation(0);
        String scenario5Text = GameOverUtility.getWinText(info);
        info = BoardSetupTest.setUpTestBoard(); // reset board

        info.setPlayer2Reputation(0);
        String scenario6Text = GameOverUtility.getWinText(info);
        info = BoardSetupTest.setUpTestBoard(); // reset board

        boolean allTextsDifferent = false;

        if (! scenario1Text.equals(scenario2Text)) {
            if (! scenario2Text.equals(scenario3Text)) {
                if (! scenario3Text.equals(scenario4Text)) {
                    if (! scenario4Text.equals(scenario5Text)) {
                        if (! scenario5Text.equals(scenario6Text)) {
                            allTextsDifferent = true;
                        }
                    }
                }
            }
        }
        Assert.assertTrue(allTextsDifferent);
    }

    @Test
    public void testCorrectWinner() {
        Assert.assertFalse(GameOverUtility.isOver(info));

        info.setPlayer2BaseInfiltrated(true);
        Assert.assertTrue(GameOverUtility.getWinner(info).equals(ColdWarGameInfo.PLAYER1));
        info = BoardSetupTest.setUpTestBoard(); // reset board

        info.setPlayer1BaseInfiltrated(true);
        Assert.assertTrue(GameOverUtility.getWinner(info).equals(ColdWarGameInfo.PLAYER2));
        info = BoardSetupTest.setUpTestBoard(); // reset board

        info.setPlayer2NumSpies(0);
        Assert.assertTrue(GameOverUtility.getWinner(info).equals(ColdWarGameInfo.PLAYER1));
        info = BoardSetupTest.setUpTestBoard(); // reset board

        info.setPlayer1NumSpies(0);
        Assert.assertTrue(GameOverUtility.getWinner(info).equals(ColdWarGameInfo.PLAYER2));
        info = BoardSetupTest.setUpTestBoard(); // reset board

        info.setPlayer1Reputation(0);
        Assert.assertTrue(GameOverUtility.getWinner(info).equals(ColdWarGameInfo.PLAYER2));
        info = BoardSetupTest.setUpTestBoard(); // reset board

        info.setPlayer2Reputation(0);
        Assert.assertTrue(GameOverUtility.getWinner(info).equals(ColdWarGameInfo.PLAYER1));
        info = BoardSetupTest.setUpTestBoard(); // reset board
    }
}
