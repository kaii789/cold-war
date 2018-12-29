package fall2018.csc2017.coldwar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import fall2018.csc2017.slidingtiles.R;
import generalclasses.GameInfo;

public class UserPiecesSelectionActivity extends PieceSelectionActivity {

    ColdWarGameInfo gameInfo;
    PiecesSelectionManager selectionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_pieces_selection);
        assignments();
        addSubmitListener();
    }

    private void assignments() {
        gameInfo = new ColdWarGameInfo(getIntent().getStringExtra("username"));
        selectionManager = new PiecesSelectionManager();
    }

    public void addSubmitListener() {
        Button submit = findViewById(R.id.submitP1);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserPiecesSelectionActivity.this, GuestPiecesSelectionActivity.class);

                // get all data from the edit texts >> these data are coordinates of the board
                HashMap<String, ArrayList<String>> agentToPositionList =
                        selectionManager.getAgentPositions(gameInfo.PLAYER1, getSpyPositions(), getDiplomatPositions());

                if (agentToPositionList != null) {
                    // add the spies to the board
                    selectionManager.addAgents("spy", agentToPositionList, gameInfo, gameInfo.PLAYER1);
                    selectionManager.addAgents("diplomat", agentToPositionList, gameInfo, gameInfo.PLAYER1);

                    intent.putExtra("gameInfo", gameInfo);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(UserPiecesSelectionActivity.this, "Please enter valid inputs", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

