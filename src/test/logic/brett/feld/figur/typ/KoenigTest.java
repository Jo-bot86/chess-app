package test.logic.brett.feld.figur.typ;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import de.chessgame.logic.brett.Brett;
import static de.chessgame.logic.brett.feld.figur.Farbe.*;
import de.chessgame.logic.brett.feld.figur.typ.Koenig;

/**
 * Testet die Klasse Koenig
 * @author Josef Weldemariam
 *
 */
public class KoenigTest {
	Brett testBrett;
	
	@Before
	public void init() {
		testBrett = new Brett(8,8);
	}
	
	@Test
	public void bestimmeAlleValidenFelderEcke_0_0_ohneHindernis() {
		Koenig testKoenig = new Koenig(0,0, BLACK);
		Brett.getBrett()[0][0].setFigur(testKoenig);
		assertEquals(3, testKoenig.bestimmeAlleValidenFelderEcke().size());
//		Brett.print();
	}
	
	@Test
	public void bestimmeAlleValidenFelderEcke_0_7_ohneHindernis() {
		Koenig testKoenig = new Koenig(0,7, BLACK);
		Brett.getBrett()[0][7].setFigur(testKoenig);
		assertEquals(3, testKoenig.bestimmeAlleValidenFelderEcke().size());
//		Brett.print();
	}
	
	@Test
	public void bestimmeAlleValidenFelderEcke_7_0_ohneHindernis() {
		Koenig testKoenig = new Koenig(7,0, BLACK);
		Brett.getBrett()[7][0].setFigur(testKoenig);
		assertEquals(3, testKoenig.bestimmeAlleValidenFelderEcke().size());
//		Brett.print();
	}
	
	@Test
	public void bestimmeAlleValidenFelderEcke_7_7_ohneHindernis() {
		Koenig testKoenig = new Koenig(7,7, BLACK);
		Brett.getBrett()[7][7].setFigur(testKoenig);
		assertEquals(3, testKoenig.bestimmeAlleValidenFelderEcke().size());
//		Brett.print();
	}
}
