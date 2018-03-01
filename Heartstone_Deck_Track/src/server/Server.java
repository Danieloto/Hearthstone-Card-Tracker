package server;

import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.ImageIcon;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import deck.Card;

public class Server {
	
	/*
	 * Creates card object from repository using name or ID
	 * returns Null if it fails
	 * Note: repository only has large images
	 */
	public static Card createCard(String name) {
	
		Card card = new Card();
	
		try {
			
			HttpResponse<JsonNode> response = Unirest.get("https://omgvamp-hearthstone-v1.p.mashape.com/cards/" + name).
					header("X-Mashape-Key", "U62MhJxHw4mshNmyI9FDQEIjddITp1thCDWjsnf1b1GRoBalny").header("Accept", "application/json").asJson();
			String body = response.getBody().getArray().get(0).toString();
			body = body.replaceAll("\"", "");
			body = body.replaceAll("\\{", "");
			body = body.replaceAll("\\}", "");
			String[] bodySplit = body.split(":|,");
			
			for(int x = 0; x < bodySplit.length; x++) {
				
				String s = bodySplit[x];
			
				if(s.equals("img")) {
					x++;
					s = bodySplit[x];
					x++;
					String imgLoc = s + ":" + bodySplit[x];
					URL url = new URL(imgLoc);
					ImageIcon icon = new ImageIcon(url);
					card.largeIcon = icon;
				}
				if(s.equals("cost")) {
					x++;
					s = bodySplit[x];
					card.mana = Integer.parseInt(s);
				}
				if(s.equals("health")) {
					x++;
					s = bodySplit[x];
					card.health = Integer.parseInt(s);
				}
				if(s.equals("type")) {
					x++;
					s = bodySplit[x];
					card.type = s;
				}
				if(s.equals("attack")) {
					x++;
					s = bodySplit[x];
					card.damage = Integer.parseInt(s);
				}
				if(s.equals("rarity")) {
					x++;
					s = bodySplit[x];
					card.rarity = s;
				}
				if(s.equals("text")) {
					x++;
					s = bodySplit[x];
					while((x < (bodySplit.length - 1)) && (bodySplit[x+1].length() > 12)) {
						x++;
						s = s + "," + bodySplit[x];
					}
					card.desc = s;
				}
			}
			
			return card;
		}
		catch(UnirestException e) {
			System.out.print(e);
			return null;
		}
		catch(MalformedURLException e) {
			System.out.print(e);
			return null;
		}
	}
}
