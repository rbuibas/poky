package com.poky.core;

import com.poky.util.DeckUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PokerDeckTest {

    private PokerDeck pokerDeck;
    private DeckUtils deckUtils;

    @BeforeEach
    public void setUp() {
        pokerDeck = new PokerDeck();
        deckUtils = new DeckUtils();
    }

    @Test
    @DisplayName("Test deck resetting")
    void resetDeck() {
    }

    @Test
    @DisplayName("Test deck shuffling")
    void shuffleDeck() {
        List<Card> cardsInInitialDeck = pokerDeck.getCards();
        System.out.println(deckUtils.deckToPrettyString(pokerDeck));
        pokerDeck.shuffleDeck();
        System.out.println(deckUtils.deckToPrettyString(pokerDeck));
        // compare the deck after it has been shuffled
        List<Card> testList = new ArrayList<>();
        List<Card> cardsInDeckAfterShuffling = pokerDeck.getCards();
        assertEquals(cardsInInitialDeck, cardsInDeckAfterShuffling);
    }
}