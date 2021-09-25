package test.logic.brett;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import de.chessgame.logic.brett.Brett;
import de.chessgame.logic.brett.feld.Feld;
import de.chessgame.logic.brett.feld.figur.Farbe;

public class BrettTest {
	 
	
	@Test
	public void init_FigurTest() {
		Brett testBrett = new Brett(8,8);
		testBrett.init();
		Brett.print();
		Feld aktuellesFeld = Brett.getBrett()[0][0];
		assertNotNull(testBrett);
		assertNotNull(aktuellesFeld.getFigur());
		assertEquals("T", aktuellesFeld.toString());
		assertEquals(0, aktuellesFeld.getZeilenIndex());
		assertEquals(0, aktuellesFeld.getSpaltenIndex());
		assertEquals(Farbe.BLACK, aktuellesFeld.getFigur().getFarbe());
	}
}
