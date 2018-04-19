package games.aztecPyramids;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Player {
	private int floor;

	private int value;

	private int T1;
	private int T3;
	private int T5;
	private boolean onePress;
	private boolean twoPress;
	private boolean threePress;
	private boolean onePressEn;
	private boolean twoPressEn;
	private boolean threePressEn;

	private World world;

	public Player(World world, int T1,int T3, int T5) {

		this.T1 = T1;
		this.T3 = T3;
		this.T5 = T5;
		this.world = world;
		floor = 0;
		onePress = false;
		twoPress = false;
		threePress = false;
		value=0;

	}

	public void update(GameContainer arg0, StateBasedGame arg1, int delta) throws SlickException {

	}

	public void valueChange() {
		if (onePress == true){
			this.value =  1;
		}
		else if (twoPress == true){
			this.value =  3;
		}
		else if (threePress == true){
			this.value =  5;
		}
	}

	public void afterChange() {
		onePress = false;
		twoPress = false;
		threePress = false;
		}

	public int getValue() {
		return this.value;
	}

	public void isEqual(Player P1,Player P2) {
		if (P1.getValue() == P2.getValue()){
			P1.value=0;
			P2.value=0;
		}
	}

	public void climb(){
		this.floor = this.floor + this.value;
	}

	public int getFloor() {
		return this.floor;
	}

	public void keyReleased(int key, char c) {

	}

	public void keyEnabled() {
			threePressEn = true;
			onePressEn = true;
			twoPressEn = true;
}

	public void keyDisabled() {

		threePressEn = false;
		onePressEn = false;
		twoPressEn = false;
}

		public void keyPressed(int key, char c) {

		if (threePressEn == true && twoPressEn == true && onePressEn == true){
			if (key == T5){
				threePress = true;
				threePressEn = false;
			}
			else if (key == T1) {
				onePress = true;
				onePressEn = false;
			}
			else if (key == T3) {
				twoPress = true;
				twoPressEn = false;
			}
		}

	}

	}
