package com.skilldistillery.blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.cards.Card;
import com.skilldistillery.cards.Deck;

public class BlackJackTable {
	// F I E L D S
	private Scanner kb = new Scanner(System.in);
	private Person playerOne = new Person();
	private Dealer dealer = new Dealer();
	private Deck deck;
	private List<Card> gameDeck = new ArrayList<>();
	private Bet bet = new Bet();
	private boolean play = true;

	// C O N S T R U C T O R S

	// M E T H O D S
	// Loops game play until user choose to quit and while deck has not run out of
	// cards.
	public void startGame() {
		bet.setBet();
		while (play) {
			play = false;
			bet.setGambleAmount();
			shuffleDeck();
			dealCards();
			showCards();
			continuePlaying();
		}
		kb.close();
	}

	// At game start or at the end of a round, checks if deck is at ~20% capacity. If so, it creates
	// a new deck and shuffles.
	//
	// Note: There was no standard re-shuffle rule online, so I went with when the
	// deck has ~20% capacity.  This should prevent the game from breaking if a
	// player or dealer continuously draws low numbers with a single 52 card deck.
	public void shuffleDeck() {
		if (gameDeck.size() == 0 || gameDeck.size() < ((int) (deck.getNum() * 0.2 * 52))) {
			deck = new Deck();
			gameDeck = deck.shuffle();
			System.out.println("The deck has been shuffled with " + gameDeck.size() + " cards.");
		}
	}

	// Deals two cards to the playerHand list and two cards to the dealerHand list.
	public void dealCards() {
		playerOne.addCard(deck.dealCard());
		playerOne.addCard(deck.dealCard());
		dealer.addCard(deck.dealCard());
		dealer.addCard(deck.dealCard());
	}

	// Prints to screen players hand and the dealers single card.  didPlayerWin method is closer to the bottom.
	public void showCards() {
		System.out.println("\nYour Hand:\n" + playerOne.toString());
		System.out.println("Dealers Hand:\n" + dealer.toString() + " | � |");
		didPlayerWin();
	}

	// User inputs a number 1 to stay or 2 to hit. Calls the hit() method and the
	// stay method();
	public void makeDecision() {
		play = false;
		boolean runMenu = true;
		while (runMenu) {
			System.out.println("\nWould you like to\n" + "1.  Stay\n" + "2.  Hit");
			String playerDecision = kb.nextLine();
			switch (playerDecision) {
			case "1":
				runMenu = false;
				stay();
				break;
			case "2":
				runMenu = false;
				hit();
				break;
			default:
				System.out.println("Invalid choice.  Try again.");
				break;
			}
		}
	}

	// If the user chooses to hit it will add a card from the deck. Then it will
	// show cards and check if the player won.
	public void hit() {
		playerOne.addCard(deck.dealCard());
		showCards();
	}

	// If user chooses to stay the dealer will continue to flip until their card
	// value is >= 17.
	public void stay() {
		System.out.println("Dealer flips and shows...\n" + dealer.showHand());

		while (dealer.getHandValue() < 17) {
			dealer.addCard(deck.dealCard());
			System.out.println("Dealer flips a new card and shows...\n" + dealer.showHand());
		}
		
		didDealerWin();
	}

	// Checks if player gets blackjack or bust whenever show cards method is run.  
	// Else it goes to the makeDecision method / menu.
	// Show dealers hand to ensure dealer did not also have a blackjack.
	public void didPlayerWin() {
		if (playerOne.isBlackJack() && ! dealer.isBlackJack()) {
			System.out.println("\nBlackJack!");
			System.out.println("Dealers hand was:\n" + dealer.showHand());
			bet.setCumulativeGambleAmount(true);
		} else if (playerOne.isBust()) {
			System.out.println("\nYour value is over 21!  Dealer wins.");
			System.out.println("Dealers hand was:\n" + dealer.showHand());
			bet.setCumulativeGambleAmount(false);
		} else {
			makeDecision();
		}
	}

	// Checks who won when a player stays.
	public void didDealerWin() {
		// Shows hand values so player can double check their numbers match, and to add less confusion.
		System.out.println("Player hand value: " + playerOne.getHandValue() + "\tDealers hand value: " + dealer.getHandValue());
		
		if (dealer.isBlackJack() && !playerOne.isBlackJack()) {
			System.out.println("Dealer gets BlackJack!");
			bet.setCumulativeGambleAmount(false);
		}
		else if (dealer.isBust() && playerOne.isBust()) {
			System.out.println("Dealer and player bost bust.  Dealer wins.");
			bet.setCumulativeGambleAmount(false);
		}
		else if (dealer.isBust()) {
			System.out.println("Dealer busts.  Player wins!");
			bet.setCumulativeGambleAmount(true);
		} 
		else if (dealer.getHandValue() < playerOne.getHandValue()) {
			System.out.println("Player wins!");
			bet.setCumulativeGambleAmount(true);
		}
		else if (dealer.getHandValue() == playerOne.getHandValue()) {
			System.out.println("Push.");
		}
		else {
			System.out.println("Dealer wins.");
			bet.setCumulativeGambleAmount(false);
		}
	}

	// Pops up after every round to make sure user wants to continue. It is run in
	// the startGame() method.
	// Clears the dealer and players cards in case 1.
	// Prints the earnings (or lack thereof) if gambling.
	public boolean continuePlaying() {
		// Prints out winnings.
		if (bet.isGamble()) {
			System.out.println(bet.toString());
		}
		
		boolean runMenu = true;
		while (runMenu) {
			System.out.println("\nWould you like to continue playing?\n1. Yes\n2. No");
			String playerDecision = kb.nextLine();
			switch (playerDecision) {
			case "1":
				play = true;
				runMenu = false;
				playerOne.clear();
				dealer.clear();
				break;
			case "2":
				System.out.println("Goodbye!");
				play = false;
				runMenu = false;
				break;
			default:
				System.out.println("Invalid choice.  Try again");
				break;
			}
		}
		return play;
	}

}