package com.skilldistillery.data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class BlackJackHighScores {
	// F I E L D S
	private List<UserName> usernameList = importHighScores();

	// C O N S T R U C T O R S

	// M E T H O D S
	// Imports high scores into an ArrayList using BuffereedReader and FileReader.
	private List<UserName> importHighScores() {
		List<UserName> usernameList = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader("BlackJackHS.txt"));
			String userData;
			String[] user = null;

			while ((userData = br.readLine()) != null) {
				user = userData.split(", ");
				int highscore = Integer.parseInt(user[0]);
				String username = user[1];
				int gambleEarnings = Integer.parseInt(user[2]);
				usernameList.add(new UserName(username, highscore, gambleEarnings));
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sortList(usernameList);
	}

	// The following methods sorts the user name list. I could not get the comparator
	// to work, but based off a stack overflow post, I learned Java 8 allows for the
	// sort method to be used directly.
	public List<UserName> sortList(List<UserName> usernameList) {
		usernameList.sort((UserName u1, UserName u2) -> {
			if (u1.getHighScore() > u2.getHighScore())
				return 1;
			if (u1.getHighScore() < u2.getHighScore())
				return -1;
			return 0;
		});
		return usernameList;
	}

	// Orders HS by the score amount.
	public void highScoresByHS() {
		System.out.println(" ~ HIGH SCORES ~\n");
		System.out.println("Username \t\t\tScore \t\t\tGambling Winnings\n");

		for (int i = (usernameList.size() - 1); i >= 0; i--) {
			System.out.println(-1 * (i - usernameList.size()) + ".  " + usernameList.get(i).toString());
		}

		System.out.println("\nPress [return] for game menu...");
	}

	// The following code first overwrites the existing txt file with the existing
	// list and after the for loop appends the new user data to the list.
	public void writeHighScores(UserName user) {
		try {
			PrintWriter pw = new PrintWriter(new FileWriter("BlackJackHS.txt"));
			for (UserName u : usernameList) {
				StringBuilder sb = new StringBuilder();
				sb.append(u.getHighScore());
				sb.append(", ");
				sb.append(u.getUsername());
				sb.append(", ");
				sb.append(u.getGambleEarnings());
				pw.println(sb);
			}
			StringBuilder sb = new StringBuilder();
			sb.append(user.getHighScore());
			sb.append(", ");
			sb.append(user.getUsername());
			sb.append(", ");
			sb.append(user.getGambleEarnings());
			pw.println(sb);

			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((usernameList == null) ? 0 : usernameList.hashCode());
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
		BlackJackHighScores other = (BlackJackHighScores) obj;
		if (usernameList == null) {
			if (other.usernameList != null)
				return false;
		} else if (!usernameList.equals(other.usernameList))
			return false;
		return true;
	}
}
