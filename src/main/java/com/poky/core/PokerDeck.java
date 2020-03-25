package com.poky.core;

import com.poky.core.PokerCardProperties.Color;
import com.poky.core.PokerCardProperties.Value;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * The playing deck
 */

public class PokerDeck implements Deck<Card> {
    private List<Card> cards;
    // how many cards are in deck at a certain moment
    private int cardsInDeck;
    Logger log;

    private static final int CARDS_IN_STARTING_DECK = 52;

    public PokerDeck() {
        log = LogManager.getLogger(this.getClass());
        cards = new ArrayList<>(CARDS_IN_STARTING_DECK);
        resetDeck();
    }

    @Override
    public void resetDeck() {
        log.info("Resetting Deck");
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
        log.info("Shuffling Deck");
        if (cardsInDeck < 52) {
            // TODO: move this to logging
            System.out.println("LOG: Shuffling incomplete deck. This is ok in some cases of misdeal. Otherwise: error");
            // let's exit for now
            return;
        }
        Collections.shuffle(cards, new Random());
    }

    @Override
    public Card drawCard() {
        log.info("Drawing one card from Deck");
        return null;
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

    public List<Card> getCards() {
        log.info("Getting cards from Deck");
        return cards;
    }
}
