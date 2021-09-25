package test.logic.ablauf;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.chessgame.logic.ablauf.SpielAblauf;
import de.chessgame.logic.brett.Brett;
import de.chessgame.logic.brett.feld.Feld;
import de.chessgame.logic.brett.feld.figur.Figur;
import de.chessgame.logic.brett.feld.figur.typ.*;

import static de.chessgame.logic.brett.feld.figur.Farbe.*;

/**
 * Testet die Klasse SpielAblauf
 * @author Josef Weldemariam
 *
 */
public class SpielAblaufTest {
	/**
	 * Enthält den Spielablauf
	 */
	private SpielAblauf ablauf;
	
	/**
	 * initialisiert den Spielablauf
	 */
	@Before
	public void init() {
		ablauf = new SpielAblauf(new Brett(8,8));
		ablauf.setAktuelleFarbeAmZug(BLACK);
//		ablauf.getBrett().init();
		

		
	}
	
//	@Test
//	public void bestimmeAlleFigurenMitValidenZuegen_Weiss_StartPosition(){
//		assertEquals(10, ablauf.bestimmeAlleFigurenMitValidenZuegen().size());
//	}
	
//	@Test
//	public void isSchach() {
//		// setzt weißen Bauer 3 Schritte vor
//		Brett.getBrett()[6][3].getFigur().moveTo2(Brett.getBrett()[5][3]);
//		Brett.getBrett()[5][3].getFigur().moveTo2(Brett.getBrett()[4][3]);
//		Brett.getBrett()[4][3].getFigur().moveTo2(Brett.getBrett()[3][3]);
//		// setzt schwarzen Bauer und schwarzen König ein Schritt vor
//		Brett.getBrett()[1][4].getFigur().moveTo2(Brett.getBrett()[2][4]);
//		Brett.getBrett()[0][4].getFigur().moveTo2(Brett.getBrett()[1][4]);
//		
//		// weißer Bauer setzt schwarzen König in Schach
//		Brett.getBrett()[3][3].getFigur().moveTo2(Brett.getBrett()[2][3]);
//		ablauf.setAktuelleFarbeAmZug(BLACK);
//		ablauf.isSchach();
//
//		Brett.print();
	
	/**
	 * Testet die Methode getAlleBedrohtenFelder
	 */
//	@Test
//	public void getAlleBedrohtenFelder() {
//		List<Feld> alleBedrohtenFelder = ablauf.getAlleBedrohtenFelder();
//		for(int i=0; i< alleBedrohtenFelder.size(); i++) {
//			System.out.println(alleBedrohtenFelder.get(i).getZeilenIndex() + " " + alleBedrohtenFelder.get(i).getSpaltenIndex());
//		}
//		Brett.print();
//	}
	
//	@Test
//	public void getFigurenDieSchlagenKoennen() {
//		Bauer schwarzerBauer = (Bauer) Brett.getBrett()[1][4].getFigur();
//		schwarzerBauer.moveTo2(Brett.getBrett()[2][4]);
//		Koenig schwarzerKoenig = (Koenig) Brett.getBrett()[0][4].getFigur();
//		schwarzerKoenig.moveTo2(Brett.getBrett()[1][4]);
//		Bauer weisserBauer = (Bauer) Brett.getBrett()[6][3].getFigur();
//		weisserBauer.moveTo2(Brett.getBrett()[2][3]);
//		ablauf.bestimmeAlleFigurenDieBedrohen();
		//assertEquals(1, ablauf.getFigurenDieSchlagenKoennen().size());
//		for(Figur figur: ablauf.getFigurenDieSchlagenKoennen()) {
//			System.out.println(figur.getZeilenIndex() + " " + figur.getSpaltenIndex());
//		}
//		Brett.print();
//	}
	
//	@Test
//	public void bereinigeValideFelderKoenig() {
//		Bauer schwarzerBauer = (Bauer) Brett.getBrett()[1][4].getFigur();
//		schwarzerBauer.moveTo2(Brett.getBrett()[2][4]);
//		Koenig schwarzerKoenig = (Koenig) Brett.getBrett()[0][4].getFigur();
//		schwarzerKoenig.moveTo2(Brett.getBrett()[1][4]);
//		Bauer weisserBauer = (Bauer) Brett.getBrett()[6][3].getFigur();
//		weisserBauer.moveTo2(Brett.getBrett()[2][3]);
//		ablauf.getBrett().setValideZuegeFuerAlleFiguren();
//		ablauf.bestimmeAlleFigurenDieBedrohen();
//		ablauf.bereinigeValideFelderKoenig();
//		assertEquals(2, schwarzerKoenig.getMoeglicheZielFelder().size());
////		Brett.print();
//	}
	
	@Test
	public void bestimmeAlleFigurenDieBedrohen() {
		assertEquals(0, ablauf.getAlleFigurenDieBedrohen().size());
		Koenig schwarzerKoenig = new Koenig(4,4,BLACK);
		Brett.getBrett()[4][4].setFigur(schwarzerKoenig);
		Bauer weisserBauer = new Bauer(2,2,WHITE);
		Brett.getBrett()[5][3].setFigur(weisserBauer);
		System.out.println(ablauf.isSchach());
//		assertEquals(0, ablauf.getAlleFigurenDieBedrohen().size());

		Brett.print();
	}
	

}
