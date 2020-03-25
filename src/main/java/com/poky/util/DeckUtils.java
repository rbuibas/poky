package com.poky.util;

import com.poky.core.Card;
import com.poky.core.Deck;

import java.util.List;

public class DeckUtils {

    public String deckToPrettyString(Deck<Card> deck) {
        List<Card> cards = deck.getCards();

        StringBuilder builder = new StringBuilder();
        for (Card card : cards) {
            builder.append(card.getValue().getValueCode());
            builder.append(card.getColor().getColorCharacter());
//            builder.append(System.lineSeparator());
            builder.append(" ");
        }
        return builder.toString();
    }
}
