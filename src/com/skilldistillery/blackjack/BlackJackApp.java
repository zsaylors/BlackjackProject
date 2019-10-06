package com.skilldistillery.blackjack;

public class BlackJackApp {

	public static void main(String[] args) {
		BlackJackApp b = new BlackJackApp();
		b.run();
	}
	
	private void run() {
		Table t = new Table();
		t.startGame();
	}
}
