package generalclasses;

import java.io.Serializable;

/**
 * A class that stores the information about the current state of a game.
 * <p>
 * Remarks: This is the main class acting as the "Model" in the MVC design pattern.
 */
public abstract class GameInfo implements Serializable {

    /**
     * The username of the User that possesses this GameInfo
     */
    private String ownerUserName;

    /**
     * @return the score of the game
     */
    public abstract int getScore();


    /**
     * Updates the score of the game at its current state based on relevant information.
     */
    public abstract void updateScore();


    /**
     * @return the user name for the game.
     */
    public String getUserName() {
        return this.ownerUserName;
    }


    /**
     * Set the username
     *
     * @param username the user name of the game.
     */
    public void setUserName(String username) {
        this.ownerUserName = username;
    }


    /**
     * @return the game name.
     */
    public abstract String getGame();
}
