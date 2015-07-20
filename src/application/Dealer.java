package application;
import java.util.ArrayList;

public class Dealer {

	public static void main(String[] args) throws OutOfCardsException {
		
		for (Suits s : Suits.values()){
			System.out.println(s);
		}
		
//		Card[] player1 = new Card[5];
//		Card[] player2 = new Card[5];
//		ArrayList<Card>player = new ArrayList<Card>();
//		Deck playingDeck = new Deck();
//		
//		playingDeck.shuffle();
//		
//		for(int i = 0; i < 5; i++){
//			player1[i] = playingDeck.deal();
//			player2[i] = playingDeck.deal();
//		}
//		System.out.println("---------------");
//		System.out.println("Player 1's Hand:");
//		System.out.println("---------------");
//		for(int i = 0; i < 5; i++){
//			System.out.println(player1[i]);
//			
//		}
//		
//		System.out.println("----------------");
//		System.out.println("Player 2's Hand");
//		System.out.println("---------------");
//		for(int i = 0; i < 5; i++){
//			System.out.println(player2[i]);
//		}
//		
//		
//		System.out.println(playingDeck.toString());
//		
//		System.out.println(Ranks.THREE.getValue());
//		
//		int sum = 0;
//		for (Ranks r : Ranks.values()){
//			sum = sum + r.getValue();
//			
//		}
//		System.out.println(sum);
//		
//
//	}

}
}
