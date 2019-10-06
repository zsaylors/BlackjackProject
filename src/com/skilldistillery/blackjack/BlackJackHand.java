package com.skilldistillery.blackjack;

import com.skilldistillery.cards.Hand;

public class BlackJackHand extends Hand {
	
	public BlackJackHand() {}

	// Checks if the player or dealer have blackjack
	public boolean isBlackJack() {
		boolean winner = false;
		if (getHandValue() == 21) {
			winner = true;
		}
		return winner;
	}
	
	//Check if the player or dealer have a bust
	public boolean isBust() {
		boolean bust = false;
		if (getHandValue() > 21) {
			bust = true;
		}
		return bust;
	}
}
