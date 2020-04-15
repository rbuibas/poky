package com.poky.core;

public class PokerCardProperties {
    public enum Color {
        Heart('\u2764'),
        Spade('\u2660'),
        Club('\u2663'),
        Diamond('\u2666');

        private final char colorCharacter;

        Color(char colorCharacter) {
            this.colorCharacter = colorCharacter;
        }

        private static final Color[] colors = Color.values();

        public static Color getColor(int i) {
            return Color.colors[i];
        }

        public char getColorCharacter() {
            return colorCharacter;
        }
    }

    public enum Value {
        Two("2"), // numeric value 0
        Three("3"),
        Four("4"),
        Five("5"),
        Six("6"),
        Seven("7"),
        Eight("8"),
        Nine("9"),
        Ten("10"),
        Jack("J"),
        Queen("Q"),
        King("K"),
        Ace("A"); // numeric value 12

        private final String valueCode;

        Value(String valueCode) {
            this.valueCode = valueCode;
        }

        private static final Value[] values = Value.values();

        public static Value getValue(int i) {
            return Value.values[i];
        }

        public String getValueCode() {
            return valueCode;
        }
    }
}
