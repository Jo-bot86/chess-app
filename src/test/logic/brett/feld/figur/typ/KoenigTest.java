package test.logic.brett.feld.figur.typ;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.chessgame.logic.brett.Brett;
import static de.chessgame.logic.brett.feld.figur.Farbe.*;

import de.chessgame.logic.brett.feld.Feld;
import de.chessgame.logic.brett.feld.figur.typ.Bauer;
import de.chessgame.logic.brett.feld.figur.typ.Dame;
import de.chessgame.logic.brett.feld.figur.typ.Koenig;
import de.chessgame.logic.brett.feld.figur.typ.Laeufer;

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
	
	@Test
	public void getAlleBedrohtenFelder() {
		Koenig testKoenig = new Koenig(7,7, BLACK);
		Brett.getBrett()[7][7].setFigur(testKoenig);
//		Bauer testBauer = new Bauer(3,3,WHITE);
//		Brett.getBrett()[3][3].setFigur(testBauer);
		Dame testDame = new Dame(6,5,WHITE);
		Brett.getBrett()[6][5].setFigur(testDame);
		List<Feld> bedrohteFelder = testKoenig.getAlleBedrohtenFelder();
//		for(int i=0; i< bedrohteFelder.size(); i++) {
//			System.out.println(bedrohteFelder.get(i).getZeilenIndex() + " " + bedrohteFelder.get(i).getSpaltenIndex());
//		}
		assertEquals(23, testKoenig.getAlleBedrohtenFelder().size());
//		Brett.print();
	}
	
	@Test
	public void getAlleEchtValidenFelder() {
		Koenig testKoenig = new Koenig(7,7, BLACK);
		Brett.getBrett()[7][7].setFigur(testKoenig);
		Dame testDame = new Dame(6,4,WHITE);
		Brett.getBrett()[6][4].setFigur(testDame);
		Bauer testBauer = new Bauer(6,6,WHITE);
		Brett.getBrett()[6][6].setFigur(testBauer);
		List<Feld> alleValidenFelder = testKoenig.bestimmeAlleEchtValidenFelder();
//		for(int i=0; i<testKoenig.bestimmeAlleValidenFelder().size(); i++) {
//			System.out.println(testKoenig.bestimmeAlleValidenFelder().get(i).getZeilenIndex() + " " + testKoenig.bestimmeAlleValidenFelder().get(i).getSpaltenIndex());
//		}
//		System.out.println();
//		for(int i=0; i<alleEchtValidenFelder.size(); i++) {
//			System.out.println(alleEchtValidenFelder.get(i).getZeilenIndex() + " " + alleEchtValidenFelder.get(i).getSpaltenIndex());
//		}
		assertEquals(3, alleValidenFelder.size());
//		Brett.print();
	}
	
	@Test
	public void isSchach_KeinSchach() {
		Koenig testKoenig = new Koenig(7,7, BLACK);
		Brett.getBrett()[7][7].setFigur(testKoenig);
		Dame testDame = new Dame(6,4,WHITE);
		Brett.getBrett()[6][4].setFigur(testDame);
		assertEquals(false, testKoenig.isSchach());
	}
	
	@Test
	public void isSchach_Schach() {
		Koenig testKoenig = new Koenig(7,7, BLACK);
		Brett.getBrett()[7][7].setFigur(testKoenig);
		Dame testDame = new Dame(7,4,WHITE);
		Brett.getBrett()[7][4].setFigur(testDame);
		assertEquals(true, testKoenig.isSchach());

//		Brett.print();
	}
	
	@Test
	public void isSchach_SchachDurchDame() {
		Koenig testKoenig = new Koenig(4,7, BLACK);
		Brett.getBrett()[4][7].setFigur(testKoenig);
		Laeufer testLaeufer1 = new Laeufer(7,2,WHITE);
		Brett.getBrett()[7][2].setFigur(testLaeufer1);
		Laeufer testLaeufer2 = new Laeufer(5,3,WHITE);
		Brett.getBrett()[5][3].setFigur(testLaeufer2);
		Dame testDame = new Dame(5,7,WHITE);
		Brett.getBrett()[5][7].setFigur(testDame);
		System.out.println(testKoenig.isSchach());
		Feld ziel = Brett.getBrett()[3][7];
		ziel.setFigur(testKoenig);
		Brett.getBrett()[4][7].setFigur(null);
		testKoenig.setZeilenIndex(ziel.getZeilenIndex());
		testKoenig.setSpaltenIndex(ziel.getSpaltenIndex());
		System.out.println(testKoenig.isSchach());

		Brett.print();
	}
}
