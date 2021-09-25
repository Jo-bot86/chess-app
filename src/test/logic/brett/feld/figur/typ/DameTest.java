package test.logic.brett.feld.figur.typ;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import de.chessgame.logic.brett.Brett;
import static de.chessgame.logic.brett.feld.figur.Farbe.*;

import de.chessgame.logic.brett.feld.Feld;
import de.chessgame.logic.brett.feld.figur.typ.Bauer;
import de.chessgame.logic.brett.feld.figur.typ.Dame;
import de.chessgame.logic.brett.feld.figur.typ.Laeufer;
import de.chessgame.logic.brett.feld.figur.typ.Springer;
import de.chessgame.logic.brett.feld.figur.typ.Turm;

/**
 * Testet die Klasse Dame
 * @author Josef Weldemariam
 *
 */
public class DameTest {
	Brett testBrett;
	Dame testDame;

	@Before
	public void init() {
		testBrett = new Brett(8, 8);
	}
//	***********************************************************************
//	****************************Dame in Eckposition************************
//	***********************************************************************
	
//   **************************Linkes oberes Eck***************************
	/**
	 * Testet bestimmeAlleValidenFelderEcke. Die Dame Steht auf dem Feld
	 * mit Indizes (0,0) und hat keine Hindernisse
	 */
	@Test
	public void bestimmeAlleValidenFelderEcke_LinksOben_ohneHindernisse() {
		testDame = new Dame(0, 0, BLACK);
		Brett.getBrett()[0][0].setFigur(testDame);
//		Brett.print();
		assertEquals(21, testDame.bestimmeAlleValidenFelderEcke().size());
	}

	/**
	 * Testet bestimmeAlleValidenFelderEcke. Die Dame Steht auf dem Feld
	 * mit Indizes (0,0) und hat ein Hindernis der gegnerischen Farbe auf (0,7)
	 */
	@Test
	public void bestimmeAlleValidenFelderEcke_LiOb_Osten_mitHinderniss_0_7Gegner() {
		testDame = new Dame(0, 0, BLACK);
		Bauer testBauerGegner = new Bauer(0, 7, WHITE);
		Brett.getBrett()[0][0].setFigur(testDame);
		Brett.getBrett()[0][0].setFigur(testBauerGegner);
		assertEquals(21, testDame.bestimmeAlleValidenFelderEcke().size());
	}

	/**
	 * Testet bestimmeAlleValidenFelderEcke. Die Dame Steht auf dem Feld
	 * mit Indizes (0,0) und hat ein Hindernis der eigenen Farbe auf (0,7)
	 */
	@Test
	public void bestimmeAlleValidenFelderEcke_LiOb_Osten_mitHinderniss_0_7Eigen() {
		testDame = new Dame(0, 0, BLACK);
		Bauer testBauerEigen = new Bauer(0, 7, BLACK);
		Brett.getBrett()[0][0].setFigur(testDame);
		Brett.getBrett()[0][7].setFigur(testBauerEigen);
//		Brett.print();
		assertEquals(20, testDame.bestimmeAlleValidenFelderEcke().size());
	}

	/**
	 * Testet bestimmeAlleValidenFelderEcke. Die Dame Steht auf dem Feld
	 * mit Indizes (0,0) und hat ein Hindernis der gegnerischen Farbe auf (7,7)
	 */
	@Test
	public void bestimmeAlleValidenFelderEcke_LiOb_Suedost_mitHinderniss_7_7Gegner() {
		testDame = new Dame(0, 0, BLACK);
		Bauer testBauerGegner = new Bauer(7, 7, WHITE);
		Brett.getBrett()[0][0].setFigur(testDame);
		Brett.getBrett()[7][7].setFigur(testBauerGegner);
//		Brett.print();
		assertEquals(21, testDame.bestimmeAlleValidenFelderEcke().size());
	}

	/**
	 * Testet bestimmeAlleValidenFelderEcke. Die Dame Steht auf dem Feld
	 * mit Indizes (0,0) und hat ein Hindernis der eigenen Farbe auf (7,7)
	 */
	@Test
	public void bestimmeAlleValidenFelderEcke_LiOb_Suedost_mitHinderniss_7_7Eigen() {
		testDame = new Dame(0, 0, BLACK);
		Bauer testBauerEigen = new Bauer(7, 7, BLACK);
		Brett.getBrett()[0][0].setFigur(testDame);
		Brett.getBrett()[7][7].setFigur(testBauerEigen);
//		Brett.print();
		assertEquals(20, testDame.bestimmeAlleValidenFelderEcke().size());
	}

	/**
	 * Testet bestimmeAlleValidenFelderEcke. Die Dame Steht auf dem Feld
	 * mit Indizes (0,0) und hat ein Hindernis der gegnerischen Farbe auf (7,0)
	 */
	@Test
	public void bestimmeAlleValidenFelderEcke_LiOb_Sued_mitHinderniss_7_0Gegner() {
		testDame = new Dame(0, 0, BLACK);
		Bauer testBauerGegner = new Bauer(7, 0, WHITE);
		Brett.getBrett()[0][0].setFigur(testDame);
		Brett.getBrett()[7][0].setFigur(testBauerGegner);
//		Brett.print();
		assertEquals(21, testDame.bestimmeAlleValidenFelderEcke().size());
	}

	/**
	 * Testet bestimmeAlleValidenFelderEcke. Die Dame Steht auf dem Feld
	 * mit Indizes (0,0) und hat ein Hindernis der eigenen Farbe auf (7,0)
	 */
	@Test
	public void bestimmeAlleValidenFelderEcke_LiOb_Sued_mitHinderniss_7_0Eigen() {
		testDame = new Dame(0, 0, BLACK);
		Bauer testBauerEigen = new Bauer(7, 0, BLACK);
		Brett.getBrett()[0][0].setFigur(testDame);
		Brett.getBrett()[7][0].setFigur(testBauerEigen);
//		Brett.print();
		assertEquals(20, testDame.bestimmeAlleValidenFelderEcke().size());
	}

	/**
	 * Testet bestimmeAlleValidenFelderEcke. Die Dame Steht auf dem Feld
	 * mit Indizes (0,0) und hat ein Hindernis der gegnerischen Farbe auf (1,0)
	 */
	@Test
	public void bestimmeAlleValidenFelderEcke_LiOb_Osten_mitHinderniss_0_1Gegner() {
		testDame = new Dame(0, 0, BLACK);
		Bauer testBauerGegner = new Bauer(0, 1, WHITE);
		Brett.getBrett()[0][0].setFigur(testDame);
		Brett.getBrett()[0][1].setFigur(testBauerGegner);
		assertEquals(15, testDame.bestimmeAlleValidenFelderEcke().size());
	}

	/**
	 * Testet bestimmeAlleValidenFelderEcke. Die Dame Steht auf dem Feld
	 * mit Indizes (0,0) und hat ein Hindernis der eigenen Farbe auf (1,0)
	 */
	@Test
	public void bestimmeAlleValidenFelderEcke_LiOb_Osten_mitHinderniss_0_1Eigen() {
		testDame = new Dame(0, 0, BLACK);
		Bauer testBauerEigen = new Bauer(0, 1, BLACK);
		Brett.getBrett()[0][0].setFigur(testDame);
		Brett.getBrett()[0][1].setFigur(testBauerEigen);
//		Brett.print();
		assertEquals(14, testDame.bestimmeAlleValidenFelderEcke().size());
	}
	
	/**
	 * Testet bestimmeAlleValidenFelderEcke. Die Dame Steht auf dem Feld
	 * mit Indizes (0,0) und hat ein Hindernis der gegnerischen Farbe auf (1,1)
	 */
	@Test
	public void bestimmeAlleValidenFelderEcke_LiOb_Suedost_mitHinderniss_1_1Gegner() {
		testDame = new Dame(0, 0, BLACK);
		Bauer testBauerGegner = new Bauer(1, 1, WHITE);
		Brett.getBrett()[0][0].setFigur(testDame);
		Brett.getBrett()[1][1].setFigur(testBauerGegner);
//		Brett.print();
		assertEquals(15, testDame.bestimmeAlleValidenFelderEcke().size());
	}

	/**
	 * Testet bestimmeAlleValidenFelderEcke. Die Dame Steht auf dem Feld
	 * mit Indizes (0,0) und hat ein Hindernis der eigenen Farbe auf (1,1)
	 */
	@Test
	public void bestimmeAlleValidenFelderEcke_LiOb_Suedost_mitHinderniss_1_1Eigen() {
		testDame = new Dame(0, 0, BLACK);
		Bauer testBauerEigen = new Bauer(1, 1, BLACK);
		Brett.getBrett()[0][0].setFigur(testDame);
		Brett.getBrett()[1][1].setFigur(testBauerEigen);
//		Brett.print();
		assertEquals(14, testDame.bestimmeAlleValidenFelderEcke().size());
	}
	
	/**
	 * Testet bestimmeAlleValidenFelderEcke. Die Dame Steht auf dem Feld
	 * mit Indizes (0,0) und hat ein Hindernis der gegnerischen Farbe auf (1,0)
	 */
	@Test
	public void bestimmeAlleValidenFelderEcke_LiOb_Sued_mitHinderniss_1_0Gegner() {
		testDame = new Dame(0, 0, BLACK);
		Bauer testBauerGegner = new Bauer(1, 0, WHITE);
		Brett.getBrett()[0][0].setFigur(testDame);
		Brett.getBrett()[1][0].setFigur(testBauerGegner);
//		Brett.print();
		assertEquals(15, testDame.bestimmeAlleValidenFelderEcke().size());
	}

	/**
	 * Testet bestimmeAlleValidenFelderEcke. Die Dame Steht auf dem Feld
	 * mit Indizes (0,0) und hat ein Hindernis der eigenen Farbe auf (1,0)
	 */
	@Test
	public void bestimmeAlleValidenFelderEcke_LiOb_Sued_mitHinderniss_1_0Eigen() {
		testDame = new Dame(0, 0, BLACK);
		Bauer testBauerEigen = new Bauer(1, 0, BLACK);
		Brett.getBrett()[0][0].setFigur(testDame);
		Brett.getBrett()[1][0].setFigur(testBauerEigen);
//		Brett.print();
		assertEquals(14, testDame.bestimmeAlleValidenFelderEcke().size());
	}
	
	/**
	 * Testet bestimmeAlleValidenFelderEcke. Die Dame Steht auf dem Feld
	 * mit Indizes (0,0) und hat ein Hindernis der gegnerischen Farbe auf (1,0),
	 * (0,1) und (1,1)
	 */
	@Test
	public void bestimmeAlleValidenFelderEcke_LiOb_mitDreiHindernissen_Gegner() {
		testDame = new Dame(0, 0, BLACK);
		Bauer testBauerGegnerEins = new Bauer(1, 0, WHITE);
		Bauer testBauerGegnerZwei = new Bauer(0, 1, WHITE);
		Bauer testBauerGegnerDrei = new Bauer(1, 1, WHITE);
		Brett.getBrett()[0][0].setFigur(testDame);
		Brett.getBrett()[1][0].setFigur(testBauerGegnerEins);
		Brett.getBrett()[0][1].setFigur(testBauerGegnerZwei);
		Brett.getBrett()[1][1].setFigur(testBauerGegnerDrei);
//		Brett.print();
		assertEquals(3, testDame.bestimmeAlleValidenFelderEcke().size());
	}
	
	/**
	 * Testet bestimmeAlleValidenFelderEcke. Die Dame Steht auf dem Feld
	 * mit Indizes (0,0) und hat ein Hindernis der eigenen Farbe auf (1,0),
	 * (0,1) und (1,1)
	 */
	@Test
	public void bestimmeAlleValidenFelderEcke_LiOb_mitDreiHindernissen_Eigen() {
		testDame = new Dame(0, 0, BLACK);
		Bauer testBauerGegnerEins = new Bauer(1, 0, BLACK);
		Bauer testBauerGegnerZwei = new Bauer(0, 1, BLACK);
		Bauer testBauerGegnerDrei = new Bauer(1, 1, BLACK);
		Brett.getBrett()[0][0].setFigur(testDame);
		Brett.getBrett()[1][0].setFigur(testBauerGegnerEins);
		Brett.getBrett()[0][1].setFigur(testBauerGegnerZwei);
		Brett.getBrett()[1][1].setFigur(testBauerGegnerDrei);
//		Brett.print();
		assertEquals(0, testDame.bestimmeAlleValidenFelderEcke().size());
	}

//  **************************Rechtes oberes Eck***************************
	/**
	 * Testet bestimmeAlleValidenFelderEcke. Die Dame Steht auf dem Feld
	 * mit Indizes (0,7) und hat keine Hindernisse
	 */
	@Test
	public void bestimmeAlleValidenFelderEcke_RechtsOben_ohneHindernisse() {
		testDame = new Dame(0, 7, BLACK);
		Brett.getBrett()[0][7].setFigur(testDame);
//		Brett.print();
		assertEquals(21, testDame.bestimmeAlleValidenFelderEcke().size());
	}

	/**
	 * Testet bestimmeAlleValidenFelderEcke. Die Dame Steht auf dem Feld
	 * mit Indizes (0,7) und hat ein Hindernis der gegnerischen Farbe auf (0,0)
	 */
	@Test
	public void bestimmeAlleValidenFelderEcke_ReOb_Westen_mitHinderniss_0_0Gegner() {
		testDame = new Dame(0, 7, BLACK);
		Bauer testBauerGegner = new Bauer(0, 0, WHITE);
		Brett.getBrett()[0][7].setFigur(testDame);
		Brett.getBrett()[0][0].setFigur(testBauerGegner);
		assertEquals(21, testDame.bestimmeAlleValidenFelderEcke().size());
	}

	/**
	 * Testet bestimmeAlleValidenFelderEcke. Die Dame Steht auf dem Feld
	 * mit Indizes (0,7) und hat ein Hindernis der eigenen Farbe auf (0,7)
	 */
	@Test
	public void bestimmeAlleValidenFelderEcke_ReOb_Westen_mitHinderniss_0_0Eigen() {
		testDame = new Dame(0, 7, BLACK);
		Bauer testBauerEigen = new Bauer(0, 0, BLACK);
		Brett.getBrett()[0][7].setFigur(testDame);
		Brett.getBrett()[0][0].setFigur(testBauerEigen);
//		Brett.print();
		assertEquals(20, testDame.bestimmeAlleValidenFelderEcke().size());
	}
	
	/**
	 * Testet bestimmeAlleValidenFelderEcke. Die Dame Steht auf dem Feld
	 * mit Indizes (0,7) und hat ein Hindernis der gegnerischen Farbe auf (7,0)
	 */
	@Test
	public void bestimmeAlleValidenFelderEcke_ReOb_SuedWest_mitHinderniss_7_0Gegner() {
		testDame = new Dame(0, 7, BLACK);
		Bauer testBauerGegner = new Bauer(7, 0, WHITE);
		Brett.getBrett()[0][7].setFigur(testDame);
		Brett.getBrett()[7][0].setFigur(testBauerGegner);
		assertEquals(21, testDame.bestimmeAlleValidenFelderEcke().size());
	}
	
	/**
	 * Testet bestimmeAlleValidenFelderEcke. Die Dame Steht auf dem Feld
	 * mit Indizes (0,7) und hat ein Hindernis der eigenen Farbe auf (7,0)
	 */
	@Test
	public void bestimmeAlleValidenFelderEcke_ReOb_SuedWest_mitHinderniss_7_0Eigen() {
		testDame = new Dame(0, 7, BLACK);
		Bauer testBauerGegner = new Bauer(7, 0, BLACK);
		Brett.getBrett()[0][7].setFigur(testDame);
		Brett.getBrett()[7][0].setFigur(testBauerGegner);
		assertEquals(20, testDame.bestimmeAlleValidenFelderEcke().size());
	}
	
	/**
	 * Testet bestimmeAlleValidenFelderEcke. Die Dame Steht auf dem Feld
	 * mit Indizes (0,7) und hat ein Hindernis der gegnerischen Farbe auf (7,0)
	 */
	@Test
	public void bestimmeAlleValidenFelderEcke_ReOb_Sueden_mitHinderniss_7_7Gegner() {
		testDame = new Dame(0, 7, BLACK);
		Bauer testBauerGegner = new Bauer(7, 7, WHITE);
		Brett.getBrett()[0][7].setFigur(testDame);
		Brett.getBrett()[7][7].setFigur(testBauerGegner);
		assertEquals(21, testDame.bestimmeAlleValidenFelderEcke().size());
	}

	/**
	 * Testet bestimmeAlleValidenFelderEcke. Die Dame Steht auf dem Feld
	 * mit Indizes (0,7) und hat ein Hindernis der gegnerischen Farbe auf (0,6)
	 */
	@Test
	public void bestimmeAlleValidenFelderEcke_ReOb_Westen_mitHinderniss_0_6Gegner() {
		testDame = new Dame(0, 7, BLACK);
		Bauer testBauerGegner = new Bauer(0, 6, WHITE);
		Brett.getBrett()[0][7].setFigur(testDame);
		Brett.getBrett()[0][6].setFigur(testBauerGegner);
		assertEquals(15, testDame.bestimmeAlleValidenFelderEcke().size());
	}

	/**
	 * Testet bestimmeAlleValidenFelderEcke. Die Dame Steht auf dem Feld
	 * mit Indizes (0,0) und hat ein Hindernis der eigenen Farbe auf (0,7)
	 */
	@Test
	public void bestimmeAlleValidenFelderEcke_ReOb_Westen_mitHinderniss_0_6Eigen() {
		testDame = new Dame(0, 7, BLACK);
		Bauer testBauerEigen = new Bauer(0, 6, BLACK);
		Brett.getBrett()[0][7].setFigur(testDame);
		Brett.getBrett()[0][6].setFigur(testBauerEigen);
//		Brett.print();
		assertEquals(14, testDame.bestimmeAlleValidenFelderEcke().size());
	}
	
	/**
	 * Testet bestimmeAlleValidenFelderEcke. Die Dame Steht auf dem Feld
	 * mit Indizes (0,0) und hat ein Hindernis der gegnerischen Farbe auf (1,0),
	 * (0,1) und (1,1)
	 */
	@Test
	public void bestimmeAlleValidenFelderEcke_ReOb_mitDreiHindernissen_Gegner() {
		testDame = new Dame(0, 7, BLACK);
		Bauer testBauerGegnerEins = new Bauer(0, 6, WHITE);
		Bauer testBauerGegnerZwei = new Bauer(1, 6, WHITE);
		Bauer testBauerGegnerDrei = new Bauer(2, 7, WHITE);
		Brett.getBrett()[0][7].setFigur(testDame);
		Brett.getBrett()[0][6].setFigur(testBauerGegnerEins);
		Brett.getBrett()[1][6].setFigur(testBauerGegnerZwei);
		Brett.getBrett()[1][7].setFigur(testBauerGegnerDrei);
//		Brett.print();
		assertEquals(3, testDame.bestimmeAlleValidenFelderEcke().size());
	}
	
	/**
	 * Testet bestimmeAlleValidenFelderEcke. Die Dame Steht auf dem Feld
	 * mit Indizes (0,0) und hat ein Hindernis der eigenen Farbe auf (1,0),
	 * (0,1) und (1,1)
	 */
	@Test
	public void bestimmeAlleValidenFelderEcke_ReOb_mitDreiHindernissen_Eigen() {
		testDame = new Dame(0, 7, BLACK);
		Bauer testBauerGegnerEins = new Bauer(0, 6, BLACK);
		Bauer testBauerGegnerZwei = new Bauer(1, 6, BLACK);
		Bauer testBauerGegnerDrei = new Bauer(1, 7, BLACK);
		Brett.getBrett()[0][7].setFigur(testDame);
		Brett.getBrett()[0][6].setFigur(testBauerGegnerEins);
		Brett.getBrett()[1][6].setFigur(testBauerGegnerZwei);
		Brett.getBrett()[1][7].setFigur(testBauerGegnerDrei);
//		Brett.print();
		assertEquals(0, testDame.bestimmeAlleValidenFelderEcke().size());
	}
//	******************************Linkes unteres Eck**********************************************
	
	/**
	 * Testet bestimmeAlleValidenFelderEcke. Die Dame Steht auf dem Feld
	 * mit Indizes (7,0) und hat keine Hindernisse
	 */
	@Test
	public void bestimmeAlleValidenFelderEcke_LinksUnten_ohneHindernisse() {
		testDame = new Dame(7, 0, BLACK);
		Brett.getBrett()[7][0].setFigur(testDame);
//		Brett.print();
		assertEquals(21, testDame.bestimmeAlleValidenFelderEcke().size());
	}

	/**
	 * Testet bestimmeAlleValidenFelderEcke. Die Dame Steht auf dem Feld
	 * mit Indizes (7,0) und hat ein Hindernis der gegnerischen Farbe auf (0,0)
	 */
	@Test
	public void bestimmeAlleValidenFelderEcke_LiUn_Norden_mitHinderniss_0_0Gegner() {
		testDame = new Dame(7, 0, BLACK);
		Bauer testBauerGegner = new Bauer(0, 0, WHITE);
		Brett.getBrett()[7][0].setFigur(testDame);
		Brett.getBrett()[0][0].setFigur(testBauerGegner);
		assertEquals(21, testDame.bestimmeAlleValidenFelderEcke().size());
	}

	/**
	 * Testet bestimmeAlleValidenFelderEcke. Die Dame Steht auf dem Feld
	 * mit Indizes (7,0) und hat ein Hindernis der eigenen Farbe auf (0,0)
	 */
	@Test
	public void bestimmeAlleValidenFelderEcke_LiUn_Norden_mitHinderniss_0_0Eigen() {
		testDame = new Dame(7, 0, BLACK);
		Bauer testBauerEigen = new Bauer(0, 0, BLACK);
		Brett.getBrett()[7][0].setFigur(testDame);
		Brett.getBrett()[0][0].setFigur(testBauerEigen);
//		Brett.print();
		assertEquals(20, testDame.bestimmeAlleValidenFelderEcke().size());
	}
	
	/**
	 * Testet bestimmeAlleValidenFelderEcke. Die Dame Steht auf dem Feld
	 * mit Indizes (7,0) und hat ein Hindernis der gegnerischen Farbe auf (0,7)
	 */
	@Test
	public void bestimmeAlleValidenFelderEcke_LiUn_Nordost_mitHinderniss_0_7Gegner() {
		testDame = new Dame(7, 0, BLACK);
		Bauer testBauerGegner = new Bauer(0, 7, WHITE);
		Brett.getBrett()[7][0].setFigur(testDame);
		Brett.getBrett()[0][7].setFigur(testBauerGegner);
//		Brett.print();
		assertEquals(21, testDame.bestimmeAlleValidenFelderEcke().size());
	}

	/**
	 * Testet bestimmeAlleValidenFelderEcke. Die Dame Steht auf dem Feld
	 * mit Indizes (7,0) und hat ein Hindernis der eigenen Farbe auf (7,7)
	 */
	@Test
	public void bestimmeAlleValidenFelderEcke_LiUn_Nordost_mitHinderniss_7_7Eigen() {
		testDame = new Dame(7, 0, BLACK);
		Bauer testBauerEigen = new Bauer(0, 7, BLACK);
		Brett.getBrett()[7][0].setFigur(testDame);
		Brett.getBrett()[0][7].setFigur(testBauerEigen);
//		Brett.print();
		assertEquals(20, testDame.bestimmeAlleValidenFelderEcke().size());
	}

	/**
	 * Testet bestimmeAlleValidenFelderEcke. Die Dame Steht auf dem Feld
	 * mit Indizes (7,0) und hat ein Hindernis der gegnerischen Farbe auf (7,7)
	 */
	@Test
	public void bestimmeAlleValidenFelderEcke_LiUn_Sued_mitHinderniss_7_7Gegner() {
		testDame = new Dame(7, 0, BLACK);
		Bauer testBauerGegner = new Bauer(7, 7, WHITE);
		Brett.getBrett()[7][0].setFigur(testDame);
		Brett.getBrett()[7][7].setFigur(testBauerGegner);
//		Brett.print();
		assertEquals(21, testDame.bestimmeAlleValidenFelderEcke().size());
	}

	/**
	 * Testet bestimmeAlleValidenFelderEcke. Die Dame Steht auf dem Feld
	 * mit Indizes (7,0) und hat ein Hindernis der eigenen Farbe auf (7,7)
	 */
	@Test
	public void bestimmeAlleValidenFelderEcke_LiUn_Osten_mitHinderniss_7_7Eigen() {
		testDame = new Dame(7, 0, BLACK);
		Bauer testBauerEigen = new Bauer(7, 7, BLACK);
		Brett.getBrett()[7][0].setFigur(testDame);
		Brett.getBrett()[7][7].setFigur(testBauerEigen);
//		Brett.print();
		assertEquals(20, testDame.bestimmeAlleValidenFelderEcke().size());
	}
	
//	************************ Rechtes unteres Eck*********************************
	
	/**
	 * Testet bestimmeAlleValidenFelderEcke. Die Dame Steht auf dem Feld
	 * mit Indizes (7,7) und hat keine Hindernisse
	 */
	@Test
	public void bestimmeAlleValidenFelderEcke_RechtsUnten_ohneHindernisse() {
		testDame = new Dame(7, 7, BLACK);
		Brett.getBrett()[7][7].setFigur(testDame);
//		Brett.print();
		assertEquals(21, testDame.bestimmeAlleValidenFelderEcke().size());
	}

	/**
	 * Testet bestimmeAlleValidenFelderEcke. Die Dame Steht auf dem Feld
	 * mit Indizes (7,7) und hat ein Hindernis der gegnerischen Farbe auf (0,7)
	 */
	@Test
	public void bestimmeAlleValidenFelderEcke_ReUn_Norden_mitHinderniss_0_7Gegner() {
		testDame = new Dame(7, 7, BLACK);
		Bauer testBauerGegner = new Bauer(0, 7, WHITE);
		Brett.getBrett()[7][7].setFigur(testDame);
		Brett.getBrett()[0][7].setFigur(testBauerGegner);
		assertEquals(21, testDame.bestimmeAlleValidenFelderEcke().size());
	}

	/**
	 * Testet bestimmeAlleValidenFelderEcke. Die Dame Steht auf dem Feld
	 * mit Indizes (7,7) und hat ein Hindernis der eigenen Farbe auf (0,7)
	 */
	@Test
	public void bestimmeAlleValidenFelderEcke_ReUn_Norden_mitHinderniss_0_7Eigen() {
		testDame = new Dame(7, 7, BLACK);
		Bauer testBauerEigen = new Bauer(0, 7, BLACK);
		Brett.getBrett()[7][7].setFigur(testDame);
		Brett.getBrett()[0][7].setFigur(testBauerEigen);
//		Brett.print();
		assertEquals(20, testDame.bestimmeAlleValidenFelderEcke().size());
	}
	
	/**
	 * Testet bestimmeAlleValidenFelderEcke. Die Dame Steht auf dem Feld
	 * mit Indizes (7,7) und hat ein Hindernis der gegnerischen Farbe auf (0,0)
	 */
	@Test
	public void bestimmeAlleValidenFelderEcke_ReUn_Nordwest_mitHinderniss_0_0Gegner() {
		testDame = new Dame(7, 7, BLACK);
		Bauer testBauerGegner = new Bauer(0, 0, WHITE);
		Brett.getBrett()[7][7].setFigur(testDame);
		Brett.getBrett()[0][0].setFigur(testBauerGegner);
		assertEquals(21, testDame.bestimmeAlleValidenFelderEcke().size());
	}

	/**
	 * Testet bestimmeAlleValidenFelderEcke. Die Dame Steht auf dem Feld
	 * mit Indizes (7,7) und hat ein Hindernis der eigenen Farbe auf (0,0)
	 */
	@Test
	public void bestimmeAlleValidenFelderEcke_ReUn_Nordwest_mitHinderniss_0_0Eigen() {
		testDame = new Dame(7, 7, BLACK);
		Bauer testBauerEigen = new Bauer(0, 0, BLACK);
		Brett.getBrett()[7][7].setFigur(testDame);
		Brett.getBrett()[0][0].setFigur(testBauerEigen);
//		Brett.print();
		assertEquals(20, testDame.bestimmeAlleValidenFelderEcke().size());
	}
	
	/**
	 * Testet bestimmeAlleValidenFelderEcke. Die Dame Steht auf dem Feld
	 * mit Indizes (7,7) und hat ein Hindernis der gegnerischen Farbe auf (7,0)
	 */
	@Test
	public void bestimmeAlleValidenFelderEcke_ReUn_Westen_mitHinderniss_7_0Gegner() {
		testDame = new Dame(7, 7, BLACK);
		Bauer testBauerGegner = new Bauer(7, 0, WHITE);
		Brett.getBrett()[7][7].setFigur(testDame);
		Brett.getBrett()[7][0].setFigur(testBauerGegner);
		assertEquals(21, testDame.bestimmeAlleValidenFelderEcke().size());
	}

	/**
	 * Testet bestimmeAlleValidenFelderEcke. Die Dame Steht auf dem Feld
	 * mit Indizes (7,7) und hat ein Hindernis der eigenen Farbe auf (0,7)
	 */
	@Test
	public void bestimmeAlleValidenFelderEcke_ReUn_Westen_mitHinderniss_7_0Eigen() {
		testDame = new Dame(7, 7, BLACK);
		Bauer testBauerEigen = new Bauer(7, 0, BLACK);
		Brett.getBrett()[7][7].setFigur(testDame);
		Brett.getBrett()[7][0].setFigur(testBauerEigen);
//		Brett.print();
		assertEquals(20, testDame.bestimmeAlleValidenFelderEcke().size());
	}
	
//	***********************************************************************
//	****************************Dame in Randposition************************
//	***********************************************************************
	
//	*****************************Linker Rand*********************************
	
	@Test
	public void bestimmeAlleValidenFelderRand_LinkerRand_2_0_OhneHinderniss() {
		testDame = new Dame(2, 0, BLACK);
		Brett.getBrett()[2][0].setFigur(testDame);
		assertEquals(21, testDame.bestimmeAlleValidenFelderRand().size());
	}
	
	/**
	 * Die Dame hat die Indizes (3,7). Ein Bauer der eigenen Farbe steth bei
	 * 
	 */
	@Test
	public void bestimmeAlleValidenFelderRandRandom() {
		testDame = new Dame(3, 7, BLACK);
		Bauer gegnerBauer = new Bauer(5,5,WHITE);
		Laeufer gegnerLaeufer = new Laeufer(2,6, WHITE);
		Turm gegnerTurm = new Turm(0,7, WHITE);
		Bauer eigenerBauer = new Bauer(3,3, BLACK);
		Springer eigenerSpringer = new Springer(6,7,BLACK);
		Brett.getBrett()[3][7].setFigur(testDame);
		Brett.getBrett()[5][5].setFigur(gegnerBauer);
		Brett.getBrett()[2][6].setFigur(gegnerLaeufer);
		Brett.getBrett()[0][7].setFigur(gegnerTurm);
		Brett.getBrett()[3][3].setFigur(eigenerBauer);
		Brett.getBrett()[6][7].setFigur(eigenerSpringer);
//		Brett.print();
		assertEquals(11, testDame.bestimmeAlleValidenFelderRand().size());

	}
	
//	***********************************************************************
//	****************************Dame in innen Position************************
//	***********************************************************************
	
	/**
	 * Die Dame hat die Indizes (4,2). Ein Bauer der eigenen Farbe steth bei
	 * 
	 */
	@Test
	public void bestimmeAlleValidenFelder_From_4_2_ohneHindernis() {
		testDame = new Dame(4, 2, BLACK);
		Brett.getBrett()[4][2].setFigur(testDame);
//		Brett.print();
		assertEquals(25, testDame.bestimmeAlleValidenFelder().size());
	}
}

