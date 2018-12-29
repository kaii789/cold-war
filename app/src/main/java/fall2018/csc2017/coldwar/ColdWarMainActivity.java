package fall2018.csc2017.coldwar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import fall2018.csc2017.slidingtiles.R;
import generalclasses.GameScoreboards;
import generalclasses.ScoreBoard;
import generalclasses.User;

/**
 * The main view under the MVC model for the Cold War game. It contains what is shown to users.
 */
public class ColdWarMainActivity extends AppCompatActivity {

    private GridView gridView;
    private TextView userReputationText;
    private TextView guestReputationText;
    private TextView selectedPositionText;
    private TextView lastMoveText;
    private Button endButton, readyButton, saveButton;

    static int selectedPosition = -1; // this is "unselected" by default
    private ColdWarGameInfo coldWarGameInfo;
    private ColdWarSaverModel mSaver;
    private ColdWarGameController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cold_war_main);

        assignments();
        setUpDisplays();
        setUpGridView();

    }

    /**
     * Set up displays related to game information that needs to be displayed to the players.
     */
    private void setUpDisplays() {
        // set up some displays
        String guestReputationString = "Guest Global Reputation: " +
                coldWarGameInfo.getPlayer2Reputation().toString();
        String userReputationString = "User Global Reputation: " +
                coldWarGameInfo.getPlayer1Reputation().toString();
        String selectedPositionString = "";
        String lastMoveString = "";
        guestReputationText.setText(guestReputationString);
        userReputationText.setText(userReputationString);
        selectedPositionText.setText(selectedPositionString);
        lastMoveText.setText(lastMoveString);
    }

    /**
     * Set up the grid view that acts as the visualization of the playing board.
     */
    private void setUpGridView() {
        gridView = findViewById(R.id.coldWarGridView);
        gridView.setAdapter(new ImageAdapterGridView(this, ColdWarGameController.getImageIDs(coldWarGameInfo)));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                controller.setViews(endButton, guestReputationText, userReputationText,
                        selectedPositionText);
                if (!controller.touchMove(coldWarGameInfo, selectedPosition, position))  {
                    Toast.makeText(ColdWarMainActivity.this, "Invalid Move", Toast.LENGTH_SHORT).show();
                }
                selectedPosition = controller.getSelectedPosition();
                controller.updateGridView(gridView, coldWarGameInfo);
            }
        });
    }

    /**
     * Make appropriate assignments for the game play view.
     */
    private void assignments() {
        endButton = findViewById(R.id.endTurnButton);
        readyButton = findViewById(R.id.beginMoveButton);
        saveButton = findViewById(R.id.saveButton);

        Intent intent = getIntent();
        coldWarGameInfo = (ColdWarGameInfo) intent.getSerializableExtra("gameInfo");

        mSaver = new ColdWarSaverModel(this);

        userReputationText = findViewById(R.id.userReputation);
        guestReputationText = findViewById(R.id.guestReputation);
        selectedPositionText = findViewById(R.id.selectedPositionText);
        lastMoveText = findViewById(R.id.lastMoveText);

        controller = new ColdWarGameController(this);

    }

    public void endTurnButtonClicked(View view) {
        TurnManagementUtility.endTurn(coldWarGameInfo);
        List<Integer> imageIDs = ColdWarGameController.getImageIDs(coldWarGameInfo);
        gridView.setAdapter(new ImageAdapterGridView(getBaseContext(), imageIDs));

        // disable end button to prevent player from ending turn before making a move
        endButton.setEnabled(false);
        readyButton.setEnabled(true);
        saveButton.setEnabled(true);
        executeWhenGameOver();

        // reset lastMoveText
        lastMoveText.setText("");

        // autosave
        User user = User.usernameToUser.get(coldWarGameInfo.getUserName());
        mSaver.autoSave(coldWarGameInfo, user);
    }

    /**
     * Check if game over and initiate appropriate Game Over sequence if needed.
     */
    private void executeWhenGameOver() {
        if (GameOverUtility.isOver(coldWarGameInfo)) {
            saveScoreBoardIfGameOver();
            String message = GameOverUtility.getWinText(coldWarGameInfo);
            showAlert(message);
        }
    }

    /**
     * Show a popup with message.
     *
     * @param message Message to show
     */
    public void showAlert(String message) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        TextView myMessage = new TextView(this);
        myMessage.setText(message);
        myMessage.setTextSize(20);
        myMessage.setGravity(Gravity.CENTER_HORIZONTAL);
        dialog.setView(myMessage);
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                finish(); // exit the activity when game is over/**/
            }
        });
        dialog.show();
    }

    /**
     * Save the game information of the current game to the scoreboard if game is over.
     */
    private void saveScoreBoardIfGameOver() {
        mSaver.loadScoreboards("COLD_WAR_SAVED_SCOREBOARDS");
        // get the correct scoreboard
        GameScoreboards gameScoreboards = mSaver.getScoreboards();
        ScoreBoard scoreBoard = gameScoreboards.getScoreboard("default");
        String userName = coldWarGameInfo.getUserName();
        int currentScore = coldWarGameInfo.getScore(scoreBoard);

        // update scoreboard with latest score, then save
        scoreBoard.addUserAndScore(userName, currentScore);
        gameScoreboards.addScoreboard("default", scoreBoard);
        mSaver.saveScoreboards(gameScoreboards, "COLD_WAR_SAVED_SCOREBOARDS");
    }

    public void readyButtonClicked(View view) {
        TurnManagementUtility.beginTurn(coldWarGameInfo);
        List<Integer> imageIDs = ColdWarGameController.getImageIDs(coldWarGameInfo);
        gridView.setAdapter(new ImageAdapterGridView(getBaseContext(), imageIDs));

        // disable ready button to prevent player from initiating ready move sequence repeatedly
        readyButton.setEnabled(false);
        // disable save button to make it impossible to save when pieces are visible
        saveButton.setEnabled(false);

        // tell the current player his/her opponent's last move
        lastMoveText.setText(coldWarGameInfo.getLastMoveString());
    }

    public void save(View view) {
        // determine current user
        User user = User.usernameToUser.get(coldWarGameInfo.getUserName());
        mSaver.manualSave(coldWarGameInfo, user);
    }
}