package de.chessgame.logic.ablauf;

import static de.chessgame.logic.brett.feld.figur.Farbe.BLACK;
import static de.chessgame.logic.brett.feld.figur.Farbe.WHITE;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import de.chessgame.logic.brett.Brett;
import de.chessgame.logic.brett.feld.Feld;
import de.chessgame.logic.brett.feld.figur.Farbe;
import de.chessgame.logic.brett.feld.figur.Figur;
import de.chessgame.logic.brett.feld.figur.typ.Bauer;
import de.chessgame.logic.brett.feld.figur.typ.Koenig;
import de.chessgame.logic.brett.feld.figur.typ.Turm;

/**
 * Repräsentiert die Beta Version der Klasse SpielAblauf
 * 
 * @author Josef Weldemariam
 *
 */
public class SpielAblauf implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5582659952535832725L;

	private Brett brett;

	/**
	 * Enthält den in brett gespeicherten Inhalt des Schachbretts
	 */
	private Feld[][] brettInhalt;

	/**
	 * Enthält die aktuelle Farbe die am Zug ist
	 */
	private Farbe aktuelleFarbeAmZug;

	/**
	 * Enhält alle Figuren der in aktuelleFarbeAmZug gespeicherten Farbe, die
	 * mindestens einen validen Zug machen koennen
	 */
	private transient List<Figur> alleFigurenMitValidenZuegen;
	
	/**
	 * Enthält den Punktestand von Schwarz
	 */
	private int punkteSchwarz;

	/**
	 * Enhält alle Figuren der anderen Farbe, die den König der aktuellen Farbe
	 * bedrohen
	 */
	private transient List<Figur> alleFigurenDieBedrohen = new ArrayList<Figur>();

	public SpielAblauf(Brett brett) {
		this.brett = brett;
		brettInhalt = Brett.getBrett();
	}
	
	
	/**
	 * Setzt den Wert für enPassantSchlagbar auf true wenn der Bauer einen doppel Schritt gemacht hat
	 * @param bauer das bewegte Bauer-Objekt
	 * @param zielFeld das Zielfeld des Bauer-Objekts
	 */
	public void setEnPassantMoeglich(Bauer bauer, Feld zielFeld ) {
		if(bauer.getFarbe()==Farbe.BLACK && bauer.getZeilenIndex() +2 == zielFeld.getZeilenIndex()) {
			bauer.setEnPassantSchlagbar(true);
		}
		if(bauer.getFarbe()==Farbe.WHITE && bauer.getZeilenIndex() -2 == zielFeld.getZeilenIndex()) {
			bauer.setEnPassantSchlagbar(true);
		}
	}
	

	/**
	 * Führt eine Rochade mit dem übergebenen König- und Turm-Objekt aus
	 * @param koenig an der Rochade beteiliges König-Objekt
	 * @param turm an der Rochade beteiliges Turm-Objekt
	 */
	public void fuehreRochadeAus(Koenig koenig, Turm turm) {
		Feld zielFeld;
		int koenigRowIndex = koenig.getZeilenIndex();
		int koenigColIndex = koenig.getSpaltenIndex();
		// Rochade mit dem Damenturm
		if (koenig.getSpaltenIndex() > turm.getSpaltenIndex()) {
			zielFeld = Brett.getBrett()[koenigRowIndex][koenigColIndex - 2];
			koenig.moveTo2(zielFeld);
			zielFeld = Brett.getBrett()[koenigRowIndex][koenigColIndex - 1];
			turm.moveTo2(zielFeld);
		} else { // Rochade mit Koenigsturm
			zielFeld = Brett.getBrett()[koenigRowIndex][koenigColIndex + 2];
			koenig.moveTo2(zielFeld);
			zielFeld = Brett.getBrett()[koenigRowIndex][koenigColIndex + 1];
			turm.moveTo2(zielFeld);
		}
	}

	/**
	 * Prüft ob der König und der Turm eine Rochade ausführen können
	 * 
	 * @param koenig an der Rochade beteiligter König
	 * @param turm   an der Rochade beteiligter Turm
	 * @return true genau dann, wenn die Bedingungen für eine Rochade erfüllt sind,
	 *         sonst false
	 */
	public boolean isRochadeMoeglich(Koenig koenig, Turm turm) {
		// prüft ob sowohl der König als auch der Turm noch nicht bewegt wurden
		if (koenig.isUnbewegt() && turm.isUnbewegt()) {
			// prüft um welchen Turm es sich handelt(Damen- oder Königsturm)
			if (turm.getSpaltenIndex() == 0) {
				// prüft ob die Felder zwischen DamenTurm und König keine Figuren enthalten
				if (felderZumDamenTurmSindUnbelegtUndUnbedroht(koenig) && !koenig.isSchach()) {
					return true;
				}
			}
			if(turm.getSpaltenIndex() == 7) {
				// prüft ob die Felder zwischen DamenTurm und König keine Figuren enthalten
				if(felderZumKoenigsTurmSindUnbelegtUndUnbedroht(koenig) && !koenig.isSchach()) {
					return true;
				}
			}
		}

		return false;
	}
	
	/**
	 * Prüft ob die Felder zwischen dem König und dem Königs-Turm sowohl unbelegt als auch unbedroht
	 * sind. Unbelegt sind Felder genau dann, wenn die Figur auf dem jeweiligen Feld null ist.
	 * Unbedroht genau dann, wenn keines der Felder ein potentielles Zielfeld einer gegnerischen 
	 * Figur ist.
	 * @param koenig der zu überprüfende König
	 * @return true genau dann wenn die Felder zwischen König und Turm unbelegt und unbedroht sind, sonst false
	 */
	public boolean felderZumKoenigsTurmSindUnbelegtUndUnbedroht(Koenig koenig) {
		for(int i =koenig.getSpaltenIndex()+1; i< 7; i++) {
			Feld feld = Brett.getBrett()[koenig.getZeilenIndex()][i];
			if(feld.getFigur() != null || koenig.getAlleBedrohtenFelder().contains(feld)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Prüft ob die Felder zwischen dem König und dem Damen-Turm sowohl unbelegt als auch unbedroht
	 * sind. Unbelegt sind Felder genau dann, wenn die Figur auf dem jeweiligen Feld null ist.
	 * Unbedroht genau dann, wenn keines der Felder ein potentielles Zielfeld einer gegnerischen 
	 * Figur ist.
	 * @param koenig der zu überprüfende König
	 * @return true genau dann wenn die Felder zwischen König und Turm unbelegt und unbedroht sind, sonst false
	 */
	public boolean felderZumDamenTurmSindUnbelegtUndUnbedroht(Koenig koenig) {
		for (int i = 1; i < koenig.getSpaltenIndex(); i++) {
			Feld feld = Brett.getBrett()[koenig.getZeilenIndex()][i];
			if (feld.getFigur() != null || koenig.getAlleBedrohtenFelder().contains(feld)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Bestimmt alle Figuren fuer die in aktuelleFarbe gesetzte Farbe, mit
	 * mindestens einer validen Zugmöglichkeit
	 * 
	 * @return alleFigurenMitValidenZuegen Eine Liste aller Figuren der aktuellen
	 *         Farbe mit Validen Zuegen
	 */
	public List<Figur> bestimmeAlleFigurenMitValidenZuegen() {
		List<Figur> alleFigurenMitValidenZuegen = new ArrayList<Figur>();
		// prüft ob der König bereits im Schach steht
		// prüft ob eine Figur blocken kann(erledigt)
		// prüft ob eine Figur schlagen kann(erledigt)
		// prüft ob der König eine Ausweichmöglichkeit hat(bestimmeAlleEchtValidenFelder
		// + ob der König nach dem Zug wieder im Schach ist (erledigt)

		// prüft ob der König nach einem Zug im Schach steht(dann ist die Figur gepinnt)
		Koenig aktuellerKoenig = (Koenig) getAktuellenKoenig();
		if (aktuellerKoenig.isSchach()) {
			// Enthält alle Figuren die blocken oder schlagen können und den König wenn er
			// schlagen oder ausweichen kann
			alleFigurenMitValidenZuegen = getAlleFigurenDieBlockenKoennen();
		} else {
			alleFigurenMitValidenZuegen = getAlleNichtGepinntenFiguren();
		}

		return alleFigurenMitValidenZuegen;
	}

	/**
	 * Gibt eine Teilmenge von alleFigurenMitValidenZuegenVorBereinigung zurueck.
	 * Für jedes Element dieser Teilmenge gilt das nach Bewegung auf ein mögliches
	 * Zielfeld der König nicht im Schach steht. D.h. keines dieser Figuren in
	 * dieser Teilmenge ist an den König gepinnt
	 * 
	 * @return Gibt eine Liste aller Figuren mit validen Zuegen zurueck die nicht
	 *         nicht gepinnt sind
	 */
	public List<Figur> getAlleNichtGepinntenFiguren() {
		List<Figur> alleNichtGepinntenFiguren = new ArrayList<Figur>();
		List<Figur> alleFigurenMitValidenZuegenVorBereinigung = bestimmeAlleFigurenMitValidenZuegenVorBereinigung();
		for (Figur figur : alleFigurenMitValidenZuegenVorBereinigung) {
			if (!(figur instanceof Koenig)) {
				List<Feld> alleValidenFelder = figur.bestimmeAlleValidenFelder();
				List<Feld> alleZielFelder = new ArrayList<Feld>();
				for (int i = 0; i < alleValidenFelder.size(); i++) {
					Feld testFeld = pruefeMoveTo(figur, alleValidenFelder.get(i));
					if (testFeld != null) {
						if (!alleNichtGepinntenFiguren.contains(figur)) {
							alleNichtGepinntenFiguren.add(figur);
						}
						alleZielFelder.add(testFeld);
					}
				}
				figur.setMoeglicheZielFelder(alleZielFelder);
			}
			if (figur.equals(getAktuellenKoenig())) {
				List<Feld> alleEchtValidenFelder = ((Koenig) figur).bestimmeAlleEchtValidenFelder();
				List<Feld> alleZielFelder = new ArrayList<Feld>();
				for (int i = 0; i < alleEchtValidenFelder.size(); i++) {
					Feld testFeld = pruefeMoveTo(getAktuellenKoenig(), alleEchtValidenFelder.get(i));
					if (testFeld != null) {
						alleNichtGepinntenFiguren.add(getAktuellenKoenig());
						alleZielFelder.add(testFeld);
					}
				}
				getAktuellenKoenig().setMoeglicheZielFelder(alleZielFelder);
			}
		}
		return alleNichtGepinntenFiguren;
	}

	/**
	 * Gibt eine Teilmenge von alleFigurenMitValidenZuegenVorBereinigung zurueck.
	 * Für jedes Element dieser Teilmenge, existiert ein Zielfeld, sodass der Koenig
	 * nicht mehr im Schach steht. Dies kann durch blockieren oder schlagen der
	 * Schachgebendend Figur sein.
	 * 
	 * @return Eine Liste aller Figuren die eine Schachsituation durch blocken
	 *         aufheben können
	 */
	public List<Figur> getAlleFigurenDieBlockenKoennen() {
		List<Figur> alleFigurenDieBlockenKoennen = new ArrayList<Figur>();
		List<Figur> alleFigurenMitValidenZuegenVorBereinigung = bestimmeAlleFigurenMitValidenZuegenVorBereinigung();
		for (Figur figur : alleFigurenMitValidenZuegenVorBereinigung) {
			if (!(figur instanceof Koenig)) {
				List<Feld> alleValidenFelder = figur.bestimmeAlleValidenFelder();
				List<Feld> alleZielFelder = new ArrayList<Feld>();
				for (int i = 0; i < alleValidenFelder.size(); i++) {
					Feld testFeld = pruefeMoveTo(figur, alleValidenFelder.get(i));
					if (testFeld != null) {
						if (!alleFigurenDieBlockenKoennen.contains(figur)) {
							alleFigurenDieBlockenKoennen.add(figur);
						}
						alleZielFelder.add(testFeld);
					}
				}
				figur.setMoeglicheZielFelder(alleZielFelder);
			}
			if (figur.equals(getAktuellenKoenig())) {
				List<Feld> alleEchtValidenFelder = ((Koenig) figur).bestimmeAlleEchtValidenFelder();
				List<Feld> alleZielFelder = new ArrayList<Feld>();
				for (int i = 0; i < alleEchtValidenFelder.size(); i++) {
					Feld testFeld = pruefeMoveTo(getAktuellenKoenig(), alleEchtValidenFelder.get(i));
					if (testFeld != null) {
						alleFigurenDieBlockenKoennen.add(getAktuellenKoenig());
						alleZielFelder.add(testFeld);
					}
				}
				getAktuellenKoenig().setMoeglicheZielFelder(alleZielFelder);
			}

		}
		return alleFigurenDieBlockenKoennen;
	}

	/**
	 * Testet ob eine Figur die Schachsituation durch bewegen auf eines ihrer
	 * Zielfelder aufheben kann
	 * 
	 * @param figur    die zu bewegende Figur
	 * @param zielFeld das ZielFeld von figur
	 * @return Gibt das Zielfeld zurueck wenn sie durch bewegen auf das Zielfeld die
	 *         Schachsituation aufhebt, sonst null
	 */
	public Feld pruefeMoveTo(Figur figur, Feld zielFeld) {
		Figur figurAufDemZielFeld = zielFeld.getFigur();
		Feld ursprungsFeld = Brett.getBrett()[figur.getZeilenIndex()][figur.getSpaltenIndex()];
		zielFeld.setFigur(figur);
		ursprungsFeld.setFigur(null);
		figur.setZeilenIndex(zielFeld.getZeilenIndex());
		figur.setSpaltenIndex(zielFeld.getSpaltenIndex());
		if (!((Koenig) getAktuellenKoenig()).isSchach()) {
			zielFeld.setFigur(figurAufDemZielFeld);
			ursprungsFeld.setFigur(figur);
			figur.setZeilenIndex(ursprungsFeld.getZeilenIndex());
			figur.setSpaltenIndex(ursprungsFeld.getSpaltenIndex());
			return zielFeld;
		}
		zielFeld.setFigur(figurAufDemZielFeld);
		ursprungsFeld.setFigur(figur);
		figur.setZeilenIndex(ursprungsFeld.getZeilenIndex());
		figur.setSpaltenIndex(ursprungsFeld.getSpaltenIndex());
		return null;
	}

	/**
	 * Bestimmt alle Figuren fuer die in aktuelleFarbe gesetzte Farbe, mit
	 * mindestens einer validen Zugmöglichkeit. Es wird nicht berücksichtigt ob
	 * aktuelleFigur evtl. gepinnt ist oder sich nicht bewegen kann weil sie nichts
	 * an einer potentiellen Schachsituation ändern kann
	 * 
	 * @return alleFigurenMitValidenZuegen Eine Liste aller Figuren der aktuellen
	 *         Farbe mit Validen Zuegen
	 */
	public List<Figur> bestimmeAlleFigurenMitValidenZuegenVorBereinigung() {
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
	 * @return the brettInhalt
	 */
	public Feld[][] getBrettInhalt() {
		return brettInhalt;
	}

	/**
	 * @param brettInhalt the brettInhalt to set
	 */
	public void setBrettInhalt(Feld[][] brettInhalt) {
		this.brettInhalt = brettInhalt;
	}

	/**
	 * @return the aktuelleFarbeAmZug
	 */
	public Farbe getAktuelleFarbeAmZug() {
		return aktuelleFarbeAmZug;
	}

	/**
	 * @param aktuelleFarbeAmZug the aktuelleFarbeAmZug to set
	 */
	public void setAktuelleFarbeAmZug(Farbe aktuelleFarbeAmZug) {
		this.aktuelleFarbeAmZug = aktuelleFarbeAmZug;
	}

	/**
	 * @return the alleFigurenMitValidenZuegen
	 */
	public List<Figur> getAlleFigurenMitValidenZuegen() {
		return alleFigurenMitValidenZuegen;
	}

	/**
	 * @param alleFigurenMitValidenZuegen the alleFigurenMitValidenZuegen to set
	 */
	public void setAlleFigurenMitValidenZuegen(List<Figur> alleFigurenMitValidenZuegen) {
		this.alleFigurenMitValidenZuegen = alleFigurenMitValidenZuegen;
	}

	/**
	 * @return the alleFigurenDieBedrohen
	 */
	public List<Figur> getAlleFigurenDieBedrohen() {
		return alleFigurenDieBedrohen;
	}

	/**
	 * @param alleFigurenDieBedrohen the alleFigurenDieBedrohen to set
	 */
	public void setAlleFigurenDieBedrohen(List<Figur> alleFigurenDieBedrohen) {
		this.alleFigurenDieBedrohen = alleFigurenDieBedrohen;
	}

	/**
	 * @return the punkteSchwarz
	 */
	public int getPunkteSchwarz() {
		return punkteSchwarz;
	}

	/**
	 * @param punkteSchwarz the punkteSchwarz to set
	 */
	public void setPunkteSchwarz(int punkteSchwarz) {
		this.punkteSchwarz = punkteSchwarz;
	}

	/**
	 * @return the brett
	 */
	public Brett getBrett() {
		return brett;
	}

	/**
	 * Setzt den Wert aktuelleFarbeAmZug auf Weiß wenn er Schwarz war, sonst auf Schwarz
	 */
	public void wechselAkutelleFarbeAmZug() {
		if (aktuelleFarbeAmZug == BLACK) {
			setAktuelleFarbeAmZug(WHITE);
		} else {
			setAktuelleFarbeAmZug(BLACK);
		}
	}

	/**
	 * Aktualisiet den Punktestand nach einem erfolgten Zug
	 * @param tmpFigurInBrett Die bewegte Figur
	 * @param feldInBrett Das Zielfeld der bewegten Figur
	 */
	public void aktualisierePunkteStand(Figur tmpFigurInBrett, Feld feldInBrett) {
		if(feldInBrett.getFigur()!= null && tmpFigurInBrett.getFarbe() == Farbe.BLACK) {
			punkteSchwarz += feldInBrett.getFigur().getFigurWert();
		}else if(feldInBrett.getFigur() !=null && tmpFigurInBrett.getFarbe() == Farbe.WHITE) {
			punkteSchwarz -= feldInBrett.getFigur().getFigurWert();
		}
	}

	

}
