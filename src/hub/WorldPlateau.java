package hub;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class WorldPlateau {
	private Case[] chemin;
	private int nbJoueurs;
	private JoueurPlateau[] listeJoueurs;
	private String[] listeCouleurs={"blue","red","yellow","green"};
	
	public WorldPlateau(int n) throws SlickException {
		nbJoueurs=n;
		listeJoueurs = new JoueurPlateau[n];
		for (int i=0;i<n;i++) {
			listeJoueurs[i].setSprite(new Image("images/pion" + i));
		}
	}
	
}
