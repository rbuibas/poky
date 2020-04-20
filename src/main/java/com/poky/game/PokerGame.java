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
    static final Logger log = LogManager.getLogger(PokerGame.class);

    private final Map<String, Player> playersMap;
    private final List<Card> communityCards;
    private String gameName;
    private PokerDeck pokerDeck;
    private int numberOfPlayers;


    static int player1Wins;
    static int player2Wins;
    static int ties;

    static PokerCalculator.HandRanking playerOneHandRank;
    static PokerCalculator.HandRanking playerTwoHandRank;

    static PokerCalculator pokerCalculator = new PokerCalculator();

    public PokerGame() {
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
        playersMap.remove(player.getName());
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


    /////////////////////////////////////////////// MOVED FROM MAIN ///////////////////////////////////


    public void playGame(Player player1, Player player2) {

        for (int i = 0; i < 10000; i++) {
            playRound(player1, player2);
        }

        log.info(player1.getName() + " won " + player1Wins + " times.");
        log.info(player2.getName() + " won " + player2Wins + " times.");
        log.info("I was a tie " + ties + " times.");
    }

    public PokerCalculator.HandRanking evaluatePlayerHand(Player player, StringBuilder builder) {
        List<Card> playerCards = new ArrayList<>();
        /*
        Bugfix: used to just get the community cards reference and ended up
        with a reference to an increasing list, due to addAll later.

        General note:
        I don't really like this and would prefer that cards are never created
        out of place, i.e. cards are only created once per game, at the beginning.
        This should be enforced somehow.
        Also cards should not be copied.
        Or at least their authenticity should be verifiable.
         */
        playerCards.addAll(this.getCommunityCards());
        playerCards.addAll(player.getHoleCards());
        PokerCalculator.HandRanking handRanking = pokerCalculator.calculateHand(playerCards);
        builder.append(player.getName());
        builder.append(" has ");
        builder.append(handRanking.getRankingName());
        builder.append(System.getProperty("line.separator"));
        return handRanking;
    }

    public void playRound(Player player1, Player player2) {
        StringBuilder builder = new StringBuilder();
        nextHand();

        player1.addHoleCard(pokerDeck.dealCard());
        player2.addHoleCard(pokerDeck.dealCard());
        player1.addHoleCard(pokerDeck.dealCard());
        player2.addHoleCard(pokerDeck.dealCard());

        dealFlop();
        dealTurn();
        dealRiver();

        builder.append(this.toString());

        // Player 1
        playerOneHandRank = evaluatePlayerHand(player1, builder);
        // Player 2
        playerTwoHandRank = evaluatePlayerHand(player2, builder);

        if (playerOneHandRank.ordinal() > playerTwoHandRank.ordinal()) {
            builder.append(player1.getName());
            builder.append(" wins!");
            player1Wins++;
        } else if (playerOneHandRank.ordinal() < playerTwoHandRank.ordinal()) {
            builder.append(player2.getName());
            builder.append(" wins!");
            player2Wins++;
        } else {
            builder.append(" It's a tie!");
            ties++;
        }

        builder.append(System.getProperty("line.separator"));

        log.info(builder.toString());
    }
}
