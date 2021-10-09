package de.chessgame.logic.brett.feld.figur.typ;

import java.util.ArrayList;
import java.util.List;

import de.chessgame.logic.brett.Brett;
import de.chessgame.logic.brett.feld.Feld;
import de.chessgame.logic.brett.feld.figur.Farbe;
import de.chessgame.logic.brett.feld.figur.Figur;

/**
 * Repräsentiert die Schachfigur Springer. Implementiert die verschiedene
 * methoden zur Bestimmung aller potentiellen Zielfelder abhängig von der
 * aktuellen Position. Folgende Skizze eines Schachbretts zeigt die moeglichen
 * Zielfelder von einer bestimmten Position aus, vorausgesetzt keines der
 * moeglichen Zielfelder wird durch eine Figur der eigenen Farbe blockiert. <br>
 * <br>
 * 2 3 4 4 4 4 3 2 <br>
 * 3 4 6 6 6 6 4 3 <br>
 * 4 6 8 8 8 8 6 4 <br>
 * 4 6 8 8 8 8 6 4 <br>
 * 4 6 8 8 8 8 6 4 <br>
 * 4 6 8 8 8 8 6 4 <br>
 * 3 4 6 6 6 6 4 3 <br>
 * 2 3 4 4 4 4 3 2 <br>
 * 
 * @author Josef Weldemariam
 * @see {@link de.chessgame.logic.brett.feld.figur.Figur Figur}
 */
public class Springer extends Figur {

	/**
	 * Speichert eine serialVersionUID fuer das lesen von Objekten aus Dateien
	 */
	private static final long serialVersionUID = 5913832857795356620L;

	/**
	 * ruft den Konstruktor der Elternklasse mit dem Zeilen- und Spaltenindex sowie
	 * mit einer Farbe auf
	 * 
	 * @param zeilenIndex  Zeilenindex des Springer-Objekts
	 * @param spaltenIndex Spaltenindex des Springer-Objekts
	 * @param farbe        Farbe des Springer-Objekts
	 */
	public Springer(int zeilenIndex, int spaltenIndex, Farbe farbe) {
		super(zeilenIndex, spaltenIndex, farbe);
		figurWert=3;
	}

	/**
	 * Bestimmt alle moeglichen Felder die ein Springer von einer Ecke des
	 * Schachbretts erreichen kann. Von jeder Ecke koennen maximal zwei Felder
	 * erreicht werden abhaengig davon, ob eines dieser Felder bereits mit einer
	 * Figur der eigenen Farbe besetzt ist. Wird ein moegliches Zielfeld von einer
	 * Figur der selben Farbe blockiert, wird dieses Feld nicht der Liste zugefuegt.
	 * Wird das Feld von einer Figur anderer Farbe blockiert, wird es zugefuegt
	 * 
	 * @return Liste mit allen moeglichen Zielfeldern
	 * @see {@link de.schach.figur.Figur#isEckFigur()}
	 */
	public List<Feld> bestimmeAlleValidenFelderEcke() {
		List<Feld> alleValidenFelderEcke = new ArrayList<Feld>();
		List<Feld> potentielleFelder = new ArrayList<Feld>();
		for(int i=0; i<2; i++) {
			Feld potentiellesFeld = null;
			potentielleFelder.add(potentiellesFeld);
		}
		if (isEckFigur() == 1) {
			potentielleFelder.set(0, Brett.getBrett()[zeilenIndex + 1][spaltenIndex + 2]);
			potentielleFelder.set(1, Brett.getBrett()[zeilenIndex + 2][spaltenIndex + 1]);
			
		}
		if (isEckFigur() == 2) {
			potentielleFelder.set(0, Brett.getBrett()[zeilenIndex + 1][spaltenIndex - 2]);
			potentielleFelder.set(1, Brett.getBrett()[zeilenIndex + 2][spaltenIndex - 1]);

		}
		if (isEckFigur() == 3) {
			potentielleFelder.set(0, Brett.getBrett()[zeilenIndex - 1][spaltenIndex + 2]);
			potentielleFelder.set(1, Brett.getBrett()[zeilenIndex - 2][spaltenIndex + 1]);
			
		}
		if (isEckFigur() == 4) {
			potentielleFelder.set(0, Brett.getBrett()[zeilenIndex - 1][spaltenIndex - 2]);
			potentielleFelder.set(1, Brett.getBrett()[zeilenIndex - 2][spaltenIndex - 1]);
			
		}
		
		for (int i = 0; i < potentielleFelder.size(); i++) {
			if (potentielleFelder.get(i).getFigur() == null
					|| farbe != potentielleFelder.get(i).getFigur().getFarbe()) {
				alleValidenFelderEcke.add(potentielleFelder.get(i));
			}
		}

		return alleValidenFelderEcke;
	}

	/**
	 * Bestimmt alle moeglichen Zielfelder die ein Springer von einem Feld am Rand
	 * des Schachbretts erreichen kann. Beruecksichtigt werden nur die Felder am
	 * Rand, die drei potentielle Zielfelder zulassen. Somit kommen nur folgende
	 * Felder in Frage: (zeilenIndex,spaltenIndex) =(0,1), (1,0), (0,6), (1,7),
	 * (6,0),(7,1),(6,7),(7,6). Wird ein moegliches Zielfeld von einer Figur der
	 * selben Farbe blockiert, wird dieses Feld nicht der Liste zugefuegt. Wird das
	 * Feld von einer Figur anderer Farbe blockiert, wird es zugefuegt
	 * 
	 * @return
	 */
	public List<Feld> bestimmeAlleValidenFelderDreier() {
		List<Feld> alleValidenFelder = new ArrayList<Feld>();
		List<Feld> potentielleFelder = new ArrayList<Feld>();
		for (int i = 0; i < 3; i++) {
			Feld potentiellesFeld = null;
			potentielleFelder.add(potentiellesFeld);
		}

		// prüft alle möglichen Zielfelder die ein Springer vom Feld mit Zeilenindex 0
		// und SpaltenIndex 1 erreichen kann
		if (zeilenIndex == 0 && spaltenIndex == 1) {
			potentielleFelder.set(0, Brett.getBrett()[zeilenIndex + 2][spaltenIndex - 1]);
			potentielleFelder.set(1, Brett.getBrett()[zeilenIndex + 2][spaltenIndex + 1]);
			potentielleFelder.set(2, Brett.getBrett()[zeilenIndex + 1][spaltenIndex + 2]);

		}
		// prüft alle möglichen Zielfelder die ein Springer vom Feld mit Zeilenindex 0
		// und SpaltenIndex 6 erreichen kann
		if (zeilenIndex == 0 && spaltenIndex == 6) {
			potentielleFelder.set(0, Brett.getBrett()[zeilenIndex + 1][spaltenIndex - 2]);
			potentielleFelder.set(1, Brett.getBrett()[zeilenIndex + 2][spaltenIndex - 1]);
			potentielleFelder.set(2, Brett.getBrett()[zeilenIndex + 2][spaltenIndex + 1]);

		}
		// prüft alle möglichen Zielfelder die ein Springer vom Feld mit Zeilenindex 1
		// und SpaltenIndex 0 erreichen kann
		if (zeilenIndex == 1 && spaltenIndex == 0) {
			potentielleFelder.set(0, Brett.getBrett()[zeilenIndex + 2][spaltenIndex + 1]);
			potentielleFelder.set(1, Brett.getBrett()[zeilenIndex + 1][spaltenIndex + 2]);
			potentielleFelder.set(2, Brett.getBrett()[zeilenIndex - 1][spaltenIndex + 2]);

		}
		// prüft alle möglichen Zielfelder die ein Springer vom Feld mit Zeilenindex 1
		// und SpaltenIndex 7 erreichen kann
		if (zeilenIndex == 1 && spaltenIndex == 7) {
			potentielleFelder.set(0, Brett.getBrett()[zeilenIndex - 1][spaltenIndex - 2]);
			potentielleFelder.set(1, Brett.getBrett()[zeilenIndex + 1][spaltenIndex - 2]);
			potentielleFelder.set(2, Brett.getBrett()[zeilenIndex + 2][spaltenIndex - 1]);

		}
		// prüft alle möglichen Zielfelder die ein Springer vom Feld mit Zeilenindex 6
		// und SpaltenIndex 0 erreichen kann
		if (zeilenIndex == 6 && spaltenIndex == 0) {
			potentielleFelder.set(0, Brett.getBrett()[zeilenIndex - 2][spaltenIndex + 1]);
			potentielleFelder.set(1, Brett.getBrett()[zeilenIndex - 1][spaltenIndex + 2]);
			potentielleFelder.set(2, Brett.getBrett()[zeilenIndex + 1][spaltenIndex + 2]);

		}
		// prüft alle möglichen Zielfelder die ein Springer vom Feld mit Zeilenindex 6
		// und SpaltenIndex 7 erreichen kann
		if (zeilenIndex == 6 && spaltenIndex == 7) {
			potentielleFelder.set(0, Brett.getBrett()[zeilenIndex - 2][spaltenIndex - 1]);
			potentielleFelder.set(1, Brett.getBrett()[zeilenIndex - 1][spaltenIndex - 2]);
			potentielleFelder.set(2, Brett.getBrett()[zeilenIndex + 1][spaltenIndex - 2]);

		}
		// prüft alle möglichen Zielfelder die ein Springer vom Feld mit Zeilenindex 7
		// und SpaltenIndex 1 erreichen kann
		if (zeilenIndex == 7 && spaltenIndex == 1) {
			potentielleFelder.set(0, Brett.getBrett()[zeilenIndex - 2][spaltenIndex - 1]);
			potentielleFelder.set(1, Brett.getBrett()[zeilenIndex - 2][spaltenIndex + 1]);
			potentielleFelder.set(2, Brett.getBrett()[zeilenIndex - 1][spaltenIndex + 2]);

		}
		// prüft alle möglichen Zielfelder die ein Springer vom Feld mit Zeilenindex 7
		// und SpaltenIndex 6 erreichen kann
		if (zeilenIndex == 7 && spaltenIndex == 6) {
			potentielleFelder.set(0, Brett.getBrett()[zeilenIndex - 1][spaltenIndex - 2]);
			potentielleFelder.set(1, Brett.getBrett()[zeilenIndex - 2][spaltenIndex - 1]);
			potentielleFelder.set(2, Brett.getBrett()[zeilenIndex - 2][spaltenIndex + 1]);
		}

		for (int i = 0; i < potentielleFelder.size(); i++) {
			if (potentielleFelder.get(i).getFigur() == null
					|| farbe != potentielleFelder.get(i).getFigur().getFarbe()) {
				alleValidenFelder.add(potentielleFelder.get(i));
			}
		}

		return alleValidenFelder;
	}

	/**
	 * Bestimmt alle meoglichen Felder die ein Springer erreichen kann, wobei das
	 * aktuelle Feld ein Feld ist von dem potentiell vier Felder erreicht werden
	 * koennen. Diese befinden sich am oberen bzw unteren Rand mit 1<spaltenIndex <6
	 * sowie am linken und rechten Rand mit 1<zeilenIndex<6 und vier weitere Felder
	 * mit den Indizes (1,1), (1,6), (6,1) und (6,6). Wird ein moegliches Zielfeld
	 * von einer Figur der selben Farbe blockiert, wird dieses Feld nicht der Liste
	 * zugefuegt. Wird das Feld von einer Figur anderer Farbe blockiert, wird es
	 * zugefuegt
	 * 
	 * @return Liste mit allen moeglichen Zielfeldern
	 */
	public List<Feld> bestimmeAlleValidenFelderVierer() {
		List<Feld> alleValidenFelder = new ArrayList<Feld>();
		List<Feld> potentielleFelder = new ArrayList<Feld>();
		for (int i = 0; i < 4; i++) {
			Feld potentiellesFeld = null;
			potentielleFelder.add(potentiellesFeld);
		}
		// Bestimmt die moeglichen Zielfelder eines Springers der zeilenIndex 0 sowie
		// spaltenIndex groeser 1 und kleiner 6 hat
		if (zeilenIndex == 0 && spaltenIndex > 1 && spaltenIndex < 6) {
			potentielleFelder.set(0, Brett.getBrett()[zeilenIndex + 1][spaltenIndex - 2]);
			potentielleFelder.set(1, Brett.getBrett()[zeilenIndex + 2][spaltenIndex - 1]);
			potentielleFelder.set(2, Brett.getBrett()[zeilenIndex + 2][spaltenIndex + 1]);
			potentielleFelder.set(3, Brett.getBrett()[zeilenIndex + 1][spaltenIndex + 2]);

		}
		// Bestimmt die moeglichen Zielfelder eines Springers der spaltenIndex 0 sowie
		// spaltenIndex groeser 1 und kleiner 6 hat
		if (spaltenIndex == 0 && zeilenIndex > 1 && zeilenIndex < 6) {
			potentielleFelder.set(0, Brett.getBrett()[zeilenIndex - 2][spaltenIndex + 1]);
			potentielleFelder.set(1, Brett.getBrett()[zeilenIndex - 1][spaltenIndex + 2]);
			potentielleFelder.set(2, Brett.getBrett()[zeilenIndex + 1][spaltenIndex + 2]);
			potentielleFelder.set(3, Brett.getBrett()[zeilenIndex + 2][spaltenIndex + 1]);
			
		}
		// Bestimmt die moeglichen Zielfelder eines Springers der spaltenIndex 7 sowie
		// zeilen groeser 1 und kleiner 6 hat
		if (spaltenIndex == 7 && zeilenIndex > 1 && zeilenIndex < 6) {
			potentielleFelder.set(0, Brett.getBrett()[zeilenIndex - 2][spaltenIndex - 1]);
			potentielleFelder.set(1, Brett.getBrett()[zeilenIndex -1 ][spaltenIndex - 2]);
			potentielleFelder.set(2, Brett.getBrett()[zeilenIndex + 1][spaltenIndex - 2]);
			potentielleFelder.set(3, Brett.getBrett()[zeilenIndex + 2][spaltenIndex - 1]);
			
		}
		// Bestimmt die moeglichen Zielfelder eines Springers der zeilenIndex 7 sowie
		// spaltenIndex groeser 1 und kleiner 6 hat
		if (zeilenIndex == 7 && spaltenIndex > 1 && spaltenIndex < 6) {
			potentielleFelder.set(0, Brett.getBrett()[zeilenIndex - 1][spaltenIndex - 2]);
			potentielleFelder.set(1, Brett.getBrett()[zeilenIndex - 2][spaltenIndex - 1]);
			potentielleFelder.set(2, Brett.getBrett()[zeilenIndex - 2][spaltenIndex + 1]);
			potentielleFelder.set(3, Brett.getBrett()[zeilenIndex - 1][spaltenIndex + 2]);
			
		}
		// Bestimmt die moeglichen Zielfelder eines Springer der
		// zeilenIndex = spaltenindex = 1 hat
		if (zeilenIndex == 1 && spaltenIndex == 1) {
			potentielleFelder.set(0, Brett.getBrett()[zeilenIndex + 2][spaltenIndex - 1]);
			potentielleFelder.set(1, Brett.getBrett()[zeilenIndex + 2][spaltenIndex + 1]);
			potentielleFelder.set(2, Brett.getBrett()[zeilenIndex + 1][spaltenIndex + 2]);
			potentielleFelder.set(3, Brett.getBrett()[zeilenIndex - 1][spaltenIndex + 2]);
			
		}
		// Bestimmt die moeglichen Zielfelder eines Springer der
		// zeilenIndex = 1 und spaltenindex = 6 hat
		if (zeilenIndex == 1 && spaltenIndex == 6) {
			potentielleFelder.set(0, Brett.getBrett()[zeilenIndex - 1][spaltenIndex - 2]);
			potentielleFelder.set(1, Brett.getBrett()[zeilenIndex + 1][spaltenIndex - 2]);
			potentielleFelder.set(2, Brett.getBrett()[zeilenIndex + 2][spaltenIndex - 1]);
			potentielleFelder.set(3, Brett.getBrett()[zeilenIndex + 2][spaltenIndex + 1]);

		}
		// Bestimmt die moeglichen Zielfelder eines Springer der
		// zeilenIndex = 6 spaltenindex = 1 hat
		if (zeilenIndex == 6 && spaltenIndex == 1) {
			potentielleFelder.set(0, Brett.getBrett()[zeilenIndex - 2][spaltenIndex - 1]);
			potentielleFelder.set(1, Brett.getBrett()[zeilenIndex - 2][spaltenIndex + 1]);
			potentielleFelder.set(2, Brett.getBrett()[zeilenIndex - 1][spaltenIndex + 2]);
			potentielleFelder.set(3, Brett.getBrett()[zeilenIndex + 1][spaltenIndex + 2]);

		}
		// Bestimmt die moeglichen Zielfelder eines Springer der
		// zeilenIndex = spaltenindex = 6 hat
		if (zeilenIndex == 6 && spaltenIndex == 6) {
			potentielleFelder.set(0, Brett.getBrett()[zeilenIndex + 1][spaltenIndex - 2]);
			potentielleFelder.set(1, Brett.getBrett()[zeilenIndex - 1][spaltenIndex - 2]);
			potentielleFelder.set(2, Brett.getBrett()[zeilenIndex - 2][spaltenIndex - 1]);
			potentielleFelder.set(3, Brett.getBrett()[zeilenIndex - 2][spaltenIndex + 1]);

		}
		
		for (int i = 0; i < potentielleFelder.size(); i++) {
			if (potentielleFelder.get(i).getFigur() == null
					|| farbe != potentielleFelder.get(i).getFigur().getFarbe()) {
				alleValidenFelder.add(potentielleFelder.get(i));
			}
		}

		return alleValidenFelder;
	}

	/**
	 * Bestimmt alle meoglichen Felder die ein Springer erreichen kann, wobei das
	 * aktuelle Feld ein Feld ist von dem potentiell sechs Felder erreicht werden
	 * koennen. Diese befinden sich bei zeilenIndex 1 und 6 mit 1<spaltenIndex <6
	 * sowie bei spaltenIndex 1 und 6 mit 1<zeilenIndex<6. Wird ein moegliches
	 * Zielfeld von einer Figur der selben Farbe blockiert, wird dieses Feld nicht
	 * der Liste zugefuegt. Wird das Feld von einer Figur anderer Farbe blockiert,
	 * wird es zugefuegt
	 * 
	 * @return Liste mit allen moeglichen Zielfeldern
	 */
	public List<Feld> bestimmeAlleValidenFelderSechser() {
		List<Feld> alleValidenFelder = new ArrayList<Feld>();
		List<Feld> potentielleFelder = new ArrayList<Feld>();
		for (int i = 0; i < 6; i++) {
			Feld potentiellesFeld = null;
			potentielleFelder.add(potentiellesFeld);
		}
		if (zeilenIndex == 1) {
			potentielleFelder.set(0, Brett.getBrett()[zeilenIndex - 1][spaltenIndex - 2]);
			potentielleFelder.set(1, Brett.getBrett()[zeilenIndex + 1][spaltenIndex - 2]);
			potentielleFelder.set(2, Brett.getBrett()[zeilenIndex + 2][spaltenIndex - 1]);
			potentielleFelder.set(3, Brett.getBrett()[zeilenIndex - 1][spaltenIndex + 2]);
			potentielleFelder.set(4, Brett.getBrett()[zeilenIndex + 1][spaltenIndex + 2]);
			potentielleFelder.set(5, Brett.getBrett()[zeilenIndex + 2][spaltenIndex + 1]);

		}
		if (zeilenIndex == 6) {
			potentielleFelder.set(0, Brett.getBrett()[zeilenIndex + 1][spaltenIndex - 2]);
			potentielleFelder.set(1, Brett.getBrett()[zeilenIndex - 1][spaltenIndex - 2]);
			potentielleFelder.set(2, Brett.getBrett()[zeilenIndex - 2][spaltenIndex - 1]);
			potentielleFelder.set(3, Brett.getBrett()[zeilenIndex - 2][spaltenIndex + 1]);
			potentielleFelder.set(4, Brett.getBrett()[zeilenIndex - 1][spaltenIndex + 2]);
			potentielleFelder.set(5, Brett.getBrett()[zeilenIndex + 1][spaltenIndex + 2]);

		}
		if (spaltenIndex == 1) {
			potentielleFelder.set(0, Brett.getBrett()[zeilenIndex - 2][spaltenIndex - 1]);
			potentielleFelder.set(1, Brett.getBrett()[zeilenIndex - 2][spaltenIndex + 1]);
			potentielleFelder.set(2, Brett.getBrett()[zeilenIndex - 1][spaltenIndex + 2]);
			potentielleFelder.set(3, Brett.getBrett()[zeilenIndex + 1][spaltenIndex + 2]);
			potentielleFelder.set(4, Brett.getBrett()[zeilenIndex + 2][spaltenIndex + 1]);
			potentielleFelder.set(5, Brett.getBrett()[zeilenIndex + 2][spaltenIndex - 1]);

		}
		if (spaltenIndex == 6) {
			potentielleFelder.set(0, Brett.getBrett()[zeilenIndex - 2][spaltenIndex - 1]);
			potentielleFelder.set(1, Brett.getBrett()[zeilenIndex - 2][spaltenIndex + 1]);
			potentielleFelder.set(2, Brett.getBrett()[zeilenIndex - 1][spaltenIndex - 2]);
			potentielleFelder.set(3, Brett.getBrett()[zeilenIndex + 1][spaltenIndex - 2]);
			potentielleFelder.set(4, Brett.getBrett()[zeilenIndex + 2][spaltenIndex + 1]);
			potentielleFelder.set(5, Brett.getBrett()[zeilenIndex + 2][spaltenIndex - 1]);
			
		}
		
		for (int i = 0; i < potentielleFelder.size(); i++) {
			if (potentielleFelder.get(i).getFigur() == null
					|| farbe != potentielleFelder.get(i).getFigur().getFarbe()) {
				alleValidenFelder.add(potentielleFelder.get(i));
			}
		}

		return alleValidenFelder;
	}

	/**
	 * Bestimmt alle meoglichen Felder die ein Springer erreichen kann, wobei das
	 * aktuelle Feld ein Feld ist von dem potentiell acht Felder erreicht werden
	 * koennen. Diese haben die Indizes 1<zeilenIndex<6 und 1<spaltenIndex<6. Wird
	 * ein moegliches Zielfeld von einer Figur der selben Farbe blockiert, wird
	 * dieses Feld nicht der Liste zugefuegt. Wird das Feld von einer Figur anderer
	 * Farbe blockiert, wird es zugefuegt
	 * 
	 * @return Liste mit allen moeglichen Zielfeldern
	 */
	private List<Feld> bestimmeAlleValidenFelderAchter() {
		List<Feld> alleValidenFelder = new ArrayList<Feld>();
		List<Feld> potentielleFelder = new ArrayList<Feld>();
		for(int i=0; i< 8; i++) {
			Feld potentiellesFeld = null;
			potentielleFelder.add(potentiellesFeld);
		}
		if (zeilenIndex > 1 && zeilenIndex < 6 && spaltenIndex > 1 && spaltenIndex < 6) {
			potentielleFelder.set(0, Brett.getBrett()[zeilenIndex - 1][spaltenIndex - 2]);
			potentielleFelder.set(1, Brett.getBrett()[zeilenIndex + 1][spaltenIndex - 2]);
			potentielleFelder.set(2, Brett.getBrett()[zeilenIndex + 2][spaltenIndex - 1]);
			potentielleFelder.set(3, Brett.getBrett()[zeilenIndex - 1][spaltenIndex + 2]);
			potentielleFelder.set(4, Brett.getBrett()[zeilenIndex + 1][spaltenIndex + 2]);
			potentielleFelder.set(5, Brett.getBrett()[zeilenIndex + 2][spaltenIndex + 1]);
			potentielleFelder.set(6, Brett.getBrett()[zeilenIndex - 2][spaltenIndex - 1]);
			potentielleFelder.set(7, Brett.getBrett()[zeilenIndex - 2][spaltenIndex + 1]);

		}
		
		for(int i=0; i< potentielleFelder.size(); i++) {
			if (potentielleFelder.get(i).getFigur() == null || farbe != potentielleFelder.get(i).getFigur().getFarbe()) {
				alleValidenFelder.add(potentielleFelder.get(i));
			}
		}
		
		return alleValidenFelder;
	}

	/**
	 * Bestimmt alle validen Zielfelder des Springer-Objekts, abhaengig von seiner
	 * Position. Hierzu werden die entsprechenden Methoden aufgerufen.
	 * 
	 * @return eine Liste aller potentiellen Zielfelder
	 */
	public List<Feld> bestimmeAlleValidenFelder() {

		if (zeilenIndex == 0 && (spaltenIndex == 0 || spaltenIndex == 7)
				|| zeilenIndex == 7 && (spaltenIndex == 0 || spaltenIndex == 7)) {
			return bestimmeAlleValidenFelderEcke();
		}
		if (zeilenIndex == 0 && (spaltenIndex == 1 || spaltenIndex == 6)
				|| zeilenIndex == 1 && (spaltenIndex == 0 || spaltenIndex == 7)
				|| zeilenIndex == 6 && (spaltenIndex == 0 || spaltenIndex == 7)
				|| zeilenIndex == 7 && (spaltenIndex == 1 || spaltenIndex == 6)) {
			return bestimmeAlleValidenFelderDreier();
		}
		if (zeilenIndex == 0 && spaltenIndex > 1 && spaltenIndex < 6
				|| spaltenIndex == 0 && zeilenIndex > 1 && zeilenIndex < 6
				|| spaltenIndex == 7 && zeilenIndex > 1 && zeilenIndex < 6
				|| zeilenIndex == 7 && spaltenIndex > 1 && spaltenIndex < 6 || zeilenIndex == 1 && spaltenIndex == 1
				|| zeilenIndex == 1 && spaltenIndex == 6 || zeilenIndex == 6 && spaltenIndex == 1
				|| zeilenIndex == 6 && spaltenIndex == 6) {
			return bestimmeAlleValidenFelderVierer();
		}
		if (zeilenIndex == 1 && spaltenIndex > 1 && spaltenIndex < 6
				|| spaltenIndex == 1 && zeilenIndex > 1 && zeilenIndex < 6
				|| spaltenIndex == 6 && zeilenIndex > 1 && zeilenIndex < 6
				|| zeilenIndex == 6 && spaltenIndex > 1 && spaltenIndex < 6) {
			return bestimmeAlleValidenFelderSechser();
		}

		return bestimmeAlleValidenFelderAchter();
	}

}
