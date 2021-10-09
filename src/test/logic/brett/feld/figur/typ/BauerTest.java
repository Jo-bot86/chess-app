package test.logic.brett.feld.figur.typ;

import static de.chessgame.logic.brett.feld.figur.Farbe.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import de.chessgame.logic.brett.Brett;
import de.chessgame.logic.brett.feld.Feld;
import de.chessgame.logic.brett.feld.figur.typ.Bauer;

/**
 * Testet die Klasse Bauer
 * @author Josef Weldemariam
 *
 */
public class BauerTest {
	Brett brett;
	
	@Before
	public void init() {
		brett = new Brett(8,8);
	}

	/**
	 * Testet isRandFigur der Klasse Figur :(
	 */
	@Test
	public void isRandBauer_LinksRechtsMitte() {
		Bauer bauerLinkerRand = new Bauer(2, 0, BLACK);
		Bauer bauerRechterRand = new Bauer(3, 7, WHITE);
		Bauer bauerUntererRand = new Bauer(7, 6, BLACK);
		assertEquals(1, bauerLinkerRand.isRandFigur());
		assertEquals(2, bauerRechterRand.isRandFigur());
		assertEquals(4, bauerUntererRand.isRandFigur());
	}

	/**
	 * Testet alleValidenFelderBlack für ein Bauer-Objekt mit Indizes (1,2)
	 * und einem Hindernis-Bauer-Objekt bei (2,2)
	 */
	@Test
	public void alleValidenFelderBlack_Keine() {
		Bauer amZugBauer = new Bauer(1, 2, BLACK);
		Bauer hindernisBauer = new Bauer(2, 2, WHITE);
		Brett.getBrett()[1][2].setFigur(amZugBauer);
		Brett.getBrett()[2][2].setFigur(hindernisBauer);
		assertEquals(0, amZugBauer.alleValidenFelderBlack().size());
//		Brett.print();
	}

	@Test
	public void alleValidenFelderBlack_EinsDurchSchlagen() {
		// Aufstellung
		Bauer amZugBauer = new Bauer(1, 2, BLACK);
		Bauer hindernisBauer = new Bauer(2, 2, WHITE);
		Bauer zuSchlagenderBauer = new Bauer(2, 3, WHITE);
		Brett.getBrett()[1][2].setFigur(amZugBauer);
		Brett.getBrett()[2][2].setFigur(hindernisBauer);
		Brett.getBrett()[2][3].setFigur(zuSchlagenderBauer);
//		Brett.print();

		ArrayList<Feld> alleFelder = new ArrayList<Feld>();
		Feld potFeld = new Feld(2, 3);
		potFeld.setFigur(zuSchlagenderBauer);
		alleFelder.add(potFeld);

		assertEquals(alleFelder.get(0).getZeilenIndex(), amZugBauer.alleValidenFelderBlack().get(0).getZeilenIndex());

	}
	
	@Test
	public void isUmwandelbar() {
		Bauer testBauer = new Bauer(6,4, WHITE);
		Brett.getBrett()[6][4].setFigur(testBauer);
		testBauer.moveTo2(Brett.getBrett()[0][4]);
		testBauer.bestimmeAlleValidenFelder();
		Brett.print();
		assertEquals(false, testBauer.isUmwandelbar());
	}

}
