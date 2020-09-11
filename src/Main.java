import javax.swing.JFrame;
import javax.swing.JOptionPane;

import app.AppGame;
import app.AppLoader;

public final class Main {

	public static final void main(String[] arguments) {
		String title = "Telecom Party";
		int width = 1280;
		int height = 720;
		boolean fullscreen = false;
		String request = "Voulez-vous jouer en plein écran ?";
		String[] options = new String[] {
			"Oui",
			"Non"
		};
		JFrame frame = new JFrame();
		frame.setIconImage(AppLoader.loadIcon("/images/icon.png").getImage());
		int returnValue = JOptionPane.showOptionDialog(
			frame,
			request,
			title,
			JOptionPane.YES_NO_OPTION,
			JOptionPane.QUESTION_MESSAGE,
			null,
			options,
			options[0]
		);
		frame.dispose();
		if (returnValue == -1) {
			return;
		}
		fullscreen = returnValue == 0;
		new AppGame(title, width, height, fullscreen) {

			@Override
			public void init() {
				this.addState(new pages.Welcome(AppGame.PAGES_WELCOME));
				this.addState(new pages.Games(AppGame.PAGES_GAMES));
				this.addState(new pages.Players(AppGame.PAGES_PLAYERS));
				this.addState(new pages.Pause(AppGame.PAGES_PAUSE));
				this.addState(new games.telecomParty.World(AppGame.GAMES_TELECOM_PARTY_WORLD));
				this.addState(new games.bomberman.World(AppGame.GAMES_BOMBERMAN_WORLD));
				this.addState(new games.clicker.World(AppGame.GAMES_CLICKER_WORLD));
				this.addState(new games.codeFall.World(AppGame.GAMES_CODE_FALL_WORLD));
				this.addState(new games.maze.World(AppGame.GAMES_MAZE_WORLD));
				this.addState(new games.pacmanBattle.World(AppGame.GAMES_PACMAN_BATTLE_WORLD));
				this.addState(new games.pathPainting.World(AppGame.GAMES_PATH_PAINTING_WORLD));
				this.addState(new games.pong.World(AppGame.GAMES_PONG_WORLD));
				this.addState(new games.preciseLock.World(AppGame.GAMES_PRECISE_LOCK_WORLD));
				this.addState(new games.reflex.World(AppGame.GAMES_REFLEX_WORLD));
				this.addState(new games.snake3001.World(AppGame.GAMES_SNAKE3001_WORLD));
				this.addState(new games.t7Maser.World(AppGame.GAMES_T7_MASER_WORLD));
			}

		};
	}

}
