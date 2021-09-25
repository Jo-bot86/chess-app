package de.chessgame.client.konsole;

import java.util.Arrays;
import java.util.Scanner;

import de.chessgame.client.Startable;
import de.chessgame.logic.ablauf.SpielAblauf;
import de.chessgame.logic.brett.Brett;
import de.chessgame.logic.brett.feld.Feld;
import de.chessgame.logic.brett.feld.figur.Figur;
import javafx.stage.Stage;

/**
 * Erstellt eine Ausgabe des aktuellen Bretts in der Konsole. Dafür
 * existiert eine member-Variable, die beim Konstruktoraufruf von der logik
 * uebergeben wird.
 * @author Josef Weldemariam
 *
 */
public class KonsolenAusgabe implements Startable{
	
	/**
	 * speichert das aktuelle Schachbrett
	 */
	private Brett aktuellesBrett;
	
	/**
	 * speichert den Spielablauf
	 */
	private SpielAblauf ablauf ;
	
	/**
	 * Erstellt eine Instanz von KonsolenAusgabe und weisst
	 * das aktuelle Brett zu
	 * @param aktuellesBrett
	 */
	public KonsolenAusgabe(SpielAblauf ablauf) {
		this.ablauf = ablauf;
	}

	/**
	 * @return the ablauf
	 */
	public SpielAblauf getAblauf() {
		return ablauf;
	}

	/**
	 * Erzeugt eine Ausgabe von brett in der Konsole
	 */
	public void print() {
		int counter = 0;
		System.out.println("  " + " 0 " + " 1 " +" 2 " +" 3 " +" 4 "+ " 5 " +" 6 " +" 7 ");
		for (Feld[] zeile : Brett.getBrett()) {
			System.out.println(counter + " " + Arrays.deepToString(zeile));
			System.out.println();
			counter++;
		}
	}

	/**
	 * Startet die Anwendung in der Konsole
	 */
	@Override
	public void start(){
		ablauf.getBrett().init();
		while(true) {
			print();
			System.out.println("Weiß ist am Zug");
			System.out.println("Bitte geben Sie den ZeilenIndex der zu bewegenden Figur ein");
			Scanner leser = new Scanner(System.in);
			int zeilenIndexFigur = leser.nextInt();
			System.out.println("Bitte geben Sie den SpaltenIndex der zu bewegenden Figur ein");
			int spaltenIndexFigur = leser.nextInt();
			System.out.println("Bitte geben Sie den ZeilenIndex des Zielfelds ein");
			int zeilenIndexFeld = leser.nextInt();
			System.out.println("Bitte geben Sie den SpaltenIndex des Zielfelds ein");
			int spaltenIndexFeld = leser.nextInt();
			
			Figur figur = Brett.getBrett()[zeilenIndexFigur][spaltenIndexFigur].getFigur();
			Feld zielFeld = Brett.getBrett()[zeilenIndexFeld][spaltenIndexFeld];
			figur.moveTo2(zielFeld);
			print();
			
			System.out.println("Schwarz ist am Zug");
			System.out.println("Bitte geben Sie den ZeilenIndex der zu bewegenden Figur ein");
			zeilenIndexFigur = leser.nextInt();
			System.out.println("Bitte geben Sie den SpaltenIndex der zu bewegenden Figur ein");
			spaltenIndexFigur = leser.nextInt();
			System.out.println("Bitte geben Sie den ZeilenIndex des Zielfelds ein");
			zeilenIndexFeld = leser.nextInt();
			System.out.println("Bitte geben Sie den SpaltenIndex des Zielfelds ein");
			spaltenIndexFeld = leser.nextInt();
			
			figur = Brett.getBrett()[zeilenIndexFigur][spaltenIndexFigur].getFigur();
			zielFeld = Brett.getBrett()[zeilenIndexFeld][spaltenIndexFeld];
			figur.moveTo2(zielFeld);
			
		}
	}

	/**
	 * Diese Methode tut rein gar nichts.
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		// Tut nichts
		
	}
}
