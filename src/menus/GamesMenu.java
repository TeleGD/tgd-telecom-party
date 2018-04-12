package menus;

import java.util.Arrays;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import general.AppGame;

import menus.ui.Menu;
import menus.ui.MenuItem;

public class GamesMenu extends Menu {

	private int ID;

	public GamesMenu (int ID) {
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
		this.setTitle ("MINI JEUX");
		this.setSubtitle ("CHOISISSEZ UN MINI JEU");
		this.setMenu (Arrays.asList (new MenuItem [] {
			new MenuItem (AppGame.TITLES [AppGame.GAMES_BATTLE_WORLD]) {
				public void itemSelected () {
					PlayersMenu playersMenu = (PlayersMenu) game.getState (AppGame.MENUS_PLAYERS_MENU);
					playersMenu.setPreviousID (AppGame.MENUS_GAMES_MENU);
					playersMenu.setNextID (AppGame.GAMES_BATTLE_WORLD);
					game.enterState (AppGame.MENUS_PLAYERS_MENU);
				}
			},
			new MenuItem (AppGame.TITLES [AppGame.GAMES_AZTEC_PYRAMIDS_WORLD]) {
				public void itemSelected () {
					game.enterState (AppGame.GAMES_AZTEC_PYRAMIDS_WORLD);
				}
			},
			new MenuItem (AppGame.TITLES [AppGame.GAMES_PONG_MULTI_WORLD]) {
				public void itemSelected () {
					PlayersMenu playersMenu = (PlayersMenu) game.getState (AppGame.MENUS_PLAYERS_MENU);
					playersMenu.setPreviousID (AppGame.MENUS_GAMES_MENU);
					playersMenu.setNextID (AppGame.GAMES_PONG_MULTI_WORLD);
					game.enterState (AppGame.MENUS_PLAYERS_MENU);
				}
			},
			new MenuItem (AppGame.TITLES [AppGame.GAMES_KOMTUVE_WORLD]) {
				public void itemSelected () {
					game.enterState (AppGame.GAMES_KOMTUVE_WORLD);
				}
			},
			new MenuItem (AppGame.TITLES [AppGame.GAMES_T7LASER_WORLD]) {
				public void itemSelected () {
					PlayersMenu playersMenu = (PlayersMenu) game.getState (AppGame.MENUS_PLAYERS_MENU);
					playersMenu.setPreviousID (AppGame.MENUS_GAMES_MENU);
					playersMenu.setNextID (AppGame.GAMES_T7LASER_WORLD);
					game.enterState (AppGame.MENUS_PLAYERS_MENU);
				}
			},
			new MenuItem (AppGame.TITLES [AppGame.GAMES_SNAKE_WORLD]) {
				public void itemSelected () {
					PlayersMenu playersMenu = (PlayersMenu) game.getState (AppGame.MENUS_PLAYERS_MENU);
					playersMenu.setPreviousID (AppGame.MENUS_GAMES_MENU);
					playersMenu.setNextID (AppGame.GAMES_SNAKE_WORLD);
					game.enterState (AppGame.MENUS_PLAYERS_MENU);
				}
			},
			new MenuItem (AppGame.TITLES [AppGame.GAMES_CLICKER_WORLD]) {
				public void itemSelected () {
					PlayersMenu playersMenu = (PlayersMenu) game.getState (AppGame.MENUS_PLAYERS_MENU);
					playersMenu.setPreviousID (AppGame.MENUS_GAMES_MENU);
					playersMenu.setNextID (AppGame.GAMES_CLICKER_WORLD);
					game.enterState (AppGame.MENUS_PLAYERS_MENU);
				}
			},
			new MenuItem (AppGame.TITLES [AppGame.GAMES_REFLEX_WORLD]) {
				public void itemSelected () {
					PlayersMenu playersMenu = (PlayersMenu) game.getState (AppGame.MENUS_PLAYERS_MENU);
					playersMenu.setPreviousID (AppGame.MENUS_GAMES_MENU);
					playersMenu.setNextID (AppGame.GAMES_REFLEX_WORLD);
					game.enterState (AppGame.MENUS_PLAYERS_MENU);
				}
			},
			new MenuItem ("Retour") {
				public void itemSelected () {
					game.enterState (AppGame.MENUS_MAIN_MENU);
				}
			}
		}));
		this.setHint ("PRESS ENTER");
	}

}
