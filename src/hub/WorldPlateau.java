package hub;

import java.util.ArrayList;
import java.util.List;

//import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import general.AppGame;
import general.ui.Button;
import general.ui.TGDComponent;
import general.ui.TGDComponent.OnClickListener;

public class WorldPlateau extends BasicGameState {

	private int ID;

	private StateBasedGame game;

	private SpiralTrack track;
	private int gridWidth;
	private int gridHeight;
	private int gridGap;
	private int turnNumber;

	private boolean menu;
	private Button plus;
	private Button moins;
	private Button ok;

	// private int [] cheminEntiers = {0, 0, 0, 0, 0, 0, 0, 0, 1};
	private int nbJoueur;
	private List<JoueurPlateau> listeJoueurs;
	private boolean enterPress;

	public WorldPlateau (int ID) {
		this.ID = ID;
	}

	@Override
	public int getID () {
		return this.ID;
	}

	@Override
	public void init (GameContainer container, StateBasedGame game) {
		WorldPlateau that;
		that = this;

		this.game = game;

		this.track = new SpiralTrack (this, 128);
		gridWidth = 64;
		gridHeight = 64;
		gridGap = 16;

		this.turnNumber = 0; // numéro du tour


		// TODO Auto-generated method stub
		this.menu = true;
		this.nbJoueur = 2;

		this.plus = new Button ("+", container, 700, 50, 20, 20);
		this.plus.setOnClickListener (new OnClickListener () {

			@Override
			public void onClick (TGDComponent component) {
				if (that.nbJoueur < 4) {
					that.nbJoueur++;
				};
			}

		});

		this.moins = new Button ("-", container, 730, 50, 20, 20);
		this.moins.setOnClickListener (new OnClickListener () {

			@Override
			public void onClick (TGDComponent component) {
				if (that.nbJoueur > 1) {
					that.nbJoueur--;
				};
			}

		});

		this.ok = new Button ("Start", container, 500, 100, 80, 20);
		this.ok.setOnClickListener (new OnClickListener () {

			@Override
			public void onClick (TGDComponent component) {
				that.menu = false;
				//TMP : Tant que les joueurs doivent être créés ici :
				initListeJoueurs(nbJoueur);
			}

		});
	}

	@Override
	public void enter (GameContainer container, StateBasedGame game) {
		this.init (container, game);
	}

	@Override
	public void update (GameContainer container, StateBasedGame game, int delta) throws SlickException {
		if (this.menu) {

		} else { // Si le jeu est lancé : on est sur le plateau
			if (enterPress) {
				listeJoueurs.get(turnNumber % nbJoueur).playRound(); // Lance le tour du joueur à qui c'est le tour
				enterPress = false;
				turnNumber ++;
			}
			for (JoueurPlateau p: this.listeJoueurs) {
				p.update (container, game, delta);
			};
		};
	}

	@Override
	public void render (GameContainer container,StateBasedGame game, Graphics context) throws SlickException {
		if (this.menu) {
			context.drawString ("nombre de joueurs : " + nbJoueur, 500, 50);
			this.plus.render (container, game, context);
			this.moins.render (container, game, context);
			this.ok.render (container, game, context);
		} else {
			// Affichage du plateau :
			for (int i = 0; i < this.track.length; i++) {
				track.getCase(i).render(container, game, context);
			}
			// Affichage des joueurs :
			for (JoueurPlateau p: this.listeJoueurs) {
				p.render (container, game, context);
			}
		}
	}
	
	// TMP :
	public void initListeJoueurs(int nbJoueur) {
		listeJoueurs = new ArrayList<>(); // initialisation de listeJoueur

		// TEST JOUEUR
		//TODO Initialisation des joueurs en accord avec ce qui a été selectionné à l'aide des boutons
		for (int i=0 ; i < nbJoueur ; i++) {
			listeJoueurs.add(new JoueurPlateau (this, i, "NOM", "images/player/pion.png"));
		}
	}

	@Override
	public void keyPressed (int key, char c) {
		switch (key) {
			case Input.KEY_ESCAPE:
				this.game.enterState (AppGame.MENUS_MAIN_MENU, new FadeOutTransition (), new FadeInTransition ());
				break;
			case Input.KEY_ENTER:
				enterPress = true ;
				break;
			default:
				super.keyPressed (key, c);
		};
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

}
