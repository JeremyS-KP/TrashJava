package pkg1;


import java.util.EmptyStackException;
import java.util.HashSet;
import java.util.Set;
public class Trash {
	private DeckOfCards deck;
	private DiscardPile pile;
	private Hand player1;
	private Hand player2;
	private Set<Integer> p1NeedSet;
	private Set<Integer> p2NeedSet;
	private int[] p1NotWild;
	private int[] p2NotWild;
	// in order to play, you must already have two players (hands) and a deck
	public Trash( Hand player1, Hand player2) {
		this.player1 = player1;
		this.player2 = player2;
		deck = new DeckOfCards();
		p1NeedSet = new HashSet<>(); // initialize set to represent the needs of player 1
		p2NeedSet = new HashSet<>(); // initialize set to represent the needs of player 2
		pile = new DiscardPile(); // initialize a discard pile for each players to donate un-needed cards to
	}// end Trash
	private void initializeNeeds(Set<Integer> needSet, int n) {
		for(int i = 1; i <= n; i++) { // for the amount of cards in hand
			needSet.add(i); // add a value incrementing by 1 starting at 1
		}// end for

	} // end initializeNeeds
	
	
	private void initializeNotWild(int[] arr) { // keep track of the that haven't been occupied by a wild card.
		for(int i = 0; i < arr.length; i++) {
			arr[i] = i + 1; // if i = 0, then the 0th index = 1, and so on because cards cannot be 0 value
		}
	} // end initializeNotWild
	private int minInArray(int[] arr) { // used to find the minimum in an array, so that the algorithm will always place the wild at the min non-wild/non-flipped
		int min = -1;
		if(arr.length > 0) {
			min = 99;
			for(int i = 0; i<arr.length; i++) {
				if(arr[i] < min) {
					 min = arr[i];
				}
			}
		}
		return min;
		// used to find the minimum in an array, so that the algorithm will always place the wild at the min non-wild/non-flipped
		// it is important to note that this doesn't necessarily have to be how wilds are placed, it truly is up to the user in
		// every day situations, however the computer just operates on a basis that keeps the game simply and intuitive.
		// similarly to how mathematicians utilize the axiom of choice.
	} // end minInArray
	public void playTrash(int numPlayer1Cards, int numPlayer2Cards) { // recursive method, housing Trash Algorithm
		if( numPlayer1Cards < 1) { // base case 1, player1 has less than 1 card in hand
			System.out.println("Player 1 wins the game of trash!");
		} // end basecase1
		else if (numPlayer2Cards < 1) { // base case 2, player2 has less than 1 card in hand
			System.out.println("Player 2 wins the game of trash!");
		} // end basecase2
		else { // otherwise, 
			boolean player1Turn = true; // keep track of whose turn it is
			deck = new DeckOfCards();  // (re)assign deck to a new, unused/undepleted deck.
			pile = new DiscardPile(); // (re)assign discard pile to a new, unused/unfilled discard pile.
			System.out.println("\tPlayer 1 has " + numPlayer1Cards + " cards"); // display how many cards player 1 has, also notes how close player 1 is to winning.
			System.out.println("\tPlayer 2 has " + numPlayer2Cards + " cards"); // display how many cards player 2 has, also notes how close player 2 is to winning.
			boolean draw = false; // by default, this hand is not yet a draw. ( edge case )
			initializeNeeds(p1NeedSet, numPlayer1Cards); // (re)initialize needs set given a needSet and number of cards are necessary.
			initializeNeeds(p2NeedSet, numPlayer2Cards); // (re)initialize needs set given a needSet and number of cards are necessary.
			p1NotWild = new int[numPlayer1Cards];
			p2NotWild = new int[numPlayer2Cards];
			initializeNotWild(p1NotWild);
			initializeNotWild(p2NotWild);
			deck.shuffle(); // shuffle the deck ( randomizes cards )
			System.out.println("\tThe deck has been shuffled"); // display that the deck has been shuffled
			System.out.println("\n");
			deck.dealCardsOneHand(numPlayer1Cards, player1); // deal the parameter number of cards to player 1
			deck.dealCardsOneHand(numPlayer2Cards, player2); // deal the parameter number of cards to player 2
			while(!p1NeedSet.isEmpty() && !p2NeedSet.isEmpty()) { // per hand loop, checking each time to see if each player has needs.
				try {
					if(player1Turn) { // check if it is player 1's turn
						System.out.println(" Player 1's turn\n|---------------|");
						play(player1, p1NeedSet, p1NotWild); // call play method, given player 1's hand and player 1's needs.
						player1Turn = false; // after player1's turn, note that it it is no longer player 1's turn
					} // end if
					else { // if it is not player 1's turn, then it must be player 2's turn
						System.out.println("Player 2's turn\n|---------------|");
						play(player2, p2NeedSet, p2NotWild); // call play method, given player 2's hand and player 2's needs.
						player1Turn = true; // after player2's turn, note that it is now player 1's turn
					} // end else
				} // end try
				catch(EmptyStackException e){ // catch the rare edge case that the deck depletes
					System.out.println("This game is a draw on account of the deck being depleted");
					draw = true; // game becomes a draw. no one wins.
					break; // break the while loop.
				} // end catch
			} // end while
			if(!draw) { // assuming the game hasn't ended in a draw
				if(p1NeedSet.isEmpty()) { // if player 1's needs have been fully satisfied
					System.out.println("Player 1 Wins this hand\n");
					playTrash(numPlayer1Cards -1, numPlayer2Cards); // reduce the number of cards player 1 has for the next hand.
				} // end if
				else if(p2NeedSet.isEmpty()) { // if player 2's needs have been fully satisfied
					System.out.println("Plaer 2 Wins this hand\n");
					playTrash(numPlayer1Cards, numPlayer2Cards - 1); // reduce the number of cards player 2 has for the next hand.
				} // end else if
				else {
					throw new IllegalArgumentException(); // debugging, catch any impossible edge cases
				} // end else
			} // end if
			else { // if the game is a draw,
				playTrash(numPlayer1Cards, numPlayer2Cards); // replay the hand at the same number of cards for both player 1 and player 2
			} // end else
		} // end else	
	} // end playTrash
	private void play(Hand player, Set<Integer> needSet, int[] notWild) { // play method should be private because it will only be called within Trash class
		System.out.println("Cards needed before turn: " + needSet +"\n");
		if(!pile.isEmpty() && needSet.contains(pile.getTopCard().getCardValue())) { // check if discard pile is not empty and if the player needs the top card of the discard pile.
			Card current = pile.drawCard(); // draw from the discard pile, if previous condition satisfied
			System.out.println("the " + current.toString() + " was drawn from the discard pile");
			boolean needCon = needSet.contains(current.getCardValue()); // define condition of the needSet having need of the current card (t/f)
			boolean wildCon = current.getCardValue() == 11; // define condition of the current card being a jack (t/f)
			if (wildCon) { // back-door way of getting into the while loop, otherwise with mutli-conditional while loop, it ends in infinite loop.
				needCon = true; // Force needCon to be true
			}
			while(needCon) {
				if(wildCon && needCon) { // first check if both are true, if both are true, this is because of back-door entry
					needCon = false; // set need condition to false so that the loop progresses immediately to the wildCon loop
				}
				while(needCon) {	// check if the card is needed
					System.out.println("the " + current.toString() + " was needed");
					int cardNum = current.getCardValue(); // store the value of the current
					notWild[cardNum - 1] = 14; // disqualifies card from being replaced by a wild
					player.giveHandCardAtIndex(cardNum - 1, current); // place the current card into an index correlating to its value
					Card newCurrent = player.removeCardAtIndex(cardNum); // new card to look at is the card displaced after moving the old one
					System.out.println("The " + current.toString() + " now occupies the spot of card number " + cardNum );
					System.out.println("the card that was displaced by the " + current.toString() + " was the " + newCurrent.toString());
					needSet.remove(current.getCardValue()); // remove that cards value from the set
					current = newCurrent; // define the new card as your current card.
					needCon = needSet.contains(current.getCardValue());	
				} // end while
				wildCon = current.getCardValue() == 11;
				while(wildCon) {
					int min = minInArray(notWild);
					if(min <=10) {
						System.out.println("WILD CARD!, the " + current.toString() + " was needed!");
						//System.out.println(min);
						notWild[min -1] = 14; // disqualifies an already wild card from being replaced by a wild.
						player.giveHandCardAtIndex(min - 1, current); // the action of inserting the jack into 
						Card newCurrent = player.removeCardAtIndex(min);
						System.out.println("The " + current.toString() + " now occupies the spot of card number " + min );
						System.out.println("the card that was displaced by the " + current.toString() + " was the " + newCurrent.toString());
						current = newCurrent; // define the new card as your current card.
						wildCon = current.getCardValue() == 11;
					}
					else {
						break;
					}
				}
				needCon = needSet.contains(current.getCardValue());
				wildCon = current.getCardValue() == 11;
			}
			System.out.println("the " + current.toString() + " was not needed, it has been discarded");
			pile.discard(current); // discard the un-needed current card after while loop terminates
			System.out.println("Cards needed at the end of the turn: " + needSet);
			System.out.println("There are " + deck.getSize() + " cards in the deck");
			System.out.println("There are " + pile.getSize() + " cards in the discard pile\n|---------------|");
			System.out.println("\n");
		} // end if
		else if(!deck.isEmpty()) { // if the discard pile is empty OR, if the top card of the discard pile is not needed,
			Card current = deck.drawCard(); // draw a card from the deck
			System.out.println("the " + current.toString() + " was drawn from the deck");
			boolean needCon = needSet.contains(current.getCardValue());
			boolean wildCon = current.getCardValue() == 11;
			if (wildCon) { // back-door way of getting into the while loop, otherwise with mutli-conditional while loop, it ends in infinite loop.
				needCon = true; // Force needCon to be true
			}
			while(needCon) {
				if(wildCon && needCon) { // first check if both are true, if both are true, this is because of back-door entry
					needCon = false; // set need condition to false so that the loop progresses immediately to the wildCon loop
				}
				while(needCon) {	// check if the card is needed
					System.out.println("the " + current.toString() + " was needed");
					int cardNum = current.getCardValue(); // store the value of the current
					notWild[cardNum - 1] = 14; // disqualifies card from being replaced by a wild
					player.giveHandCardAtIndex(cardNum - 1, current); // place the current card into an index correlating to its value
					Card newCurrent = player.removeCardAtIndex(cardNum); // new card to look at is the card displaced after moving the old one
					System.out.println("The " + current.toString() + " now occupies the spot of card number " + cardNum );
					System.out.println("the card that was displaced by the " + current.toString() + " was the " + newCurrent.toString());
					needSet.remove(current.getCardValue()); // remove that cards value from the set
					current = newCurrent; // define the new card as your current card.
					needCon = needSet.contains(current.getCardValue());	
				} // end while
				wildCon = current.getCardValue() == 11;
				while(wildCon) {
					int min = minInArray(notWild);
					if(min<=10) {
						System.out.println("WILD CARD!, the " + current.toString() + " was needed!");
						// System.out.println(min);
						notWild[min -1] = 14; // disqualifies an already wild card from being replaced by a wild.
						player.giveHandCardAtIndex(min - 1, current); // the action of inserting the jack into 
						Card newCurrent = player.removeCardAtIndex(min);
						System.out.println("The " + current.toString() + " now occupies the spot of card number " + min );
						System.out.println("the card that was displaced by the " + current.toString() + " was the " + newCurrent.toString());
						current = newCurrent; // define the new card as your current card.
						wildCon = current.getCardValue() == 11;
					}
					else {
						
						break;
					}
				}
				needCon = needSet.contains(current.getCardValue());
				wildCon = current.getCardValue() == 11;
				
			}
			System.out.println("the " + current.toString() + " was not needed, it has been discarded");
			pile.discard(current);// discard the un-needed current card after while loop terminates
			System.out.println("\nCards needed at the end of the turn: " + needSet);
			System.out.println("There are " + deck.getSize() + " cards in the deck");
			System.out.println("There are " + pile.getSize() + " card(s) in the discard pile\n|---------------|");
			System.out.println("\n");
			
		} // end else if
		else if(deck.isEmpty()) { // if the deck is empty, throw an EmptyStackException, denoting that the deck is empty ( which functions as a stack )
			throw new EmptyStackException();
		} // end else if
	} // end private play
} // end trash class
