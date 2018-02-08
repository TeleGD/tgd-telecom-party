package hub;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import general.World;
import general.ui.Button;
import general.ui.TGDComponent;
import general.ui.TGDComponent.OnClickListener;


public class WorldPlateau extends BasicGameState {
	
	static int gridWidth;
	static int gridHeight;
	
	public static int ID=7;
	public static String name = "Jeu de plateau";
	
	private boolean menu;
	private Button plus,moins;
	
	private Case[] chemin;
	private int[] cheminEntiers={0,0,0,0,0,0,0,0,1};
	private int nbJoueur;
	private JoueurPlateau[] listeJoueurs;
	
	@Override
	public void init(GameContainer container, StateBasedGame arg1) throws SlickException {
		// TODO Auto-generated method stub
		menu=true;
		nbJoueur=1;
		
		plus = new Button("+",container,700,50,20,20);
		plus.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(TGDComponent componenent) {
				if (nbJoueur<4) {
					nbJoueur++;
				}

			}});
		
		moins = new Button("-",container,730,50,20,20);
		moins.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(TGDComponent componenent) {
				if (nbJoueur>1) {
					nbJoueur--;
				}

			}});

	}
	
	
	public void render(GameContainer container,StateBasedGame game, Graphics g) throws SlickException {
		if (menu) {
			g.drawString("nombre de joueurs : " + nbJoueur, 500, 50);
			plus.render(container, game, g);
			moins.render(container, game, g);
		} else {
			
		}
	}
	
	public void update(GameContainer container,StateBasedGame game, int delta) throws SlickException {
		if (menu) {
			
		} else {
			
		}
	}
	
	public static void reset(){
	}
	
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return ID;
	}
}
