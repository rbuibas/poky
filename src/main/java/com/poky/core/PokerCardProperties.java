package com.poky.core;

public class PokerCardProperties {
    public enum Color {
        Heart, Spade, Club, Diamond;

        private static final Color[] colors = Color.values();

        public static Color getColor(int i) {
            return Color.colors[i];
        }
    }

    public enum Value {
        Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King, Ace;

        private static final Value[] values = Value.values();

        public static Value getValue(int i) {
            return Value.values[i];
        }
    }
}
