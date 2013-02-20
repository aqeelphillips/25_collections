import java.util.ArrayList;
import java.util.Collections;
import java.io.*;

public class Poker {
	private ArrayList<Card> Deck;
	private ArrayList<Card> userHand;
	
	public Poker() {
		Deck = new ArrayList<Card>(52);
		initDeck();
		userHand = new ArrayList<Card>(5);
		dealHand(5);
		prettyPrintHand();
		promptUser();
		scoreHand();
	}
	
	public void redeal(ArrayList<Integer> indices) {
		for (int i = 0; i < indices.size(); i++) {
			getDeck().add(getUserHand().get(indices.get(i)));
			getUserHand().set(indices.get(i), null);
		}
		for (int i = 0; i < 5; i++) {
			getUserHand().remove(null);
		}
		int neededCards = 5 - getUserHand().size();
		dealHand(neededCards);
	}
	
	public void dealHand(int n) {
		for (int i = 0; i < n; i++) {
			getUserHand().add(getDeck().get(0));
			getDeck().remove(0);
		}
		Collections.sort(getUserHand());
	}
	
	public void initDeck() {
		Card c;
		for (int i = 1; i < 14; i++) {
			Deck.add(new Card(i, "Spades"));
			Deck.add(new Card(i, "Clubs"));
			Deck.add(new Card(i, "Hearts"));
			Deck.add(new Card(i, "Diamonds"));
		}
		Collections.shuffle(Deck);
	}
	
	public String getUserInp(String prompt) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			print(prompt);
			String cmd = br.readLine();
			return cmd;
		} catch (IOException e) {
			System.out.println("Oops! There was an error reading your command.");
			return "";
		}
	}
	
	private void promptUser() {
		print("");
		String cmd = getUserInp("Return none, some, or all cards?").toLowerCase();
		String[] commands = cmd.split(" ");
		ArrayList<Integer> indices = new ArrayList<Integer>();
		if (commands[0].equals("some")) { 
			String[] indexArray = getUserInp("Which cards (indices separated by commas ', ')?").split(", ");
			for (String e : indexArray) {	
				int ind = Integer.parseInt(e) - 1;
				getDeck().add(getUserHand().get(ind));
				getUserHand().set(ind, null);
			}
			for (int i = 0; i < getUserHand().size(); i++) {
				getUserHand().remove(null);
			}
			dealHand(indexArray.length);
		} else if (commands[0].equals("none")) {
			//Do nothing. Yay!
		} else if (commands[0].equals("all")) {
			for (int i = 0; i < getUserHand().size(); i++) {
				getDeck().add(getUserHand().get(i));
			}
			getUserHand().clear();
			dealHand(5);
		} else {
			print("Command not recognized. Please try again.");
			promptUser();
		}
		print("");
		print("Your new hand:");
		prettyPrintHand();
	}
	
	public void scoreHand() {
		//check flush
		if (checkFlush()) {
			System.out.println("Flush!");
			return;
		}
		//check straight
		if (checkStraight()) {
			System.out.println("Straight!");
			return;
		}
		//check three of a kind
		if (checkThreeOfAKind()) {
			System.out.println("Three of a kind!");
			return;
		}
		//get number of two of a kinds
		int pairs = getPairs();
		if (pairs == 2) {
			System.out.println("Two pairs!");
			return;
		} else if (pairs == 1) {
			System.out.println("One pair!");
			return;
		}
		System.out.println("No score...");
	}
	
	public boolean checkFlush() {
		ArrayList<Card> h = getUserHand();
		for (int i = 1; i < h.size(); i++) {
			if (!h.get(0).compareSuit(h.get(i))) {
				return false;
			}
		}
		return true;
	}
	
	public boolean checkStraight() {
		ArrayList<Card> h = getUserHand();
		for (int i = 1; i < h.size(); i++) {
			if (h.get(i-1).getVal() != h.get(i).getVal() - 1) {
				return false;
			}
		}
		return true;
	}
	
	public boolean checkThreeOfAKind() {
		ArrayList<Card> h = getUserHand();
		if (h.get(0).getVal() == h.get(1).getVal() && h.get(1).getVal() == h.get(2).getVal()) { return true; } 
		if (h.get(1).getVal() == h.get(2).getVal() && h.get(2).getVal() == h.get(3).getVal()) { return true; } 
		if (h.get(2).getVal() == h.get(3).getVal() && h.get(3).getVal() == h.get(4).getVal()) { return true; } 
		return false;
	}
		
	public int getPairs() {
		ArrayList<Card> h = getUserHand();
		int tally = 0;
		for (int i = 1; i < h.size(); i++) {
			if (h.get(i-1).getVal() == h.get(i).getVal()) {
				tally++;
			}
		}
		return tally;
	}
	
	public ArrayList<Card> getDeck() {
		return Deck;
	}
	
	public ArrayList<Card> getUserHand() {
		return userHand;
	}
	
	public void prettyPrintHand() {
		ArrayList<Card> hand = getUserHand();
		for (int i = 1; i < 6; i++) {
			print("Card "+i+": "+hand.get(i-1).toString());
		}
	}
	
	public void print(String p) {
		System.out.println(p);
	}
	
	public static void main(String[] args) {
		Poker p = new Poker();
	}
}