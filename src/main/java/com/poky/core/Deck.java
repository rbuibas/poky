package com.poky.core;

import java.util.List;

public interface Deck<E> {
    void resetDeck();


    void shuffleDeck();

    /**
     * Draw next card from the deck.
     * The retrieval is destructive, i.e. the Card will be removed from the Deck.
     * The Deck is shuffled in advanced, meaning the draw is not random.
     *
     * @return a Card from the deck.
     */
    Card drawCard();

    List<E> getCards();

}
