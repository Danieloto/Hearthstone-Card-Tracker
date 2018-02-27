package logReader;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Log_Reader {

	public Log_Reader() {

	}

	public boolean lineContainsCards(String line) {

		return line.contains(
				"PowerTaskList.DebugPrintPower() -     SHOW_ENTITY - Updating Entity=[entityName=UNKNOWN ENTITY [cardType=INVALID]");
	}

	public boolean isFriendlyCards(String line) {
		return line.charAt(line.indexOf("player=") + 7) == '1' && Character.isDigit(line.charAt(line.length() - 1));
	}

	public boolean isOpponentCards(String line) {
		return line.charAt(line.indexOf("player=") + 7) == '2' && Character.isDigit(line.charAt(line.length() - 1));
	}
	
	public boolean isNewGame(String line) {
		return line.contains("CREATE_");
	}

	public String idToNames(String id) {
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
