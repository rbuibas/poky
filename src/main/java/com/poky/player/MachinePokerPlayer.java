package com.poky.player;

public class MachinePokerPlayer extends PokerPlayer {
    private final String playerName;

    public MachinePokerPlayer(String playerName) {
        this.playerName = playerName;
    }

    @Override
    public String getName() {
        return playerName;
    }

    @Override
    public String toString() {
        return getName();
    }
}
