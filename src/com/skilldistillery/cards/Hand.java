package com.skilldistillery.cards;

import java.util.ArrayList;
import java.util.List;

abstract public class Hand {
	// F I E L D S
	protected List<Card> cards = new ArrayList<>();

	// C O N S T R U C T O R S
	public Hand() {
	}

	// M E T H O D S
	// Adds cards to the hand.
	public void addCard(Card card) {
		cards.add(card);
	}

	// Resets the hand to having no cards. There is a bug where the cards.size() is
	// equal to THREE in the for loop, while out of the loop running a sysout on
	// cards.size() will print the actual size.
	//
	// Anything greater than index 2 will not be removed from the loops. Therefore,
	// additional (redundant) if statements have been added until the issue is
	// figured out.
	
	//Attempted with for each loop and bug persisted.
	public void clear() {
		for (int i = 0; i <= cards.size(); i++) {
			cards.remove(0);
		}
		
		// Redundant if statements to be removed when cards.size() bug is figured out.
		// The amount of if statements should ensure that the program does not break.
		if (cards.size() >= 1) {
			cards.remove(0);
		}

		if (cards.size() >= 1) {
			cards.remove(0);
		}

		if (cards.size() >= 1) {
			cards.remove(0);
		}
		if (cards.size() >= 1) {
			cards.remove(0);
		}
	}

	// returns the current hand value.
	public int getHandValue() {
		int handValue = 0;
		for (Card card : cards) {
			handValue += card.getValue();
		}
		return handValue;
	}

	// Prints the cards to the screen. If/else statement added for spacing.
	// For loop used in case a different game is added vice just printing two cards.
	public String toString() {
		String showCards = "";
		for (int i = 0; i < cards.size(); i++) {
			if (i < cards.size() - 1) {
				showCards += cards.get(i) + " ";
			} else {
				showCards += cards.get(i);
			}
		}
		return showCards;
	}

}
