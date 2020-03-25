package com.poky.game;

import com.poky.core.Card;
import com.poky.core.Deck;
import com.poky.core.PokerDeck;
import com.poky.util.DeckUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PokerGame {
    private DeckUtils deckUtils = new DeckUtils();
    Logger log;

    public PokerGame() {
        log = LogManager.getLogger(this.getClass());
    }

    public void initGame() {
        Deck<Card> pokerDeck = new PokerDeck();
        log.info(deckUtils.deckToPrettyString(pokerDeck));
        pokerDeck.shuffleDeck();
        log.info(deckUtils.deckToPrettyString(pokerDeck));
    }
}
