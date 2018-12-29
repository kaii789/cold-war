package fall2018.csc2017.coldwar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import fall2018.csc2017.slidingtiles.R;
import generalactivities.ScoreBoardActivity;
import generalclasses.GameScoreboards;
import generalclasses.ScoreBoard;
import generalclasses.User;

public class ColdWarScoreBoardActivity extends ScoreBoardActivity {

    User user;
    private TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cold_war_score_board);


        // load the scoreboards from save file//
        loadScoreboards("COLD_WAR_SAVED_SCOREBOARDS");
        user = (User) getIntent().getSerializableExtra("user");
        if (scoreboards == null) {
            scoreboards = new GameScoreboards();
        }
        final ScoreBoard scoreboard = scoreboards.getScoreboard("default");
        if (scoreboard != null) {
            scores = scoreboard.getScoreMap();
        }


        // Getting the Buttons and display
        Button globalButton = findViewById(R.id.globalButton);
        Button localButton = findViewById(R.id.localButton);
        display = findViewById(R.id.display);

        // Assigning the button to display global rankings (all users scores and rankings)
        globalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (scoreboard != null) {
                    displayGlobalRankings();
                }
                display.setText("Global Rankings");
            }
        });

        // Assigning the button to display local rankings depending on which user is logged in
        localButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (scoreboard != null) {
                    if (scores.containsKey(user.getName())) {
                        displayLocalRankings(user.getName());
                    } else {
                        displayBlankRankings();
                    }
                }
                display.setText("Local Rankings");
            }
        });
    }
}
