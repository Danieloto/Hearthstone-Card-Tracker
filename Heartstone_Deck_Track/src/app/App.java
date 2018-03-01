package app;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import logReader.Log_Reader;

public class App {

	private boolean stillOn;
	private String macLogAddress="/Applications/Hearthstone/Logs/Power.log";
	private String pcLogAddress="C:/Program Files (x86)/Hearthstone/Logs/Power.log";
	private ArrayList<String> friendlyCards;
	private ArrayList<String> opponentCards;
	private ArrayList<String> outPut;

	public App() {
		stillOn = true;
		friendlyCards = new ArrayList<String>();
		opponentCards = new ArrayList<String>();
		outPut= new ArrayList<String>();
		//If determine system type so knows where log file is
		if(System.getProperty("os.name").toLowerCase().contains("win"))
			openFile(pcLogAddress);
		else
			openFile(macLogAddress);
		
		
	}

	//windows at C:\Program Files (x86)\Hearthstone\Logs\Power.log
	// see : https://github.com/jleclanche/fireplace/wiki/How-to-enable-logging
	
	public static void main(String args[]) {
		App app = new App();
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

	private void prcessingLine(String line) {

		if (new Log_Reader().lineContainsCards(line)) {
			String cardName;

			if (new Log_Reader().isFriendlyCards(line)) {
				friendlyCards.add(new Log_Reader().idToNames(line.substring(line.lastIndexOf('=') + 1)));
				cardName=friendlyCards.get(friendlyCards.size() - 1);				 
//				outPut.add(cardName);
				/*
				 * use frendlyCards to develop your code
				 */

			} else if (new Log_Reader().isOpponentCards(line)) {

				opponentCards.add(new Log_Reader().idToNames(line.substring(line.lastIndexOf('=') + 1)));
				cardName=opponentCards.get(opponentCards.size()-1);
				outPut.add(cardName);
				//System.out.println(opponentCards.get(opponentCards.size() - 1));
				
				/*
				 * use oppoinentCards to develop your code 
				 */
			}
			

		}
		else if(new Log_Reader().isNewGame(line)) {
			if(!outPut.isEmpty()) {
				outPut.clear();
				friendlyCards.clear();
				opponentCards.clear();
			}
			
		}

	}

}
