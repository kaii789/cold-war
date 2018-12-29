package generalactivities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


import fall2018.csc2017.slidingtiles.R;

/**
 * cited from: https://stackoverflow.com/questions/27338691/changing-background-of-an-activity-from-another-activity
 * Theme setting menu
 */
public class SettingActivity extends AppCompatActivity {
    private Button theme0;
    private Button theme1;
    private Button theme2;
    private Button theme3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        theme0 = findViewById(R.id.theme0);
        theme1 = findViewById(R.id.theme1);
        theme2 = findViewById(R.id.theme2);
        theme3 = findViewById(R.id.theme3);
        addthemeZeroButtonListener();
        addthemeOneButtonListener();
        addthemeTwoButtonListener();
        addthemeThreeButtonListener();
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
        int bg = sharedPref.getInt("background_resources", android.R.color.white);
        getWindow().setBackgroundDrawableResource(bg);
    }

    /**
     * Listener for default theme
     */
    private void addthemeZeroButtonListener() {
        theme0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("background_resources", android.R.color.white);
                editor.apply();
                findViewById(R.id.setting_menu).setBackgroundResource(android.R.color.white);
            }
        });
    }

    /**
     * Listener for theme 1
     */
    private void addthemeOneButtonListener() {
        theme1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("background_resources", R.drawable.theme_1);
                editor.apply();
                findViewById(R.id.setting_menu).setBackgroundResource(R.drawable.theme_1);
            }
        });
    }

    /**
     * Listener for theme 2
     */
    private void addthemeTwoButtonListener() {
        theme2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("background_resources", R.drawable.theme_2);
                editor.apply();
                findViewById(R.id.setting_menu).setBackgroundResource(R.drawable.theme_2);
            }
        });
    }

    /**
     * Listener for theme three
     */
    private void addthemeThreeButtonListener() {
        theme3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("background_resources", R.drawable.theme_3);
                editor.apply();
                findViewById(R.id.setting_menu).setBackgroundResource(R.drawable.theme_3);
            }
        });
    }
}
