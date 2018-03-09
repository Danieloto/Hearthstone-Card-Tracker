package logReader;

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
	//If value was graveyard or something, it was sent to grave!
	public boolean isSendingCardBackToDeck(String line) {
		return line.contains("PowerTaskList.DebugPrintPower() -     HIDE_ENTITY - Entity=[entityName")&&line.contains("value=DECK");
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
	public String[] createTestIDs(int deckTestNumber){
		String[] result= new String[30];
		if(deckTestNumber==0) {
			result[0]=result[1]="EX1_277";//Arcane missles
			result[2]=result[3]="CS2_168";//Murloc Raider
			result[4]=result[5]="CS2_025";//Arcane Explosion
			result[6]=result[7]="CS2_172";//Bloodfen Raptor
			result[8]=result[9]="EX1_015";//Novice Engineer
			result[10]=result[11]="CS2_120";//River Crocolisk
			result[12]=result[13]="CS2_023";//Arcane Intellect
			result[14]=result[15]="CS2_122";//Raid Leader
			result[16]=result[17]="CS2_124";//Wolfrider
			result[18]=result[19]="CS2_029";//Fireball
			result[20]=result[21]="CS2_119";//Oasis Snapjaw
			result[22]=result[23]="CS2_022";//Polymorph
			result[24]=result[25]="CS2_179";//Sen'jin Shieldmasta
			result[26]=result[27]="EX1_593";//Nightblade
			result[28]=result[29]="CS2_200";//Boulderfist Ogre	
		}
		return result;
	}
	public String[] createTestNames(int deckTestNumber) {
		String[] result= new String[30];
		if(deckTestNumber==0) {
		result[0]=result[1]="Arcane Missiles";//Arcane missles
		result[2]=result[3]="Murloc Raider";//Murloc Raider
		result[4]=result[5]="Arcane Explosion";//Arcane Explosion
		result[6]=result[7]="Bloodfen Raptor";//Bloodfen Raptor
		result[8]=result[9]="Novice Engineer";//Novice Engineer
		result[10]=result[11]="River Crocolisk";//River Crocolisk
		result[12]=result[13]="Arcane Intellect";//Arcane Intellect
		result[14]=result[15]="Raid Leader";//Raid Leader
		result[16]=result[17]="Wolf rider";//Wolfrider
		result[18]=result[19]="Fireball";//Fireball
		result[20]=result[21]="Oasis Snapjaw";//Oasis Snapjaw
		result[22]=result[23]="Poly morph";//Polymorph
		result[24]=result[25]="Sen'jin Shieldmasta";//Sen'jin Shieldmasta
		result[26]=result[27]="Nightblade";//Nightblade
		result[28]=result[29]="Boulderfist Ogre";//Boulderfist Ogre	
	}
	return result;
}
}