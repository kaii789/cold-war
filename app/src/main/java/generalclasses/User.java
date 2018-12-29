package generalclasses;

import android.content.res.Resources;

import java.io.Serializable;
import java.util.HashMap;



/**
 * A user that has a name, password, and can save games.
 * Also keeps track of existing users and can create new users.
 */
public class User implements Serializable {

    /**
     * A hash map that contains the names of all existing users as keys, and each key maps to its
     * corresponding User.
     */
    public static HashMap<String, User> usernameToUser = new HashMap<>();

    private String name;

    private String password;

    /**
     * A hash map that maps each user's saves to its saved GameInfo based on its game.
     */
    private HashMap<String, HashMap<String, GameInfo>> gameToSaves;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        gameToSaves = new HashMap<>();
    }

    /**
     * @return the name of User.
     */
    public String getName() {
        return this.name;
    }


    /**
     * @return the password of User.
     */
    public String getPassword() {
        return this.password;
    }


    /**
     * @param newSave a new GameInfo to be saved.
     */
    public void addSave(String game, String saveName, GameInfo newSave) {
        if (gameToSaves.keySet().contains(game)) {
            HashMap<String, GameInfo> saves = gameToSaves.get(game);
            saves.put(saveName, newSave);
        } else {
            HashMap<String, GameInfo> saves = new HashMap<>();
            saves.put(saveName, newSave);
            gameToSaves.put(game, saves);
        }
    }


    /**
     * @param game a game that is from the available set of games.
     * @return the hash map of save names to GameInfos for game.
     */
    public HashMap<String, GameInfo> getSavesForGame(String game) {
        if (gameToSaves.keySet().contains(game)) {
            return gameToSaves.get(game);
        }
        return new HashMap<>();
    }


    /**
     * @param game a game that is available from the available set of games.
     * @return the array of save names for game.
     */
    public String[] getSaveNamesForGame(String game) {
        return getSavesForGame(game).keySet().toArray(new
                String[getSavesForGame(game).keySet().size()]);
    }


    /**
     * Create a new User with username and password and store it on the main hash map of users and
     * their saves.
     *
     * @param username the username of the User to be created.
     * @param password the password of the User to be created.
     */
    public static void createUser(String username, String password) {
        if (User.usernameToUser.keySet().contains(username)) {
            throw new IllegalArgumentException("This user already exists.");
        } else {
            User user = new User(username, password);
            usernameToUser.put(username, user);
        }
    }


    /**
     * A static method for determining whether the given string user is in the list of existing
     * users.
     *
     * @return whether username is in the list of existing users.
     */
    public static boolean isValidLogin(String username) {
        return User.usernameToUser.keySet().contains(username);
    }
}
