package dungeonsCrawler;
import java.util.Properties;

import net.slashie.libjcsi.CharKey;
import net.slashie.libjcsi.ConsoleSystemInterface;
import net.slashie.libjcsi.wswing.WSwingConsoleInterface;
public class Main {
	public static void main(String... arg) {

		//Definitions des propriétés de la console pour la font + taille par défaut a utiliser

		Properties parametres = new Properties();
		parametres.setProperty("fontSize","16");
		parametres.setProperty("font", "Courier");
		boolean fin = false;
		
		//Création et initialisation de la console
		ConsoleSystemInterface csi = new
		WSwingConsoleInterface("MaFenetre", parametres);

		csi.cls();
		csi.print(0, 0, "TP JEUX JAVA", ConsoleSystemInterface.WHITE);

		//On save le buffer ca représente tout ce qui est
		//actuellement affiché et devra toujours être réaffiché

		csi.saveBuffer();
		while(!fin) {
			//On refresh pour mettre à jour l'affiche de la console
	
			csi.refresh();
			//Capture touche pour gérer les commandes
			int key = csi.inkey().code;
				switch(key) {
				case CharKey.Q: case CharKey.q: case
		
				CharKey.ESC:
		
				fin = true;
				break;
			}
		}
		//Si on est sortie du programme (ESC) on attend
		//l'appuie sur espace pour fermer la fenêtre
		csi.print(1,20, "Appuyer sur espace pour terminer");

		csi.refresh();
		csi.waitKey(CharKey.SPACE);
		System.exit(0);
	}
}