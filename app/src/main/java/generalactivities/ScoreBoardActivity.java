package generalactivities;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import generalclasses.GameScoreboards;

public class ScoreBoardActivity extends AppCompatActivity {
    protected GameScoreboards scoreboards;
    /**
     * A HashMap of all the scores, where the key is the User and value is his/her score.
     */
    protected LinkedHashMap<String, ArrayList<Integer>> scores;
    /**
     * A HashMap of the top 9 scores in 'scores', where the key is the User and value is his/her
     * score.
     */
    protected LinkedHashMap<String, Integer> highScores = new LinkedHashMap<>();

    protected void loadScoreboards(String scoreBoardToLoad) {
        try {
            InputStream inputStream = this.openFileInput(scoreBoardToLoad);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                scoreboards = (GameScoreboards) input.readObject();
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
     * Making the scoreboard display the global rankings.
     */
    protected void displayGlobalRankings() {
        updateHighScores();
        int i = 0;
        for (String user : highScores.keySet()) {
            setTextValues(i, user, String.valueOf(highScores.get(user)));
            i += 1;
        }
        for (; i < 9; i++) {
            setTextValues(i, "N/A", "N/A");
        }
    }

    /**
     * Making the scoreboard display local rankings of the current user logged in.
     *
     * @param username the username of the current user logged in.
     */
    protected void displayLocalRankings(String username) {
        ArrayList<Integer> localScores = scores.get(username);
        Collections.sort(localScores);
        int i = 0;

        // Setting the text values for all the user's scores
        for (Integer score : localScores) {
            setTextValues(i, username, String.valueOf(score));
            i += 1;
        }

        // Setting the text values as N/A and N/A for the rest of the scores.
        for (; i < 9; i++) {
            setTextValues(i, "N/A", "N/A");
        }
    }

    /**
     * Making the scoreboard display blank rankings where user is N/A and score is N/A
     */
    protected void displayBlankRankings() {
        for (int i = 0; i < 9; i++) {
            setTextValues(i, "N/A", "N/A");
        }
    }

    /**
     * Sets the TextViews for useri and scorei in content_sliding_tiles_score_board.xml
     *
     * @param i         the num id of the user/score in the XML file. i.e user0 or score0
     * @param userText  the text you want to replace useri with.
     * @param scoreText the text you want to replace scorei with.
     */
    public void setTextValues(int i, String userText, String scoreText) {
        TextView currentUserText = findViewById(getResources().getIdentifier("user" + i,
                "id",
                this.getPackageName()));
        currentUserText.setText(userText);

        TextView currentScoreText = findViewById(getResources().getIdentifier("score" + i,
                "id",
                this.getPackageName()));
        currentScoreText.setText(scoreText);
    }

    /**
     * Updates 'highScores' LinkedHashMap with the current scores.
     */
    public void updateHighScores() {
        ArrayList<Object[]> highScoreArray = getHighScoreArray();
        for (Object[] set : highScoreArray) {
            highScores.put((String) set[0], (Integer) set[1]); // set[0] = user, set[1] = score
        }
    }

    /**
     * Helper function for updateHighScores. Allows for easier mapping to LinkedHashMap with ArrayList
     * of HighScores.
     *
     * @return a sorted ArrayList<[User, Score]> of the highest scoring players and their scores.
     */
    private ArrayList<Object[]> getHighScoreArray() {

        ArrayList<Object[]> highScoresArray = new ArrayList<>(); // max size: 9, contains [user, score]
        for (String user : scores.keySet()) {
            ArrayList<Integer> value = scores.get(user);
            Object[] set = {user, Collections.min(value)};
            sortHighScoreArray(highScoresArray);
            // if its not full
            if (highScoresArray.size() != 9) {
                highScoresArray.add(set);
            }
            // if the lowest score in the scoreboard is greater than current score
            else if ((int) highScoresArray.get(8)[1] > (int) set[1]) {
                highScoresArray.remove(8);
                highScoresArray.add(set);

            }
        }
        sortHighScoreArray(highScoresArray);
        return highScoresArray;
    }

    /**
     * Helper function used for getHighScoreArray.
     * It sorts highScoresArray based on the score.
     */
    private void sortHighScoreArray(ArrayList highScoreArray) {
        Collections.sort(highScoreArray, new Comparator<Object[]>() {
            public int compare(Object[] temp1, Object[] temp2) {
                return Integer.compare((int) temp1[1], (int) temp2[1]);
            }
        });
    }
}
