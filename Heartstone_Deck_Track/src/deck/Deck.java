package deck;

import javax.swing.ImageIcon;

public class Deck{

	private Card[] deck; //object array of cards to make up deck.
	int count;
	private ImageIcon[] images = new ImageIcon[30];
	
	public Deck(){ //Creates Deck
		count = 0;
		deck=new Card[30];
	}
	
	public void addCard(Card card){
		if(count < 30){ //Adds card if deck is not full
			deck[count] = card;
			images[count] = card.largepicture();
			count++;//Changes card total
		}
	}
	
	public void removeCard(Card card){ //Removes specified card
		for(int i = 0; i < 30; i++){
			if(deck[i] == card){
				for(int j = 0; j + i < 29; j++){ //Shifts cards back a spot 
					deck[i+j] = deck[i+j+1]; //to remove selected card
				}
				deck[29] = null; //Sets last card to empty
				count--; //Changes card total
			}
		}
	}
	
	public Card getCard(int num){
		return deck[num];
	}
	
	public Card getCard(Card card){
		int loc = -1;
		for(int i = 0; i < 30; i++){
			if(deck[i] == card){
				loc = i;
			}
		}
		if(loc == -1){
			throw new java.lang.Error("Card not in deck");
		}
		return deck[loc];
	}
	
	public int getCardLocation(Card card){
		int loc = -1;
		for(int i = 0; i < 30; i++){
			if(deck[i] == card){
				loc = i;
			}
		}
		if(loc == -1){
			throw new java.lang.Error("Card not in deck");
		}
		return loc;
	}
	
	public void sortDeckMana(Deck deck){
		Card temp;
		int j;
		for(int i = 1; i<deck.count; i++){
			temp = deck.deck[i];
			for(j = i-1; j > -1 && deck.deck[j].mana > deck.deck[i].mana; j--){
				deck.deck[j+1] = deck.deck[j];
			}
			deck.deck[j+1] = temp;
		}
	}
	
	public void sortDeckHealth(Deck deck){
		Card temp;
		int j;
		for(int i = 1; i<deck.count; i++){
			temp = deck.deck[i];
			for(j = i-1; j > -1 && deck.deck[j].health > deck.deck[i].health; j--){
				deck.deck[j+1] = deck.deck[j];
			}
			deck.deck[j+1] = temp;
		}
	}
	
	public void sortDeckArmor(Deck deck){
		Card temp;
		int j;
		for(int i = 1; i<deck.count; i++){
			temp = deck.deck[i];
			for(j = i-1; j > -1 && deck.deck[j].armor > deck.deck[i].armor; j--){
				deck.deck[j+1] = deck.deck[j];
			}
			deck.deck[j+1] = temp;
		}
	}
	
	public void sortDeckDamage(Deck deck){
		Card temp;
		int j;
		for(int i = 1; i<deck.count; i++){
			temp = deck.deck[i];
			for(j = i-1; j > -1 && deck.deck[j].damage > deck.deck[i].damage; j--){
				deck.deck[j+1] = deck.deck[j];
			}
			deck.deck[j+1] = temp;
		}
	}
	
	public void sortDeckRarity(Deck deck){
		Card temp;
		int j,a,b;
		a = b = 0;
		for(int i = 1; i<deck.count; i++){
			temp = deck.deck[i];
			for(j = i-1; j > -1; j--){
				if(deck.deck[i].rarity == "free"){
					a = 0;
				}
				else if(deck.deck[i].rarity == "common"){
					a = 1;
				}
				else if(deck.deck[i].rarity == "rare"){
					a = 2;
				}
				else if(deck.deck[i].rarity == "epic"){
					a = 3;
				}
				else if(deck.deck[i].rarity == "legendary"){
					a = 4;
				}
				if(deck.deck[j].rarity == "free"){
					b = 0;
				}
				else if(deck.deck[j].rarity == "common"){
					b = 1;
				}
				else if(deck.deck[j].rarity == "rare"){
					b = 2;
				}
				else if(deck.deck[j].rarity == "epic"){
					b = 3;
				}
				else if(deck.deck[j].rarity == "legendary"){
					b = 4;
				}
				
				if(b > a){
					deck.deck[j+1] = deck.deck[j];
				}
			}
			deck.deck[j+1] = temp;
		}
	}
}

