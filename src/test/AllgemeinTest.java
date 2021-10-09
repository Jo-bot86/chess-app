package test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.chessgame.logic.ablauf.SpielAblauf;
import de.chessgame.logic.brett.Brett;
import de.chessgame.logic.brett.feld.figur.Farbe;
import de.chessgame.logic.brett.feld.figur.Figur;


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
	public void koenigSollteKeineValidenZuegeHaben() {
		ablauf.getBrett().init();
		ablauf.setAktuelleFarbeAmZug(Farbe.BLACK);
		Brett.getBrett()[1][0].getFigur().moveTo2(Brett.getBrett()[3][0]);
		Brett.getBrett()[1][4].getFigur().moveTo2(Brett.getBrett()[2][4]);
		Brett.getBrett()[6][3].getFigur().moveTo2(Brett.getBrett()[2][4]);
		Brett.getBrett()[6][4].getFigur().moveTo2(Brett.getBrett()[3][4]);
		Brett.getBrett()[0][4].getFigur().moveTo2(Brett.getBrett()[4][7]);
		Brett.getBrett()[7][3].getFigur().moveTo2(Brett.getBrett()[5][7]);
		Brett.getBrett()[7][5].getFigur().moveTo2(Brett.getBrett()[5][3]);
		List<Figur> alleFiguren =ablauf.bestimmeAlleFigurenMitValidenZuegen();
		assertEquals(0, alleFiguren.size());
		
//		Brett.print();
	}
}
