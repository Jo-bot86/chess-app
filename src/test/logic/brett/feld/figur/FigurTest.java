package test.logic.brett.feld.figur;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import de.chessgame.logic.brett.Brett;
import static de.chessgame.logic.brett.feld.figur.Farbe.*;
import de.chessgame.logic.brett.feld.figur.typ.Bauer;
import de.chessgame.logic.brett.feld.figur.typ.Dame;

/**
 * Testet die Klasse Figur. Da diese abstrakt ist, wird diese anhand
 * einer Instanz einer Kindsklasse getestet
 * @author Josef Weldemariam
 *
 */
public class FigurTest {

	Brett testBrett;
	
	@Before
	public void init() {
		testBrett = new Brett(8,8);
	}
	
	@Test
	public void moveTo_Bauer_From_1_1_To_2_1() {
		Bauer testBauer = new Bauer(1,1, BLACK);
		Brett.getBrett()[1][1].setFigur(testBauer);
		testBauer.moveTo(Brett.getBrett()[2][1]);
		assertEquals(2, testBauer.getZeilenIndex());
		assertEquals(null, Brett.getBrett()[1][1].getFigur());
		assertEquals(testBauer, Brett.getBrett()[2][1].getFigur());
	}
	
	@Test
	public void moveTo_Dame_From_4_2_To_1_5() {
		Dame testDame = new Dame(4,2, WHITE);
		Brett.getBrett()[4][2].setFigur(testDame);
		testDame.moveTo(Brett.getBrett()[1][5]);
		assertEquals(1, testDame.getZeilenIndex());
		assertEquals(5, testDame.getSpaltenIndex());
		assertEquals(null, Brett.getBrett()[4][2].getFigur());
		assertEquals(testDame, Brett.getBrett()[1][5].getFigur());
	}
}
