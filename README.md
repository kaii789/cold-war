# Cold War
COLD WAR was created entirely from scratch by Kailong Huang and Kelvin Yao Fan.**

## Code Tour of Cold War
* **Class Agent** The abstract parent of all pieces in the game. Its subclasses include the 2 bases, Spy, and Diplomat.

* **Class ColdWarGameController** The controller under the MVC model for the Cold War game. It manages inputs from the user
and manipulates and reads from the model if necessary. 

* **Class ColdWarGameInfo** The model under the MVC model for the Cold War game. It contains all relevant 
information regarding the current state of the game, including the location of each piece, the number of 
remaining pieces owned by each owner, etc.

* **Class ColdWarMainActivity** The main view under the MVC model for the Cold War game. It contains what
is shown to users.

* **Class GameOverUtility** A utility class used by the controller for tasks related to game over handling.

* **Class MovementUtility** A utilities package for handling move-making.

* **Class TurnManagementUtility** A utilities package for handling tasks related to beginning, executing, and ending turns.

## Code Tour of GeneralClasses

* **Class GameSelectionActivity:** This screen allows the user to pick which game they wish to play.

* **Class MenuActivity:** This screen allows the user to logout, configure their settings, or proceed 
to choosing a game to play.

* **Class LoginActivity:** This is the interface where users have to sign in into their accounts before 
getting access to the menu selection screen. New users are required to sign up first.

* **Class ScoreBoardActivity:** This screen allows the user to display the global rankings which 
shows the ranking of the top players of the game at all time with their username, top score and ranking.
The user can also choose to display local rankings which shows their personal achievements.
