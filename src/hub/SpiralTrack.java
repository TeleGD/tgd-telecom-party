package hub;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import app.AppGame;
import hub.cases.Remi;

public class SpiralTrack {

	private World plateau;
	public final int length;
	private Case [] cases;
	private int dx;
	private int dy;

	public SpiralTrack (World plateau, int length) {
		assert length >= 0;
		this.plateau = plateau;
		this.length = length;
		this.cases = new Case [length];
		// Décalage pour centrer la spirale
		dx = (AppGame.width - this.plateau.getGridWidth()) / 2;
		dy = (AppGame.height - this.plateau.getGridHeight()) / 2;

		ArrayList<Integer> typeCases = createTrack();

		for (int i = 0; i < length; i++) {
			int [] coord = getCoordinates(i);
			int type = typeCases.get(i);
			switch(type) {
				case 0 : this.cases [i] = new Case (plateau, i,coord[0],coord[1],type); break;
				case 1 : this.cases [i] = new Remi (plateau, i,coord[0],coord[1],type); 
						plateau.getListeRemis().add((Remi) this.cases[i]);
						break;
				
				default : this.cases [i] = new Case (plateau, i,coord[0],coord[1],type); break;
					
			}
		}

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

	public ArrayList<Integer> createTrack() {
		// Créer le tableau qui servira à attribuer à chaque case son type de manière prodédurale
		ArrayList<Integer> caseType = new ArrayList<Integer>();
		int halfSpiral = (int) Math.floor(length/2);

		int[] moitie1 = {4,5,2,2,2,1,2,2,0}; // moitie1[i] représente le nombre de case du type i de la première moitié de la spirale, nombre adapté pour une spirale de taille 70
		int[] moitie2 = {1,0,2,1,1,1,2,0,4};

		for (int i=0 ; i < moitie1.length ; i++) {	// Adaptation du nombre de case pour le nombre de cases à attribuer
			moitie1[i] = (int) Math.floor(moitie1[i] * (double) length/70);
			moitie2[i] = (int) Math.floor(moitie2[i] * (double) length/70);
		}

		caseType.add(0);	// TODO Case de début (0 pour l'instant)
		placeCases(moitie1, caseType, halfSpiral-1); // Attribution de la première moitié de la spirale

		caseType.add(10); // La case du milieu est une case de type "consolidation"

		placeCases(moitie2, caseType, length -4); // Attribution de la deuxième moitié de la spirale

		caseType.add(10);// Une autre conso en fin de spiral
		caseType.add(11);// La R1 en avant dernière case

		caseType.add(12); // Arrivée

		return caseType;
	}

	public void placeCases(int[] moitie, ArrayList<Integer> attributionType, int fin){

		//TODO : Ajouter les cases 0 pour eviter les outOfBound
		int debut = attributionType.size();
		for (int i = 0 ; i < moitie.length ; i++) {		// On place dans l'ordre les toutes les cases dont on dispose dans attributionType
			while(moitie[i] > 0) {
				attributionType.add(i+1);
				moitie[i] --;
			}
		}

		while( attributionType.size() < fin +1) {
			attributionType.add(0);
		}

		Collections.shuffle(attributionType.subList(debut, fin + 1));
	}
}
