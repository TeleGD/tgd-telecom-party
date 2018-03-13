package menus;

import java.util.ArrayList;
import java.util.Arrays;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import general.utils.FontUtils;

/**
 *
 * Pour faire un menu c'est simple,
 * il suffit de faire une classe qui herite de celle la et de
 * reseiner via les setters, les params TitrePrincipal, titreSecondaire
 * et les items. Et C'est tout
 * Vous recevrez l'index de l'item selectionné dans la méthode
 * onOptionItemSelected.
 *
		super.setTitrePrincipal("TELE-ARCADE DESIGN");
		super.setTitreSecondaire("Menu Principal");
		super.setItems("Jouer","Editeur", "Quitter");

		super.setEnableClignote(true);
		super.setCouleurClignote(Color.red);
		super.setTempsClignote(400);
 *
 *on peut add/remove item maintenant
 *ajouter autant d'item qu'on veut
 *
 *@author Jérôme
 */

public abstract class Menu extends BasicGameState {

	static private Font fontTitrePrincipal = FontUtils.loadFont ("PressStart2P.ttf", java.awt.Font.BOLD, 40, false);
	static private Font fontTitreSecondaire = FontUtils.loadFont ("Kalinga", java.awt.Font.BOLD, 24, true);
	static private Font fontItem = FontUtils.loadFont ("Kalinga", java.awt.Font.BOLD, 14, true);
	static protected Font fontConfirmText = FontUtils.loadFont ("PressStart2P.ttf", java.awt.Font.PLAIN, 20, false); // TODO: private ?

	private int itemHeight;
	private int startMenuY;
	private int endMenuY;
	private int visibleItemsMax;

	protected String titrePrincipal;
	protected String titreSecondaire;
	protected String bottomText;
	protected ArrayList <String> items = new ArrayList <String> ();

	protected long tempsClignote;
	protected Color couleurClignote;
	protected boolean enableClignote;

	protected GameContainer container;
	protected StateBasedGame game;
	protected long time; // TODO: private or remove ?

	private int indexItemPlusGrand;
	private int selection;
	private int decalage;

	public Menu () {}

	@Override
	public void init (GameContainer container, StateBasedGame game) throws SlickException {
		this.itemHeight = 30;
		this.startMenuY = container.getHeight () / 2 - 130;
		this.endMenuY = container.getHeight () - 200;
		this.visibleItemsMax = (this.endMenuY - this.startMenuY) / this.itemHeight - 3;
		this.titrePrincipal = "INSERER TITRE ICI";
		this.titreSecondaire = "INSERER SOUS-TITRE ICI";
		this.bottomText = "PRESS ENTER";
		this.items = new ArrayList <String> ();
		this.tempsClignote = 400;
		this.couleurClignote = Color.red;
		this.enableClignote = false;
		this.indexItemPlusGrand = 0;
		this.selection = 0;
		this.decalage = 0;

		this.container = container;
		this.game = game;
		this.time = System.currentTimeMillis ();
		container.setShowFPS (false);
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		int width = arg0.getWidth ();

		g.setBackground(Color.black);

		renderTitrePrincipal(arg0,arg1,g);
		renderTitreSecondaire(arg0,arg1,g);
		renderMenusItems(arg0,arg1,g);
		renderSelectionItem(arg0,arg1,g,selection);

		g.setColor(Color.white);
		g.drawRect(width/2-300, this.startMenuY, 600,37);

		g.drawRect(width/2-300, this.endMenuY, 600,37);

		g.setFont(fontConfirmText);
		g.drawString(bottomText, width/2-fontConfirmText.getWidth(bottomText)/2, 530);
	}

	public void renderSelectionItem(GameContainer arg0, StateBasedGame arg1, Graphics g,int position) {
		if(items==null)return;
		int width = arg0.getWidth ();
		if(enableClignote){
			if((System.currentTimeMillis()-time)%(2*tempsClignote)<=tempsClignote)g.setColor(Color.white);
			else g.setColor(couleurClignote);
		}else{
			g.setColor(couleurClignote);
		}
		g.drawString(">>", width/2-fontItem.getWidth(items.get(indexItemPlusGrand))/2-35, getYMenu() + this.itemHeight * (position-decalage));
		g.drawString("<<", width/2+fontItem.getWidth(items.get(indexItemPlusGrand))/2+25, getYMenu() + this.itemHeight * (position-decalage));
	}

	public void renderTitrePrincipal(GameContainer arg0, StateBasedGame arg1, Graphics g) {
		int width = arg0.getWidth ();
		g.setColor(Color.red);
		g.setFont(fontTitrePrincipal);
		g.drawString(titrePrincipal,(width-fontTitrePrincipal.getWidth(titrePrincipal))/2 , 120);
		g.setColor(Color.white);
		g.setFont(fontTitrePrincipal);
		g.drawString(titrePrincipal,(width-fontTitrePrincipal.getWidth(titrePrincipal))/2+4 , 122);
	}

	public void renderTitreSecondaire(GameContainer arg0, StateBasedGame arg1, Graphics g) {
		int width = arg0.getWidth ();
		g.setFont(fontTitreSecondaire);
		g.drawString(titreSecondaire, width/2-fontTitreSecondaire.getWidth(titreSecondaire)/2, 232);
	}

	public void renderMenusItems(GameContainer arg0, StateBasedGame arg1, Graphics g) {
		if(items==null)return;
		int width = arg0.getWidth ();

		g.setColor(Color.white);

		for (int i = decalage; i < Math.min(items.size(),decalage+this.visibleItemsMax); i++) {
			g.setFont(fontItem);
			g.drawString(this.items.get(i), width/2-fontItem.getWidth(items.get(indexItemPlusGrand))/2, getYMenu() + this.itemHeight * (i-decalage));
		}
	}

	private int getYMenu() {
		return this.startMenuY+37+(this.endMenuY-this.startMenuY-37)/2-this.itemHeight*Math.min(this.visibleItemsMax,items.size())/2;
	}

	@Override
	public void keyPressed(int key, char c) {
		super.keyPressed(key, c);
		//time=System.currentTimeMillis();
		switch (key) {
		//case Input.KEY_NUMPAD2:
		case Input.KEY_DOWN:
			if (selection < items.size() - 1)
				selection++;
			else
				selection = 0;
			if(selection>=this.visibleItemsMax){
				decalage=selection-this.visibleItemsMax+1;
			}else decalage=0;
			onOptionItemFocusedChanged(selection);
			break;
		//case Input.KEY_NUMPAD8:
		case Input.KEY_UP:
			if (selection > 0)
				selection--;
			else
				selection = items.size() - 1;
			if(selection>=this.visibleItemsMax){
				decalage=selection-this.visibleItemsMax+1;
			}else decalage=0;
			onOptionItemFocusedChanged(selection);
			break;
		case Input.KEY_ENTER:
			onOptionItemSelected(selection);
			break;

		case Input.KEY_ESCAPE:
			//exit();
			break;
		}
	}

	public abstract void onOptionItemFocusedChanged(int position);

	public abstract void onOptionItemSelected(int position);

	public String getTitrePrincipal() {
		return titrePrincipal;
	}

	public void setTitrePrincipal(String titrePrincipal) {
		this.titrePrincipal = titrePrincipal;
	}

	public String getTitreSecondaire() {
		return titreSecondaire;
	}

	public void setTitreSecondaire(String titreSecondaire) {
		this.titreSecondaire = titreSecondaire;
	}

	public ArrayList<String> getItems() {
		return items;
	}

	public void setItems(String... itemsLoc) {
		this.items = new ArrayList<String>(Arrays.asList(itemsLoc));
		calculerPlusGrandItem();
	}

	private void calculerPlusGrandItem() {
		indexItemPlusGrand=0;
		for(int i=0;i<items.size();i++){
			if(items.get(indexItemPlusGrand).length()<items.get(i).length()){
				indexItemPlusGrand=i;
			}
		}
	}

	public void setFontTitrePrincipal(String name, int type, int size, boolean isSystemFont) {
		fontTitrePrincipal=FontUtils.loadFont(name,type,size,isSystemFont);
	}

	public void setFontTitreSecondaire(String name, int type, int size, boolean isSystemFont) {
		fontTitreSecondaire=FontUtils.loadFont(name,type,size,isSystemFont);
	}

	public void setFontItem(String name, int type, int size, boolean isSystemFont) {
		fontItem=FontUtils.loadFont(name,type,size,isSystemFont);
	}

	public void setEnableClignote(boolean b) {
		enableClignote=b;
	}

	public void setTempsClignote(long timeEnMillisecond){
		this.tempsClignote=timeEnMillisecond;
	}

	public void setCouleurClignote(Color coul){
		this.couleurClignote=coul;
	}

	public void addItem(String titre) {
		items.add(titre);
		calculerPlusGrandItem();
	}

	public void removeItemAtIndex(int index) {
		items.remove(index);
		calculerPlusGrandItem();
	}

	public void removeAllItems() {
		items.removeAll(items);
	}

	public void setBottomText(String text){
		this.bottomText=text;
	}

	public String getBottomText(){
		return bottomText;
	}

}
