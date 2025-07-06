package pkg1;

import java.util.ArrayList;
import java.util.Random;

public class Hand {
	private ArrayList<Card> cardsInHand;
	private Random rand;
	
	public Hand() {
		cardsInHand = new ArrayList<>();
		rand = new Random();
		
	}
	public Hand( ArrayList<Card> cardsInHand) {
		this.cardsInHand = cardsInHand;
		rand = new Random();
	}
	public Hand copyHand() {
		return new Hand(getAllCards());
	}
	public int getHighCard() {
		int max = 0;
	    for (Card card : cardsInHand) {
	        int value = card.getCardValue();
	        if (value == 1) {
	        	value = 14;  // Treat Ace as high without modifying the card
	    	}
	        if (value > max) {
	            max = value;
	        }
	    }
	    return max;
	}
	public int numCardsInHand() {
		return cardsInHand.size();
	}
	public void giveHandCard(Card card) {
		cardsInHand.add(card);
	}
	public void giveHandCardAtIndex(int index, Card card) {
		cardsInHand.add(index, card);
	}
	public Card removeCardAtIndex(int index) {
		return cardsInHand.remove(index);
	}
	public Card getRandomCard() {
		int randNum = rand.nextInt(cardsInHand.size()); // 0 (inclusive) to size (exclusive), no need for out of bounds concern then
		return cardsInHand.get(randNum);
	}
	public Card getCard(int index) {
		return cardsInHand.get(index);
	}
	public ArrayList<Card> getAllCards() {
		return cardsInHand;
	}
	public Card removeRandomCard() {
		int randNum = rand.nextInt(cardsInHand.size()); // 0 (inclusive) to size (exclusive), no need for out of bounds concern then
		Card removedCard = cardsInHand.get(randNum);
		cardsInHand.remove(randNum);
		return removedCard;
	}
	public void sortHandByValue() {
		quickSort(cardsInHand , 0, cardsInHand.size() - 1);
	}
	public void sortHandBySuit() {
		// initialize an ArrayList for each suit
		ArrayList<Card> spades = new ArrayList<>(); 
		ArrayList<Card> diamonds = new ArrayList<>();
		ArrayList<Card> clubs = new ArrayList<>();
		ArrayList<Card> hearts = new ArrayList<>();
		// initialize a helper arrayList of each suit ArrayList
		ArrayList<ArrayList<Card>> allCardList = new ArrayList<>(); 
		allCardList.add(spades);
		allCardList.add(diamonds);
		allCardList.add(clubs);
		allCardList.add(hearts);
		if(!cardsInHand.isEmpty()) {
			for(Card card : cardsInHand) { // first designate cards by suit into their appropriate ArrayList
				if (card.getSuit().equals("spades")) {
					spades.add(card);
				}
				else if (card.getSuit().equals("diamonds")) {
					diamonds.add(card);
				}
				else if (card.getSuit().equals("clubs")) {
					clubs.add(card);
				}
				else if (card.getSuit().equals("hearts")) {
					hearts.add(card);
				}
			}
			if(!spades.isEmpty()) { // then sort cards within suit by value
				quickSort(spades, 0, spades.size() - 1);
			}
			if(!diamonds.isEmpty()) { // then sort cards within suit by value
				quickSort(diamonds, 0, diamonds.size() - 1);
			}
			if(!clubs.isEmpty()) { // then sort cards within suit by value
				quickSort(clubs, 0, clubs.size() -1 );
			}
			if(!hearts.isEmpty()) { // then sort cards within suit by value
				quickSort(hearts, 0, hearts.size() - 1);
			}
			cardsInHand.clear(); // clear original cards now that you have 4 sub-ArrayLists
			for(ArrayList<Card> list : allCardList) { // for every suit list
				for( Card card : list) { // add the sorted values back to the cardsInHand arrayList
					cardsInHand.add(card);
				}
			}
		}
	}
	public void quickSort(ArrayList<Card> list, int start, int end) { // quick sort method
        if (start < end) {
            int pivotIndex = partition(list, start, end);
            quickSort(list, start, pivotIndex - 1);
            quickSort(list, pivotIndex + 1, end);
        }
    }
    private int partition(ArrayList<Card> list, int start, int end) { // partition method used in quick sort method
        Card pivot = list.get(end); 
        int i = start - 1;
        for (int j = start; j < end; j++) {
            if (list.get(j).getCardValue() <= pivot.getCardValue()) {
                i++;
                Card temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
            }
        }
        Card temp = list.get(i + 1);
        list.set(i + 1, list.get(end));
        list.set(end, temp);
        return i + 1;
    }
    
    
    
	@Override
	public String toString() {
		String str = "cards in this hand: ";
		for ( int i = 0; i < cardsInHand.size(); i++) {
			str += cardsInHand.get(i).toString();
			if ( i < cardsInHand.size() - 1) {
				str += ", ";
			}
		}
		return str;
	}
}
