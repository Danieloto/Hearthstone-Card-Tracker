package deck;

import javax.swing.ImageIcon;

public class Card {
	public int health, armor, mana, damage;
	public String rarity, type, desc, name, id;
	public ImageIcon largeIcon, barIcon;
	
	
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
		name = "";
		id = "";
	}
	
	public int Health(){
		if(Type() == "weapon" || Type() == "spell")
			return 0;
		
		return health;
	}
	
	public int Cost(){
		if(mana >= 0){
			if(Type() == "hero")
				return 0;
		
			return mana;
		}
		else
			throw new java.lang.Error("Cost cannot be less than 0");
	}
	
	public String Rarity(){
		return rarity;
	}
	
	public String Type(){
		return type;
	}
	
	public String Name(){
		return name;
	}
	
	public String ID(){
		return id;
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
		largeIcon = new ImageIcon("Dog.jpg");
		return largeIcon;
	}
	
	public String description() {
		return desc;
	}
}
