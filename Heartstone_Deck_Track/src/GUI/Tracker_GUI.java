package GUI;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import deck.Card;
import deck.Deck;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import logReader.Log_Reader;
import server.Server;

public class Tracker_GUI extends Application {
	///////////////////// profile////////////////////////////////
	Background black = new Background(new BackgroundFill(Paint.valueOf("#000000"), CornerRadii.EMPTY, Insets.EMPTY));// red
	Background white = new Background(new BackgroundFill(Paint.valueOf("#FFFFFF"), CornerRadii.EMPTY, Insets.EMPTY));// ye
	LargePic lp = new LargePic();
	private int n;
	Image[] Larp = new Image[30];
	Image[] smlp = new Image[30];

	private boolean stillOn;
	private static String macLogAddress = "/Applications/Hearthstone/Logs/Power.log";
	private static String pcLogAddress = "C:/Program Files (x86)/Hearthstone/Logs/Power.log";
	private ArrayList<String> friendlyCards;
	private ArrayList<String> opponentCards;
	private ArrayList<String> outPut;
	private Server server;
	public Deck friendlyDeck;
	private Log_Reader logReader = new Log_Reader();
	///////////////////// total///////////////////////////////

	@Override
	public void start(Stage primaryStage) {
		try {
			stillOn = true;
			friendlyCards = new ArrayList<String>();
			opponentCards = new ArrayList<String>();
			outPut = new ArrayList<String>();
			AnchorPane buttom = new AnchorPane();
			buttom.setPrefSize(350, 700); 
			buttom.setBackground(null);

			Pane p2 = new Pane();
			p2.setPrefSize(280, 100);
			p2.setLayoutX(30);
			p2.setLayoutY(20);
			p2.setBackground(null);
			BackgroundImage BackImage2 = new BackgroundImage(
					new Image("Image/hearthstone_logo.png", 280, 100, false, true), BackgroundRepeat.REPEAT,
					BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
			p2.setBackground(new Background(BackImage2));
			buttom.getChildren().add(p2);

			ScrollPane list = new ScrollPane();
			list.setPrefHeight(600);
			list.setPrefWidth(350);
			list.setLayoutX(0);
			list.setLayoutY(100);
			buttom.getChildren().add(list);

			VBox vb = new VBox();
			list.setContent(vb);
			Label[] cards = new Label[30];
			String[] image_name = logReader.createTestNames(0);

			put_large_image(image_name); // put images into the image array larp and smlp
			put_small_image(image_name);

			for (int i = 0; i < 30; i++) {
				cards[i] = new Label();
				cards[i].setPrefSize(340, 50);
				BackgroundImage BackImage = new BackgroundImage((smlp[i]), BackgroundRepeat.REPEAT,
						BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
				cards[i].setBackground(new Background(BackImage));
				vb.getChildren().add(cards[i]);
				

			}
			// new event 
			
			for (int i = 0; i < 30; i++) {
				cards[i].addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent e) {
						// lp.setImage(i);
						Stage sta = new Stage();
						try {
							lp.getLoca(primaryStage.getX(), primaryStage.getY());

							if (e.getSource() == cards[0]) {
								lp.setImage(Larp[0]);
							} else if (e.getSource() == cards[1]) {
								lp.setImage(Larp[1]);
							} else if (e.getSource() == cards[2]) {
								lp.setImage(Larp[2]);
							} else if (e.getSource() == cards[3]) {
								lp.setImage(Larp[3]);
							} else if (e.getSource() == cards[4]) {
								lp.setImage(Larp[4]);
							} else if (e.getSource() == cards[5]) {
								lp.setImage(Larp[5]);
							} else if (e.getSource() == cards[6]) {
								lp.setImage(Larp[6]);
							} else if (e.getSource() == cards[7]) {
								lp.setImage(Larp[7]);
							} else if (e.getSource() == cards[8]) {
								lp.setImage(Larp[8]);
							} else if (e.getSource() == cards[9]) {
								lp.setImage(Larp[9]);
							} else if (e.getSource() == cards[10]) {
								lp.setImage(Larp[10]);
							} else if (e.getSource() == cards[11]) {
								lp.setImage(Larp[11]);
							} else if (e.getSource() == cards[12]) {
								lp.setImage(Larp[12]);
							} else if (e.getSource() == cards[13]) {
								lp.setImage(Larp[13]);
							} else if (e.getSource() == cards[14]) {
								lp.setImage(Larp[14]);
							} else if (e.getSource() == cards[15]) {
								lp.setImage(Larp[15]);
							} else if (e.getSource() == cards[16]) {
								lp.setImage(Larp[16]);
							} else if (e.getSource() == cards[17]) {
								lp.setImage(Larp[17]);
							} else if (e.getSource() == cards[18]) {
								lp.setImage(Larp[18]);
							} else if (e.getSource() == cards[19]) {
								lp.setImage(Larp[19]);
							} else if (e.getSource() == cards[20]) {
								lp.setImage(Larp[20]);
							} else if (e.getSource() == cards[21]) {
								lp.setImage(Larp[21]);
							} else if (e.getSource() == cards[22]) {
								lp.setImage(Larp[22]);
							} else if (e.getSource() == cards[23]) {
								lp.setImage(Larp[23]);
							} else if (e.getSource() == cards[24]) {
								lp.setImage(Larp[24]);
							} else if (e.getSource() == cards[25]) {
								lp.setImage(Larp[25]);
							} else if (e.getSource() == cards[26]) {
								lp.setImage(Larp[26]);
							} else if (e.getSource() == cards[27]) {
								lp.setImage(Larp[27]);
							} else if (e.getSource() == cards[28]) {
								lp.setImage(Larp[28]);
							} else if (e.getSource() == cards[29]) {
								lp.setImage(Larp[29]);
							}

							lp.start(sta);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				cards[i].addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent e) {
						lp.Close();

					}
				});

			}
			
			Scene scene = new Scene(buttom, 350, 700);
			primaryStage.setScene(scene);
			primaryStage.setResizable(true);
			primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		launch(args);
		
		
	
	}

	public void put_large_image(String[] image_name) {
		
		// System.out.println(("Image/"+image_name[0]+".png").replaceAll("\\s+",""));
		for (int i = 0; i < 30; i++) {
			Larp[i] = new Image(("Image/" + image_name[i] + ".png").replaceAll("\\s+", ""));
		}

	}

	public void put_small_image(String[] image_name) {
		smlp[0] = new Image(("Image/" + image_name[0] + "(s).png").replaceAll("\\s+", ""));
		for (int i = 0; i < 30; i++) {
			smlp[i] = new Image(("Image/" + image_name[i] + "(s).png").replaceAll("\\s+", ""));
		}

	}

	private void prcessingLine(String line) {
		/*
		 * Checks to see if a new card was played in game
		 */
		if (logReader.lineContainsCards(line)) {
			String cardName;

			if (logReader.isFriendlyCards(line)) {
				friendlyCards.add(logReader.idToNames(line.substring(line.lastIndexOf('=') + 1)));
				cardName = friendlyCards.get(friendlyCards.size() - 1);
				outPut.add(cardName);
				/*
				 * use frendlyCards to develop your code
				 */

			} else if (logReader.isOpponentCards(line)) {

				opponentCards.add(logReader.idToNames(line.substring(line.lastIndexOf('=') + 1)));
				cardName = opponentCards.get(opponentCards.size() - 1);
				// outPut.add(cardName);
				// System.out.println(opponentCards.get(opponentCards.size() - 1));

				/*
				 * use oppoinentCards to develop your code
				 */
			}

		}
		/*
		 * Checks to see if a new game has started
		 */
		else if (logReader.isNewGame(line)) {
			if (!outPut.isEmpty()) {
				outPut.clear();
				friendlyCards.clear();
				opponentCards.clear();
			}

		}
		/*
		 * Checks to see if a card was returned
		 */
		else if (logReader.isSendingCardBackToDeck(line)) {
			Log_Reader temp = new Log_Reader();
			int beginIndex, endIndex;
			beginIndex = line.indexOf("card") + 7;
			endIndex = line.indexOf("card") + 14;
			String cardName = temp.idToNames(line.substring(beginIndex, endIndex));
			friendlyCards.remove(cardName);
			outPut.add("*" + cardName + " returned to deck");
		}

	}

	public void openFile(String logLocation) {

		try {
			FileInputStream fstream = new FileInputStream(logLocation);
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

			String line = null;
			while (stillOn) {
				line = br.readLine();
				if (line != null) {

					prcessingLine(line);

				}
				while (line == null) {
					for (String s : outPut)
						System.out.println(s);
					outPut.clear();
					line = br.readLine();
					if (br.readLine() != null) {
						Thread.sleep(1000);
						break;
					}
				}

			}

			fstream.close();

		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}

	}

}
