package com.poky.player;

import com.poky.core.Card;

import java.util.Arrays;
import java.util.List;

public abstract class PokerPlayer implements Player {

    /**
     * The {@link #holeCards} will contain exactly two cards.
     */
    private final List<Card> holeCards = Arrays.asList(new Card[2]);
    /**
     * How many cards does the player currently hold.
     * TODO: consider better solution for this.
     */
    int cardsInHand;

    @Override
    public List<Card> getHoleCards() {
        return holeCards;
    }

    @Deprecated
    public void resetHand() {
        cardsInHand = 0;
    }

    public void addHoleCard(Card card) {
        holeCards.set(cardsInHand++, card);
    }

    @Override
    public String toString() {
        List<Card> holeCards = getHoleCards();
        return this.getName() + " "
                + holeCards.get(0).toString() + " "
                + holeCards.get(1).toString() + " ";
    }
}
