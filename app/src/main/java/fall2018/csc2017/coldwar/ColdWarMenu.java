package fall2018.csc2017.coldwar;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import java.util.HashMap;

import fall2018.csc2017.slidingtiles.R;
import generalclasses.GameInfo;
import generalclasses.GameScoreboards;
import generalclasses.ScoreBoard;
import generalclasses.User;

public class ColdWarMenu extends AppCompatActivity {

    String username;
    User user;
    ColdWarSaverModel mSaver;
    GameScoreboards scoreboards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cold_war_menu2);
        assignments();
        setTheme();
    }

    private void assignments() {
        username = getIntent().getStringExtra("username");
        user = User.usernameToUser.get(username);
        mSaver = new ColdWarSaverModel(this);
    }

    public void startGame(View view) {
        // if this is the very first game, need to add
        if (scoreboards.getScoreboard("default") == null) {
            scoreboards.addScoreboard("default", new ScoreBoard());
            mSaver.saveScoreboards(scoreboards, "COLD_WAR_SAVED_SCOREBOARDS");
        }
        Intent intent = new Intent(this, UserPiecesSelectionActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }

    public void loadGame(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(ColdWarMenu.this);
        alert.setTitle("Select your save");
        alert.setIcon(android.R.drawable.ic_dialog_alert);

        final HashMap<String, GameInfo> saves = user.getSavesForGame("Cold War");
        final String[] saveNames = saves.keySet().toArray(new String[saves.keySet().size()]);
        alert.setItems(saveNames, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                Intent intent = new Intent(ColdWarMenu.this, ColdWarMainActivity.class);
                String nameOfSaveToLoad = saveNames[i];
                ColdWarGameInfo saveToLoad = (ColdWarGameInfo) saves.get(nameOfSaveToLoad);
                intent.putExtra("gameInfo", saveToLoad);
                startActivity(intent);
            }
        });
        alert.show();
    }

    public void loadScoreboard(View view) {
        Intent intent = new Intent(this, ColdWarScoreBoardActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    public void instructions(View view) {
        Intent intent = new Intent(this, InstructionActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // load most updated copy of user
        mSaver.startingResume(username);
        user = mSaver.getUser();
        // also load the most recent copy of scores
        scoreboards = mSaver.getScoreboards();
    }

    private void setTheme() {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
        // the second parameter will be fallback if the preference is not found
        int bg = sharedPref.getInt("background_resources", android.R.color.white);
        getWindow().setBackgroundDrawableResource(bg);
    }
}
