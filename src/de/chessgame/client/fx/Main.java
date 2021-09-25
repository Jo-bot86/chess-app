package de.chessgame.client.fx;

import javafx.application.Application;

/**
 * Enthält die main-Methode die die launch-Methode der Klasse Application aufruft.
 * Die in dieser Klasse enthaltene main-Mehtode dient als Einstiegspunkt in die Anwendung.
 * <br><br>
 * <u><b>HINWEIS</b></u><br>
 * Das Programm enthält noch nicht alle Reglen. So ist es noch möglich invalide Züge zu machen.
 * Z.b. ist ein König in der Lage sich selbst ins Schach zu stellen. Das muss somit vom Benutzer selbst
 * vermieden werden. Darüber hinaus ist eine Figur die gepinnt ist, ebenfalls in der Lage ihren Posten zu
 * verlassen und somit den König sich selbst zu überlassen. Außerdem wird nicht erkannt, dass
 * eine Figur den König schützen kann, indem sie die Schachgebende Figur blockt. Diese Figur hat dann keinen
 * validen Zug und ist nicht frei gegeben um sie zu bewegen. Desweiteren ist es noch nicht möglich eine Bauern
 * en passant zu schlagen sowie eine Rochade auszuführen.
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
