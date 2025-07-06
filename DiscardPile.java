package pkg1;

import java.util.Stack;

public class DiscardPile {
	private Stack<Card> pile;
	public DiscardPile() {
		pile = new Stack<>();
	}
	public void discard(Card unusableCard) {
		pile.push(unusableCard);
	}
	public Card getTopCard() {
		return pile.peek();
	}
	public Card drawCard() {
		return pile.pop();
	}
	public boolean isEmpty() {
		return pile.isEmpty();
	}
	public int getSize() {
		return pile.size();
	}
}
