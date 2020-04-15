package com.poky;

import com.poky.core.Card;
import com.poky.core.Deck;
import com.poky.game.PokerCalculator;
import com.poky.game.PokerGame;
import com.poky.player.MachinePokerPlayer;
import com.poky.player.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class EntryPoint {
    static Logger log = LogManager.getLogger();

    static PokerGame pokerGame;
    static Deck<Card> pokerDeck;
    static Player player1;
    static Player player2;

    public static void main(String[] args) {
        pokerGame = new PokerGame();
        pokerGame.initGame();
//        players.put("Flying Piglet",)
        pokerGame.addPlayer(new MachinePokerPlayer("Flying Piglet"));
        pokerGame.addPlayer(new MachinePokerPlayer("Sitting Frog"));

        // get the deck and shuffle it
        pokerDeck = pokerGame.getPokerDeck();

        player1 = pokerGame.getPlayer("Flying Piglet");
        player2 = pokerGame.getPlayer("Sitting Frog");

        for (int i = 0; i < 5; i++) {
            playRound();
        }
    }

    private static void playRound() {
        pokerGame.nextHand();

        player1.addHoleCard(pokerDeck.dealCard());
        player2.addHoleCard(pokerDeck.dealCard());
        player1.addHoleCard(pokerDeck.dealCard());
        player2.addHoleCard(pokerDeck.dealCard());

        pokerGame.dealFlop();
        pokerGame.dealTurn();
        pokerGame.dealRiver();

        log.info(pokerGame.toString());

        PokerCalculator pokerCalculator = new PokerCalculator();
        List<Card> allCards = pokerGame.getCommunityCards();
        allCards.addAll(player1.getHoleCards());
        pokerCalculator.calculateHand(allCards);
    }
}
