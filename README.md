# Blackjack Project

![alt text](https://i.ibb.co/3fMVmQm/Screen-Shot-2019-10-06-at-5-08-27-PM.png "Start menu")

### Skill Distillery Week Four Homework

### Overview
The Blackjack game is run through a Card Games class.  While blackjack is the only game listed, the program allows for expansion to multiple card games.  

#### Entry Menu
When entering a user will be presented with a game option or a highscores option.  Picking 1. Blackjack will start gameplay.  Picking 2. Highscores will allow the user to see highscores assorted by username, rank, and total gamble winnings.  It is sorted by rank.

#### blackjack

##### Initial Prompts -
First the user is prompted to enter a username.  It must be between 4 - 10 characters.  This was implemented for highscores formatting, and to practice at regex.

The user is them prompted if they would like to gamble or not.  If gambling is selected, the next prompt will ask for a wager.  After each round a prompt will appear allowing the user to change their bet.  Gamble amounts are set between $0 and $10,000.  If gambling is not selected, no prompts will show up during gameplay regarding gambling.  

Next, a prompt will allow a user to pick 1, 2, 6, or 8 decks.  Once selected, the deck size will stay the same until the user quits.  When the deck size is ~20% its original capacity, the deck will be shuffled.

##### Gameplay -

Gameplay begins and the user is shown his or her two cards and one of the dealers cards.  
A menu displays that will allow the user to:
1. **Stay**: - The user does not draw an additional card and the dealer will flip.  If the dealers cards value is less than 17, the dealer will continue to flip until at or above 17.  When the dealer is finished drawing, the program will compare and determine who wins.
2. **Hit**: - If the user opts to hit, a card will be drawn and displayed on screen.  If the value is over 21, the player will loose.  Otherwise, the menu will redisplay allowing the option to stay, hit or, double again.
3. **Double** - The player may double the wager, but will only be allowed to draw once.  After the draw, the dealer will draw and the computer will determine who won.

The game will present who won, and will also present the users cumulative earnings from the game start (which may be negative).

Example of menu and player choice:
![alt text](https://i.ibb.co/848cFQc/Screen-Shot-2019-10-06-at-5-23-38-AM.png "Menu example")

The user will be prompted if they want to continue playing.
1.  **Yes** - The players and dealers cards will be cleared and the program will loop, drawing two new cards from the deck for each.  The user may enter a new bet amount at this time.
2.  **No** - The players high score will be displayed and the program will exit.  The players highscore data will be saved to a txt file, and can be seen in highscores when the game app is reopened.

### How to run
Download the files from the GitHub repository.  

Open the files in an Java Integrated Development Environment, such as Eclipse (free).

The main class is called `CardGamesApp.java` in the package titled `com.skilldistillery.cards`.

From there the program can be run as a Java Program through the IDE's console.

### Technologies/ Topics Applied

- **Object Oriented Programming**:
The biggest topic applied in this project was object oriented programming.  There were many classes or objects needed in order for the game to function.  A UML was imperative to understand the flow of the project.  Without it and proper planning, the project could have easily taken double the time.  The project allowed to expand on getters, setters, interfaces, and abstract classes.

- **UML**:
As previously mentioned, a UML diagram was necessary.  I expanded on the original diagram will a few extra classes, such as adding a dealer and player object.

- **Enumerated Types**:
This was a new topic explained earlier on Friday.  Enumerated types were used to create the card suits and values since they are predefined values that will remain constant.

- **Regex**:
This was not in the scope of the project, but I finally was able to implement regex!  It was practice that was needed.  The user must stick within the confines of 4-10 characters with a few extra exceptions.

- **Exceptions**:
There were a few try catch exceptions used, and I used switch statements when possible so I could use Strings for menus.  This prevents the programming from breaking in most situations if the user enters a mismatched type.

- **ArrayList**:
Array lists were used throughout the project, from the initial deck, to the players' hands and even in the highscores.



### Lessons Learned
The hardest part of the project was getting started.  I thought I had a good UML set up, but it was complicated and I scrapped it.  I went with something very similar to the provided UML from Skill Distillery.  I did add a few classes such as the Dealer, Player, and Table.  This goes to show how important planning is in the programming process.

During the project, I had a take a step back and review getters and setters.  I was trying to instantiate everywhere and it was getting messy and confusing.  After that I started to incorporate getters more to get values of cards in different classes.  The highscores that I added helped understand this better, too.  The UserName class relies on getters and setters for the highscores to work.

Smaller classes helped significantly when working through the logic parts of the project.  When implementing the double method to double a players bet, it only took minutes since I took from the other methods.  But, I also learned I have to be careful when placing methods inside other methods.  At one point, a menu was running multiple times, and when I tried to clear the cards to reset the game, I got an odd (inception?) exception.  That took a huge chunk of time to sift through.
