package games.preciseLock;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import app.AppInput;
import app.AppPlayer;

public class Player {
	private int angle; // nb de degrés parcourus depuis le point 0 situé à gauche
	private int precision; // Nombres de degrés acceptés au dessus/en dessous de l'objectif
	private int speed; // vitesse de la rotation, diminue à chaque mort
	private int controllerID;
	// private String name;
	private Color fillColor;
	private Color strokeColor;
	private boolean finished;
	private int startX;
	private int startY;
	private int width;
	private int height;
	private int centerX;
	private int centerY;
	private int radius;
	
	public Player(AppPlayer appPlayer, int angle, int startX, int startY, int width, int height) {
		// this.name=appPlayer.getName();
		this.controllerID=appPlayer.getControllerID();
		this.fillColor=AppPlayer.FILL_COLORS[appPlayer.getColorID()];
		this.strokeColor=AppPlayer.STROKE_COLORS[appPlayer.getColorID()];
		this.angle=angle;
		this.precision=3;
		this.speed=8;
		this.finished=false;
		this.startX=startX;
		this.startY=startY;
		this.width=width;
		this.height=height;
		this.centerX=startX+width/2;
		this.centerY=startY+height/2;
		this.radius=(width<height?width/3:height/3);
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) {
		if (!finished) {
			AppInput appInput = (AppInput) container.getInput();
			if (appInput.isButtonPressed(AppInput.BUTTON_A,controllerID)) {
				int tmp = this.angle+speed;
				this.angle=(tmp<180)?tmp:tmp-360;
			}
			if (angle>=180-precision || angle<=-180+precision && !appInput.isButtonPressed(AppInput.BUTTON_A,controllerID)) {
				finished=true;
			}
		}
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics context) {
		context.setLineWidth(3);
		context.setColor(Color.white);
		context.drawRect(startX,startY,width,height);
		context.setColor(Color.red);
		context.drawLine(centerX-width/2+2,centerY,centerX-radius-1,centerY);
		context.fillOval(centerX-radius,centerY-radius,2*radius,2*radius);
		context.setColor(fillColor);
		context.fillArc(centerX-radius,centerY-radius,2*radius,2*radius, angle+precision, angle-precision);
		context.setColor(strokeColor);
		context.drawArc(centerX-radius,centerY-radius,2*radius,2*radius, angle+precision, angle-precision);
	}
	
	public boolean hasFinished() {
		return finished;
	}

	public Integer getControllerID() {
		return this.controllerID;
	}
	
	public void slowRotation() {
		speed-=2;
	}
}
