package games.t7Laser;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import general.AppGame;
import general.AppInput;
import general.Playable;
import general.utils.FontUtils;

public class World extends BasicGameState implements Playable{

	private int ID;
	
	public final static String GAME_FOLDER_NAME="T7Laser";
	public final static String DIRECTORY_SOUNDS="musics"+File.separator+GAME_FOLDER_NAME+File.separator;
	public final static String DIRECTORY_MUSICS="musics"+File.separator+GAME_FOLDER_NAME+File.separator;
	public final static String DIRECTORY_IMAGES="images"+File.separator+GAME_FOLDER_NAME+File.separator;
	public static final Font Font = FontUtils.loadFont ("Kalinga", java.awt.Font.BOLD, 18, true);
	
	private List<Player> players;
	private List<Player> morts;
	private Grid grid;
	private Music music;
	private Music end;
	Sound cat;	
	private float renderScale = 1;
	public int height;
	public int width;
	private int nbJoueursInit;
	private boolean fin;
	
	public World (int ID) {
		this.ID = ID;
	}

	@Override
	public int getID () {
		return this.ID;
	}	
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		width=container.getWidth();
		height=container.getHeight();
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		//Ici mettre tous les chargement d'image, creation de perso/decor et autre truc qui mettent du temps	
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		//Affichage
		Color c = fin ? Color.black : Color.white;
		g.setColor(c);
		g.fillRect(0,0,width,height);
		if (!fin) {
			grid.render(container,game,g);
			for (Player p : players) {
				p.render(container,game,g);
			}
		} else {
			g.setColor(Color.black);
			for (int i=0; i<morts.size();i++) {
				g.setColor(morts.get(i).getCouleur());
				String s = morts.get(i).getName()+" : "+morts.get(i).getScore();
				g.drawString(s,width/2-Font.getWidth(s)/2,height/2-(Font.getHeight(s)+5)*(nbJoueursInit/2-i));
			}
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		AppInput appInput = (AppInput) container.getInput ();
		AppGame appGame = (AppGame) game;
		int gameMasterID = appGame.appPlayers.get (0).getControllerID ();
		if (appInput.isKeyPressed (AppInput.KEY_ESCAPE) || appInput.isButtonPressed (AppInput.BUTTON_PLUS, gameMasterID)) {
			game.enterState (general.AppGame.MENUS_GAMES_MENU, new FadeOutTransition (), new FadeInTransition ());
		} else {
			if (!fin) {
				for (Player p : players) {
					p.update(container,game,delta);
				}
				grid.update(container,game,delta);
				
				for (int i=0 ; i<players.size() ; i++) {
					if (players.get(i).getLives()==0) {
						players.get(i).addScore(200*morts.size());
						morts.add(players.get(i));
						// enlever le joueur du plateau
						players.remove(i);
					}
				}
				
				if (morts.size()>=nbJoueursInit) {
					music.stop();
					end.play();
					players.get(0).addScore(200*morts.size());
					morts.add(players.get(0));
					Player tri[] = new Player[morts.size()];
					for (int i=tri.length-1 ; i>0 ; i--) {
						for (int j=0; j<i ; j++) {
							if (tri[j+1].getScore()<tri[j].getScore()) {
								Player tmp = tri[j+1];
								tri[j+1] = tri[j];
								tri[j]=tmp;
							}
						}
					}
					morts = new ArrayList<>(Arrays.asList(tri));
					fin = true;
				}
			}
		}
	}
	
	public Grid getGrid(){
		return grid;
	}
	
	public List<Player> getPlayers(){
		return players;
	}
	
	public float  getRenderScale(){
		return renderScale;
	}
	
	public void setRenderScale(float d){
		 renderScale = d;
	}

	@Override
	public void initPlayers(GameContainer container, StateBasedGame game) {
		AppGame appGame = (AppGame) game;
		fin = false;
		try {
			grid = new Grid(this,4,4);
			music = new Music(DIRECTORY_MUSICS+"EpicSaxGuy.ogg");
			end = new Music(DIRECTORY_MUSICS+"EndSong.ogg");
			cat= new Sound(DIRECTORY_SOUNDS+"Cat.ogg");
			music.loop();
		} catch (SlickException e1) {
			e1.printStackTrace();
		}
		renderScale = 1;
		nbJoueursInit = appGame.appPlayers.size ();
		this.players = new ArrayList<Player>();
		this.morts = new ArrayList<Player>();
		int w = grid.getColumns ();
		int h = grid.getRows ();
		for (int i = 0; i < nbJoueursInit; i++) {
		    try {
				this.players.add (new Player (this, (-i >> 1 & 1) * w, (i & 1) * h, i, appGame.appPlayers.get(i)));
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
	}

}