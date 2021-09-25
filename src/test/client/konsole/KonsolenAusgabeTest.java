package test.client.konsole;

import de.chessgame.client.konsole.KonsolenAusgabe;
import de.chessgame.logic.ablauf.SpielAblauf;
import de.chessgame.logic.brett.Brett;

public class KonsolenAusgabeTest {

	public static void main(String[] args) {
		Brett testBrett = new Brett(8,8);
		KonsolenAusgabe ausgabe = new KonsolenAusgabe(new SpielAblauf(testBrett));
		testBrett.init();
		ausgabe.print();
	}

}
