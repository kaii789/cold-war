package fall2018.csc2017.coldwar;

import java.io.Serializable;

/**
 * The abstract parent for all pieces in the game.
 */
public abstract class Agent implements Serializable {

    private String owner;

    /**
     * Indicates whether his piece is visible.
     */
    private boolean isVisible = false;

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    /**
     * Indicates whether this piece can move.
     */
    private boolean canMove = false;

    boolean isCanMove() {
        return canMove;
    }

    void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    void setOwner(String owner) {
        this.owner = owner;
    }

    String getOwner() {
        return owner;
    }

    public abstract int getPicture();
}