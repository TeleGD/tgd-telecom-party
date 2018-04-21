package hub.cases;

import java.util.Random;

import hub.Case;
import hub.Player;
import hub.World;

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
