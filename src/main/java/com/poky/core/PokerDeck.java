package com.poky.core;

import com.poky.core.PokerCardProperties.Color;
import com.poky.core.PokerCardProperties.Value;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * The playing deck
 */

public class PokerDeck implements Deck{
    private List<PokerCard> cards;
    // how many cards are in deck at a certain moment
    private int cardsInDeck;

    private static final int CARDS_IN_STARTING_DECK = 52;

    public PokerDeck() {
        cards = new ArrayList<>(CARDS_IN_STARTING_DECK);
        resetDeck();
    }

    @Override
    public void resetDeck() {
        Color[] colors = Color.values();
        Value[] values = Value.values();
        cardsInDeck = 0;

        for (Color color : colors) {
            for (int j = 0; j < values.length; j++) {
                cards.add(new PokerCard(color, Value.getValue(j)));
                cardsInDeck++;
            }
        }
    }

    @Override
    public void shuffleDeck() {
        if (cardsInDeck < 52) {
            // TODO: move this to logging
            System.out.println("LOG: Shuffling incomplete deck. This is ok in some cases of misdeal. Otherwise: error");
            // let's exit for now
            return;
        }
        Collections.shuffle(cards, new Random());
    }

    @Override
    public String toString() {
        return "PokerDeck {" + System.lineSeparator() +
                "\tcardsInDeck = " + cardsInDeck + System.lineSeparator() +
                "\tcards = " +
                cards.stream().reduce("", (partialResult, card) -> partialResult + "\n" + card.toString(), String::concat)
                + System.lineSeparator() +
                '}';
    }

    public String toPrettyString() {
        StringBuilder builder = new StringBuilder();
        for (Card card : cards) {
            builder.append(card.getValue().getValueCode());
            builder.append(card.getColor().getColorCharacter());
//            builder.append(System.lineSeparator());
            builder.append(" ");
        }
        return builder.toString();
    }
}
