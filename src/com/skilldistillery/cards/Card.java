package com.skilldistillery.cards;

public class Card {
	//F I E L D S
	private Rank rank;
	private Suit suit;
	
	//C O N S T R U C T O R S
	public Card() {}
	
	public Card(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
	}
	
	//M E T H O D S
	public String toString() {
		return "|" + rank.name() + " " + suit + "|";
	}
	
	public Rank getRank() {
		return rank;
	}
	
	public int getValue() {
		return rank.getValue();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rank == null) ? 0 : rank.hashCode());
		result = prime * result + ((suit == null) ? 0 : suit.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (rank != other.rank)
			return false;
		if (suit != other.suit)
			return false;
		return true;
	}
}
