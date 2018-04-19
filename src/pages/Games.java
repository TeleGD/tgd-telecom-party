package pages;

import java.util.Arrays;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import app.AppGame;
import app.AppMenu;
import app.elements.MenuItem;

public class Games extends AppMenu {

	private int ID;

	public Games (int ID) {
		this.ID = ID;
	}

	@Override
	public int getID () {
		return this.ID;
	}

	@Override
	public void init (GameContainer container, StateBasedGame game) {
		super.initSize (container, game, 600, 400);
		super.init (container, game);
		this.setTitle ("Mini-jeux");
		this.setSubtitle ("Sans sous-titre");
		this.setMenu (Arrays.asList (new MenuItem [] {
			new MenuItem (AppGame.TITLES [AppGame.GAMES_AZTECPYRAMIDS_WORLD]) {
				public void itemSelected () {
					game.enterState (AppGame.GAMES_AZTECPYRAMIDS_WORLD);
				}
			},
			new MenuItem (AppGame.TITLES [AppGame.GAMES_BATTLE_WORLD]) {
				public void itemSelected () {
					Players players = (Players) game.getState (AppGame.PAGES_PLAYERS);
					players.setPreviousID (AppGame.PAGES_GAMES);
					players.setNextID (AppGame.GAMES_BATTLE_WORLD);
					game.enterState (AppGame.PAGES_PLAYERS);
				}
			},
			new MenuItem (AppGame.TITLES [AppGame.GAMES_BOMBERMAN_WORLD]) {
				public void itemSelected () {
					Players players = (Players) game.getState (AppGame.PAGES_PLAYERS);
					players.setPreviousID (AppGame.PAGES_GAMES);
					players.setNextID (AppGame.GAMES_BOMBERMAN_WORLD);
					game.enterState (AppGame.PAGES_PLAYERS);
				}
			},
			new MenuItem (AppGame.TITLES [AppGame.GAMES_CLICKER_WORLD]) {
				public void itemSelected () {
					Players players = (Players) game.getState (AppGame.PAGES_PLAYERS);
					players.setPreviousID (AppGame.PAGES_GAMES);
					players.setNextID (AppGame.GAMES_CLICKER_WORLD);
					game.enterState (AppGame.PAGES_PLAYERS);
				}
			},
			new MenuItem (AppGame.TITLES [AppGame.GAMES_CONTESTFALL_WORLD]) {
				public void itemSelected () {
					Players players = (Players) game.getState (AppGame.PAGES_PLAYERS);
					players.setPreviousID (AppGame.PAGES_GAMES);
					players.setNextID (AppGame.GAMES_CONTESTFALL_WORLD);
					game.enterState (AppGame.PAGES_PLAYERS);
				}
			},
			new MenuItem (AppGame.TITLES [AppGame.GAMES_KOMTUVE_WORLD]) {
				public void itemSelected () {
					game.enterState (AppGame.GAMES_KOMTUVE_WORLD);
				}
			},
			new MenuItem (AppGame.TITLES [AppGame.GAMES_MAZE_WORLD]) {
				public void itemSelected () {
					Players players = (Players) game.getState (AppGame.PAGES_PLAYERS);
					players.setPreviousID (AppGame.PAGES_GAMES);
					players.setNextID (AppGame.GAMES_MAZE_WORLD);
					game.enterState (AppGame.PAGES_PLAYERS);
				}
			},
			new MenuItem (AppGame.TITLES [AppGame.GAMES_PONG_WORLD]) {
				public void itemSelected () {
					Players players = (Players) game.getState (AppGame.PAGES_PLAYERS);
					players.setPreviousID (AppGame.PAGES_GAMES);
					players.setNextID (AppGame.GAMES_PONG_WORLD);
					game.enterState (AppGame.PAGES_PLAYERS);
				}
			},
			new MenuItem (AppGame.TITLES [AppGame.GAMES_SNAKE_WORLD]) {
				public void itemSelected () {
					Players players = (Players) game.getState (AppGame.PAGES_PLAYERS);
					players.setPreviousID (AppGame.PAGES_GAMES);
					players.setNextID (AppGame.GAMES_SNAKE_WORLD);
					game.enterState (AppGame.PAGES_PLAYERS);
				}
			},
			new MenuItem (AppGame.TITLES [AppGame.GAMES_T7LASER_WORLD]) {
				public void itemSelected () {
					Players players = (Players) game.getState (AppGame.PAGES_PLAYERS);
					players.setPreviousID (AppGame.PAGES_GAMES);
					players.setNextID (AppGame.GAMES_T7LASER_WORLD);
					game.enterState (AppGame.PAGES_PLAYERS);
				}
			},
			new MenuItem ("Retour") {
				public void itemSelected () {
					game.enterState (AppGame.PAGES_TITLES);
				}
			}
		}));
		this.setHint ("SELECT A GAME");
	}

}
