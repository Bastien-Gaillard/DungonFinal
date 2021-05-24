package dungeonsCrawler;
import net.slashie.libjcsi.CSIColor;
/**
* La class Mur, est un Sprite particulier prÃƒÂ©dÃƒÂ©fini, et qui sera solide (non traversable)
* @author Gaillard
*/
public class Mur extends Sprite {
	
	Boolean solid = true;
	Boolean visite = false;
	
	public Mur(int px, int py) {
		super(px,py,'\u25a0',CSIColor.BLUE);
	}
	public boolean estSolide() {
		return solid;
	}

	public void visite(Boolean visite) {
		this.visite = visite;
	}

	public boolean getVisite() {
		return visite;
	}

	public void setSolid(Boolean solid) {
		this.solid = solid;
	}
	public void couleurRandom() {
		
	}
	public void couleurMur() {
		
	}
	public void affiche() {


		if (!solid) {
			this.setCouleur(CSIColor.BLACK);
		} else {
			if (visite) {
				this.setCouleur(CSIColor.BLACK);
			}
			this.display();
			JavaTpJeu.csi.refresh();
		}
	}
		
}