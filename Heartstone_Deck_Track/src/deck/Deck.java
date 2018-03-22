package deck;

import javax.swing.ImageIcon;

public class Deck{

	private Card[] deck; //object array of cards to make up deck.
	int count;
	public ImageIcon[] images = new ImageIcon[30];
	private int inDeck;
	
	public Deck(){ //Creates Deck
		count = 0;
		deck=new Card[30];
	}
	
	public void addCard(Card card){
		if(count < 30){ //Adds card if deck is not full
			inDeck = 0;
			for(int i = 0; i < count; i++){
				if(card.Name().equals(deck[i].Name())){
					inDeck++;
				}
			}
			if(inDeck < 2){
				deck[count] = card;
				images[count] = card.largepicture();
				count++;//Changes card total
			}
		}
		return;
	}
	public int getSize() {
		return count;
	}
	public void removeCard(String name){ //Removes specified card
		for(int i = 0; i < count; i++){
			if(deck[i].Name().equals(name)){
				for(int j = 0; j + i < count; j++){ //Shifts cards back a spot 
					deck[i+j] = deck[i+j+1]; //to remove selected card
				}
				deck[29] = null; //Sets last card to empty
				count--; //Changes card total
				return;
			}
		}
		return;
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
	
	public void clearDeck(){
		String name;
		while(count != 0){
			name = this.deck[0].Name();
			this.removeCard(name);
		}
		return;
	}
	
	public void sortDeckMana(){
		Card temp;
		int j,i;
		for (i = 1; i < count; i++){
	        j = i - 1;
	        while (j >= 0 && deck[i].mana < deck[j].mana ){
	            temp = deck[i];
	            deck[i] = deck[j];
	            deck[j] = temp;
	            i=j;
	            j--;
	        }
	    }
	}
	
	public void sortDeckHealth(){
		Card temp;
		int j,i;
		for (i = 1; i < count; i++){
	        j = i - 1;
	        while (j >= 0 && deck[i].health < deck[j].health ){
	            temp = deck[i];
	            deck[i] = deck[j];
	            deck[j] = temp;
	            i=j;
	            j--;
	        }
	    }
	}
	
	public void sortDeckArmor(){
		Card temp;
		int j,i;
		for (i = 1; i < count; i++){
	        j = i - 1;
	        while (j >= 0 && deck[i].armor < deck[j].armor ){
	            temp = deck[i];
	            deck[i] = deck[j];
	            deck[j] = temp;
	            i=j;
	            j--;
	        }
	    }
	}
	
	public void sortDeckDamage(){
		Card temp;
		int j,i;
		for (i = 1; i < count; i++){
	        j = i - 1;
	        while (j >= 0 && deck[i].damage < deck[j].damage ){
	            temp = deck[i];
	            deck[i] = deck[j];
	            deck[j] = temp;
	            i=j;
	            j--;
	        }
	    }
	}
	
	public void sortDeckRarity(){
		Card temp;
		int j,i;
		for(i = 0; i < count; i++){
			deck[i].setRarityID();
		}
		for (i = 1; i < count; i++){
	        j = i - 1;
	        while (j >= 0 && deck[j].rarityid < deck[i].rarityid ){
	            temp = deck[i];
	            deck[i] = deck[j];
	            deck[j] = temp;
	            i=j;
	            j--;
	        }
	    }
	}
}

