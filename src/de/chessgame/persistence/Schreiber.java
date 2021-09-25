package de.chessgame.persistence;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import de.chessgame.logic.ablauf.SpielAblauf;
import de.chessgame.logic.brett.Brett;

/**
 * Stellt eine Methode zum Schreiben eines SpielAblauf-Objekts in eine Datei bereit.
 * Diese Datei ist Read-Only
 * @author Josef Weldemariam
 *
 */
public class Schreiber {

	/**
	 * Schreibt den Spielablauf
	 */
	public void schreibeSpielAblauf(File zielDatei, SpielAblauf ablauf) {
		try(ObjectOutputStream schreiber = new ObjectOutputStream(new FileOutputStream(zielDatei))){
			schreiber.writeObject(ablauf);
		}catch(IOException ausnahme){
			ausnahme.printStackTrace();
		}
	}
}
