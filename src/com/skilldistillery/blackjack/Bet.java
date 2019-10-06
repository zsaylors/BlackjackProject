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
	public Bet() {}

	// M E T H O D S
	// Returns if user is gambling or not.
	public boolean isGamble() {
		return gamble;
	}

	// Determines if the user wants to bet. Leaves gamble at true if so.
	public boolean setBet() {
		boolean gamble = true;
		boolean runMenu = true;
		while (runMenu) {
			System.out.println("\nWould you like to gamble?\n" + "1.  Yes\n" + "2.  No");
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
	// Throws exception if a String is passed instead of an integer and reruns the
	// prompt.
	public void setGambleAmount() {
		if (gamble) {
			boolean nonValidAmount = true;
			do {
				try {
					if (gamble) {
						System.out.println("\nHow much would you like to gamble? (0 - 10000)");
						gambleAmount = kb.nextInt();

						if (gambleAmount < 0 || gambleAmount > 10000) {
							System.out.println("Invalid Number.  Please enter an ammount between 1 and 10000.");
						} else {
							System.out.println("\nYou got it!  Betting \uD83D\uDCB2" + gambleAmount + "!");
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

	//If the player opts to double, this doubles the wager.
	public void setGambleAmountDouble() {
		this.gambleAmount = gambleAmount * 2;
	}
	
	// returns the cumulative gambling amount.
	public int getCumulativeGambleAmount() {
		return cumulativeGambleAmount;
	}

	// Sets the cumulative gambling amount.
	public int setCumulativeGambleAmount(int win) {
		if (win == 1) {
			cumulativeGambleAmount += gambleAmount * 2;
		} else if (win == 2) {
			cumulativeGambleAmount += Math.round(gambleAmount * 3 / 2 + gambleAmount - 0.5);
		} else {
			cumulativeGambleAmount -= gambleAmount;
		}
		return cumulativeGambleAmount;
	}

	// Prints cumulative gambling amount. The toString is found in the
	// continuePlaying() method / menu at the bottom of the Table class.
	public String toString() {
		return "\nYour cumulative earnings are: \uD83D\uDCB2" + getCumulativeGambleAmount();
	}

}
