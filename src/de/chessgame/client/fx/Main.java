package de.chessgame.client.fx;

import javafx.application.Application;

/**
 * Enth�lt die main-Methode die die launch-Methode der Klasse Application aufruft.
 * Die in dieser Klasse enthaltene main-Mehtode dient als Einstiegspunkt in die Anwendung.
 * <br><br>
 * <u><b>HINWEIS</b></u><br>
 * Das Programm verf�gt �ber einen vollst�ndigen user-support. D.h. es ist nicht m�glich nicht-legale
 * Z�ge auszuf�hren. Um ein vollwertiges Schachspiel zu sein, fehlt einzig die Funktion einen Bauern umwandeln
 * zu k�nnen, wenn dieser das Brett vollst�ndig �berquert hat.
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
