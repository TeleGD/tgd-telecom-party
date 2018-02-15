package hub;

import org.newdawn.slick.SlickException;

public class SpiralTrack {
	
	public final int length;
	private Case [] cases;
	
	public SpiralTrack (int length) throws SlickException {
		assert length >= 0;
		this.length = length;
		this.cases = new Case [length];
		for (int i = 0; i < length; i++) {
			this.cases [i] = new Case (i); // TODO: add effect
		}
	}
	
	public Case getCase (int i) {
		return this.cases [i];
	}
	
	public int [] getCoordinates (int i) {
		int n = (int) (Math.sqrt (1 + 2 * i) - 1) / 2;
		int i0 = 2 * n * (n + 1);
		int d0 = 2 * (n + 1);
		int d = i - i0 - d0;
		int [] c = new int [2];
		c [0] = c [1] = (int) ((n + 1) / 2) * 2;
		c [1] -= d0;
		// c [1] = ~c [1];
		c [d < 0 ? 1 : 0] -= d;
		c [n % 2] *= -1;
		return c;
	}
	
}
