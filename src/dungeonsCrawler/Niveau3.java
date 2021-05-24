package dungeonsCrawler;
import java.util.Random;
/**
* Classe de représentation du niveau 2, la fonction de
generationAuto est surchargé pour générer un level spécifique
* Avec un nombre de pommes déterminé
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
		//Génération des Pommes
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
		//Génération des monstre
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
		//Génération des Bonus
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