package de.chessgame.client.fx;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

/**
 * Erweitert die Button-Klasse aus javafx.scene.control.Button.
 * Diese Button dienen der Erstellung Bretts im GUI. Dieses wird
 * in der Klasse SchachApp erstellt.
 * 
 * @author Josef Weldemariam
 * @see {@link de.chessgame.client.fx.SchachApp SchachApp}
 */
public class ErweiterterButton extends Button {

	/**
	 * Gibt an ob der Button ausgew�hlt wurde und er somit
	 * aktiv ist.
	 */
	private boolean ausgewaehlt = false;
	
	/**
	 * Gibt an ob es sich um einen schwarzes Feld im Schachbrett handelt
	 */
	private boolean schwarzesFeld;
	
	/**
	 * Enth�lt das auf dem Button abgebildete ImageView
	 */
	private ImageView figur = null;
	
	/**
	 * Enth�lt den Zeilenindex des Button.
	 */
	private final int ZEILEN_INDEX;
	
	/**
	 * Enth�lt den Spaltenindex des Button.
	 */
	private final int SPALTEN_INDEX;


	/**
	 * Initialisiet den Button mit einem Zeilen- und Spaltenindex.
	 * @param zeilenIndex
	 * @param spaltenIndex
	 */
	public ErweiterterButton(int zeilenIndex, int spaltenIndex) {
		super();
		this.ZEILEN_INDEX= zeilenIndex;
		this.SPALTEN_INDEX= spaltenIndex;
	}

	/**
	 * Gibt das ImageView des Buttons zur�ck.
	 * @return the figur
	 */
	public ImageView getFigur() {
		return figur;
	}


	/**
	 * Setzt das ImageView des Button.
	 * @param figur the figur to set
	 */
	public void setFigur(ImageView figur) {
		this.figur = figur;
	}

	/**
	 * Gibt den Zeilenindex des Button zur�ck.
	 * @return the zEILEN_INDEX
	 */
	public int getZEILEN_INDEX() {
		return ZEILEN_INDEX;
	}

	/**
	 * Gibt den Spaltenindex des Button zur�ck.
	 * @return the sPALTEN_INDEX
	 */
	public int getSPALTEN_INDEX() {
		return SPALTEN_INDEX;
	}

	/**
	 * �berschreibt die toString-Methode f�r Testzwecke.
	 */
	@Override
	public String toString() {
		return "ErweiterterButton [ausgewaehlt=" + ausgewaehlt + ", schwarzesFeld=" + schwarzesFeld + ", figur=" + figur
				+ "]";
	}

	/**
	 * Gibt an ob es sich um eine schwarzes Feld handelt.
	 * @return schwarzesFeld true genau dann wenn das Feld schwarz ist, sonst false. 
	 */
	public boolean isSchwarzesFeld() {
		return schwarzesFeld;
	}

	/**
	 * Setzt den Wert f�r schwarzesFeld
	 * @param schwarzesFeld the schwarzesFeld to set
	 */
	public void setSchwarzesFeld(boolean schwarzesFeld) {
		this.schwarzesFeld = schwarzesFeld;
	}

	/**
	 * Gibt an ob ein Button durch klick ausgew�hlt ist
	 * @return the ausgewaehlt
	 */
	public boolean isAusgewaehlt() {
		return ausgewaehlt;
	}


	/**
	 * setzt ausgew�hlt auf true oder false
	 * @param ausgewaehlt the ausgewaehlt to set
	 */
	public void setAusgewaehlt(boolean ausgewaehlt) {
		this.ausgewaehlt = ausgewaehlt;
	}
	
	
}
