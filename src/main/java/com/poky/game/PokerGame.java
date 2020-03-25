package com.poky.game;

import com.poky.core.Card;
import com.poky.core.Deck;
import com.poky.core.PokerDeck;
import com.poky.util.DeckUtils;

public class PokerGame {
    private DeckUtils deckUtils = new DeckUtils();

    public void initGame() {
        Deck<Card> pokerDeck = new PokerDeck();
//        System.out.println(pokerDeck.toString());
//        System.out.println();
        System.out.println("Poker Deck");
        System.out.println(deckUtils.deckToPrettyString(pokerDeck));
        System.out.println("Shuffling the Deck");
        pokerDeck.shuffleDeck();
        System.out.println(deckUtils.deckToPrettyString(pokerDeck));
    }
}
