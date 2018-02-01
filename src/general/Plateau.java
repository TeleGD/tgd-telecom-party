package general;

public class Plateau {
	private Case[] chemin;
	private int nbJoueurs;
	private JoueurPlateau[] listeJoueurs;
	private String[] listeCouleurs={"blue","red","yellow","green"};
	
	public Plateau(int n) {
		nbJoueurs=n;
		listeJoueurs = new JoueurPlateau[n];
		for (int i=0;i<n;i++) {
			listeJoueurs[i].setCouleur(listeCouleurs[i]);
		}
	}
	
}
