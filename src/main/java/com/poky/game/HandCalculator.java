package com.poky.game;

import com.poky.core.Card;
import com.poky.core.PokerCardProperties;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

public class HandCalculator {
    private static final int FIVE_CARDS = 5;
    private static final int FOUR_CARDS = 4;
    private static final int THREE_CARDS = 3;
    private static final int TWO_CARDS = 2;
    private static final int ACE_CARD_VALUE = 12;

    /**
     * Check if flush can be put together from the cards provided in the list.<br/>
     * If the number of cards is below {@link #FIVE_CARDS}, false is automatically returned.<br/>
     *
     * @param sortedCards list of <b>sorted</b> cards
     * @return true if flush is found, false otherwise
     */
    public boolean checkFlush(List<Card> sortedCards) {
        if (sortedCards.size() < FIVE_CARDS) return false;
        Map<PokerCardProperties.Color, List<Card>> mapByColors =
                sortedCards.stream().collect(groupingBy(Card::getColor)); // so cool!
        return mapByColors.values()
                .stream()
                .filter(sameColor -> sameColor.size() >= FIVE_CARDS)
                .findAny().isPresent();
//                .stream()
//                .max(Comparator.comparingInt(Card::getValueOrdinal));
    }

    /**
     * Check if four same value cards exist in the list of provided cards.<br/>
     * If the number of cards is below {@link #FOUR_CARDS}, false is automatically returned.<br/>
     *
     * @param sortedCards list of <b>sorted</b> cards
     * @return true if four cards of the same value is found, false otherwise
     */
    public boolean checkFourOfKind(List<Card> sortedCards) {
        if (sortedCards.size() < FOUR_CARDS) return false;
        Map<Integer, List<Card>> mapByValue =
                sortedCards.stream().collect(groupingBy(Card::getValueOrdinal));
        return mapByValue.values()
                .stream()
                .anyMatch(values -> values.size() == FOUR_CARDS);
    }

    /**
     * Check if three same value cards exist in the list of provided cards.<br/>
     * If the number of cards is below {@link #THREE_CARDS}, false is automatically returned.<br/>
     *
     * @param sortedCards list of <b>sorted</b> cards
     * @return true if four cards of the same value is found, false otherwise
     */
    public boolean checkThreeOfKind(List<Card> sortedCards) {
        if (sortedCards.size() < THREE_CARDS) return false;
        Map<Integer, List<Card>> mapByValue =
                sortedCards.stream().collect(groupingBy(Card::getValueOrdinal));
        return mapByValue.values()
                .stream()
                .anyMatch(values -> values.size() == THREE_CARDS);
    }

    /**
     * Check if two pairs of same value cards exist in the list of provided cards.<br/>
     * If the number of cards is below {@link #THREE_CARDS}, false is automatically returned.<br/>
     *
     * @param sortedCards list of <b>sorted</b> cards
     * @return true if four cards of the same value is found, false otherwise
     */
    public boolean checkTwoPair(List<Card> sortedCards) {
        if (sortedCards.size() < FOUR_CARDS) return false;
        Map<Integer, List<Card>> mapByValue =
                sortedCards.stream().collect(groupingBy(Card::getValueOrdinal));
        return mapByValue.values()
                .stream()
                .filter(values -> values.size() == TWO_CARDS)
                .count() > 1;
    }

    /**
     * Check if two pairs of same value cards exist in the list of provided cards.<br/>
     * If the number of cards is below {@link #THREE_CARDS}, false is automatically returned.<br/>
     *
     * @param sortedCards list of <b>sorted</b> cards
     * @return true if four cards of the same value is found, false otherwise
     */
    public boolean checkPair(List<Card> sortedCards) {
        if (sortedCards.size() < TWO_CARDS) return false;
        Map<Integer, List<Card>> mapByValue =
                sortedCards.stream().collect(groupingBy(Card::getValueOrdinal));
        return mapByValue.values()
                .stream()
                .filter(values -> values.size() == TWO_CARDS)
                .count() == 1;
    }

    /**
     * Check if straight can be put together from the cards provided in the list.<br/>
     * If the number of cards is below 5, false is automatically returned.<br/>
     * <b>Important</b>: the provided cards must be sorted by value.
     *
     * @param sortedCards list of <b>sorted</b> cards
     * @return true if straight is found, false otherwise
     */
    public boolean checkStraight(List<Card> sortedCards) {
        if (sortedCards.size() < FIVE_CARDS) return false;
        // let's do the crude way
        boolean increasing = true;
        for (int i = 0; i <= sortedCards.size() - FIVE_CARDS; i++) {
            increasing = true;
            for (int j = i; j < FIVE_CARDS + i - 1; j++) {
                if ((sortedCards.get(j).getValueOrdinal()) !=
                        sortedCards.get(j + 1).getValueOrdinal() - 1) {
                    increasing = false;
                    break;
                }
            }
            if (increasing) break;
        }
        return increasing;
    }

    /**
     * Check if royal flush exists in provided card list.<br/>
     * If the number of cards is below 5, false is automatically returned.<br/>
     *
     * @param sortedCards list of <b>sorted</b> cards
     * @return true if straight is found, false otherwise
     */
    public boolean checkStraightFlush(List<Card> sortedCards) {
        if (sortedCards.size() < FIVE_CARDS) return false;
        return checkFlush(sortedCards) && checkStraight(sortedCards);
    }

    /**
     * Check if straight flush exists in provided card list.<br/>
     * If the number of cards is below 5, false is automatically returned.<br/>
     *
     * @param sortedCards list of <b>sorted</b> cards
     * @return true if straight is found, false otherwise
     */
    public boolean checkRoyalFlush(List<Card> sortedCards) {
        if (sortedCards.size() < FIVE_CARDS) return false;
        boolean isAceHighFlush;

        Map<PokerCardProperties.Color, List<Card>> mapByColors =
                sortedCards.stream().collect(groupingBy(Card::getColor));
        isAceHighFlush = mapByColors.values()
                .stream()
                .anyMatch(colors -> colors.size() >= FIVE_CARDS &&
                        colors.stream()
                                .filter(card -> card.getValueOrdinal()
                                        == ACE_CARD_VALUE)
                                .findAny().isPresent());
        return isAceHighFlush && checkStraight(sortedCards);
    }

    public boolean checkFullHouse(List<Card> sortedCards) {
        if (sortedCards.size() < FIVE_CARDS) return false;
        return checkThreeOfKind(sortedCards) && checkPair(sortedCards);
    }
}
