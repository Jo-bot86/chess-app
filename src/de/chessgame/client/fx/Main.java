package de.chessgame.client.fx;

import javafx.application.Application;

/**
 * Enth�lt die main-Methode die die launch-Methode der Klasse Application aufruft.
 * Die in dieser Klasse enthaltene main-Mehtode dient als Einstiegspunkt in die Anwendung.
 * <br><br>
 * <u><b>HINWEIS</b></u><br>
 * Das Programm enth�lt noch nicht alle Reglen. So ist es noch m�glich invalide Z�ge zu machen.
 * Z.b. ist ein K�nig in der Lage sich selbst ins Schach zu stellen. Das muss somit vom Benutzer selbst
 * vermieden werden. Dar�ber hinaus ist eine Figur die gepinnt ist, ebenfalls in der Lage ihren Posten zu
 * verlassen und somit den K�nig sich selbst zu �berlassen. Au�erdem wird nicht erkannt, dass
 * eine Figur den K�nig sch�tzen kann, indem sie die Schachgebende Figur blockt. Diese Figur hat dann keinen
 * validen Zug und ist nicht frei gegeben um sie zu bewegen. Desweiteren ist es noch nicht m�glich eine Bauern
 * en passant zu schlagen sowie eine Rochade auszuf�hren.
 * @author Josef Weldemariam
 *
 */
public class Main {
	/**
	 * Startpunkt der Klasse SchachApp
	 * @param args
	 */
	public static void main(String[] args) {
		Application.launch(de.chessgame.client.fx.SchachApp.class);
	}	
}
