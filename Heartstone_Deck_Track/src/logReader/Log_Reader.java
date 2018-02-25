package logReader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// logLocation: /Applications/Hearthstone/Logs/Power.log

/* 1.GameState.DebugPrintEntitiesChosen() takes you to the initial hand 
 * 2. GameState.DebugPrintOptions() takes you to the options you have 
 * 3. PowerTaskList.DebugPrintPower() -     SHOW_ENTITY - Updating Entity=[entityName=UNKNOWN ENTITY [cardType=INVALID] 
 * 		a.takes you new cards drawn by you 
 * 		b.new cards played by your opponent
 * 		c.enhancement of cards, 4 digit cardID format ex.CS2_122e
 * 4. tagsInPracticeModer: player 1 friendly late start(4 cards and a coin)
 *          	               player 2 opposing 
 * 
 * 
 * 
 * */

public class Log_Reader {
	private boolean stillOn;
	private BufferedWriter  friendlyCards_bw;
	

	public Log_Reader(String logLocation) {

		this.stillOn = true;

		openFile(logLocation);
	}

	public void openFile(String logLocation) {

		try {
			FileInputStream fstream = new FileInputStream(logLocation);
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			FileOutputStream freindlyCard_fos = new FileOutputStream("frendlyCards.txt");
			OutputStreamWriter ow = new OutputStreamWriter(freindlyCard_fos);
			friendlyCards_bw = new BufferedWriter(ow);
			
			
			
			String line;
			while (stillOn) {
				line = br.readLine();
				if (line != null) {

					addingCardsToArray(line);

					// pw.println(line);

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

	private void addingCardsToArray(String line) {
		

		// pwFriendlyCards.println(cardID);
		// pwFriendlyCards.flush();

		if (line.contains(
				"PowerTaskList.DebugPrintPower() -     SHOW_ENTITY - Updating Entity=[entityName=UNKNOWN ENTITY [cardType=INVALID]")) {
			// System.out.println(line.substring(line.lastIndexOf('=')+1));

			if (line.charAt(line.indexOf("player=") + 7) == '1' && Character.isDigit(line.charAt(line.length() - 1))) {
				System.out.println(idToNames(line.substring(line.lastIndexOf('=') + 1)));// print out card names to console
				/*try {
				friendlyCards_bw.write(idToNames(line.substring(line.lastIndexOf('=') + 1)));
				friendlyCards_bw.newLine();
				friendlyCards_bw.flush();
				}catch(Exception e) {
					e.printStackTrace();
				}
				*/
			} else if (line.charAt(line.indexOf("player=") + 7) == '2'
					&& Character.isDigit(line.charAt(line.length() - 1))) {
				// System.out.println(idToNames(line.substring(line.lastIndexOf('=') + 1)));
			}

		}

	}

	private String idToNames(String id) {
		String name = "";
		switch (id) {
		case "EX1_277":
			name = "Arcane Missiles";
			break;
		case "CS2_168":
			name = "Murloc Raider";
			break;
		case "CS2_025":
			name = "Arcane Explosion";
			break;
		case "CS2_172":
			name = "Bloodfen Raptor";
			break;
		case "EX1_015":
			name = "Novice Engineer";
			break;
		case "CS2_120":
			name = "River Crocolisk";
			break;
		case "CS2_023":
			name = "Arcane Intellect";
			break;
		case "CS2_122":
			name = "Raid Leader";
			break;
		case "CS2_124":
			name = "Wolfrider";
			break;
		case "CS2_029":
			name = "Fireball";
			break;
		case "CS2_119":
			name = "Oasis Snapjaw";
			break;
		case "CS2_022":
			name = "Polymorph";
			break;
		case "CS2_179":
			name = "Sen'jin Shieldmasta";
			break;
		case "EX1_593":
			name = "Nightblade";
			break;
		case "CS2_200":
			name = "Boulderfist Ogre";
			break;

		}

		return name;
	}

}

