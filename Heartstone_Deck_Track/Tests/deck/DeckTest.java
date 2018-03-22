package deck;

import static org.junit.Assert.*;

import org.junit.Test;

import server.Server;

public class DeckTest{

	@Test
	public void decktest(){
		Deck deck = new Deck();
		Card card1 = new Card();
		Card card2 = new Card();
		Card card3 = new Card();
		Card card4 = new Card();
		Card card5 = new Card();
		Card card6 = new Card();
		card5 = Server.createCard("Ysera");
		card1.name = "test";
		card2.name = "test1";
		card3.name = "test2";
		card4.name = "test2";
		card6.name = "test2";
		assertEquals(deck.count, 0);
		deck.addCard(card1);
		assertEquals(deck.count, 1);
		deck.addCard(card2);
		//System.out.println(deck.count);
		deck.addCard(card3);
		//System.out.println(deck.count);
		deck.addCard(card4);
		//System.out.println(deck.count);
		deck.addCard(card5);
		//System.out.println(deck.count);
		assertEquals(deck.count, 5);
		deck.removeCard("test1");
		assertEquals(deck.count, 4);
		deck.addCard(card6);
		assertEquals(deck.count, 4);
		assertEquals(deck.getCard(card1),card1);
		assertEquals(deck.getCard(card3),card3);
		assertEquals(deck.getCard(1),card3);
		assertEquals(deck.getCardLocation(card3), 1);
		deck.addCard(Server.createCard("Ysera"));
		assertEquals(deck.count, 5);
		for(int abc1 = 0; abc1 < deck.getSize(); abc1++){
			System.out.println(deck.getCard(abc1).Name());
		}
		deck.removeCard("test");
		deck.removeCard("Ysera");
		System.out.println("");
		for(int abc1 = 0; abc1 < deck.getSize(); abc1++){
			System.out.println(deck.getCard(abc1).Name());
		}
		deck.clearDeck();
		assertEquals(deck.count, 0);
		for(int abc1 = 0; abc1 < deck.getSize(); abc1++){
			System.out.println(deck.getCard(abc1).Name());
		}
		
		card1.name = "f";
		card2.name = "e";
		card3.name = "d";
		card4.name = "c";
		card5.name = "b";
		card6.name = "a";
		deck.addCard(card1);
		deck.addCard(card2);
		deck.addCard(card3);
		deck.addCard(card4);
		deck.addCard(card5);
		deck.addCard(card6);
		System.out.println("Original: Should be fedcba");
		for(int abc1 = 0; abc1 < deck.getSize(); abc1++){
			System.out.print(deck.getCard(abc1).Name());
		}
		card1.mana = 1;
		card2.mana = 2;
		card3.mana = 3;
		card4.mana = 4;
		card5.mana = 5;
		card6.mana = 6;
		deck.sortDeckMana();
		System.out.println("");
		System.out.println("Mana: Should be fedcba");
		for(int abc1 = 0; abc1 < deck.getSize(); abc1++){
			System.out.print(deck.getCard(abc1).Name());
		}
		card1.armor = 6;
		card2.armor = 5;
		card3.armor = 4;
		card4.armor = 3;
		card5.armor = 2;
		card6.armor = 1;
		deck.sortDeckArmor();
		System.out.println("");
		System.out.println("Armor: Should be abcdef");
		for(int abc1 = 0; abc1 < deck.getSize(); abc1++){
			System.out.print(deck.getCard(abc1).Name());
		}
		card1.damage = 1;
		card2.damage = 2;
		card3.damage = 3;
		card4.damage = 4;
		card5.damage = 5;
		card6.damage = 6;
		deck.sortDeckDamage();
		System.out.println("");
		System.out.println("Damage: Should be fedcba");
		for(int abc1 = 0; abc1 < deck.getSize(); abc1++){
			System.out.print(deck.getCard(abc1).Name());
		}
		card1.rarity = "Legendary";
		card2.rarity = "Epic";
		card3.rarity = "Rare";
		card4.rarity = "Free";
		card5.rarity = "Common";
		card6.rarity = "Free";
		deck.sortDeckRarity();
		System.out.println("");
		System.out.println("Rarity: Should be fedbca or fedbac");
		for(int abc1 = 0; abc1 < deck.getSize(); abc1++){
			System.out.print(deck.getCard(abc1).Name());
		}
	}
	
	@Test
	public void CardTest(){
		Card card = new Card();
		card.desc = "Big monster";
		card.damage = 9;
		card.health = -1;
		card.largepicture();
		
		card.id = "insert id";
		assertEquals(card.id, "insert id");
	}

}
