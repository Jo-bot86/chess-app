package de.chessgame.logic.brett.feld.figur;

import java.io.Serializable;
import java.util.List;

import de.chessgame.logic.brett.Brett;
import de.chessgame.logic.brett.feld.Feld;

/**
 * Repräsentiert die Elternklasse aller konkreten Klassen von Schachfiguren.
 * Alle Kindsklassen erhalten einen Zeilen- und Spaltenindex, eine Liste mit
 * allen möglichen Zielfeldern sowie eine Farbe. Darüber hinaus müssen sie alle
 * die Methode moveTo implementieren.
 * 
 * @author Josef Weldemariam
 * 
 */
public abstract class Figur implements Serializable {
	/**
	 * Speichert eine serialVersionUID fuer das lesen von Objekten aus Dateien
	 */
	private static final long serialVersionUID = 1309594443114105484L;

	/**
	 * Legt die Farbe der Objekte der Kindsklassen fest. protected: damit die
	 * Kindsklassen Zugriff auf die farbe haben.
	 */
	protected final Farbe farbe;

	/**
	 * Gibt den Zeilenindex des Feldes, auf dem sich die Figur befindet an .
	 */
	protected int zeilenIndex;

	/**
	 * Gibt den Spaltenindex des Feldes auf dem sich die Figur befindet an.
	 */
	protected int spaltenIndex;

	/**
	 * Enthält für ein Objekt einer erbenden Klasse alle potentiellen Feld-Objekte
	 * die von der aktuellen Position erreichbar sind.
	 */
	protected List<Feld> moeglicheZielFelder;

	/**
	 * Initialisiert eine Figur mit dem Zeilen- und Spaltenindex und der Farbe.
	 * 
	 * @param zeilenIndex  Zeilenindex der Figur
	 * @param spaltenIndex Spaltenindex der Figur
	 * @param farbe        Farbe der Figur.
	 */
	public Figur(int zeilenIndex, int spaltenIndex, Farbe farbe) {
		this.zeilenIndex = zeilenIndex;
		this.spaltenIndex = spaltenIndex;
		this.farbe = farbe;
	}

	/**
	 * Prüft ob sich das Figur-Objekt am Rand befindet, dh. spaltenIndex 0 oder 7
	 * hat Hat das Objekt spaltenIndex 0 gibt die Methode 1 zurück. Hat das Objekt
	 * spaltenIndex 7 gibt die Methode 2 zurück. Für den zeilenIndex 0 gibt sie 3
	 * zurück und für den zeilenIndex 7 gibt sie 4 zurück sonst 0.
	 * 
	 * @return 0, 1, 2, 3 oder 4
	 */
	public int isRandFigur() {
		if (spaltenIndex == 0) {
			return 1;
		}
		if (spaltenIndex == 7) {
			return 2;
		}
		if (zeilenIndex == 0) {
			return 3;
		}
		if (zeilenIndex == 7) {
			return 4;
		}
		return 0;
	}

	/**
	 * Prüft ob sich das Figur-Objekt in einer Ecke befindet. Gibt 1 zurück wenn die
	 * Figur im linken oberen, 2 im rechten oberen, 3 im linken unteren und 4 im
	 * rechten unteren, Eck ist.
	 * 
	 * @return 0, 1, 2, 3 oder 4
	 */
	public int isEckFigur() {
		if (zeilenIndex == 0 && spaltenIndex == 0) {
			return 1;
		}
		if (zeilenIndex == 0 && spaltenIndex == 7) {
			return 2;
		}
		if (zeilenIndex == 7 && spaltenIndex == 0) {
			return 3;
		}
		if (zeilenIndex == 7 && spaltenIndex == 7) {
			return 4;
		}
		return 0;
	}

	/**
	 * bestimmt alle validen Felder auf die ein Objekt ziehen kann. Diese Methode
	 * muss für alle Figuren implemeniert werden Abhängig davon ob es sich um ein
	 * schwarzes oder weißes Bauer-Objekt handelt, wird die Methode
	 * alleValidenFelderBlack bzw alleValidenFelderWhite aufgerufen.
	 * 
	 * @return Eine Liste mit allen validen Feld-Objekten für eine bestimmte Farbe
	 */
	public abstract List<Feld> bestimmeAlleValidenFelder();

	/**
	 * Diese Methode steht allen Kindsklassen zur Verfügung. Sie führt die Bewegung
	 * des jeweiligen Objekts der erbenden Klasse aus, sofern es sich um einen
	 * valides Zielfeld handelt. Dabei werden die Indizes der bewegten Figur
	 * angepasst und das Attribut figur des Ursprungsfeldes auf null gesetzt.
	 * 
	 * @param zielFeld das Feld auf welches das Objekt zieht
	 */
	public void moveTo(Feld zielFeld) {
		// ruft hier bereits die abstrakte Methode bestimmeAlleValidenFelder auf die in
		// den Kindsklassen implementiert wird
		moeglicheZielFelder = Brett.getBrett()[zeilenIndex][spaltenIndex].getFigur().bestimmeAlleValidenFelder();
		for (int i = 0; i < getMoeglicheZielFelder().size(); i++) {
			// prüfe ob eines der Felder in moeglicheZielFelder mit dem übergebenen Zielfeld
			// übereinstimmt
			if (zielFeld.getZeilenIndex() == getMoeglicheZielFelder().get(i).getZeilenIndex()
					&& zielFeld.getSpaltenIndex() == getMoeglicheZielFelder().get(i).getSpaltenIndex()) {
				Figur aktuelleFigur = Brett.getBrett()[zeilenIndex][spaltenIndex].getFigur();
				// setzt die Figur auf das gewählte Feld
				Brett.getBrett()[zielFeld.getZeilenIndex()][zielFeld.getSpaltenIndex()].setFigur(aktuelleFigur);
				// setzte die Figur auf dem Ursprungsfeld auf null
				Brett.getBrett()[aktuelleFigur.getZeilenIndex()][aktuelleFigur.getSpaltenIndex()].setFigur(null);
				// anpassen der Indize der bewegten Figur
				Brett.getBrett()[zielFeld.getZeilenIndex()][zielFeld.getSpaltenIndex()].getFigur()
						.setZeilenIndex(zielFeld.getZeilenIndex());
				Brett.getBrett()[zielFeld.getZeilenIndex()][zielFeld.getSpaltenIndex()].getFigur()
						.setSpaltenIndex(zielFeld.getSpaltenIndex());
			}
		}
	}

	/**
	 * Diese Methode steht allen Kindsklassen zur Verfügung. Sie führt die Bewegung
	 * des jeweiligen Objekts der erbenden Klasse aus. Dabei werden die Indizes der
	 * bewegten Figur angepasst und das Attribut figur des Ursprungsfeldes auf null
	 * gesetzt.
	 * 
	 * @param zielFeld das Feld auf welches das Objekt zieht
	 */
	public void moveTo2(Feld zielFeld) {
		Feld ursprungsFeld = Brett.getBrett()[zeilenIndex][spaltenIndex];
		Figur aktuelleFigur = ursprungsFeld.getFigur();
		// setzt die Figur auf das gewählte Feld
		Brett.getBrett()[zielFeld.getZeilenIndex()][zielFeld.getSpaltenIndex()].setFigur(aktuelleFigur);
		// setzte die Figur auf dem Ursprungsfeld auf null
		ursprungsFeld.setFigur(null);
		// anpassen der Indize der bewegten Figur
		aktuelleFigur.setZeilenIndex(zielFeld.getZeilenIndex());
		aktuelleFigur.setSpaltenIndex(zielFeld.getSpaltenIndex());
	}

	/**
	 * Gibt die Farbe des Figur-Objekts zurueck
	 * 
	 * @return the farbe Farbe der Figur
	 */
	public Farbe getFarbe() {
		return farbe;
	}

	/**
	 * Gibt den Zeilenindex des Figur-Objekts zurueck
	 * 
	 * @return the zeilenIndex Zeilenindex der Figur
	 */
	public int getZeilenIndex() {
		return zeilenIndex;
	}

	/**
	 * Gibt den Spaltenindex des Figur-Objekts zurueck.
	 * 
	 * @return the spaltenIndex Spaltenindex der Figur
	 */
	public int getSpaltenIndex() {
		return spaltenIndex;
	}

	/**
	 * Setzt den Zeilenindex des Figur-Objekts.
	 * 
	 * @param zeilenIndex zu setzende Zeilenindex
	 */
	public void setZeilenIndex(int zeilenIndex) {
		this.zeilenIndex = zeilenIndex;
	}

	/**
	 * Setzt den Spalteninde des Figur-Objekts
	 * 
	 * @param spaltenIndex zu setzende Spaltenindex
	 */
	public void setSpaltenIndex(int spaltenIndex) {
		this.spaltenIndex = spaltenIndex;
	}

	/**
	 * Gibt alle moeglichen Zielfelder einer Figur zurueck. D.h. alle potentiellen
	 * Felder auf die eine Figur den naechsten Zug machen kann.
	 * 
	 * @return the moeglicheZielFelder alle potentiellen Zielfelder
	 */
	public List<Feld> getMoeglicheZielFelder() {
		return moeglicheZielFelder;
	}

	/**
	 * @param moeglicheZielFelder the moeglicheZielFelder to set
	 */
	public void setMoeglicheZielFelder(List<Feld> moeglicheZielFelder) {
		this.moeglicheZielFelder = moeglicheZielFelder;
	}

}
