package games.snake;

import general.AppGame;
import general.AppInput;
import general.Playable;
import general.utils.FontUtils;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;

public class World extends BasicGameState implements Playable {
	
    private int ID;
    
    public int nbcasesh;
    public int nbcasesl;
    public int longueur;
    public int hauteur;

    private float widthBandeau;
    private ArrayList<Bonus> bonus;
    private ArrayList<Snake> snakes;
    private ArrayList<Snake> morts;  

    private TrueTypeFont font = FontUtils.loadSystemFont("Arial", java.awt.Font.BOLD,20);

	private boolean jeuTermine;

    static Sound sonMouette;
    static Sound sonSncf;
    static Sound sonChute;
    static Sound sonCheval;
    static Sound sonEclair;
    static Sound sonMagic;
    static Sound sonMartien;
    static Sound sonPerdu;
    private static Music soundMusicBackground;
    
    static {
    	try {
	    	sonMouette = new Sound("musics/Snake/seagulls-chatting.ogg");
	        sonSncf = new Sound("musics/Snake/0564.ogg");
	        sonChute = new Sound("musics/Snake/0477.ogg");
	        sonCheval = new Sound("musics/Snake/horse-whinnies.ogg");
	        sonEclair = new Sound("musics/Snake/ChargedLightningAttack8-Bit.ogg");
	        sonMagic = new Sound("musics/Snake/FreezeMagic.ogg");
	        sonMartien = new Sound("musics/Snake/martian-gun.ogg");
	        sonPerdu = new Sound("musics/Snake/perdu.ogg");
	        soundMusicBackground=new Music("musics/Snake/hymne_russe.ogg");
    	} catch (SlickException e) {
    	}
    }
    
    public World (int ID) {
		this.ID = ID;
	}

	@Override
	public int getID() {
		return this.ID;
	}
    
    @Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
	}
    
    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        soundMusicBackground.loop(1,0.3f);
    }

    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {

        for(int i=0;i<bonus.size();i++){
            bonus.get(i).render(container, game, g);
        }


        for(int i=0;i<snakes.size();i++){
            snakes.get(i).render(container, game, g);
            g.setColor(Color.black);
        }

        g.setColor(new Color(150,150,150));
        g.fillRect(longueur-widthBandeau+2,0,widthBandeau,hauteur);
        g.setColor(new Color(170,170,170));
        g.fillRect(longueur-widthBandeau+4,0,widthBandeau,hauteur);
        g.setColor(new Color(200,200,200));
        g.fillRect(longueur-widthBandeau+6,0,widthBandeau,hauteur);

        g.setFont(font);
        g.setColor(Color.black);
        g.drawString("SNAKE 3000 ! ",longueur-widthBandeau+20,20);

        g.setColor(new Color(150,150,150));
        g.fillRect(longueur-widthBandeau+6,60,widthBandeau,5);
        g.resetFont();

        if(jeuTermine){
            g.setColor(Color.black);
            g.fillRoundRect(longueur/2-75-widthBandeau/2,hauteur/2-50,
            		150,100,20);
            g.setColor(Color.white);
            g.fillRoundRect(longueur/2-75+4-widthBandeau/2,hauteur/2-50+4,150-8,92,20);
            g.setColor(Color.black);
            g.setFont(font);
            g.drawString("Fin du jeu", longueur/2-42-widthBandeau/2,hauteur/2-15);
            for(int i=morts.size()-1; i>=0 ;i--){
                g.setColor(morts.get(i).couleur);
                g.drawString(morts.get(i).nom+" : "+morts.get(i).score,longueur-widthBandeau+20,100+50*i+20);
            }
        } else {
        	for(int i=0;i<snakes.size();i++){
                g.setColor(snakes.get(i).couleur);
                g.drawString(snakes.get(i).nom+" : "+snakes.get(i).score,longueur-widthBandeau+20,100+50*i+20);
            }
        }

    }

    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
    	AppInput appInput = (AppInput) container.getInput ();
		AppGame appGame = (AppGame) game;
		int gameMasterID = appGame.appPlayers.get (0).getControllerID ();
		if (appInput.isKeyPressed (AppInput.KEY_ESCAPE) || appInput.isButtonPressed (AppInput.BUTTON_PLUS, gameMasterID)) {
			soundMusicBackground.stop();
			game.enterState (general.AppGame.MENUS_GAMES_MENU, new FadeOutTransition (), new FadeInTransition ());
		} else {
	    	if(!jeuTermine){
	    	   	jeuTermine = isFini();
	           	addBonus();
	           	for(int i=0;i<snakes.size();i++) {
	                Snake snake = snakes.get(i);
	
	                snake.GScore(1);
	                snake.update(container, game, delta);
	
	                for (int j = 0; j < bonus.size(); j++) {
	                	bonus.get(j).update(container, game, delta);
	                    if (!snakes.get(i).mort) {
	                        if (bonus.get(j).isInBonus(snakes.get(i).body.get(0))) {
	                            applyBonus(bonus.get(j), snakes.get(i));
	                            bonus.remove(j);
	                            j--;
	                        }
	                    }
	                }
	
	                for (int j = 0; j < snakes.size(); j++) {
	
	                    if (j != i) {
	                        if (!snakes.get(i).mort) {
	                            if (collide(snake.body.get(0), snakes.get(j),false)) {
	                                snake.meurt();
	                            }
	                        }
	                    }
	                }
	            }
	    	}
	    	if (jeuTermine && morts.size()==0) {
	    		for(int i=0;i<snakes.size();i++){
	                if(!snakes.get(i).mort)snakes.get(i).GScore(200);
	            }
	    		Snake tri[] = new Snake[snakes.size()];
				for (int i=0; i<snakes.size(); i++) {
					tri[i] = snakes.get(i);
				}
				for (int i=tri.length-1 ; i>0 ; i--) {
					for (int j=0; j<i ; j++) {
						if (tri[j+1].score<tri[j].score) {
							Snake tmp = tri[j+1];
							tri[j+1] = tri[j];
							tri[j]=tmp;
						}
					}
				}
				morts = new ArrayList<Snake>(Arrays.asList(tri));
	    	}
	    }
    }

    private void applyBonus(Bonus bonus, Snake snake ) {
        bonus.applyBonus(snake);

        if(bonus.type == Bonus.bonusType.bInverseBonus){
           for(int i= 0;i<snakes.size();i++){
               if(!snakes.get(i).equals(snake)){
                    snakes.get(i).inverse = !snakes.get(i).inverse;
               }
           }
        }
    }

    private boolean collide(Point point, Snake snake, boolean exceptHead) {

        for(int i=exceptHead?3:0;i<snake.body.size();i++)
        {
            if(snake.body.get(i).x==point.x && snake.body.get(i).y==point.y){
                if(i==0)snakes.get(i).meurt();
                return true;
            }
        }
        return false;
    }

    public void addBonus(Bonus bonusLoc)
    {
        bonus.add(bonusLoc);
    }

    private void addBonus(){
        Random r =  new Random();
        if(r.nextFloat() >= 0.99){
            bonus.add(Bonus.RandomBonus(this, new Point(r.nextInt(nbcasesl)-28,r.nextInt(nbcasesh))));
        }
    }

    public boolean isFini() {

        int compt = 0;

        if(snakes.size()==1){
            if(snakes.get(0).mort)return true;
            else return false;
        }

        for(int i=0;i<snakes.size();i++){
            if(!snakes.get(i).mort)compt++;
        }

        if(compt<=1)return true;

        return false;
    }

	@Override
	public void initPlayers(GameContainer container, StateBasedGame game) {
		AppGame appGame = (AppGame) game;
		int nJoueur = appGame.appPlayers.size();
		longueur = container.getWidth();
        hauteur = container.getHeight();
        nbcasesh = hauteur/10;
        nbcasesl = longueur/10;
        widthBandeau = longueur-1000;
		this.snakes = new ArrayList<Snake>();
		this.morts = new ArrayList<Snake>();
		for (int i=0 ; i<nJoueur ; i++) {
			snakes.add(new Snake(this, (100-nJoueur)/(nJoueur+1) + i*((100-nJoueur)/(nJoueur+1)+1), appGame.appPlayers.get(i)));
		}
		this.bonus = new ArrayList <Bonus> ();
		this.jeuTermine=false;
	}
}
