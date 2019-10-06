package com.skilldistillery.cards;

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
