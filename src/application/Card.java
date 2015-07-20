package application;
import java.util.ArrayList;

/**
 * Basic Card class with suits and ranks
 */
public class Card {

	private Suits suit;
	private Ranks rank;
	

	public Card(Suits suit, Ranks rank) {
		this.suit = suit;
		this.rank = rank;

	}
	
	public Suits getSuit() {
		return suit;
	}

	public void setSuit(Suits suit) {
		this.suit = suit;
	}

	public Ranks getRank() {
		return rank;
	}

	public void setRank(Ranks rank) {
		this.rank = rank;
	}

	@Override
	public String toString() {
		return rank + " of " + suit;
	}

}
