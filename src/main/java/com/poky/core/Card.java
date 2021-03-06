package com.poky.core;

public interface Card {

    PokerCardProperties.Color getColor();

    PokerCardProperties.Value getValue();

    String getValueCode();

    int getValueOrdinal();
}
