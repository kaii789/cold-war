package fall2018.csc2017.coldwar;

import android.content.Context;
import android.widget.Button;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fall2018.csc2017.slidingtiles.R;

import static org.junit.Assert.*;

public class ColdWarGameControllerTest {
    ColdWarMainActivity mainActivity;
    ColdWarGameController controller;
    ColdWarGameInfo gameInfo;

    Button button;
    TextView guestText;
    TextView userText;
    TextView positionText;

    @Before
    public void setUp() {
        button = Mockito.mock(Button.class);
        guestText = Mockito.mock(TextView.class);
        userText = Mockito.mock(TextView.class);
        positionText = Mockito.mock(TextView.class);

        mainActivity = Mockito.mock(ColdWarMainActivity.class);
        Context context = mainActivity.getBaseContext();
        controller = new ColdWarGameController(context);
        controller.setViews(button, guestText, userText,
                positionText);
        gameInfo = BoardSetupTest.setUpTestBoard();
    }


    @Test
    public void testTouchMoveSelectCorrectPosition() {
        setUp();
        controller.touchMove(gameInfo, -1, 2);
        assertEquals(2, controller.getSelectedPosition());
    }

    @Test
    public void testTouchMoveMakeInvalidMove() {
        setUp();
        controller.touchMove(gameInfo, 0, 10);
        assertEquals(false, button.isClickable());
    }

    @Test
    public void getImageIDs() {
        setUp();
        gameInfo = BoardSetupTest.setUpTestBoard2();
        makeVisibleandSetOwnerToP1();

        // make the expected list of imageIDs
        List<Integer> imageIDs = new ArrayList<>();
        imageIDs.add(R.drawable.kremlin);
        Collections.addAll(imageIDs, R.drawable.spy, R.drawable.spy, R.drawable.spy, R.drawable.spy);
        imageIDs.add(R.drawable.kremlin);
        Collections.addAll(imageIDs, R.drawable.diplomat, R.drawable.diplomat, R.drawable.diplomat, R.drawable.diplomat);
        Collections.addAll(imageIDs, R.drawable.spy, R.drawable.spy, R.drawable.spy, R.drawable.spy);
        Collections.addAll(imageIDs, R.drawable.diplomat, R.drawable.diplomat, R.drawable.diplomat, R.drawable.diplomat);
        for (int i = imageIDs.size(); i < 36; i++) {
            imageIDs.add(R.drawable.cold_war_blank_tile);
        }
        imageIDs.add(30, R.drawable.capitol);
        imageIDs.add(35, R.drawable.capitol);
        imageIDs.remove(36);
        imageIDs.remove(36);

//        assertEquals(R.drawable.spy, R.drawable.capitol);
        assertEquals(imageIDs, controller.getImageIDs(gameInfo));
    }

    private void makeVisibleandSetOwnerToP1() {
        for (Tile tile : gameInfo.getBoard()) {
            if (tile.getAgent() != null ) {
                tile.getAgent().setOwner("p1");
                tile.getAgent().setVisible(true);
            }
        }
    }


}