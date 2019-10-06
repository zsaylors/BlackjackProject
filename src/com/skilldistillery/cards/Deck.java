package com.skilldistillery.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Deck {
	// F I E L D S
	private List<Card> cards;

	// Scanner and int num are static. Once game has started numOfDecks should not
	// be changed on shuffle. This prevents that. Also, it allows for deck shuffle on a
	// percentage of deck size rather than a set amount of cards.
	private static Scanner kb = new Scanner(System.in);
	static int num = setNumberOfDecks();

	// C O N S T R U C T O R S
	public Deck() {
		cards = createDeck();
	}

	// M E T H O D S

	// Creates the game deck. The nested for loop first determines what the size
	// the deck will be using the setNumberOfDecks() method (at bottom of class).
	// Then, it fills the list that many times.
	public List<Card> createDeck() {
		List<Card> deck = new ArrayList<>();

		// int num = setNumberOfDecks();
		for (int i = 0; i < num; i++) {
			for (Suit s : Suit.values()) {
				for (Rank r : Rank.values()) {
					deck.add(new Card(r, s));
				}
			}
		}
		return deck;
	}

	// Check the deck size. Mostly to determine if it needs to be shuffled.
	public int checkDeckSize() {
		return cards.size();
	}

	// Shuffles deck.
	public List<Card> shuffle() {
		Collections.shuffle(cards);
		return cards;
	}

	// Removes one card from the deck.
	public Card dealCard() {
		return cards.remove(0);
	}

	// Menu and switch statement allowing decks of size 1, 2, 6, and 8. Returns
	// number for createDeck method.
	public static int setNumberOfDecks() {
		String userChoice = "";
		int numberOfDecks = 1;
		boolean validNumber = true;
		while (validNumber) {
			System.out.println("How many decks you would you like to use?  (1, 2, 6, or 8)");
			userChoice = kb.nextLine();
			switch (userChoice) {
			case "1":
			case "2":
			case "6":
			case "8":
				numberOfDecks = Integer.parseInt(userChoice);
				validNumber = false;
				break;
			default:
				System.out.println("Invalid number.  Try again.");
				break;
			}
		}
		return numberOfDecks;
	}
	
	public int getNum() {
		return num;
	}

}
