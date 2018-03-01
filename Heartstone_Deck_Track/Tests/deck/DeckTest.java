package deck;

import static org.junit.Assert.*;

import org.junit.Test;

public class DeckTest{

	@Test
	public void decktest(){
		Deck deck = new Deck();
		Card card1 = new Card();
		Card card2 = new Card();
		Card card3 = new Card();
		Card card4 = new Card();
		Card card5 = new Card();
		card1.mana = 4;
		assertEquals(deck.count, 0);
		deck.addCard(card1);
		assertEquals(deck.count, 1);
		deck.addCard(card2);
		deck.addCard(card3);
		deck.addCard(card4);
		deck.addCard(card5);
		assertEquals(deck.count, 5);
		deck.removeCard(card2);
		assertEquals(deck.count, 4);
		assertEquals(deck.getCard(card1),card1);
		assertEquals(deck.getCard(card3),card3);
		assertEquals(deck.getCard(1),card3);
		assertEquals(deck.getCardLocation(card3), 1);
		
		
	}
	
	@Test
	public void CardTest(){
	}

}
