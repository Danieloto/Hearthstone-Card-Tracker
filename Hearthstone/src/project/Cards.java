package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Cards {

	//scans html file to complile list of cards
	public static void main(String[] args) {
		
		File file = new File("rare_cards.html"); //input file
		
		try {
			
			//output file
			PrintWriter out = new PrintWriter(new FileWriter("cards.txt"));
			out.println("Name" + "\t" + "Rarity" + "\t" + "Type" + "\t" + "Sub-Type" + "\t" + "Class"
					+ "\t" + "Cost" + "\t" + "Attack" + "\t" + "HP" + "\t" + "CanGet" + "\t" + "Decription");
			out.println("");
			
			Scanner scanFile = new Scanner(file);
			int x = 0; // 0-8 represent variables below in order. 9 means create new Card
			
			String name = "";
			String rarity = "";
			String type = "";
			String subType = "";
			String clas = "";
			int cost = 0;
			int atk = 0;
			int hp = 0;
			String desc = "";
			boolean collectable = true;
			
			while(scanFile.hasNextLine()) {
				String line = scanFile.nextLine();
				
				//cutoff in file between collectible and uncollectible table
				if(line.equals("Uncollectible")) {
					collectable = false;
					continue;
				}
				
				//Spells have no subType, atk, or hp
				if(type.equals("Spell")) {
					if(x == 3) {
						subType = "";
						scanFile.nextLine();
						x++;
						continue;
					}
					if(x == 6) {
						atk = -1;
						scanFile.nextLine();
						x++;
						continue;
					}
					if(x == 7) {
						hp = -1;
						scanFile.nextLine();
						x++;
						continue;
					}
				}
				
				if(x == 9) {
					out.println(name + "\t" + rarity + "\t" + type + "\t" + subType + "\t" + clas + "\t"
							+ cost + "\t" + atk + "\t" + hp + "\t" + collectable + "\t" + desc);
					x = 0;
					continue;
				}
				
				//isolate variable
				if(x == 8) {
					int cutoff = line.indexOf('>');
					line = line.substring(cutoff + 1);
					line = line.replace("<b>", "");
					line = line.replace("</b>", "");
					line = line.replace("&#160;", "");
					line = line.replace("<i>", "");
					line = line.replace("</i>", "");
				}
				else {
					int cutoff = line.lastIndexOf('<');
					if(cutoff != 0)
						line = line.substring(0, cutoff);
					if(x > 4) {
						cutoff = line.lastIndexOf('<');
						line = line.substring(0, cutoff);
					}
					cutoff = line.lastIndexOf('>');
					line = line.substring(cutoff + 1);
					line = line.replaceAll("\\s+","");
				}
				
				if(x == 0)
					name = line;
				if(x == 1)
					rarity = line;
				if(x == 2)
					type = line;
				if(x == 3)
					subType = line;
				if(x == 4)
					clas = line;
				if(x == 5)
					cost = Integer.parseInt(line);
				if(x == 6)
					atk = Integer.parseInt(line);
				if(x == 7)
					hp = Integer.parseInt(line);
				if(x == 8)
					desc = line;
				if(scanFile.hasNextLine())
					scanFile.nextLine(); //skip useless line after each line
				x++;
			}
			scanFile.close();
			out.close();
		}
		catch(FileNotFoundException e) {}
		catch(IOException e) {}
	}
}
