package dungeonsCrawler;
import net.slashie.libjcsi.CSIColor;
/**
* Il s'agit d'une entitÃƒÂ© particuliere qui est scorable donc qui
donne des points au joueur si il marche dessus.
* On la fait bouger, c'est principalement pour montrer le
fonctionnement que pourrait avoir le dÃƒÂ©placement d'une entitÃƒÂ©.
* Chaque type d'entitÃƒÂ© pourrait avoir sa propre logique de
mouvement
* @author coupr
*/
public class EntityMonstre extends Entity implements EntityJoueur {
	public EntityMonstre(int px, int py) {
		super(px, py, '\u2600', CSIColor.RED);
		// TODO Auto-generated constructor stub
	}
	public void moveRandom() {
		double r = Math.random();
		if(r<0.2) moveLeft();
		else if(r<=0.4) moveRight();
		else if(r<=0.6) moveUp();
		else if(r<=0.8) moveDown();
	}
	public void display() {
		moveRandom();
		super.display();
	}
	public boolean estMangeable() {
		return false;
	}
	public boolean attaqueJoueur() {
		return true;
	}
	@Override
	public int getVie() {
		// TODO Auto-generated method stub
		return 1;
	}
	@Override
	public int getScore() {
		// TODO Auto-generated method stub
		return 10;
	}
}