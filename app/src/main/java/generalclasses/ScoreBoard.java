package generalclasses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class ScoreBoard implements Serializable {

    /**
     * A HashMap of all the scores, where the key is the User and value is his/her score.
     */
    private LinkedHashMap<String, ArrayList<Integer>> scores = new LinkedHashMap<>();

    /**
     * Returns the scores
     *
     * @return scores
     */
    public LinkedHashMap<String, ArrayList<Integer>> getScoreMap() {
        return scores;
    }

    /**
     * Adds a user that is not in the list and his/her score
     *
     * @param user  the player's username
     * @param score the player's score
     */
    public void addUserAndScore(String user, int score) {
        ArrayList<Integer> scoreArray = new ArrayList<>();
        scoreArray.add(score);
        scores.put(user, scoreArray);
    }

    /**
     * Add a score to a existing user.
     *
     * @param user  the player's username
     * @param score the player's score
     */
    public void addScore(String user, int score) {
        scores.get(user).add(score);
    }
}
