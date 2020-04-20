package com.poky;

import com.poky.core.Card;
import com.poky.core.Deck;
import com.poky.game.PokerGame;
import com.poky.player.MachinePokerPlayer;
import com.poky.player.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EntryPoint {
    static final Logger log = LogManager.getLogger();

    static PokerGame pokerGame;
    static Deck<Card> pokerDeck;
    static Player player1;
    static Player player2;

    public static void main(String[] args) {
        pokerGame = new PokerGame();
        pokerGame.initGame();
//        players.put("Flying Piglet",)
        pokerGame.addPlayer(new MachinePokerPlayer("Extreme Blob"));
        pokerGame.addPlayer(new MachinePokerPlayer("Sitting Frog"));

        player1 = pokerGame.getPlayer("Extreme Blob");
        player2 = pokerGame.getPlayer("Sitting Frog");

        pokerGame.playGame(player1, player2);
    }
}
