package hub;

import org.newdawn.slick.Image;

public class Case {
	private int id;
	/* liste des cases :
	 * 0 : Normal (sans effet)
	 * 1 : Super-jeu (meilleure récompense & difficulté supérieure)
	 * 2 : Duel (bonus pour le gagnant, malus pour le perdant, le défié choisi le mini jeu dans une petite liste)
	 * 3 : Bonus pour le mini-jeu suivant (valeur du dé x2)
	 * 4 : Collin : recule du nombre de case indiqué par ton prochain dé (dé inversé par rapport à la position)
	 * 5 : Sabeur : si vous voulez : avancez de 2 cases ou faites reculer un adversaire de 2 cases (en appliquant l’effet de la nouvelle case)
	 * 6 : Consolidations : (impossible de bouger jusqu’à gagner un mini-jeu)
	 * 7 : Da Silva : tu peux te faire offrir un café par le dernier joueur
	 * 8 : Tomczak : retour au début de l’année
	 * 9 : Heurtel : la description du mini-jeu suivant n'apparaît qu’0.5 seconde
	 * 10 : Festor : passe ton tour (on ne joue plus après 19h)
	 * 11 : Rémi Bachelet : tp sur une autre case Rémi Bachelet aléatoire
	 */
	
	private Image sprite;
}
