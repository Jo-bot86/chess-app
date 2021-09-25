package de.chessgame.logic.brett.feld.figur.typ;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import de.chessgame.logic.brett.Brett;
import de.chessgame.logic.brett.feld.Feld;
import static de.chessgame.logic.brett.feld.figur.Farbe.*;

import de.chessgame.logic.brett.feld.figur.Farbe;
import de.chessgame.logic.brett.feld.figur.Figur;

/**
 * Repr�sentiert die Schachfigur Bauer. Enthaelt eine Mehtode
 * bestimmeAlleValidenFelder zum bestimmen aller potentiellen Zielfelder. Diese
 * ruft abhangig von der Farbe des Bauer-Objekts die Methode
 * bestimmeAlleValidenFelderBlack oder bestimmeAlleValidenFelderWhite auf.
 * 
 * @author Josef Weldemariam
 * @see {@link de.chessgame.logic.brett.feld.figur.Figur Figur}
 */
public class Bauer extends Figur implements Serializable {

	/**
	 * Speichert eine serialVersionUID fuer das lesen von Objekten aus Dateien
	 */
	private static final long serialVersionUID = 3707551796474659051L;

	/**
	 * ruft den Konstruktor der Elternklasse mit dem Zeilen- und Spaltenindex sowie
	 * mit einer Farbe auf. Instanzen dieser Bauer-Klasse k�nnen ein Feld vorr�cken
	 * sofern das Zielfeld nicht besetzt ist. Sie k�nnen auch zwei Z�ge vorr�cken
	 * wenn weder das Zielfeld noch das Feld zwischen dem aktuellen und dem Zielfeld
	 * besetzt ist. Dr�berhinaus kann ein Objekt dieser Klasse auch entlang der
	 * Diagonalen ein Feld vorr�cken sofern eine Figur des Gegners sich auf diesem
	 * Feld befindet
	 * 
	 * @param zeilenIndex  Zeilenindex des Bauer-Objekts
	 * @param spaltenIndex Spaltenindex des Bauer-Objekts
	 * @param farbe        Farbe des Bauer-Objekts
	 */
	public Bauer(int zeilenIndex, int spaltenIndex, Farbe farbe) {
		super(zeilenIndex, spaltenIndex, farbe);
		this.moeglicheZielFelder = bestimmeAlleValidenFelder();
	}

	/**
	 * Bestimmt alle validen Zielfelder eines schwarzen Bauern, die vertikal
	 * verlaufen. Handelt es sich um den ersten Zug eines Bauern-Objekts
	 * kann es vertikal auch zwei Schritte vorr�cken.
	 * @param zeilenIndex Der Zeileninde des Bauern-Objekts
	 * @return Liste aller potentiellen Zielfelder in vertikaler Richtung
	 */
	public List<Feld> pruefeGeradenSchwarz(int zeilenIndex) {
		List<Feld> alleValidenFelderGerade = new ArrayList<Feld>();
		Feld potentiellesFeld;
		if (zeilenIndex < 7) {
			potentiellesFeld = Brett.getBrett()[zeilenIndex + 1][spaltenIndex];
			if (potentiellesFeld.getFigur() == null) {
				alleValidenFelderGerade.add(potentiellesFeld);
				// pr�ft ob das Feld mit um zwei erh�hten ZeilenIndex frei ist und
				// ob das Bauer-Objekt bereits bewegt wurde
				if (zeilenIndex < 6) {
					potentiellesFeld = Brett.getBrett()[zeilenIndex + 2][spaltenIndex];
					if (potentiellesFeld.getFigur() == null && zeilenIndex == 1) {
						alleValidenFelderGerade.add(potentiellesFeld);
					}
				}
			}
		}
		return alleValidenFelderGerade;
	}

	/**
	 * Bestimmt alle validen Zielfelder eines wei�en Bauern, die vertikal
	 * verlaufen. Handelt es sich um den ersten Zug eines Bauern-Objekts
	 * kann es vertikal auch zwei Schritte vorr�cken.
	 * @param zeilenIndex Der Zeileninde des Bauern-Objekts
	 * @return Liste aller potentiellen Zielfelder in vertikaler Richtung
	 */
	public List<Feld> pruefeGeradenWeiss(int zeilenIndex) {
		List<Feld> alleValidenFelderGerade = new ArrayList<Feld>();
		Feld potentiellesFeld;
		if (zeilenIndex > 0) {
			potentiellesFeld = Brett.getBrett()[zeilenIndex - 1][spaltenIndex];
			if (potentiellesFeld.getFigur() == null) {
				alleValidenFelderGerade.add(potentiellesFeld);
				// pr�ft ob das Feld mit um zwei erh�hten ZeilenIndex frei ist und
				// ob das Bauer-Objekt bereits bewegt wurde
				if (zeilenIndex > 1) {
					potentiellesFeld = Brett.getBrett()[zeilenIndex - 2][spaltenIndex];
					if (potentiellesFeld.getFigur() == null && zeilenIndex == 6) {
						alleValidenFelderGerade.add(potentiellesFeld);
					}
				}
			}
		}
		return alleValidenFelderGerade;
	}

	/**
	 * bestimmt alle validen Felder auf die das schwarze Bauer-Objekt ziehen kann.
	 * M�glich sind ein Feld vorzur�cken oder die zwei diagonal Felder in vorw�rts
	 * Richtung. Ber�cksichtig au�erdem beim Zugriff auf die Felder ob es sich bei
	 * der aktuellen Position des Objekts um ein Randfeld handelt. Der Fall, dass
	 * sich ein schwarzes Bauer-Objekt auf einem Feld mit zeilenIndex 0 befindet,
	 * ist ausgeschlossen und wird somit nicht ber�cksichtigt
	 * 
	 * @return alleValidenFelder alle Felder auf die das schwarze Bauer-Objekt
	 *         ziehen kann
	 */
	public List<Feld> alleValidenFelderBlack() {

		List<Feld> alleValidenFelder = new ArrayList<Feld>();
		Feld potentiellesFeld;
		// pr�ft ob das Feld mit um eins erh�hten zeilenindex frei ist um ein Feld
		// vorzur�cken. Ist das der Fall, wird auch gepr�ft ob die figur zwei vorziehen
		// kann
		List<Feld> alleValidenFelderGerade = pruefeGeradenSchwarz(zeilenIndex);
		for (Feld feld : alleValidenFelderGerade) {
			alleValidenFelder.add(feld);
		}
		// p�ft die Diagonalschritte welche nur durch schlagen einer gegnerischen Figur
		// erreicht werden k�nnen
		// Das Objekt befindet sich am Rand und hat den spaltenIndex 0
		if (zeilenIndex < 7) {
			if (isRandFigur() == 1) {
				// pr�ft ob es eine Figur schlagen kann
				potentiellesFeld = Brett.getBrett()[zeilenIndex + 1][spaltenIndex + 1];
				if (potentiellesFeld.getFigur() != null && potentiellesFeld.getFigur().getFarbe() == WHITE) {
					alleValidenFelder.add(potentiellesFeld);
				}
			}
			// Das Objekt befindet sich am Rand und hat den spaltenIndex 7
			if (isRandFigur() == 2) {
				// pr�ft ob es eine Figur schlagen kann
				potentiellesFeld = Brett.getBrett()[zeilenIndex + 1][spaltenIndex - 1];
				if (potentiellesFeld.getFigur() != null && potentiellesFeld.getFigur().getFarbe() == WHITE) {
					alleValidenFelder.add(potentiellesFeld);
				}
			}
			// Das Objekt hat das Feld komplett �berquert und der Spieler darf eine Figur
			// die
			// vom Gegner geschlagen wurde zur�ckfordern
			if (isRandFigur() == 4) {
				// TODO Aufruf einer Methode die es erlaubt eine Figur auszusuchen
			}
			// Das Objekt befindet sich nicht am Rand und muss beide diagonalen �berpr�fen
			if (isRandFigur() == 0) {
				potentiellesFeld = Brett.getBrett()[zeilenIndex + 1][spaltenIndex - 1];
				if (potentiellesFeld.getFigur() != null && potentiellesFeld.getFigur().getFarbe() == WHITE) {
					alleValidenFelder.add(potentiellesFeld);
				}
				potentiellesFeld = Brett.getBrett()[zeilenIndex + 1][spaltenIndex + 1];
				if (potentiellesFeld.getFigur() != null && potentiellesFeld.getFigur().getFarbe() == WHITE) {
					alleValidenFelder.add(potentiellesFeld);
				}
			}
		}
		return alleValidenFelder;
	}

	/**
	 * bestimmt alle validen Felder auf die das wei�e Bauer-Objekt ziehen kann.
	 * M�glich sind ein Feld vorzur�cken oder die zwei diagonal Felder in vorw�rts
	 * Richtung. Ber�cksichtig au�erdem beim Zugriff auf die Felder ob es sich bei
	 * der aktuellen Position des Objekts um ein Randfeld handelt. Der Fall, dass
	 * sich ein wei�es Bauer-Objekt auf einem Feld mit zeilenIndex 7 befindet, ist
	 * ausgeschlossen und wird somit nicht ber�cksichtigt
	 * 
	 * @return alleValidenFelder alle Felder auf die das wei�e Bauer-Objekt ziehen
	 *         kann
	 */
	public List<Feld> alleValidenFelderWhite() {
		List<Feld> alleValidenFelder = new ArrayList<Feld>();
		Feld potentiellesFeld;
		// pr�ft ob das Feld mit um eins verminderten zeilenindex frei ist um ein Feld
		// vorzur�cken
		List<Feld> alleValidenFelderGerade = pruefeGeradenWeiss(zeilenIndex);
		for (Feld feld : alleValidenFelderGerade) {
			alleValidenFelder.add(feld);
		}
		// p�ft die Diagonalschritte welche nur durch schlagen einer gegnerischen Figur
		// erreicht werden k�nnen
		// Das Objekt befindet sich am Rand und hat den spaltenIndex 0
		if (zeilenIndex > 0) {
			if (isRandFigur() == 1) {
				// pr�ft ob es eine Figur schlagen kann

				potentiellesFeld = Brett.getBrett()[zeilenIndex - 1][spaltenIndex + 1];
				if (potentiellesFeld.getFigur() != null && potentiellesFeld.getFigur().getFarbe() == BLACK) {
					alleValidenFelder.add(potentiellesFeld);
				}

			}
			// Das Objekt befindet sich am Rand und hat den spaltenIndex 7
			if (isRandFigur() == 2) {
				// pr�ft ob es eine Figur schlagen kann
				potentiellesFeld = Brett.getBrett()[zeilenIndex - 1][spaltenIndex - 1];
				if (potentiellesFeld.getFigur() != null && potentiellesFeld.getFigur().getFarbe() == BLACK) {
					alleValidenFelder.add(potentiellesFeld);
				}
			}
			// Das Objekt hat das Feld komplett �berquert und der Spieler darf eine Figur
			// die
			// vom Gegner geschlagen wurde zur�ckfordern
			if (isRandFigur() == 3) {
				// TODO Aufruf einer Methode die es erlaubt eine Figur auszusuchen
			}
			// Das Objekt befindet sich nicht am Rand und muss beide diagonalen �berpr�fen
			if (isRandFigur() == 0) {
				potentiellesFeld = Brett.getBrett()[zeilenIndex - 1][spaltenIndex - 1];
				if (potentiellesFeld.getFigur() != null && potentiellesFeld.getFigur().getFarbe() == BLACK) {
					alleValidenFelder.add(potentiellesFeld);
				}
				potentiellesFeld = Brett.getBrett()[zeilenIndex - 1][spaltenIndex + 1];
				if (potentiellesFeld.getFigur() != null && potentiellesFeld.getFigur().getFarbe() == BLACK) {
					alleValidenFelder.add(potentiellesFeld);
				}
			}
		}
		return alleValidenFelder;
	}

	/**
	 * bestimmt alle validen Felder auf die das Bauer-Objekt ziehen kann. M�glich
	 * sind ein Feld vorzur�cken oder die zwei diagonal Felder in vorw�rts Richtung.
	 * Abh�ngig davon ob es sich um ein schwarzes oder wei�es Bauer-Objekt handelt,
	 * wird die Methode alleValidenFelderBlack bzw alleValidenFelderWhite
	 * aufgerufen.
	 * 
	 * @return alleValidenFelder Eine Liste mit allen validen Feld-Objekten f�r eine
	 *         bestimmte Farbe
	 * 
	 * @see {@link #alleValidenFelderBlack()} 
	 * @see {@link #alleValidenFelderWhite()}
	 */
	@Override
	public List<Feld> bestimmeAlleValidenFelder() {
		if (farbe == BLACK) {
			return alleValidenFelderBlack();
		}
		return alleValidenFelderWhite();
	}

//	TODO die Methode alle ValidenFelder muss nach jedem Zug neu aufgerufen werden
//	TODO Beachte das der Zeilen- und Spaltenindex der Figur vorher aktualisiert wird (erledigt)

//	TODO kann auch zwei Felder vorr�cken wenn das Bauer-Objekt noch nicht bewegt wurde und das Ziel-Feld 
//		 nicht bereits besetzt ist (erledigt)

//	TODO ber�cksichtigen das der Bauer die diagonale entlang schlagen kann (erledigt)

//	TODO ber�cksichtigen das der Bauer en passant geschlagen werden kann (nicht relevant f�r diese Objekt)
//  TODO kann eingetauscht werden wenn dass komplette Feld �berquert wurde

}
