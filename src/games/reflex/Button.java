package games.reflex;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import app.AppInput;
import app.utils.FontUtils;

public abstract class Button {

	protected int x,y;
	protected Player player;
	protected int numero;
	protected Color color;

	public Button(Player player,int num) {
		this.x = player.getPos();
		this.y = 300;
		this.player = player;
		numero = num;
	}

	public abstract void render(GameContainer container, StateBasedGame game, Graphics context);

	public int getNumero() {
		return numero;
	}
}
