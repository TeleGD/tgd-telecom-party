package games.clicker;

import general.AppInput;
import general.AppPlayer;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public class Player {
	private String name;
	private Color color;
	private int controllerId;
	private int score;
	private int multi;

	public Player(AppPlayer appPlayer) {
		this.name = appPlayer.getName();
		this.color = appPlayer.FILL_COLORS[appPlayer.getColorID()];
		this.controllerId = appPlayer.getControllerID();
		score = 0;
		multi =0;
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics context) {
		// TODO Auto-generated method stub
		
	}

	public void update(GameContainer container, StateBasedGame game, int delta) {
		// TODO Auto-generated method stub
		multi = 0;
		AppInput appInput = (AppInput) container.getInput();
		multi += appInput.isButtonPressed(AppInput.BUTTON_A,controllerId) ? 1:0;
		multi += appInput.isButtonPressed(AppInput.BUTTON_B,controllerId) ? 1:0;
		multi += appInput.isButtonPressed(AppInput.BUTTON_X,controllerId) ? 1:0;
		multi += appInput.isButtonPressed(AppInput.BUTTON_Y,controllerId) ? 1:0;
		if (multi >= 3){
			score -= 5;
		} else if (multi == 1){
			score += multi;
		}
	}
}
