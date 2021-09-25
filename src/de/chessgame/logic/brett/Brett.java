package de.chessgame.logic.brett;

import java.io.Serializable;
import java.util.Arrays;

import de.chessgame.logic.brett.feld.Feld;
import de.chessgame.logic.brett.feld.figur.Figur;
import de.chessgame.logic.brett.feld.figur.typ.*;

import static de.chessgame.logic.brett.feld.figur.Farbe.*;

/**
 * Enthält die Daten eines Brett-Objekts, welches durch ein zwei-dimensionales Array
 * vom Typ Feld repraesentiert wird sowie durch die Zeilen- und Spaltendimension, stellt
 * somit im Sinne des DAO-Pattern ein DTO dar. Das Attribut brett repraesentiert hier
 * den Inhalt des Schachbretts.
 * 
 * @author Josef Weldemariam
 * @see {@link de.chessgame.backend.brett.feld.Feld Feld}
 */
public class Brett implements Serializable{

	/**
	 * Speichert eine serialVersionUID fuer das lesen von Objekten
	 * aus Dateien
	 */
	private static final long serialVersionUID = -4012063447687895874L;

	/**
	 * Legt eine Referenz an, um ein Array Objekt mit dem Bezeichner brett
	 * anzusprechen. 
	 */
	private static Feld[][] brett;

	/**
	 * Enthaelt die Anzahl Zeilen des brett
	 */
	private final int ZEILEN_DIM;

	/**
	 * Enthaelt die Anzahl Spalten des brett
	 */
	private final int SPALTEN_DIM;

	/**
	 * Initialisiert das Brett-Objekt, wobei das Attribut brett ein zwei-dimensionalen
	 * Array vom Typ Feld ist
	 * @param zeilenDim
	 * @param spaltenDim
	 */
	public Brett(int zeilenDim, int spaltenDim) {
		brett = new Feld[zeilenDim][spaltenDim];
		ZEILEN_DIM = zeilenDim;
		SPALTEN_DIM = spaltenDim;
		for (int i = 0; i < zeilenDim; i++) {
			for (int j = 0; j < spaltenDim; j++) {
				brett[i][j] = new Feld(i, j);
			}
		}
	}
	
	/**
	 * Setzt die möglichen Zielfelder für alle Figuren
	 */
	public void setValideZuegeFuerAlleFiguren() {
		for(int i =0; i< brett.length; i++) {
			for(int j=0; j< brett[0].length; j++) {
				Figur figur  = brett[i][j].getFigur();
				if(figur!= null) {
					brett[i][j].getFigur().setMoeglicheZielFelder(figur.bestimmeAlleValidenFelder());
				}
			}
		}
	}
	
	/**
	 * Initialisiert brett mit den jeweiligen Figuren (hard gecoded) 
	 */
	public void init() {
		// stellt die schwarzen Bauern auf
		for (int j = 0; j < brett[0].length; j++) {
			brett[1][j].setFigur(new Bauer(1, j, BLACK));
		}
		// stellt die weißen Bauern auf
		for (int j = 0; j < brett[0].length; j++) {
			brett[brett.length - 2][j].setFigur(new Bauer(brett.length - 2, j, WHITE));
		}

		// stellt die schwarzen Tuerme auf
		brett[0][0].setFigur(new Turm(0, 0, BLACK));
		brett[0][brett[0].length - 1].setFigur(new Turm(0, brett[0].length - 1, BLACK));
		// stellt die weißen Tuerme auf
		brett[brett.length - 1][0].setFigur(new Turm(brett.length - 1, 0, WHITE));
		brett[brett.length - 1][brett[0].length - 1].setFigur(new Turm(brett.length - 1, brett[0].length - 1, WHITE));

		// stellt die schwarzen Springer auf
		brett[0][1].setFigur(new Springer(0, 1, BLACK));
		brett[0][brett[0].length - 2].setFigur(new Springer(0, brett[0].length - 2, BLACK));
		// stellt die weißen Springer auf
		brett[brett.length - 1][1].setFigur(new Springer(brett.length - 1, 1, WHITE));
		brett[brett.length - 1][brett[0].length - 2]
				.setFigur(new Springer(brett.length - 1, brett[0].length - 2, WHITE));

		// stellt die schwarzen Laeufer auf
		brett[0][2].setFigur(new Laeufer(0, 2, BLACK));
		brett[0][brett[0].length - 3].setFigur(new Laeufer(0, brett[0].length - 3, BLACK));
		// stellt die weißen Laeufer auf
		brett[brett.length - 1][2].setFigur(new Laeufer(brett.length - 1, 2, WHITE));
		brett[brett.length - 1][brett[0].length - 3]
				.setFigur(new Laeufer(brett.length - 1, brett[0].length - 3, WHITE));

		// stellt die schwarze Dame auf
		brett[0][3].setFigur(new Dame(0, 3, BLACK));
		// stellt die weiße Dame auf
		brett[brett.length - 1][3].setFigur(new Dame(brett.length - 1, 3, WHITE));

		// stellt den schwarzen Koenig auf
		brett[0][brett[0].length - 4].setFigur(new Koenig(0, brett[0].length - 4, BLACK));
		// stellt den weißen Koenig auf
		brett[brett.length - 1][brett[0].length - 4].setFigur(new Koenig(brett.length - 1, brett[0].length - 4, WHITE));

		setValideZuegeFuerAlleFiguren();
	}

	/**
	 * Gibt den aktuellen Zustand des Brett-Objekts wieder
	 * @return brett
	 */
	public static Feld[][] getBrett() {
		return brett;
	}

	/**
	 * Setzt brett auf das als Parmeter uebergebene brett
	 * @param brett zu setztende Brett
	 */
	public static void setBrett(Feld[][] brett) {
		Brett.brett = brett;
	}

	/**
	 * Gibt die Zeilen-Dimension des bett-Attributs zurueck
	 * @return Zeilen-Dimension des Bretts
	 */
	public int getZEILEN_DIM() {
		return ZEILEN_DIM;
	}

	/**
	 * Gibt die Spalten-Dimension des bett-Attributs zurueck
	 * @return Spalten-Dimension des Bretts
	 */
	public int getSPALTEN_DIM() {
		return SPALTEN_DIM;
	}

	/**
	 * Generiert einen hashCode aufgrund der Ueberschreibung der equals-Methode
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + SPALTEN_DIM;
		result = prime * result + ZEILEN_DIM;
		return result;
	}

	/**
	 * Überschreibt die von Object geerbte equals-Methode.
	 * Gibt ausschliesslich true zurueck, wenn es sich um
	 * das selbe Objekt handelt, andernfalls false.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		return false;
	}
	
	/**
	 * Erzeugt eine Ausgabe von brett in der Konsole
	 * Ist hier nur fuer Testzwecke und befindet sich final
	 * in der Klasse de.chessgame.client.konsole.KonsolenAusgabe
	 */
	public static void print() {
		for (Feld[] zeile : brett) {
			System.out.println(Arrays.deepToString(zeile));
			System.out.println();
		}
	}
	
}

