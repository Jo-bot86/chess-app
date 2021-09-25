package test.persitence;

import java.io.File;

import de.chessgame.logic.ablauf.SpielAblauf;
import de.chessgame.logic.brett.Brett;
import de.chessgame.logic.brett.feld.Feld;
import de.chessgame.logic.brett.feld.figur.Farbe;
import de.chessgame.persistence.Leser;
import de.chessgame.persistence.Schreiber;

public class TestMain {

	public static void main(String[] args) {
//		SpielAblauf ablauf = new SpielAblauf(new Brett(8,8));
//		ablauf.getBrett().init();
//		ablauf.setAktuelleFarbeAmZug(Farbe.BLACK);
//		Brett.print();
//		
//		File ziel = new File("resources/spielstaende/testBrett");
//		Schreiber schreiber = new Schreiber();
//		schreiber.schreibeSpielAblauf(ziel, ablauf);
		
		File quelle = new File("resources/spielstaende/testBrett");
		Leser leser = new Leser();
		SpielAblauf gelesenerAblauf =leser.leseSpielAblauf(quelle);
		Feld[][] testBrett = gelesenerAblauf.getBrettInhalt();
		Brett.setBrett(testBrett);
		Brett.print();
	}

}
