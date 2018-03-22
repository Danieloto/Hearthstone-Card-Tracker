package GUI;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import deck.Card;
import deck.Deck;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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

	Background black = new Background(new BackgroundFill(Paint.valueOf("#000000"), CornerRadii.EMPTY, Insets.EMPTY));// red
	Background white = new Background(new BackgroundFill(Paint.valueOf("#FFFFFF"), CornerRadii.EMPTY, Insets.EMPTY));// ye
	LargePic lp = new LargePic();
	Image[] large_picture = new Image[30];
	Image[] small_picture = new Image[30];
	static Label[] lables;
	Scene scene;
	AnchorPane buttom;
	public static Deck deck1;
	private static int count;
	private static boolean stillOn = true;
	private static String macLogAddress = "/Applications/Hearthstone/Logs/Power.log";
	private static String pcLogAddress = "C:/Program Files (x86)/Hearthstone/Logs/Power.log";
	private static ArrayList<String> friendlyCards = new ArrayList<String>();
	private static ArrayList<String> opponentCards = new ArrayList<String>();
	private static ArrayList<String> outPut = new ArrayList<String>();
	public static Deck friendlyDeck;
	private static Log_Reader logReader = new Log_Reader();

	/*
	 * inner class for threading
	 */
	static class Log_Processor extends Thread {

		public void run() {

			if (System.getProperty("os.name").toLowerCase().contains("win"))
				openFile(pcLogAddress);
			else
				openFile(macLogAddress);
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
				for(int i=0;i<30;i++) {
					String cardNameInDeck=friendlyDeck.getCard(i).name;
					double testNumber=friendlyDeck.getCard(i).getValue().doubleValue();
					if(cardName.equals(cardNameInDeck)&&testNumber==.25) {
						
						friendlyDeck.getCard(i).setValue(1);
						lables[i].setOpacity(1);
						break;
					}
				}
				friendlyCards.remove(cardName);
				outPut.add("*" + cardName + " returned to deck");
			}

		}

		public void openFile(String logLocation) {

			try {
				FileInputStream fstream = new FileInputStream(logLocation);
				BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
				Thread.sleep(1000);
				String line = null;
				while (stillOn) {
					line = br.readLine();
					if (line != null) {

						prcessingLine(line);

					}
					while (line == null) {
						for (String s : outPut)
							System.out.println(s);
						Card tempCard;
						Server server=new Server();
						ArrayList<String> cardsDrawn=friendlyCards;
						Deck dummyDeck=friendlyDeck;
						for(String cardName: friendlyCards) {
							for(int i=0;i<30;i++) {
								String cardNameInDeck=friendlyDeck.getCard(i).name;
								double testNumber=friendlyDeck.getCard(i).getValue().doubleValue();
								if(cardName.equals(cardNameInDeck)&&testNumber!=.25) {
									
									friendlyDeck.getCard(i).setValue(.25);
									lables[i].setOpacity(.25);
									break;
								}
							}
						}
						friendlyCards.clear();
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

	@Override
	public void start(Stage primaryStage) {
		
		try {

			buttom= new AnchorPane();
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
			String[] image_name = logReader.createTestNames(0);
			
			
			TextField text = new TextField("");
			TextField search = new TextField("Add Cards to Deck");
			TextField delete1 = new TextField("Remove Cards to Deck");
			TextField delete = new TextField("");
			Button save = new Button("Save Deck");
			save.setLayoutX(350);
			save.setLayoutY(0);
			Button load = new Button("Load Deck");
			load.setLayoutX(500);
			load.setLayoutY(0);
			search.setLayoutX(350);
			search.setLayoutY(25);
			search.setDisable(true);
			text.setLayoutX(350);
			text.setLayoutY(50);
			delete1.setLayoutX(500);
			delete1.setLayoutY(25);
			delete1.setDisable(true);
			delete.setLayoutX(500);
			delete.setLayoutY(50);
			buttom.getChildren().addAll(save,load,search,text,delete1,delete);
			text.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent e) {
					if(e.getCode().equals(KeyCode.ENTER)){
						//Server.createCard(text.getCharacters().toString());	
						deck1.addCard(Server.createCard(text.getCharacters().toString()));
						System.out.println(" ");
						for(int abc = 0; abc < deck1.getSize(); abc++){
							System.out.println(deck1.getCard(abc).Name());
						}
						System.out.println(" ");
						text.clear();
					}
				}
			});
			delete.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent e1) {
					if(e1.getCode().equals(KeyCode.ENTER)){
						//Server.createCard(text.getCharacters().toString());	
						deck1.removeCard(delete.getCharacters().toString());
						System.out.println(" ");
						for(int abc1 = 0; abc1 < deck1.getSize(); abc1++){
							System.out.println(deck1.getCard(abc1).Name());
						}
						System.out.println(" ");
						delete.clear();
					}
				}
			});
			save.setOnAction(new EventHandler<ActionEvent>() {

		        @Override
		        public void handle(ActionEvent event) {
		            try {
						PrintWriter writer = new PrintWriter("deck.txt");
						for(int i=0;i < deck1.getSize(); i++){
							writer.println(deck1.getCard(i).Name());
						}
						writer.close();
						System.out.println("");
						System.out.println("Deck Saved!");
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        }
		    });
			load.setOnAction(new EventHandler<ActionEvent>() {

		        @Override
		        public void handle(ActionEvent event) {
		            String cardname = null;
		        	try {
						deck1.clearDeck();
		            	FileReader reader = new FileReader("deck.txt");
						BufferedReader bReader = new BufferedReader(reader);
						while((cardname = bReader.readLine()) != null){
							deck1.addCard(Server.createCard(cardname));
						}
						bReader.close();
		            	System.out.println("");
						System.out.println("Loaded Deck:");
						for(int abc1 = 0; abc1 < deck1.getSize(); abc1++){
							System.out.println(deck1.getCard(abc1).Name());
						}
						System.out.println("");
						
					} catch (FileNotFoundException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        }
		    });
				
			
			
			put_large_image(image_name); // put images into the image array larp and smlp
			put_small_image(image_name);

			for (int i = 0; i < 30; i++) {
				lables[i] = new Label();

				lables[i].setPrefSize(340, 50);

				BackgroundImage BackImage = new BackgroundImage((small_picture[i]), BackgroundRepeat.REPEAT,
						BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
				lables[i].setBackground(new Background(BackImage));
				vb.getChildren().add(lables[i]);

			}
			//System.out.println(friendlyCards.get(0));
			//System.out.println(lables[0].getText());
//			for(int i=0;i<friendlyDeck.getSize();i++) {
//				lables[i].opacityProperty().bind(friendlyDeck.getCard(i));
//			}
			//lables[0].setOpacity(0);
			

			

			// new event

			for (int i = 0; i < 30; i++) {
				lables[i].addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent e) {
						// lp.setImage(i);
						Stage sta = new Stage();
						try {
							lp.getLoca(primaryStage.getX(), primaryStage.getY());

							if (e.getSource() == lables[0]) {
								lp.setImage(large_picture[0]);
							} else if (e.getSource() == lables[1]) {
								lp.setImage(large_picture[1]);
							} else if (e.getSource() == lables[2]) {
								lp.setImage(large_picture[2]);
							} else if (e.getSource() == lables[3]) {
								lp.setImage(large_picture[3]);
							} else if (e.getSource() == lables[4]) {
								lp.setImage(large_picture[4]);
							} else if (e.getSource() == lables[5]) {
								lp.setImage(large_picture[5]);
							} else if (e.getSource() == lables[6]) {
								lp.setImage(large_picture[6]);
							} else if (e.getSource() == lables[7]) {
								lp.setImage(large_picture[7]);
							} else if (e.getSource() == lables[8]) {
								lp.setImage(large_picture[8]);
							} else if (e.getSource() == lables[9]) {
								lp.setImage(large_picture[9]);
							} else if (e.getSource() == lables[10]) {
								lp.setImage(large_picture[10]);
							} else if (e.getSource() == lables[11]) {
								lp.setImage(large_picture[11]);
							} else if (e.getSource() == lables[12]) {
								lp.setImage(large_picture[12]);
							} else if (e.getSource() == lables[13]) {
								lp.setImage(large_picture[13]);
							} else if (e.getSource() == lables[14]) {
								lp.setImage(large_picture[14]);
							} else if (e.getSource() == lables[15]) {
								lp.setImage(large_picture[15]);
							} else if (e.getSource() == lables[16]) {
								lp.setImage(large_picture[16]);
							} else if (e.getSource() == lables[17]) {
								lp.setImage(large_picture[17]);
							} else if (e.getSource() == lables[18]) {
								lp.setImage(large_picture[18]);
							} else if (e.getSource() == lables[19]) {
								lp.setImage(large_picture[19]);
							} else if (e.getSource() == lables[20]) {
								lp.setImage(large_picture[20]);
							} else if (e.getSource() == lables[21]) {
								lp.setImage(large_picture[21]);
							} else if (e.getSource() == lables[22]) {
								lp.setImage(large_picture[22]);
							} else if (e.getSource() == lables[23]) {
								lp.setImage(large_picture[23]);
							} else if (e.getSource() == lables[24]) {
								lp.setImage(large_picture[24]);
							} else if (e.getSource() == lables[25]) {
								lp.setImage(large_picture[25]);
							} else if (e.getSource() == lables[26]) {
								lp.setImage(large_picture[26]);
							} else if (e.getSource() == lables[27]) {
								lp.setImage(large_picture[27]);
							} else if (e.getSource() == lables[28]) {
								lp.setImage(large_picture[28]);
							} else if (e.getSource() == lables[29]) {
								lp.setImage(large_picture[29]);
							}

							lp.start(sta);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				lables[i].addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent e) {
						lp.Close();

					}
				});
			}

		    scene = new Scene(buttom, 650, 700);
			primaryStage.setScene(scene);
			primaryStage.setResizable(true);
			primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		lables = new Label[30];
		friendlyDeck=new Deck();
		deck1 = new Deck();
		String[] deckCards= logReader.createTestNames(0);
		Server server = new Server();
		Card tempCard;
		for(String cardName: deckCards) {
			tempCard=server.createCard(cardName);
			friendlyDeck.addCard(tempCard);
		}
		Log_Processor logProcessor = new Log_Processor();
		logProcessor.start();
		while(true) {
			launch(args);
		}
		

	}

	public void put_large_image(String[] image_name) {

		// System.out.println(("Image/"+image_name[0]+".png").replaceAll("\\s+",""));
		for (int i = 0; i < 30; i++) {
			large_picture[i] = new Image(("Image/" + image_name[i] + ".png").replaceAll("\\s+", ""));
		}

	}

	public void put_small_image(String[] image_name) {

		for (int i = 0; i < 30; i++) {
			small_picture[i] = new Image(("Image/" + image_name[i] + "(s).png").replaceAll("\\s+", ""));
		}

	}

}
