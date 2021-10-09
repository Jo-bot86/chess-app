package de.chessgame.logic.brett.feld.figur.typ;

import java.util.ArrayList;
import java.util.List;

import de.chessgame.logic.brett.feld.Feld;
import de.chessgame.logic.brett.feld.figur.Farbe;
import de.chessgame.logic.brett.feld.figur.Figur;
import de.chessgame.logic.brett.feld.figur.bewegungen.LaeuferBewegung;

/**
 * Repräsentiert die Schachfigur Laeufer. Stellt Methoden zur Bestimmung der
 * validen Zielfelder eines Laeufer-Objekts bereit
 * 
 * @author Josef Weldemariam
 * @see {@link de.chessgame.logic.brett.feld.figur.Figur Figur}
 */
public class Laeufer extends Figur {

	/**
	 * Speichert eine serialVersionUID fuer das lesen von Objekten aus Dateien
	 */
	private static final long serialVersionUID = 6076225192577541451L;

	/**
	 * Speichert ein Objekt der Klasse LäuferBewegung
	 */
	private LaeuferBewegung laeuferBewegung;

	/**
	 * Ruft den Konstruktor der Elternklasse mit dem Zeilen- und Spaltenindex sowie
	 * mit einer Farbe auf und initialisiert laeuferBewegung
	 * 
	 * @param zeilenIndex  Zeilenindex des Laeufer-Objekts
	 * @param spaltenIndex Spaltenindex des Laeufer-Objekts
	 * @param farbe        Farbe des Läufer-Objekts
	 */
	public Laeufer(int zeilenIndex, int spaltenIndex, Farbe farbe) {
		super(zeilenIndex, spaltenIndex, farbe);
		laeuferBewegung = new LaeuferBewegung(this);
		figurWert = 3;
	}

	/**
	 * Bestimmt alle möglichen Zielfelder die ein Läufer von einer Ecke aus
	 * erreichen kann
	 * 
	 * @return eine Liste aller möglichen Zielfelder mit Ursprung in einer Ecke
	 */
	public List<Feld> bestimmeAlleValidenFelderEcke() {
		List<Feld> alleValidenFelderEcke = new ArrayList<Feld>();
		List<Feld> alleValidenFelderSuedOst = new ArrayList<Feld>();
		List<Feld> alleValidenFelderSuedWest = new ArrayList<Feld>();
		List<Feld> alleValidenFelderNordOst = new ArrayList<Feld>();
		List<Feld> alleValidenFelderNordWest = new ArrayList<Feld>();
		if (isEckFigur() == 1) {
			alleValidenFelderSuedOst = laeuferBewegung.bestimmeAlleValidenFelderSuedOst();
		}
		if (isEckFigur() == 2) {
			alleValidenFelderSuedWest = laeuferBewegung.bestimmeAlleValidenFelderSuedWest();
		}
		if (isEckFigur() == 3) {
			alleValidenFelderNordOst = laeuferBewegung.bestimmeAlleValidenFelderNordOst();
		}
		if (isEckFigur() == 4) {
			alleValidenFelderNordWest = laeuferBewegung.bestimmeAlleValidenFelderNordWest();
		}

		for (Feld feld : alleValidenFelderNordOst) {
			alleValidenFelderEcke.add(feld);
		}
		for (Feld feld : alleValidenFelderNordWest) {
			alleValidenFelderEcke.add(feld);
		}
		for (Feld feld : alleValidenFelderSuedOst) {
			alleValidenFelderEcke.add(feld);
		}
		for (Feld feld : alleValidenFelderSuedWest) {
			alleValidenFelderEcke.add(feld);
		}
		return alleValidenFelderEcke;
	}

	/**
	 * Bestimmt alle möglichen Zielfelder die eine Läufer vom Rand aus erreichen
	 * kann
	 * 
	 * @return eine Liste aller möglichen Zielfelder mit Ursprung am Rand
	 */
	public List<Feld> bestimmeAlleValidenFelderRand() {
		List<Feld> alleValidenFelderRand = new ArrayList<Feld>();
		List<Feld> alleValidenFelderSuedOst = new ArrayList<Feld>();
		List<Feld> alleValidenFelderSuedWest = new ArrayList<Feld>();
		List<Feld> alleValidenFelderNordOst = new ArrayList<Feld>();
		List<Feld> alleValidenFelderNordWest = new ArrayList<Feld>();
		if (isRandFigur() == 1) {
			alleValidenFelderNordOst = laeuferBewegung.bestimmeAlleValidenFelderNordOst();
			alleValidenFelderSuedOst = laeuferBewegung.bestimmeAlleValidenFelderSuedOst();
		}
		if (isRandFigur() == 2) {
			alleValidenFelderNordWest = laeuferBewegung.bestimmeAlleValidenFelderNordWest();
			alleValidenFelderSuedWest = laeuferBewegung.bestimmeAlleValidenFelderSuedWest();
		}
		if (isRandFigur() == 3) {
			alleValidenFelderSuedWest = laeuferBewegung.bestimmeAlleValidenFelderSuedWest();
			alleValidenFelderSuedOst = laeuferBewegung.bestimmeAlleValidenFelderSuedOst();
		}
		if (isRandFigur() == 4) {
			alleValidenFelderNordWest = laeuferBewegung.bestimmeAlleValidenFelderNordWest();
			alleValidenFelderNordOst = laeuferBewegung.bestimmeAlleValidenFelderNordOst();
		}

		for (Feld feld : alleValidenFelderNordOst) {
			alleValidenFelderRand.add(feld);
		}
		for (Feld feld : alleValidenFelderNordWest) {
			alleValidenFelderRand.add(feld);
		}
		for (Feld feld : alleValidenFelderSuedOst) {
			alleValidenFelderRand.add(feld);
		}
		for (Feld feld : alleValidenFelderSuedWest) {
			alleValidenFelderRand.add(feld);
		}

		return alleValidenFelderRand;
	}

	/**
	 * Bestimmt alle validen Felder wenn ein Läufer weder in einer Ecke noch am Rand
	 * steht
	 * 
	 * @return eine Liste aller validen Ziel-Felder
	 */
	public List<Feld> bestimmeAlleValidenFelderInnen() {
		List<Feld> alleValidenFelderInnen = new ArrayList<Feld>();
		List<Feld> alleValidenFelderSuedOst = new ArrayList<Feld>();
		List<Feld> alleValidenFelderSuedWest = new ArrayList<Feld>();
		List<Feld> alleValidenFelderNordOst = new ArrayList<Feld>();
		List<Feld> alleValidenFelderNordWest = new ArrayList<Feld>();

		alleValidenFelderNordOst = laeuferBewegung.bestimmeAlleValidenFelderNordOst();
		alleValidenFelderSuedOst = laeuferBewegung.bestimmeAlleValidenFelderSuedOst();
		alleValidenFelderSuedWest = laeuferBewegung.bestimmeAlleValidenFelderSuedWest();
		alleValidenFelderNordWest = laeuferBewegung.bestimmeAlleValidenFelderNordWest();

		for (Feld feld : alleValidenFelderNordOst) {
			alleValidenFelderInnen.add(feld);
		}
		for (Feld feld : alleValidenFelderNordWest) {
			alleValidenFelderInnen.add(feld);
		}
		for (Feld feld : alleValidenFelderSuedOst) {
			alleValidenFelderInnen.add(feld);
		}
		for (Feld feld : alleValidenFelderSuedWest) {
			alleValidenFelderInnen.add(feld);
		}
		return alleValidenFelderInnen;

	}

	/**
	 * Ruft abhängig von der Position des Läufer-Objekts,
	 * bestimmeAlleValidenFelderEcke(), bestimmeAlleValidenFelderRand() oder
	 * bestimmeAlleValidenFelderInnen() auf.
	 * 
	 * @return eine Liste aller potentiellen Zielfelder
	 */
	@Override
	public List<Feld> bestimmeAlleValidenFelder() {
		if (zeilenIndex == 0 && (spaltenIndex == 0 || spaltenIndex == 7)
				|| zeilenIndex == 7 && (spaltenIndex == 0 || spaltenIndex == 7)) {
			return bestimmeAlleValidenFelderEcke();
		}
		if (zeilenIndex == 0 || zeilenIndex == 7 || spaltenIndex == 0 || spaltenIndex == 7) {
			return bestimmeAlleValidenFelderRand();
		}
		return bestimmeAlleValidenFelderInnen();
	}

}
