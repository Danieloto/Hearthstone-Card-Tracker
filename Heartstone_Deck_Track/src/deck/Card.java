package deck;

import javax.swing.ImageIcon;

public class Card {
	public int health, armor, mana, damage;
	public String rarity, type, desc;
	public ImageIcon largeIcon, barIcon = null;
	
	
	//Builds a basic card object with default values
	public Card(){
		health = 0;
		armor = 0;
		mana = 0;
		damage = 0;
		rarity = "";
		type = "";
		barIcon = null;
		largeIcon = null;
		desc = "";
	}
	
	public int Health(){
		if(Type() == "weapon" || Type() == "spell")
			return 0;
		
		return health;
	}
	
	public int Cost(){
		if(Type() == "hero")
			return 0;
		
		return mana;
	}
	
	public String Rarity(){
		return rarity;
	}
	
	public String Type(){
		return type;
	}
	
	public int Armor(){
		return armor;
	}
	
	public int Damage(){
		return damage;
	}
	
	public ImageIcon barpicture(){
		return barIcon;
	}
	
	public ImageIcon largepicture(){
		return largeIcon;
	}
	
	public String description() {
		return desc;
	}
}
