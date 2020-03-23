package com.poky.core;

import com.poky.core.PokerCardProperties.Color;
import com.poky.core.PokerCardProperties.Value;

import java.util.Arrays;

/**
 * The playing deck
 */

public class PokerDeck implements Deck{
    private PokerCard[] cards;
    // how many cards are in deck at a certain moment
    private int cardsInDeck;

    private static final int CARDS_IN_STARTING_DECK = 52;
    private static final int DISTINCT_CARDS_IN_STARTING_DECK = 13;

    public PokerDeck() {
        cards = new PokerCard[CARDS_IN_STARTING_DECK];
        resetDeck();
    }

    public void resetDeck() {
        Color[] colors = Color.values();
        Value[] values = Value.values();
        cardsInDeck = 0;

        for (Color color : colors) {
            for (int j = 0; j < values.length; j++) {
                cards[cardsInDeck++] = new PokerCard(color, Value.getValue(j));
            }
        }
    }

    @Override
    public String toString() {
        return "PokerDeck {" + System.lineSeparator() +
                "\tcardsInDeck = " + cardsInDeck + System.lineSeparator() +
                "\tcards = " + getPrettyCards(cards) + System.lineSeparator() +
                '}';
    }

    public String getPrettyCards(Card[] cards) {
        StringBuilder builder = new StringBuilder();
        builder.append(Arrays.toString(cards));
//        for (Card card : cards) {
//            builder.append(card.toString());
//        }
        return builder.toString();
    }
}
