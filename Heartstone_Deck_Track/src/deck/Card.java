package deck;

import java.awt.Image;

public abstract class Card {
	public int health, armor, mana, damage;
	public String rarity, type;
	Image largeIcon, barIcon = null;
	
	
	//Builds a basic card object with default values
	public Card(){
		health = 0;
//		armor = 0;
		mana = 0;
		damage = 0;
		rarity = "";
		type = "";
		barIcon = null;
		largeIcon = null;
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
	
//	public int Armor(){
//		return armor;
//	}
	
	public int Damage(){
		return damage;
	}
	
	public Image picture(){
		return icon;
	}
	
}