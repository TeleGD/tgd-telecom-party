import org.newdawn.slick.SlickException;

import general.AppContainer;
import general.AppGame;

public class Main {

	public static void main (String [] arguments) throws SlickException {
		AppGame appGame = new AppGame ("Projet_2018 #NoName");
		AppContainer container = new AppContainer (appGame, 1280, 720, false);
		//System.out.println (container.getInput ());
		container.setTargetFrameRate (60);
		container.setVSync (true);
		container.setShowFPS (true);
		container.start ();
	}

}
