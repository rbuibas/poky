package com.poky.game;

import com.poky.core.PokerDeck;

public class PokerGame {
    public static void main(String[] args) {
        PokerDeck pokerDeck = new PokerDeck();
//        System.out.println(pokerDeck.toString());
//        System.out.println();
        System.out.println("Poker Deck");
        System.out.println(pokerDeck.toPrettyString());
        System.out.println("Shuffling the Deck");
        pokerDeck.shuffleDeck();
        System.out.println(pokerDeck.toPrettyString());
    }
}
