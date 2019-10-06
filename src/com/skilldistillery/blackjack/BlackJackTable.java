package com.skilldistillery.blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.skilldistillery.cards.Card;
import com.skilldistillery.cards.Deck;
import com.skilldistillery.data.BlackJackHighScores;
import com.skilldistillery.data.UserName;

public class BlackJackTable {
	// F I E L D S
	private Scanner kb = new Scanner(System.in);
	private Person playerOne = new Person();
	private Dealer dealer = new Dealer();
	private Deck deck;
	private List<Card> gameDeck = new ArrayList<>();
	private Bet bet = new Bet();
	private boolean play = true;
	private UserName user;

	// C O N S T R U C T O R S

	// M E T H O D S
	// Loops game play until user choose to quit and while deck has not run out of
	// cards.
	public void startGame() {
		newUserName();
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

	// At game start or at the end of a round, checks if deck is at ~20% capacity.
	// If so, it creates
	// a new deck and shuffles.
	//
	// Note: There was no standard re-shuffle rule online, so I went with when the
	// deck has ~20% capacity. This should prevent the game from breaking if a
	// player or dealer continuously draws low numbers with a single 52 card deck.
	private void shuffleDeck() {
		if (gameDeck.size() == 0 || gameDeck.size() < ((int) (deck.getNum() * 0.2 * 52))) {
			deck = new Deck();
			gameDeck = deck.shuffle();
			System.out.println("The deck has been shuffled with " + gameDeck.size() + " cards.");
		}
	}

	// Deals two cards to the playerHand list and two cards to the dealerHand list.
	private void dealCards() {
		playerOne.addCard(deck.dealCard());
		playerOne.addCard(deck.dealCard());
		dealer.addCard(deck.dealCard());
		dealer.addCard(deck.dealCard());
	}

	// Prints to screen players hand and the dealers single card. didPlayerWin
	// method is closer to the bottom.
	private void showCards() {
		System.out.println("\nYour Hand:\n" + playerOne.toString());
		System.out.println("Dealers Hand:\n" + dealer.toString() + " | � |");
		didPlayerWin();
	}

	// User inputs a number 1 to stay or 2 to hit. Calls the hit() method and the
	// stay method();
	private void makeDecision() {
		play = false;
		boolean runMenu = true;
		while (runMenu) {
			System.out.println("\nWould you like to\n" + "1.  Stay\n" + "2.  Hit\n" + "3.  Double");
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
			case "3":
				runMenu = false;
				doubleBet();
				break;
			default:
				System.out.println("Invalid choice.  Try again.");
				break;
			}
		}
	}

	// If the user chooses to hit it will add a card from the deck. Then it will
	// show cards and check if the player won.
	private void hit() {
		playerOne.addCard(deck.dealCard());
		showCards();
	}

	// If user chooses to stay the dealer will continue to flip until their card
	// value is >= 17.
	private void stay() {
		System.out.println("Dealer flips and shows...\n" + dealer.showHand());

		while (dealer.getHandValue() < 17) {
			dealer.addCard(deck.dealCard());
			System.out.println("Dealer flips a new card and shows...\n" + dealer.showHand());
		}

		didDealerWin();
	}
	
	// Doubles the players wager but player only gets one card to draw.
	private void doubleBet() {
		playerOne.addCard(deck.dealCard());
		System.out.println("Your Hand:\n" + playerOne.toString() + "\n");
		bet.setGambleAmountDouble();
		stay();
	}
	
	// Checks if player gets blackjack or bust whenever show cards method is run.
	// Else it goes to the makeDecision method / menu.
	// Show dealers hand to ensure dealer did not also have a blackjack.
	private void didPlayerWin() {
		if (playerOne.isBlackJack() && !dealer.isBlackJack()) {
			System.out.println("\nBlackJack!");
			System.out.println("Dealers hand was:\n" + dealer.showHand());
			bet.setCumulativeGambleAmount(2);
			user.setHighScore(2);
		} else if (playerOne.isBust()) {
			System.out.println("\nYour value is over 21!  Dealer wins.");
			System.out.println("Dealers hand was:\n" + dealer.showHand());
			bet.setCumulativeGambleAmount(0);
			user.setHighScore(0);
		} else {
			makeDecision();
		}
	}

	// Checks who won when a player stays.
	private void didDealerWin() {
		// Shows hand values so player can double check their numbers match, and to add
		// less confusion.
		System.out.println(
				"Player hand value: " + playerOne.getHandValue() + "\tDealers hand value: " + dealer.getHandValue());

		if (dealer.isBlackJack() && !playerOne.isBlackJack()) {
			System.out.println("Dealer gets BlackJack!");
			bet.setCumulativeGambleAmount(0);
			user.setHighScore(0);
		} else if (dealer.isBust() && playerOne.isBust()) {
			System.out.println("Dealer and player bost bust.  Dealer wins.");
			bet.setCumulativeGambleAmount(0);
			user.setHighScore(0);
		} else if (dealer.isBust()) {
			System.out.println("Dealer busts.  Player wins!");
			bet.setCumulativeGambleAmount(1);
			user.setHighScore(1);
		} else if (dealer.getHandValue() < playerOne.getHandValue()) {
			System.out.println("Player wins!");
			bet.setCumulativeGambleAmount(1);
			user.setHighScore(1);
		} else if (dealer.getHandValue() == playerOne.getHandValue()) {
			System.out.println("Push.");
		} else {
			System.out.println("Dealer wins.");
			bet.setCumulativeGambleAmount(0);
			user.setHighScore(0);
		}
	}

	// Pops up after every round to make sure user wants to continue. It is run in
	// the startGame() method.
	// Clears the dealer and players cards in case 1.
	// Saves sets user earnings for highscores and saves user data to .txt file.
	// Prints the earnings (or lack thereof) if gambling.
	private boolean continuePlaying() {
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
				user.setGambleEarnings(bet.getCumulativeGambleAmount());
				BlackJackHighScores hs = new BlackJackHighScores();
				hs.writeHighScores(user);
				System.out.println("\nYour highscores are:");
				System.out.println("Username \t\tScore \t\tGambling Winnings\n" + user.toString());
				System.out.println("\nGoodbye!");
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

	// Sets players user name
	// boolean and pattern ensures user name is between 4-10 chars. This is for high
	// score formatting.
	public void newUserName() {
		String username;
		boolean b = false;

		do {
			user = new UserName();
			System.out.println("\nPlease enter a username: (4-10 characters)");
			System.out.print(">> ");
			username = kb.nextLine();

			b = Pattern.matches("[a-zA-Z0-9_-]{4,10}$", username);
			System.out.println(b);

		} while (!b);
		user.setUsername(username);
	}

}