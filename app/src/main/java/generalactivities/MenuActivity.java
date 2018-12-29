package generalactivities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import fall2018.csc2017.slidingtiles.R;

/**
 * Main menu of the app
 */
public class MenuActivity extends AppCompatActivity {

    /*
    The currentUser variable keeps track of the current user interacting with this activity.
     */
    private String username;
    private Button logout;
    private Button games;
    private Button setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        assignments();
        addLogOutButtonListener();
        addGamesButtonListener();
        addSettingButtonListener();
    }

    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
        int bg = sharedPref.getInt("background_resources", android.R.color.white); // the second parameter will be fallback if the preference is not found
        getWindow().setBackgroundDrawableResource(bg);

    }

    /**
     * Find views by ids for resources in the view
     */
    private void assignments() {
        username = getIntent().getStringExtra("username");
        logout = findViewById(R.id.logout);
        games = findViewById(R.id.games);
        setting = findViewById(R.id.setting);

    }

    /**
     * Listener for logout button
     */
    private void addLogOutButtonListener() {
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Listener for game button
     */
    private void addGamesButtonListener() {
        games.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, GameSelectionActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });
    }

    private void addSettingButtonListener() {
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, SettingActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });
    }

    public void changeBackGround(){
        findViewById(R.id.menus).setBackgroundResource(R.drawable.tile_1);
    }


}
