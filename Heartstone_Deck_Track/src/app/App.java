package app;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import logReader.Log_Reader;

public class App {

	private boolean stillOn;

	private ArrayList<String> friendlyCards;
	private ArrayList<String> opponentCards;

	public App(String logLocation) {
		stillOn = true;
		friendlyCards = new ArrayList<String>();
		opponentCards = new ArrayList<String>();

		openFile(logLocation);
	}

	//windows at C:\Program Files (x86)\Hearthstone\Logs\Power.log
	// see : https://github.com/jleclanche/fireplace/wiki/How-to-enable-logging
	
	public static void main(String args[]) {
		App app = new App("/Applications/Hearthstone/Logs/Power.log");

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
					line = br.readLine();
					if (br.readLine() != null) {

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

			if (new Log_Reader().isFriendlyCards(line)) {

				friendlyCards.add(new Log_Reader().idToNames(line.substring(line.lastIndexOf('=') + 1)));
				 System.out.println(friendlyCards.get(friendlyCards.size() - 1));
				
				/*
				 * use frendlyCards to develop your code
				 */

			} else if (new Log_Reader().isOpponentCards(line)) {

				opponentCards.add(new Log_Reader().idToNames(line.substring(line.lastIndexOf('=') + 1)));
				//System.out.println(opponentCards.get(opponentCards.size() - 1));
				
				/*
				 * use oppoinentCards to develop your code 
				 */
			}

		}

	}

}
