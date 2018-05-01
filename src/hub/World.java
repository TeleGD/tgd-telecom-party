package hub;

import java.util.ArrayList;
import java.util.List;

// import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import app.AppGame;
import app.AppPlayer;
import app.AppWorld;
// import app.ui.Button;
// import app.ui.TGDComponent;
// import app.ui.TGDComponent.OnClickListener;
import hub.cases.Remi;

public class World extends AppWorld {

	@Deprecated
	private StateBasedGame game;

	private SpiralTrack track;
	private int gridWidth;
	private int gridHeight;
	private int gridGap;
	private int turnNumber;
	private List<Player> listeJoueurs;
	private ArrayList<Remi> listeRemis; // La liste des cases Rémi Bachelet ! TODO : l'initialiser
	private boolean enterPress;

	public World (int ID) {
		super (ID);
	}

	@Override
	public void init (GameContainer container, StateBasedGame game) {
		this.game = game;

		gridWidth = 64;
		gridHeight = 64;
		gridGap = 16;
		listeRemis = new ArrayList<Remi>();
		this.track = new SpiralTrack (this, 70);

		this.turnNumber = 0; // numéro du tour
	}

	@Override
	public void update (GameContainer container, StateBasedGame game, int delta) throws SlickException {
		if (enterPress) {
			int nbJoueurs = listeJoueurs.size ();
			listeJoueurs.get(turnNumber % nbJoueurs).playRound(); // Lance le tour du joueur à qui c'est le tour
			enterPress = false;
			turnNumber ++;
		}
		for (Player p: this.listeJoueurs) {
			p.update (container, game, delta);
		}
	}

	@Override
	public void render (GameContainer container,StateBasedGame game, Graphics context) throws SlickException {
		// Affichage du plateau :
		for (int i = 0; i < this.track.length; i++) {
			track.getCase(i).render(container, game, context);
		}
		// Affichage des joueurs :
		for (Player p: this.listeJoueurs) {
			p.render (container, game, context);
		}
	}

	@Override
	public void play (GameContainer container, StateBasedGame game) {
		this.listeJoueurs = new ArrayList <Player> (); // initialisation de listeJoueur
		for (AppPlayer appPlayer: ((AppGame) game).appPlayers) {
			this.listeJoueurs.add (new Player (this, appPlayer.getColorID (), appPlayer.getControllerID (), appPlayer.getName (), "images/hub/pion.png"));
		}
	}

	@Override
	public void keyPressed (int key, char c) {
		switch (key) {
			case Input.KEY_ESCAPE:
				this.game.enterState (AppGame.PAGES_TITLES, new FadeOutTransition (), new FadeInTransition ());
				break;
			case Input.KEY_ENTER:
				enterPress = true ;
				break;
			default:
				super.keyPressed (key, c);
		}
	}

	public SpiralTrack getTrack () {
		return this.track;
	}

	public int getGridWidth () {
		return this.gridWidth;
	}

	public int getGridHeight () {
		return this.gridHeight;
	}

	public int getGridGap() {
		return this.gridGap;
	}

	public List<Player> getListeJoueurs() {
		return listeJoueurs;
	}

	public List<Remi> getListeRemis() {
		return listeRemis;
	}

}
