package com.skilldistillery.blackjack;

public enum Suit {
	HEARTS("♡"), SPADES("♠"), CLUBS("♣"), DIAMONDS("♢");

	private String name;

	Suit(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
