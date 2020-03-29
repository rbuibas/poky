package com.poky.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testing the poker deck")
class PokerDeckTest {

    /**
     * Maximum number of cards in deck.
     */
    int CARDS_IN_STARTING_DECK = 52;

    private PokerDeck pokerDeck;

    @BeforeEach
    public void setUp() {
        pokerDeck = new PokerDeck();
    }

    @Test
    void initialize() {
        /*
        THe deck is initialized when the created.
        In order to test deck initialization one should:
        - deep clone the initial object
        - do some actions, like draw, shuffle on the original deck
        - compare the deck with the deep clone
        - re-initialize the object and then compare again
         */
//        Still considering whether this is a good idea
    }

    @Test
    void burnCard() {
        fail("Not yet implemented.");
    }

    @Test
    void reset() {
        fail("Not yet implemented.");
    }

    @Test
    void getLiveCards() {
        fail("Not yet implemented.");
    }

    @Test
    void getDealtCards() {
        fail("Not yet implemented.");
    }

    @Test
    void getBurntCards() {
        fail("Not yet implemented.");
    }

    @Test
    void getAllCards() {
        fail("Not yet implemented.");
    }

    @Nested
    @DisplayName("Test deck resetting")
    class DeckResetting {
        @Test
        @DisplayName("Test deck resetting")
        void resetDeck() {
            Card card = pokerDeck.dealCard();
        }
    }

    @Nested
    @DisplayName("Test deck shuffling")
    class DeckShuffling {
        @Test
        @DisplayName("Test deck shuffling")
        void shuffleDeck() {
            List<Card> cardsInInitialDeck = new ArrayList<>(pokerDeck.getAllCards());
            pokerDeck.shuffle();
            List<Card> cardsInDeckAfterShuffling = new ArrayList<>(pokerDeck.getAllCards());
            assertNotEquals(cardsInInitialDeck, cardsInDeckAfterShuffling);
        }
    }

    @Nested
    @DisplayName("Test card dealing")
    class CardDealing {
        @Test
        void dealOneCard() {
            assertEquals(CARDS_IN_STARTING_DECK, pokerDeck.getLiveCards().size());
            pokerDeck.dealCard();
            assertEquals(CARDS_IN_STARTING_DECK - 1, pokerDeck.getLiveCards().size());
            assertEquals(1, pokerDeck.getDealtCards().size());
        }

        @Test
        void dealThreeCards() {
            assertEquals(CARDS_IN_STARTING_DECK, pokerDeck.getLiveCards().size());
            for (int i = 0; i < 3; i++) {
                pokerDeck.dealCard();
            }
            assertEquals(CARDS_IN_STARTING_DECK - 3, pokerDeck.getLiveCards().size());
            assertEquals(3, pokerDeck.getDealtCards().size());
        }

        @Test
        void dealMoreCardsThanAvailable() {
            assertEquals(CARDS_IN_STARTING_DECK, pokerDeck.getLiveCards().size());
            for (int i = 0; i < CARDS_IN_STARTING_DECK + 1; i++) {
                pokerDeck.dealCard();
            }
            assertEquals(CARDS_IN_STARTING_DECK - 3, pokerDeck.getLiveCards().size());
            assertEquals(3, pokerDeck.getDealtCards().size());
        }
    }
}