package hub;

public class WorldPlateau {
	private Case[] chemin;
	private int nbJoueurs;
	private JoueurPlateau[] listeJoueurs;
	private String[] listeCouleurs={"blue","red","yellow","green"};
	
	public WorldPlateau(int n) {
		nbJoueurs=n;
		listeJoueurs = new JoueurPlateau[n];
		for (int i=0;i<n;i++) {
			listeJoueurs[i].setcolor(listeCouleurs[i]);
		}
	}
	
}
