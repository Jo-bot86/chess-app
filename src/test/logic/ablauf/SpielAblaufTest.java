package test.logic.ablauf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.chessgame.logic.ablauf.SpielAblauf;
import de.chessgame.logic.brett.Brett;
import de.chessgame.logic.brett.feld.Feld;
import de.chessgame.logic.brett.feld.figur.Farbe;
import de.chessgame.logic.brett.feld.figur.Figur;
import de.chessgame.logic.brett.feld.figur.typ.*;

/**
 * Testet die Beta Version der Klasse SpielAblauf
 * 
 * @author Josef Weldemariam
 *
 */
public class SpielAblaufTest {
	SpielAblauf ablauf;
	Brett testBrett;

	@Before
	public void init() {
		ablauf = new SpielAblauf(new Brett(8, 8));
		ablauf.setAktuelleFarbeAmZug(Farbe.WHITE);
	}

	@Test
	public void bestimmeAlleFigurenMitValidenZuegenVorBereinigung_StartAufstellung() {
		ablauf.getBrett().init();
		ablauf.setBrettInhalt(Brett.getBrett()); // Nicht notwendig da die Mehtode nicht darauf zugreift
		assertEquals(10, ablauf.bestimmeAlleFigurenMitValidenZuegenVorBereinigung().size());
//		Brett.print();	
	}

	@Test
	public void bestimmeAlleFigurenMitValidenZuegenVorBereinigung_KoenigImSchach() {
		ablauf.getBrett().init();
		Dame schwarzeDame = (Dame) Brett.getBrett()[0][3].getFigur();
		schwarzeDame.moveTo2(Brett.getBrett()[2][4]);
		Bauer weisserBauer = (Bauer) Brett.getBrett()[6][4].getFigur();
		weisserBauer.moveTo2(Brett.getBrett()[5][3]);
		assertEquals(12, ablauf.bestimmeAlleFigurenMitValidenZuegenVorBereinigung().size());
//		Brett.print();
	}

	@Test
	public void pruefeMoveTo_SpringerAuf_6_4() {
		ablauf.getBrett().init();
		Dame schwarzeDame = (Dame) Brett.getBrett()[0][3].getFigur();
		schwarzeDame.moveTo2(Brett.getBrett()[2][4]);
		Bauer weisserBauer = (Bauer) Brett.getBrett()[6][4].getFigur();
		weisserBauer.moveTo2(Brett.getBrett()[5][3]);
		Springer schwarzerSpringer = (Springer) Brett.getBrett()[7][6].getFigur();
		Feld zielFeldSpringer = Brett.getBrett()[6][4];
		assertNotNull(ablauf.pruefeMoveTo(schwarzerSpringer, zielFeldSpringer));
//		Brett.print();
	}

	@Test
	public void pruefeMoveTo_SpringerAuf_5_5() {
		ablauf.getBrett().init();
		Dame schwarzeDame = (Dame) Brett.getBrett()[0][3].getFigur();
		schwarzeDame.moveTo2(Brett.getBrett()[2][4]);
		Bauer weisserBauer = (Bauer) Brett.getBrett()[6][4].getFigur();
		weisserBauer.moveTo2(Brett.getBrett()[5][3]);
		Springer schwarzerSpringer = (Springer) Brett.getBrett()[7][6].getFigur();
		Feld zielFeldSpringer = Brett.getBrett()[5][5];
		assertNull(ablauf.pruefeMoveTo(schwarzerSpringer, zielFeldSpringer));
//		Brett.print();
	}

	@Test
	public void getAlleFigurenDieBlockenKoennenDurchPinnen() {
		ablauf.getBrett().init();
		Dame schwarzeDame = (Dame) Brett.getBrett()[0][3].getFigur();
		schwarzeDame.moveTo2(Brett.getBrett()[2][4]);
		Bauer weisserBauer = (Bauer) Brett.getBrett()[6][4].getFigur();
		weisserBauer.moveTo2(Brett.getBrett()[5][3]);
		List<Figur> alleBlocker = ablauf.getAlleFigurenDieBlockenKoennen();
		assertEquals(3, alleBlocker.size());
//		Brett.print();
	}

	@Test
	public void getAlleFigurenDieBlockenKoennenDurchSchlagen() {
		ablauf.getBrett().init();
		Dame schwarzeDame = (Dame) Brett.getBrett()[0][3].getFigur();
		schwarzeDame.moveTo2(Brett.getBrett()[5][4]);
		Bauer weisserBauer = (Bauer) Brett.getBrett()[6][4].getFigur();
		weisserBauer.moveTo2(Brett.getBrett()[5][3]);
		List<Figur> alleBlocker = ablauf.getAlleFigurenDieBlockenKoennen();
		assertEquals(5, alleBlocker.size());
//		Brett.print();
	}

	/**
	 * Der weisse König kann die schwarze Dame nicht schlagen, da diese vom
	 * schwarzen Turm gedeckt ist
	 */
	@Test
	public void getAlleFigurenDieBlockenKoennenDurchPinnenUndSchlagen() {
		ablauf.getBrett().init();
		Bauer weisserBauer = (Bauer) Brett.getBrett()[6][4].getFigur();
		weisserBauer.moveTo2(Brett.getBrett()[5][3]);
		Dame schwarzeDame = (Dame) Brett.getBrett()[0][3].getFigur();
		schwarzeDame.moveTo2(Brett.getBrett()[6][4]);
		Turm schwarzerTurm = (Turm) Brett.getBrett()[0][0].getFigur();
		schwarzerTurm.moveTo2(Brett.getBrett()[2][4]);
		List<Figur> alleBlocker = ablauf.getAlleFigurenDieBlockenKoennen();
		// erwarte 3
		assertEquals(3, alleBlocker.size());

//		Brett.print();
	}

	@Test
	public void pruefeObGepinnt_IstGepinnt() {
		ablauf.getBrett().init();
		Bauer weisserBauer = (Bauer) Brett.getBrett()[6][5].getFigur();
		Dame schwarzeDame = (Dame) Brett.getBrett()[0][3].getFigur();
		schwarzeDame.moveTo2(Brett.getBrett()[4][7]);
		Feld zielFeldBauer = Brett.getBrett()[5][5];
		assertNull(ablauf.pruefeMoveTo(weisserBauer, zielFeldBauer));
//		Brett.print();
	}

	@Test
	public void pruefeObGepinnt_IstNichtGepinnt() {
		ablauf.getBrett().init();
		Bauer weisserBauer = (Bauer) Brett.getBrett()[6][4].getFigur();
		Dame schwarzeDame = (Dame) Brett.getBrett()[0][3].getFigur();
		schwarzeDame.moveTo2(Brett.getBrett()[4][7]);
		Feld zielFeldBauer = Brett.getBrett()[5][4];
		assertNotNull(ablauf.pruefeMoveTo(weisserBauer, zielFeldBauer));
//		Brett.print();
	}

	@Test
	public void getAlleNichtGepinntenFiguren_BauerIstGepinnt() {
		ablauf.getBrett().init();
		Dame schwarzeDame = (Dame) Brett.getBrett()[0][3].getFigur();
		schwarzeDame.moveTo2(Brett.getBrett()[4][7]);
		List<Figur> alleNichtGepinnten = ablauf.getAlleNichtGepinntenFiguren();
		assertEquals(9, alleNichtGepinnten.size());
//		Brett.print();
	}

	@Test
	public void getAlleNichtGepinntenFiguren_KoenigKannAusweichen() {
		ablauf.getBrett().init();
		Dame schwarzeDame = (Dame) Brett.getBrett()[0][3].getFigur();
		schwarzeDame.moveTo2(Brett.getBrett()[4][7]);
		Laeufer weisserLaeufer = (Laeufer) Brett.getBrett()[7][5].getFigur();
		weisserLaeufer.moveTo2(Brett.getBrett()[4][0]);
		List<Figur> alleNichtGepinnten = ablauf.getAlleNichtGepinntenFiguren();

		assertEquals(11, alleNichtGepinnten.size());
//		Brett.print();
	}
//	*********************************************************************************
//	************************weisser König & Damenturm********************************
	@Test
	public void felderZumDamenTurmSindUnbelegtUndUnbedroht_IstUnbedrohtAberBesetzt_Weiss() {
		Koenig koenig = new Koenig(7,4,Farbe.WHITE);
		Brett.getBrett()[7][0].setFigur(new Turm(7,0,Farbe.WHITE));
		Brett.getBrett()[7][4].setFigur(koenig);
		Brett.getBrett()[7][2].setFigur(new Bauer(7,2,Farbe.WHITE));
		assertEquals(false, ablauf.felderZumDamenTurmSindUnbelegtUndUnbedroht(koenig));
//		Brett.print();
	}
	
	@Test
	public void felderZumDamenTurmSindUnbelegtUndUnbedroht_IstBedrohtAberUnbesetzt_Weiss() {
		Koenig koenig = new Koenig(7,4,Farbe.WHITE);
		Brett.getBrett()[7][0].setFigur(new Turm(7,0,Farbe.WHITE));
		Brett.getBrett()[7][4].setFigur(koenig);
		Brett.getBrett()[2][7].setFigur(new Laeufer(2,7,Farbe.BLACK));
		assertEquals(false, ablauf.felderZumDamenTurmSindUnbelegtUndUnbedroht(koenig));
//		Brett.print();
	}
	
	@Test
	public void felderZumDamenTurmSindUnbelegtUndUnbedroht_IstUnbedrohtUndUnbesetzt_Weiss() {
		Koenig koenig = new Koenig(7,4,Farbe.WHITE);
		Brett.getBrett()[7][0].setFigur(new Turm(7,0,Farbe.WHITE));
		Brett.getBrett()[7][4].setFigur(koenig);
		assertEquals(true, ablauf.felderZumDamenTurmSindUnbelegtUndUnbedroht(koenig));
//		Brett.print();
	}
	
	@Test
	public void rochadeMoeglich_NichtMoeglichDaFeldBedroht_Weiss() {
		Koenig koenig = new Koenig(7,4,Farbe.WHITE);
		Turm turm = new Turm(7,0,Farbe.WHITE);
		Brett.getBrett()[7][0].setFigur(turm);
		Brett.getBrett()[7][4].setFigur(koenig);
		Brett.getBrett()[2][7].setFigur(new Laeufer(2,7,Farbe.BLACK));
		assertEquals(false, ablauf.isRochadeMoeglich(koenig, turm));
//		Brett.print();
	}
	
	@Test
	public void rochadeMoeglich_NichtMoeglichDaFeldBesetzt_Weiss() {
		Koenig koenig = new Koenig(7,4,Farbe.WHITE);
		Turm turm = new Turm(7,0,Farbe.WHITE);
		Brett.getBrett()[7][0].setFigur(turm);
		Brett.getBrett()[7][4].setFigur(koenig);
		Brett.getBrett()[7][2].setFigur(new Bauer(7,2,Farbe.WHITE));
		assertEquals(false, ablauf.isRochadeMoeglich(koenig, turm));
//		Brett.print();
	}
	
	@Test
	public void rochadeMoeglich_NichtMoeglichDaKoenigImSchach_Weiss() {
		Koenig koenig = new Koenig(7,4,Farbe.WHITE);
		Turm turm = new Turm(7,0,Farbe.WHITE);
		Brett.getBrett()[7][0].setFigur(turm);
		Brett.getBrett()[7][4].setFigur(koenig);
		Brett.getBrett()[4][7].setFigur(new Laeufer(4,7,Farbe.BLACK));
		assertEquals(false, ablauf.isRochadeMoeglich(koenig, turm));
//		Brett.print();
	}
	
	@Test
	public void rochadeMoeglich_MoeglichDaUnbedrohtUndUnbesetzt_Weiss() {
		Koenig koenig = new Koenig(7,4,Farbe.WHITE);
		Turm turm = new Turm(7,0,Farbe.WHITE);
		Brett.getBrett()[7][0].setFigur(turm);
		Brett.getBrett()[7][4].setFigur(koenig);
		assertEquals(true, ablauf.isRochadeMoeglich(koenig, turm));
//		Brett.print();
	}
	
//	************************schwarzer König & Damenturm********************************
	@Test
	public void felderZumDamenTurmSindUnbelegtUndUnbedroht_IstUnbedrohtAberBesetzt_Schwarz() {
		Koenig koenig = new Koenig(0,4,Farbe.BLACK);
		Brett.getBrett()[0][0].setFigur(new Turm(0,0,Farbe.BLACK));
		Brett.getBrett()[0][4].setFigur(koenig);
		Brett.getBrett()[0][2].setFigur(new Bauer(0,2,Farbe.BLACK));
		assertEquals(false, ablauf.felderZumDamenTurmSindUnbelegtUndUnbedroht(koenig));
//		Brett.print();
	}
	
	@Test
	public void felderZumDamenTurmSindUnbelegtUndUnbedroht_IstBedrohtAberUnbesetzt_Schwarz() {
		Koenig koenig = new Koenig(0,4,Farbe.BLACK);
		Brett.getBrett()[0][0].setFigur(new Turm(0,0,Farbe.BLACK));
		Brett.getBrett()[0][4].setFigur(koenig);
		Brett.getBrett()[4][7].setFigur(new Laeufer(4,7,Farbe.WHITE));
		assertEquals(false, ablauf.felderZumDamenTurmSindUnbelegtUndUnbedroht(koenig));
//		Brett.print();
	}
	
	@Test
	public void felderZumDamenTurmSindUnbelegtUndUnbedroht_IstUnbedrohtUndUnbesetzt_Schwarz() {
		Koenig koenig = new Koenig(0,4,Farbe.BLACK);
		Brett.getBrett()[0][0].setFigur(new Turm(0,0,Farbe.BLACK));
		Brett.getBrett()[0][4].setFigur(koenig);
		assertEquals(true, ablauf.felderZumDamenTurmSindUnbelegtUndUnbedroht(koenig));
//		Brett.print();
	}
	
	@Test
	public void rochadeMoeglich_NichtMoeglichDaFeldBedroht_Schwarz() {
		Koenig koenig = new Koenig(0,4,Farbe.BLACK);
		Turm turm = new Turm(0,0,Farbe.BLACK);
		Brett.getBrett()[0][0].setFigur(turm);
		Brett.getBrett()[0][4].setFigur(koenig);
		Brett.getBrett()[4][7].setFigur(new Laeufer(4,7,Farbe.WHITE));
		assertEquals(false, ablauf.isRochadeMoeglich(koenig, turm));
//		Brett.print();
	}
	
	@Test
	public void rochadeMoeglich_NichtMoeglichDaFeldBesetzt_Schwarz() {
		Koenig koenig = new Koenig(0,4,Farbe.BLACK);
		Turm turm = new Turm(0,0,Farbe.BLACK);
		Brett.getBrett()[0][0].setFigur(turm);
		Brett.getBrett()[0][4].setFigur(koenig);
		Brett.getBrett()[0][2].setFigur(new Bauer(0,2,Farbe.BLACK));
		assertEquals(false, ablauf.isRochadeMoeglich(koenig, turm));
//		Brett.print();
	}
	
	@Test
	public void rochadeMoeglich_NichtMoeglichDaKoenigImSchach_Schwarz() {
		Koenig koenig = new Koenig(0,4,Farbe.BLACK);
		Turm turm = new Turm(0,0,Farbe.BLACK);
		Brett.getBrett()[0][0].setFigur(turm);
		Brett.getBrett()[0][4].setFigur(koenig);
		Brett.getBrett()[3][7].setFigur(new Laeufer(3,7,Farbe.WHITE));
		assertEquals(false, ablauf.isRochadeMoeglich(koenig, turm));
//		Brett.print();
	}
	
	@Test
	public void rochadeMoeglich_MoeglichDaUnbedrohtUndUnbesetzt_Schwarz() {
		Koenig koenig = new Koenig(0,4,Farbe.BLACK);
		Turm turm = new Turm(0,0,Farbe.BLACK);
		Brett.getBrett()[0][0].setFigur(turm);
		Brett.getBrett()[0][4].setFigur(koenig);
		assertEquals(true, ablauf.isRochadeMoeglich(koenig, turm));
//		Brett.print();
	}
	
	
	
	
	

}
