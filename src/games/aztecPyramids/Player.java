package games.aztecPyramids;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

public class Player {
	private int floor;

	private int value;

	private int T1;
	private int T2;
	private int T3;
	private boolean onePress;
	private boolean twoPress;
	private boolean threePress;
	private boolean onePressEn;
	private boolean twoPressEn;
	private boolean threePressEn;
	private boolean doitchanger;
	private boolean winner;

	private World world;

	public Player(World world, int T1,int T2, int T3) {
		this.doitchanger = false;
		this.winner = false;
		this.T1 = T1;
		this.T2 = T2;
		this.T3 = T3;
		this.world = world;
		floor = 0;
		onePress = false;
		twoPress = false;
		threePress = false;
		value=0;
	}

	public void update(GameContainer arg0, StateBasedGame arg1, int delta) {

	}

	public void isWinner() {
		if (this.floor == 10){
			this.winner = true;
		}
	}

	public boolean getWinner() {
		return this.winner;
	}

	public void valueChange() {
		if (onePress == true){
			this.value =  1;
		}
		else if (twoPress == true){
			this.value =  2;
		}
		else if (threePress == true){
			this.value =  3;
		}
	}

	public void finalChange(){
		if (this.doitchanger == true) {
			this.value = 0;
		}
		this.doitchanger = false;
	}

	public void afterChange() {
		onePress = false;
		twoPress = false;
		threePress = false;
	}

	public int getValue() {
		return this.value;
	}

	public void isEqual(Player P) {
		System.out.println("A");
		System.out.println(this.value);
		if (this.value == P.getValue()){
			this.doitchanger=true;
		}
		System.out.println("B");
		System.out.println(this.doitchanger);
	}

	public void climb(){
		System.out.println("indigo");
		System.out.println(this.value);
		this.floor = this.floor + this.value;
		System.out.println(this.floor);
	}

	public int getFloor() {
		return this.floor;
	}

	public void keyReleased(int key, char c) {}

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

	public String test1(){
		return ("toto");
	}

	public String test2(){
		return ("moto");
	}

	public void keyPressed(int key, char c) {
		if (threePressEn == true && twoPressEn == true && onePressEn == true){
			if (key == T3){
				threePress = true;
				threePressEn = false;
			} else if (key == T1) {
				System.out.println(test1());
				onePress = true;
				onePressEn = false;
			} else if (key == T2) {
				System.out.println(test2());
				twoPress = true;
				twoPressEn = false;
			}
		}
	}

}
