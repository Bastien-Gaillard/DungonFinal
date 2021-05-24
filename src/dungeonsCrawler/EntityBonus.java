package dungeonsCrawler;
import java.util.ArrayList;

import net.slashie.libjcsi.CSIColor;
/**
* Il s'agit d'une entitÃƒÂ© particuliere qui donne un bonus au joueur si il marche dessus
* @author Gaillard
*/

public class EntityBonus extends Entity {

	public EntityBonus(int px, int py) {
		super(px, py, '★', CSIColor.ALICE_BLUE);
	}
	public void display() {
		super.display();
	}
	public boolean estMangeable() {
		return true;
	}
	@Override
	public int getScore() {
		// TODO Auto-generated method stub
		return 10;
	}
}
