package games.contestFall;

import java.io.File;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import general.Playable;
import general.utils.FontUtils;

public class World extends BasicGameState implements Playable {

	private int id;
	
	public final static String GAME_FOLDER_NAME="contestFall";
	public final static String DIRECTORY_SOUNDS="musics"+File.separator+GAME_FOLDER_NAME+File.separator;
	public final static String DIRECTORY_MUSICS="musics"+File.separator+GAME_FOLDER_NAME+File.separator;
	public final static String DIRECTORY_IMAGES="images"+File.separator+GAME_FOLDER_NAME+File.separator;
	public final static TrueTypeFont Font = FontUtils.loadFont ("Kalinga", java.awt.Font.BOLD, 20, true);

	public static Image fondPlateforme;
	public static Image fond;
	public static Image[] casse = new Image[4];
	public static Image ammo;
	
	Platform platform;
	int startX;
	int startY;
	int taille;
	
	static {
		try {
			fondPlateforme = new Image(DIRECTORY_IMAGES+"plateforme.png");
			fond = new Image(DIRECTORY_IMAGES+"fond.png");
			casse[0] = new Image(DIRECTORY_IMAGES+"destroy1.png");
			casse[1] = new Image(DIRECTORY_IMAGES+"destroy2.png");
			casse[2] = new Image(DIRECTORY_IMAGES+"destroy3.png");
			casse[3] = new Image(DIRECTORY_IMAGES+"destroy4.png");
			ammo = new Image(DIRECTORY_IMAGES+"c.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public World(int id) {
		this.id = id;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) {
		// TODO Auto-generated method stub
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics context) {
		context.drawImage(fond,startX,startY,startX+taille,startY+taille,0,0,fond.getWidth(),fond.getHeight());
		platform.render(container, game, context);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) {
		platform.update(container, game, delta);
	}

	@Override
	public void initPlayers(GameContainer container, StateBasedGame game) {
		int width = container.getWidth();
		int height = container.getHeight();
		taille = (width>height)?height:width;
		startX = (width>height)?width/2-height/2:0;
		startY = (width<height)?height/2-width/2:0;
		platform = new Platform(this, 12);
	}

	@Override
	public int getID() {
		return id;
	}

}
