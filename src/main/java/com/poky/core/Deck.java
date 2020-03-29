package com.poky.core;

import java.util.List;

public interface Deck<E> {
    /**
     * Initialize the deck by adding the cards.
     */
    void initialize();

    /**
     * Reset the deck by making all the cards available.
     * Implementation can chose whether reset means re-initialization.
     */
    void reset();

    /**
     * Shuffle the cards in the deck.
     */
    void shuffle();

    /**
     * Draw next card from the deck.
     * The retrieval is <b>destructive</b>, i.e. the card will not be available in for drawing any longer.
     * Implementation should deal with the disposal or safe-keeping of the card.
     *
     * @return a Card from the deck.
     */
    Card dealCard();

    /**
     * Get all the existing cards in the deck.
     *
     * @return all the cards in the deck
     */
    List<E> getAllCards();

}
