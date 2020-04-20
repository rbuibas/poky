package com.poky.game;

import com.poky.core.Card;

import java.util.Comparator;
import java.util.List;

/**
 * Poker hand-strength calculator and comparator
 * <p>
 * Simple version of the calculator:<br/>
 * First, calculate the hand by checking for each of the possible hands:<br/>
 * <ol>
 * <li>high card</li>
 * <li>pair</li>
 * <li>two-pair</li>
 * <li>three of a kind</li>
 * <li>straight</li>
 * <li>flush</li>
 * <li>full-house</li>
 * <li>4 of a kind</li>
 * <li>straight-flush</li>
 * <li>royal-flush</li>
 * </ol>
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
    public HandRanking calculateHand(List<Card> cards) {
        HandCalculator handCalculator = new HandCalculator();
        // almost all the checks go easier if the cards are sorted
        cards.sort(Comparator.comparingInt(Card::getValueOrdinal));
        // I know this is ugly, worst thing ever, but I want to see it work
        if (handCalculator.checkRoyalFlush(cards)) return HandRanking.RoyalFlush;
        if (handCalculator.checkStraightFlush(cards)) return HandRanking.StraightFlush;
        if (handCalculator.checkFourOfKind(cards)) return HandRanking.FourKind;
        /*
        As it stands, the code will not work if there are two sets of
        three-of-a-kind, which can be a Full House.
        TODO: do it.
         */
        if (handCalculator.checkFullHouse(cards)) return HandRanking.FullHouse;
        if (handCalculator.checkFlush(cards)) return HandRanking.Flush;
        if (handCalculator.checkStraight(cards)) return HandRanking.Straight;
        if (handCalculator.checkThreeOfKind(cards)) return HandRanking.ThreeKind;
        if (handCalculator.checkTwoPair(cards)) return HandRanking.TwoPair;
        if (handCalculator.checkPair(cards)) return HandRanking.Pair;
        return HandRanking.HighCard;
    }

    public enum HandRanking {
        HighCard("High card"),
        Pair("Pair"),
        TwoPair("Two paris"),
        ThreeKind("Three of a kind"),
        Straight("Straight"),
        Flush("Flush"),
        FullHouse("Full house"),
        FourKind("Four of a kind"),
        StraightFlush("Straight flush"),
        RoyalFlush("Royal flush");

        private final String rankingName;

        HandRanking(String rankingName) {
            this.rankingName = rankingName;
        }

        public String getRankingName() {
            return this.rankingName;
        }
    }
}
