package dungeonsCrawler;
import java.util.Random;
/**
* Classe de repr�sentation du niveau 2, la fonction de
generationAuto est surcharg� pour g�n�rer un level sp�cifique
* Avec un nombre de pommes d�termin�
* @author coupr
*/
public class Niveau3 extends Niveau {
	public Niveau3() {
		super();
		setNumNiveau(3);
	}
	@Override
	public void generationAuto() {
		System.out.println("Override");
        for(int i=JavaTpJeu.minX;i<JavaTpJeu.maxX;i++) {
            for(int j = JavaTpJeu.minY; j < JavaTpJeu.maxY;j++){
                addCase(i, j, new Mur(i,j));
            }
        }
		//G�n�ration des Pommes
		int nbPommes=14;
		while(nbPommes > 0) {
			int x=new Random().nextInt(JavaTpJeu.maxX);
			int y=new Random().nextInt(JavaTpJeu.maxY);
			if(x >= JavaTpJeu.minX && x < JavaTpJeu.maxX && y >= JavaTpJeu.minY && y < JavaTpJeu.maxY) {
				Sprite c = this.getCase(x, y);
				System.out.println(x+","+y);
				if(c == null || !c.estSolide()) {
					EntityPomme p= new EntityPomme(x,y);
					p.setNiveau(this);
					addEntity(p);
					nbPommes--;
				}
			}
		}
		//G�n�ration des monstre
		int nbMonstre= 36;
		while(nbMonstre > 0) {
			int x=new Random().nextInt(JavaTpJeu.maxX);
			int y=new Random().nextInt(JavaTpJeu.maxY);
			if(x >= JavaTpJeu.minX && x < JavaTpJeu.maxX && y >= JavaTpJeu.minY && y < JavaTpJeu.maxY) {
				Sprite c = this.getCase(x, y);
				System.out.println(x+","+y);
				if(c == null || !c.estSolide()) {
					EntityMonstre p= new EntityMonstre(x,y);
					p.setNiveau(this);
					addEntity(p);
					nbMonstre--;
				}
			}
		}
		//G�n�ration des Bonus
		int nbBonus= 16;
		while(nbBonus > 0) {
			int x=new Random().nextInt(JavaTpJeu.maxX);
			int y=new Random().nextInt(JavaTpJeu.maxY);
			if(x >= JavaTpJeu.minX && x < JavaTpJeu.maxX && y >= JavaTpJeu.minY && y < JavaTpJeu.maxY) {
				Sprite c = this.getCase(x, y);
				System.out.println(x+","+y);
				if(c == null || !c.estSolide()) {
					EntityBonus p= new EntityBonus(x,y);
					p.setNiveau(this);
					addEntity(p);
					nbBonus--;
				}
			}
		}
	}
}