package de.chessgame.client.fx;

import javafx.application.Application;

/**
 * Enthält die main-Methode die die launch-Methode der Klasse Application aufruft.
 * Die in dieser Klasse enthaltene main-Mehtode dient als Einstiegspunkt in die Anwendung.
 * <br><br>
 * <u><b>HINWEIS</b></u><br>
 * Das Programm verfügt über einen vollständigen user-support. D.h. es ist nicht möglich nicht-legale
 * Züge auszuführen. Um ein vollwertiges Schachspiel zu sein, fehlt einzig die Funktion einen Bauern umwandeln
 * zu können, wenn dieser das Brett vollständig überquert hat.
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
