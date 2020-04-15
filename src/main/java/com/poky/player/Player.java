package com.poky.player;

import com.poky.core.Card;

import java.util.List;

public interface Player {
    List<Card> getHoleCards();

    String getName();

    void addHoleCard(Card card);

    void resetHand();
}
