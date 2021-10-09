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
 * Repr�sentiert die Beta Version der Klasse SpielAblauf
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
	 * Enth�lt den Punktestand von Schwarz
	 */
	private int punkteSchwarz;

	/**
	 * Enh�lt alle Figuren der anderen Farbe, die den K�nig der aktuellen Farbe
	 * bedrohen
	 */
	private transient List<Figur> alleFigurenDieBedrohen = new ArrayList<Figur>();

	public SpielAblauf(Brett brett) {
		this.brett = brett;
		brettInhalt = Brett.getBrett();
	}
	
	
	/**
	 * Setzt den Wert f�r enPassantSchlagbar auf true wenn der Bauer einen doppel Schritt gemacht hat
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
	 * F�hrt eine Rochade mit dem �bergebenen K�nig- und Turm-Objekt aus
	 * @param koenig an der Rochade beteiliges K�nig-Objekt
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
	 * Pr�ft ob der K�nig und der Turm eine Rochade ausf�hren k�nnen
	 * 
	 * @param koenig an der Rochade beteiligter K�nig
	 * @param turm   an der Rochade beteiligter Turm
	 * @return true genau dann, wenn die Bedingungen f�r eine Rochade erf�llt sind,
	 *         sonst false
	 */
	public boolean isRochadeMoeglich(Koenig koenig, Turm turm) {
		// pr�ft ob sowohl der K�nig als auch der Turm noch nicht bewegt wurden
		if (koenig.isUnbewegt() && turm.isUnbewegt()) {
			// pr�ft um welchen Turm es sich handelt(Damen- oder K�nigsturm)
			if (turm.getSpaltenIndex() == 0) {
				// pr�ft ob die Felder zwischen DamenTurm und K�nig keine Figuren enthalten
				if (felderZumDamenTurmSindUnbelegtUndUnbedroht(koenig) && !koenig.isSchach()) {
					return true;
				}
			}
			if(turm.getSpaltenIndex() == 7) {
				// pr�ft ob die Felder zwischen DamenTurm und K�nig keine Figuren enthalten
				if(felderZumKoenigsTurmSindUnbelegtUndUnbedroht(koenig) && !koenig.isSchach()) {
					return true;
				}
			}
		}

		return false;
	}
	
	/**
	 * Pr�ft ob die Felder zwischen dem K�nig und dem K�nigs-Turm sowohl unbelegt als auch unbedroht
	 * sind. Unbelegt sind Felder genau dann, wenn die Figur auf dem jeweiligen Feld null ist.
	 * Unbedroht genau dann, wenn keines der Felder ein potentielles Zielfeld einer gegnerischen 
	 * Figur ist.
	 * @param koenig der zu �berpr�fende K�nig
	 * @return true genau dann wenn die Felder zwischen K�nig und Turm unbelegt und unbedroht sind, sonst false
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
	 * Pr�ft ob die Felder zwischen dem K�nig und dem Damen-Turm sowohl unbelegt als auch unbedroht
	 * sind. Unbelegt sind Felder genau dann, wenn die Figur auf dem jeweiligen Feld null ist.
	 * Unbedroht genau dann, wenn keines der Felder ein potentielles Zielfeld einer gegnerischen 
	 * Figur ist.
	 * @param koenig der zu �berpr�fende K�nig
	 * @return true genau dann wenn die Felder zwischen K�nig und Turm unbelegt und unbedroht sind, sonst false
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
	 * mindestens einer validen Zugm�glichkeit
	 * 
	 * @return alleFigurenMitValidenZuegen Eine Liste aller Figuren der aktuellen
	 *         Farbe mit Validen Zuegen
	 */
	public List<Figur> bestimmeAlleFigurenMitValidenZuegen() {
		List<Figur> alleFigurenMitValidenZuegen = new ArrayList<Figur>();
		// pr�ft ob der K�nig bereits im Schach steht
		// pr�ft ob eine Figur blocken kann(erledigt)
		// pr�ft ob eine Figur schlagen kann(erledigt)
		// pr�ft ob der K�nig eine Ausweichm�glichkeit hat(bestimmeAlleEchtValidenFelder
		// + ob der K�nig nach dem Zug wieder im Schach ist (erledigt)

		// pr�ft ob der K�nig nach einem Zug im Schach steht(dann ist die Figur gepinnt)
		Koenig aktuellerKoenig = (Koenig) getAktuellenKoenig();
		if (aktuellerKoenig.isSchach()) {
			// Enth�lt alle Figuren die blocken oder schlagen k�nnen und den K�nig wenn er
			// schlagen oder ausweichen kann
			alleFigurenMitValidenZuegen = getAlleFigurenDieBlockenKoennen();
		} else {
			alleFigurenMitValidenZuegen = getAlleNichtGepinntenFiguren();
		}

		return alleFigurenMitValidenZuegen;
	}

	/**
	 * Gibt eine Teilmenge von alleFigurenMitValidenZuegenVorBereinigung zurueck.
	 * F�r jedes Element dieser Teilmenge gilt das nach Bewegung auf ein m�gliches
	 * Zielfeld der K�nig nicht im Schach steht. D.h. keines dieser Figuren in
	 * dieser Teilmenge ist an den K�nig gepinnt
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
	 * F�r jedes Element dieser Teilmenge, existiert ein Zielfeld, sodass der Koenig
	 * nicht mehr im Schach steht. Dies kann durch blockieren oder schlagen der
	 * Schachgebendend Figur sein.
	 * 
	 * @return Eine Liste aller Figuren die eine Schachsituation durch blocken
	 *         aufheben k�nnen
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
	 * mindestens einer validen Zugm�glichkeit. Es wird nicht ber�cksichtigt ob
	 * aktuelleFigur evtl. gepinnt ist oder sich nicht bewegen kann weil sie nichts
	 * an einer potentiellen Schachsituation �ndern kann
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
	 * Setzt den Wert aktuelleFarbeAmZug auf Wei� wenn er Schwarz war, sonst auf Schwarz
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
