package pkg1;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Stack;
import java.util.Random;

public class DeckOfCards {

    private Stack<Card> deck;
    private Set<Integer> indexSet;
    private Random rand;

    public DeckOfCards() { // define default constructor
        deck = new Stack<>();
        indexSet = new LinkedHashSet<>();
        rand = new Random();
        initializeIndexSet();
        initializeDeck();
    }
    private void initializeIndexSet() {
    	for (int i = 0; i < 52; i++) { 
            indexSet.add(i);
        }
    }
    private void initializeDeck() { // populate deck in order of how cards come out of the box
        String[] suits = {"spades", "diamonds", "clubs", "hearts"};
        for (String suit : suits) {
            for (int rank = 1; rank <= 13; rank++) {
                deck.push(new Card(rank, suit));
            }
        }
    }

    public void shuffle() {
    	int deckSize = deck.size(); // define the size of the deck
        Card[] nonStackDeck = new Card[deckSize]; // initialize an array to populate from stack
        Card[] postShuffleDeck = new Card[deckSize]; // initialize an array to fill after the cards have been assigned random indices
        // Transfer cards from stack to array
        for (int i = 0; i < deckSize; i++) {
            if (!deck.isEmpty()) {
                nonStackDeck[deckSize - 1 - i] = deck.pop();
            }
        }
        int count = 0;
        // Shuffle logic using non-duplicative property of sets and contains method
        while (count < deckSize) {
            int randNum = rand.nextInt(deckSize);
            if (indexSet.contains(randNum)) {
                postShuffleDeck[count] = nonStackDeck[randNum];
                indexSet.remove(randNum);
                count++;
            }
        }
        // Push shuffled cards back into stack
        for (int i = 0; i < deckSize; i++) { 
            deck.push(postShuffleDeck[i]);
        }

        // Reinitialize indexSet for future shuffles
        indexSet.clear();
        initializeIndexSet(); // initialize & populate new indexSet}
    }
    public Card getTopCard() {
        return deck.peek();
    }
    public void dealCardsOneHand(int numCards, Hand player) {
    	if(numCards <= deck.size()) {
    		for(int i = 0; i < numCards; i++) {
    			player.giveHandCard(deck.pop());
    		}
    		
    	}
    	else {
			throw new IllegalArgumentException("Not enough cards in the deck to deal " + numCards + " cards to this player.");
		}
    }
    public void dealCardsTwoHands(int numCards, Hand playerOne, Hand playerTwo) {
    	if (numCards * 2 <= deck.size()) { // make sure that there are enough cards in the deck before dealing cards
    		for(int i = 0; i < numCards; i++) { // alternate between players for each card drawn
    			playerOne.giveHandCard(deck.pop()); // player one gets a card first
    			playerTwo.giveHandCard(deck.pop()); // player two gets a card second
    		}
    	}
    	else {
    	    throw new IllegalArgumentException("Not enough cards in the deck to deal " + numCards + " cards to each player.");
    	}
    }
    
    public void burnCard() {
    	deck.pop();
    }
    public Card drawCard() {
    	return deck.pop();
    }
    public void resetDeck() {
    	deck.clear(); // clear previous deck, so that it can be re-shuffled
        initializeDeck(); // initialize & populate new deck
    	indexSet.clear(); // clear previous indexSet, so shuffle functions right
    	initializeIndexSet(); // initialize & populate new indexSet
        rand = new Random(); // clear any random biases to avoid pseudo random cases.
    }
    public boolean isEmpty() {
    	return deck.isEmpty();
    }
    public int getSize() {
    	return deck.size();
    }
    @Override
    public String toString() {
        String str = "";
        for (Card card : deck) {
            str += card.toString() + "\n";
        }
        return str;
    }
    
}
