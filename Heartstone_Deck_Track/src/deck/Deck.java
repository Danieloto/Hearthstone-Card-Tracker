package deck;

import javax.swing.ImageIcon;

public class Deck extends Card{

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
				for(int j = 0; j < 29; j++){ //Shifts cards back a spot 
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
	
	public void sortDeck(Deck deck){
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
}
