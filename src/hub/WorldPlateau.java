package hub;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class WorldPlateau {
	private Case[] chemin;
	private int[] cheminEntiers={0,0,0,0,0,0,0,0,1};
	private int nbJoueurs;
	private JoueurPlateau[] listeJoueurs;
	
	public WorldPlateau(int n) throws SlickException {
		nbJoueurs=n;
		listeJoueurs = new JoueurPlateau[n];
		for (int i=0;i<n;i++) {
			listeJoueurs[i].setSprite(new Image("images/pion" + i));
		}
		for (int i=0;i<cheminEntiers.length;i++) {
			chemin[i]=new Case(cheminEntiers[i]);
		}
	}
	
	public void render(GameContainer container,StateBasedGame game, Graphics g) throws SlickException {
		
	}
	
	public void update(GameContainer container,StateBasedGame game, int delta) throws SlickException {
		
	}
}
