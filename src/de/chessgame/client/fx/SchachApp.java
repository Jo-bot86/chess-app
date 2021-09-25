package de.chessgame.client.fx;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.chessgame.client.Startable;
import de.chessgame.logic.ablauf.SpielAblauf;
import de.chessgame.logic.brett.Brett;
import static de.chessgame.logic.brett.feld.figur.Farbe.*;

import de.chessgame.logic.brett.feld.Feld;
import de.chessgame.logic.brett.feld.figur.Figur;
import de.chessgame.logic.brett.feld.figur.typ.*;
import de.chessgame.persistence.Leser;
import de.chessgame.persistence.Schreiber;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Repräsentiert das GUI für das Schachbrett und eine Anzeige für den
 * Spielverlauf sowie zwei Buttons zum navigieren durch die verschiedenen
 * Zustände des Schachbretts.
 * 
 * @author Josef Weldemariam
 *
 */
public class SchachApp extends Application implements Startable {
//	************************Erstellen des Hauptbereichs******************************

	/**
	 * Speichert den Spielablauf
	 */
	private SpielAblauf ablauf;

	/**
	 * Speichert eine Liste von Spielabläufen
	 */
	private List<SpielAblauf> spielZustaende;
	
	/**
	 * Speichert eine Liste aller Zustände des Schachbretts
	 */
	private List<Feld[][]> brettZustaende;

	/**
	 * Enthält den Index des aktuell angezeigten Spielzustands
	 */
	private int brettZustaendeIndex;

	/**
	 * Leiste die das Menue enthält
	 */
	private MenuBar leiste = new MenuBar();

	/**
	 * Enthält das Menu Datei
	 */
	private Menu datei = new Menu("Datei");

	/**
	 * Enthält den Eintrag Neues Spiel
	 */
	private MenuItem neuesSpiel = new Menu("Neues Spiel");

	/**
	 * Enthält den Eintrag Öffnen
	 */
	private MenuItem oeffnen = new Menu("Öffnen");

	/**
	 * Enthält den Eintrag Speichern
	 */
	private MenuItem speichern = new Menu("Speichern");

	/**
	 * Enthält den Eintrag Beenden
	 */
	private MenuItem beenden = new Menu("Beenden");

	/**
	 * Enthaelt den Hauptbereich des anzuzeigenden Fenster
	 */
	private BorderPane hauptbereich;

	/**
	 * Enthaelt das Schachbrett
	 */
	private GridPane brett;

	/**
	 * Enhält den Spielverlauf einer Schachpartie
	 */
	private Label spielVerlauf;

	/**
	 * Enthält einen Button der es ermöglicht vorwärts durch die verschiedenen
	 * Zustände des Bretts zu navigieren
	 */
	private Button vor;

	/**
	 * Enthält einen Button der es ermöglicht rückwärts durch die verschiedenen
	 * Zustände des Bretts zu navigieren
	 */
	private Button zurueck;

	/**
	 * Enthält den vor und zurueck Button
	 */
	private FlowPane buttonContainer;

	/**
	 * Enthält das Label spielVerlauf und das FlowPane buttonContainer
	 */
	private VBox labelUndNavigation = new VBox();

	/**
	 * Enthält die MenuBar leiste und das BorderPane
	 */
	private VBox virtualBox = new VBox();

//	************************************Erstellen des Spielbretts*************************

	/**
	 * Enthält die Button, die die Felder des Schachbretts darstellen. Diese werden
	 * von der Klasse ErweiterterButton dargestellt welche von der Button-Klasse
	 * erbt
	 */
	private ErweiterterButton[][] felder = new ErweiterterButton[8][8];

//	***************schwarze Figuren*****************
	/**
	 * Enthält alle Darstellungen eines schwarzen Bauern
	 */
	private List<ImageView> bauernSchwarzView;

	/**
	 * Enthält alle Darstellungen eines schwarzen Turm
	 */
	private List<ImageView> tuermeSchwarzView;

	/**
	 * Enthält alle Darstellungen eines schwarzen Springers
	 */
	private List<ImageView> springerSchwarzView;

	/**
	 * Enthält alle Darstellungen eines schwarzen Läufers
	 */
	private List<ImageView> laeuferSchwarzView;

	/**
	 * Enthält die Darstellung einer schwarzen Dame
	 */
	private ImageView dameSchwarzView;

	/**
	 * Enthält die Darstellung eines schwarzen Königs
	 */
	private ImageView koenigSchwarzView;

	/**
	 * Enthält alle Darstellungen eines weißen Bauern
	 */
	private List<ImageView> bauernWeissView;

	/**
	 * Enthält alle Darstellungen eines weißen Turm
	 */
	private List<ImageView> tuermeWeissView;

	/**
	 * Enthält alle Darstellungen eines weißen Springers
	 */
	private List<ImageView> springerWeissView;

	/**
	 * Enthält alle Darstellungen eines weißen Läufers
	 */
	private List<ImageView> laeuferWeissView;

	/**
	 * Enthält die Darstellung einer weißen Dame
	 */
	private ImageView dameWeissView;

	/**
	 * Enthält die Darstellung eines weißen Königs
	 */
	private ImageView koenigWeissView;

//	*********************************************************************************

	/**
	 * Überschreibt die aus Application geerbte start-Methode.
	 * Erstellt den Hauptbereich, das Brett, den Brettrand, die Figuren und die Scene.
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		erstelleHauptbereich();
		erstelleBrett();
		erstelleBrettRand();
		erstelleFiguren();
		handleMenu();
		handleClickOnNavigation();

		Scene spiel = new Scene(virtualBox, 700, 545);
		spiel.getStylesheets().add("file:resources/style.css");

		primaryStage.setTitle("Schach App");
		primaryStage.getIcons().add(new Image("file:resources/figuren/bauer-schwarz.png"));
		primaryStage.setScene(spiel);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	/**
	 * Erstellt die Menuleiste und den Hauptbereich. Der Hauptbereich verwendet das
	 * Borderlayout und positioniert im Zentrum das dargestellte Schachbrett und
	 * rechts ein Label für den Spielverlauf.
	 */
	public void erstelleHauptbereich() {
		hauptbereich = new BorderPane();
		brett = new GridPane();
		spielVerlauf = new Label("Hier steht der Verlauf des Spiels");
		buttonContainer = new FlowPane();
		vor = new Button(">");
		zurueck = new Button("<");

		hauptbereich.setRight(labelUndNavigation);
		hauptbereich.setCenter(brett);
		hauptbereich.setPadding(new Insets(10, 10, 10, 10));
		BorderPane.setAlignment(brett, Pos.CENTER);
		BorderPane.setMargin(brett, new Insets(10, 10, 10, 10));
//		BorderPane.setMargin(spielVerlauf, new Insets(10, 5, 0, 10));

//		spielVerlauf.setPadding(new Insets(10, 20, 10, 10));
		spielVerlauf.setMinSize(140, 460);
		spielVerlauf.setMaxSize(160, 460);

		buttonContainer.getChildren().addAll(zurueck, vor);
		buttonContainer.setMinSize(140, 20);
		buttonContainer.setMaxSize(140, 20);

		labelUndNavigation.getChildren().addAll(spielVerlauf, buttonContainer);
		labelUndNavigation.setPadding(new Insets(10, 20, 10, 10));
		labelUndNavigation.setMinSize(140, 480);
		labelUndNavigation.setMaxSize(160, 480);

		vor.setMinSize(70, 30);
		vor.setMaxSize(70, 30);
		zurueck.setMinSize(70, 30);
		zurueck.setMaxSize(70, 30);
//		vor.setPrefSize(60, 20);
//		zurueck.setPrefSize(60, 20);

		erstelleMenu();
		virtualBox.getChildren().addAll(leiste, hauptbereich);
	}

	/**
	 * Erstellt das Brett aus Buttons die die Felder repräsentieren und setzt die
	 * Größe der einzelnen Buttons
	 */
	public void erstelleBrett() {
		for (int i = 0; i < 8; i++) {
			final int spalte = i;
			for (int j = 0; j < 8; j++) {
				final int zeile = j;
				ErweiterterButton feld = new ErweiterterButton(zeile, spalte); // new Button("" + (i +8*j+1));
				feld.setMaxSize(60, 60);
				feld.setMinSize(60, 60);
				felder[spalte][zeile] = feld;
				if (spalte % 2 == 0 && zeile % 2 == 0 || spalte % 2 == 1 && zeile % 2 == 1) {
					feld.getStyleClass().add("black-button");
					feld.setSchwarzesFeld(true);
				} else {
					feld.getStyleClass().add("white-button");
					feld.setSchwarzesFeld(false);
				}
				handleMouseOnClick(feld);

				brett.add(feld, spalte, zeile);
			}
		}
	}

	/**
	 * Behandelt die events die von den MenuItems neuesSpiel, oeffnen, speichern und
	 * beenden gefeuert werden. neuesSpiel initialisiert ein neues Schachbrett,
	 * offnen lädt einen gespeicherten Spielstand, speichern spreichert einen
	 * bestehenden Spielstand und beenden beendet die Anwendung.
	 */
	public void handleMenu() {
		neuesSpiel.setOnAction(event -> {
			File quelle = new File("resources/spielstaende/initBrett");
			Leser leser = new Leser();
			SpielAblauf gelesenerAblauf = leser.leseSpielAblauf(quelle);
			Feld[][] gelesenerBrettInhalt = gelesenerAblauf.getBrettInhalt();
			Brett.setBrett(gelesenerBrettInhalt);
			ablauf.setAktuelleFarbeAmZug(gelesenerAblauf.getAktuelleFarbeAmZug());
			stelleFigurenAuf();
//			spielZustaende = new ArrayList<SpielAblauf>();
			brettZustaende = new ArrayList<Feld[][]>();
			brettZustaende.add(Brett.getBrett());
			brettZustaendeIndex = 0;
			// Falls die initBrett Datei gelöscht wird lässt sich
			// durch auskommentieren der Zeilen 251-258 und herein nehmen
			// der Zeilen 262-264 die Startaufstellung auf herkömmlichen Wege
			// wieder herstellen
//			ablauf.getBrett().init();
//			ablauf.setAktuelleFarbeAmZug(WHITE);
//			stelleFigurenAuf();
		});
		oeffnen.setOnAction(event -> {
			File quelle = new File("resources/spielstaende/gespeicherterSpielstand");
			Leser leser = new Leser();
			SpielAblauf gelesenerAblauf = leser.leseSpielAblauf(quelle);
			Feld[][] gelesenerBrettInhalt = gelesenerAblauf.getBrettInhalt();
			Brett.setBrett(gelesenerBrettInhalt);
			ablauf.setAktuelleFarbeAmZug(gelesenerAblauf.getAktuelleFarbeAmZug());
			stelleFigurenAuf();
		});
		speichern.setOnAction(event -> {
			File ziel = new File("resources/spielstaende/gespeicherterSpielstand");
			Schreiber schreiber = new Schreiber();
			ablauf.setBrettInhalt(Brett.getBrett());
			schreiber.schreibeSpielAblauf(ziel, ablauf);
			entferneFiguren();
		});
		beenden.setOnAction(event -> {
			Platform.exit();
		});

	}

	/**
	 * Behandelt das Ereignis, das ausglöst wird wenn
	 * auf den vor bzw zurück Button geklickt wird.
	 * Ermöglicht das navigieren durch die einzelnen Zustände
	 * des Schachbretts
	 */
	public void handleClickOnNavigation() {
		vor.setOnMouseClicked(event -> {
			if (brettZustaendeIndex < brettZustaende.size() - 1) {
				brettZustaendeIndex++;
				Brett.setBrett(brettZustaende.get(brettZustaendeIndex));
				entferneFiguren();
				stelleFigurenAuf();
				ablauf.wechselAkutelleFarbeAmZug();
			}
		});

		zurueck.setOnMouseClicked(event -> {
			if (brettZustaendeIndex > 0) {
				brettZustaendeIndex--;
				Brett.setBrett(brettZustaende.get(brettZustaendeIndex));
//				entferneFiguren();
				stelleFigurenAuf();
				
				ablauf.wechselAkutelleFarbeAmZug();
			}		
		});
	}

	/**
	 * Behandelt das Mouse-Event das ausgelöst wird wenn ein Feld auf dem
	 * Schachbrett geklickt wird. Handelt es sich um ein valides Feld, ändert sich
	 * die Hintergrundfarbe. Wird das selbe Feld ein zweites mal geklickt wird die
	 * Hintergrundfarbe wieder auf die ursprüngliche Farbe gesetzt. War bereits ein
	 * Feld geklickt und wird ein valides Feld im Anschluss geklickt, bewegt sich
	 * die Figur auf dieses Feld.
	 * 
	 * @param feld Das Feld das geklickt wurde
	 */
	public void handleMouseOnClick(ErweiterterButton feld) {
		ablauf.setAlleFigurenMitValidenZuegen(ablauf.bestimmeAlleFigurenMitValidenZuegen());
		feld.setOnMousePressed(event -> {
			int rowIndex = feld.getZEILEN_INDEX();
			int colIndex = feld.getSPALTEN_INDEX();
			Feld feldInBrett = Brett.getBrett()[rowIndex][colIndex];
			Figur figurInBrett = feldInBrett.getFigur();
			ErweiterterButton tmp = oneAlreadyClicked();
			// Der Fall, dass noch kein anderes Feld ausgewählt wurde
			if (tmp == null) {
				if (ablauf.bestimmeAlleFigurenMitValidenZuegen().contains(figurInBrett)) {
					feld.setStyle("-fx-background-color: #eafa75");
					feld.setAusgewaehlt(true);
				}
			} else {
				Feld tmpFeldInBrett = Brett.getBrett()[tmp.getZEILEN_INDEX()][tmp.getSPALTEN_INDEX()];
				Figur tmpFigurInBrett = tmpFeldInBrett.getFigur();
				List<Feld> alleMoeglichenZielFelder = tmpFigurInBrett.getMoeglicheZielFelder();
				if (ablauf.bestimmeAlleFigurenMitValidenZuegen().contains(figurInBrett)) {
					if (!tmp.equals(feld)) {
						setUrsprungsFarbe(tmp);
						tmp.setAusgewaehlt(false);
						feld.setStyle("-fx-background-color: #eafa75");
						feld.setAusgewaehlt(true);
					} else {
						setUrsprungsFarbe(feld);
						feld.setAusgewaehlt(false);
					}
				} else if (alleMoeglichenZielFelder.contains(feldInBrett)) {
					ablauf.getAlleFigurenDieBedrohen().removeAll(ablauf.getAlleFigurenDieBedrohen());
					tmpFigurInBrett.moveTo(feldInBrett);
					feld.setGraphic(tmp.getFigur());
					feld.setFigur(tmp.getFigur());
					tmp.setGraphic(null);
					tmp.setFigur(null);
					setUrsprungsFarbe(tmp);
					ablauf.getBrett().setValideZuegeFuerAlleFiguren();
					ablauf.wechselAkutelleFarbeAmZug();
					ablauf.bestimmeAlleFigurenDieBedrohen();
					//Für die Navigation durch die verschiedenen Zustände
					// des Schachbretts
					Feld[][] tmpBrett = erstelleTiefeKopieVonBrett();
					brettZustaende.add(tmpBrett);
					brettZustaendeIndex++;
				}
			}
		});
	}
	
	/**
	 * Erstellt eine Kopie des Spielablaufs
	 * @return Eine Kopie vom Spielablauf
	 */
//	public SpielAblauf erstelleKopieVonSpielablauf() {
//		SpielAblauf tmpAblauf = new SpielAblauf(new Brett(8,8));
//		tmpAblauf.setAktuelleFarbeAmZug(ablauf.getAktuelleFarbeAmZug());
//		tmpAblauf.setAlleFigurenDieBedrohen(ablauf.getAlleFigurenDieBedrohen());
//		tmpAblauf.setAlleFigurenMitValidenZuegen(ablauf.getAlleFigurenMitValidenZuegen());
//		return tmpAblauf;
//	}
	
	/**
	 * Erstellt eine tiefe Kopie des Brettinhalts
	 * @return Eine Kopie vom Brettinhalt
	 */
	public Feld[][] erstelleTiefeKopieVonBrett(){
		Feld[][] tmpBrett = new Feld[8][8];
		Feld[][] dasBrett = Brett.getBrett();
		for(int i=0; i<8; i++) {
			for(int j=0; j<8; j++) {
				Feld tmpFeld = new Feld(i,j);
				tmpFeld.setFigur(dasBrett[i][j].getFigur());
				tmpBrett[i][j]= tmpFeld;
			}
		}
		return tmpBrett;
	}

	/**
	 * Setzt für den übergebenen Butten die Ursprungsfarbe
	 * 
	 * @param feld Button dessen Ursprungsfarbe gesetzt werden soll
	 */
	public void setUrsprungsFarbe(ErweiterterButton feld) {
		if (feld.isSchwarzesFeld()) {
			feld.setStyle("-fx-background-color: linear-gradient(to bottom right, #04de13, #147a1b)");
			feld.setAusgewaehlt(false);
		} else {
			feld.setStyle("-fx-background-color: #b9c9b9");
			feld.setAusgewaehlt(false);
		}
	}

	/**
	 * Gibt an ob in felder bereits ein ErweiterterButton im Attribut ausgewaehlt
	 * den Wert true hat und gibt diesen Button zurueck. Kann auch null sein wenn
	 * kein Button bereits ausgewählt ist
	 * 
	 * @return einen Button aus felder der isAusgewaehlt() true liefert oder null
	 * @see {@link de.chessgame.client.fx.ErweiterterButton#isAusgewaehlt()}
	 */
	public ErweiterterButton oneAlreadyClicked() {
		for (int zeile = 0; zeile < felder.length; zeile++) {
			for (int spalte = 0; spalte < felder[0].length; spalte++) {
				if (felder[zeile][spalte].isAusgewaehlt()) {
					return felder[zeile][spalte];
				}
			}
		}
		return null;
	}

	/**
	 * Erstellt den Rahmen des SpielBretts
	 */
	public void erstelleBrettRand() {
		// Erstellt den Spielfeld Rand
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				if (x == 0) {
					felder[x][y].getStyleClass().add("border-left");
				}
				if (y == 0) {
					felder[x][y].getStyleClass().add("border-top");
				}
				if (x == 7) {
					felder[x][y].getStyleClass().add("border-right");
				}
				if (y == 7) {
					felder[x][y].getStyleClass().add("border-bottom");
				}
				if (x == 0 && y == 0) {
					felder[x][y].getStyleClass().add("border-left-top");
				}
				if (x == 7 && y == 0) {
					felder[x][y].getStyleClass().add("border-right-top");
				}
				if (x == 0 && y == 7) {
					felder[x][y].getStyleClass().add("border-left-bottom");
				}
				if (x == 7 && y == 7) {
					felder[x][y].getStyleClass().add("border-right-bottom");
				}
			}
		}

	}

	/**
	 * Entfernt alle alle Figuren vom Schachbrett
	 */
	public void entferneFiguren() {
		for (int i = 0; i < felder.length; i++) {
			for (int j = 0; j < felder[0].length; j++) {
				felder[i][j].setFigur(null);
				felder[i][j].setGraphic(null);
				felder[i][j].setAusgewaehlt(false);
				setUrsprungsFarbe(felder[i][j]);
			}
		}
	}

	/**
	 * Stellt die Figuren anhand des von der Klasse Brett gelieferten Schachbretts
	 * auf
	 * 
	 * @see {@link de.chessgame.logic.brett.Brett Brett}
	 */
	public void stelleFigurenAuf() {
		// Speichert in einer Liste den Laufindex für die Bauern, Türme, Sprigner und
		// Läufer um die View listen zu durchlaufen
		// ViewIndex für den schwazen Bauer ist bei Index 0 und für den weissen Bauer
		// bei Index 1
		// schwarzer Turm bei 2 und weisser Turm bei 3
		// schwarzer Springer bei 4 und weisser Springer bei 5
		// schwarzer Läufer bei 6 und weisser Läufer bei 7
		List<Integer> indizes = new ArrayList<Integer>();
		for (int i = 0; i < 8; i++) {
			indizes.add(0);
		}
		for (int i = 0; i < ablauf.getBrett().getZEILEN_DIM(); i++) {
			for (int j = 0; j < ablauf.getBrett().getSPALTEN_DIM(); j++) {
				Figur aktuelleFigur = Brett.getBrett()[i][j].getFigur();
				if (aktuelleFigur instanceof Bauer && aktuelleFigur.getFarbe() == BLACK) {
					felder[j][i].setGraphic(bauernSchwarzView.get(indizes.get(0)));
					felder[j][i].setFigur(bauernSchwarzView.get(indizes.get(0)));
					indizes.set(0, indizes.get(0) + 1);
				}
				if (aktuelleFigur instanceof Bauer && aktuelleFigur.getFarbe() == WHITE) {
					felder[j][i].setGraphic(bauernWeissView.get(indizes.get(1)));
					felder[j][i].setFigur(bauernWeissView.get(indizes.get(1)));
					indizes.set(1, indizes.get(1) + 1);
				}
				if (aktuelleFigur instanceof Turm && aktuelleFigur.getFarbe() == BLACK) {
					felder[j][i].setGraphic(tuermeSchwarzView.get(indizes.get(2)));
					felder[j][i].setFigur(tuermeSchwarzView.get(indizes.get(2)));
					indizes.set(2, indizes.get(2) + 1);
				}
				if (aktuelleFigur instanceof Turm && aktuelleFigur.getFarbe() == WHITE) {
					felder[j][i].setGraphic(tuermeWeissView.get(indizes.get(3)));
					felder[j][i].setFigur(tuermeWeissView.get(indizes.get(3)));
					indizes.set(3, indizes.get(3) + 1);
				}
				if (aktuelleFigur instanceof Springer && aktuelleFigur.getFarbe() == BLACK) {
					felder[j][i].setGraphic(springerSchwarzView.get(indizes.get(4)));
					felder[j][i].setFigur(springerSchwarzView.get(indizes.get(4)));
					indizes.set(4, indizes.get(4) + 1);
				}
				if (aktuelleFigur instanceof Springer && aktuelleFigur.getFarbe() == WHITE) {
					felder[j][i].setGraphic(springerWeissView.get(indizes.get(5)));
					felder[j][i].setFigur(springerWeissView.get(indizes.get(5)));
					indizes.set(5, indizes.get(5) + 1);
				}
				if (aktuelleFigur instanceof Laeufer && aktuelleFigur.getFarbe() == BLACK) {
					felder[j][i].setGraphic(laeuferSchwarzView.get(indizes.get(6)));
					felder[j][i].setFigur(laeuferSchwarzView.get(indizes.get(6)));
					indizes.set(6, indizes.get(6) + 1);
				}
				if (aktuelleFigur instanceof Laeufer && aktuelleFigur.getFarbe() == WHITE) {
					felder[j][i].setGraphic(laeuferWeissView.get(indizes.get(7)));
					felder[j][i].setFigur(laeuferWeissView.get(indizes.get(7)));
					indizes.set(7, indizes.get(7) + 1);
				}
				if (aktuelleFigur instanceof Dame && aktuelleFigur.getFarbe() == BLACK) {
					felder[j][i].setGraphic(dameSchwarzView);
					felder[j][i].setFigur(dameSchwarzView);

				}
				if (aktuelleFigur instanceof Dame && aktuelleFigur.getFarbe() == WHITE) {
					felder[j][i].setGraphic(dameWeissView);
					felder[j][i].setFigur(dameWeissView);
				}
				if (aktuelleFigur instanceof Koenig && aktuelleFigur.getFarbe() == BLACK) {
					felder[j][i].setGraphic(koenigSchwarzView);
					felder[j][i].setFigur(koenigSchwarzView);

				}
				if (aktuelleFigur instanceof Koenig && aktuelleFigur.getFarbe() == WHITE) {
					felder[j][i].setGraphic(koenigWeissView);
					felder[j][i].setFigur(koenigWeissView);

				}
			}
		}

	}
	
	public void stelleFigurenAuf(SpielAblauf ablauf) {
		// Speichert in einer Liste den Laufindex für die Bauern, Türme, Sprigner und
		// Läufer um die View listen zu durchlaufen
		// ViewIndex für den schwazen Bauer ist bei Index 0 und für den weissen Bauer
		// bei Index 1
		// schwarzer Turm bei 2 und weisser Turm bei 3
		// schwarzer Springer bei 4 und weisser Springer bei 5
		// schwarzer Läufer bei 6 und weisser Läufer bei 7
		List<Integer> indizes = new ArrayList<Integer>();
		for (int i = 0; i < 8; i++) {
			indizes.add(0);
		}
		for (int i = 0; i < ablauf.getBrett().getZEILEN_DIM(); i++) {
			for (int j = 0; j < ablauf.getBrett().getSPALTEN_DIM(); j++) {
				Figur aktuelleFigur = Brett.getBrett()[i][j].getFigur();
				if (aktuelleFigur instanceof Bauer && aktuelleFigur.getFarbe() == BLACK) {
					felder[j][i].setGraphic(bauernSchwarzView.get(indizes.get(0)));
					felder[j][i].setFigur(bauernSchwarzView.get(indizes.get(0)));
					indizes.set(0, indizes.get(0) + 1);
				}
				if (aktuelleFigur instanceof Bauer && aktuelleFigur.getFarbe() == WHITE) {
					felder[j][i].setGraphic(bauernWeissView.get(indizes.get(1)));
					felder[j][i].setFigur(bauernWeissView.get(indizes.get(1)));
					indizes.set(1, indizes.get(1) + 1);
				}
				if (aktuelleFigur instanceof Turm && aktuelleFigur.getFarbe() == BLACK) {
					felder[j][i].setGraphic(tuermeSchwarzView.get(indizes.get(2)));
					felder[j][i].setFigur(tuermeSchwarzView.get(indizes.get(2)));
					indizes.set(2, indizes.get(2) + 1);
				}
				if (aktuelleFigur instanceof Turm && aktuelleFigur.getFarbe() == WHITE) {
					felder[j][i].setGraphic(tuermeWeissView.get(indizes.get(3)));
					felder[j][i].setFigur(tuermeWeissView.get(indizes.get(3)));
					indizes.set(3, indizes.get(3) + 1);
				}
				if (aktuelleFigur instanceof Springer && aktuelleFigur.getFarbe() == BLACK) {
					felder[j][i].setGraphic(springerSchwarzView.get(indizes.get(4)));
					felder[j][i].setFigur(springerSchwarzView.get(indizes.get(4)));
					indizes.set(4, indizes.get(4) + 1);
				}
				if (aktuelleFigur instanceof Springer && aktuelleFigur.getFarbe() == WHITE) {
					felder[j][i].setGraphic(springerWeissView.get(indizes.get(5)));
					felder[j][i].setFigur(springerWeissView.get(indizes.get(5)));
					indizes.set(5, indizes.get(5) + 1);
				}
				if (aktuelleFigur instanceof Laeufer && aktuelleFigur.getFarbe() == BLACK) {
					felder[j][i].setGraphic(laeuferSchwarzView.get(indizes.get(6)));
					felder[j][i].setFigur(laeuferSchwarzView.get(indizes.get(6)));
					indizes.set(6, indizes.get(6) + 1);
				}
				if (aktuelleFigur instanceof Laeufer && aktuelleFigur.getFarbe() == WHITE) {
					felder[j][i].setGraphic(laeuferWeissView.get(indizes.get(7)));
					felder[j][i].setFigur(laeuferWeissView.get(indizes.get(7)));
					indizes.set(7, indizes.get(7) + 1);
				}
				if (aktuelleFigur instanceof Dame && aktuelleFigur.getFarbe() == BLACK) {
					felder[j][i].setGraphic(dameSchwarzView);
					felder[j][i].setFigur(dameSchwarzView);

				}
				if (aktuelleFigur instanceof Dame && aktuelleFigur.getFarbe() == WHITE) {
					felder[j][i].setGraphic(dameWeissView);
					felder[j][i].setFigur(dameWeissView);
				}
				if (aktuelleFigur instanceof Koenig && aktuelleFigur.getFarbe() == BLACK) {
					felder[j][i].setGraphic(koenigSchwarzView);
					felder[j][i].setFigur(koenigSchwarzView);

				}
				if (aktuelleFigur instanceof Koenig && aktuelleFigur.getFarbe() == WHITE) {
					felder[j][i].setGraphic(koenigWeissView);
					felder[j][i].setFigur(koenigWeissView);

				}
			}
		}

	}

	/**
	 * erstellt die schwarzen Figuren für die Darstellung
	 */
	public void erstelleSchwarzeFiguren() {
		// Ließt die Images aus
		Image bauerSchwarz = new Image("file:resources/figuren/bauer-schwarz.png");
		Image turmSchwarz = new Image("file:resources/figuren/turm-schwarz.png");
		Image springerSchwarz = new Image("file:resources/figuren/springer-schwarz.png");
		Image laeuferSchwarz = new Image("file:resources/figuren/laeufer-schwarz.png");
		Image dameSchwarz = new Image("file:resources/figuren/dame-schwarz.png");
		Image koenigSchwarz = new Image("file:resources/figuren/koenig-schwarz.png");

		// Zeichnet die Imgaes
		bauernSchwarzView = new ArrayList<ImageView>();
		tuermeSchwarzView = new ArrayList<ImageView>();
		springerSchwarzView = new ArrayList<ImageView>();
		laeuferSchwarzView = new ArrayList<ImageView>();

		for (int i = 0; i < 8; i++) {
			bauernSchwarzView.add(new ImageView(bauerSchwarz));
		}

		for (int i = 0; i < 2; i++) {
			tuermeSchwarzView.add(new ImageView(turmSchwarz));
			springerSchwarzView.add(new ImageView(springerSchwarz));
			laeuferSchwarzView.add(new ImageView(laeuferSchwarz));
		}

		dameSchwarzView = new ImageView(dameSchwarz);
		koenigSchwarzView = new ImageView(koenigSchwarz);

	}

	/**
	 * erstellt die weißen Figuren für die Darstellung
	 */
	public void erstelleWeisseFiguren() {
		// Ließt die Images aus
		Image bauerWeiss = new Image("file:resources/figuren/bauer-weiss.png");
		Image turmWeiss = new Image("file:resources/figuren/turm-weiss.png");
		Image springerWeiss = new Image("file:resources/figuren/springer-weiss.png");
		Image laeuferWeiss = new Image("file:resources/figuren/laeufer-weiss.png");
		Image dameWeiss = new Image("file:resources/figuren/dame-weiss.png");
		Image koenigWeiss = new Image("file:resources/figuren/koenig-weiss.png");

		// Zeichnet die Imgaes
		bauernWeissView = new ArrayList<ImageView>();
		tuermeWeissView = new ArrayList<ImageView>();
		springerWeissView = new ArrayList<ImageView>();
		laeuferWeissView = new ArrayList<ImageView>();

		for (int i = 0; i < 8; i++) {
			bauernWeissView.add(new ImageView(bauerWeiss));
		}

		for (int i = 0; i < 2; i++) {
			tuermeWeissView.add(new ImageView(turmWeiss));
			springerWeissView.add(new ImageView(springerWeiss));
			laeuferWeissView.add(new ImageView(laeuferWeiss));
		}

		dameWeissView = new ImageView(dameWeiss);
		koenigWeissView = new ImageView(koenigWeiss);
	}

	public void erstelleFiguren() {
		erstelleSchwarzeFiguren();
		erstelleWeisseFiguren();
	}

	/**
	 * erstellt die Menuleiste und das Menu datei
	 */
	public void erstelleMenu() {
		datei.getItems().addAll(neuesSpiel, oeffnen, speichern, beenden);

		leiste.getMenus().add(datei);
	}

	/**
	 * @param ablauf the ablauf to set
	 */
	public void setAblauf(SpielAblauf ablauf) {
		this.ablauf = ablauf;
	}

	/**
	 * Überschreibt die von Application geerbte init Methode und initialisiert das
	 * Schachbrett der Klasse Brett
	 */
	@Override
	public void init() {
		ablauf = new SpielAblauf(new Brett(8, 8));
	}

	/**
	 * Diese Method tut rein gar nichts
	 */
	@Override
	public void start() {
		// Tut nichts
	}

}
