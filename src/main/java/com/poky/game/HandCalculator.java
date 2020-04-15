package com.poky.game;

import com.poky.core.Card;
import com.poky.core.PokerCardProperties;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

public class HandCalculator {
    private static final int FIVE_CARDS = 5;

    public boolean checkFlush(List<Card> sortedCards) {
        if (sortedCards.size() < FIVE_CARDS) return false;
        Map<PokerCardProperties.Color, List<Card>> mapByColors =
                sortedCards.stream().collect(groupingBy(Card::getColor)); // so cool!
        return mapByColors.values()
                .stream()
                .anyMatch(colors -> colors.size() >= FIVE_CARDS);
    }

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
}
