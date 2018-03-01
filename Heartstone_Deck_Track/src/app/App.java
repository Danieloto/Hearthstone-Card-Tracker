package app;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import deck.Card;
import deck.Deck;
import logReader.Log_Reader;
import server.Server;

public class App {

	private boolean stillOn;
	private static String macLogAddress="/Applications/Hearthstone/Logs/Power.log";
	private static String pcLogAddress="C:/Program Files (x86)/Hearthstone/Logs/Power.log";
	private ArrayList<String> friendlyCards;
	private ArrayList<String> opponentCards;
	private ArrayList<String> outPut;
	private Server server;
	public Deck friendlyDeck;
	private Log_Reader logReader= new Log_Reader();

	public App() {
		stillOn = true;
		friendlyCards = new ArrayList<String>();
		opponentCards = new ArrayList<String>();
		outPut= new ArrayList<String>();
		server=new Server();
		friendlyDeck=new Deck();
		String[] deckCards= logReader.createTestIDs(0);
		Card tempCard;
		for(String cardName: deckCards) {
			tempCard=server.createCard(logReader.idToNames(cardName));
			friendlyDeck.addCard(tempCard);
		}
		//Moved this to main. So only rain when setting this as main function.
//		if(System.getProperty("os.name").toLowerCase().contains("win"))
//			openFile(pcLogAddress);
//		else
//			openFile(macLogAddress);
		
	}

	//windows at C:\Program Files (x86)\Hearthstone\Logs\Power.log
	// see : https://github.com/jleclanche/fireplace/wiki/How-to-enable-logging
	
	public static void main(String args[]) {
		App app = new App();
		//It determine system type so knows where log file is
		if(System.getProperty("os.name").toLowerCase().contains("win"))
			app.openFile(pcLogAddress);
		else
			app.openFile(macLogAddress);
		
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
					for(String s: outPut)
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

	/**
	 * Tries to understand what is happening in the game by reading the line of the Power Log
	 * 
	 * @param line-The line from Power Log
	 */
	private void prcessingLine(String line) {
		/*
		 * Checks to see if a new card was played in game
		 */
		if (logReader.lineContainsCards(line)) {
			String cardName;

			if (logReader.isFriendlyCards(line)) {
				friendlyCards.add(logReader.idToNames(line.substring(line.lastIndexOf('=') + 1)));
				cardName=friendlyCards.get(friendlyCards.size() - 1);				 
				outPut.add(cardName);
				/*
				 * use frendlyCards to develop your code
				 */

			} else if (logReader.isOpponentCards(line)) {

				opponentCards.add(logReader.idToNames(line.substring(line.lastIndexOf('=') + 1)));
				cardName=opponentCards.get(opponentCards.size()-1);
//				outPut.add(cardName);
				//System.out.println(opponentCards.get(opponentCards.size() - 1));
				
				/*
				 * use oppoinentCards to develop your code 
				 */
			}
			

		}
		/*
		 * Checks to see if a new game has started
		 */
		else if(logReader.isNewGame(line)) {
			if(!outPut.isEmpty()) {
				outPut.clear();
				friendlyCards.clear();
				opponentCards.clear();
			}
			
		}
		/*
		 * Checks to see if a card was returned
		 */
		else if(logReader.isSendingCardBackToDeck(line)) {
			Log_Reader temp=new Log_Reader();
			int beginIndex,endIndex;
			beginIndex=line.indexOf("card")+7;
			endIndex=line.indexOf("card")+14;
			String cardName=temp.idToNames(line.substring(beginIndex,endIndex));
			friendlyCards.remove(cardName);
			outPut.add("*"+cardName+" returned to deck");
		}

	}

}
