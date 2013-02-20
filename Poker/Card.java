public class Card implements Comparable<Card> {
	private int val;
	private String suit;
	
	public Card(int v, String s) {
		val = v;
		suit = s;
	}
	
	public int getVal() {
		return val;
	}
	
	public String getSuit() {
		return suit;
	}
	
	public boolean compareSuit(Card c) {
		return (getSuit().equals(c.getSuit()));
	}
	
	public int compareTo(Card other) {
		return getVal() - other.getVal();
	}
	
	public String toString() {
		return getVal()+" of "+getSuit();
	}
}