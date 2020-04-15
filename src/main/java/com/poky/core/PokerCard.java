package com.poky.core;

import com.poky.core.PokerCardProperties.Color;
import com.poky.core.PokerCardProperties.Value;

public class PokerCard implements Card {

    private final Color color;
    private final Value value;

    public PokerCard(final Color color, final Value value) {
        this.color = color;
        this.value = value;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public Value getValue() {
        return value;
    }

    @Override
    public String getValueCode() {
        return value.getValueCode();
    }

    @Override
    public int getValueOrdinal() {
        return value.ordinal();
    }

    @Override
    public String toString() {
        return value.getValueCode() + color.getColorCharacter() + " ";
    }
}

