package dungeonsCrawler;
import java.util.Properties;

import net.slashie.libjcsi.CharKey;
import net.slashie.libjcsi.ConsoleSystemInterface;
import net.slashie.libjcsi.wswing.WSwingConsoleInterface;
public class Main {
	public static void main(String... arg) {

		//Definitions des propri�t�s de la console pour la font + taille par d�faut a utiliser

		Properties parametres = new Properties();
		parametres.setProperty("fontSize","16");
		parametres.setProperty("font", "Courier");
		boolean fin = false;
		
		//Cr�ation et initialisation de la console
		ConsoleSystemInterface csi = new
		WSwingConsoleInterface("MaFenetre", parametres);

		csi.cls();
		csi.print(0, 0, "TP JEUX JAVA", ConsoleSystemInterface.WHITE);

		//On save le buffer ca repr�sente tout ce qui est
		//actuellement affich� et devra toujours �tre r�affich�

		csi.saveBuffer();
		while(!fin) {
			//On refresh pour mettre � jour l'affiche de la console
	
			csi.refresh();
			//Capture touche pour g�rer les commandes
			int key = csi.inkey().code;
				switch(key) {
				case CharKey.Q: case CharKey.q: case
		
				CharKey.ESC:
		
				fin = true;
				break;
			}
		}
		//Si on est sortie du programme (ESC) on attend
		//l'appuie sur espace pour fermer la fen�tre
		csi.print(1,20, "Appuyer sur espace pour terminer");

		csi.refresh();
		csi.waitKey(CharKey.SPACE);
		System.exit(0);
	}
}