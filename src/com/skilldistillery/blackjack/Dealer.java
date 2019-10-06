package com.skilldistillery.blackjack;

public class Dealer extends BlackJackHand implements ShowHand {

	public Dealer() {}

	public String toString() {
		String showCards = "" + cards.get(0);
		return showCards;
	}

	@Override
	public String showHand() {
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