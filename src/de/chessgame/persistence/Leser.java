package de.chessgame.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import de.chessgame.logic.ablauf.SpielAblauf;
import de.chessgame.logic.brett.Brett;

/**
 * Lie�t ein gespeicherten Spielablauf ein
 * @author Josef Weldemariam
 *
 */
public class Leser {
	
	/**
	 * Lie�t den Spielablauf ein
	 * @return ablauf Gibt den ausgelesenen Spielablauf zur�ck
	 */
	public SpielAblauf leseSpielAblauf(File quellDatei) {
		SpielAblauf ablauf = new SpielAblauf(new Brett(8,8));
		try(ObjectInputStream leser = new ObjectInputStream(new FileInputStream(quellDatei))){
			ablauf = (SpielAblauf) leser.readObject();
		}catch(IOException | ClassNotFoundException ausnahme) {
			ausnahme.printStackTrace();
		}
		return ablauf;
	}
	
}
