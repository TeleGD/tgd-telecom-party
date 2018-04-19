package games.clicker;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import app.AppGame;
import app.AppInput;
import app.AppPlayer;
import app.AppWorld;
import app.utils.FontUtils;

public class World extends AppWorld {

	public static final Font Font = FontUtils.loadFont ("Kalinga", java.awt.Font.BOLD, 18, true);
	private final int ID;
	private ArrayList<Player> players;
	private int count;
	private boolean finished;
	private int height;
	private int width;
	private int nbJoueursInit;
	private static int marge = 10;

	public World(int id) {
		this.ID=id;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics context) {
		// TODO Auto-generated method stub
		if (finished){
			context.setFont(Font);
			for (int i=players.size()-1; i>=0;i--) {
				context.setColor(players.get(i).getColor());
				String s = players.get(i).getName() + " : " + players.get(i).getScore();
				context.drawString(s, width / 2 - Font.getWidth(s) / 2, height / 2 - (Font.getHeight(s) + 5) * (nbJoueursInit / 2 - i));
			}
		} else {
			if (nbJoueursInit > 2){
				context.setColor(Color.white);
				context.fillRect(0,0,width,height);
				context.setColor(Color.black);
				context.fillRect(marge,marge,width/2-2*marge,height/2-2*marge);
				context.fillRect(marge+width/2,marge,width/2-2*marge,height/2-2*marge);
				context.fillRect(marge,marge+height/2,width/2-2*marge,height/2-2*marge);
				context.fillRect(marge+width/2,marge+height/2,width/2-2*marge,height/2-2*marge);
				context.setColor(Color.white);
				context.drawRect(width/2-width/6,height/2-height/6,width/3,height/3);
				context.setColor(Color.black);
				context.fillRect(width/2-width/6+1,height/2-height/6+1,width/3-1,height/3-1);
				if (nbJoueursInit == 3){
			Image image = null;
			try {
				image = new Image("images/clicker/lucas.png");
			} catch (SlickException e) {

			}
			image.draw(width*3/4-8*marge,height*3/4-13*marge);
				}
				for (int i = 0; i < nbJoueursInit; i++){
					context.setColor(players.get(i).getColor());
					String s = players.get(i).getName() + " : " + players.get(i).getScore();
					int x = (width + 2*marge)/4 - Font.getWidth(s)/2;
					int y = (height + 2*marge)/4 - Font.getHeight(s)/2;
					context.drawString(s,(i&1)*width/2+x,(i&2)*height/2+y);
				}
			} else {
				context.setColor(Color.white);
				context.fillRect(0,0,width,height);
				context.setColor(Color.black);
				context.fillRect(marge,marge,width/2-2*marge,height-2*marge);
				context.fillRect(marge+width/2,marge,width/2-2*marge,height-2*marge);
				context.setColor(Color.white);
				context.drawRect(width/2-width/6,height/2-height/6,width/3,height/3);
				context.setColor(Color.black);
				context.fillRect(width/2-width/6+1,height/2-height/6+1,width/3-1,height/3-1);
				for (int i = 0; i < nbJoueursInit; i++){
					context.setColor(players.get(i).getColor());
					String s = players.get(i).getName() + " : " + players.get(i).getScore();
					int x = (width + 2*marge)/6 - Font.getWidth(s)/2;
					int y = (height + 2*marge)/2 - Font.getHeight(s)/2;
					context.drawString(s,i*4*width/6+x,y);
				}
			}
			context.setColor(Color.white);
			String s2 = "Temps restant : " + count/1000;
			context.drawString(s2,width/2-75,height/2);
		}

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) {
		AppInput appInput = (AppInput) container.getInput ();
		AppGame appGame = (AppGame) game;
		int gameMasterID = appGame.appPlayers.get (0).getControllerID ();
		if (appInput.isKeyPressed (AppInput.KEY_ESCAPE) || appInput.isButtonPressed (AppInput.BUTTON_PLUS, gameMasterID)) {
			game.enterState (app.AppGame.PAGES_GAMES, new FadeOutTransition(), new FadeInTransition());
		} else if (!finished){
			count -= delta;
			for (Player player : players) {
				player.update(container, game, delta);
			}
			if (count < 0){
				finished = true;
			}
		}
	}

	@Override
	public void play (GameContainer container, StateBasedGame game) {
		AppGame appGame = (AppGame) game;
		finished =false;
		count = 30000;
		players = new ArrayList<Player>();
		for(AppPlayer player : appGame.appPlayers){
			players.add(new Player(player)) ;
		}
		height = container.getHeight();
		width = container.getWidth();
		nbJoueursInit = appGame.appPlayers.size();
	}

	@Override
	public int getID() {
		return ID;
	}

}
