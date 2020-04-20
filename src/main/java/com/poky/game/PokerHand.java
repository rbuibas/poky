package com.poky.game;

import com.poky.core.Card;

import java.util.List;

/**
 * This class represents a poker hand.
 * A poker hand can have from 2 to 7 cards.
 * A complete hand has 5 cards and in case there are more cards to chose from,
 * the remainder of the cards will not be considered in the hand ranking calculation
 */
public class PokerHand {
    /**
     * All the cards which can be used to form a hand.
     * If {@link #allCards} has more than 5 cards, the best 5-cards hand
     * will be created and stored in {@link #handCards}, while the rest of the cards will be ignored.
     */
    private List<Card> allCards;
    /**
     * List of the cards forming a rankable poker hand.
     * Number of cards can vary from 2 to 5 and it will be the same as the
     * number of cards in {@link #allCards}, as long as {@link #allCards}
     * does not have more than 5 cards.</br>
     * This list can only be modified internally, based on the additions to
     * {@link #allCards}. From the outside, it is effectively read-only.
     */
    private List<Card> handCards;


    /**
     * Returning a list of all the cards that can be used to form the hand.
     *
     * @return {@link #allCards}
     */
    public List<Card> getAllCards() {
        return allCards;
    }

    /**
     * Return the list of the cards forming the highest ranking hand out of
     * all the available cards ({@link #allCards})
     *
     * @return {@link #handCards}
     */
    public List<Card> getHandCards() {
        return handCards;
    }

    /**
     * Adds a new card to the hand, triggering the hand ranking recalculation.
     *
     * @param card to be added to the hand
     */
    public void addCardToHand(Card card) {

    }

    /**
     * @return ranking of the current hand
     */
    public PokerHandRanking getHandRanking() {
        throw new UnsupportedOperationException();
    }
}
