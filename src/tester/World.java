package tester;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import general.AppGame;
import general.AppInput;
import general.AppPlayer;
import general.Playable;
public class World extends BasicGameState implements Playable {

	private int ID;

	public World (int ID) {
		this.ID = ID;
	}

	public int getID () {
		return this.ID;
	}

	public void init (GameContainer container, StateBasedGame game) {}

	public void update (GameContainer container, StateBasedGame game, int delta) {
		AppInput appInput = (AppInput) container.getInput ();
		AppGame appGame = (AppGame) game;
		AppPlayer gameMaster = appGame.appPlayers.get (0);
		int gameMasterID = gameMaster.getControllerID ();
		for (int i = 0, l = appInput.getButtonCount (gameMasterID); i < l; i++) {
			if (appInput.isButtonPressed (1 << i, gameMasterID)) {
				System.out.println ("isButtonPressed: " + i);
			}
		}
		for (int i = 0, l = appInput.getControlCount (gameMasterID); i < l; i++) {
			if (appInput.isControlPressed (1 << i, gameMasterID)) {
				System.out.println ("isControlPressed: " + i);
			}
		}
		for (int i = 0, l = appInput.getAxisCount (gameMasterID); i < l; i++) {
			float j = appInput.getAxisValue (i, gameMasterID);
			if (j <= -.5f || j >= .5f) {
				System.out.println ("getAxisValue: " + i + " -> " + j);
			}
		}
		appInput.clearControlPressedRecord ();
	}

	public void render (GameContainer container, StateBasedGame game, Graphics context) {}

	public void initPlayers (GameContainer container, StateBasedGame game) {}

}
