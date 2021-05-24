package dungeonsCrawler;

import net.slashie.libjcsi.CSIColor;

/**
* Les entit�s sont des sprites particuliers qui peuvent repr�senter des "objets" ou des "monstres" ou le "joueur"
* Ils sont rattach� a un niveau ce qui leur permet de tester leur environnement pour se deplacer
* On note que les fonctions de d�placements tests la destination pour v�rifier que le d�placement est "possible"
* @author Gaillard
*/
public class EntityTire extends Entity {
	private Niveau niveau;
	public EntityTire(int px, int py) {
		super(px, py, 'B', CSIColor.ELECTRIC_PURPLE);
	}
	public void setNiveau(Niveau n) {
		niveau = n;
	}
	public boolean estSolide() {
		return false;
	}
	/**
	* N'est pas utilis� pour l'instant
	* @return
	*/
	public boolean estMangeable() {
		return false;
	}
	
	public void display() {
		super.display();
	}
	public boolean moveLeft() {
		if(getX() <= JavaTpJeu.minX) return false;
		Sprite voisin = niveau.getCase(getX()-1, getY());
		if(voisin != null && voisin.estSolide()) return false;
		setX(getX()-1);
		return true;
	}
	public boolean moveRight() {
		if(getX() >= JavaTpJeu.maxX-1) return false;
		Sprite voisin = niveau.getCase(getX()+1, getY());
		if(voisin != null && voisin.estSolide()) return false;
		setX(getX()+1);
		return true;
	}
	public boolean moveUp() {
		if(getY() <= JavaTpJeu.minY) return false;
		Sprite voisin = niveau.getCase(getX(), getY()-1);
		if(voisin != null && voisin.estSolide()) return false;
		setY(getY()-1);
		return true;
	}
	public boolean moveDown() {
		if(getY() >= JavaTpJeu.maxY-1) return false;
		Sprite voisin = niveau.getCase(getX(), getY()+1);
		if(voisin != null && voisin.estSolide()) return false;
		setY(getY()+1);
		return true;
	}
}