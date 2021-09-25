package de.chessgame.logic.brett.feld.figur.bewegungen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import de.chessgame.logic.brett.Brett;
import de.chessgame.logic.brett.feld.Feld;
import de.chessgame.logic.brett.feld.figur.Figur;
import de.chessgame.logic.brett.feld.figur.typ.Koenig;

/**
 * Repr�sentiert die Bewegungen eines Turms. Stellt Methoden bereit um die
 * validen Zielfelder einer Figur in s�dlicher, n�rdlicher, westlicher oder
 * �stlicher Richtung zu bestimmen
 * 
 * @author Josef Weldemariam
 */
public class TurmBewegung implements Serializable{

	/**
	 * Speichert eine serialVersionUID fuer das lesen von Objekten aus Dateien
	 */
	private static final long serialVersionUID = -8409987283284069339L;
	
	/**
	 * Speichert die Figur, die die Turm Bewegungen ausf�hren soll
	 */
	private Figur figur;

	/**
	 * Initialisiert die Figur, die die Turm Bewegungen ausf�hren soll
	 * 
	 * @param figur         die die Turm-Bewegung ausf�hrt
	 * @param beschraenkung die Beschr�nkung die f�r die Bewegung gilt
	 */
	public TurmBewegung(Figur figur) {
		this.figur = figur;
	}

	/**
	 * Bestimmt alle validen Felder in �stlicher Richtung
	 * 
	 * @return alle validen Felder in �stlicher Richtung
	 */
	public List<Feld> bestimmeAlleValidenFelderOsten() {
		List<Feld> alleValidenFelderOsten = new ArrayList<Feld>();
		// pr�ft die m�glichen Felder in Richtung Osten
		if (figur instanceof Koenig) {
			Feld potentiellesFeld = Brett.getBrett()[figur.getZeilenIndex()][figur.getSpaltenIndex() + 1];
			if (potentiellesFeld.getFigur() == null || potentiellesFeld.getFigur().getFarbe() != figur.getFarbe()) {
				alleValidenFelderOsten.add(potentiellesFeld);
			}
		} else {
			for (int i = figur.getSpaltenIndex() + 1; i < Brett.getBrett()[0].length; i++) {
				Feld potentiellesFeld = Brett.getBrett()[figur.getZeilenIndex()][i];
				if (potentiellesFeld.getFigur() == null) {
					alleValidenFelderOsten.add(potentiellesFeld);
				}
				if (potentiellesFeld.getFigur() != null && potentiellesFeld.getFigur().getFarbe() != figur.getFarbe()) {
					alleValidenFelderOsten.add(potentiellesFeld);
					break;
				}
				if (potentiellesFeld.getFigur() != null && potentiellesFeld.getFigur().getFarbe() == figur.getFarbe()) {
					break;
				}
			}
		}

		return alleValidenFelderOsten;
	}

	/**
	 * Bestimmt alle validen Felder in s�dlicher Richtung
	 * 
	 * @return alle validen Felder in s�dlicher Richtung
	 */
	public List<Feld> bestimmeAlleValidenFelderSueden() {
		List<Feld> alleValidenFelderSueden = new ArrayList<Feld>();
		// pr�ft die m�glichen Felder in Richtung S�den
		if (figur instanceof Koenig) {
			Feld potentiellesFeld = Brett.getBrett()[figur.getZeilenIndex() + 1][figur.getSpaltenIndex()];
			if (potentiellesFeld.getFigur() == null || potentiellesFeld.getFigur().getFarbe() != figur.getFarbe()) {
				alleValidenFelderSueden.add(potentiellesFeld);
			}
		} else {
			for (int i = figur.getZeilenIndex() + 1; i < Brett.getBrett().length; i++) {
				Feld potentiellesFeld = Brett.getBrett()[i][figur.getSpaltenIndex()];
				if (potentiellesFeld.getFigur() == null) {
					alleValidenFelderSueden.add(potentiellesFeld);
				}
				if (potentiellesFeld.getFigur() != null && potentiellesFeld.getFigur().getFarbe() != figur.getFarbe()) {
					alleValidenFelderSueden.add(potentiellesFeld);
					break;
				}
				if (potentiellesFeld.getFigur() != null && potentiellesFeld.getFigur().getFarbe() == figur.getFarbe()) {
					break;
				}
			}
		}
		return alleValidenFelderSueden;
	}

	/**
	 * Bestimmt alle validen Felder in n�rdlicher Richtung
	 * 
	 * @return alle validen Felder in n�rdlicher Richtung
	 */
	public List<Feld> bestimmeAlleValidenFelderNorden() {
		List<Feld> alleValidenFelderNorden = new ArrayList<Feld>();
		// pr�ft die m�glichen Felder in Richtung S�den
		if (figur instanceof Koenig) {
			Feld potentiellesFeld = Brett.getBrett()[figur.getZeilenIndex() - 1][figur.getSpaltenIndex()];
			if (potentiellesFeld.getFigur() == null || potentiellesFeld.getFigur().getFarbe() != figur.getFarbe()) {
				alleValidenFelderNorden.add(potentiellesFeld);
			}
		} else {
			for (int i = figur.getZeilenIndex() - 1; i >= 0; i--) {
				Feld potentiellesFeld = Brett.getBrett()[i][figur.getSpaltenIndex()];
				if (potentiellesFeld.getFigur() == null) {
					alleValidenFelderNorden.add(potentiellesFeld);
				}
				if (potentiellesFeld.getFigur() != null && potentiellesFeld.getFigur().getFarbe() != figur.getFarbe()) {
					alleValidenFelderNorden.add(potentiellesFeld);
					break;
				}
				if (potentiellesFeld.getFigur() != null && potentiellesFeld.getFigur().getFarbe() == figur.getFarbe()) {
					break;
				}
			}
		}
		return alleValidenFelderNorden;
	}

	/**
	 * Bestimmt alle validen Felder in westlicher Richtung
	 * 
	 * @return alle validen Felder in westlicher Richtung
	 */
	public List<Feld> bestimmeAlleValidenFelderWesten() {
		List<Feld> alleValidenFelderWesten = new ArrayList<Feld>();
		// pr�ft die m�glichen Felder in Richtung S�den
		if (figur instanceof Koenig) {
			Feld potentiellesFeld = Brett.getBrett()[figur.getZeilenIndex()][figur.getSpaltenIndex() - 1];
			if (potentiellesFeld.getFigur() == null || potentiellesFeld.getFigur().getFarbe() != figur.getFarbe()) {
				alleValidenFelderWesten.add(potentiellesFeld);
			}
		} else {
			for (int i = figur.getSpaltenIndex() - 1; i >= 0; i--) {
				Feld potentiellesFeld = Brett.getBrett()[figur.getZeilenIndex()][i];
				if (potentiellesFeld.getFigur() == null) {
					alleValidenFelderWesten.add(potentiellesFeld);
				}
				if (potentiellesFeld.getFigur() != null && potentiellesFeld.getFigur().getFarbe() != figur.getFarbe()) {
					alleValidenFelderWesten.add(potentiellesFeld);
					break;
				}
				if (potentiellesFeld.getFigur() != null && potentiellesFeld.getFigur().getFarbe() == figur.getFarbe()) {
					break;
				}
			}
		}
		return alleValidenFelderWesten;
	}

}