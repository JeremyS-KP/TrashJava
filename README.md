# Trash Card Game

Welcome to Trash! Trash is a playing card game where a standard deck of 52 cards without jokers is used. Up to 2 players can play.

## Game Setup

Initially there are 10 cards dealt to each player. The player then places these cards face down in front of them.

## Objective

The objective of the game is to draw cards either from the deck or the discard pile and flip each card A - 10, corresponding to the card drawn. Once you place the card in its corresponding place, you look at the card displaced as a result and see if you have the unflipped card that corresponds to the card flipped. Repeating this process of displacing cards until the card displaced is one you do not need, or you satisfy all of your needs. Your turn ends when you discard a card you do not need.

## Special Rules

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

## How To Run
1. Make sure you have Java installed  (JDK 17+ ideally)
2. Download TrashImplementation.jar from this repository
3. Open Terminal/command prompt and run by doing the following:
    type cd (the directory which you saved TrashImplementation to)
    then type,    Java -jar TrashImplementation.jar
