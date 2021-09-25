package test;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import de.chessgame.logic.ablauf.SpielAblauf;
import de.chessgame.logic.brett.Brett;
import de.chessgame.logic.brett.feld.Feld;
import de.chessgame.logic.brett.feld.figur.typ.Springer;
import de.chessgame.persistence.Leser;

/**
 * Diese Klasse testet verschiedene Situationen in denen sich das Programm nicht
 * verhält wie erwartet
 * 
 * @author Josef Weldemariam
 *
 */
public class AllgemeinTest {
	SpielAblauf ablauf;

	@Before
	public void init() {
		ablauf = new SpielAblauf(new Brett(8, 8));
	}

	@Test
	public void springerValideZuege() {
		File quelle = new File("resources/spielstaende/gespeichertesBrett");
		Leser leser = new Leser();
		SpielAblauf gelesenerAblauf = leser.leseSpielAblauf(quelle);
		Feld[][] gelesenerBrettInhalt = gelesenerAblauf.getBrettInhalt();
		Brett.setBrett(gelesenerBrettInhalt);
		ablauf.setAktuelleFarbeAmZug(gelesenerAblauf.getAktuelleFarbeAmZug());

		Springer springer = (Springer) Brett.getBrett()[2][2].getFigur();
		for (int i = 0; i < springer.getMoeglicheZielFelder().size(); i++) {
			System.out.println(springer.getMoeglicheZielFelder().get(i).getZeilenIndex() + " "
					+ springer.getMoeglicheZielFelder().get(i).getSpaltenIndex());
		}
		Brett.print();
	}
}
