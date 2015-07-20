package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Deck Class
 */
public class Deck {

	private List<Card> myDeck = new ArrayList<Card>();
	private int topCard;
	public final static int DECK_LIMIT = 52;
	
	/**
	 * Deck constructor that initializes all 52 card objects
	 */
	public Deck() {

		topCard = 0;

		for (Suits s : Suits.values()) {
			for (Ranks r : Ranks.values()) {

				myDeck.add(new Card(s, r));

			}
		}

	}
	
	/**
	 * Method that deals a card
	 * @return Returns the next card in the deck
	 * @throws OutOfCardsException thrown is the deck runs out of cards
	 */
	public Card deal() throws OutOfCardsException {

		if (topCard < DECK_LIMIT) {

			return (myDeck.get(topCard++));

		} else {
			throw new OutOfCardsException("Deck is out of cards");
		}

	}
	
	/**
	 * Method that shuffles the deck
	 * Takes a random index 52 times within the deck and swaps itself with every card
	 */
	public void shuffle() {
		
//		Collections.shuffle(myDeck);
		int randomIndex;

		for (int j = 0; j < DECK_LIMIT; j++) {

			randomIndex = (int) (DECK_LIMIT * Math.random());

			Card tmp = myDeck.get(j);
			myDeck.set(j, myDeck.get(randomIndex));
			myDeck.set(randomIndex, tmp);
		}
		topCard = 0;

	}
	
	/**
	 * toString prints out every card in the deck in order
	 */
	@Override
	public String toString() {
		return "Deck [cards=" + myDeck + "]";
	}

}
