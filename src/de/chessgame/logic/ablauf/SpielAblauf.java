package de.chessgame.logic.ablauf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import de.chessgame.logic.brett.Brett;
import de.chessgame.logic.brett.feld.Feld;
import static de.chessgame.logic.brett.feld.figur.Farbe.*;

import de.chessgame.logic.brett.feld.figur.Farbe;
import de.chessgame.logic.brett.feld.figur.Figur;
import de.chessgame.logic.brett.feld.figur.typ.Bauer;
import de.chessgame.logic.brett.feld.figur.typ.Koenig;

/**
 * Steuert den Spielablauf einer Schachpartie. Steuert den Spielerwechsel,
 * entscheidet abh�ngig von der Figurfarbe, die an der Reihe ist, welche Figuren
 * bewegt werden k�nnen. Bestimmt den Gewinner/Verlierer einer Schachpartie,
 * oder ob ein Patt vorliegt. Bestimmt ob der Spieler der an der Reihe ist, im
 * Schach steht und somit nur den K�nig bewegen darf.
 * 
 * @author Josef Weldemariam
 *
 */
public class SpielAblauf implements Serializable {

	/**
	 * Enth�lt eine serialVersionUID zum lesen eines gespeicherten Spielablaufs
	 */
	private static final long serialVersionUID = -8779849931931304384L;

	/**
	 * Enth�lt das Brett f�r den der Spielablauf gesteuert werden soll
	 */
	private Brett brett;

	/**
	 * Enth�lt den in brett gespeicherten Inhalt des Schachbretts
	 */
	private Feld[][] brettInhalt;

	/**
	 * Enth�lt die aktuelle Farbe die am Zug ist
	 */
	private Farbe aktuelleFarbeAmZug;

	/**
	 * Enh�lt alle Figuren der in aktuelleFarbeAmZug gespeicherten Farbe, die
	 * mindestens einen validen Zug machen koennen
	 */
	private transient List<Figur> alleFigurenMitValidenZuegen;

	/**
	 * Enh�lt alle Figuren der anderen Farbe, die den K�nig der aktuellen Farbe
	 * bedrohen
	 */
	private transient List<Figur> alleFigurenDieBedrohen = new ArrayList<Figur>();

	/**
	 * Gibt an ob eine Schachpartie beendet ist
	 */
	private transient boolean spielBeendet;

	/**
	 * Erstellt einen neuen SpielAblauf.
	 * 
	 * @param brett
	 */
	public SpielAblauf(Brett brett) {
		this.brett = brett;
//		this.alleFigurenMitValidenZuegen = bestimmeAlleFigurenMitValidenZuegen();
		this.brettInhalt = Brett.getBrett();
	}

//	public void schachAufheben() {
//		if(wegVersperren()) {
//			
//		}
//		if(drohendeFigurSchlagen()) {
//			if(mitKoenigSchlagbar) {
//				
//			}
//			if(durchAndereFigurSchlagbar) {
//				
//			}
//		}
//		if(bewegeKoenig()) {
//			
//		}
//		if(!wegVersperren() && !drohendeFigurSchlagen() && !bewegeKoenig()) {
//			spiel Beendet
//		}
//	}

	/**
	 * Beta-Version von bestimmeAlleFigurenMitValidenZuegen.
	 * Ist noch nicht fertig und somit nicht nutzbar
	 * @return
	 */
	public List<Figur> bestimmeAlleFigurenMitValidenZuegen2() {
		List<Figur> alleFigurenMitValidenZuegen = new ArrayList<Figur>();
		// aktueller K�nig steht im Schach
		if (alleFigurenDieBedrohen.size() > 0) {
			List<Figur> blocker = getFigurenDieBlockenKoennen();
			List<Figur> schlagen = getFigurenDieSchlagenKoennen();
			for (Figur figur : blocker) {
				alleFigurenMitValidenZuegen.add(figur);
			}
			for (Figur figur : schlagen) {
				alleFigurenMitValidenZuegen.add(figur);
			}

		} else {
			List<Figur> alleFiguren = bestimmeAlleFigurenMitValidenZuegenEinfach();
			for (Figur figur : alleFiguren) {
				alleFigurenMitValidenZuegen.add(figur);
			}
		}

		return alleFigurenMitValidenZuegen;
	}

	/**
	 * Bestimmt eine Liste aller Figuren der aktuellen Farbe die durch schlagen das
	 * Schach aufheben
	 * 
	 * @return eine Liste aller Figuren die durch schlagen das Schach aufheben
	 */
	public List<Figur> getFigurenDieSchlagenKoennen() {
		List<Figur> alleFigurenDieSchlagenKoennen = new ArrayList<Figur>();
		for (Feld[] feldReihe : Brett.getBrett()) {
			for (Feld feld : feldReihe) {
				Figur aktuelleFigur = feld.getFigur();
				if ((aktuelleFigur != null) && aktuelleFarbeAmZug == aktuelleFigur.getFarbe()) {
					for (Figur drohendeFigur : alleFigurenDieBedrohen) {
						Feld feldDrohenderFigur = Brett.getBrett()[drohendeFigur.getZeilenIndex()][drohendeFigur
								.getSpaltenIndex()];
						if (aktuelleFigur.bestimmeAlleValidenFelder().contains(feldDrohenderFigur)) {
							alleFigurenDieSchlagenKoennen.add(aktuelleFigur);
						}
					}
				}
			}
		}
		// Entferne den K�nig falls er durch Schlagen im Schach stehen w�rde
		if (alleFigurenDieSchlagenKoennen.contains(getAktuellenKoenig())) {
			for (Figur drohendeFigur : alleFigurenDieBedrohen) {
				Feld feldDrohenderFigur = Brett.getBrett()[drohendeFigur.getZeilenIndex()][drohendeFigur
						.getSpaltenIndex()];
				feldDrohenderFigur.setFigur(getAktuellenKoenig());
				if (isSchach() && !hatKoenigAusweichMoglichkeit()) {
					alleFigurenDieSchlagenKoennen.remove(getAktuellenKoenig());
				}
				feldDrohenderFigur.setFigur(drohendeFigur);
			}
		}
	

		return alleFigurenDieSchlagenKoennen;
	}

	/**
	 * Gibt eine Liste mit Figuren zurueck die durch blocken eine 
	 * eine Schachsituation aufheben
	 * Diese Methode ist noch nicht implementiert
	 * @return Liste von Figuren die ein Schach blocken k�nnen
	 */
	private List<Figur> getFigurenDieBlockenKoennen() {
		List<Figur> alleFigurenDieBlockenKoennen = new ArrayList<Figur>();
		// TODO Auto-generated method stub
		return alleFigurenDieBlockenKoennen;
	}

	/**
	 * Gibt an ob ein K�nig nach dem er Schach gestellt wurd eine Ausweichm�glichkeit
	 * in Form eines Zielfelds hat das nicht bedroht wird.
	 * @return true genau dann, wenn der K�nig eine Ausweichm�glichkeit hat sonst false.
	 */
	public boolean hatKoenigAusweichMoglichkeit() {
		List<Feld> alleValidenFelderKoenig = getAktuellenKoenig().getMoeglicheZielFelder();
		System.out.println(alleValidenFelderKoenig.size());
		for (int i = 0; i < alleValidenFelderKoenig.size(); i++) {
			Feld feld = alleValidenFelderKoenig.get(i);
			Figur figur = feld.getFigur();
			feld.setFigur(getAktuellenKoenig());
			if (isSchach()) {
				alleValidenFelderKoenig.remove(feld);
			}
			feld.setFigur(figur);
		}
		getAktuellenKoenig().setMoeglicheZielFelder(alleValidenFelderKoenig);
		if(getAktuellenKoenig().getMoeglicheZielFelder().size()>0) {
			return true;
		}
		
		return false;
	}

	/**
	 * Bestimmt alle Figuren fuer die in aktuelleFarbe gesetzte Farbe, mit
	 * mindestens einer validen Zugm�glichkeit
	 * 
	 * @return alleFigurenMitValidenZuegen Eine Liste aller Figuren der aktuellen
	 *         Farbe mit Validen Zuegen
	 */
	public List<Figur> bestimmeAlleFigurenMitValidenZuegenEinfach() {
		List<Figur> alleFigurenMitValidenZuegen = new ArrayList<Figur>();
		for (Feld[] feldReihe : Brett.getBrett()) {
			for (Feld feld : feldReihe) {
				Figur aktuelleFigur = feld.getFigur();
				if (aktuelleFigur != null && aktuelleFarbeAmZug == aktuelleFigur.getFarbe()) {
					if (aktuelleFigur.bestimmeAlleValidenFelder().size() > 0)
						alleFigurenMitValidenZuegen.add(aktuelleFigur);
				}
			}
		}
		return alleFigurenMitValidenZuegen;
	}

	/**
	 * Bestimmt alle Figuren fuer die in aktuelleFarbe gesetzte Farbe, mit
	 * mindestens einer validen Zugm�glichkeit
	 * 
	 * @return alleFigurenMitValidenZuegen Eine Liste aller Figuren der aktuellen
	 *         Farbe mit Validen Zuegen
	 */
	public List<Figur> bestimmeAlleFigurenMitValidenZuegen() {
		List<Figur> alleFigurenMitValidenZuegen = new ArrayList<Figur>();
		for (Feld[] feldReihe : Brett.getBrett()) {
			for (Feld feld : feldReihe) {
				Figur aktuelleFigur = feld.getFigur();
				if (aktuelleFarbeAmZug == WHITE && (aktuelleFigur != null) && (aktuelleFigur.getFarbe() == WHITE)
						&& aktuelleFigur.bestimmeAlleValidenFelder().size() > 0) {
					if (alleFigurenDieBedrohen.size() > 0) {
						for (Figur drohendeFigur : alleFigurenDieBedrohen) {
							Feld feldDrohenderFigur = Brett.getBrett()[drohendeFigur.getZeilenIndex()][drohendeFigur
									.getSpaltenIndex()];
							// pr�ft ob eine Figur einen validen Zug durch schlagen der drohenden Figur hat
							if (aktuelleFigur.bestimmeAlleValidenFelder().contains(feldDrohenderFigur)) {
								alleFigurenMitValidenZuegen.add(aktuelleFigur);
							}

						}
						alleFigurenMitValidenZuegen.add(getAktuellenKoenig());
					} else {
						alleFigurenMitValidenZuegen.add(aktuelleFigur);
					}

				}
				if (aktuelleFarbeAmZug == BLACK && aktuelleFigur != null && aktuelleFigur.getFarbe() == BLACK
						&& aktuelleFigur.bestimmeAlleValidenFelder().size() > 0) {
					if (alleFigurenDieBedrohen.size() > 0) {
						for (Figur drohendeFigur : alleFigurenDieBedrohen) {
							Feld feldDrohenderFigur = Brett.getBrett()[drohendeFigur.getZeilenIndex()][drohendeFigur
									.getSpaltenIndex()];
							if (aktuelleFigur.bestimmeAlleValidenFelder().contains(feldDrohenderFigur)) {
								alleFigurenMitValidenZuegen.add(aktuelleFigur);
							}
						}
						alleFigurenMitValidenZuegen.add(getAktuellenKoenig());

					} else {
						alleFigurenMitValidenZuegen.add(aktuelleFigur);
					}
				}
			}
		}
		return alleFigurenMitValidenZuegen;
	}

	/**
	 * Bestimmt alle validen Felder des K�nigs, die von der gegnerischen Farbe
	 * bedroht werden.
	 */
	public void bereinigeValideFelderKoenig() {
		List<Feld> alleValidenFelderKoenig = getAktuellenKoenig().getMoeglicheZielFelder();
		for (int i = 0; i < alleValidenFelderKoenig.size(); i++) {
			Feld zielFeld = alleValidenFelderKoenig.get(i);
			Figur tmpfigur = zielFeld.getFigur();
			Figur koenig = getAktuellenKoenig();
			Feld feldKoenig = Brett.getBrett()[koenig.getZeilenIndex()][koenig.getSpaltenIndex()];
			zielFeld.setFigur(koenig);
			feldKoenig.setFigur(null);
			
			if (isSchach()) {
				System.out.println(zielFeld.getZeilenIndex() + " " + zielFeld.getSpaltenIndex());
				bestimmeAlleFigurenDieBedrohen();
				System.out.println("Figur die Bedroht");
				for(Figur figur: alleFigurenDieBedrohen) {
					System.out.println(figur.getZeilenIndex() + " " + figur.getSpaltenIndex());
				}
				alleValidenFelderKoenig.remove(zielFeld);
				Brett.print();
			}
			feldKoenig.setFigur(koenig);
			zielFeld.setFigur(tmpfigur);
		}
		getAktuellenKoenig().setMoeglicheZielFelder(alleValidenFelderKoenig);
	}

	/**
	 * Gibt den Koenig, der Farbe, die am Zug ist zurueck.
	 * 
	 * @return Koenig, der Farbe, die aktuell am Zug ist.
	 */
	public Figur getAktuellenKoenig() {
		Figur aktuellerKoenig = null;
		for (Feld[] feldReihe : Brett.getBrett()) {
			for (Feld feld : feldReihe) {
				if (feld.getFigur() != null && feld.getFigur() instanceof Koenig
						&& feld.getFigur().getFarbe() == aktuelleFarbeAmZug) {
					aktuellerKoenig = feld.getFigur();
				}
			}
		}
		return aktuellerKoenig;
	}

	/**
	 * Bestimmt alle vom Gegener bedrohten Felder.
	 * 
	 * @return Liste aller vom Gegner bedrohten Felder.
	 */
	public List<Feld> getAlleBedrohtenFelder() {
		List<Feld> alleBedrohtenFelder = new ArrayList<Feld>();
		wechselAkutelleFarbeAmZug();
		List<Figur> alleFigurenMitValidenZuegen = bestimmeAlleFigurenMitValidenZuegen();
		List<Feld> alleVonBauernBedrohtenFelder = getAlleVonBauernBedrohtenFelder();
		for (Figur figur : alleFigurenMitValidenZuegen) {
			if (!(figur instanceof Bauer)) {
				List<Feld> alleValidenFelder = figur.bestimmeAlleValidenFelder();
				for (Feld validesFeld : alleValidenFelder) {
					alleBedrohtenFelder.add(validesFeld);
				}
			}
		}
		for (Feld vonBauerBedrohtesFeld : alleVonBauernBedrohtenFelder) {
			alleBedrohtenFelder.add(vonBauerBedrohtesFeld);
		}

		wechselAkutelleFarbeAmZug();
		return alleBedrohtenFelder;
	}

	/**
	 * Liefert alle von Bauern der aktuellen Farbe bedrohten Felder.
	 * 
	 * @return Liste aller Felder, die von Bauern der eigenen Farbe bedroht werden.
	 */
	public List<Feld> getAlleVonBauernBedrohtenFelder() {
		List<Feld> alleBauernFelder = getAlleBauernFelder();
		List<Feld> alleVonBauernBedrohtenFelder = new ArrayList<Feld>();
		for (Feld bauernFeld : alleBauernFelder) {
			if (getAktuelleFarbeAmZug() == BLACK) {
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
			} else {
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
			}
		}
		return alleVonBauernBedrohtenFelder;
	}

	/**
	 * Liefert eine Liste aller Felder die mit einem Bauern der aktuellen Farbe
	 * besetzt sind.
	 * 
	 * @return Liste alle Felder die einen Bauern der aktuellen Farbe enthalten.
	 */
	public List<Feld> getAlleBauernFelder() {
		List<Feld> alleBauernFelder = new ArrayList<Feld>();
		for (Feld[] feldReihe : Brett.getBrett()) {
			for (Feld feld : feldReihe) {
				if (feld.getFigur() != null && feld.getFigur() instanceof Bauer
						&& feld.getFigur().getFarbe() == aktuelleFarbeAmZug) {
					alleBauernFelder.add(feld);
				}
			}
		}
		return alleBauernFelder;
	}

	/**
	 * Bestimmt alle validen Zielfelder des K�nigs unter Ber�cksichtigung der
	 * Tatsache, dass ein potentielles Zielfeld vom Gegner bedroht wird. Ist das f�r
	 * ein Feld der Fall, wird dieses der Liste nicht hinzugef�gt.
	 * 
	 * @return alle echt validen Zielfelder des K�nigs der aktuellen Farbe.
	 */
	public List<Feld> getAlleEchtValidenFelderKoenig() {
		Figur aktuellerKoenig = getAktuellenKoenig();
		List<Feld> alleEchtValidenFelderKoenig = aktuellerKoenig.bestimmeAlleValidenFelder();
		List<Feld> zuLoeschendeFelderKoenig = new ArrayList<Feld>();
		List<Feld> alleBedrohtenFelder = getAlleBedrohtenFelder();
		for (Feld bedrohtesFeld : alleBedrohtenFelder) {
			if (alleEchtValidenFelderKoenig.contains(bedrohtesFeld)) {
				zuLoeschendeFelderKoenig.add(bedrohtesFeld);
			}
		}
		alleEchtValidenFelderKoenig.removeAll(zuLoeschendeFelderKoenig);
		return alleEchtValidenFelderKoenig;
	}

	/**
	 * �ndert den aktuelleFarbeAmZug Wert auf Schwarz, wenn er Wei� war und auf Wei�,
	 * wenn er Schwarz war.
	 */
	public void wechselAkutelleFarbeAmZug() {
		if (aktuelleFarbeAmZug == BLACK) {
			setAktuelleFarbeAmZug(WHITE);
		} else {
			setAktuelleFarbeAmZug(BLACK);
		}
	}

	/**
	 * Gibt die aktuelle Farbe die am Zug ist zurueck
	 * @return the aktuelleFarbeAmZug
	 */
	public Farbe getAktuelleFarbeAmZug() {
		return aktuelleFarbeAmZug;
	}

	/**
	 * Setzt die aktuelle Farbe die am Zug ist 
	 * @param aktuelleFarbeAmZug the aktuelleFarbeAmZug to set
	 */
	public void setAktuelleFarbeAmZug(Farbe aktuelleFarbeAmZug) {
		this.aktuelleFarbeAmZug = aktuelleFarbeAmZug;
	}

	/**
	 * Setzt ein true wenn das Spiel beendet ist, sonst false.
	 * @param spielBeendet the spielBeendet to set
	 */
	public void setSpielBeendet(boolean spielBeendet) {
		this.spielBeendet = spielBeendet;
	}

	/**
	 * Pr�ft ob das Spiel beendet ist, indem isSchachMatt aufgerufen wird
	 * 
	 * @return true genau dann wennn isSchachMatt true ist sonst false
	 * @see {@link de.chessgame.logic.ablauf.SpielAblauf#isSchachMatt()}
	 */
	public boolean isSpielBeendet() {
		if (isSchachMatt()) {
			return true;
		}
		return false;
	}

	/**
	 * Gibt an ob der Koenig der aktuellen Farbe am Zug Schach Matt ist (Noch nicht
	 * implementiert, gibt momentan immer false zurueck)
	 * 
	 * @return true genau dann, wenn der Koenig der aktuellen Farbe Schach Matt ist
	 */
	public boolean isSchachMatt() {

		return false;
	}

	/**
	 * Gibt an ob der K�nig der aktuellen Farbe am Zug im Schach steht
	 * 
	 * @return true genau dann wenn der K�nig der aktuellen Farbe im Schach steht
	 */
	public boolean isSchach() {
		bestimmeAlleFigurenDieBedrohen();
		if (alleFigurenDieBedrohen.size() > 0) {
			return true;
		}
		return false;
	}

	/**
	 * Bestimmt ob der K�nig der akutellen Farbe im Schach steht, und f�gt alle
	 * Figuren die dem K�nig mit Schach drohen, der Liste alleFigurenDieDrohen hinzu.
	 */
	public void bestimmeAlleFigurenDieBedrohen() {
		// bestimme das Feld des K�nigs der aktuellen Farbe
		Feld feldMitKoenig = null;
		for (Feld[] feldReihe : Brett.getBrett()) {
			for (Feld feld : feldReihe) {
				if (feld.getFigur() != null && feld.getFigur().getFarbe() == aktuelleFarbeAmZug
						&& feld.getFigur() instanceof Koenig) {
					feldMitKoenig = feld;
				}
			}
		}

		// bestimme alle Figuren des Gegners mit validen Z�gen auf das
		// Feld mit dem Koenig der aktuellen Farbe
		List<Figur> alleFigurenMitValidenZuegenGegner;
		wechselAkutelleFarbeAmZug();
		alleFigurenMitValidenZuegenGegner = bestimmeAlleFigurenMitValidenZuegen();
		for (Figur figur : alleFigurenMitValidenZuegenGegner) {
			List<Feld> alleValidenZielFelderGegner = figur.bestimmeAlleValidenFelder();
			if (alleValidenZielFelderGegner.contains(feldMitKoenig)) {
//				alleFigurenDieBedrohen.removeAll(alleFigurenDieBedrohen);
				alleFigurenDieBedrohen.add(figur);
			}
		}
		wechselAkutelleFarbeAmZug();

	}

	/**
	 * Gibt eine Liste aller gegnerischen Figuren, die dem K�nig Schach geben, zur�ck.
	 * @return the alleFigurenDieBedrohen
	 */
	public List<Figur> getAlleFigurenDieBedrohen() {
		return alleFigurenDieBedrohen;
	}

	/**
	 * Setzt eine Liste aller gegnerischen Figuren, die dem K�nig Schach geben.
	 * @param alleFigurenDieBedrohen the alleFigurenDieBedrohen to set
	 */
	public void setAlleFigurenDieBedrohen(List<Figur> alleFigurenDieBedrohen) {
		this.alleFigurenDieBedrohen = alleFigurenDieBedrohen;
	}

	/**
	 * Gibt das Brett-Objekt zur�ck
	 * @return the brett
	 */
	public Brett getBrett() {
		return brett;
	}

	/**
	 * Setzt das Brett-Objekt
	 * @param brett the brett to set
	 */
	public void setBrett(Brett brett) {
		this.brett = brett;
	}

	/**
	 * Gibt den Brettinhalt zur�ck
	 * @return the brettInhalt
	 */
	public Feld[][] getBrettInhalt() {
		return brettInhalt;
	}

	/**
	 * Setzt den Brettinhalt
	 * @param brettInhalt the brettInhalt to set
	 */
	public void setBrettInhalt(Feld[][] brettInhalt) {
		this.brettInhalt = brettInhalt;
	}

	/**
	 * Gibt eine Liste aller Figuren der eigenen Farbe mit validen Z�gen zur�ck.
	 * @return the alleFigurenMitValidenZuegen
	 */
	public List<Figur> getAlleFigurenMitValidenZuegen() {
		return alleFigurenMitValidenZuegen;
	}

	/**
	 * Setzt eine Liste aller Figuren der eigenen Farbe mit validen Z�gen.
	 * @param alleFigurenMitValidenZuegen the alleFigurenMitValidenZuegen to set.
	 */
	public void setAlleFigurenMitValidenZuegen(List<Figur> alleFigurenMitValidenZuegen) {
		this.alleFigurenMitValidenZuegen = alleFigurenMitValidenZuegen;
	}

}
