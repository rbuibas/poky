package com.poky.game.handcalculation;

import com.poky.core.Card;
import com.poky.game.HandCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Testing Hand Calculation")
@ExtendWith(MockitoExtension.class)
public class StraightCalculationTest {

    HandCalculator handCalculator;
    @Mock
    Card mockCardSeven;
    @Mock
    Card mockCardEight;
    @Mock
    Card mockCardNine;
    @Mock
    Card mockCardTen;
    @Mock
    Card mockCardJack;
    @Mock
    Card mockCardAce;
    @Mock
    Card mockCardKing;

    @BeforeEach
    void setUp() {
        handCalculator = new HandCalculator();
    }

    @BeforeEach
    void mockTheCards() {
        Mockito.lenient().when(mockCardSeven.getValueOrdinal()).thenReturn(5);
        Mockito.lenient().when(mockCardEight.getValueOrdinal()).thenReturn(6);
        Mockito.lenient().when(mockCardNine.getValueOrdinal()).thenReturn(7);
        Mockito.lenient().when(mockCardTen.getValueOrdinal()).thenReturn(8);
        Mockito.lenient().when(mockCardJack.getValueOrdinal()).thenReturn(9);
        Mockito.lenient().when(mockCardAce.getValueOrdinal()).thenReturn(12);
        Mockito.lenient().when(mockCardKing.getValueOrdinal()).thenReturn(11);
    }

    @Nested
    class StraightAbsent {
        @Test
        void checkWith7Cards_OneShortOfStraight() {
            boolean isStraight = handCalculator.checkStraight(Arrays.asList(
                    mockCardEight,
                    mockCardEight,
                    mockCardNine,
                    mockCardTen,
                    mockCardJack,
                    mockCardAce,
                    mockCardKing
            ));
            assertFalse(isStraight);
        }

        @Test
        void checkWith7Cards_TwoShortOfStraight() {
            boolean isStraight = handCalculator.checkStraight(Arrays.asList(
                    mockCardEight,
                    mockCardEight,
                    mockCardEight,
                    mockCardTen,
                    mockCardJack,
                    mockCardAce,
                    mockCardKing
            ));
            assertFalse(isStraight);
        }

        @Test
        void checkWith6Cards_OneShortOfStraight() {
            boolean isStraight = handCalculator.checkStraight(Arrays.asList(
                    mockCardEight,
                    mockCardNine,
                    mockCardTen,
                    mockCardJack,
                    mockCardAce,
                    mockCardKing
            ));
            assertFalse(isStraight);
        }

        @Test
        void checkWith5Cards_TwoPair() {
            boolean isStraight = handCalculator.checkStraight(Arrays.asList(
                    mockCardNine,
                    mockCardNine,
                    mockCardAce,
                    mockCardAce,
                    mockCardKing
            ));
            assertFalse(isStraight);
        }

        @Test
        void checkWith7Cards_Unsorted_StraightPresent_SpecialCase() {
            boolean isStraight = handCalculator.checkStraight(Arrays.asList(
                    mockCardEight,
                    mockCardSeven,
                    mockCardTen,
                    mockCardNine,
                    mockCardAce,
                    mockCardJack,
                    mockCardKing
            ));
            assertFalse(isStraight);
        }

        // cases with not enough cards - automatic false
        @Test
        void checkWith4Cards() {
            boolean isStraight = handCalculator.checkStraight(Arrays.asList(
                    mockCardNine,
                    mockCardAce,
                    mockCardAce,
                    mockCardKing
            ));
            assertFalse(isStraight);
        }

        @Test
        void checkWith3Cards() {
            boolean isStraight = handCalculator.checkStraight(Arrays.asList(
                    mockCardNine,
                    mockCardAce,
                    mockCardKing
            ));
            assertFalse(isStraight);
        }

        @Test
        void checkWith2Cards() {
            boolean isStraight = handCalculator.checkStraight(Arrays.asList(
                    mockCardNine,
                    mockCardKing
            ));
            assertFalse(isStraight);
        }

        @Test
        void checkWith1Card() {
            boolean isStraight = handCalculator.checkStraight(Arrays.asList(
                    mockCardKing
            ));
            assertFalse(isStraight);
        }
    }

    @Nested
    class StraightPresent {
        @Test
        void checkWith7Cards_StraightAtTheBeginning() {
            boolean isStraight = handCalculator.checkStraight(Arrays.asList(
                    mockCardSeven,
                    mockCardEight,
                    mockCardNine,
                    mockCardTen,
                    mockCardJack,
                    mockCardAce,
                    mockCardKing
            ));
            assertTrue(isStraight);
        }

        @Test
        void checkWith7Cards_StraightAtTheEnd() {
            boolean isStraight = handCalculator.checkStraight(Arrays.asList(
                    mockCardAce,
                    mockCardKing,
                    mockCardSeven,
                    mockCardEight,
                    mockCardNine,
                    mockCardTen,
                    mockCardJack
            ));
            assertTrue(isStraight);
        }

        @Test
        void checkWith7Cards_StraightInTheMiddle() {
            boolean isStraight = handCalculator.checkStraight(Arrays.asList(
                    mockCardAce,
                    mockCardSeven,
                    mockCardEight,
                    mockCardNine,
                    mockCardTen,
                    mockCardJack,
                    mockCardKing
            ));
            assertTrue(isStraight);
        }

        @Test
        void checkWith6Cards_StraightAtTheBeginning() {
            boolean isStraight = handCalculator.checkStraight(Arrays.asList(
                    mockCardSeven,
                    mockCardEight,
                    mockCardNine,
                    mockCardTen,
                    mockCardJack,
                    mockCardKing
            ));
            assertTrue(isStraight);
        }

        @Test
        void checkWith5Cards_Straight() {
            boolean isStraight = handCalculator.checkStraight(Arrays.asList(
                    mockCardSeven,
                    mockCardEight,
                    mockCardNine,
                    mockCardTen,
                    mockCardJack
            ));
            assertTrue(isStraight);
        }
    }
}