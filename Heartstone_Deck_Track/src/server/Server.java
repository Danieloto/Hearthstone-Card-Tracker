package server;

import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import deck.Card;
import javax.imageio.ImageIO;

import java.awt.Component;
import java.awt.Image;
import java.awt.image.FilteredImageSource;
import java.io.File;
import java.io.IOException;


@SuppressWarnings("serial")
public class Server extends JComponent {
	
	/*
	 * Creates card object from repository using name or ID
	 * returns Null if it fails
	 * Note: repository only has large images
	 */
	public static Card createCard(String name) {
	
		Card card = new Card();
	
		try {
			
			//get info from repo
			HttpResponse<JsonNode> response = Unirest.get("https://omgvamp-hearthstone-v1.p.mashape.com/cards/" + name).
					header("X-Mashape-Key", "U62MhJxHw4mshNmyI9FDQEIjddITp1thCDWjsnf1b1GRoBalny").header("Accept", "application/json").asJson();
			
			//get body and parse out filler
			String body = response.getBody().getArray().get(0).toString();
			body = body.replaceAll("\"", "");
			body = body.replaceAll("\\{", "");
			body = body.replaceAll("\\}", "");
			
			//split by delimiters. ':' split object and value, and ',' split different objects
			String[] bodySplit = body.split(":|,");
			
			for(int x = 0; x < bodySplit.length; x++) {
				
				String s = bodySplit[x];
			
				//values relevent to card object
				if(s.equals("img")) {
					x++;
					s = bodySplit[x];
					x++;
					String imgLoc = s + ":" + bodySplit[x];
					URL url = new URL(imgLoc);
					ImageIcon icon = new ImageIcon(url);
					card.largeIcon = icon;
					
					5
					
					Image image_disp = icon.getImage();
			        image_disp = createImage(new FilteredImageSource(image_disp.getSource(), new CropImageFilter(0, 188, 286, 50)));
			    		
			    	Image image_disp2 = icon.getImage();
			    	image_disp2 = createImage(new FilteredImageSource(image_disp2.getSource(), new CropImageFilter(0, 35, 75, 50)));

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
					
					//splitting by delimiters also splits most descriptions, so if
					//the next line is longer then longest object name, assume part of description
					while((x < (bodySplit.length - 1)) && (bodySplit[x+1].length() > 12)) {
						x++;
						s = s + "," + bodySplit[x];
					}
					card.desc = s;
				}
			}
			card.name=name;
			return card;
		}
		catch(UnirestException e) { 
			//httpResponse fails
			System.out.print(e);
			return null;
		}
		catch(MalformedURLException e) { 
			//downloading image fails
			System.out.print(e);
			return null;
		}
	}
}
