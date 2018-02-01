package hub;

public class JoueurPlateau {
	private int place;
	private String couleur;
	
	public JoueurPlateau() {
		place=0;
		couleur="blue";
	}
	
	public void avance(int n) {
		place+=n;
	}

	public int getPlace() {
		return place;
	}

	public void setPlace(int place) {
		this.place = place;
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}
	
}
