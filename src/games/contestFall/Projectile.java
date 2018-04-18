package games.contestFall;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public class Projectile {

	private int direction;
	private int speed;
	private int x;
	private int y;
	private int posX;
	private int posY;
	private int taille;
	private Player owner;
	private World world;
	private Color couleur;
	private boolean destructed;
	private int imgSize = World.ammo.getWidth();
	
	public Projectile(World w, Player player, int dir, int x, int y, int size) {
		this.owner=player;
		this.world=w;
		this.couleur=owner.getColor();
		this.direction=dir;
		this.x=x;
		this.y=y;
		this.posX=x/size;
		this.posY=y/size;
		this.taille=size;
		this.destructed = false;
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics context) {
		context.drawImage(World.ammo, x-taille/4, y-taille/4, x+taille/4, y+taille/4, 0, 0, imgSize, imgSize, couleur);
	}

	public void update(GameContainer container, StateBasedGame game, int delta) {
		if (!destructed) {
			move();
			if (world.platform.getCell(posX,posY).hasPlayer() && world.platform.getCell(posX,posY).getPlayer()!=owner) {
				world.platform.getCell(posX,posY).getPlayer().forceMove(direction);
				destructed=true;
			}
		}
	}

	private void move() {
		x=x+speed*((-direction+2)%2);
		y=y+speed*((-direction+1)%2);
		posX=x/taille;
		posY=y/taille;
	}
	
}

