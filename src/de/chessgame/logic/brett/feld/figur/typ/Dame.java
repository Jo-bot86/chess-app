package de.chessgame.logic.brett.feld.figur.typ;

import java.util.ArrayList;
import java.util.List;

import de.chessgame.logic.brett.feld.Feld;
import de.chessgame.logic.brett.feld.figur.Farbe;
import de.chessgame.logic.brett.feld.figur.Figur;
import de.chessgame.logic.brett.feld.figur.bewegungen.LaeuferBewegung;
import de.chessgame.logic.brett.feld.figur.bewegungen.TurmBewegung;

/**
 * Repräsentiert die Schachfigur Dame. Stellt Methoden zur Bestimmung der
 * validen Zielfelder eines Damen-Objekts bereit.
 * 
 * @author Josef Weldemariam
 * @see {@link de.chessgame.logic.brett.feld.figur.Figur Figur}
 */
public class Dame extends Figur {

	/**
	 * Speichert eine serialVersionUID fuer das lesen von Objekten aus Dateien
	 */
	private static final long serialVersionUID = 834989020388597910L;

	/**
	 * Speichert ein Objekt der Klasse LäuferBewegung
	 */
	private LaeuferBewegung laeuferBewegung;
	
	/**
	 * Speichert ein Objekt der Klasse TurmBewegung
	 */
	private TurmBewegung turmBewegung;

	/**
	 * Ruft den Konstruktor der Elternklasse mit dem Zeilen- und Spaltenindex sowie
	 * mit einer Farbe auf und initialisiert laeufer- und turmBewegung
	 * 
	 * @param zeilenIndex  Zeilenindex des Dame-Objekts
	 * @param spaltenIndex Spaltenindex des Dame-Objekts
	 * @param farbe        Farbe des Dame-Objekts
	 * @see {@link de.chessgame.logic.brett.feld.figur.bewegungen.TurmBewegung TurmBewegung}
	 * @see {@link de.chessgame.logic.brett.feld.figur.bewegungen.LaeuferBewegung LaeuferBewegung }
	 */
	public Dame(int zeilenIndex, int spaltenIndex, Farbe farbe) {
		super(zeilenIndex, spaltenIndex, farbe);
		laeuferBewegung = new LaeuferBewegung(this);
		turmBewegung = new TurmBewegung(this);
		figurWert = 9;
	}

	/**
	 * Bestimmt alle möglichen Zielfelder die eine Dame vom Rand aus erreichen kann
	 * 
	 * @return eine Liste aller möglichen Zielfelder mit Ursprung am Rand
	 */
	public List<Feld> bestimmeAlleValidenFelderRand() {
		List<Feld> alleValidenFelderRand = new ArrayList<Feld>();
		List<Feld> alleValidenFelderOsten = new ArrayList<Feld>();
		List<Feld> alleValidenFelderWesten = new ArrayList<Feld>();
		List<Feld> alleValidenFelderSueden = new ArrayList<Feld>();
		List<Feld> alleValidenFelderNorden = new ArrayList<Feld>();
		List<Feld> alleValidenFelderSuedOst = new ArrayList<Feld>();
		List<Feld> alleValidenFelderSuedWest = new ArrayList<Feld>();
		List<Feld> alleValidenFelderNordOst = new ArrayList<Feld>();
		List<Feld> alleValidenFelderNordWest = new ArrayList<Feld>();
		if (isRandFigur() == 1) {
			alleValidenFelderNorden = turmBewegung.bestimmeAlleValidenFelderNorden();
			alleValidenFelderNordOst = laeuferBewegung.bestimmeAlleValidenFelderNordOst();
			alleValidenFelderOsten = turmBewegung.bestimmeAlleValidenFelderOsten();
			alleValidenFelderSuedOst = laeuferBewegung.bestimmeAlleValidenFelderSuedOst();
			alleValidenFelderSueden = turmBewegung.bestimmeAlleValidenFelderSueden();
		}
		if (isRandFigur() == 2) {
			alleValidenFelderNorden = turmBewegung.bestimmeAlleValidenFelderNorden();
			alleValidenFelderNordWest = laeuferBewegung.bestimmeAlleValidenFelderNordWest();
			alleValidenFelderWesten = turmBewegung.bestimmeAlleValidenFelderWesten();
			alleValidenFelderSuedWest = laeuferBewegung.bestimmeAlleValidenFelderSuedWest();
			alleValidenFelderSueden = turmBewegung.bestimmeAlleValidenFelderSueden();
		}
		if (isRandFigur() == 3) {
			alleValidenFelderWesten = turmBewegung.bestimmeAlleValidenFelderWesten();
			alleValidenFelderSuedWest = laeuferBewegung.bestimmeAlleValidenFelderSuedWest();
			alleValidenFelderSueden = turmBewegung.bestimmeAlleValidenFelderSueden();
			alleValidenFelderSuedOst = laeuferBewegung.bestimmeAlleValidenFelderSuedOst();
			alleValidenFelderOsten = turmBewegung.bestimmeAlleValidenFelderOsten();
		}
		if (isRandFigur() == 4) {
			alleValidenFelderWesten = turmBewegung.bestimmeAlleValidenFelderWesten();
			alleValidenFelderNordWest = laeuferBewegung.bestimmeAlleValidenFelderNordWest();
			alleValidenFelderNorden = turmBewegung.bestimmeAlleValidenFelderNorden();
			alleValidenFelderNordOst = laeuferBewegung.bestimmeAlleValidenFelderNordOst();
			alleValidenFelderOsten = turmBewegung.bestimmeAlleValidenFelderOsten();
		}

		for (Feld feld : alleValidenFelderNorden) {
			alleValidenFelderRand.add(feld);
		}
		for (Feld feld : alleValidenFelderSueden) {
			alleValidenFelderRand.add(feld);
		}
		for (Feld feld : alleValidenFelderWesten) {
			alleValidenFelderRand.add(feld);
		}
		for (Feld feld : alleValidenFelderOsten) {
			alleValidenFelderRand.add(feld);
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
	 * Bestimmt alle möglichen Zielfelder die eine Dame von einer Ecke aus erreichen
	 * kann
	 * 
	 * @return eine Liste aller möglichen Zielfelder mit Ursprung in einer Ecke
	 */
	public List<Feld> bestimmeAlleValidenFelderEcke() {
		List<Feld> alleValidenFelderEcke = new ArrayList<Feld>();
		List<Feld> alleValidenFelderOsten = new ArrayList<Feld>();
		List<Feld> alleValidenFelderWesten = new ArrayList<Feld>();
		List<Feld> alleValidenFelderSueden = new ArrayList<Feld>();
		List<Feld> alleValidenFelderNorden = new ArrayList<Feld>();
		List<Feld> alleValidenFelderSuedOst = new ArrayList<Feld>();
		List<Feld> alleValidenFelderSuedWest = new ArrayList<Feld>();
		List<Feld> alleValidenFelderNordOst = new ArrayList<Feld>();
		List<Feld> alleValidenFelderNordWest = new ArrayList<Feld>();
		if (isEckFigur() == 1) {
			alleValidenFelderOsten = turmBewegung.bestimmeAlleValidenFelderOsten();
			alleValidenFelderSuedOst = laeuferBewegung.bestimmeAlleValidenFelderSuedOst();
			alleValidenFelderSueden = turmBewegung.bestimmeAlleValidenFelderSueden();
		}

		if (isEckFigur() == 2) {
			alleValidenFelderWesten = turmBewegung.bestimmeAlleValidenFelderWesten();
			alleValidenFelderSuedWest = laeuferBewegung.bestimmeAlleValidenFelderSuedWest();
			alleValidenFelderSueden = turmBewegung.bestimmeAlleValidenFelderSueden();
		}
		if (isEckFigur() == 3) {
			alleValidenFelderNorden = turmBewegung.bestimmeAlleValidenFelderNorden();
			alleValidenFelderNordOst = laeuferBewegung.bestimmeAlleValidenFelderNordOst();
			alleValidenFelderOsten = turmBewegung.bestimmeAlleValidenFelderOsten();
		}
		if (isEckFigur() == 4) {
			alleValidenFelderNorden = turmBewegung.bestimmeAlleValidenFelderNorden();
			alleValidenFelderNordWest = laeuferBewegung.bestimmeAlleValidenFelderNordWest();
			alleValidenFelderWesten = turmBewegung.bestimmeAlleValidenFelderWesten();
		}
		for (Feld feld : alleValidenFelderNorden) {
			alleValidenFelderEcke.add(feld);
		}
		for (Feld feld : alleValidenFelderSueden) {
			alleValidenFelderEcke.add(feld);
		}
		for (Feld feld : alleValidenFelderWesten) {
			alleValidenFelderEcke.add(feld);
		}
		for (Feld feld : alleValidenFelderOsten) {
			alleValidenFelderEcke.add(feld);
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
	 * Bestimmt alle validen Felder wenn eine Dame weder in einer Ecke noch am Rand
	 * Steht
	 * 
	 * @return eine Liste aller validen Ziel-Felder
	 */
	public List<Feld> bestimmeAlleValidenFelderInnen() {
		List<Feld> alleValidenFelderInnen = new ArrayList<Feld>();
		List<Feld> alleValidenFelderOsten = new ArrayList<Feld>();
		List<Feld> alleValidenFelderWesten = new ArrayList<Feld>();
		List<Feld> alleValidenFelderSueden = new ArrayList<Feld>();
		List<Feld> alleValidenFelderNorden = new ArrayList<Feld>();
		List<Feld> alleValidenFelderSuedOst = new ArrayList<Feld>();
		List<Feld> alleValidenFelderSuedWest = new ArrayList<Feld>();
		List<Feld> alleValidenFelderNordOst = new ArrayList<Feld>();
		List<Feld> alleValidenFelderNordWest = new ArrayList<Feld>();

		alleValidenFelderNorden = turmBewegung.bestimmeAlleValidenFelderNorden();
		alleValidenFelderNordOst = laeuferBewegung.bestimmeAlleValidenFelderNordOst();
		alleValidenFelderOsten = turmBewegung.bestimmeAlleValidenFelderOsten();
		alleValidenFelderSuedOst = laeuferBewegung.bestimmeAlleValidenFelderSuedOst();
		alleValidenFelderSueden = turmBewegung.bestimmeAlleValidenFelderSueden();
		alleValidenFelderSuedWest = laeuferBewegung.bestimmeAlleValidenFelderSuedWest();
		alleValidenFelderWesten = turmBewegung.bestimmeAlleValidenFelderWesten();
		alleValidenFelderNordWest = laeuferBewegung.bestimmeAlleValidenFelderNordWest();

		for (Feld feld : alleValidenFelderNorden) {
			alleValidenFelderInnen.add(feld);
		}
		for (Feld feld : alleValidenFelderSueden) {
			alleValidenFelderInnen.add(feld);
		}
		for (Feld feld : alleValidenFelderWesten) {
			alleValidenFelderInnen.add(feld);
		}
		for (Feld feld : alleValidenFelderOsten) {
			alleValidenFelderInnen.add(feld);
		}
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
	 * Ruft abhängig von der Position des Dame-Objekts, bestimmeAlleValidenFelderEcke(),
	 * bestimmeAlleValidenFelderRand() oder bestimmeAlleValidenFelderInnen() auf.
	 * 
	 * @return eine Liste aller potentiellen Zielfelder
	 * @see {@link de.chessgame.logic.brett.feld.figur.typ.Dame#bestimmeAlleValidenFelderEcke() bestimmeAlleValidenFelderEcke}
	 * @see {@link de.chessgame.logic.brett.feld.figur.typ.Dame#bestimmeAlleValidenFelderRand() bestimmeAlleValidenFelderRand}
	 * @see {@link de.chessgame.logic.brett.feld.figur.typ.Dame#bestimmeAlleValidenFelderInnen() bestimmeAlleValidenFelderInnen}
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
