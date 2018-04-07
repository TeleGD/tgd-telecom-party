import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import general.AppGame;

public class Main {

	public static void main (String [] arguments) throws SlickException {
		AppGame game = new AppGame ("Projet_2018 #NoName");
		AppGameContainer container = new AppGameContainer (game, 1280, 720, false);
		container.setTargetFrameRate (60);
		container.setVSync (true);
		container.setShowFPS (true);
		container.start ();
	}

}
