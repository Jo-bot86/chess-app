package de.chessgame.logic.brett.feld.figur.typ;

import java.util.ArrayList;
import java.util.List;

import de.chessgame.logic.brett.feld.Feld;
import de.chessgame.logic.brett.feld.figur.Farbe;
import de.chessgame.logic.brett.feld.figur.Figur;
import de.chessgame.logic.brett.feld.figur.bewegungen.TurmBewegung;

/**
 * Repräsentiert die Schachfigur Turm. Stellt Methoden zur Bestimmung der
 * validen Zielfelder eines Turm-Objekts bereit.
 * @author Josef Weldemariam
 * @see {@link de.chessgame.logic.brett.feld.figur.Figur Figur}
 */
public class Turm extends Figur {

	/**
	 * Speichert eine serialVersionUID fuer das lesen von Objekten aus Dateien
	 */
	private static final long serialVersionUID = 4262798890753094512L;

	/**
	 * Speichert ein Objekt der Klasse TurmBewegung
	 */
	private TurmBewegung turmBewegung;
	
	/**
	 * Gibt an, ob der König nicht bewegt wurde. Nötig um evtl. eine Rochade auszuführen.
	 */
	private boolean unbewegt;

	/**
	 * ruft den Konstruktor der Elternklasse mit dem Zeilen- und Spaltenindex sowie
	 * mit einer Farbe auf und initialisiert turmBewegung
	 * 
	 * @param zeilenIndex  Zeilenindex des Turm-Objekts
	 * @param spaltenIndex Spaltenindex des Turm-Objekts
	 * @param farbe        Farbe des Turm-Objekts
	 * @see {@link de.chessgame.logic.brett.feld.figur.bewegungen.TurmBewegung TurmBewegung}
	 */
	public Turm(int zeilenIndex, int spaltenIndex, Farbe farbe) {
		super(zeilenIndex, spaltenIndex, farbe);
		turmBewegung = new TurmBewegung(this);
		unbewegt = true;
		figurWert = 5;
	}

	/**
	 * Bestimmt alle möglichen Zielfelder die ein Turm von einer Ecke aus erreichen
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
		if (isEckFigur() == 1) {
			alleValidenFelderOsten = turmBewegung.bestimmeAlleValidenFelderOsten();
			alleValidenFelderSueden = turmBewegung.bestimmeAlleValidenFelderSueden();
		}

		if (isEckFigur() == 2) {
			alleValidenFelderWesten = turmBewegung.bestimmeAlleValidenFelderWesten();
			alleValidenFelderSueden = turmBewegung.bestimmeAlleValidenFelderSueden();
		}
		if (isEckFigur() == 3) {
			alleValidenFelderNorden = turmBewegung.bestimmeAlleValidenFelderNorden();
			alleValidenFelderOsten = turmBewegung.bestimmeAlleValidenFelderOsten();
		}
		if (isEckFigur() == 4) {
			alleValidenFelderNorden = turmBewegung.bestimmeAlleValidenFelderNorden();
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
		return alleValidenFelderEcke;
	}

	/**
	 * Bestimmt alle möglichen Zielfelder die ein Turm vom Rand aus erreichen kann
	 * 
	 * @return eine Liste aller möglichen Zielfelder mit Ursprung am Rand
	 */
	public List<Feld> bestimmeAlleValidenFelderRand() {
		List<Feld> alleValidenFelderRand = new ArrayList<Feld>();
		List<Feld> alleValidenFelderOsten = new ArrayList<Feld>();
		List<Feld> alleValidenFelderWesten = new ArrayList<Feld>();
		List<Feld> alleValidenFelderSueden = new ArrayList<Feld>();
		List<Feld> alleValidenFelderNorden = new ArrayList<Feld>();

		if (isRandFigur() == 1) {
			alleValidenFelderNorden = turmBewegung.bestimmeAlleValidenFelderNorden();
			alleValidenFelderOsten = turmBewegung.bestimmeAlleValidenFelderOsten();
			alleValidenFelderSueden = turmBewegung.bestimmeAlleValidenFelderSueden();
		}
		if (isRandFigur() == 2) {
			alleValidenFelderNorden = turmBewegung.bestimmeAlleValidenFelderNorden();
			alleValidenFelderWesten = turmBewegung.bestimmeAlleValidenFelderWesten();
			alleValidenFelderSueden = turmBewegung.bestimmeAlleValidenFelderSueden();
		}
		if (isRandFigur() == 3) {
			alleValidenFelderWesten = turmBewegung.bestimmeAlleValidenFelderWesten();
			alleValidenFelderSueden = turmBewegung.bestimmeAlleValidenFelderSueden();
			alleValidenFelderOsten = turmBewegung.bestimmeAlleValidenFelderOsten();
		}
		if (isRandFigur() == 4) {
			alleValidenFelderWesten = turmBewegung.bestimmeAlleValidenFelderWesten();
			alleValidenFelderNorden = turmBewegung.bestimmeAlleValidenFelderNorden();
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

		return alleValidenFelderRand;
	}

	/**
	 * Bestimmt alle validen Felder wenn ein Turm weder in einer Ecke noch am Rand
	 * steht
	 * 
	 * @return eine Liste aller validen Ziel-Felder
	 */
	public List<Feld> bestimmeAlleValidenFelderInnen() {
		List<Feld> alleValidenFelderInnen = new ArrayList<Feld>();
		List<Feld> alleValidenFelderOsten = new ArrayList<Feld>();
		List<Feld> alleValidenFelderWesten = new ArrayList<Feld>();
		List<Feld> alleValidenFelderSueden = new ArrayList<Feld>();
		List<Feld> alleValidenFelderNorden = new ArrayList<Feld>();

		alleValidenFelderNorden = turmBewegung.bestimmeAlleValidenFelderNorden();
		alleValidenFelderOsten = turmBewegung.bestimmeAlleValidenFelderOsten();
		alleValidenFelderSueden = turmBewegung.bestimmeAlleValidenFelderSueden();
		alleValidenFelderWesten = turmBewegung.bestimmeAlleValidenFelderWesten();

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
		return alleValidenFelderInnen;
	}

	/**
	 * Ruft abhängig von der Position des Turm-Objekts, bestimmeAlleValidenFelderEcke(),
	 * bestimmeAlleValidenFelderRand() oder bestimmeAlleValidenFelderInnen() auf.
	 * 
	 * @return eine Liste aller potentiellen Zielfelder
	 * @see 
	 * {@link de.chessgame.logic.brett.feld.figur.typ.Turm#bestimmeAlleValidenFelderEcke() bestimmeAlleValidenFelderEcke()},
	 * {@link de.chessgame.logic.brett.feld.figur.typ.Turm#bestimmeAlleValidenFelderRand() bestimmeAlleValidenFelderRand()},
	 * {@link de.chessgame.logic.brett.feld.figur.typ.Turm#bestimmeAlleValidenFelderInnen() bestimmeAlleValidenFelderInnen()}
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

	/**
	 * @return the unbewegt
	 */
	public boolean isUnbewegt() {
		return unbewegt;
	}

	/**
	 * @param unbewegt the unbewegt to set
	 */
	public void setUnbewegt(boolean unbewegt) {
		this.unbewegt = unbewegt;
	}

}
