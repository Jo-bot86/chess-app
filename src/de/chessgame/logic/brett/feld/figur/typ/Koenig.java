package de.chessgame.logic.brett.feld.figur.typ;

import static de.chessgame.logic.brett.feld.figur.Farbe.BLACK;

import java.util.ArrayList;
import java.util.List;

import de.chessgame.logic.brett.Brett;
import de.chessgame.logic.brett.feld.Feld;
import static de.chessgame.logic.brett.feld.figur.Farbe.*;

import de.chessgame.logic.brett.feld.figur.Farbe;
import de.chessgame.logic.brett.feld.figur.Figur;
import de.chessgame.logic.brett.feld.figur.bewegungen.LaeuferBewegung;
import de.chessgame.logic.brett.feld.figur.bewegungen.TurmBewegung;

/**
 * Repr�sentiert die Schachfigur K�nig. Stellt Methoden zur Bestimmung der
 * validen Zielfelder eines K�nig-Objekts bereit.
 * 
 * @author Josef Weldemariam
 * @see {@link de.chessgame.logic.brett.feld.figur.Figur Figur}
 */
public class Koenig extends Figur {

	/**
	 * Speichert eine serialVersionUID fuer das lesen von Objekten aus Dateien
	 */
	private static final long serialVersionUID = 4761240995093543032L;

	/**
	 * Speichert ein Objekt der Klasse L�uferBewegung
	 */
	private LaeuferBewegung laeuferBewegung;

	/**
	 * Speichert ein Objekt der Klasse TurmBewegung
	 */
	private TurmBewegung turmBewegung;
	
	/**
	 * Gibt an, ob der K�nig nicht bewegt wurde. N�tig um evtl. eine Rochade auszuf�hren.
	 */
	private boolean unbewegt;

	/**
	 * ruft den Konstruktor der Elternklasse mit dem Zeilen- und Spaltenindex sowie
	 * mit einer Farbe auf und initialisiert laeufer- und turmBewegung.
	 * 
	 * @param zeilenIndex  Zeilenindex des K�nig-Objekts
	 * @param spaltenIndex Spaltenindex des K�nig-Objekts
	 * @param farbe        Farbe des K�nig-Objekts
	 * @see {@link de.chessgame.logic.brett.feld.figur.bewegungen.TurmBewegung
	 *      TurmBewegung}
	 * @see {@link de.chessgame.logic.brett.feld.figur.bewegungen.LaeuferBewegung
	 *      LaeuferBewegung }
	 */
	public Koenig(int zeilenIndex, int spaltenIndex, Farbe farbe) {
		super(zeilenIndex, spaltenIndex, farbe);
		laeuferBewegung = new LaeuferBewegung(this);
		turmBewegung = new TurmBewegung(this);
		unbewegt = true;
	}
	
	/**
	 * Gibt an ob der K�nig der aktuellen Farbe am Zug im Schach steht
	 * 
	 * @return true genau dann wenn der K�nig der aktuellen Farbe im Schach steht
	 */
	public boolean isSchach() {
		List<Feld> alleBedrohtenFelder = getAlleBedrohtenFelder();
		Feld aktuellesFeldVonKoenig = Brett.getBrett()[zeilenIndex][spaltenIndex];
		if(alleBedrohtenFelder.contains(aktuellesFeldVonKoenig)) {
			return true;
		}
		return false;
	}

	/**
	 * Bestimmt alle m�glichen Zielfelder die ein K�nig von einer Ecke aus erreichen
	 * kann
	 * 
	 * @return eine Liste aller m�glichen Zielfelder mit Ursprung in einer Ecke
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
	 * Bestimmt alle m�glichen Zielfelder die ein K�nig vom Rand aus erreichen kann
	 * 
	 * @return eine Liste aller m�glichen Zielfelder mit Ursprung am Rand
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
	 * Bestimmt alle vom Gegener bedrohten Felder
	 * 
	 * @return Liste aller vom Gegner bedrohten Felder
	 */
	public List<Feld> getAlleBedrohtenFelder() {
		List<Feld> alleBedrohtenFelder = new ArrayList<Feld>();
		List<Feld> alleVonBauernBedrohtenFelder = getAlleVonBauernBedrohtenFelder();
		List<Feld> alleVonNichtBauernBedrohtenFelder = getAlleVonNichtBauernBedrohtenFelder();

		// F�ge alle von Bauern bedrohten Felder hinzu
		alleBedrohtenFelder.addAll(alleVonBauernBedrohtenFelder);

		// F�ge alle von den restlichen Figuren bedrohten Felder hinzu
		alleBedrohtenFelder.addAll(alleVonNichtBauernBedrohtenFelder);

		return alleBedrohtenFelder;
	}

	/**
	 * Bestimmt alle von der gegnerischen Farbe bedrohten Felder wobei alle
	 * drohenden Figuren keine Bauern sind. Wird f�r getAlleBedrohtenFelder
	 * gebraucht
	 */
	public List<Feld> getAlleVonNichtBauernBedrohtenFelder() {
		List<Feld> alleVonNichtBauernBedrohtenFelder = new ArrayList<Feld>();
		for (Feld[] feldReihe : Brett.getBrett()) {
			for (Feld feld : feldReihe) {
				if (feld.getFigur() != null && !(feld.getFigur() instanceof Bauer)) {
					if (farbe != feld.getFigur().getFarbe()) {
						List<Feld> alleValidenFelder = feld.getFigur().bestimmeAlleValidenFelder();
						if (alleValidenFelder.size() > 0) {
							alleVonNichtBauernBedrohtenFelder.addAll(alleValidenFelder);
						}
					}
				}
			}
		}

		return alleVonNichtBauernBedrohtenFelder;
	}

	/**
	 * Gibt eine Liste aller von Bauern bedrohten Feldern der gegnerischen Farbe
	 * zurueck. Wird f�r getAlleBedrohtenFelder gebraucht
	 * 
	 * @return Eine Liste aller von Bauern der gegnerischen Farbe bedrohten Felder
	 */
	public List<Feld> getAlleVonBauernBedrohtenFelder() {
		List<Feld> alleBauernFelder = getAlleBauernFelderGegner();
		List<Feld> alleVonBauernBedrohtenFelder = new ArrayList<Feld>();
		for (Feld bauernFeld : alleBauernFelder) {
			if (farbe == BLACK) {
				if (bauernFeld.getZeilenIndex() > 0 && bauernFeld.getSpaltenIndex() > 0
						&& bauernFeld.getSpaltenIndex() < 7) {
					alleVonBauernBedrohtenFelder
							.add(Brett.getBrett()[bauernFeld.getZeilenIndex() - 1][bauernFeld.getSpaltenIndex() - 1]);
					alleVonBauernBedrohtenFelder
							.add(Brett.getBrett()[bauernFeld.getZeilenIndex() - 1][bauernFeld.getSpaltenIndex() + 1]);
				}
				if (bauernFeld.getZeilenIndex() > 0 && bauernFeld.getSpaltenIndex() == 0) {
					alleVonBauernBedrohtenFelder
							.add(Brett.getBrett()[bauernFeld.getZeilenIndex() - 1][bauernFeld.getSpaltenIndex() + 1]);
				}
				if (bauernFeld.getZeilenIndex() > 0 && bauernFeld.getSpaltenIndex() == 7) {
					alleVonBauernBedrohtenFelder
							.add(Brett.getBrett()[bauernFeld.getZeilenIndex() - 1][bauernFeld.getSpaltenIndex() - 1]);
				}
			} else {
				if (bauernFeld.getZeilenIndex() < 7 && bauernFeld.getSpaltenIndex() > 0
						&& bauernFeld.getSpaltenIndex() < 7) {
					alleVonBauernBedrohtenFelder
							.add(Brett.getBrett()[bauernFeld.getZeilenIndex() + 1][bauernFeld.getSpaltenIndex() - 1]);
					alleVonBauernBedrohtenFelder
							.add(Brett.getBrett()[bauernFeld.getZeilenIndex() + 1][bauernFeld.getSpaltenIndex() + 1]);
				}
				if (bauernFeld.getZeilenIndex() < 7 && bauernFeld.getSpaltenIndex() == 0) {
					alleVonBauernBedrohtenFelder
							.add(Brett.getBrett()[bauernFeld.getZeilenIndex() + 1][bauernFeld.getSpaltenIndex() + 1]);
				}
				if (bauernFeld.getZeilenIndex() < 7 && bauernFeld.getSpaltenIndex() == 7) {
					alleVonBauernBedrohtenFelder
							.add(Brett.getBrett()[bauernFeld.getZeilenIndex() + 1][bauernFeld.getSpaltenIndex() - 1]);
				}
			}
		}
		return alleVonBauernBedrohtenFelder;
	}

	/**
	 * Liefert eine Liste aller Felder die mit einem Bauern der gegnerischen Farbe
	 * besetzt sind
	 * 
	 * @return Liste alle Felder die einen Bauern der gegnerischen Farbe enthalten
	 */
	public List<Feld> getAlleBauernFelderGegner() {
		List<Feld> alleBauernFelder = new ArrayList<Feld>();
		for (Feld[] feldReihe : Brett.getBrett()) {
			for (Feld feld : feldReihe) {
				if (farbe == BLACK) {
					if (feld.getFigur() != null && feld.getFigur() instanceof Bauer
							&& feld.getFigur().getFarbe() == WHITE) {
						alleBauernFelder.add(feld);
					}
				}
				if (farbe == WHITE) {
					if (feld.getFigur() != null && feld.getFigur() instanceof Bauer
							&& feld.getFigur().getFarbe() == BLACK) {
						alleBauernFelder.add(feld);
					}
				}
			}
		}
		return alleBauernFelder;
	}

	/**
	 * Bestimmt alle echt validen Felder des K�nigs noch vor seinem Zug. Hier kann
	 * es vorkommen, dass wenn der K�nig eine Figur schl�gt, dann auf einem Feld
	 * steht dass von einer anderen Figur bedroht wird. Das liegt daran, dass das
	 * Feld auf dem die geschlagene Figur stand nicht zu den validen Ziel-Feldern
	 * der nun bedrohenden Figur z�hlt. Somit muss noch gepr�ft werden ob der K�nig
	 * auf diesen potentiellen Feldern im Schach steht.
	 * 
	 * @return Eine Liste aller validen Ziel-Felder des K�nigs
	 */
	public List<Feld> bestimmeAlleEchtValidenFelder() {
		List<Feld> alleEchtValidenFelder = new ArrayList<Feld>();
		List<Feld> alleValidenFelderVorBereinigung = bestimmeAlleValidenFelder();
		List<Feld> alleBedrohtenFelder = getAlleBedrohtenFelder();
		for (int i = 0; i < alleValidenFelderVorBereinigung.size(); i++) {
			if (!(alleBedrohtenFelder.contains(alleValidenFelderVorBereinigung.get(i)))) {
				alleEchtValidenFelder.add(alleValidenFelderVorBereinigung.get(i));
			}
		}
		return alleEchtValidenFelder;
	}

	/**
	 * Bestimmt eine Vorauswahl aller potentiellen Zielfelder des K�nig-Objekts.
	 * Hier werden bedrohte Felder nicht ber�cksichtigt
	 * 
	 * @return eine Liste aller potentiellen Zielfelder vor Bereinigung
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
