package com.poky.game;

import com.poky.core.Card;

import java.util.Comparator;
import java.util.List;

/**
 * Poker hand-strength calculator and comparator
 * <p>
 * Simple version of the calculator:
 * First, calculate the hand by checking for each of the possible hands:
 * 1. high card
 * 2. pair
 * 3. two-pair
 * 4. three of a kind
 * 5. straight
 * 6. flush
 * 7. full-house
 * 8. 4 of a kind
 * 9. straight-flush
 * 10. royal-flush
 * <p>
 * Calculation can be done for two cards and above (up to 7).
 * Best 5 cards are taken.
 * If less cards exist, some rankings will not be available.
 * <p>
 * Second, compare with other player's hand.
 * <p>
 * If equal, check how high (usually just check the highest card)
 */
public class PokerCalculator {
    public void calculateHand(List<Card> cards) {
        HandCalculator handCalculator = new HandCalculator();
        // almost all the checks go easier if the cards are sorted
        cards.sort(Comparator.comparingInt(Card::getValueOrdinal));
        handCalculator.checkFlush(cards);
    }

    public enum HandRanking {
        HighCard,
        Pair,
        TwoPair,
        ThreeKind,
        Straight,
        Flush,
        FullHouse,
        FourKind,
        StraightFlush,
        RoyalFlush
    }
}
