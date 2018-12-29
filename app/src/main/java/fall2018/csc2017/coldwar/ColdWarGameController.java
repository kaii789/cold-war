package fall2018.csc2017.coldwar;

import android.content.Context;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fall2018.csc2017.slidingtiles.R;

/**
 * The controller for Cold War. It manages inputs from the user and manipulates and reads from the
 * model as needed.
 */
class ColdWarGameController {
    private int selectedPosition;
    private Button endButton;
    private TextView guestReputationText;
    private TextView userReputationText;
    private TextView selectedPositionText;
    private Context context;

    ColdWarGameController(Context context) {
        this.context = context;
    }

    /** Update the selected position if the user is selecting. Move the item at selectedPosition to
     * position if the user has already selected a position.
     * @param coldWarGameInfo the model for Cold War
     * @param selectedPosition the position of the item the user wants to move
     * @param position the posiiton the user wants to move the item at selectedPosition to
     * @return whether or not the move was successful
     */
    boolean touchMove(ColdWarGameInfo coldWarGameInfo, int selectedPosition, int position) {
        if (selectedPosition == -1) { // this means the user has not selected a piece to move yet
            setPosition(position, coldWarGameInfo);
            return true;
        }
        // user has selected a piece to move, so makeMove if position is a valid position to move to
        return executeMove(coldWarGameInfo, selectedPosition, position);

    }

    private boolean executeMove(ColdWarGameInfo coldWarGameInfo, int selectedPosition, int position) {
        boolean validMove = MovementUtility.makeMove(coldWarGameInfo, selectedPosition, position);
        this.selectedPosition = -1; // this indicates that selectedPosition is reset to "unselected"
        updateUserDisplay(coldWarGameInfo);
        if (!validMove) {
            return false;
        }
        endButton.setEnabled(true);
        return true;
    }

    /**
     * Update the information shown to players in the game view.
     * @param coldWarGameInfo The information about the current game
     */
    private void updateUserDisplay(ColdWarGameInfo coldWarGameInfo) {
        String guestReputationString = "Guest Global Reputation: " +
                coldWarGameInfo.getPlayer2Reputation().toString();
        String userReputationString = "User Global Reputation: " +
                coldWarGameInfo.getPlayer1Reputation().toString();
        String selectedPositionString;
        if (selectedPosition == -1) {
            selectedPositionString = "No position is selected.";
        } else {
            selectedPositionString = "Current Selected Position is: " + MovementUtility.positionToCoordinates(selectedPosition);
        }

        guestReputationText.setText(guestReputationString);
        userReputationText.setText(userReputationString);
        selectedPositionText.setText(selectedPositionString);
    }

    /** Update gridView with the latest data from coldWarGameInfo
     * @param gridView the GridView that displays the game board
     * @param coldWarGameInfo model for Cold War
     */
    void updateGridView(GridView gridView, ColdWarGameInfo coldWarGameInfo) {
        List<Integer> imageIDs = getImageIDs(coldWarGameInfo);
        gridView.setAdapter(new ImageAdapterGridView(context, imageIDs));
    }


    void setViews(Button endButton, TextView guestReputationText, TextView userReputationText,
                  TextView selectedPositionText) {
        this.endButton = endButton;
        this.guestReputationText = guestReputationText;
        this.userReputationText = userReputationText;
        this.selectedPositionText = selectedPositionText;
    }

    int getSelectedPosition() {
        return selectedPosition;
    }

    /**
     * Return a list of imageIDs essential for displaying a visual representation of board.
     *
     * @param coldWarGameInfo Information used to generate imageIDs list.
     * @return A list of integers corresponding to imageIDs in the drawables folder based on the
     * information in coldWarGameInfo's board.
     */
    static List<Integer> getImageIDs(ColdWarGameInfo coldWarGameInfo) {
        List<Integer> IDs = new ArrayList<>();
        List<Tile> board = coldWarGameInfo.getBoard();

        for (int i = 0; i < board.size(); i++) {
            Agent occupant = board.get(i).getAgent();
            if (occupant == null) {
                IDs.add(R.drawable.cold_war_blank_tile);
            } else if (occupant instanceof USBase | occupant instanceof  SUBase) {
                // Bases are shown regardless of visibility
                IDs.add(occupant.getPicture());
            }
            else if (occupant.getOwner().equals(coldWarGameInfo.getCurrentPlayer())
                    && occupant.isVisible()) {
                IDs.add(occupant.getPicture());
            } else {
                IDs.add(R.drawable.unknown);
            }
        }

        return IDs;
    }

    private void setPosition(int position, ColdWarGameInfo coldWarGameInfo) {
        this.selectedPosition = position;
        updateUserDisplay(coldWarGameInfo);
    }
}
