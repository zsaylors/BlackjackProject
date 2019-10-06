package com.skilldistillery.cards;

import com.skilldistillery.blackjack.BlackJackTable;

public class CardGamesApp {

	public static void main(String[] args) {
		CardGamesApp cga = new CardGamesApp();
		cga.run();

	}

	private void run() {
		BlackJackTable t = new BlackJackTable();
		t.startGame();
		
	}
}
