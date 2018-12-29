package generalclasses;

import java.io.Serializable;
import java.util.HashMap;

public class GameScoreboards implements Serializable{
    private HashMap<String, ScoreBoard> scoreboardsForGame = new HashMap<>();

    public void addScoreboard(String difficulty, ScoreBoard scoreBoard) {
        scoreboardsForGame.put(difficulty, scoreBoard);
    }

    public ScoreBoard getScoreboard(String difficulty) {
        if (!scoreboardsForGame.keySet().contains(difficulty)) {
            scoreboardsForGame.put(difficulty, null);
        }
        return scoreboardsForGame.get(difficulty);
    }
}
