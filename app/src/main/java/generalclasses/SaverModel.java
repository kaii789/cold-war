package generalclasses;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public abstract class SaverModel {
    public Context context;
    public GameScoreboards scoreboards;
    public User user;


    public User getUser() {
        return user;
    }

    public SaverModel(Context context) {
        this.context = context;
    }

    /**
     * Save the current hash map with each user's saves to fileName.
     *
     * @param fileName the name of the file
     */
    public void saveToFile(String fileName) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    context.openFileOutput(fileName, context.MODE_PRIVATE));
            outputStream.writeObject(User.usernameToUser);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    public void saveScoreboards(GameScoreboards scoreboards, String scoreBoardSaveLocation) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    context.openFileOutput(scoreBoardSaveLocation, context.MODE_PRIVATE));
            outputStream.writeObject(scoreboards);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    /**
     * Display that there was nothing to save.
     */
    public void makeToastNothingToSave() {
        Toast.makeText(context, "Nothing to Save", Toast.LENGTH_SHORT).show();
    }

    /**
     * Display that a game was saved successfully.
     */
    public void makeToastSavedText() {
        Toast.makeText(context, "Game Saved", Toast.LENGTH_SHORT).show();
    }

    public void loadScoreboards(String scoreBoardSaveLocation) {
        try {
            InputStream inputStream = context.openFileInput(scoreBoardSaveLocation);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                scoreboards = (GameScoreboards) input.readObject();
                inputStream.close();
            }
        } catch (FileNotFoundException e) {
            scoreboards = new GameScoreboards();
            saveScoreboards(scoreboards, scoreBoardSaveLocation);
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        } catch (ClassNotFoundException e) {
            Log.e("login activity", "File contained unexpected data type: " + e.toString());
        }
    }

}
