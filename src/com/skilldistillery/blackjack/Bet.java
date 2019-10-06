package com.skilldistillery.blackjack;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Bet {
	// F I E L D S
	private Scanner kb = new Scanner(System.in);
	private boolean gamble;
	private int gambleAmount;
	private int cumulativeGambleAmount;

	// C O N S T R U C T O R S
	public Bet() {
	}

	// M E T H O D S
	// Returns if user is gambling or not.
	public boolean isGamble() {
		return gamble;
	}

	// Determines if the user wants to bet. Sets gamble to true if so.
	public boolean setBet() {
		boolean gamble = true;
		boolean runMenu = true;
		while (runMenu) {
			System.out.println("Would you like to gamble?\n" + "1.  Yes\n" + "2.  No");
			String playerDecision = kb.nextLine();
			switch (playerDecision) {
			case "1":
				runMenu = false;
				break;
			case "2":
				runMenu = false;
				gamble = false;
				break;
			default:
				System.out.println("Invalid choice.  Try again.");
				break;
			}
		}
		return this.gamble = gamble;
	}

	// Returns the amount that is gambled.
	public int getGambleAmount() {
		return gambleAmount;
	}

	// The users gamble amount. Must be between 1 and 10,000.
	// Throws exception if a String is passed instead of an int and reruns the
	// prompt.
	public void setGambleAmount() {
		if (gamble) {
			boolean nonValidAmount = true;
			do {
				try {
					if (gamble) {
						System.out.println("How much would you like to gamble? (0 - 10000)");
						gambleAmount = kb.nextInt();

						if (gambleAmount < 0 || gambleAmount > 10000) {
							System.out.println("Invalid Number.  Please enter an ammount between 1 and 10000.");
						} else {
							System.out.println("You got it!  Betting " + gambleAmount + "!");
							nonValidAmount = false;
						}
					}
				} catch (InputMismatchException e) {
					System.out.println("Invalid entry.  Try agian.");
					kb.nextLine();
				}
			} while (nonValidAmount);
		}
	}

	// returns the cumulative gambling amount.
	public int getCumulativeGambleAmount() {
		return cumulativeGambleAmount;
	}

	// Sets the cumulative gambling amount.
	public int setCumulativeGambleAmount(boolean win) {
		if (win) {
			return cumulativeGambleAmount += gambleAmount * 2;
		} else {
			return cumulativeGambleAmount -= gambleAmount;
		}
	}

	// Prints cumulative gambling amount. The toString is found in the
	// continuePlaying() method / menu at the bottom of the Table class.
	public String toString() {
		return "\nYour cumulative earnings are: " + getCumulativeGambleAmount();
	}

}
