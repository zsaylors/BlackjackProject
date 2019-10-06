package com.skilldistillery.cards;

public enum Suit {
	HEARTS("♡"), SPADES("♠"), CLUBS("♣"), DIAMONDS("♢");

	//F I E L D S
	private String name;

	//C O N S T R U C T O R S
	Suit(String name) {
		this.name = name;
	}

	//M E T H O D S
	@Override
	public String toString() {
		return name;
	}
}
