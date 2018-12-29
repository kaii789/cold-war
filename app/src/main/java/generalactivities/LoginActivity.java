package generalactivities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import fall2018.csc2017.slidingtiles.R;
import generalclasses.User;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    /**
     * The master save file.
     * <p>
     * This file should contain the hash map of user names to users, hence containing information
     * about ALL users and their saves.
     */
    public static final String SAVE_FILENAME = "master_save_file.ser";

    private EditText name;
    private EditText password;
    private TextView info;
    private Button signup;
    private Button signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        declarations();
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
        int bg = sharedPref.getInt("background_resources", android.R.color.white); // the second parameter will be fallback if the preference is not found
        getWindow().setBackgroundDrawableResource(bg);
        addSignIntButtonListener();
        addSignUpButtonListener();
        load();
    }

    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
        int bg = sharedPref.getInt("background_resources", android.R.color.white); // the second parameter will be fallback if the preference is not found
        getWindow().setBackgroundDrawableResource(bg);

    }


    /**
     * Load data from the master save file. If unable to load, add data to the master save file.
     */
    private void load() {
        try {
            loadFromFile(SAVE_FILENAME);
        } catch (Exception e) {
            saveToFile(SAVE_FILENAME);
        }
    }


    /**
     * Make all the assignments for attributes of LoginActivity.
     */
    private void declarations() {
        name = findViewById(R.id.username);
        password = findViewById(R.id.password);
        info = findViewById(R.id.explanation);
        signin = findViewById(R.id.signin);
        signup = findViewById(R.id.signup);
    }


    /**
     * Add a click listener to handle sign in.
     */
    private void addSignIntButtonListener() {
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = name.getText().toString();
                boolean valid = User.isValidLogin(username); // Check if the login is valid
                if (valid) {
                    startMenuActivity(username);
                } else {
                    info.setText("Invalid username");
                }
            }
        });
    }

    /**
     * Start and pass in username to MenuActivity.
     * @param username usrname
     */
    private void startMenuActivity(String username) {
        User user = User.usernameToUser.get(username);
        // Create MenuActivity if credentials are valid
        if (user.getPassword().equals(password.getText().toString())) {
            Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
            intent.putExtra("username", username); // tells the next activity the current user
            startActivity(intent);
        } else {
            info.setText("Invalid password");
        }
    }

    /**
     * Add a click listener to handle sign up.
     */
    private void addSignUpButtonListener() {
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = name.getText().toString();
                signUpUser(username);
            }
        });
    }

    /** Sign up user with user name username if username is valid.
     * @param username username
     */
    private void signUpUser(String username) {
        boolean valid = User.isValidLogin(username); // check if the username is taken or not
        if (name.getText().toString().isEmpty()) {
            // if there was no input, warns on the bottom text field
            info.setText("Both fields must be filled in");
        } else if (valid) {
            info.setText("Username is already taken");
        } else {
            User.createUser(name.getText().toString(), password.getText().toString());
            saveToFile(SAVE_FILENAME);
        }
    }

    /**
     * Load the usernameToUsers hash map from fileName.
     * @param fileName the name of the file
     */
    private void loadFromFile(String fileName) {

        try {
            InputStream inputStream = this.openFileInput(fileName);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                User.usernameToUser = (HashMap<String, User>) input.readObject();
                // TODO: according to StackOverflow, there is no way to fix the above warning. If you guys think it's ok, delete this TODO. Kelvin
                inputStream.close();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        } catch (ClassNotFoundException e) {
            Log.e("login activity", "File contained unexpected data type: " + e.toString());
        }
    }

    /**
     * Save the usernameToUser hash map to fileName.
     *
     * @param fileName the name of the file
     */
    public void saveToFile(String fileName) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    this.openFileOutput(fileName, MODE_PRIVATE));
            outputStream.writeObject(User.usernameToUser);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

}

