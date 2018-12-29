package generalclasses;

/**
 * A tool used by the GameController class to help manage information about the current state of the
 * game, AKA class GameInfo.
 */
public abstract class Manager {

    /**
     * Returns whether the game in its current state is considered finished.
     *
     * @return whether game is over.
     */
    public abstract boolean isOver();


    /**
     * Returns whether the move attempt by the user is valid.
     *
     * @return whether move is valid.
     */
//    public abstract boolean isValidMove(Object move);


    /**
     * Perform the move attempted by user.
     * <p>
     * Precondition: isValidMove(move) returns true.
     */
//    public abstract void makeMove(Object move);
}
