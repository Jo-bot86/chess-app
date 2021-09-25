package test.logic.brett.feld.figur.typ;

import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.chessgame.logic.brett.Brett;
import de.chessgame.logic.brett.feld.Feld;
import de.chessgame.logic.brett.feld.figur.typ.*;
import static de.chessgame.logic.brett.feld.figur.Farbe.*;

public class SpringerTest {
	Brett brett;
	
	 @Before
	 public void init() {
		 brett = new Brett(8,8);
	 }
	
//	 Testet bestimmeAlleValidenFelderEcke() fuer
//	 das linke obere Eck-Feld. 
	@Test
	public void bestimmeAlleValidenFelderEcke_0_0_ohneHindernis() {	
		Springer springer = new Springer(0, 0, BLACK);
		Brett.getBrett()[0][0].setFigur(springer);
		assertEquals(2, springer.bestimmeAlleValidenFelderEcke().size());
//		brett.print();
	}

	@Test
	public void bestimmeAlleValidenFelderEcke_0_0_mitHindernis_1_2() {
		Springer springer = new Springer(0, 0, BLACK);
		Bauer hindernisBauer = new Bauer(1, 2, BLACK);
		Brett.getBrett()[0][0].setFigur(springer);
		Brett.getBrett()[1][2].setFigur(hindernisBauer);
		assertEquals(1, springer.bestimmeAlleValidenFelderEcke().size());
//		brett.print();
	}

	@Test
	public void bestimmeAlleValidenFelderEcke_0_0_mitHindernis_2_1() {
		Springer springer = new Springer(0, 0, BLACK);
		Bauer hindernisBauer = new Bauer(2, 1, BLACK);
		Brett.getBrett()[0][0].setFigur(springer);
		Brett.getBrett()[2][1].setFigur(hindernisBauer);
		assertEquals(1, springer.bestimmeAlleValidenFelderEcke().size());
//		brett.print();
	}

	@Test
	public void bestimmeAlleValidenFelderEcke_0_0_mitHindernis_1_2_und_2_1() {
		Springer springer = new Springer(0, 0, BLACK);
		Bauer hindernisBauer1 = new Bauer(1, 2, BLACK);
		Bauer hindernisBauer2 = new Bauer(2, 1, BLACK);
		Brett.getBrett()[0][0].setFigur(springer);
		Brett.getBrett()[1][2].setFigur(hindernisBauer1);
		Brett.getBrett()[2][1].setFigur(hindernisBauer2);
		assertEquals(0, springer.bestimmeAlleValidenFelderEcke().size());
//		brett.print();
	}

	@Test
	public void bestimmeAlleValidenFelderEcke_0_0_mitGegner_2_1() {
		
		Springer springer = new Springer(0, 0, BLACK);
		Bauer gegnerBauer = new Bauer(2, 1, WHITE);
		Brett.getBrett()[0][0].setFigur(springer);
		Brett.getBrett()[2][1].setFigur(gegnerBauer);
		assertEquals(2, springer.bestimmeAlleValidenFelderEcke().size());
//		brett.print();
	}

	@Test
	public void bestimmeAlleValidenFelderEcke_0_0_mitGegner_1_2() {	
		Springer springer = new Springer(0, 0, BLACK);
		Bauer gegnerBauer = new Bauer(1, 2, WHITE);
		Brett.getBrett()[0][0].setFigur(springer);
		Brett.getBrett()[1][2].setFigur(gegnerBauer);
		assertEquals(2, springer.bestimmeAlleValidenFelderEcke().size());
//		brett.print();
	}

	@Test
	public void bestimmeAlleValidenFelderEcke_0_0_mitGegner_1_2_und_2_1() {	
		Springer springer = new Springer(0, 0, BLACK);
		Bauer gegnerBauer1 = new Bauer(1, 2, WHITE);
		Bauer gegnerBauer2 = new Bauer(2, 1, WHITE);
		Brett.getBrett()[0][0].setFigur(springer);
		Brett.getBrett()[1][2].setFigur(gegnerBauer1);
		Brett.getBrett()[2][1].setFigur(gegnerBauer2);
		assertEquals(2, springer.bestimmeAlleValidenFelderEcke().size());
//		brett.print();
	}

//	***************************************************************************
//	 Testet bestimmeAlleValidenFelderEcke() fuer
//	 das rechte obere Eck-Feld. 
	@Test
	public void bestimmeAlleValidenFelderEcke_0_7_ohneHindernis() {
		Springer springer = new Springer(0, 7, BLACK);
		Brett.getBrett()[0][7].setFigur(springer);
		assertEquals(2, springer.bestimmeAlleValidenFelderEcke().size());
//		Brett.print();
	}

//	***************************************************************************
//	 Testet bestimmeAlleValidenFelderEcke() fuer
//	 das linke untere Eck-Feld. 
	@Test
	public void bestimmeAlleValidenFelderEcke_7_0_ohneHindernis() {	
		Springer springer = new Springer(7, 0, BLACK);
		Brett.getBrett()[7][0].setFigur(springer);
		assertEquals(2, springer.bestimmeAlleValidenFelderEcke().size());
//		brett.print();
	}

//	***************************************************************************
//	 Testet bestimmeAlleValidenFelderEcke() fuer
//	 das rechte untere Eck-Feld.
	@Test
	public void bestimmeAlleValidenFelderEcke_7_7_ohneHindernis() {	
		Springer springer = new Springer(7, 7, BLACK);
		Brett.getBrett()[7][7].setFigur(springer);
		assertEquals(2, springer.bestimmeAlleValidenFelderEcke().size());
//		brett.print();
	}

//	*************************************************************************
//	*************************************************************************

//	Testet bestimmeAlleValidenFelderDreier() 
	@Test
	public void bestimmeAlleValidenFelderDreier_0_1_ohneHindernis() {
		Springer springer = new Springer(0, 1, BLACK);
		Brett.getBrett()[0][1].setFigur(springer);
		assertEquals(3, springer.bestimmeAlleValidenFelderDreier().size());
	}

//	**************************************************************************
	@Test
	public void bestimmeAlleValidenFelderDreier_0_6_ohneHindernis() {
		Springer springer = new Springer(0, 6, BLACK);
		Brett.getBrett()[0][6].setFigur(springer);
		assertEquals(3, springer.bestimmeAlleValidenFelderDreier().size());
	}

//	**************************************************************************
	@Test
	public void bestimmeAlleValidenFelderDreier_1_0_ohneHindernis() {
		Springer springer = new Springer(1, 0, BLACK);
		Brett.getBrett()[1][0].setFigur(springer);
		assertEquals(3, springer.bestimmeAlleValidenFelderDreier().size());
	}

//	**************************************************************************
	@Test
	public void bestimmeAlleValidenFelderDreier_1_7_ohneHindernis() {
		Springer springer = new Springer(1, 7, BLACK);
		Brett.getBrett()[1][7].setFigur(springer);
		assertEquals(3, springer.bestimmeAlleValidenFelderDreier().size());
	}
//	**************************************************************************
	@Test
	public void bestimmeAlleValidenFelderDreier_6_0_ohneHindernis() {
		Springer springer = new Springer(6, 0, BLACK);
		Brett.getBrett()[6][0].setFigur(springer);
		assertEquals(3, springer.bestimmeAlleValidenFelderDreier().size());
	}
//	**************************************************************************
	@Test
	public void bestimmeAlleValidenFelderDreier_6_7_ohneHindernis() {
		Springer springer = new Springer(6, 7, BLACK);
		Brett.getBrett()[6][7].setFigur(springer);
		assertEquals(3, springer.bestimmeAlleValidenFelderDreier().size());
	}
//	**************************************************************************
	@Test
	public void bestimmeAlleValidenFelderDreier_7_1_ohneHindernis() {
		
		Springer springer = new Springer(7, 1, BLACK);
		Brett.getBrett()[7][1].setFigur(springer);
		assertEquals(3, springer.bestimmeAlleValidenFelderDreier().size());
	}
//	**************************************************************************
	@Test
	public void bestimmeAlleValidenFelderDreier_7_6_ohneHindernis() {
		Springer springer = new Springer(7, 6, BLACK);
		Brett.getBrett()[7][6].setFigur(springer);
		assertEquals(3, springer.bestimmeAlleValidenFelderDreier().size());
	}

//	******************************************************************************
//	******************************************************************************
//	Testet bestimmeAlleValidenFelderVierer()
//	oberer Rand anfang
	@Test
	public void bestimmeAlleValidenFelderVierer_0_2_ohneHindernis() {
		Springer springer = new Springer(0, 2, BLACK);
		Brett.getBrett()[0][2].setFigur(springer);
		assertEquals(4, springer.bestimmeAlleValidenFelderVierer().size());
//		brett.print();
	}
//	oberer Rand ende
	@Test
	public void bestimmeAlleValidenFelderVierer_0_5_ohneHindernis() {
		Springer springer = new Springer(0, 5, BLACK);
		Brett.getBrett()[0][5].setFigur(springer);
		assertEquals(4, springer.bestimmeAlleValidenFelderVierer().size());
//		brett.print();
	}
//	linker Rand anfang
	@Test
	public void bestimmeAlleValidenFelderVierer_2_0_ohneHindernis() {
		Springer springer = new Springer(2, 0, BLACK);
		Brett.getBrett()[2][0].setFigur(springer);
		assertEquals(4, springer.bestimmeAlleValidenFelderVierer().size());
//		brett.print();
	}
//	linker Rand ende
	@Test
	public void bestimmeAlleValidenFelderVierer_5_0_ohneHindernis() {
		Springer springer = new Springer(5, 0, BLACK);
		Brett.getBrett()[5][0].setFigur(springer);
		assertEquals(4, springer.bestimmeAlleValidenFelderVierer().size());
//		brett.print();
	}
//	rechter Rand anfang
	@Test
	public void bestimmeAlleValidenFelderVierer_2_7_ohneHindernis() {
		Springer springer = new Springer(2, 7, BLACK);
		Brett.getBrett()[2][7].setFigur(springer);
		assertEquals(4, springer.bestimmeAlleValidenFelderVierer().size());
//		brett.print();
	}
//	rechter Rand ende
	@Test
	public void bestimmeAlleValidenFelderVierer_5_7_ohneHindernis() {
		Springer springer = new Springer(5, 7, BLACK);
		Brett.getBrett()[5][7].setFigur(springer);
		assertEquals(4, springer.bestimmeAlleValidenFelderVierer().size());
//		brett.print();
	}
//	unterer Rand anfang
	@Test
	public void bestimmeAlleValidenFelderVierer_7_2_ohneHindernis() {
		Springer springer = new Springer(7, 2, BLACK);
		Brett.getBrett()[7][2].setFigur(springer);
		assertEquals(4, springer.bestimmeAlleValidenFelderVierer().size());
//		brett.print();
	}
//	unterer Rand ende
	@Test
	public void bestimmeAlleValidenFelderVierer_7_5_ohneHindernis() {
		Springer springer = new Springer(7, 5, BLACK);
		Brett.getBrett()[7][5].setFigur(springer);
		assertEquals(4, springer.bestimmeAlleValidenFelderVierer().size());
//		brett.print();
	}
	
	@Test
	public void bestimmeAlleValidenFelderVierer_2_0_mitHindernis_4_1() {
		Springer springer = new Springer(2, 0, BLACK);
		Brett.getBrett()[2][0].setFigur(springer);
		assertEquals(4, springer.bestimmeAlleValidenFelderVierer().size());
		springer.moveTo2(Brett.getBrett()[3][2]);
		
//		Brett.print();
	}
//	**************************************************************************************
//	**************************************************************************************
//	Tests für bestimmeAlleValidenFelderSechser() und bestimmeAlleValidenFelderAchter()
	
	@Test
	public void bestimmeAlleValidenFelderSechser_1_2_ohneHindernis() {
		Springer springer = new Springer(1, 2, BLACK);
		Brett.getBrett()[1][2].setFigur(springer);
		assertEquals(6, springer.bestimmeAlleValidenFelderSechser().size());
		Feld potFeld = Brett.getBrett()[3][1];
		List<Feld> allePotFelder = springer.bestimmeAlleValidenFelderSechser();
		assertEquals(true,allePotFelder.contains(potFeld));
//		Brett.print();
	}
	
	@Test
	public void bestimmeAlleValidenFelderAchter_2_2_ohneHindernis() {
		Springer springer = new Springer(1, 2, BLACK);
		Brett.getBrett()[2][2].setFigur(springer);
		assertEquals(6, springer.bestimmeAlleValidenFelder().size());
	}
}
