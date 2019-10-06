package com.skilldistillery.cards;

import java.util.Scanner;

import com.skilldistillery.blackjack.BlackJackTable;
import com.skilldistillery.data.BlackJackHighScores;

public class CardGamesApp {
	// F I E L D S
	private Scanner kb = new Scanner(System.in);

	// Instantiate games
	BlackJackTable t = new BlackJackTable();

	// Instantiate high scores
	BlackJackHighScores bjhs = new BlackJackHighScores();

	// M E T H O D S
	public static void main(String[] args) {
		CardGamesApp cga = new CardGamesApp();
		cga.run();
	}

	// menu allows user to pick either games or high scores.
	private void run() {
		menu();
		kb.close();
	}

	// Menu design
	private void menuPrintOut() {
		System.out.println("--------------------");
		System.out.println("| \uD83C\uDCA1  CARD GAMES  \uD83C\uDCBE |");
		System.out.println("--------------------");
		System.out.println("Pick a game:\n" 
				+ "1.  Blackjack\n"  
				+ "\n"			
				+ "View High Scores:\n" 
				+ "2. Blackjack");
	}

	// Menu switch statement. Currently only has blackjack game and highscores.
	private void menu() {
		boolean runMenu = true;
		while (runMenu) {
			menuPrintOut();
			String playerDecision = kb.nextLine();
			switch (playerDecision) {
			case "1":
				runMenu = false;
				t.startGame();
				break;
			case "2":
				bjhs.highScoresByHS();
				kb.nextLine();
				break;
			default:
				System.out.println("Invalid choice.  Try again.");
				break;
			}
		}
	}

}
