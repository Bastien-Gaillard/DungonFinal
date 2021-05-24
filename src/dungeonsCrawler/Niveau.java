package dungeonsCrawler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import static dungeonsCrawler.JavaTpJeu.csi;

import net.slashie.libjcsi.CSIColor;
/**
* Le niveau représente la carte d'un level du jeux. Elle contient
des cases qui sont les sprites de type mur par exemple
* Et listeENtite qui sont les objets / créatures qui se trouvent
dans le niveau
* @author coupr
*/
public class Niveau {
	private ArrayList listeEntite;
	private int numNiveau;
    private Map<Integer , Integer> coordonnee = new HashMap<Integer ,Integer>();
    private List<String> visite =  new ArrayList<String>() ;
    private Map<Integer, Map> lignes = new HashMap<Integer, Map>();
    private ArrayList<Sprite> cases = new ArrayList<Sprite>();
	private ArrayList<CSIColor> listeCouleur = new ArrayList<CSIColor>();

	public Niveau() {
		listeEntite = new ArrayList();
		generationAuto();
	}
	//Pour ajouter un sprite dans le tableau des cases
	public void addCase(int x, int y, Sprite s) {
        cases.add(s);
        if(!lignes.containsKey(y)) {
            Map<Integer, Sprite> ligne = new HashMap<Integer, Sprite>();
            ligne.put(x, s);
            lignes.put(y, ligne);
        } else {
            Map<Integer, Sprite> ligne = lignes.get(y);
            ligne.put(x, s);
        }
	}
	//Pour ajotuer une entité dans le tableau des entités
	public void addEntity(Entity e) {
		listeEntite.add(e);
	}
	//Génération automatique d'un level avec des murs et des pommes aléatoire
	public void generationAuto() {
        System.out.println("Override");
        for(int i=JavaTpJeu.minX;i<JavaTpJeu.maxX;i++) {
            for(int j = JavaTpJeu.minY; j < JavaTpJeu.maxY;j++){
                addCase(i, j, new Mur(i,j));
            }
        }
		for(int i=JavaTpJeu.minX+1;i<JavaTpJeu.maxX-1;i++) {
			for(int j=JavaTpJeu.minY+1;j<JavaTpJeu.maxY-1;j++) {
				double r = Math.random();
				if(r <= 0.05)
					cases.add(new Mur(i,j));
				else if(r <= 0.1) {
					EntityPomme p= new EntityPomme(i,j);
					p.setNiveau(this);
					listeEntite.add(p);
					EntityMonstre m= new EntityMonstre(i,j);
					m.setNiveau(this);
					listeEntite.add(m);
					EntityBonus b= new EntityBonus(i,j);
					b.setNiveau(this);
					listeEntite.add(b);
				}
			}
		}

	}
	//lance l'affichage des Sprites
	public void display() {
		for(Iterator<Sprite> it = cases.iterator();it.hasNext();) {
			Sprite s = it.next();
			s.display();
		}
	}
	//Lance l'affichage des entités
	public void displayEntites() {
		for(Iterator<Sprite> it =
			listeEntite.iterator();it.hasNext();) {
			Sprite s = it.next();
			s.display();
		}
	}
	//Permet de récupérer le sprite présent sur une case donnée
	public Sprite getCase(int x, int y) {
		for(Iterator<Sprite> it = cases.iterator();it.hasNext();) {
			Sprite s = it.next();
			if(s.getX()==x && s.getY()==y) return (Mur)s;
		}
		return null;
	}
	//permet de récupérer l'entité présente sur une case donnée
	public Entity getEntite(int x, int y) {
		for(Iterator<Sprite> it = listeEntite.iterator();it.hasNext();) {
			Sprite s = it.next();
			if(s.getX()==x && s.getY()==y) return (Entity)s;
		}
		return null;
	}
	//Supprimer une entité de la liste
	public void supprimerEntites(int x, int y) {
		Entity e = getEntite(x, y);
		if(e != null) listeEntite.remove(e);
	}
	//Polymorphisme paramétrique pour supprimer l'entité quand on la connait
	public void supprimerEntites(Entity e) {
		listeEntite.remove(e);
	}
	//Polymorphisme paramétrique pour supprimer l'entité quand on la connait
	public void supprimerMur() {
		cases.clear();
	}
	public void cacherJoueur(Entity j) {
		CSIColor c = CSIColor.BLACK;
		j.setCouleur(c);
	}
	public void afficherJoueur(Entity j) {
		CSIColor c = CSIColor.YELLOW;
		j.setCouleur(c);
		j.setX(40);
		j.setY(12);
	}
	public void clear(Entity joueur) {
		//On supprime l'affichage et on rafraichie
		csi.cls();
		csi.refresh();
		//On suprime toutes les entites de la map
		for(int i = JavaTpJeu.minX; i < JavaTpJeu.maxX; i++) {
			for(int j = JavaTpJeu.minY; j < JavaTpJeu.maxY; j++) {
				Entity lesPommesEtMonstre = this.getEntite(i, j);
				this.supprimerEntites(lesPommesEtMonstre);
			}
		}
		this.supprimerMur();
		this.cacherJoueur(joueur);
		csi.refresh();
	}
	public static void playLevel(Niveau n, Entity joueur) {
			csi.refresh();
			csi.cls();
			
			csi.refresh();
			joueur.setNiveau(n);
			n.display();
			//On save le buffer ca représente tout ce qui est actuellement affiché et devra toujours être réaffiché	
			csi.saveBuffer();
			n.afficherJoueur(joueur);	
	}
	public int getNumNiveau() {
		return numNiveau;
	}
	public void setNumNiveau(int numNiveau) {
		this.numNiveau = numNiveau;
	}
	public int tire(EntityTire t,String derniereTouche) {
		
		int x = t.getX();
		int y = t.getY();
		int score = 0;
		Sound s = new Sound();
		Sprite voisin = null;
		Entity e = this.getEntite(x, y);
		if(derniereTouche == "up") {
			t.setC('\u2b99');		
			t.moveUp();
			voisin = this.getCase(x, y - 1);
		} else if(derniereTouche == "down") {
			t.setC('\u2b9b');
			t.moveDown();
			voisin = this.getCase(x, y + 1);
		} else if(derniereTouche == "left") {
			t.setC('\u2b98');
			t.moveLeft();
			voisin = this.getCase(x - 1, y);
		} else if(derniereTouche == "right") {
			t.setC('\u2b9a');
			t.moveRight();
			voisin = this.getCase(x + 1, y);
		}
		if(e != null && e instanceof EntityJoueur) {
			this.supprimerEntites(e);
			this.supprimerEntites(t);
			score += ((EntityMonstre)e).getScore();
			s.jouerSon("src\\son\\explosion.wav");
		} else if((x <= JavaTpJeu.minX) || (x >= JavaTpJeu.maxX - 1) || (y <= JavaTpJeu.minY) || (y >= JavaTpJeu.maxY - 1) || (voisin != null && voisin.estSolide())) {
			this.supprimerEntites(t);
		} else if(voisin != null && voisin.estSolide()) {
			this.supprimerEntites(t);
		}
		return score;
	}
	public int bonusPomme(Entity e) {
		int score = 0;
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				Entity e1 = this.getEntite(e.getX() + i, e.getY() + j);
				Entity e2 = this.getEntite(e.getX() + i, e.getY() - j);
				Entity e3 = this.getEntite(e.getX() - i, e.getY() + j);
				Entity e4 = this.getEntite(e.getX() - i, e.getY() - j);
				if(e1 != null && e1 instanceof Scorable) {
					this.supprimerEntites(e1.getX(), e1.getY());
					score += ((Scorable)e1).getScore();

				}
				if(e2 != null && e2 instanceof Scorable) {
					this.supprimerEntites(e2.getX(), e2.getY());
					score += ((Scorable)e2).getScore();
				}
				if(e3 != null && e3 instanceof Scorable) {
					this.supprimerEntites(e3.getX(), e3.getY());
					score += ((Scorable)e3).getScore();
				}
				if(e4 != null && e4 instanceof Scorable) {
					this.supprimerEntites(e4.getX(), e4.getY());
					score += ((Scorable)e4).getScore();
				}
				System.out.println("Pomme à la fin : " + score);
				System.out.println("\n");
			}
		}
		return score;
	}
	public int bonusMonstre(Entity e) {
		int score = 0;
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				Entity e1 = this.getEntite(e.getX() + i, e.getY() + j);
				Entity e2 = this.getEntite(e.getX() + i, e.getY() - j);
				Entity e3 = this.getEntite(e.getX() - i, e.getY() + j);
				Entity e4 = this.getEntite(e.getX() - i, e.getY() - j);
				if(e1 != null && e1 instanceof EntityMonstre) {
					this.supprimerEntites(e1.getX(), e1.getY());
					score += ((EntityMonstre)e1).getScore();
				}
				if(e2 != null && e2 instanceof EntityMonstre) {
					this.supprimerEntites(e2.getX(), e2.getY());
					score += ((EntityMonstre)e2).getScore();
				}
				if(e3 != null && e3 instanceof EntityMonstre) {
					this.supprimerEntites(e3.getX(), e3.getY());
					score += ((EntityMonstre)e3).getScore();
				}
				if(e4 != null && e4 instanceof EntityMonstre) {
					this.supprimerEntites(e4.getX(), e4.getY());
					score += ((EntityMonstre)e4).getScore();
				}
			}
		}
		return score;
	}
	public int bonusInvincible(Entity e, Niveau n, int vie) {
		int duree = 0;
		int sauvegarde = 0;
		if(e != null && e instanceof EntityMonstre && duree < 200) {
			vie = sauvegarde;
		}
		duree += duree;
		return vie;
	}
	public void generationLabyrinthe() {
		Mur c= (Mur) ((lignes.get(2)).get(2));
        Mur c2 = null;
        int x = c.getX();
        int y = c.getY();
        int x2 = c.getX();
        int y2 = c.getY();
        int fin = 1;
        c.visite(true);
        c.setSolid(false);
        c.affiche();
        while(fin < 440) {
            double r2 = Math.random();
            double r3 = Math.random();
            //	Choisi al�atoirement la direction du prochain passagez
            if(r2 < 0.25) {
                x2 = x+1;
                x = x + 2;
                y2 = y;
            } else if( r2 < 0.5){
                x2 = x-1;
                x = x - 2;
                y2 = y;
            } else if(r2 < 0.75) {
                y2 = y+1;
                y = y + 2;
                x2 = x;
            } else {
                y2 = y-1;
                y = y-2;
                x2 = x;
            }
            //Si x ou y est trop grand, on le r�adapte
            if(x <= JavaTpJeu.minX ) {
                x = JavaTpJeu.minX;
            } else if( x >= JavaTpJeu.maxX) {
                x = JavaTpJeu.maxX - 2;
            }

            if(y <= JavaTpJeu.minY) {
                y = JavaTpJeu.minY;
            } else if(y >= JavaTpJeu.maxY) {
                y = JavaTpJeu.maxY - 2;
            }
            if(x2 <= JavaTpJeu.minX ) {
                x2 = JavaTpJeu.minX;
            } else if( x2 >= JavaTpJeu.maxX) {
                x2 = JavaTpJeu.maxX - 1;
            }

            if(y2 <= JavaTpJeu.minY) {
                y2 = JavaTpJeu.minY;
            } else if(y2 >= JavaTpJeu.maxY) {
                y2 = JavaTpJeu.maxY - 1;
            }
            
            c = (Mur) ((lignes.get(y)).get(x));
            c2 = (Mur) ((lignes.get(y2)).get(x2));

            //Si la case n'a pas �tait visit�e, on dis qu'elle a �tait visit� et on enleve �a solidit� 
            if(c.getVisite() == false) {
                c.visite(true);
                c.setSolid(false);
                c2.setSolid(false);
                fin = fin + 1;
            }
            //Onactualise l'affichage
           
            c.affiche();
            c2.affiche();

        }

    }
	public void couleurBonus() {
		for(int i = JavaTpJeu.minX; i < JavaTpJeu.maxX; i++) {
			for(int j = JavaTpJeu.minY; j < JavaTpJeu.maxY; j++) {
				double r = Math.random();
				Entity e = this.getEntite(i, j);
				if(e != null && e instanceof EntityBonus) {
					if( r < 0.2) {
						e.setCouleur(CSIColor.FUCSHIA_PINK);
						//entity.setC('\u2738');
					} else if(r < 0.6) {
						e.setCouleur(CSIColor.CYAN);
						//entity.setC('\u2726');
					} else if(r < 1) {
						e.setCouleur(CSIColor.ORANGE_PEEL);
					}

				}
			}
		}
	}
	
}