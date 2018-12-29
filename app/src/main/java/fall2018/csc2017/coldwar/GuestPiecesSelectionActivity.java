package fall2018.csc2017.coldwar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import fall2018.csc2017.slidingtiles.R;

public class GuestPiecesSelectionActivity extends PieceSelectionActivity {

    ColdWarGameInfo gameInfo;
    PiecesSelectionManager selectionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_pieces_selection);
        assignments();
        addSubmitListener();
    }

    private void assignments() {
        Intent intent = getIntent();
        gameInfo = (ColdWarGameInfo) intent.getSerializableExtra("gameInfo");
        selectionManager = new PiecesSelectionManager();
    }

    public void addSubmitListener() {
        Button submit = findViewById(R.id.submitP2);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GuestPiecesSelectionActivity.this, ColdWarMainActivity.class);

                // get all data from the edit texts >> these data are coordinates of the board
                HashMap<String, ArrayList<String>> agentToPositionList =
                        selectionManager.getAgentPositions(gameInfo.PLAYER2, getSpyPositions(), getDiplomatPositions());

                if (agentToPositionList != null) {
                    // add the spies to the board
                    selectionManager.addAgents("spy", agentToPositionList, gameInfo, gameInfo.PLAYER2);
                    selectionManager.addAgents("diplomat", agentToPositionList, gameInfo, gameInfo.PLAYER2);

                    intent.putExtra("gameInfo", gameInfo);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(GuestPiecesSelectionActivity.this, "Please enter valid inputs", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
