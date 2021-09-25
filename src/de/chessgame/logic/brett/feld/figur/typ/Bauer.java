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
 * Repräsentiert die Schachfigur Bauer. Enthaelt eine Mehtode
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
	 * mit einer Farbe auf. Instanzen dieser Bauer-Klasse können ein Feld vorrücken
	 * sofern das Zielfeld nicht besetzt ist. Sie können auch zwei Züge vorrücken
	 * wenn weder das Zielfeld noch das Feld zwischen dem aktuellen und dem Zielfeld
	 * besetzt ist. Drüberhinaus kann ein Objekt dieser Klasse auch entlang der
	 * Diagonalen ein Feld vorrücken sofern eine Figur des Gegners sich auf diesem
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
	 * kann es vertikal auch zwei Schritte vorrücken.
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
				// prüft ob das Feld mit um zwei erhöhten ZeilenIndex frei ist und
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
	 * Bestimmt alle validen Zielfelder eines weißen Bauern, die vertikal
	 * verlaufen. Handelt es sich um den ersten Zug eines Bauern-Objekts
	 * kann es vertikal auch zwei Schritte vorrücken.
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
				// prüft ob das Feld mit um zwei erhöhten ZeilenIndex frei ist und
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
	 * Möglich sind ein Feld vorzurücken oder die zwei diagonal Felder in vorwärts
	 * Richtung. Berücksichtig außerdem beim Zugriff auf die Felder ob es sich bei
	 * der aktuellen Position des Objekts um ein Randfeld handelt. Der Fall, dass
	 * sich ein schwarzes Bauer-Objekt auf einem Feld mit zeilenIndex 0 befindet,
	 * ist ausgeschlossen und wird somit nicht berücksichtigt
	 * 
	 * @return alleValidenFelder alle Felder auf die das schwarze Bauer-Objekt
	 *         ziehen kann
	 */
	public List<Feld> alleValidenFelderBlack() {

		List<Feld> alleValidenFelder = new ArrayList<Feld>();
		Feld potentiellesFeld;
		// prüft ob das Feld mit um eins erhöhten zeilenindex frei ist um ein Feld
		// vorzurücken. Ist das der Fall, wird auch geprüft ob die figur zwei vorziehen
		// kann
		List<Feld> alleValidenFelderGerade = pruefeGeradenSchwarz(zeilenIndex);
		for (Feld feld : alleValidenFelderGerade) {
			alleValidenFelder.add(feld);
		}
		// püft die Diagonalschritte welche nur durch schlagen einer gegnerischen Figur
		// erreicht werden können
		// Das Objekt befindet sich am Rand und hat den spaltenIndex 0
		if (zeilenIndex < 7) {
			if (isRandFigur() == 1) {
				// prüft ob es eine Figur schlagen kann
				potentiellesFeld = Brett.getBrett()[zeilenIndex + 1][spaltenIndex + 1];
				if (potentiellesFeld.getFigur() != null && potentiellesFeld.getFigur().getFarbe() == WHITE) {
					alleValidenFelder.add(potentiellesFeld);
				}
			}
			// Das Objekt befindet sich am Rand und hat den spaltenIndex 7
			if (isRandFigur() == 2) {
				// prüft ob es eine Figur schlagen kann
				potentiellesFeld = Brett.getBrett()[zeilenIndex + 1][spaltenIndex - 1];
				if (potentiellesFeld.getFigur() != null && potentiellesFeld.getFigur().getFarbe() == WHITE) {
					alleValidenFelder.add(potentiellesFeld);
				}
			}
			// Das Objekt hat das Feld komplett überquert und der Spieler darf eine Figur
			// die
			// vom Gegner geschlagen wurde zurückfordern
			if (isRandFigur() == 4) {
				// TODO Aufruf einer Methode die es erlaubt eine Figur auszusuchen
			}
			// Das Objekt befindet sich nicht am Rand und muss beide diagonalen überprüfen
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
	 * bestimmt alle validen Felder auf die das weiße Bauer-Objekt ziehen kann.
	 * Möglich sind ein Feld vorzurücken oder die zwei diagonal Felder in vorwärts
	 * Richtung. Berücksichtig außerdem beim Zugriff auf die Felder ob es sich bei
	 * der aktuellen Position des Objekts um ein Randfeld handelt. Der Fall, dass
	 * sich ein weißes Bauer-Objekt auf einem Feld mit zeilenIndex 7 befindet, ist
	 * ausgeschlossen und wird somit nicht berücksichtigt
	 * 
	 * @return alleValidenFelder alle Felder auf die das weiße Bauer-Objekt ziehen
	 *         kann
	 */
	public List<Feld> alleValidenFelderWhite() {
		List<Feld> alleValidenFelder = new ArrayList<Feld>();
		Feld potentiellesFeld;
		// prüft ob das Feld mit um eins verminderten zeilenindex frei ist um ein Feld
		// vorzurücken
		List<Feld> alleValidenFelderGerade = pruefeGeradenWeiss(zeilenIndex);
		for (Feld feld : alleValidenFelderGerade) {
			alleValidenFelder.add(feld);
		}
		// püft die Diagonalschritte welche nur durch schlagen einer gegnerischen Figur
		// erreicht werden können
		// Das Objekt befindet sich am Rand und hat den spaltenIndex 0
		if (zeilenIndex > 0) {
			if (isRandFigur() == 1) {
				// prüft ob es eine Figur schlagen kann

				potentiellesFeld = Brett.getBrett()[zeilenIndex - 1][spaltenIndex + 1];
				if (potentiellesFeld.getFigur() != null && potentiellesFeld.getFigur().getFarbe() == BLACK) {
					alleValidenFelder.add(potentiellesFeld);
				}

			}
			// Das Objekt befindet sich am Rand und hat den spaltenIndex 7
			if (isRandFigur() == 2) {
				// prüft ob es eine Figur schlagen kann
				potentiellesFeld = Brett.getBrett()[zeilenIndex - 1][spaltenIndex - 1];
				if (potentiellesFeld.getFigur() != null && potentiellesFeld.getFigur().getFarbe() == BLACK) {
					alleValidenFelder.add(potentiellesFeld);
				}
			}
			// Das Objekt hat das Feld komplett überquert und der Spieler darf eine Figur
			// die
			// vom Gegner geschlagen wurde zurückfordern
			if (isRandFigur() == 3) {
				// TODO Aufruf einer Methode die es erlaubt eine Figur auszusuchen
			}
			// Das Objekt befindet sich nicht am Rand und muss beide diagonalen überprüfen
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
	 * bestimmt alle validen Felder auf die das Bauer-Objekt ziehen kann. Möglich
	 * sind ein Feld vorzurücken oder die zwei diagonal Felder in vorwärts Richtung.
	 * Abhängig davon ob es sich um ein schwarzes oder weißes Bauer-Objekt handelt,
	 * wird die Methode alleValidenFelderBlack bzw alleValidenFelderWhite
	 * aufgerufen.
	 * 
	 * @return alleValidenFelder Eine Liste mit allen validen Feld-Objekten für eine
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

//	TODO kann auch zwei Felder vorrücken wenn das Bauer-Objekt noch nicht bewegt wurde und das Ziel-Feld 
//		 nicht bereits besetzt ist (erledigt)

//	TODO berücksichtigen das der Bauer die diagonale entlang schlagen kann (erledigt)

//	TODO berücksichtigen das der Bauer en passant geschlagen werden kann (nicht relevant für diese Objekt)
//  TODO kann eingetauscht werden wenn dass komplette Feld überquert wurde

}
