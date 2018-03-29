package hub;

import java.util.Random;

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
			int[] typeCases = createTrack();
			this.cases [i] = new Case (plateau, i,coord[0],coord[1],typeCases[i]);
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
	
	public int[] createTrack() {
		// Créer le tableau qui servira à attribuer à chaque case son type de manière prodédurale
		int[] caseType = new int[length];
		int halfSpiral = (int) Math.floor(length/2);
		
		int[] moitie1 = {14,4,5,2,2,2,1,2,2,0}; // moitie1[i] représente le nombre de case du type i de la première moitié de la spirale, nombre adapté pour une spirale de taille 70
		int[] moitie2 = {21,1,0,2,1,1,1,2,0,4};
		
		for (int i=0 ; i < moitie1.length ; i++) {	// Adaptation du nombre de case pour le nombre de cases à attribuer
			moitie1[i] = (int) Math.ceil(moitie1[i] * length/70);
			moitie2[i] = (int) Math.ceil(moitie2[i] * length/70);
		}
		
		caseType = placeCases(1, moitie1, caseType); // Attribution de la première moitié de la spirale
		
		caseType[halfSpiral] = 10; // La case du milieu est une case de type "consolidation"
		
		caseType = placeCases(halfSpiral +1, moitie2, caseType); // Attribution de la deuxième moitié de la spirale
		
		caseType[length -3] = 10; // Une autre conso en fin de spiral
		caseType[length -2] = 11; // La R1 en avant dernière case
		caseType[length -1] = 12; // Arrivée
				
		return caseType;
	}
	
	public int[] placeCases(int debut, int[] moitie, int[] attributionType) {
		// Effectue le tirage sans remise à partir de moitie et le met dans attributionType en commençant à le remplir à partir de la case début
		
		Random r = new Random();
		int sum = 0;
		for (int i = 0 ; i < moitie.length ; i++) {
			sum += moitie[i];
		}
		
		for (int i = debut; i < sum + debut ; i++) {
			int typeAPlacer = r.nextInt(moitie.length);
			
			while(moitie[typeAPlacer] == 0) { // Boucle pour gérer la pénurie d'exemplaire du type qu'on souhaite ajouter
				// En théorie c'est pas une boucle infinie !
				typeAPlacer ++;
				if (typeAPlacer >= moitie.length) { // Si on dépasse le nombre de types différents, alors on reprend au type n°0
					typeAPlacer = 0;
				}	
			}
			attributionType[i] = typeAPlacer; // On place notre exemplaire du typeAPlacer à la case i
			moitie[typeAPlacer] --; // On retire un exemplaire au type qu'on vient de placer
		}
		return attributionType;
	}

}
