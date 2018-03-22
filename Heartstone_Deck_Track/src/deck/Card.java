package deck;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.xml.bind.Unmarshaller.Listener;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class Card implements ObservableValue<Number>{
	private Number opacity;
	public int health, armor, mana, damage, rarityid;
	public String rarity, type, desc, name, id;
	public ImageIcon largeIcon, barIcon, manaIcon;
	ArrayList<InvalidationListener> invalListener = new ArrayList<InvalidationListener>();
	ArrayList<ChangeListener> changeListener = new ArrayList<ChangeListener>();

	//Builds a basic card object with default values
	public Card(){
		opacity=1.0;
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
		rarityid = 0;
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
		return largeIcon;
	}
	
	public String description() {
		return desc;
	}
	public void setValue(Number n) {
		opacity=n;
		
	}
	public Number getValue() {
		// TODO Auto-generated method stub
		return opacity;
	}

	@Override
	public void addListener(InvalidationListener listener) {
		invalListener.add(listener);
		
	}

	@Override
	public void removeListener(InvalidationListener listener) {
		invalListener.remove((Listener) listener);
		
	}
	@Override
	public void addListener(ChangeListener listener) {
		changeListener.add(listener);
		
	}

	@Override
	public void removeListener(ChangeListener listener) {
		changeListener.remove(listener);
		
	}
	
	public void setRarityID(){
		switch(rarity){
		case "Legendary":
			rarityid = 4;
			break;
		case "Epic":
			rarityid = 3;
			break;
		case "Rare":
			rarityid = 2;
			break;
		case "Common":
			rarityid = 1;
			break;
		case "Free":
			rarityid = 0;
			break;
		}
		return;
	}
}
