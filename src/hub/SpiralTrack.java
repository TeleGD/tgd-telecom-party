package hub;

import general.AppGame;

public class SpiralTrack {

	private WorldPlateau plateau;
	public final int length;
	private Case [] cases;
	private int dx;
	private int dy;

	public SpiralTrack (WorldPlateau plateau, int length) {
		assert length >= 0;
		this.plateau = plateau;
		this.length = length;
		this.cases = new Case [length];
		// Décalage pour centrer la spirale
		dx = (AppGame.width - this.plateau.getGridWidth()) / 2;
		dy = (AppGame.height - this.plateau.getGridHeight()) / 2;

		for (int i = 0; i < length; i++) {
			int [] coord = getCoordinates(i);
			this.cases [i] = new Case (plateau, i,coord[0],coord[1],0); // TODO: Ajouter la génération procédurale des cases
		};


	}

	public Case getCase (int i) {
		return this.cases [i];
	}

	public int [] getCoordinates (int i) {
		// Renvoit dans le tableau c la valeur des coordonnées x et y de la case n°i
		int [] c = new int [2];

		int n = (int) (Math.sqrt (1 + 2 * i) - 1) / 2;
		int i0 = 2 * n * (n + 1);
		int d0 = 2 * (n + 1);
		int d = i - i0 - d0;
		c [0] = c [1] = (int) ((n + 1) / 2) * 2;
		c [1] -= d0;
		// c [1] = ~c [1];
		c [d < 0 ? 1 : 0] -= d;
		c [n % 2] *= -1;

		// Application des décalages pour rencentrer les cases :
		c[0] = c[0] * this.plateau.getGridWidth() + dx;
		c[1] = c[1] * this.plateau.getGridHeight() + dy;

		return c;
	}

}
