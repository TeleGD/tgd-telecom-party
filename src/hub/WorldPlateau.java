package hub;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import general.Main;
import general.World;
import general.ui.Button;
import general.ui.TGDComponent;
import general.ui.TGDComponent.OnClickListener;


public class WorldPlateau extends BasicGameState {

	public static SpiralTrack track;
	private int gridWidth;
	private int gridHeight;
	private int gridGap;
	private int playerHeight, playerWidth;
	
	public static int ID=7;
	public static String name = "Jeu de plateau";
	
	private boolean menu;
	private Button plus, moins, ok;

	// private int[] cheminEntiers={0,0,0,0,0,0,0,0,1};
	private int nbJoueur;
	private JoueurPlateau[] listeJoueurs;
	
	@Override
	public void init(GameContainer container, StateBasedGame arg1) throws SlickException {
		this.track = new SpiralTrack (128);
		this.gridWidth = 64;
		this.gridHeight = 64;
		this.gridGap = 16;
		playerHeight = gridHeight - 10;
		playerWidth = gridWidth - 10;
		
		listeJoueurs = new JoueurPlateau[1]; // 1 joueurs max
		
		// TEST JOUEUR
		listeJoueurs[0] = new JoueurPlateau(0, "NOM", "images/player/pion.png", playerHeight, playerWidth);
		
		// TODO Auto-generated method stub
		menu=true;
		nbJoueur=1;
		
		plus = new Button("+",container,700,50,20,20);
		plus.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(TGDComponent component) {
				if (nbJoueur<4) {
					nbJoueur++;
				}

			}});
		
		moins = new Button("-",container,730,50,20,20);
		moins.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(TGDComponent component) {
				if (nbJoueur>1) {
					nbJoueur--;
				}

			}});
		
		ok = new Button ("Start", container, 500, 100, 80, 20);
		ok.setOnClickListener (new OnClickListener () {
			@Override
			public void onClick (TGDComponent component) {
				menu = false;
			};
		});

	}
	
	
	public void render(GameContainer container,StateBasedGame game, Graphics g) throws SlickException {
		if (menu) {
			g.drawString("nombre de joueurs : " + nbJoueur, 500, 50);
			plus.render(container, game, g);
			moins.render(container, game, g);
			ok.render(container, game, g);
		} else {
			
			// Affichage du plateau :
			int width = this.gridWidth - this.gridGap / 2;
			int height = this.gridHeight - this.gridGap / 2;
			int dx = (Main.width - this.gridWidth) / 2;
			int dy = (Main.height - this.gridHeight) / 2;
			int radius = this.gridGap / 2;
			Color backgroundColor = new Color (255, 0, 0);
			Color textColor = new Color (127, 0, 0);
			for (int i = 0; i < this.track.length; i++) {
				int [] xy = this.track.getCoordinates (i);
				g.setColor (backgroundColor);
				g.fillRoundRect ((float) xy [0] * this.gridWidth + dx, (float) xy [1] * this.gridHeight + dy, width, height, radius);
				g.setColor (textColor);
				g.drawString ("[" + i + "]", xy [0] * this.gridWidth + dx, xy [1] * this.gridHeight + dy);
			}
			// Fin d'affichage du plateau
			// Affichage des joueurs :
			for(JoueurPlateau p : listeJoueurs){
				p.render(container,game,g);
			}
			
			;
		}
	}
	
	public void update(GameContainer container,StateBasedGame game, int delta) throws SlickException {
		if (menu) {
			
		} else {
			
			// Update des joueurs :
			for(JoueurPlateau p : listeJoueurs){
				p.update(container,game,delta,this);
			}
			
		}
	}
	
	public static void reset(){
	}
	
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return ID;
	}
	
	public int getGridWidth() {
		return gridWidth;
	}

	public int getGridHeight() {
		return gridHeight;
	}
}
