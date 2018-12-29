package fall2018.csc2017.coldwar;

import org.junit.Assert;
import org.junit.Test;

import fall2018.csc2017.slidingtiles.R;

public class AgentTest {

    @Test
    public void agentsCannotMoveAtCreationAndHasCorrectOwner() {
        // Given
        String player1 = "p1";
        String player2 = "p2";

        // When
        Agent spyAgent = new Spy(player1);
        Agent diplomatAgent = new Diplomat(player2);

        // Then
        Assert.assertEquals(spyAgent.getOwner(), player1);
        Assert.assertEquals(diplomatAgent.getOwner(), player2);
        Assert.assertFalse(spyAgent.isCanMove());
        Assert.assertFalse(diplomatAgent.isCanMove());
    }

    @Test
    public void agentsHaveCorrectPicture() {
        // Given
        String player1 = "p1";
        String player2 = "p2";

        // When
        Agent spyAgent = new Spy(player2);
        Agent diplomatAgent = new Diplomat(player1);
        Agent usBase = new USBase(player1);
        Agent suBase = new SUBase(player2);

        // Then
        Assert.assertEquals(spyAgent.getPicture(), R.drawable.spy);
        Assert.assertEquals(diplomatAgent.getPicture(), R.drawable.diplomat);
        Assert.assertEquals(usBase.getPicture(), R.drawable.capitol);
        Assert.assertEquals(suBase.getPicture(), R.drawable.kremlin);
    }
}