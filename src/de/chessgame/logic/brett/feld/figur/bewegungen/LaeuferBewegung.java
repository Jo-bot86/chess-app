package de.chessgame.logic.brett.feld.figur.bewegungen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import de.chessgame.logic.brett.Brett;
import de.chessgame.logic.brett.feld.Feld;
import de.chessgame.logic.brett.feld.figur.Figur;
import de.chessgame.logic.brett.feld.figur.typ.Koenig;

/**
 * Repr�sentiert die Bewegungen eines L�ufers. Stellt Methoden bereit um die
 * validen Zielfelder einer Figur in s�d�stlicher, nord�stlicher, s�dwestlicher
 * oder nordwestlicher Richtung zu bestimmen
 * 
 * @author Josef Weldemariam
 *
 */
public class LaeuferBewegung implements Serializable{

	/**
	 * Speichert eine serialVersionUID fuer das lesen von Objekten aus Dateien
	 */
	private static final long serialVersionUID = -4571684390724074844L;
	/**
	 * Speichert die Figur, die die L�ufer Bewegungen ausf�hren soll
	 */
	private Figur figur;

	/**
	 * Initialisiert die Figur, die die L�ufer-Bewegungen ausf�hren soll
	 * 
	 * @param figur die Figur die die L�ufer-Bewegung ausf�hren soll
	 */
	public LaeuferBewegung(Figur figur) {
		this.figur = figur;
	}

	/**
	 * Bestimmt alle validen Felder in nord�stlicher Richtung
	 * 
	 * @return alle validen Felder in nord�stlicher Richtung
	 */
	public List<Feld> bestimmeAlleValidenFelderNordOst() {
		List<Feld> alleValidenFelderNordOst = new ArrayList<Feld>();
		// pr�ft die m�glichen Felder in Richtung Nordost
		if (figur instanceof Koenig) {
			Feld potentiellesFeld = Brett.getBrett()[figur.getZeilenIndex() - 1][figur.getSpaltenIndex() + 1];
			if (potentiellesFeld.getFigur() == null || potentiellesFeld.getFigur().getFarbe() != figur.getFarbe()) {
				alleValidenFelderNordOst.add(potentiellesFeld);

			}
		} else {
			int i = figur.getZeilenIndex() - 1;
			int j = figur.getSpaltenIndex() + 1;
			while (i >= 0 && j < Brett.getBrett()[0].length) {
				Feld potentiellesFeld = Brett.getBrett()[i][j];
				if (potentiellesFeld.getFigur() == null) {
					alleValidenFelderNordOst.add(potentiellesFeld);
				}
				if (potentiellesFeld.getFigur() != null && potentiellesFeld.getFigur().getFarbe() != figur.getFarbe()) {
					alleValidenFelderNordOst.add(potentiellesFeld);
					break;
				}
				if (potentiellesFeld.getFigur() != null && potentiellesFeld.getFigur().getFarbe() == figur.getFarbe()) {
					break;
				}
				i--;
				j++;
			}
		}
		return alleValidenFelderNordOst;
	}

	/**
	 * Bestimmt alle validen Felder in s�dwestlicher Richtung
	 * 
	 * @return alle validen Felder in s�dwestlicher Richtung
	 */
	public List<Feld> bestimmeAlleValidenFelderSuedWest() {
		List<Feld> alleValidenFelderSuedWest = new ArrayList<Feld>();
		// pr�ft die m�glichen Felder in Richtung S�dosten
		if (figur instanceof Koenig) {
			Feld potentiellesFeld = Brett.getBrett()[figur.getZeilenIndex() + 1][figur.getSpaltenIndex() - 1];
			if (potentiellesFeld.getFigur() == null || potentiellesFeld.getFigur().getFarbe() != figur.getFarbe()) {
				alleValidenFelderSuedWest.add(potentiellesFeld);

			}
		} else {
			int i = figur.getZeilenIndex() + 1;
			int j = figur.getSpaltenIndex() - 1;
			while (i < Brett.getBrett().length && j >= 0) {
				Feld potentiellesFeld = Brett.getBrett()[i][j];
				if (potentiellesFeld.getFigur() == null) {
					alleValidenFelderSuedWest.add(potentiellesFeld);
				}
				if (potentiellesFeld.getFigur() != null && potentiellesFeld.getFigur().getFarbe() != figur.getFarbe()) {
					alleValidenFelderSuedWest.add(potentiellesFeld);
					break;
				}
				if (potentiellesFeld.getFigur() != null && potentiellesFeld.getFigur().getFarbe() == figur.getFarbe()) {
					break;
				}
				i++;
				j--;
			}
		}
		return alleValidenFelderSuedWest;
	}

	/**
	 * Bestimmt alle validen Felder in s�d�stlicher Richtung
	 * 
	 * @return alle validen Felder in s�d�stlicher Richtung
	 */
	public List<Feld> bestimmeAlleValidenFelderSuedOst() {
		List<Feld> alleValidenFelderSuedOst = new ArrayList<Feld>();
		// pr�ft die m�glichen Felder in Richtung S�dosten
		if (figur instanceof Koenig) {
			Feld potentiellesFeld = Brett.getBrett()[figur.getZeilenIndex() + 1][figur.getSpaltenIndex() + 1];
			if (potentiellesFeld.getFigur() == null || potentiellesFeld.getFigur().getFarbe() != figur.getFarbe()) {
				alleValidenFelderSuedOst.add(potentiellesFeld);
			}

		} else {
			int i = figur.getZeilenIndex() + 1;
			int j = figur.getSpaltenIndex() + 1;
			while (i < Brett.getBrett().length && j < Brett.getBrett()[0].length) {
				Feld potentiellesFeld = Brett.getBrett()[i][j];
				if (potentiellesFeld.getFigur() == null) {
					alleValidenFelderSuedOst.add(potentiellesFeld);
				}
				if (potentiellesFeld.getFigur() != null && potentiellesFeld.getFigur().getFarbe() != figur.getFarbe()) {
					alleValidenFelderSuedOst.add(potentiellesFeld);
					break;
				}
				if (potentiellesFeld.getFigur() != null && potentiellesFeld.getFigur().getFarbe() == figur.getFarbe()) {
					break;
				}
				i++;
				j++;
			}
		}
		return alleValidenFelderSuedOst;
	}

	/**
	 * Bestimmt alle validen Felder in nordwestlicher Richtung
	 * 
	 * @return alle validen Felder in nordwestlicher Richtung
	 */
	public List<Feld> bestimmeAlleValidenFelderNordWest() {
		List<Feld> alleValidenFelderNordWest = new ArrayList<Feld>();
		// pr�ft die m�glichen Felder in Richtung Nordwest
		if (figur instanceof Koenig) {
			Feld potentiellesFeld = Brett.getBrett()[figur.getZeilenIndex() - 1][figur.getSpaltenIndex() - 1];
			if (potentiellesFeld.getFigur() == null || potentiellesFeld.getFigur().getFarbe() != figur.getFarbe()) {
				alleValidenFelderNordWest.add(potentiellesFeld);
			}

		} else {
			int i = figur.getZeilenIndex() - 1;
			int j = figur.getSpaltenIndex() - 1;
			while (i >= 0 && j >= 0) {
				Feld potentiellesFeld = Brett.getBrett()[i][j];
				if (potentiellesFeld.getFigur() == null) {
					alleValidenFelderNordWest.add(potentiellesFeld);
				}
				if (potentiellesFeld.getFigur() != null && potentiellesFeld.getFigur().getFarbe() != figur.getFarbe()) {
					alleValidenFelderNordWest.add(potentiellesFeld);
					break;
				}
				if (potentiellesFeld.getFigur() != null && potentiellesFeld.getFigur().getFarbe() == figur.getFarbe()) {
					break;
				}
				i--;
				j--;
			}
		}
		return alleValidenFelderNordWest;
	}
}
