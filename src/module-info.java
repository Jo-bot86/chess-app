module my_chessgame_third_version {
	requires javafx.graphics;
	requires javafx.controls;
	requires junit;
	requires javafx.base;
	
	opens de.chessgame.client.fx;
	opens test;
	opens test.logic.brett;
	opens test.logic.brett.feld.figur.typ;
	opens test.logic.ablauf;
	opens test.logic.brett.feld.figur;
}