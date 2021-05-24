package dungeonsCrawler;
import static dungeonsCrawler.JavaTpJeu.csi;
import net.slashie.libjcsi.CSIColor;

public class Rules {
	public void regles() {
		csi.print(1, 1, "Le joueur : ⛄", CSIColor.NAVAJO_WHITE);
		csi.print(1, 3, "Les monstres : ☀", CSIColor.RED_VIOLET);
		csi.print(1, 5, "Les pommes : ❅", CSIColor.WHITE);
		csi.print(1, 7, "Les bonus : ★", CSIColor.YELLOW);
		csi.print(1, 9, "Les touches : ", CSIColor.NAVAJO_WHITE);
		csi.print(31, 10, "↑", CSIColor.NAVAJO_WHITE);
		csi.print(1, 11, "Les flèches directionelles : ← →", CSIColor.NAVAJO_WHITE);
		csi.print(31, 12, "↓", CSIColor.NAVAJO_WHITE);
		csi.print(1, 13, "Espace pour tirer", CSIColor.NAVAJO_WHITE);
	}
}