package general;

public class SkeletonPlayer {
	/*
	 * Classe servant à transporter les informations nécessaires à la création des joueurs dans les minijeux et dans le plateau
	 * Un SkeletonPlayer servira à la création d'un joueur
	 */

	private String color, name;
	
	public SkeletonPlayer(String color, String name) {
		
		this.color =color;
		this.name = name;		
	}

	public String getColor() {
		return color;
	}

	public String getName() {
		return name;
	}

}
