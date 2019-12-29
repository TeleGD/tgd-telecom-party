package games.telecomParty.cases;

import java.util.Random;

import games.telecomParty.Case;
import games.telecomParty.Player;
import games.telecomParty.World;

public class Remi extends Case {

	private Random r;

	public Remi(World plateau, int id, int x, int y, int type) {
		super(plateau, id, x, y, type);
		r = new Random();
	}

	public void doEffect(Player p) {
		int remiToGo = r.nextInt(plateau.getListeRemis().size());
		Remi caseToGo = plateau.getListeRemis().get(remiToGo);
		p.setPlace(caseToGo.getId()
				);
		System.out.println("Remi bouge !");
	}

}
