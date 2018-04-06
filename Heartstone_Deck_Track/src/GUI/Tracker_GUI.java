package GUI;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import deck.Card;
import deck.Deck;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import logReader.Log_Reader;
import server.Server;
import javafx.embed.swing.SwingFXUtils;
import java.io.File;
import java.io.FileWriter;
import java.util.Optional;
import java.util.Scanner;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


@SuppressWarnings("restriction")
public class Tracker_GUI extends Application {
	
	private static final int OPPCARDCOUNTMAX=31;
	private static final int FRIENDLYCARDCOUNTMAX=30;
	Background black = new Background(new BackgroundFill(Paint.valueOf("#000000"), CornerRadii.EMPTY, Insets.EMPTY));// red
	Background white = new Background(new BackgroundFill(Paint.valueOf("#FFFFFF"), CornerRadii.EMPTY, Insets.EMPTY));// ye
	LargePic lp = new LargePic();
	static Image[] large_picture_friendly = new Image[FRIENDLYCARDCOUNTMAX];
	static Image[] small_picture_friendly = new Image[FRIENDLYCARDCOUNTMAX];
	static Image[] large_picture_opp = new Image[OPPCARDCOUNTMAX];
	static Image[] small_picture_opp = new Image[OPPCARDCOUNTMAX];
	static Label[] lablesMyDeck;
	static Label[] lablesOppDeck;
	Scene scene;
	AnchorPane buttom;
	public static Deck deck1;
	private static int count;
	private static boolean stillOn = true;
	private static String pcPath="Decks/";
	private static String macPath="/Users/chenjunjie/repository/A8/Heartstone_Deck_Track/Decks/";
	private static String macLogAddress = "/Applications/Hearthstone/Logs/Power.log";
	private static String pcLogAddress = "C:/Program Files (x86)/Hearthstone/Logs/Power.log";
	private static ArrayList<String> friendlyCards = new ArrayList<String>();
	private static ArrayList<String> opponentCards = new ArrayList<String>();
	private static ArrayList<String> outPut = new ArrayList<String>();
	public static Deck friendlyDeck;
	public static Deck oppDeck;
	private static Log_Reader logReader = new Log_Reader();
	static VBox vbYourCards = new VBox();
	static VBox vbOppCards = new VBox();
	FileWriter resultFile;
	PrintWriter writer;
	Button[] deckName;
	static Server server = new Server();

	public static Label twoCardChance = new Label("[2]: 6.6%");
	static int remainingCards = 30;
	private static String oneCardString = "null";
	static Label oneCardChance = new Label(oneCardString);

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
				remainingCards = FRIENDLYCARDCOUNTMAX;
				outPut.clear();
				friendlyCards.clear();
				opponentCards.clear();
				oppDeck.clearDeck();
				for (int i = 0; i < FRIENDLYCARDCOUNTMAX; i++) {
					lablesMyDeck[i].setOpacity(1);
					lablesOppDeck[i].setBackground(null);
					
				}
				lablesOppDeck[OPPCARDCOUNTMAX-1].setBackground(null);
				/*
				 * 
				 * 
				 * mark
				 * 
				 */
				// oneCardChance.setOpacity(1);

				// twoCardChance.setText("[2]: "+Integer.toString(1/remainingCards));

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
				for (int i = 0; i < FRIENDLYCARDCOUNTMAX; i++) {
					String cardNameInDeck = friendlyDeck.getCard(i).name;
					double testNumber = friendlyDeck.getCard(i).getValue().doubleValue();
					if (cardName.equals(cardNameInDeck) && testNumber == .25) {

						friendlyDeck.getCard(i).setValue(1);
						lablesMyDeck[i].setOpacity(1);
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
					while (line == null) {//if at end of log file
						for (String s : outPut)
							System.out.println(s);
						Card tempCard;
						ArrayList<String> cardsDrawn = friendlyCards;
						Deck dummyDeck = friendlyDeck;
						for (String cardName : friendlyCards) {
							for (int i = 0; i < FRIENDLYCARDCOUNTMAX; i++) {
								String cardNameInDeck = friendlyDeck.getCard(i).name;
								double testNumber = friendlyDeck.getCard(i).getValue().doubleValue();
								if (cardName.equals(cardNameInDeck) && testNumber != .25) {

									friendlyDeck.getCard(i).setValue(.25);
									lablesMyDeck[i].setOpacity(.25);
									updateLabel();
									 remainingCards--;
									// System.out.println(remainingCards);
									break;
								}
							}
							// UpdateChance(remainingCards);
						}
						if(opponentCards.size()>0) {
							for (int x=0; x<opponentCards.size();x++) {
								tempCard= Server.createCard(opponentCards.get(x));
								oppDeck.addCard(tempCard);
							}
							
							updateOppLabel();
						}
						friendlyCards.clear();
						outPut.clear();
						opponentCards.clear();
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
			buttom.setPrefSize(450, 700);
			buttom.setBackground(null);
			
			  BackgroundImage BackGround = new BackgroundImage(new Image("Image/BackGround.jpg", 750, 700, false, true),
		
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

			Button CreateDeck=new Button("Create");
			CreateDeck.setPrefSize(85, 23);
			CreateDeck.setLayoutX(560);
			CreateDeck.setLayoutY(50);
			buttom.getChildren().add(CreateDeck);
			
			

			TabPane tab = new TabPane();
			Tab myDeck = new Tab("MyDeck");
			Tab oppo = new Tab("Enemy Deck");
			myDeck.setClosable(false);
			oppo.setClosable(false);
			tab.getTabs().addAll(myDeck, oppo);
			buttom.getChildren().add(tab);

			ScrollPane listMyDeck = new ScrollPane();
			tab.setPrefHeight(580);
			tab.setPrefWidth(286 + 110);
			tab.setLayoutX(0);
			tab.setLayoutY(90);
			myDeck.setContent(listMyDeck);
			
			ScrollPane listoppDeck = new ScrollPane();
			tab.setPrefHeight(580);
			tab.setPrefWidth(286 + 110);
			tab.setLayoutX(0);
			tab.setLayoutY(90);
			oppo.setContent(listoppDeck);

			TextField DeckName = new TextField();
			// DeckName.setPrefSize(prefWidth, prefHeight);

			TextArea CardsInDeck = new TextArea();
			CardsInDeck.setLayoutX(450);
			CardsInDeck.setLayoutY(130);
			CardsInDeck.setPrefSize(280, 470);
			CardsInDeck.setEditable(false);
			CardsInDeck.setOpacity(0.75);
			CardsInDeck.setVisible(false);
			buttom.getChildren().add(CardsInDeck);

			Button SaveDeck = new Button("Save Deck");
			SaveDeck.setLayoutX(450);
			SaveDeck.setLayoutY(610);
			SaveDeck.setVisible(false);
			buttom.getChildren().add(SaveDeck);

			Button MoveToLeft = new Button("Move to the Left");
			MoveToLeft.setLayoutX(530);
			MoveToLeft.setLayoutY(610);
			MoveToLeft.setVisible(false);
			buttom.getChildren().add(MoveToLeft);

			Button BACK = new Button("Back");
			BACK.setFont(Font.font("Arail", FontWeight.BOLD, 18));
			BACK.setPrefSize(200, 35);
			BACK.setLayoutX(500);
			BACK.setLayoutY(650);
			buttom.getChildren().add(BACK);
			BACK.setVisible(false);

			VBox DeckList = new VBox();
			DeckList.setLayoutX(450);
			DeckList.setLayoutY(100);
			DeckList.setPrefSize(280, 580);
			buttom.getChildren().add(DeckList);
			DeckList.setVisible(true);

			Label CurrentDeck = new Label();
			CurrentDeck.setLayoutY(15);
			CurrentDeck.setLayoutX(525);
			CurrentDeck.setPrefSize(120, 140);
			CurrentDeck.setVisible(false);
			CurrentDeck.setFont(Font.font("Arail", FontWeight.BOLD, 18));
			CurrentDeck.setTextFill(Color.WHITE);

			listMyDeck.setContent(vbYourCards);
			listoppDeck.setContent(vbOppCards);
			String[] image_name = logReader.createTestNames(0);

			updateLabel(image_name);
			
			//String of all cards
	        String[] options = {"Acidic Swamp Ooze",
	        		"Ancestral Healing",
	        		"Animal Companion",
	        		"Arcane Explosion",
	        		"Arcane Intellect",
	        		"Arcane Missiles",
	        		"Arcane Shot",
	        		"Arcanite Reaper",
	        		"Archmage",
	        		"Assassin's Blade",
	        		"Assassinate",
	        		"Backstab",
	        		"Blessing of Kings",
	        		"Blessing of Might",
	        		"Bloodfen Raptor",
	        		"Bloodlust",
	        		"Bluegill Warrior",
	        		"Booty Bay Bodyguard",
	        		"Boulderfist Ogre",
	        		"Charge",
	        		"Chillwind Yeti",
	        		"Claw",
	        		"Cleave",
	        		"Consecration",
	        		"Core Hound",
	        		"Corruption",
	        		"Dalaran Mage",
	        		"Darkscale Healer",
	        		"Deadly Poison",
	        		"Divine Spirit",
	        		"Dragonling Mechanic",
	        		"Drain Life",
	        		"Dread Infernal",
	        		"Elven Archer",
	        		"Execute",
	        		"Fan of Knives",
	        		"Fiery War Axe",
	        		"Fire Elemental",
	        		"Fireball",
	        		"Flamestrike",
	        		"Flametongue Totem",
	        		"Frost Nova",
	        		"Frost Shock",
	        		"Frostbolt",
	        		"Frostwolf Grunt",
	        		"Frostwolf Warlord",
	        		"Gnomish Inventor",
	        		"Goldshire Footman",
	        		"Grimscale Oracle",
	        		"Guardian of Kings",
	        		"Gurubashi Berserker",
	        		"Hammer of Wrath",
	        		"Hand of Protection",
	        		"Healing Touch",
	        		"Hellfire",
	        		"Heroic Strike",
	        		"Hex",
	        		"Holy Light",
	        		"Holy Nova",
	        		"Holy Smite",
	        		"Houndmaster",
	        		"Humility",
	        		"Hunter's Mark",
	        		"Innervate",
	        		"Ironbark Protector",
	        		"Ironforge Rifleman",
	        		"Ironfur Grizzly",
	        		"Kill Command",
	        		"Kobold Geomancer",
	        		"Kor'kron Elite",
	        		"Light's Justice",
	        		"Lord of the Arena",
	        		"Magma Rager",
	        		"Mark of the Wild",
	        		"Mind Blast",
	        		"Mind Control",
	        		"Mind Vision",
	        		"Mirror Image",
	        		"Moonfire",
	        		"Mortal Coil",
	        		"Multi-Shot",
	        		"Murloc Raider",
	        		"Murloc Tidehunter",
	        		"Nightblade",
	        		"Northshire Cleric",
	        		"Novice Engineer",
	        		"Oasis Snapjaw",
	        		"Ogre Magi",
	        		"Polymorph",
	        		"Power Word: Shield",
	        		"Raid Leader",
	        		"Razorfen Hunter",
	        		"Reckless Rocketeer",
	        		"River Crocolisk",
	        		"Rockbiter Weapon",
	        		"Sacrificial Pact",
	        		"Sap",
	        		"Savage Roar",
	        		"Sen'jin Shieldmasta",
	        		"Shadow Bolt",
	        		"Shadow Word: Death",
	        		"Shadow Word: Pain",
	        		"Shattered Sun Cleric",
	        		"Shield Block",
	        		"Shiv",
	        		"Silverback Patriarch",
	        		"Sinister Strike",
	        		"Soulfire",
	        		"Sprint",
	        		"Starfire",
	        		"Starving Buzzard",
	        		"Stonetusk Boar",
	        		"Stormpike Commando",
	        		"Stormwind Champion",
	        		"Stormwind Knight",
	        		"Succubus",
	        		"Swipe",
	        		"Timber Wolf",
	        		"Totemic Might",
	        		"Tracking",
	        		"Truesilver Champion",
	        		"Tundra Rhino",
	        		"Vanish",
	        		"Voidwalker",
	        		"Voodoo Doctor",
	        		"War Golem",
	        		"Warsong Commander",
	        		"Water Elemental",
	        		"Whirlwind",
	        		"Wild Growth",
	        		"Windfury",
	        		"Windspeaker",
	        		"Wolfrider",
	        		"A Light in the Darkness",
	        		"Aberrant Berserker",
	        		"Abusive Sergeant",
	        		"Abyssal Enforcer",
	        		"Acherus Veteran",
	        		"Acolyte of Agony",
	        		"Acolyte of Pain",
	        		"Adaptation",
	        		"Air Elemental",
	        		"Alleycat",
	        		"Am'gam Rager",
	        		"Amani Berserker",
	        		"Ancient Brewmaster",
	        		"Ancient of Blossoms",
	        		"Animated Berserker",
	        		"Arathi Weaponsmith",
	        		"Arcane Anomaly",
	        		"Arcane Artificer",
	        		"Arcanologist",
	        		"Arcanosmith",
	        		"Argent Protector",
	        		"Argent Squire",
	        		"Backstreet Leper",
	        		"Barkskin",
	        		"Battle Rage",
	        		"Bearshark",
	        		"Beckoner of Evil",
	        		"Benevolent Djinn",
	        		"Betrayal",
	        		"Big-Time Racketeer",
	        		"Bilefin Tidehunter",
	        		"Binding Heal",
	        		"Bladed Cultist",
	        		"Blastcrystal Potion",
	        		"Blessing of Wisdom",
	        		"Blood Imp",
	        		"Bloodhoof Brave",
	        		"Bloodsail Raider",
	        		"Bloodworm",
	        		"Blowgill Sniper",
	        		"Bog Creeper",
	        		"Boisterous Bard",
	        		"Bone Baron",
	        		"Bonemare",
	        		"Breath of Sindragosa",
	        		"Brrrloc",
	        		"C'Thun's Chosen",
	        		"Call in the Finishers",
	        		"Candleshot",
	        		"Carrion Grub",
	        		"Cave Hydra",
	        		"Cavern Shinyfinder",
	        		"Cheat Death",
	        		"Chillblade Champion",
	        		"Circle of Healing",
	        		"Cloaked Huntress",
	        		"Cobalt Scalebane",
	        		"Cold Blood",
	        		"Coldwraith",
	        		"Cone of Cold",
	        		"Corrosive Sludge",
	        		"Crackling Razormaw",
	        		"Cruel Taskmaster",
	        		"Crushing Hand",
	        		"Cryomancer",
	        		"Crypt Lord",
	        		"Crystalweaver",
	        		"Cult Apothecary",
	        		"Cult Master",
	        		"Cursed Disciple",
	        		"Daring Reporter",
	        		"Dark Arakkoa",
	        		"Dark Conviction",
	        		"Dark Iron Dwarf",
	        		"Dark Pact",
	        		"Darkshire Alchemist",
	        		"Darkshire Councilman",
	        		"Deadly Fork",
	        		"Deadly Shot",
	        		"Deadscale Knight",
	        		"Deathspeaker",
	        		"Defias Ringleader",
	        		"Demonfire",
	        		"Dire Mole",
	        		"Dire Wolf Alpha",
	        		"Divine Strength",
	        		"Dragonslayer",
	        		"Drain Soul",
	        		"Drakkari Defender",
	        		"Dread Corsair",
	        		"Druid of the Claw",
	        		"Drygulch Jailor",
	        		"Drywhisker Armorer",
	        		"Duskboar",
	        		"Dust Devil",
	        		"Earth Shock",
	        		"Earthen Ring Farseer",
	        		"Eggnapper",
	        		"Elder Longneck",
	        		"Eldritch Horror",
	        		"Emerald Reaver",
	        		"Enchanted Raven",
	        		"Eviscerate",
	        		"Evolved Kobold",
	        		"Explosive Trap",
	        		"Eye for an Eye",
	        		"Faceless Behemoth",
	        		"Faceless Summoner",
	        		"Faerie Dragon",
	        		"Fallen Sun Cleric",
	        		"Fen Creeper",
	        		"Feral Rage",
	        		"Fiery Bat",
	        		"Fire Fly",
	        		"Fire Plume Phoenix",
	        		"Firelands Portal",
	        		"Flame Geyser",
	        		"Flame Imp",
	        		"Flamewreathed Faceless",
	        		"Flanking Strike",
	        		"Flesheating Ghoul",
	        		"Fool's Bane",
	        		"Forge of Souls",
	        		"Forked Lightning",
	        		"Freezing Potion",
	        		"Freezing Trap",
	        		"Friendly Bartender",
	        		"Frost Elemental",
	        		"Frozen Clone",
	        		"Fungal Enchanter",
	        		"Fungalmancer",
	        		"Gadgetzan Socialite",
	        		"Gemstudded Golem",
	        		"Giant Mastodon",
	        		"Giant Wasp",
	        		"Gilded Gargoyle",
	        		"Glacial Shard",
	        		"Gnash",
	        		"Grave Shambler",
	        		"Green Jelly",
	        		"Grievous Bite",
	        		"Grim Necromancer",
	        		"Grimestreet Outfitter",
	        		"Grimestreet Smuggler",
	        		"Grimscale Chum",
	        		"Grimy Gadgeteer",
	        		"Grook Fu Master",
	        		"Grotesque Dragonhawk",
	        		"Guild Recruiter",
	        		"Hallucination",
	        		"Harvest Golem",
	        		"Healing Rain",
	        		"Hired Gun",
	        		"Hoarding Dragon",
	        		"Hooded Acolyte",
	        		"Hot Spring Guardian",
	        		"Howlfiend",
	        		"Hozen Healer",
	        		"Hydrologist",
	        		"Hyldnir Frostrider",
	        		"I Know a Guy",
	        		"Ice Barrier",
	        		"Ice Fishing",
	        		"Igneous Elemental",
	        		"Infested Tauren",
	        		"Inner Fire",
	        		"Inner Rage",
	        		"Iron Hide",
	        		"Ironbeak Owl",
	        		"Ironforge Portal",
	        		"Ironwood Golem",
	        		"Jade Behemoth",
	        		"Jade Blossom",
	        		"Jade Chieftain",
	        		"Jade Lightning",
	        		"Jade Shuriken",
	        		"Jade Spirit",
	        		"Jade Swarmer",
	        		"Jeweled Macaw",
	        		"Jungle Panther",
	        		"Kabal Chemist",
	        		"Kabal Lackey",
	        		"Kabal Songstealer",
	        		"Kabal Talonpriest",
	        		"Kara Kazham!",
	        		"Kindly Grandmother",
	        		"Kobold Apprentice",
	        		"Kobold Hermit",
	        		"Kobold Librarian",
	        		"Kooky Chemist",
	        		"Lakkari Felhound",
	        		"Leeching Poison",
	        		"Leper Gnome",
	        		"Lightning Bolt",
	        		"Lightspawn",
	        		"Loot Hoarder",
	        		"Lost in the Jungle",
	        		"Mad Bomber",
	        		"Malchezaar's Imp",
	        		"Mana Wyrm",
	        		"Mark of Nature",
	        		"Mark of Y'Shaarj",
	        		"Mark of the Lotus",
	        		"Medivh's Valet",
	        		"Menagerie Magician",
	        		"Menagerie Warden",
	        		"Mirror Entity",
	        		"Mistress of Mixtures",
	        		"Mogu'shan Warden",
	        		"N'Zoth's First Mate",
	        		"Naga Corsair",
	        		"Naturalize",
	        		"Necrotic Geist",
	        		"Nerubian Prophet",
	        		"Nesting Roc",
	        		"Netherspite Historian",
	        		"Night Howler",
	        		"Nightbane Templar",
	        		"Noble Sacrifice",
	        		"Oaken Summons",
	        		"On the Hunt",
	        		"Ornery Direhorn",
	        		"Pantry Spider",
	        		"Plated Beetle",
	        		"Play Dead",
	        		"Polluted Hoarder",
	        		"Pompous Thespian",
	        		"Possessed Villager",
	        		"Potion of Heroism",
	        		"Potion of Madness",
	        		"Power Word: Tentacles",
	        		"Power of the Wild",
	        		"Priest of the Feast",
	        		"Priestess of Elune",
	        		"Primal Fusion",
	        		"Primalfin Lookout",
	        		"Psionic Probe",
	        		"Psych-o-Tron",
	        		"Pterrordax Hatchling",
	        		"Public Defender",
	        		"Purify",
	        		"Radiant Elemental",
	        		"Raging Worgen",
	        		"Rampage",
	        		"Ravaging Ghoul",
	        		"Ravasaur Runt",
	        		"Raven Familiar",
	        		"Ravenous Pterrordax",
	        		"Razorpetal Lasher",
	        		"Razorpetal Volley",
	        		"Red Mana Wyrm",
	        		"Redemption",
	        		"Repentance",
	        		"Righteous Protector",
	        		"Rockpool Hunter",
	        		"Runic Egg",
	        		"Sabretooth Stalker",
	        		"Sanguine Reveler",
	        		"Sated Threshadon",
	        		"Scarlet Crusader",
	        		"Scavenging Hyena",
	        		"Sense Demons",
	        		"Sewer Crawler",
	        		"Shadow Ascendant",
	        		"Shadow Rager",
	        		"Shadow Strike",
	        		"Shadowstep",
	        		"Shaky Zipgunner",
	        		"Shatter",
	        		"Shieldbearer",
	        		"Shifting Scroll",
	        		"Shimmering Tempest",
	        		"Shroom Brewer",
	        		"Silence",
	        		"Silver Hand Knight",
	        		"Silver Vanguard",
	        		"Silvermoon Guardian",
	        		"Silvermoon Portal",
	        		"Skelemancer",
	        		"Slam",
	        		"Sleepy Dragon",
	        		"Smuggler's Crate",
	        		"Smuggler's Run",
	        		"Sneaky Devil",
	        		"Snipe",
	        		"Snowflipper Penguin",
	        		"Sorcerer's Apprentice",
	        		"Soul of the Forest",
	        		"Southsea Deckhand",
	        		"Southsea Squidface",
	        		"Spawn of N'Zoth",
	        		"Spellbreaker",
	        		"Spellweaver",
	        		"Spirit Claws",
	        		"Spirit Lash",
	        		"Spiteful Smith",
	        		"Squirming Tentacle",
	        		"Stand Against Darkness",
	        		"Stegodon",
	        		"Stitched Tracker",
	        		"Stoneskin Basilisk",
	        		"Stormcrack",
	        		"Stormforged Axe",
	        		"Stormwatcher",
	        		"Stranglethorn Tiger",
	        		"Street Trickster",
	        		"Streetwise Investigator",
	        		"Stubborn Gastropod",
	        		"Sudden Betrayal",
	        		"Summoning Portal",
	        		"Sunborne Val'kyr",
	        		"Swashburglar",
	        		"Tainted Zealot",
	        		"Tanaris Hogchopper",
	        		"Tar Creeper",
	        		"Tar Lord",
	        		"Tar Lurker",
	        		"Tauren Warrior",
	        		"Temple Enforcer",
	        		"Tentacle of N'Zoth",
	        		"Thoughtsteal",
	        		"Thrallmar Farseer",
	        		"Thunder Lizard",
	        		"Tidal Surge",
	        		"Toothy Chest",
	        		"Tortollan Forager",
	        		"Tortollan Shellraiser",
	        		"Toxic Sewer Ooze",
	        		"Trogg Gloomeater",
	        		"Tuskarr Fisherman",
	        		"Twilight Elder",
	        		"Twilight Flamecaller",
	        		"Twilight Geomancer",
	        		"Twisted Worgen",
	        		"Ultrasaur",
	        		"Unbound Elemental",
	        		"Unidentified Elixir",
	        		"Unidentified Shield",
	        		"Unleash the Hounds",
	        		"Usher of Souls",
	        		"Venomancer",
	        		"Venture Co. Mercenary",
	        		"Verdant Longneck",
	        		"Violet Illusionist",
	        		"Violet Wurm",
	        		"Volatile Elemental",
	        		"Vryghoul",
	        		"Vulgar Homunculus",
	        		"Wax Elemental",
	        		"Webweave",
	        		"Wicked Skeleton",
	        		"Wicked Witchdoctor",
	        		"Windfury Harpy",
	        		"Wisp",
	        		"Worgen Greaser",
	        		"Worgen Infiltrator",
	        		"Wrath",
	        		"Wretched Tiller",
	        		"Young Dragonhawk",
	        		"Youthful Brewmaster",
	        		"Zealous Initiate",
	        		"Zoobot",
	        		"Abominable Bowman",
	        		"Ancient Harbinger",
	        		"Ancient of Lore",
	        		"Ancient of War",
	        		"Arcane Giant",
	        		"Arcane Tyrant",
	        		"Astral Tiger",
	        		"Avenging Wrath",
	        		"Bane of Doom",
	        		"Bestial Wrath",
	        		"Big Game Hunter",
	        		"Biteweed",
	        		"Bittertide Hydra",
	        		"Blackguard",
	        		"Blade of C'Thun",
	        		"Bladed Gauntlet",
	        		"Blazecaller",
	        		"Blood Knight",
	        		"Blood Warriors",
	        		"Blood of The Ancient One",
	        		"Bloodbloom",
	        		"Blubber Baron",
	        		"Branching Paths",
	        		"Brass Knuckles",
	        		"Brawl",
	        		"Bright-Eyed Scout",
	        		"Bring It On!",
	        		"Burgly Bully",
	        		"Cabal Shadow Priest",
	        		"Cabalist's Tome",
	        		"Call of the Wild",
	        		"Call to Arms",
	        		"Carnivorous Cube",
	        		"Cataclysm",
	        		"Charged Devilsaur",
	        		"Chittering Tunneler",
	        		"Corpsetaker",
	        		"Corridor Creeper",
	        		"Crazed Worshipper",
	        		"Crushing Walls",
	        		"Cryostasis",
	        		"Curious Glimmerroot",
	        		"Cyclopian Horror",
	        		"DOOM!",
	        		"Darkspeaker",
	        		"Dead Man's Hand",
	        		"Deathaxe Punisher",
	        		"Deck of Wonders",
	        		"Defias Cleaner",
	        		"Dinomancy",
	        		"Dinosize",
	        		"Dirty Rat",
	        		"Doomerang",
	        		"Doomhammer",
	        		"Doomsayer",
	        		"Dragon's Fury",
	        		"Dragonfire Potion",
	        		"Dragonhatcher",
	        		"Drakkari Enchanter",
	        		"Earth Elemental",
	        		"Embrace Darkness",
	        		"Embrace the Shadow",
	        		"Emerald Hive Queen",
	        		"Eternal Sentinel",
	        		"Evasion",
	        		"Explore Un'Goro",
	        		"Faceless Manipulator",
	        		"Faceless Shambler",
	        		"Fal'dorei Strider",
	        		"Far Sight",
	        		"Fatespinner",
	        		"Fel Orc Soulfiend",
	        		"Fight Promoter",
	        		"Finders Keepers",
	        		"Forbidden Ancient",
	        		"Forbidden Flame",
	        		"Forbidden Healing",
	        		"Forbidden Shaping",
	        		"Force of Nature",
	        		"Furnacefire Colossus",
	        		"Gentle Megasaur",
	        		"Giant Anaconda",
	        		"Giant Sand Worm",
	        		"Glacial Mysteries",
	        		"Gladiator's Longbow",
	        		"Gluttonous Ooze",
	        		"Gnomeferatu",
	        		"Gorehowl",
	        		"Grand Archivist",
	        		"Greater Arcane Missiles",
	        		"Hammer of Twilight",
	        		"Hungry Crab",
	        		"Ice Block",
	        		"Kabal Trafficker",
	        		"Kidnapper",
	        		"Lay on Hands",
	        		"Leatherclad Hogleader",
	        		"Level Up!",
	        		"Light's Sorrow",
	        		"Living Mana",
	        		"Lotus Assassin",
	        		"Lotus Illusionist",
	        		"Luckydo Buccaneer",
	        		"Lunar Visions",
	        		"Mana Geode",
	        		"Manic Soulcaster",
	        		"Meanstreet Marshal",
	        		"Meat Wagon",
	        		"Meteor",
	        		"Mindgames",
	        		"Molten Giant",
	        		"Mountain Giant",
	        		"Murloc Warleader",
	        		"Nerubian Unraveler",
	        		"Obsidian Statue",
	        		"Patient Assassin",
	        		"Pilfered Power",
	        		"Piranha Launcher",
	        		"Pit Lord",
	        		"Preparation",
	        		"Primalfin Champion",
	        		"Primordial Drake",
	        		"Primordial Glyph",
	        		"Psychic Scream",
	        		"Pyroblast",
	        		"Rat Pack",
	        		"Rattling Rascal",
	        		"Reckless Flurry",
	        		"Renounce Darkness",
	        		"Rummaging Kobold",
	        		"Scaled Nightmare",
	        		"Sea Giant",
	        		"Seeping Oozeling",
	        		"Shadow Visions",
	        		"Shadowcaster",
	        		"Shadowform",
	        		"Shield Slam",
	        		"Shimmering Courser",
	        		"Simulacrum",
	        		"Skulking Geist",
	        		"Sleep with the Fishes",
	        		"Small-Time Recruits",
	        		"Snake Trap",
	        		"Snowfury Giant",
	        		"Southsea Captain",
	        		"Spectral Pillager",
	        		"Spellbender",
	        		"Spirit Echo",
	        		"Spiteful Summoner",
	        		"Stampede",
	        		"Stone Sentinel",
	        		"Sudden Genesis",
	        		"Sword of Justice",
	        		"Tentacles for Arms",
	        		"To My Side!",
	        		"Tomb Lurker",
	        		"Tortollan Primalist",
	        		"Toxic Arrow",
	        		"Treachery",
	        		"Twilight Acolyte",
	        		"Twilight Summoner",
	        		"Twisting Nether",
	        		"Ultimate Infestation",
	        		"Unlicensed Apothecary",
	        		"Unstable Evolution",
	        		"Validated Doomsayer",
	        		"Vilefin Inquisitor",
	        		"Vilespine Slayer",
	        		"Void Ripper",
	        		"Voidlord",
	        		"Weasel Tunneler",
	        		"Wind-up Burglebot",
	        		"Windshear Stormcaller",
	        		"Wisps of the Old Gods",
	        		"Al'Akir the Windlord",
	        		"Alexstrasza",
	        		"Aluneth",
	        		"Anomalus",
	        		"Archbishop Benedictus",
	        		"Archmage Antonidas",
	        		"Arfus",
	        		"Auctionmaster Beardo",
	        		"Awaken the Makers",
	        		"Aya Blackpaw",
	        		"Barnes",
	        		"Baron Geddon",
	        		"Blood-Queen Lana'thel",
	        		"Bloodmage Thalnos",
	        		"Bolvar, Fireblood",
	        		"C'Thun",
	        		"Cairne Bloodhoof",
	        		"Captain Greenskin",
	        		"Cenarius",
	        		"Cho'gall",
	        		"Clutchmother Zavas",
	        		"Deathwing",
	        		"Deathwing, Dragonlord",
	        		"Don Han'Cho",
	        		"Dragon Soul",
	        		"Dragoncaller Alanna",
	        		"Edwin VanCleef",
	        		"Elise the Trailblazer",
	        		"Fandral Staghelm",
	        		"Finja, the Flying Star",
	        		"Fire Plume's Heart",
	        		"Genzo, the Shark",
	        		"Geosculptor Yip",
	        		"Grommash Hellscream",
	        		"Grumble, Worldshaker",
	        		"Gruul",
	        		"Hadronox",
	        		"Hallazeal the Ascended",
	        		"Harrison Jones",
	        		"Hemet, Jungle Hunter",
	        		"Herald Volazj",
	        		"Hobart Grapplehammer",
	        		"Hogger",
	        		"Hogger, Doom of Elwynn",
	        		"Illidan Stormrage",
	        		"Inkmaster Solia",
	        		"Ixlid, Fungal Lord",
	        		"Jungle Giants",
	        		"Kalimos, Primal Lord",
	        		"Kathrena Winterwisp",
	        		"Kazakus",
	        		"King Krush",
	        		"King Mosh",
	        		"King Mukla",
	        		"King Togwaggle",
	        		"Kingsbane",
	        		"Knuckles",
	        		"Krul the Unshackled",
	        		"Kun the Forgotten King",
	        		"Lakkari Sacrifice",
	        		"Leeroy Jenkins",
	        		"Lilian Voss",
	        		"Lord Jaraxxus",
	        		"Lorewalker Cho",
	        		"Lynessa Sunsorrow",
	        		"Lyra the Sunshard",
	        		"Madam Goya",
	        		"Malfurion the Pestilent",
	        		"Malkorok",
	        		"Malygos",
	        		"Marin the Fox",
	        		"Master Oakheart",
	        		"Mayor Noggenfogger",
	        		"Medivh, the Guardian",
	        		"Millhouse Manastorm",
	        		"Moorabi",
	        		"Moroes",
	        		"Mukla, Tyrant of the Vale",
	        		"N'Zoth, the Corruptor",
	        		"Nat Pagle",
	        		"Nat, the Darkfisher",
	        		"Nozdormu",
	        		"Onyxia",
	        		"Open the Waygate",
	        		"Ozruk",
	        		"Patches the Pirate",
	        		"Prince Keleseth",
	        		"Prince Malchezaar",
	        		"Prince Taldaram",
	        		"Prince Valanar",
	        		"Princess Huhuran",
	        		"Professor Putricide",
	        		"Prophet Velen",
	        		"Pyros",
	        		"Ragnaros, Lightlord",
	        		"Raza the Chained",
	        		"Rhok'delar",
	        		"Rin, the First Disciple",
	        		"Rotface",
	        		"Sergeant Sally",
	        		"Shaku, the Collector",
	        		"Sherazin, Corpse Flower",
	        		"Shifter Zerus",
	        		"Sindragosa",
	        		"Skull of the Man'ari",
	        		"Soggoth the Slitherer",
	        		"Sonya Shadowdancer",
	        		"Spiritsinger Umbra",
	        		"Sunkeeper Tarim",
	        		"Swamp King Dred",
	        		"Temporus",
	        		"The Beast",
	        		"The Black Knight",
	        		"The Boogeymonster",
	        		"The Caverns Below",
	        		"The Curator",
	        		"The Darkness",
	        		"The Last Kaleidosaur",
	        		"The Lich King",
	        		"The Marsh Queen",
	        		"The Runespear",
	        		"The Voraxx",
	        		"Tinkmaster Overspark",
	        		"Tirion Fordring",
	        		"Twig of the World Tree",
	        		"Twin Emperor Vek'lor",
	        		"Tyrantus",
	        		"Unite the Murlocs",
	        		"Val'anyr",
	        		"White Eyes",
	        		"Wickerflame Burnbristle",
	        		"Woecleaver",
	        		"Wrathion",
	        		"Xaril, Poisoned Mind",
	        		"Y'Shaarj, Rage Unbound",
	        		"Yogg-Saron, Hope's End",
	        		"Ysera",
	        		"Zola the Gorgon",
	        		"Abomination",
	        		"Addled Grizzly",
	        		"Alarm-o-Bot",
	        		"Aldor Peacekeeper",
	        		"Alley Armorsmith",
	        		"Ancestral Spirit",
	        		"Ancient Mage",
	        		"Ancient Shieldbearer",
	        		"Ancient Watcher",
	        		"Angry Chicken",
	        		"Arcane Golem",
	        		"Argent Commander",
	        		"Armorsmith",
	        		"Arrogant Crusader",
	        		"Auchenai Soulpriest",
	        		"Avalanche",
	        		"Avian Watcher",
	        		"Babbling Book",
	        		"Backroom Bouncer",
	        		"Bite",
	        		"Blackwater Pirate",
	        		"Blade Flurry",
	        		"Blessed Champion",
	        		"Blizzard",
	        		"Blood Razor",
	        		"Blood To Ichor",
	        		"Bloodfury Potion",
	        		"Bloodsail Corsair",
	        		"Bloodsail Cultist",
	        		"Bomb Squad",
	        		"Bone Drake",
	        		"Book Wyrm",
	        		"Cat Trick",
	        		"Celestial Dreamer",
	        		"Coldlight Oracle",
	        		"Coldlight Seer",
	        		"Commanding Shout",
	        		"Cornered Sentry",
	        		"Corpse Raiser",
	        		"Corpse Widow",
	        		"Corrupted Healbot",
	        		"Corrupted Seer",
	        		"Corrupting Mist",
	        		"Counterfeit Coin",
	        		"Counterspell",
	        		"Crazed Alchemist",
	        		"Cruel Dinomancer",
	        		"Crystal Lion",
	        		"Crystalline Oracle",
	        		"Cult Sorcerer",
	        		"Darkshire Librarian",
	        		"Death Revenant",
	        		"Defender of Argus",
	        		"Defile",
	        		"Demented Frostcaller",
	        		"Demolisher",
	        		"Desperate Stand",
	        		"Despicable Dreadlord",
	        		"Devilsaur Egg",
	        		"Devolve",
	        		"Devour Mind",
	        		"Direhorn Hatchling",
	        		"Disciple of C'Thun",
	        		"Dispatch Kodo",
	        		"Divine Favor",
	        		"Doomcaller",
	        		"Doomed Apprentice",
	        		"Doomguard",
	        		"Doppelgangster",
	        		"Drakonid Operative",
	        		"Druid of the Swarm",
	        		"Duskbreaker",
	        		"Eaglehorn Bow",
	        		"Earthen Scales",
	        		"Eater of Secrets",
	        		"Ebon Dragonsmith",
	        		"Elven Minstrel",
	        		"Emperor Cobra",
	        		"Envenom Weapon",
	        		"Equality",
	        		"Eternal Servitude",
	        		"Ethereal Arcanist",
	        		"Ethereal Peddler",
	        		"Evolve",
	        		"Evolving Spores",
	        		"Exploding Bloatbat",
	        		"Explosive Runes",
	        		"Explosive Shot",
	        		"Feeding Time",
	        		"Felfire Potion",
	        		"Felguard",
	        		"Feral Gibberer",
	        		"Feral Spirit",
	        		"Fire Plume Harbinger",
	        		"Flare",
	        		"Forbidden Ritual",
	        		"Forlorn Stalker",
	        		"Free From Amber",
	        		"Frothing Berserker",
	        		"Frozen Crusher",
	        		"Furbolg Mossbinder",
	        		"Gadgetzan Auctioneer",
	        		"Gadgetzan Ferryman",
	        		"Gather Your Party",
	        		"Getaway Kodo",
	        		"Ghastly Conjurer",
	        		"Golakka Crawler",
	        		"Gravelsnout Knight",
	        		"Greater Healing Potion",
	        		"Greedy Sprite",
	        		"Grimestreet Enforcer",
	        		"Grimestreet Informant",
	        		"Grimestreet Pawnbroker",
	        		"Grimestreet Protector",
	        		"Grizzled Guardian",
	        		"Happy Ghoul",
	        		"Headcrack",
	        		"Hidden Cache",
	        		"Holy Fire",
	        		"Holy Wrath",
	        		"Hooked Reaver",
	        		"Howling Commander",
	        		"Humongous Razorleaf",
	        		"Hungry Ettin",
	        		"Ice Breaker",
	        		"Ice Walker",
	        		"Imp Master",
	        		"Infest",
	        		"Infested Wolf",
	        		"Injured Blademaster",
	        		"Ivory Knight",
	        		"Jade Claws",
	        		"Jade Idol",
	        		"Jinyu Waterspeaker",
	        		"Journey Below",
	        		"Kabal Courier",
	        		"Kabal Crystal Runner",
	        		"Keening Banshee",
	        		"Keeper of the Grove",
	        		"Kirin Tor Mage",
	        		"Klaxxi Amber-Weaver",
	        		"Knife Juggler",
	        		"Kobold Barbarian",
	        		"Kobold Illusionist",
	        		"Kobold Monk",
	        		"Lava Burst",
	        		"Lesser Amethyst Spellstone",
	        		"Lesser Diamond Spellstone",
	        		"Lesser Emerald Spellstone",
	        		"Lesser Jasper Spellstone",
	        		"Lesser Mithril Spellstone",
	        		"Lesser Onyx Spellstone",
	        		"Lesser Pearl Spellstone",
	        		"Lesser Ruby Spellstone",
	        		"Lesser Sapphire Spellstone",
	        		"Leyline Manipulator",
	        		"Lightfused Stegodon",
	        		"Lightning Storm",
	        		"Lightwarden",
	        		"Lightwell",
	        		"Lone Champion",
	        		"Lotus Agents",
	        		"Maelstrom Portal",
	        		"Mana Addict",
	        		"Mana Bind",
	        		"Mana Tide Totem",
	        		"Mana Wraith",
	        		"Mass Dispel",
	        		"Master Swordsmith",
	        		"Master of Disguise",
	        		"Master of Evolution",
	        		"Midnight Drake",
	        		"Mimic Pod",
	        		"Mind Control Tech",
	        		"Mindbreaker",
	        		"Mirage Caller",
	        		"Mire Keeper",
	        		"Misdirection",
	        		"Moat Lurker",
	        		"Molten Blade",
	        		"Molten Reflection",
	        		"Moonglade Portal",
	        		"Mortal Strike",
	        		"Mountainfire Armor",
	        		"Murloc Tidecaller",
	        		"Murmuring Elemental",
	        		"Nourish",
	        		"Obsidian Shard",
	        		"Onyx Bishop",
	        		"Perdition's Blade",
	        		"Phantom Freebooter",
	        		"Pint-Size Potion",
	        		"Pint-Sized Summoner",
	        		"Plague Scientist",
	        		"Possessed Lackey",
	        		"Potion of Polymorph",
	        		"Primal Talismans",
	        		"Primalfin Totem",
	        		"Protect the King!",
	        		"Questing Adventurer",
	        		"Rallying Blade",
	        		"Raptor Hatchling",
	        		"Ravenholdt Assassin",
	        		"Roll the Bones",
	        		"Runeforge Haunter",
	        		"SI:7 Agent",
	        		"Saronite Chain Gang",
	        		"Savagery",
	        		"Savannah Highmane",
	        		"Scorp-o-matic",
	        		"Seadevil Stinger",
	        		"Second-Rate Bruiser",
	        		"Secretkeeper",
	        		"Selfless Hero",
	        		"Servant of Kalimos",
	        		"Servant of Yogg-Saron",
	        		"Shadow Essence",
	        		"Shadow Madness",
	        		"Shadow Sensei",
	        		"Shadow Word: Horror",
	        		"Shadowblade",
	        		"Shadowflame",
	        		"Shallow Gravedigger",
	        		"Shellshifter",
	        		"Shifting Shade",
	        		"Shrieking Shroom",
	        		"Silithid Swarmer",
	        		"Silverware Golem",
	        		"Siphon Soul",
	        		"Skeram Cultist",
	        		"Small-Time Buccaneer",
	        		"Spiked Hogrider",
	        		"Spikeridged Steed",
	        		"Spreading Madness",
	        		"Spreading Plague",
	        		"Stampeding Kodo",
	        		"Starfall",
	        		"Steam Surger",
	        		"Steward of Darkshire",
	        		"Stolen Goods",
	        		"Stonehill Defender",
	        		"Strongshell Scavenger",
	        		"Sunfury Protector",
	        		"Sunwalker",
	        		"Terrorscale Stalker",
	        		"Thing from Below",
	        		"Thistle Tea",
	        		"Ticking Abomination",
	        		"Tol'vir Stoneshaper",
	        		"Tol'vir Warden",
	        		"Trogg Beastrager",
	        		"Twilight Darkmender",
	        		"Twilight Drake",
	        		"Twilight's Call",
	        		"Undercity Huckster",
	        		"Unidentified Maul",
	        		"Unwilling Sacrifice",
	        		"Upgrade!",
	        		"Val'kyr Soulclaimer",
	        		"Vaporize",
	        		"Venomstrike Trap",
	        		"Vicious Fledgling",
	        		"Vinecleaver",
	        		"Violet Teacher",
	        		"Virmen Sensei",
	        		"Void Terror",
	        		"Volcanic Potion",
	        		"Volcano",
	        		"Volcanosaur",
	        		"Voodoo Hexxer",
	        		"Wandering Monster",
	        		"Wild Pyromancer",
	        		"Young Priestess",
	        		"Amethyst Spellstone",
	        		"Blessed Maul",
	        		"Champion's Maul",
	        		"Diamond Spellstone",
	        		"Doppelgangster",
	        		"Doppelgangster",
	        		"Druid of the Swarm",
	        		"Druid of the Swarm",
	        		"Druid of the Swarm",
	        		"Emerald Spellstone",
	        		"Greater Amethyst Spellstone",
	        		"Greater Diamond Spellstone",
	        		"Greater Emerald Spellstone",
	        		"Greater Jasper Spellstone",
	        		"Greater Mithril Spellstone",
	        		"Greater Onyx Spellstone",
	        		"Greater Pearl Spellstone",
	        		"Greater Ruby Spellstone",
	        		"Greater Sapphire Spellstone",
	        		"Hyena",
	        		"Jasper Spellstone",
	        		"Mithril Spellstone",
	        		"Onyx Spellstone",
	        		"Pearl Spellstone",
	        		"Purifier's Maul",
	        		"Ruby Spellstone",
	        		"Sacred Maul",
	        		"Sapphire Spellstone",
	        		"Shellshifter",
	        		"Shellshifter",
	        		"Shellshifter",
	        		"Spirit Wolf",
	        		"Baine Bloodhoof",
	        		"Finkle Einhorn",
	        		"Grave Vengeance",
	        		"Pyros",
	        		"Pyros",
	        		"The Darkness",
	        		"The Storm Guardian",
	        		"Twin Emperor Vek'nilash",
	        		"Shadow of Nothing",
	        		"Spellbender",
	        		"The Ancient One",
	        		"Bananas",
	        		"Battle Axe",
	        		"Blood Fury",
	        		"Boar",
	        		"Damaged Golem",
	        		"Defender",
	        		"Defias Bandit",
	        		"Devilsaur",
	        		"Druid of the Claw",
	        		"Druid of the Claw",
	        		"Druid of the Claw",
	        		"Elixir of Hope",
	        		"Elixir of Life",
	        		"Elixir of Purity",
	        		"Elixir of Shadows",
	        		"Flame of Azzinoth",
	        		"Frog",
	        		"Gnoll",
	        		"Heavy Axe",
	        		"Hound",
	        		"Huffer",
	        		"Infernal",
	        		"Leokk",
	        		"Mechanical Dragonling",
	        		"Mirror Image",
	        		"Misha",
	        		"Murloc Scout",
	        		"Panther",
	        		"Runed Shield",
	        		"Rusty Hook",
	        		"Serrated Shield",
	        		"Sheep",
	        		"Snake",
	        		"Spiked Shield",
	        		"Squire",
	        		"Squirrel",
	        		"Tower Shield +10",
	        		"Treant",
	        		"Treant",
	        		"Violet Apprentice",
	        		"Whelp",
	        		"Worthless Imp",
	        		"Healing Totem",
	        		"Searing Totem",
	        		"Silver Hand Recruit",
	        		"Stoneclaw Totem",
	        		"Wicked Knife",
	        		"Wrath of Air Totem"
	        }; 
	        GridPane container = new GridPane();

	        HBox searchBox = new HBox();

	        TextField text = new TextField(); 

	        // add a listener to listen to the changes in the text field
	        text.textProperty().addListener((observable, oldValue, newValue) -> {
	        		if(container.getChildren().size()>1){ // if already contains a drop-down menu -> remove it 
	        			container.getChildren().remove(1);
	        		}
	        		container.add(populateDropDownMenu(newValue, options),0,1); // then add the populated drop-down menu to the second row in the grid pane
	        });

	        // those buttons just for example
	        // note that you can add action listeners to them ..etc
	        Button save = new Button("Add");
	        Button remove = new Button("Delete");
	        searchBox.getChildren().addAll(text,save, remove);

	        // add the search box to first row
	        container.add(searchBox, 0, 0);

	        // the colors in all containers only for example
	        //container.setBackground(new Background(new BackgroundFill(Color.GRAY, null,null)));

	        buttom.getChildren().add(container);
//			updateOppLabel();
			//TextField text = new TextField("");
			// Label search = new Label("Add Cards to Deck");
			// Label delete1 = new Label("Remove Cards to Deck");
			//Button save = new Button("Save");
			//Button remove = new Button("Remove");
			ChoiceBox<String> choice = new ChoiceBox<String>();
			choice.getItems().addAll("Mana Cost", "Armor", "Health", "Damage", "Rarity");
			choice.setLayoutX(560);
			choice.setLayoutY(15);
			choice.setMaxWidth(85);
//			save.setLayoutX(340);
//			save.setLayoutY(15);
//			save.setPrefSize(70, 20);
//			remove.setLayoutX(420);
//			remove.setLayoutY(15);
//			remove.setPrefSize(70, 20);

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
			container.setLayoutX(450);
			container.setLayoutY(100);
//			buttom.getChildren().addAll(save, load, remove, choice, sort, CurrentDeck);
			buttom.getChildren().addAll(load, choice, sort, CurrentDeck);

			SaveDeck.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					try {
						//System.out.println(CurrentDeck.getText());
						File read;
						if (System.getProperty("os.name").toLowerCase().contains("win")) {
							read = new File(pcPath + CurrentDeck.getText() + ".txt");
						}else {
							
							read = new File("/Users/chenjunjie/repository/A8/Heartstone_Deck_Track/Decks/"+CurrentDeck.getText()+ ".txt");
						}
						
						read.delete();
						if (System.getProperty("os.name").toLowerCase().contains("win")) {
							resultFile = new FileWriter(pcPath + CurrentDeck.getText() + ".txt", true);
						}else {
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
						if (cards[i].equals(text.getText())&&l==0) {
							l=1;
						}
						else {
							CardsInDeck.appendText(cards[i]+"\n");
						}
					}
					// CardsInDeck.deleteText(3, 5);

				}
			});
			
			CreateDeck.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					String name="";
					TextInputDialog dialog = new TextInputDialog();
					dialog.setTitle("Create a new Deck");
					dialog.setHeaderText("What's the name of new Deck?");
					dialog.setContentText("Name: ");
					Optional result = dialog.showAndWait();
					if (result.isPresent()&&!result.get().toString().equals("")&&result.get().toString()!=null) {
					   name=result.get().toString();
					
						
					try {
						String fileName;
						if (System.getProperty("os.name").toLowerCase().contains("win")) {
							fileName = "Decks/" + name+ ".txt";
						}else {
							
							fileName = "/Users/chenjunjie/repository/A8/Heartstone_Deck_Track/Decks/"+name+ ".txt";
						}
						
						System.out.println(fileName);
						File newfile = new File(fileName);
						
						newfile.createNewFile();
					}
					catch(IOException e) {
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
									PathName = "Decks/" + deckName[Integer.parseInt(temp.substring(42))].getText()
											+ ".txt";
								}else {
									
									PathName = "/Users/chenjunjie/repository/A8/Heartstone_Deck_Track/Decks/"+deckName[Integer.parseInt(temp.substring(42))].getText()
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
			 * text.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
			 * 
			 * @Override public void handle(KeyEvent e) { if
			 * (e.getCode().equals(KeyCode.ENTER)) { //
			 * Server.createCard(text.getCharacters().toString());
			 * deck1.addCard(Server.createCard(text.getCharacters().toString()));
			 * System.out.println(" "); for (int abc = 0; abc < deck1.getSize(); abc++) {
			 * System.out.println(deck1.getCard(abc).Name()); } System.out.println(" ");
			 * text.clear(); } } });
			 */
			/*
			 * delete.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
			 * 
			 * @Override public void handle(KeyEvent e1) { if
			 * (e1.getCode().equals(KeyCode.ENTER)) { //
			 * Server.createCard(text.getCharacters().toString());
			 * deck1.removeCard(delete.getCharacters().toString()); System.out.println(" ");
			 * for (int abc1 = 0; abc1 < deck1.getSize(); abc1++) {
			 * System.out.println(deck1.getCard(abc1).Name()); } System.out.println(" ");
			 * delete.clear(); } } });
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
					if (cardsname.length < FRIENDLYCARDCOUNTMAX) {
						Alert alert = new Alert(Alert.AlertType.INFORMATION);
						alert.setTitle("Alert");
						alert.setHeaderText("You can't create a deck less than 30 cards");
						alert.showAndWait();
					} else {
						
						for (int i = 0; i < FRIENDLYCARDCOUNTMAX; i++) {
							
							String t = cardsname[i];
							//System.out.println(t);
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
								//String PathName = "/Decks/" + deckName[Integer.parseInt(temp.substring(42))].getText()
									//	+ ".txt";
								String PathName;
								
								if (System.getProperty("os.name").toLowerCase().contains("win")) {
									PathName = "Decks/" + deckName[Integer.parseInt(temp.substring(42))].getText()
											+ ".txt";
								}else {
									
									PathName = "/Users/chenjunjie/repository/A8/Heartstone_Deck_Track/Decks/"+deckName[Integer.parseInt(temp.substring(42))].getText()
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

			// Event: Show big picture for your card

			for (int i = 0; i < FRIENDLYCARDCOUNTMAX; i++) {
				lablesMyDeck[i].addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent e) {
						// lp.setImage(i);
						Stage sta = new Stage();
						try {
							lp.getLoca(primaryStage.getX(), primaryStage.getY());

							if (e.getSource() == lablesMyDeck[0]) {
								lp.setImage(large_picture_friendly[0]);
							} else if (e.getSource() == lablesMyDeck[1]) {
								lp.setImage(large_picture_friendly[1]);
							} else if (e.getSource() == lablesMyDeck[2]) {
								lp.setImage(large_picture_friendly[2]);
							} else if (e.getSource() == lablesMyDeck[3]) {
								lp.setImage(large_picture_friendly[3]);
							} else if (e.getSource() == lablesMyDeck[4]) {
								lp.setImage(large_picture_friendly[4]);
							} else if (e.getSource() == lablesMyDeck[5]) {
								lp.setImage(large_picture_friendly[5]);
							} else if (e.getSource() == lablesMyDeck[6]) {
								lp.setImage(large_picture_friendly[6]);
							} else if (e.getSource() == lablesMyDeck[7]) {
								lp.setImage(large_picture_friendly[7]);
							} else if (e.getSource() == lablesMyDeck[8]) {
								lp.setImage(large_picture_friendly[8]);
							} else if (e.getSource() == lablesMyDeck[9]) {
								lp.setImage(large_picture_friendly[9]);
							} else if (e.getSource() == lablesMyDeck[10]) {
								lp.setImage(large_picture_friendly[10]);
							} else if (e.getSource() == lablesMyDeck[11]) {
								lp.setImage(large_picture_friendly[11]);
							} else if (e.getSource() == lablesMyDeck[12]) {
								lp.setImage(large_picture_friendly[12]);
							} else if (e.getSource() == lablesMyDeck[13]) {
								lp.setImage(large_picture_friendly[13]);
							} else if (e.getSource() == lablesMyDeck[14]) {
								lp.setImage(large_picture_friendly[14]);
							} else if (e.getSource() == lablesMyDeck[15]) {
								lp.setImage(large_picture_friendly[15]);
							} else if (e.getSource() == lablesMyDeck[16]) {
								lp.setImage(large_picture_friendly[16]);
							} else if (e.getSource() == lablesMyDeck[17]) {
								lp.setImage(large_picture_friendly[17]);
							} else if (e.getSource() == lablesMyDeck[18]) {
								lp.setImage(large_picture_friendly[18]);
							} else if (e.getSource() == lablesMyDeck[19]) {
								lp.setImage(large_picture_friendly[19]);
							} else if (e.getSource() == lablesMyDeck[20]) {
								lp.setImage(large_picture_friendly[20]);
							} else if (e.getSource() == lablesMyDeck[21]) {
								lp.setImage(large_picture_friendly[21]);
							} else if (e.getSource() == lablesMyDeck[22]) {
								lp.setImage(large_picture_friendly[22]);
							} else if (e.getSource() == lablesMyDeck[23]) {
								lp.setImage(large_picture_friendly[23]);
							} else if (e.getSource() == lablesMyDeck[24]) {
								lp.setImage(large_picture_friendly[24]);
							} else if (e.getSource() == lablesMyDeck[25]) {
								lp.setImage(large_picture_friendly[25]);
							} else if (e.getSource() == lablesMyDeck[26]) {
								lp.setImage(large_picture_friendly[26]);
							} else if (e.getSource() == lablesMyDeck[27]) {
								lp.setImage(large_picture_friendly[27]);
							} else if (e.getSource() == lablesMyDeck[28]) {
								lp.setImage(large_picture_friendly[28]);
							} else if (e.getSource() == lablesMyDeck[29]) {
								lp.setImage(large_picture_friendly[29]);
							}

							lp.start(sta);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				lablesMyDeck[i].addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent e) {
						lp.Close();

					}
				});
			}
			for (int i = 0; i < OPPCARDCOUNTMAX; i++) {
				lablesOppDeck[i].addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent e) {
						// lp.setImage(i);
						Stage sta = new Stage();
						try {
							lp.getLoca(primaryStage.getX(), primaryStage.getY());

							if (e.getSource() == lablesOppDeck[0]) {
								lp.setImage(large_picture_opp[0]);
							} else if (e.getSource() == lablesOppDeck[1]) {
								lp.setImage(large_picture_opp[1]);
							} else if (e.getSource() == lablesOppDeck[2]) {
								lp.setImage(large_picture_opp[2]);
							} else if (e.getSource() == lablesOppDeck[3]) {
								lp.setImage(large_picture_opp[3]);
							} else if (e.getSource() == lablesOppDeck[4]) {
								lp.setImage(large_picture_opp[4]);
							} else if (e.getSource() == lablesOppDeck[5]) {
								lp.setImage(large_picture_opp[5]);
							} else if (e.getSource() == lablesOppDeck[6]) {
								lp.setImage(large_picture_opp[6]);
							} else if (e.getSource() == lablesOppDeck[7]) {
								lp.setImage(large_picture_opp[7]);
							} else if (e.getSource() == lablesOppDeck[8]) {
								lp.setImage(large_picture_opp[8]);
							} else if (e.getSource() == lablesOppDeck[9]) {
								lp.setImage(large_picture_opp[9]);
							} else if (e.getSource() == lablesOppDeck[10]) {
								lp.setImage(large_picture_opp[10]);
							} else if (e.getSource() == lablesOppDeck[11]) {
								lp.setImage(large_picture_opp[11]);
							} else if (e.getSource() == lablesOppDeck[12]) {
								lp.setImage(large_picture_opp[12]);
							} else if (e.getSource() == lablesOppDeck[13]) {
								lp.setImage(large_picture_opp[13]);
							} else if (e.getSource() == lablesOppDeck[14]) {
								lp.setImage(large_picture_opp[14]);
							} else if (e.getSource() == lablesOppDeck[15]) {
								lp.setImage(large_picture_opp[15]);
							} else if (e.getSource() == lablesOppDeck[16]) {
								lp.setImage(large_picture_opp[16]);
							} else if (e.getSource() == lablesOppDeck[17]) {
								lp.setImage(large_picture_opp[17]);
							} else if (e.getSource() == lablesOppDeck[18]) {
								lp.setImage(large_picture_opp[18]);
							} else if (e.getSource() == lablesOppDeck[19]) {
								lp.setImage(large_picture_opp[19]);
							} else if (e.getSource() == lablesOppDeck[20]) {
								lp.setImage(large_picture_opp[20]);
							} else if (e.getSource() == lablesOppDeck[21]) {
								lp.setImage(large_picture_opp[21]);
							} else if (e.getSource() == lablesOppDeck[22]) {
								lp.setImage(large_picture_opp[22]);
							} else if (e.getSource() == lablesOppDeck[23]) {
								lp.setImage(large_picture_opp[23]);
							} else if (e.getSource() == lablesOppDeck[24]) {
								lp.setImage(large_picture_opp[24]);
							} else if (e.getSource() == lablesOppDeck[25]) {
								lp.setImage(large_picture_opp[25]);
							} else if (e.getSource() == lablesOppDeck[26]) {
								lp.setImage(large_picture_opp[26]);
							} else if (e.getSource() == lablesOppDeck[27]) {
								lp.setImage(large_picture_opp[27]);
							} else if (e.getSource() == lablesOppDeck[28]) {
								lp.setImage(large_picture_opp[28]);
							} else if (e.getSource() == lablesOppDeck[29]) {
								lp.setImage(large_picture_opp[29]);
							}

							lp.start(sta);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				lablesOppDeck[i].addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent e) {
						lp.Close();

					}
				});
			}

			scene = new Scene(buttom, 750, 700);
			primaryStage.setScene(scene);
			primaryStage.setResizable(true);
			primaryStage.setTitle("HearthStone Tracker");
			//primaryStage.getIcons().add(new Image("Image/icon.PNG"));

			// primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		lablesMyDeck = new Label[FRIENDLYCARDCOUNTMAX];
		lablesOppDeck=new Label[OPPCARDCOUNTMAX];
		for(int x=0;x<FRIENDLYCARDCOUNTMAX;x++) {
			lablesMyDeck[x] = new Label();
			lablesOppDeck[x]=new Label();
			vbYourCards.getChildren().add(lablesMyDeck[x]);
			vbOppCards.getChildren().add(lablesOppDeck[x]);
		}
		lablesOppDeck[OPPCARDCOUNTMAX-1]=new Label();
		vbOppCards.getChildren().add(lablesOppDeck[OPPCARDCOUNTMAX-1]);
		friendlyDeck = new Deck();
		oppDeck=new Deck();
		deck1 = new Deck();
		String[] deckCards = logReader.createTestNames(0);
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

	public static void put_large_image(String[] image_name,boolean isFriendly) {

		// System.out.println(("Image/"+image_name[0]+".png").replaceAll("\\s+",""));
		if(isFriendly) {
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
					large_picture_friendly[i] = SwingFXUtils.toFXImage(bimage, null);
				} else {
					large_picture_friendly[i] = new Image(("Image/" + "null.png").replaceAll("\\s+", ""));
				}
			}
		}
		else {
			for (int i = 0; i < oppDeck.getSize(); i++) {
				// small_picture[i] = new Image(("Image/" + image_name[i] +
				// "(s).png").replaceAll("\\s+", ""));
	
				Card crd = oppDeck.getCard(i);
				if (crd.barIcon != null) {
					java.awt.Image img = crd.largeIcon.getImage();
					BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null),
							BufferedImage.TYPE_INT_ARGB);
					Graphics2D bGr = bimage.createGraphics();
					bGr.drawImage(img, 0, 0, null);
					bGr.dispose();
					large_picture_opp[i] = SwingFXUtils.toFXImage(bimage, null);
				} else {
					large_picture_opp[i] = new Image(("Image/" + "null.png").replaceAll("\\s+", ""));
				}
			}
		}
		

	}

	public static void put_small_image(String[] image_name,boolean isFriendly) {
		
		if(isFriendly) {
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
					bGr.setColor(java.awt.Color.black);
					if(crd.getValue().intValue() == 1){
						String prob = drawProbability(crd);
						bGr.setFont(bGr.getFont().deriveFont(30f));
				        bGr.drawString(prob, 0, 50);
					}
					bGr.dispose();
					small_picture_friendly[i] = SwingFXUtils.toFXImage(bimage, null);
				} else {
					small_picture_friendly[i] = new Image(("Image/" + "nullBar.png").replaceAll("\\s+", ""));
				}
			}
		}
		else
		{
			for (int i = 0; i < oppDeck.getSize(); i++) {
				// small_picture[i] = new Image(("Image/" + image_name[i] +
				// "(s).png").replaceAll("\\s+", ""));
	
				Card crd = oppDeck.getCard(i);
				if (crd.barIcon != null) {
					java.awt.Image img = crd.barIcon.getImage();
					BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null),
							BufferedImage.TYPE_INT_ARGB);
					Graphics2D bGr = bimage.createGraphics();
					bGr.drawImage(img, 0, 0, null);
					bGr.dispose();
					small_picture_opp[i] = SwingFXUtils.toFXImage(bimage, null);
				} else {
					small_picture_opp[i] = new Image(("Image/" + "nullBar.png").replaceAll("\\s+", ""));
				}
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
		if (deck1.getSize() == FRIENDLYCARDCOUNTMAX) {
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

	private static void updateLabel() {
		String[] imageNames = new String[FRIENDLYCARDCOUNTMAX];
		for (int i = 0; i < friendlyDeck.getSize(); i++) {
			imageNames[i] = friendlyDeck.getCard(i).Name();
		}
		put_large_image(imageNames,true);
		put_small_image(imageNames,true);

		for (int i = 0; i < FRIENDLYCARDCOUNTMAX; i++) {

			lablesMyDeck[i].setPrefSize(286 + 90, 50);

			BackgroundImage BackImage = new BackgroundImage((small_picture_friendly[i]), BackgroundRepeat.REPEAT,
					BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
			lablesMyDeck[i].setBackground(new Background(BackImage));

		}
	}
	private static void updateOppLabel() {
		String[] imageNames = new String[31];
		for (int i = 0; i < oppDeck.getSize(); i++) {
			imageNames[i] = oppDeck.getCard(i).Name();
		}
		put_large_image(imageNames,false);
		put_small_image(imageNames,false);

		for (int i = 0; i < oppDeck.getSize(); i++) {

			lablesOppDeck[i].setPrefSize(286 + 90, 50);

			BackgroundImage BackImage = new BackgroundImage((small_picture_opp[i]), BackgroundRepeat.REPEAT,
					BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
			lablesOppDeck[i].setBackground(new Background(BackImage));
//			vbOppCards.getChildren().add(lablesOppDeck[i]);
		}
	}
	private void updateLabel(String[] imageNames) {
		put_large_image(imageNames,true);
		put_small_image(imageNames,true);

		for (int i = 0; i < FRIENDLYCARDCOUNTMAX; i++) {
//			lablesMyDeck[i] = new Label();

			lablesMyDeck[i].setPrefSize(286 + 90, 50);

			BackgroundImage BackImage = new BackgroundImage((small_picture_friendly[i]), BackgroundRepeat.REPEAT,
					BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
			
			lablesMyDeck[i].setBackground(new Background(BackImage));
//			vbYourCards.getChildren().add(lablesMyDeck[i]);

		}
	}
	
	private static String drawProbability(Card selectedCard) {
		double count = 0.0;
		String result = "";
		double totalCards = (double)remainingCards;
		
		for(int i = 0; i < friendlyDeck.getSize(); i++) {
			Card crd = friendlyDeck.getCard(i);
			if(crd.getValue().intValue() < 1){
				continue;
			}
			
			if(selectedCard.name == crd.name)
				count++;
		}
		result = "" + Math.round((count / totalCards) * 100.0) + "%";
		return result;
		
	}
	
	public static VBox populateDropDownMenu(String text, String[] options){
        VBox dropDownMenu = new VBox();
        dropDownMenu.setBackground(new Background(new BackgroundFill(Color.GREY, null,null))); // colors just for example
        dropDownMenu.setAlignment(Pos.CENTER); // all these are optional and up to you

        for(String option : options){ // loop through every String in the array
            // if the given text is not empty and doesn't consists of spaces only, as well as it's a part of one (or more) of the options
            if(!text.replace(" ", "").isEmpty() && option.toUpperCase().contains(text.toUpperCase())){ 
                Label label = new Label(option); // create a label and set the text 
                // you can add listener to the label here if you want
                // your user to be able to click on the options in the drop-down menu
                dropDownMenu.getChildren().add(label); // add the label to the VBox
            }
        }

        return dropDownMenu; // at the end return the VBox (i.e. drop-down menu)
    }

}