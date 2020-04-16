package com.poky.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testing the poker deck")
class PokerDeckTest {

    /**
     * Maximum number of cards in deck.
     */
    final int CARDS_IN_DECK = 52;

    private PokerDeck pokerDeck;

    @BeforeEach
    public void setUp() {
        pokerDeck = new PokerDeck();
    }

    // Helper methods


    /**
     * Helper method for a standard deal:</br>
     * <ul>
     *     <li>burn one card</li>
     *     <li>deal three cards, i.e. the flop</li>
     *     <li>burn one card</li>
     *     <li>deal one cards, i.e. the turn</li>
     *     <li>burn one card</li>
     *     <li>deal one cards, i.e. the river</li>
     * </ul>
     *
     * @param numberOfRuns how many times to deal (i.e. "run it twice")
     */
    private void standardDeal(int numberOfRuns, PokerDeck pokerDeck) {
        pokerDeck.burnCard(); // first burn
        pokerDeck.dealCard(); // 1st flop card
        pokerDeck.dealCard(); // 2nd flop card
        pokerDeck.dealCard(); // 3rd flop card
        pokerDeck.burnCard(); // second burn
        pokerDeck.dealCard(); // turn card
        pokerDeck.burnCard(); // third burn
        pokerDeck.dealCard(); // river card
    }

    @Test
    void initialize() {
        /*
        The deck is initialized when created.
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
        // probably shouldn't allow multiple burns
        // TODO: verify this
    }

    /**
     * Verify that {@link PokerDeck#getAllCards()} returns all live, dealt
     * and burned cards.
     */
    @Test
    void getAllCards() {
        // two standard deals
        standardDeal(2, pokerDeck);
        assertEquals(pokerDeck.getAllCards().size(),
                pokerDeck.getDealtCards().size() +
                        pokerDeck.getBurntCards().size() +
                        pokerDeck.getLiveCards().size());
    }

    @Nested
    @DisplayName("Test deck resetting")
    class DeckResetting {
        @Test
        @DisplayName("Test deck resetting after dealing two cards")
        void resetDeckAfterDealing() {
            pokerDeck.dealCard();
            pokerDeck.dealCard();
            assertEquals(CARDS_IN_DECK - 2, pokerDeck.getLiveCards().size());
            assertEquals(2, pokerDeck.getDealtCards().size());
            pokerDeck.reset();
            assertEquals(CARDS_IN_DECK, pokerDeck.getLiveCards().size());
        }

        @Test
        @DisplayName("Test deck resetting after burning two cards")
        void resetDeckAfterBurning() {
            pokerDeck.burnCard();
            pokerDeck.burnCard();
            assertEquals(CARDS_IN_DECK - 2, pokerDeck.getLiveCards().size());
            assertEquals(2, pokerDeck.getBurntCards().size());
            pokerDeck.reset();
            assertEquals(CARDS_IN_DECK, pokerDeck.getLiveCards().size());
        }

        @Test
        @DisplayName("Test deck resetting after a standard deal")
        void resetDeckAfterStandardDeal() {
            standardDeal(1, pokerDeck);
            assertEquals(CARDS_IN_DECK - 8, pokerDeck.getLiveCards().size());
            assertEquals(3, pokerDeck.getBurntCards().size());
            assertEquals(5, pokerDeck.getDealtCards().size());
            pokerDeck.reset();
            assertEquals(CARDS_IN_DECK, pokerDeck.getLiveCards().size());
        }

        @Test
        @DisplayName("Test resetting locked deck after a standard deal")
        void resetLockedDeckAfterStandardDeal() {
            pokerDeck.setDeckLocked(true);
            standardDeal(1, pokerDeck);
            assertEquals(CARDS_IN_DECK - 8, pokerDeck.getLiveCards().size());
            assertEquals(3, pokerDeck.getBurntCards().size());
            assertEquals(5, pokerDeck.getDealtCards().size());
            pokerDeck.reset();
            assertEquals(CARDS_IN_DECK - 8, pokerDeck.getLiveCards().size());
            assertEquals(3, pokerDeck.getBurntCards().size());
            assertEquals(5, pokerDeck.getDealtCards().size());
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

        @Test
        @DisplayName("Test deck shuffling")
        void shuffleLockedDeck() {
            pokerDeck.setDeckLocked(true);
            List<Card> cardsInInitialDeck = new ArrayList<>(pokerDeck.getAllCards());
            pokerDeck.shuffle();
            List<Card> cardsInDeckAfterShuffling = new ArrayList<>(pokerDeck.getAllCards());
            assertEquals(cardsInInitialDeck, cardsInDeckAfterShuffling);
        }
    }

    @Nested
    @DisplayName("Test card dealing")
    class CardDealing {
        @Test
        void dealOneCard() {
            assertEquals(CARDS_IN_DECK, pokerDeck.getLiveCards().size());
            pokerDeck.dealCard();
            assertEquals(CARDS_IN_DECK - 1, pokerDeck.getLiveCards().size());
            assertEquals(1, pokerDeck.getDealtCards().size());
            assertFalse(pokerDeck.getLiveCards().contains((pokerDeck.getDealtCards().get(0))));
        }

        @Test
        void dealThreeCards() {
            assertEquals(CARDS_IN_DECK, pokerDeck.getLiveCards().size());
            for (int i = 0; i < 3; i++) {
                pokerDeck.dealCard();
            }
            assertEquals(CARDS_IN_DECK - 3, pokerDeck.getLiveCards().size());
            assertEquals(3, pokerDeck.getDealtCards().size());
            pokerDeck.getDealtCards().forEach(card -> assertFalse(pokerDeck.getLiveCards().contains(card)));
        }

        @Test
        void dealMoreCardsThanAvailable() {
            assertEquals(CARDS_IN_DECK, pokerDeck.getLiveCards().size());
            for (int i = 0; i < CARDS_IN_DECK; i++) {
                pokerDeck.dealCard();
            }
            assertEquals(0, pokerDeck.getLiveCards().size());
            assertEquals(CARDS_IN_DECK, pokerDeck.getDealtCards().size());
            // now deal one extra card
            assertThrows(NoSuchElementException.class, () -> pokerDeck.dealCard());
        }
    }
}