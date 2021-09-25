package test.client;

import java.util.Random;

import de.chessgame.client.Startable;
import de.chessgame.client.konsole.KonsolenAusgabe;
import de.chessgame.logic.ablauf.SpielAblauf;
import de.chessgame.logic.brett.Brett;
import javafx.application.Application;

public class StartTest {

	public static void main(String[] args) {
		Random wuerfel = new Random();
//		if (wuerfel.nextBoolean()) {
//			Application.launch(de.chessgame.client.fx.SchachApp.class);
//		} else {
			Startable ausgabe = new KonsolenAusgabe(new SpielAblauf(new Brett(8, 8)));
			ausgabe.start();
//		}
	}

}
