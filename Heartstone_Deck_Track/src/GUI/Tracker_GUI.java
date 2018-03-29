package GUI;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;

import java.io.FileInputStream;

import java.io.FileNotFoundException;

import java.io.FileReader;

import java.io.FileWriter;

import java.io.IOException;

import java.io.InputStreamReader;

import java.io.PrintWriter;

import java.util.ArrayList;

import java.util.Optional;

import java.util.Scanner;

import javax.swing.ImageIcon;

import deck.Card;

import deck.Deck;

import javafx.application.Application;

import javafx.beans.value.ObservableValue;

import javafx.collections.ObservableList;

import javafx.event.ActionEvent;

import javafx.event.EventHandler;

import javafx.geometry.Insets;

import javafx.scene.Node;

import javafx.scene.Scene;

import javafx.scene.control.Alert;

import javafx.scene.control.Button;

import javafx.scene.control.ChoiceBox;

import javafx.scene.control.Label;

import javafx.scene.control.ProgressBar;

import javafx.scene.control.ScrollPane;

import javafx.scene.control.Tab;

import javafx.scene.control.TabPane;

import javafx.scene.control.TextArea;

import javafx.scene.control.TextField;

import javafx.scene.control.TextInputDialog;

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

import javafx.scene.paint.Color;

import javafx.scene.paint.Paint;

import javafx.scene.text.Font;

import javafx.scene.text.FontWeight;

import javafx.stage.Stage;

import javafx.stage.StageStyle;

import logReader.Log_Reader;

import server.Server;

import javafx.embed.swing.SwingFXUtils;

@SuppressWarnings("restriction")

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

	private static String pcPath = "/Decks/";

	private static String macPath = "/Users/chenjunjie/repository/A8/Heartstone_Deck_Track/Decks/";

	private static String macLogAddress = "/Applications/Hearthstone/Logs/Power.log";

	private static String pcLogAddress = "C:/Program Files (x86)/Hearthstone/Logs/Power.log";

	private static ArrayList<String> friendlyCards = new ArrayList<String>();

	private static ArrayList<String> opponentCards = new ArrayList<String>();

	private static ArrayList<String> outPut = new ArrayList<String>();

	public static Deck friendlyDeck;

	private static Log_Reader logReader = new Log_Reader();

	VBox vb = new VBox();

	FileWriter resultFile;

	PrintWriter writer;

	Button[] deckName;

	Server server = new Server();

	public static Label twoCardChance = new Label("[2]: 6.6%");

	static int remainingCards = 0;

	private static String oneCardString = "null";

	static Label oneCardChance = new Label(oneCardString);

	/*
	 * 
	 * inner class for threading
	 * 
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
			 * 
			 * Checks to see if a new card was played in game
			 * 
			 */

			if (logReader.lineContainsCards(line)) {

				String cardName;

				if (logReader.isFriendlyCards(line)) {

					friendlyCards.add(logReader.idToNames(line.substring(line.lastIndexOf('=') + 1)));

					cardName = friendlyCards.get(friendlyCards.size() - 1);

					outPut.add(cardName);

					/*
					 * 
					 * use frendlyCards to develop your code
					 * 
					 */

				} else if (logReader.isOpponentCards(line)) {

					opponentCards.add(logReader.idToNames(line.substring(line.lastIndexOf('=') + 1)));

					cardName = opponentCards.get(opponentCards.size() - 1);

					// outPut.add(cardName);

					// System.out.println(opponentCards.get(opponentCards.size() - 1));

					/*
					 * 
					 * use oppoinentCards to develop your code
					 * 
					 */

				}

			}

			/*
			 * 
			 * Checks to see if a new game has started
			 * 
			 */

			else if (logReader.isNewGame(line)) {

				remainingCards = 30;

				outPut.clear();

				friendlyCards.clear();

				opponentCards.clear();

				for (int i = 0; i < 30; i++) {

					lables[i].setOpacity(1);

				}

				/*
				 * 
				 * 
				 * 
				 * 
				 * 
				 * mark
				 * 
				 * 
				 * 
				 */

				// oneCardChance.setOpacity(1);

				// twoCardChance.setText("[2]: "+Integer.toString(1/remainingCards));

			}

			/*
			 * 
			 * Checks to see if a card was returned
			 * 
			 */

			else if (logReader.isSendingCardBackToDeck(line)) {

				Log_Reader temp = new Log_Reader();

				int beginIndex, endIndex;

				beginIndex = line.indexOf("card") + 7;

				endIndex = line.indexOf("card") + 14;

				String cardName = temp.idToNames(line.substring(beginIndex, endIndex));

				for (int i = 0; i < 30; i++) {

					String cardNameInDeck = friendlyDeck.getCard(i).name;

					double testNumber = friendlyDeck.getCard(i).getValue().doubleValue();

					if (cardName.equals(cardNameInDeck) && testNumber == .25) {

						friendlyDeck.getCard(i).setValue(1);

						lables[i].setOpacity(1);

						remainingCards++;

						oneCardChance.setText("[1]: " + Integer.toString(1 / remainingCards));

						twoCardChance.setText("[2]: " + Integer.toString(1 / remainingCards));

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

						Server server = new Server();

						ArrayList<String> cardsDrawn = friendlyCards;

						Deck dummyDeck = friendlyDeck;

						for (String cardName : friendlyCards) {

							for (int i = 0; i < 30; i++) {

								String cardNameInDeck = friendlyDeck.getCard(i).name;

								double testNumber = friendlyDeck.getCard(i).getValue().doubleValue();

								if (cardName.equals(cardNameInDeck) && testNumber != .25) {

									friendlyDeck.getCard(i).setValue(.25);

									lables[i].setOpacity(.25);

									// remainingCards--;

									// System.out.println(remainingCards);

									break;

								}

							}

							// UpdateChance(remainingCards);

						}

						friendlyCards.clear();

						outPut.clear();

						line = br.readLine();

						if (br.readLine() != null) {

							Thread.sleep(3000);

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

			oneCardChance.setText("this");

			buttom = new AnchorPane();

			buttom.setPrefSize(350, 700);

			buttom.setBackground(null);

			BackgroundImage BackGround = new BackgroundImage(new Image("/Image/BackGround.jpg", 650, 700, false, true),

					BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,

					BackgroundSize.DEFAULT);

			buttom.setBackground(new Background(BackGround));

			Pane p2 = new Pane();

			p2.setPrefSize(280, 100);

			p2.setLayoutX(30);

			p2.setLayoutY(0);

			p2.setBackground(null);

			BackgroundImage BackImage2 = new BackgroundImage(

					new Image("/Image/hearthstone_logo.png", 280, 100, false, true), BackgroundRepeat.REPEAT,

					BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

			p2.setBackground(new Background(BackImage2));

			buttom.getChildren().add(p2);

			Button CreateDeck = new Button("Create");

			CreateDeck.setPrefSize(85, 23);

			CreateDeck.setLayoutX(560);

			CreateDeck.setLayoutY(50);

			buttom.getChildren().add(CreateDeck);

			TabPane tab = new TabPane();

			Tab myDeck = new Tab("MyDeck");

			Tab oppo = new Tab("Enermy Deck");

			Tab grave = new Tab("Grave Yard");

			myDeck.setClosable(false);

			oppo.setClosable(false);

			grave.setClosable(false);

			tab.getTabs().addAll(myDeck, oppo, grave);

			buttom.getChildren().add(tab);

			ScrollPane list = new ScrollPane();

			tab.setPrefHeight(580);

			tab.setPrefWidth(286 + 50);

<<<<<<< HEAD
		        @Override
		        public void handle(ActionEvent event) {
		            String cardname = null;
		        	try {
						deck1.clearDeck();
		            	FileReader reader = new FileReader(deckList.getValue() + ".txt");
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
			
			sort.setOnAction(e -> getChoice(choice));
		
			swap.setOnAction(e -> setNewDeck());
			
			addDeck.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent e1) {
					PrintWriter listwriter;
					if(e1.getCode().equals(KeyCode.ENTER)){
						try {
							listwriter = new PrintWriter(new FileWriter("decklist.txt",true));
							listwriter.println(addDeck.getCharacters().toString());
							listwriter.close();
							deckList.getItems().add(addDeck.getCharacters().toString());
							PrintWriter newWriter = new PrintWriter(addDeck.getCharacters().toString() + ".txt");
							newWriter.close();
							System.out.println("");
							System.out.println("Deck Added!");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						addDeck.clear();
					}
				}
			});
			
			
			
			//System.out.println(friendlyCards.get(0));
			//System.out.println(lables[0].getText());
//			for(int i=0;i<friendlyDeck.getSize();i++) {
//				lables[i].opacityProperty().bind(friendlyDeck.getCard(i));
//			}
			//lables[0].setOpacity(0);
			

			
=======
			tab.setLayoutX(0);
>>>>>>> 1ec05427a1972cfb7c382aac338609d7d26117c3

			tab.setLayoutY(90);

			myDeck.setContent(list);

			TextField DeckName = new TextField();

			// DeckName.setPrefSize(prefWidth, prefHeight);

			TextArea CardsInDeck = new TextArea();

			CardsInDeck.setLayoutX(350);

			CardsInDeck.setLayoutY(100);

			CardsInDeck.setPrefSize(280, 500);

			CardsInDeck.setEditable(false);

			CardsInDeck.setOpacity(0.75);

			CardsInDeck.setVisible(false);

			buttom.getChildren().add(CardsInDeck);

			Button SaveDeck = new Button("Save Deck");

			SaveDeck.setLayoutX(350);

			SaveDeck.setLayoutY(610);

			SaveDeck.setVisible(false);

			buttom.getChildren().add(SaveDeck);

			Button MoveToLeft = new Button("Move to the Left");

			MoveToLeft.setLayoutX(430);

			MoveToLeft.setLayoutY(610);

			MoveToLeft.setVisible(false);

			buttom.getChildren().add(MoveToLeft);

			Button BACK = new Button("Back");

			BACK.setFont(Font.font("Arail", FontWeight.BOLD, 18));

			BACK.setPrefSize(200, 35);

			BACK.setLayoutX(400);

			BACK.setLayoutY(650);

			buttom.getChildren().add(BACK);

			BACK.setVisible(false);

			VBox DeckList = new VBox();

			DeckList.setLayoutX(350);

			DeckList.setLayoutY(100);

			DeckList.setPrefSize(280, 500);

			buttom.getChildren().add(DeckList);

			DeckList.setVisible(true);

			Label CurrentDeck = new Label();

			CurrentDeck.setLayoutY(60);

			CurrentDeck.setLayoutX(525);

			CurrentDeck.setPrefSize(120, 40);

			CurrentDeck.setVisible(false);

			CurrentDeck.setFont(Font.font("Arail", FontWeight.BOLD, 18));

			CurrentDeck.setTextFill(Color.WHITE);

			list.setContent(vb);

			String[] image_name = logReader.createTestNames(0);

			updateLabel(image_name);

			TextField text = new TextField("");

			// Label search = new Label("Add Cards to Deck");

			// Label delete1 = new Label("Remove Cards to Deck");

			Button save = new Button("Save");

			Button remove = new Button("Remove");

			ChoiceBox<String> choice = new ChoiceBox<String>();

			choice.getItems().addAll("Mana Cost", "Armor", "Health", "Damage", "Rarity");

			choice.setLayoutX(560);

			choice.setLayoutY(15);

			choice.setMaxWidth(85);

			save.setLayoutX(340);

			save.setLayoutY(15);

			save.setPrefSize(70, 20);

			remove.setLayoutX(420);

			remove.setLayoutY(15);

			remove.setPrefSize(70, 20);

			remove.setVisible(false);

			text.setVisible(false);

			save.setVisible(false);

			Button sort = new Button("Sort");

			sort.setLayoutX(505);

			sort.setLayoutY(15);

			Button load = new Button("Load Deck");

			load.setLayoutX(385);

			load.setLayoutY(10);

			load.setPrefSize(100, 30);

			text.setLayoutX(340);

			text.setLayoutY(50);

			buttom.getChildren().addAll(save, load, text, remove, choice, sort, CurrentDeck);

			SaveDeck.setOnAction(new EventHandler<ActionEvent>() {

				@Override

				public void handle(ActionEvent event) {

					try {

						// System.out.println(CurrentDeck.getText());

						File read;

						if (System.getProperty("os.name").toLowerCase().contains("win")) {

							read = new File(pcPath + CurrentDeck.getText() + ".txt");

						} else {

							read = new File("/Users/chenjunjie/repository/A8/Heartstone_Deck_Track/Decks/"
									+ CurrentDeck.getText() + ".txt");

						}

						read.delete();

						if (System.getProperty("os.name").toLowerCase().contains("win")) {

							resultFile = new FileWriter(pcPath + CurrentDeck.getText() + ".txt", true);

						} else {

							resultFile = new FileWriter(macPath + CurrentDeck.getText() + ".txt", true);

						}

						writer = new PrintWriter(resultFile);

						Scanner ScanCards = new Scanner(CardsInDeck.getText());

						while (ScanCards.hasNextLine()) {

							writer.println(ScanCards.nextLine());

							writer.flush();

						}

						ScanCards.close();

						// CardsInDeck.setText(null);

						;

					} catch (IOException e) {

						// TODO Auto-generated catch block

						e.printStackTrace();

					}

					Alert alert = new Alert(Alert.AlertType.INFORMATION);

					alert.setTitle("Information");

					alert.setHeaderText("Saved successfully!");

					alert.showAndWait();

					writer.close();

				}

			});

			remove.setOnAction(new EventHandler<ActionEvent>() {

				@Override

				public void handle(ActionEvent event) {

					String[] cards = CardsInDeck.getText().split("\n");

					CardsInDeck.clear();

					int l = 0;

					for (int i = 0; i < cards.length; i++) {

						if (cards[i].equals(text.getText()) && l == 0) {

							l = 1;

						}

						else {

							CardsInDeck.appendText(cards[i] + "\n");

						}

					}

					// CardsInDeck.deleteText(3, 5);

				}

			});

			CreateDeck.setOnAction(new EventHandler<ActionEvent>() {

				@Override

				public void handle(ActionEvent event) {

					String name = "";

					TextInputDialog dialog = new TextInputDialog();

					dialog.setTitle("Create a new Deck");

					dialog.setHeaderText("What's the name of new Deck?");

					dialog.setContentText("Name: ");

					Optional result = dialog.showAndWait();

					if (result.isPresent() && !result.get().toString().equals("") && result.get().toString() != null) {

						name = result.get().toString();

						try {

							String fileName;

							if (System.getProperty("os.name").toLowerCase().contains("win")) {

								fileName = "/Decks/" + name + ".txt";

							} else {

								fileName = "/Users/chenjunjie/repository/A8/Heartstone_Deck_Track/Decks/" + name
										+ ".txt";

							}

							System.out.println(fileName);

							File newfile = new File(fileName);

							newfile.createNewFile();

						}

						catch (IOException e) {

							e.printStackTrace();

						}

						String cardname = null;

						if (DeckList.getChildren() != null) {

							ObservableList<Node> NeedToDelete = DeckList.getChildren();

							DeckList.getChildren().removeAll(NeedToDelete);

						}

						int numberOfFiles = new File("Decks").listFiles().length;

						File dirFile = new File("Decks");

						String[] fileList = dirFile.list();

						deckName = new Button[numberOfFiles];

						for (int k = 0; k < fileList.length; k++) {

							deckName[k] = new Button(fileList[k].replace(".txt", ""));

							deckName[k].setId(k + "");

							deckName[k].setPrefSize(280, 50);

							deckName[k].setOpacity(0.7);

							deckName[k].setFont(Font.font("Arail", FontWeight.BOLD, 16));

							DeckList.getChildren().add(deckName[k]);

							deckName[k].setOnAction(new EventHandler<ActionEvent>() {

								@Override

								public void handle(ActionEvent event) {

									load.setVisible(false);

									CreateDeck.setVisible(false);

									save.setVisible(true);

									SaveDeck.setVisible(true);

									BACK.setVisible(true);

									text.setVisible(true);

									MoveToLeft.setVisible(true);

									remove.setVisible(true);

									CurrentDeck.setVisible(true);

									CardsInDeck.clear();

									CardsInDeck.setVisible(true);

									DeckList.setVisible(false);

									Scanner scan = new Scanner(event.toString());

									scan.useDelimiter(",");

									String temp = scan.next();

									scan.close();

									CurrentDeck.setText(deckName[Integer.parseInt(temp.substring(42))].getText());

									String PathName;

									if (System.getProperty("os.name").toLowerCase().contains("win")) {

										PathName = "/Decks/" + deckName[Integer.parseInt(temp.substring(42))].getText()

												+ ".txt";

									} else {

										PathName = "/Users/chenjunjie/repository/A8/Heartstone_Deck_Track/Decks/"
												+ deckName[Integer.parseInt(temp.substring(42))].getText()

												+ ".txt";

									}

									System.out.println(PathName);

									File ShowDeck = new File(PathName);

									Scanner scan2;

									try {

										scan2 = new Scanner(ShowDeck);

										while (scan2.hasNextLine()) {

											CardsInDeck.appendText(scan2.nextLine() + "\n");

										}

									} catch (FileNotFoundException e) {

										// TODO Auto-generated catch block

										e.printStackTrace();

									}

								}

							});

							System.out.println(fileList[k]);

						}

					}

				}

			});

			BACK.setOnAction(new EventHandler<ActionEvent>() {

				@Override

				public void handle(ActionEvent event) {

					BACK.setVisible(false);

					save.setVisible(false);

					CreateDeck.setVisible(true);

					load.setVisible(true);

					text.setVisible(false);

					CurrentDeck.setVisible(false);

					remove.setVisible(false);

					MoveToLeft.setVisible(false);

					if (CardsInDeck.isVisible() == true) {

						SaveDeck.setVisible(false);

						CardsInDeck.setVisible(false);

						CardsInDeck.setText(null);

						DeckList.setVisible(true);

					} else {

						SaveDeck.setVisible(true);

						CardsInDeck.setVisible(true);

						DeckList.setVisible(false);

						CardsInDeck.setText(null);

					}

				}

			});

			/*
			 * 
			 * text.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
			 * 
			 * 
			 * 
			 * @Override public void handle(KeyEvent e) { if
			 * 
			 * (e.getCode().equals(KeyCode.ENTER)) { //
			 * 
			 * Server.createCard(text.getCharacters().toString());
			 * 
			 * deck1.addCard(Server.createCard(text.getCharacters().toString()));
			 * 
			 * System.out.println(" "); for (int abc = 0; abc < deck1.getSize(); abc++) {
			 * 
			 * System.out.println(deck1.getCard(abc).Name()); } System.out.println(" ");
			 * 
			 * text.clear(); } } });
			 * 
			 */

			/*
			 * 
			 * delete.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
			 * 
			 * 
			 * 
			 * @Override public void handle(KeyEvent e1) { if
			 * 
			 * (e1.getCode().equals(KeyCode.ENTER)) { //
			 * 
			 * Server.createCard(text.getCharacters().toString());
			 * 
			 * deck1.removeCard(delete.getCharacters().toString()); System.out.println(" ");
			 * 
			 * for (int abc1 = 0; abc1 < deck1.getSize(); abc1++) {
			 * 
			 * System.out.println(deck1.getCard(abc1).Name()); } System.out.println(" ");
			 * 
			 * delete.clear(); } } });
			 * 
			 */

			save.setOnAction(new EventHandler<ActionEvent>() {

				@Override

				public void handle(ActionEvent event) {

					if (text.getText() != null && !text.getText().equals("")) {

						CardsInDeck.appendText(text.getText() + "\n");

					}

				}

			});

			MoveToLeft.setOnAction(new EventHandler<ActionEvent>() {

				@Override

				public void handle(ActionEvent event) {

					friendlyDeck.clearDeck();

					String[] cardsname = CardsInDeck.getText().split("\n");

					if (cardsname.length < 30) {

						Alert alert = new Alert(Alert.AlertType.INFORMATION);

						alert.setTitle("Alert");

						alert.setHeaderText("You can't create a deck less than 30 cards");

						alert.showAndWait();

					} else {

						for (int i = 0; i < 30; i++) {

							String t = cardsname[i];

							// System.out.println(t);

							if (!t.equals("") && t != null) {

								Card card = server.createCard(t);

								friendlyDeck.addCard(card);

								System.out.println(friendlyDeck.getCard(i).name);

							}

						}

						updateLabel();

					}

				}

			});

			load.setOnAction(new EventHandler<ActionEvent>() {

				@Override

				public void handle(ActionEvent event) {

					String cardname = null;

					if (DeckList.getChildren() != null) {

						ObservableList<Node> NeedToDelete = DeckList.getChildren();

						DeckList.getChildren().removeAll(NeedToDelete);

					}

					int numberOfFiles = new File("Decks").listFiles().length;

					File dirFile = new File("Decks");

					String[] fileList = dirFile.list();

					deckName = new Button[numberOfFiles];

					for (int k = 0; k < fileList.length; k++) {

						deckName[k] = new Button(fileList[k].replace(".txt", ""));

						deckName[k].setId(k + "");

						deckName[k].setPrefSize(280, 50);

						deckName[k].setOpacity(0.7);

						deckName[k].setFont(Font.font("Arail", FontWeight.BOLD, 16));

						DeckList.getChildren().add(deckName[k]);

						deckName[k].setOnAction(new EventHandler<ActionEvent>() {

							@Override

							public void handle(ActionEvent event) {

								load.setVisible(false);

								save.setVisible(true);

								CreateDeck.setVisible(false);

								SaveDeck.setVisible(true);

								BACK.setVisible(true);

								text.setVisible(true);

								MoveToLeft.setVisible(true);

								remove.setVisible(true);

								CurrentDeck.setVisible(true);

								CardsInDeck.clear();

								CardsInDeck.setVisible(true);

								DeckList.setVisible(false);

								Scanner scan = new Scanner(event.toString());

								scan.useDelimiter(",");

								String temp = scan.next();

								scan.close();

								CurrentDeck.setText(deckName[Integer.parseInt(temp.substring(42))].getText());

								// String PathName = "/Decks/" +
								// deckName[Integer.parseInt(temp.substring(42))].getText()

								// + ".txt";

								String PathName;

								if (System.getProperty("os.name").toLowerCase().contains("win")) {

									PathName = "/Decks/" + deckName[Integer.parseInt(temp.substring(42))].getText()

											+ ".txt";

								} else {

									PathName = "/Users/chenjunjie/repository/A8/Heartstone_Deck_Track/Decks/"
											+ deckName[Integer.parseInt(temp.substring(42))].getText()

											+ ".txt";

								}

								File ShowDeck = new File(PathName);

								Scanner scan2;

								try {

									scan2 = new Scanner(ShowDeck);

									while (scan2.hasNextLine()) {

										CardsInDeck.appendText(scan2.nextLine() + "\n");

									}

								} catch (FileNotFoundException e) {

									// TODO Auto-generated catch block

									e.printStackTrace();

								}

							}

						});

						System.out.println(fileList[k]);

					}

				}

			});

			sort.setOnAction(e -> getChoice(choice));

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

			primaryStage.setTitle("HearthStone Tracker");

			// primaryStage.getIcons().add(new Image("Image/icon.PNG"));

			// primaryStage.initStyle(StageStyle.UNDECORATED);

			primaryStage.show();

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	public static void main(String[] args) {

		lables = new Label[30];

		friendlyDeck = new Deck();

		deck1 = new Deck();

		String[] deckCards = logReader.createTestNames(0);

		Server server = new Server();

		Card tempCard;

		for (String cardName : deckCards) {

			tempCard = server.createCard(cardName);

			friendlyDeck.addCard(tempCard);

		}

		Log_Processor logProcessor = new Log_Processor();

		logProcessor.start();

		while (true) {

			launch(args);

		}

	}

	public void put_large_image(String[] image_name) {

		// System.out.println(("Image/"+image_name[0]+".png").replaceAll("\\s+",""));

		for (int i = 0; i < friendlyDeck.getSize(); i++) {

			// large_picture[i] = new Image(("Image/" + image_name[i] +

			// ".png").replaceAll("\\s+", ""));

			Card crd = friendlyDeck.getCard(i);

			if (crd.barIcon != null) {

				java.awt.Image img = crd.largeIcon.getImage();

				BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null),

						BufferedImage.TYPE_INT_ARGB);

				Graphics2D bGr = bimage.createGraphics();

				bGr.drawImage(img, 0, 0, null);

				bGr.dispose();

				large_picture[i] = SwingFXUtils.toFXImage(bimage, null);

			} else {

				large_picture[i] = new Image(("Image/" + "null.png").replaceAll("\\s+", ""));

			}

		}

	}

	public void put_small_image(String[] image_name) {

		for (int i = 0; i < friendlyDeck.getSize(); i++) {

			// small_picture[i] = new Image(("Image/" + image_name[i] +

			// "(s).png").replaceAll("\\s+", ""));

			Card crd = friendlyDeck.getCard(i);

			if (crd.barIcon != null) {

				java.awt.Image img = crd.barIcon.getImage();

				BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null),

						BufferedImage.TYPE_INT_ARGB);

				Graphics2D bGr = bimage.createGraphics();

				bGr.drawImage(img, 0, 0, null);

				bGr.dispose();

				small_picture[i] = SwingFXUtils.toFXImage(bimage, null);

			} else {

				small_picture[i] = new Image(("Image/" + "nullBar.png").replaceAll("\\s+", ""));

			}

		}

	}

	private void getChoice(ChoiceBox<String> choice) {

		String selection = choice.getValue();

		switch (selection) {

		case "Mana Cost":

			friendlyDeck.sortDeckMana();

			break;

		case "Armor":

			friendlyDeck.sortDeckArmor();

			break;

		case "Health":

			friendlyDeck.sortDeckHealth();

			break;

		case "Rarity":

			friendlyDeck.sortDeckRarity();

			break;

		case "Damage":

			friendlyDeck.sortDeckDamage();

			break;

		default:

			break;

		}

		System.out.println(" ");

		for (int abc1 = 0; abc1 < friendlyDeck.getSize(); abc1++) {

			System.out.println(friendlyDeck.getCard(abc1).Name());

		}

		System.out.println(" ");

		updateLabel();

		return;

	}

	private void setNewDeck() {

		if (deck1.getSize() == 30) {

			friendlyDeck.clearDeck();

			for (int abc1 = 0; abc1 < deck1.getSize(); abc1++) {

				friendlyDeck.addCard(deck1.getCard(abc1));

			}

			updateLabel();

			for (int abc1 = 0; abc1 < friendlyDeck.getSize(); abc1++) {

				System.out.println(friendlyDeck.getCard(abc1).Name());

			}

			System.out.println("");

		}

		return;

	}

	private void updateLabel() {

		String[] imageNames = new String[30];

		for (int i = 0; i < friendlyDeck.getSize(); i++) {

			imageNames[i] = friendlyDeck.getCard(i).Name();

		}

		// put_large_image(imageNames);

		put_small_image(imageNames);

		for (int i = 0; i < 30; i++) {

			lables[i].setPrefSize(286 + 30, 50);

			BackgroundImage BackImage = new BackgroundImage((small_picture[i]), BackgroundRepeat.REPEAT,

					BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

			lables[i].setBackground(new Background(BackImage));

		}

	}

	private void updateLabel(String[] imageNames) {

		// put_large_image(imageNames);

		put_small_image(imageNames);

		for (int i = 0; i < 30; i++) {

			lables[i] = new Label();

			lables[i].setPrefSize(286 + 30, 50);

			BackgroundImage BackImage = new BackgroundImage((small_picture[i]), BackgroundRepeat.REPEAT,
					BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
			lables[i].setBackground(new Background(BackImage));

			vb.getChildren().add(lables[i]);

		}

	}
	
	private int drawProbability(Card selectedCard) {
		int total = friendlyDeck.getSize();
		int count = 0;
		
		for(int i = 0; i < total; i++) {
			Card crd = friendlyDeck.getCard(i);
			
			if(selectedCard.id == crd.id)
				count++;
		}
		
		return count / total;
		
	}

}
