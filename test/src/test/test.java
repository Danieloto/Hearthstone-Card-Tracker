package test;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;

import javax.swing.ImageIcon;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.awt.*;  
import javax.swing.JFrame;  

public class test extends Canvas{
	
	static Card card = new Card();

	public static void main(String[] args) {
		
		try {
			//get info from repo
			HttpResponse<JsonNode> response = Unirest.get("https://omgvamp-hearthstone-v1.p.mashape.com/cards/Polymorph").
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
					
					int x2 = 20, y = 235, w = 256, h = 50;
					Image img = icon.getImage();
					BufferedImage nameBar = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
					nameBar.getGraphics().drawImage(img, 0, 0, w, h, x2, y, x2 + w, y + h, null);
					
					x2 = 20;
					y = 70;
					w = 60;
					BufferedImage manaBar = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
					manaBar.getGraphics().drawImage(img, 0, 0, w, h, x2, y, x2 + w, y + h, null);
					
					int wid = nameBar.getWidth()+manaBar.getWidth()+5;
					BufferedImage fullBar = new BufferedImage(wid,h + 5, BufferedImage.TYPE_INT_ARGB);
					Graphics2D g2 = fullBar.createGraphics();
				    Color oldColor = g2.getColor();
				    g2.setPaint(Color.WHITE);
			        g2.fillRect(0, 0, wid, h);
			        g2.setColor(oldColor);
			        g2.drawImage(nameBar, null, 0, 0);
			        g2.drawImage(manaBar, null, nameBar.getWidth() + 5, 0);
			        g2.dispose();
			        
			        ImageIcon icon2 = new ImageIcon(("Image/" + "blank.png").replaceAll("\\s+", ""));
			        img = icon2.getImage();
			        BufferedImage blank = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
			        blank.getGraphics().drawImage(img, 0, 0, null);
			        
			        wid = fullBar.getWidth() + blank.getWidth() + 5;
			        BufferedImage blankFull = new BufferedImage(wid, h + 5, BufferedImage.TYPE_INT_ARGB);
			        g2 = blankFull.createGraphics();
			        oldColor = g2.getColor();
				    g2.setPaint(Color.WHITE);
			        g2.fillRect(0, 0, wid, h);
			        g2.setColor(oldColor);
			        g2.drawImage(blank, null, 0, 0);
			        g2.drawImage(fullBar, null, blank.getWidth() + 5, 0);
			        g2.setFont(g2.getFont().deriveFont(30f));
			        g2.drawString("Hello World!", 100, 50);
			        g2.dispose();
			        
			        ImageIcon bar = new ImageIcon(blankFull);
			        card.barIcon = bar;
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
					if(s.equals("Enchantment")) {
						if(response.getBody().getArray().length() > 1) {
							body = response.getBody().getArray().get(1).toString();
							body = body.replaceAll("\"", "");
							body = body.replaceAll("\\{", "");
							body = body.replaceAll("\\}", "");
							bodySplit = body.split(":|,");
							x = -1;
							continue;
						}
					}
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
		}
		catch(UnirestException e) { 
			//httpResponse fails
			System.out.print(e);
		}
		catch(MalformedURLException e) { 
			//downloading image fails
			System.out.print(e);
		}
		
		test m=new test();  
        JFrame f=new JFrame();  
        f.add(m);  
        f.setSize(400,100);  
        f.setVisible(true);
	}
	
	public void paint(Graphics g) {
          
		Image i = card.barIcon.getImage();
		g.drawImage(i, 0, 0, null);
    }  
}
