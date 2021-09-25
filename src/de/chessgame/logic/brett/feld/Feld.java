package de.chessgame.logic.brett.feld;

import java.io.Serializable;

import de.chessgame.logic.brett.feld.figur.typ.*;
import de.chessgame.logic.brett.feld.figur.Figur;

/**
 * Repräsentiert ein Feld auf einem Schachbrett, enthält Information ueber 
 * die genaue Lage auf dem Schachbrett und ueber die auf einem Feld stehende 
 * Figur.
 * @author Josef Weldemariam
 * @see {@link de.chessgame.logic.brett.feld.figur.Figur Figur}
 */
public class Feld implements Serializable{
	
	/**
	 * Enthält die serialVersionUID fuer das Lesen von Objekten aus
	 * Dateien.
	 */
	private static final long serialVersionUID = 2360425856861238777L;

	/**
	 * Enthält den Zeilenindex des Feld-Objekts.
	 */
	private final int ZEILEN_INDEX;
	
	/**
	 * Enthält den Spaltenindex des Feld-Objekts.
	 */
	private final int SPALTEN_INDEX;
	
	/**
	 * Enthält ein Objekt der von Figur erbenden Klassen welche
	 * mit dem Referenztyp Figur angesprochen werden.
	 */
	private Figur figur;

	/**
	 * Erstellt ein neues Feld und weist diesem einen Zeilen- und 
	 * Spaltenindex zu sowie eine Farbe.
	 * @param ZEILEN_INDEX  Zeilenindex des Feld
	 * @param SPALTEN_INDEX Spaltenindex des Feld
	 */
	public Feld(int zeilenIndex, int spaltenIndex) {
		ZEILEN_INDEX = zeilenIndex;
		SPALTEN_INDEX = spaltenIndex;
	}

	/**
	 * Gibt den Zeilenindex des Feld-Objekts zurueck.
	 * @return the zeilenIndex Zeilenindex des Feld
	 */
	public int getZeilenIndex() {
		return ZEILEN_INDEX;
	}

	/**
	 * Gibt den Spaltenindex des Feld-Objekts zurueck.
	 * @return the spaltenIndex Spaltenindex des Feld
	 */
	public int getSpaltenIndex() {
		return SPALTEN_INDEX;
	}
	
	/**
	 * Gibt die auf dem Feld-Objekt aktuelle Figur zurueck.
	 * Kann null sein wenn keine das Feld keine Figur enthält.
	 * @return the figur Figur des Feld
	 */
	public Figur getFigur() {
		return figur;
	}

	/**
	 * Setzt die als Parameter uebergebene Figur auf das Feld-Objekt.
	 * @param figur die zu setztende Figur
	 */
	public void setFigur(Figur figur) {
		this.figur = figur;
	}
	
	/**
	 * Überschreibt die toString-Methode der Object Klasse. 
	 * Abhängig von der Figur des jeweiligen Felds gibt sie
	 * B fuer Bauer, T fuer Turm, D fuer Dame, S fuer Springer,
	 * K fuer König und L fuer Läufer zurueck.
	 * Befindet sich keine Figur auf dem Feld wird N fuer Null 
	 * ausgegeben.
	 */
	@Override
	public String toString() {
		//String koordinaten = "(" + Integer.toString(ZEILEN_INDEX) + "|" + Integer.toString(SPALTEN_INDEX)+ ")";
		if(figur instanceof Bauer) {
			return "B";
		}
		if(figur instanceof Turm) {
			return "T";
		}
		if(figur instanceof Springer) {
			return "S";
		}
		if(figur instanceof Laeufer) {
			return "L";
		}
		if(figur instanceof Dame) {
			return "D";
		}
		if(figur instanceof Koenig) {
			return "K";
		}
		return "N";
	}
}
