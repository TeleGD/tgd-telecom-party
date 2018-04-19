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
	private int imgSize = World.ammo.getWidth();
	
	public Projectile(World w, Player player, int dir, int x, int y, int size) {
		this.owner=player;
		this.world=w;
		this.couleur=owner.getColor();
		this.direction=dir;
		this.x=x;
		this.y=y;
		this.posX=(x-w.startX)/size;
		this.posY=(y-w.startY)/size;
		this.taille=size;
		this.speed=10;
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics context) {
		context.drawImage(World.ammo, x-taille/4, y-taille/4, x+taille/4, y+taille/4, 0, 0, imgSize, imgSize, couleur);
	}

	public void update(GameContainer container, StateBasedGame game, int delta) {
		move();
		if (posX<=0 || posY<=0 || posX>=world.platform.getSize()-1 || posY>=world.platform.getSize()-1) {
			world.projectiles.remove(this);
		} else if (world.platform.getCell(posX,posY).hasPlayer() && world.platform.getCell(posX,posY).getPlayer()!=owner) {
			world.platform.getCell(posX,posY).getPlayer().forceMove(direction);
			world.projectiles.remove(this);
		}
	}

	private void move() {
		x=x+speed*((-direction+2)%2);
		y=y+speed*((-direction+1)%2);
		this.posX=(x-world.startX)/taille;
		this.posY=(y-world.startY)/taille;
	}
}

