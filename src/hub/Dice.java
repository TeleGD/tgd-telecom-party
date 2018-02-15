package hub;

import java.util.Random;
import org.newdawn.slick.Image;

public class Dice {
	
	private int nbFaces; // Nombre de faces d'un de.
	private int[] faces; // Ensemble des valeurs des faces du de (a preciser pour elaborer des des speciaux).
	private int num; // Derniere face sur laquelle le de est tombe.
	
	public Dice(int n, int[] T, int i) {
		this.nbFaces = n;
		this.faces = T;
		this.num = i;
	}
	
	public void roll() {
		// Lance le de.
		Random r = new Random();
		this.num = this.faces[r.nextInt(this.nbFaces)];
	}
	
	public int getValue() {
		// Retourne la derniere valeur du de.
		return this.num;
	}
	
	public int getRolledValue() {
		roll();
		return getValue();
	}
	

}
