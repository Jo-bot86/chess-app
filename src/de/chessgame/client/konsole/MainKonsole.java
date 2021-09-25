package de.chessgame.client.konsole;

import de.chessgame.logic.ablauf.SpielAblauf;
import de.chessgame.logic.brett.Brett;

/**
 * Führt die Konolenanwendung aus
 * @author Josef Weldemariam
 *
 */
public class MainKonsole {

	public static void main(String[] args) {
		KonsolenAusgabe ausgabe = new KonsolenAusgabe(new SpielAblauf(new Brett(8,8)));
		ausgabe.getAblauf().getBrett().init();
		Brett.print();		
		System.out.println(Brett.getBrett()[7][1].getFigur().getMoeglicheZielFelder());
	}

}
