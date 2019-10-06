package com.skilldistillery.data;

public class UserName {
	// F I E L D S
	private String username;
	private int highScore;
	private int gambleEarnings;

	// C O N S T R U C T O R S
	public UserName() {
	}

	public UserName(String username, int highScore, int gambleEarnings) {
		this.username = username;
		this.highScore = highScore;
		this.gambleEarnings = gambleEarnings;
	}

	// M E T H O D S
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getHighScore() {
		return highScore;
	}

	public int getGambleEarnings() {
		return gambleEarnings;
	}

	public void setGambleEarnings(int gambleEarnings) {
		this.gambleEarnings = gambleEarnings;
	}

	// Prints user name, the high score, and the gambling earnings to high scores
	// list.
	public String toString() {
		return username + "\t\t\t" + highScore + "\t\t\t\uD83D\uDCB2" + gambleEarnings;
	}

	// Sets high score. Formula: +10 if player beats dealer, +20 for blackjack, -5
	// if dealer wins, 0 for busts.
	public void setHighScore(int val) {
		if (val == 1) {
			this.highScore = highScore += 10;
		} else if (val == 2) {
			this.highScore = highScore += 20;
		} else {
			this.highScore = highScore -= 5;
		}
	}
}
