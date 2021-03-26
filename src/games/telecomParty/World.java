package games.telecomParty;

import java.util.ArrayList;
import java.util.List;

// import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import app.AppGame;
import app.AppInput;
import app.AppPlayer;
import app.AppWorld;
// import app.ui.Button;
// import app.ui.TGDComponent;
// import app.ui.TGDComponent.OnClickListener;
import games.telecomParty.cases.Remi;

public class World extends AppWorld {

	@Deprecated
	private StateBasedGame game;

	private SpiralTrack track;
	private int gridWidth;
	private int gridHeight;
	private int gridGap;
	private int numJoueurEnCours;
	private List<Player> listeJoueurs;
	private ArrayList<Remi> listeRemis; // La liste des cases Rémi Bachelet ! TODO : l'initialiser

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
		this.track = new SpiralTrack(container, this, 70);

		this.numJoueurEnCours = 0; // numéro du joueur à qui c'est le tour
	}

	@Override
	public void update (GameContainer container, StateBasedGame game, int delta) {
		super.update (container, game, delta);
		AppInput appInput = (AppInput) container.getInput ();

		Player joueurEnCours = listeJoueurs.get(numJoueurEnCours);

		if (appInput.isControlPressed(AppInput.BUTTON_A, joueurEnCours.getControllerID())) {

			appInput.clearControlPressedRecord();

			joueurEnCours.playRound(); // Lance le tour du joueur à qui c'est le tour
			numJoueurEnCours ++;
			numJoueurEnCours %= listeJoueurs.size(); // Permet de reboucler
			if (numJoueurEnCours == 0) {	// Lorsque tout le monde a joué
				// TODO : lancer un mini-jeu ici
				System.out.println("mini-jeu !");
			}
		}
		for (Player p: this.listeJoueurs) {
			p.update (container, game, delta);
		}

	}

	@Override
	public void render (GameContainer container,StateBasedGame game, Graphics context) {
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
			this.listeJoueurs.add (new Player (this, appPlayer.getColorID (), appPlayer.getControllerID (), appPlayer.getName (), "/images/telecomParty/pion.png"));
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
