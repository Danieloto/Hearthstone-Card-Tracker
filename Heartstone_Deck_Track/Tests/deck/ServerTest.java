package deck;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import server.Server;
import deck.Card;

public class ServerTest {
	
	Card cardName;
	Card cardID;
	
	@Before
	public void before() {
		cardName = Server.createCard("Ysera");
		cardID = Server.createCard("EX1_572");
	}

	@Test
	public void health() {
		
		assertEquals(cardName.health, 12);
		assertEquals(cardID.health, 12);
	}

	@Test
	public void mana() {
		
		assertEquals(cardName.mana, 9);
		assertEquals(cardID.mana, 9);
	}
	
	@Test
	public void damage() {
		
		assertEquals(cardName.damage, 4);
		assertEquals(cardID.damage, 4);
	}
	
	@Test
	public void rarity() {
		
		assertEquals(cardName.rarity, "Legendary");
		assertEquals(cardID.rarity, "Legendary");
	}
	
	@Test
	public void type() {
		
		assertEquals(cardName.type, "Minion");
		assertEquals(cardID.type, "Minion");
	}
	
	@Test
	public void desc() {
		
		assertEquals(cardName.desc, "At the end of your turn, add_a Dream Card to_your hand.");
		assertEquals(cardID.desc, "At the end of your turn, add_a Dream Card to_your hand.");
	}
}
