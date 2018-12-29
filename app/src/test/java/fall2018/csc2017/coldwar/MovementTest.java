package fall2018.csc2017.coldwar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MovementTest {
    private ColdWarGameInfo info; // create a new ColdWarGameInfo variable

    @Before
    public void setUpBoard(){
        info = BoardSetupTest.setUpTestBoard();
    }

    @Test
    public void testMoveToNeighbour() {
        MovementUtility.toggleMovability(info);

        Integer from1 = 8, to1 = 9; // neighbouring on the same row
        Integer from2 = 11, to2 = 9; // not neighbouring on the same row
        Integer from3 = 7, to3 = 1; // neighbouring on different row
        Integer from4 = 6, to4 = 18; // not neighbouring on different row

        Assert.assertTrue(MovementUtility.makeMove(info, from1, to1));
        MovementUtility.toggleMovability(info); // need to make piece movable again, since true

        Assert.assertFalse(MovementUtility.makeMove(info, from2, to2));

        Assert.assertTrue(MovementUtility.makeMove(info, from3, to3));
        MovementUtility.toggleMovability(info); // need to make piece movable again, since true

        Assert.assertFalse(MovementUtility.makeMove(info, from4, to4));
    }

    @Test
    public void testMovable() {
        MovementUtility.toggleMovability(info); // make pieces movable (they are not by default)

        Integer from1 = 11, to1 = 10; // move a piece owned by player 1
        Integer from2 = 8, to2 = 9; // move a piece owned by player 1
        Integer from3 = 14, to3 = 15; // move an empty tile

        // since it is player 1's turn, this should be legal
        Assert.assertTrue(MovementUtility.makeMove(info, from1, to1));

        // since a move was made, it is now player 2's turn, this should not be legal
        Assert.assertFalse(MovementUtility.makeMove(info, from2, to2));

        // since tile is empty, there is no piece to move, so this is illegal
        Assert.assertFalse(MovementUtility.makeMove(info, from3, to3));
    }

    @Test
    public void testPerformAction() {
        MovementUtility.toggleMovability(info); // make pieces movable (they are not by default)

        Integer from1 = 16, to1 = 22; // piece legally perform action on diplomat
        Integer from2 = 11, to2 = 5; // diplomat illegally perform action on base
        Integer from3 = 7, to3 = 8; // piece illegally perform action on friendly
        Integer from4 = 26, to4 = 32; // piece legally perform action on spy
        Integer from5 = 6, to5 = 0; // spy legally perform action on base

        Assert.assertTrue(MovementUtility.makeMove(info, from1, to1));
        MovementUtility.toggleMovability(info);

        Assert.assertFalse(MovementUtility.makeMove(info, from2, to2));

        Assert.assertFalse(MovementUtility.makeMove(info, from3, to3));

        Assert.assertTrue(MovementUtility.makeMove(info, from4, to4));
        MovementUtility.toggleMovability(info);

        Assert.assertTrue(MovementUtility.makeMove(info, from5, to5));
        MovementUtility.toggleMovability(info);
    }
}
