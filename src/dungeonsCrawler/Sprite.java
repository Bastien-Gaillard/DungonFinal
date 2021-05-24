package dungeonsCrawler;
import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.ConsoleSystemInterface;
/**
* La classe sprite permet de gérer les éléments qui s'afficheront
a l'écran, on a besoin pour l'affichage
* d'un caractère, c'une couleur et d'une position
* La méthode abstraite estSolide, permettra de différencier les
sprites traversable, des murs.
* @author coupr
*/
public abstract class Sprite {
	private int x, y;
	private char c;
	private CSIColor couleur;
	public Sprite(int px, int py, char carac, CSIColor col) {
	this.x = px;
	this.y = py;
	this.c = carac;
	this.couleur = col;
	}
	
	public ConsoleSystemInterface getCsi() {
		return JavaTpJeu.csi;
	}
	public void display() {
		getCsi().print(x, y, c, (CSIColor)couleur);
	}
	public abstract boolean estSolide();
		public int getX() {
	return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public char getC() {
		return c;
	}
	public void setC(char c) {
		this.c = c;
	}
	public CSIColor getCouleur() {
		return couleur;
	}
	public void setCouleur(CSIColor couleur) {
		this.couleur = couleur;
	}
}