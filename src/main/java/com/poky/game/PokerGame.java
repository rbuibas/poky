package com.poky.game;

import com.poky.core.Card;
import com.poky.core.Deck;
import com.poky.core.PokerDeck;
import com.poky.player.Player;
import com.poky.util.DeckUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PokerGame {
    Logger log;

    private final Map<String, Player> playersMap;
    private final List<Card> communityCards;
    private String gameName;
    private PokerDeck pokerDeck;
    private int numberOfPlayers;

    public PokerGame() {
        log = LogManager.getLogger(this.getClass());
        playersMap = new HashMap<>();
        communityCards = new ArrayList<>();
    }

    /**
     * Initialize the game:<br/>
     * <ul>
     *     <li>Create the deck</li>
     *     <li>Shuffle the deck</li>
     * </ul>
     */
    public void initGame() {
        pokerDeck = new PokerDeck();
        log.info(DeckUtils.deckToPrettyString(getPokerDeck()));
        getPokerDeck().shuffle();
        log.info(DeckUtils.deckToPrettyString(getPokerDeck()));
    }

    public void nextHand() {
        playersMap.values().forEach(Player::resetHand);
        communityCards.clear();
        pokerDeck.reset();
        pokerDeck.shuffle();
    }

    public String addPlayer(Player player) {
        playersMap.put(player.getName(), player);
        return player.getName();
    }

    public Player getPlayer(String playerName) {
        return playersMap.get(playerName);
    }

    public void dealFlop() {
        pokerDeck.burnCard();
        communityCards.add(pokerDeck.dealCard());
        communityCards.add(pokerDeck.dealCard());
        communityCards.add(pokerDeck.dealCard());
    }

    public void dealTurn() {
        pokerDeck.burnCard();
        communityCards.add(pokerDeck.dealCard());
    }

    public void dealRiver() {
        pokerDeck.burnCard();
        communityCards.add(pokerDeck.dealCard());
    }

    /**
     * Remove a player from the game.
     * The player is being identified with a reference to a {@link Player}
     * TODO: consider if this is ok
     *
     * @param player player identifier
     */
    public void removePlayer(Player player) {
        playersMap.remove(player);
    }

    public Deck<Card> getPokerDeck() {
        return pokerDeck;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Game: ");
        builder.append(gameName);
        builder.append(System.getProperty("line.separator"));
        builder.append(System.getProperty("line.separator"));
        builder.append("Board: ");
        communityCards.forEach(card -> builder.append(card.toString()));
        builder.append(System.getProperty("line.separator"));
        builder.append(System.getProperty("line.separator"));
        for (Map.Entry<String, Player> entry : playersMap.entrySet()) {
            builder.append("Player ");
            builder.append(entry.getValue().getName());
            builder.append(System.getProperty("line.separator"));
            builder.append("Cards ");
            builder.append(entry.getValue().getHoleCards().get(0).toString());
            builder.append(entry.getValue().getHoleCards().get(1).toString());
            builder.append(System.getProperty("line.separator"));
            builder.append("----------------------------------");
            builder.append(System.getProperty("line.separator"));
        }
        return builder.toString();
    }

    public List<Card> getCommunityCards() {
        return communityCards;
    }
}
