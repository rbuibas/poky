package com.poky.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class PokerDeckTest {

    private PokerDeck pokerDeck;

    @BeforeEach
    public void setUp() {
        pokerDeck = new PokerDeck();
    }

    @Test
    @DisplayName("Test deck resetting")
    void resetDeck() {
    }

    @Test
    @DisplayName("Test deck shuffling")
    void shuffleDeck() {
        List<Card> cardsInInitialDeck = pokerDeck.getCards();
        pokerDeck.shuffleDeck();
        // compare the deck after it has been shuffled
    }
}