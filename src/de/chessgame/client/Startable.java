package de.chessgame.client;

import javafx.stage.Stage;

/**
 * Dieses Interface muss von allen Klassen implementiert werden,
 * die den Spielablauf einer Schachpartie starten möchten.
 * @author Josef Weldemariam
 *
 */
public interface Startable {

	/**
	 * Startet den Spielablauf einer Schachpartie
	 */
	public void start();
	
	/**
	 * Startet den Spielablauf einer Schachpartie
	 */
	public void start(Stage primaryStage) throws Exception;
}
