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
public class EntityPomme extends Entity implements Scorable {
	public EntityPomme(int px, int py) {
		super(px, py, '\u2746', CSIColor.WHITE);
	}
	public void moveRandom() {
		double r = Math.random();
		if(r<0.1) moveLeft();
		else if(r<=0.2) moveRight();
		else if(r<=0.3) moveUp();
		else if(r<=0.4) moveDown();
	}
	public void display() {
		moveRandom();
		super.display();
	}
	public boolean estMangeable() {
		return true;
	}
	@Override
	public int getScore() {
		// TODO Auto-generated method stub
		return 100;
	}
	@Override
	public int getMangerPomme() {
		// TODO Auto-generated method stub
		return 1;
	}

}