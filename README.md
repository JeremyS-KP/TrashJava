# Trash Card Game

Welcome to Trash! Trash is a playing card game where a standard deck of 52 cards without jokers is used. In this implementation there are 2 players.

## Game Setup

Initially there are 10 cards dealt to each player. The player then places these cards face down in front of them.

## Objective

The objective of the game is to draw cards either from the deck or the discard pile and flip each card A - 10, corresponding to the card drawn. Once you place the card in its corresponding place, you look at the card displaced as a result and see if you have the unflipped card that corresponds to the card flipped. Repeating this process of displacing cards until the card displaced is one you do not need, or you satisfy all of your needs. Your turn ends when you discard a card you do not need.

## Rules

- Jacks are wild cards in this iteration of the game.
- Wild cards cannot be used to advance the objective of the game, being corresponding drawn cards to face-down cards.
- Wild cards provide a distinct advantage as they allow you to see the card displaced by the wild.
- Wild cards can be placed anywhere to correspond with any card, so long as it is not another wild card.

## Progression and Winning

Every time the objective is met by either player (only one player can meet the objective [it's first to meet the objective]), the highest card the player needs decrements by one (e.g., 10 becomes 9, 9 becomes 8). This continues until a player reduces their card needed to just an ace, and they satisfy it. The first player to satisfy this singular need wins the game of Trash.

## Developer Notes

This program operates recursively on this basis: once the objective is met each hand, decrement the number of cards the player that met the objective needs until the base case of cards needed less than one is met.

It is also important to note that in this program for simplicity's sake, wilds are assigned to the lowest needed value, though that is left up to the player in practical circumstances.

The intrigue of this program arose as a result of an observation that I made when playing Trash: the game takes us humans a substantial amount of time to play whilst requiring minimal card-playing strategy.

Some parts of my shuffle method are intentionally left inefficient to show my initial problem-solving approach, rather than just copying the most efficient known algorithms. This keeps the project authentic and entirely my own work.

## My Approach

This project took me roughly three months of sporadic effort to put together entirely, and there were certainly points where I found myself frustrated and confused by errors within the code, oftentimes relating to infinite while loops and recursion, along with some boolean-related logical errors. This lead me to make the outputs far more descriptive, as it prints the state of data structures and logic flow at the beginning and end of each turn, which quickly identified my mistakes. After overcoming the initial errors that I encountered (subsequent to building the structure of my program, such as objects and flow of logic—e.g., recursion/while loops to navigate varying iterations the game may cycle through), I finally completed a Trash algorithm that I was proud to show to my friends, family, and prospective employers.

However, there was one issue: in an effort to make sure I was not biting off more than I could chew, I initially decided to write the program without including wild cards. This worked out for me in the beginning, but as I re-ran my code numerous times, it occured to me that I had stopped just short of my personal expectation of completion. With this challenge staring at me point-blank, I decided to back up my prior working Trash algorithm and put my head down so I could implement wild cards, to hold true to the game of cards that I would play with my friends and family. I approached this issue in the same way I learned to approach the last: be descriptive with each instruction and keep an eye on pesky boolean-related logical errors to avoid the dreaded infinite loops and recursion. At last, I had properly implemented wild cards into my working Trash algorithm and have finally met that sought-after expectation of completion.

## How To Run
1. Make sure you have Java installed  (JDK 17+ ideally)
2. Download TrashImplementation.jar from this repository
3. Open Terminal/command prompt and run by doing the following:
    type cd (the directory which you saved TrashImplementation to)
    then type,    Java -jar TrashImplementation.jar


