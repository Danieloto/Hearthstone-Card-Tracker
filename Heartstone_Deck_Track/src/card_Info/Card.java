package card_Info;

public abstract class Card {
	public int health, armor, mana, damage;
	public String rarity, type;
	
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
	
}