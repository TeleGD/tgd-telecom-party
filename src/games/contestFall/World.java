package games.contestFall;

import java.io.File;
import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import general.AppGame;
import general.AppInput;
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
	private ArrayList<Player> players;
	ArrayList<Projectile> projectiles;
	
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
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics context) {
		context.drawImage(fond,startX,startY,startX+taille,startY+taille,0,0,fond.getWidth(),fond.getHeight());
		platform.render(container, game, context);
		for (Player p : players) {
			p.render(container, game, context);
		}
		for (Projectile p : projectiles) {
			p.render(container, game, context);
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) {
		AppInput appInput = (AppInput) container.getInput ();
		AppGame appGame = (AppGame) game;
		int gameMasterID = appGame.appPlayers.get (0).getControllerID ();
		if (appInput.isKeyPressed (AppInput.KEY_ESCAPE) || appInput.isButtonPressed (AppInput.BUTTON_PLUS, gameMasterID)) {
			game.enterState (general.AppGame.MENUS_GAMES_MENU, new FadeOutTransition (), new FadeInTransition ());
		} else {
			platform.update(container, game, delta);
			for (int i=players.size()-1; i>=0 ; i--) {
				players.get(i).update(container, game, delta);
				if (players.get(i).isDead()) {
					players.remove(i);
				}
			}
			for (int i=projectiles.size()-1; i>=0 ; i--) {
				projectiles.get(i).update(container, game, delta);
			}
		}
	}

	@Override
	public void initPlayers(GameContainer container, StateBasedGame game) {
		int width = container.getWidth();
		int height = container.getHeight();
		taille = (width>height)?height:width;
		startX = (width>height)?width/2-height/2:0;
		startY = (width<height)?height/2-width/2:0;
		int platformSize = 12;
		platform = new Platform(this, platformSize);
		platformSize=platform.getSize();
		
		AppGame appGame = (AppGame) game;
		this.players = new ArrayList<Player>();
		for (int i = 0; i < appGame.appPlayers.size(); i++) {
			int pStartX = 0;
			int pStartY = 0;
			int direction = 0;
			switch (i) {
			case 0:
				pStartX = 1;
				pStartY = platformSize/2;
				direction=3;
				break;
			case 1:
				pStartX = platformSize-2;
				pStartY = platformSize/2;
				direction=1;
				break;
			case 2:
				pStartX = platformSize/2;
				pStartY = 1;
				direction=2;
				break;
			case 3:
				pStartX = platformSize/2;
				pStartY = platformSize-2;
				direction=0;
				break;
			default:
				break;
			}
			this.players.add(new Player(this, pStartX, pStartY, direction, appGame.appPlayers.get(i)));
		}
		
		projectiles = new ArrayList<Projectile>();
	}

	@Override
	public int getID() {
		return id;
	}

}
