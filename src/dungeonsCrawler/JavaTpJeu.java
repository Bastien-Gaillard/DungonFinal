package dungeonsCrawler;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.CharKey;
import net.slashie.libjcsi.ConsoleSystemInterface;
import net.slashie.libjcsi.wswing.WSwingConsoleInterface;
/**
* https://github.com/slashman/libjcsi librairie Pour la gestion
des entrÃ©es sorties de la console
* @author coupr
*/

public class JavaTpJeu {

	// Gestion de la fin du programme
	public static boolean fin = false;
	//La console grer par la librairie
	public static ConsoleSystemInterface csi;
	//Les dimension utiliser liÃ© a la taille max de la console de 80x25
	public static int minX=0,minY=2,maxX=80,maxY=24;
	//Le score actuel de la partie
	public static int score = 0;
	//La vie actuel de la partie
	public static int vie = 3;
	//Le nombre de pomme mangÃ©e
	public static int pomme = 0;
	//La derniÃ¨re direction du joueur ( up au debut)
	public static String touche = "up";
	//Le niveau actuelle de la partie
	public static int niveauActuelle = 1;
	//Le tire qui est null
	public static EntityTire t;
	//Une map qui garde en mÃ©moire le tire et sa direction
	public static Map<EntityTire, String> tire = new HashMap<EntityTire, String>();
	//Le score des monstre tuÃ©
	public static int monstreMort = 0;
	//Variable utilisé pour l'invincibilité
	public static boolean invincible = false;
	//Durée de l'invincibilité
	public static int duree = 0;
	//Texte afficher de base à l'endroit des bonus
	public static String bonus = "aucun";
	//Variable pour changer l'écriture du bonus
	public static int resetBonus = 0;
	//Couleur de base du texte bonus
	public static CSIColor 	couleur = CSIColor.YELLOW; ;
	public static void main(String... arg) {
	Boolean rejouer = true;
	do {
		//Definitions des propriÃ©tÃ©s de la console pour la font + taille par dÃ©faut a utiliser
		Properties parametres = new Properties();
		parametres.setProperty("fontSize","18");
		parametres.setProperty("font", "SansSerif");
		//CrÃ©ation et initialisation de la console
		csi = new WSwingConsoleInterface("MaFenetre", parametres);
		csi.cls();
		Sound s = new Sound();
		//PrÃ©paration du premier niveau et afficahge des sprites (murs)
		Niveau n = new Niveau1();
		n.display();

		//On save le buffer ca reprÃ©sente tout ce qui est actuellement affichÃ© et devra toujours Ãªtre rÃ©affichÃ©
				
		csi.saveBuffer();
		
		//On change les couleurs des bonus
		n.couleurBonus();

		//On affiche les entitÃ©s (
		n.displayEntites();
		
		//CrÃ©ation de l'entitÃ© Joueur et affectation du niveau actuel, on utilise pour l'instant l'entitÃ© "gÃ©nÃ©rique"
		
		Entity joueur = new Entity(40,12,'\u26c4',CSIColor.YELLOW);
		joueur.setNiveau(n);
		//Tant que la partie n'est pas terminÃ©
		while(!fin) {

			Boolean gameover = false;
			// On restore le buffer
			csi.restore();
			// On affiche les donnÃ©es dynamique
			csi.print(0, 0, "Tokenaide", ConsoleSystemInterface.WHITE);
			csi.print(20,0, "Score : "+score + "/" + niveauActuelle*300, CSIColor.WHITE);
			csi.print(20,1, "Niveau : " +  niveauActuelle + "/ 10", CSIColor.BUFF);
			csi.print(40,0, "Vie = "+ vie, CSIColor.RED);
			csi.print(40,1, "Bonus = "+ bonus, couleur);

			//Si on a 0 de vie, gameover devient vrai, on ce retrouve dans le if d'aprÃ¨s
			if(vie == 0) {
				gameover = true;
			}
			//Quand le joueur a perdu 
			if(gameover) {
				//On cache le joueur
				s.jouerSon("src\\son\\gameOver.wav");
				n.clear(joueur);
				
				//On affiche game over 
				csi.print((maxX / 2) - 5, (maxY / 2), "Game Over", ConsoleSystemInterface.RED);
				csi.print((maxX / 2) - 16, (maxY / 2) + 2, "Q pour quitter et R pour rejouer", ConsoleSystemInterface.WHITE);
				int key = csi.inkey().code;
				switch(key) {
				//Relance la partie si R préssé
					case CharKey.r: case CharKey.R:
						csi.cls();
						score = 0;
						vie = 3;
						joueur.setNiveau(n);
						n.afficherJoueur(joueur);
						
						n.display();

						csi.refresh();
						n.generationAuto();
						n.couleurBonus();
						n.displayEntites();
						gameover = false;
						//n.afficherJoueur(joueur);
					break;
					case CharKey.Q: case CharKey.q: case CharKey.ESC:
						System.exit(0);
					break;
				}
			}
			//Fin de partie quand le joeur a 3200 de score
			if(score >= 200) {
				s.jouerSon("src\\son\\win.wav");
				n.clear(joueur);
				csi.print((maxX / 2) - 5, (maxY / 2), "Bravo, vous avez gagnez", ConsoleSystemInterface.CYAN);
				csi.print((maxX / 2) - 16, (maxY / 2) + 2, "Q pour quitter et R pour rejouer", ConsoleSystemInterface.WHITE);
				int key = csi.inkey().code;
				switch(key) {
					case CharKey.r: case CharKey.R:
						csi.cls();
						score = 0;
						vie = 3;
						pomme = 0;
						bonus = "aucun";
						joueur.setNiveau(n);
						n.afficherJoueur(joueur);
						n.display();
						n.generationAuto();
						csi.refresh();
						gameover = false;
						csi.refresh();
					break;
					case CharKey.Q: case CharKey.q: case CharKey.ESC:
						System.exit(0);
					break;
				}
			}
			int scoreRequis = niveauActuelle * 300;
			//Si le joueur atteint le score nécessaire, on change de niveau
			if(score >= scoreRequis && n.getNumNiveau() == niveauActuelle) {
				int niveauSuivant = niveauActuelle + 1;
				n.clear(joueur);
				Class<Niveau> clazz;
				try {
					clazz = (Class<Niveau>) Class.forName("dungeonsCrawler.Niveau"+niveauSuivant);
					n=(Niveau) clazz.newInstance();
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e1) {
					// TODO Auto-generated catch block
					niveauActuelle = niveauActuelle - 1;
				}
				csi.print(35, 12, "Bravo, vous passé au niveau suivant", ConsoleSystemInterface.WHITE);
				s.jouerSon("src\\son\\levelUp.wav");
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//n = new Niveau2();
				Niveau.playLevel(n,joueur);
				score = 0;
				vie = 3;
				pomme = 0;
				niveauActuelle = niveauActuelle + 1;
				n.couleurBonus();
				n.displayEntites();
				}
			joueur.display();
			
			n.displayEntites();
			//On refresh pour mettre a jour l'affiche de la console

			csi.refresh();
			//Capture touche pour gÃ©rer les commandes
			int key = csi.inkey().code;
			switch(key) {
				case CharKey.UARROW:
					joueur.moveUp();
					touche = "up";
				break;
				case CharKey.DARROW:
					joueur.moveDown();
					touche = "down";
				break;
				case CharKey.LARROW:
					joueur.moveLeft();
					touche = "left";
				break;
				case CharKey.RARROW:
					joueur.moveRight();
					touche = "right";
				break;
				case CharKey.Q: case CharKey.q: case CharKey.ESC:
					fin = true;
				break;
				case CharKey.SPACE:
					//Le tire ce positionne au niveau du joueur
					t = new EntityTire(joueur.getX(),joueur.getY());
					t.setNiveau(n);
					n.addEntity(t);
					tire.put(t, touche);
					s.jouerSon("src\\son\\tireJoueur.wav");
				break;
					}
			//Test aprÃ¨s mouvement si collision avec une entitÃ©		
			Entity e = n.getEntite(joueur.getX(), joueur.getY());
			if(e != null && e instanceof Scorable) {
				score += ((Scorable)e).getScore();
				pomme += ((Scorable)e).getMangerPomme();
				n.supprimerEntites(e);
				s.jouerSon("src\\son\\bouffe.wav");

			}
			
			if(e != null && e instanceof EntityJoueur) {
				if(invincible == false) {
					s.jouerSon("src\\son\\degats.wav");
					vie -= ((EntityJoueur)e).getVie();

				};
				n.supprimerEntites(e);
			}
			if(e != null && e instanceof EntityBonus) {
				n.supprimerEntites(e);
				double r = Math.random();
				if(e.getCouleur() == CSIColor.CYAN) {
					int nbrMonstre = n.bonusMonstre(e);
					score += nbrMonstre;
					couleur = CSIColor.CYAN; 
					bonus = "monstre d\u00e9truit";
					resetBonus = 1;
					s.jouerSon("src\\son\\bonus1.wav");

				} else if (e.getCouleur() == CSIColor.ORANGE_PEEL){
					int scorePomme = n.bonusPomme(e);
					score += scorePomme;
					couleur = CSIColor.ORANGE_PEEL; 
					bonus = "pomme d\u00e9truite";
					resetBonus = 1;
					s.jouerSon("src\\son\\bonus2.wav");

				} else if(r < 1){
					invincible = true;
					s.jouerSon("src\\son\\bonus3.wav");

				}
			}
			//Reinitialiser le bonus ²Ã  aucun 
			if(resetBonus > 0 && resetBonus < 3 ) {
				resetBonus += 1;
			} else if(resetBonus == 3) {
				couleur = CSIColor.YELLOW; 
				bonus = "aucun";
			}
			//Si il est invincible
			if(invincible == true) {
				duree += 1;
				couleur = CSIColor.FUCSHIA_PINK;
				bonus = "invincible durant " + (40 - duree) + " tours";
				if(duree > 40) {
					invincible = false;
					duree = 0;
				}
			}

			//GÃ©rer les tires
			if(t != null) {
				for (Map.Entry<EntityTire, String> entry : tire.entrySet()){
					monstreMort = n.tire(entry.getKey(), entry.getValue());
					score += monstreMort;
				}
			}
			};
			//Changer couleur du joueur selon Ã§a vie
			if( vie < 3) {
				System.out.println("ezjop");
			}
			//Capture touche pour gÃ©rer les commandes
    } while (rejouer == true);
	//Si on est sortie du programme (ESC) on attend l'appuie sur espace pour fermer la fenÃªtre
	csi.print(1,20, "Appuyer sur espace pour terminer");
	csi.refresh();
	csi.waitKey(CharKey.SPACE);
	System.exit(0);
	}

}