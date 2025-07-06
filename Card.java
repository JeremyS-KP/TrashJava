package pkg1;

public class Card {
	private int cardValue;
	
	private String suit;
	public Card(int cardValue, String suit) {
		this.cardValue = cardValue;
		this.suit = suit;
	}
	public int getCardValue() {
		return cardValue;
	}
	public void setCardValue(int cardValue) {
		this.cardValue = cardValue;
	}
	public String getSuit() {
		return suit;
	}
	public void setSuit(String suit) {
		this.suit = suit;
	}
	
	@Override
	public String toString() {
		String rank = "";
		if ( cardValue == 1 || cardValue > 10 ) {
			if ( cardValue == 1 || cardValue == 14) {
				rank+= "ace";
			}
			else if ( cardValue == 11) {
				rank+= "jack";
			}
			else if ( cardValue == 12) {
				rank+= "queen";
			}
			else if ( cardValue == 13) {
				rank+= "king";
			}
		}
		else {
			rank+= getCardValue();
		}
		return rank + " of " + getSuit();
	}
	
}
