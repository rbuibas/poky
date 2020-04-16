package com.poky.game.handcalculation;

import com.poky.core.Card;
import com.poky.core.PokerCardProperties.Color;
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
public class TwoPairCalculationTest {

    HandCalculator handCalculator;
    @Mock
    Card mockCardTwoOfDiamond;
    @Mock
    Card mockCardThreeOfDiamond;
    @Mock
    Card mockCardFourOfDiamond;
    @Mock
    Card mockCardFiveOfDiamond;
    @Mock
    Card mockCardKingOfSpade;
    @Mock
    Card mockCardKingOfHeart;
    @Mock
    Card mockCardAceOfHeart;
    @Mock
    Card mockCardQueenOfClub;
    @Mock
    Card mockCardAceOfDiamond;
    @Mock
    Card mockCardAceOfSpade;
    @Mock
    Card mockCardAceOfClub;

    @BeforeEach
    void setUp() {
        handCalculator = new HandCalculator();
    }

    @BeforeEach
    void mockTheCards() {
        Mockito.lenient().when(mockCardTwoOfDiamond.getValueOrdinal()).thenReturn(0);
        Mockito.lenient().when(mockCardTwoOfDiamond.getColor()).thenReturn(Color.Diamond);
        Mockito.lenient().when(mockCardThreeOfDiamond.getValueOrdinal()).thenReturn(1);
        Mockito.lenient().when(mockCardThreeOfDiamond.getColor()).thenReturn(Color.Diamond);
        Mockito.lenient().when(mockCardFourOfDiamond.getValueOrdinal()).thenReturn(2);
        Mockito.lenient().when(mockCardFourOfDiamond.getColor()).thenReturn(Color.Diamond);
        Mockito.lenient().when(mockCardFiveOfDiamond.getValueOrdinal()).thenReturn(3);
        Mockito.lenient().when(mockCardFiveOfDiamond.getColor()).thenReturn(Color.Diamond);
        Mockito.lenient().when(mockCardKingOfSpade.getValueOrdinal()).thenReturn(11);
        Mockito.lenient().when(mockCardKingOfSpade.getColor()).thenReturn(Color.Spade);
        Mockito.lenient().when(mockCardKingOfHeart.getValueOrdinal()).thenReturn(11);
        Mockito.lenient().when(mockCardKingOfHeart.getColor()).thenReturn(Color.Heart);
        Mockito.lenient().when(mockCardQueenOfClub.getValueOrdinal()).thenReturn(10);
        Mockito.lenient().when(mockCardQueenOfClub.getColor()).thenReturn(Color.Club);
        Mockito.lenient().when(mockCardAceOfHeart.getValueOrdinal()).thenReturn(12);
        Mockito.lenient().when(mockCardAceOfHeart.getColor()).thenReturn(Color.Heart);
        Mockito.lenient().when(mockCardAceOfDiamond.getValueOrdinal()).thenReturn(12);
        Mockito.lenient().when(mockCardAceOfDiamond.getColor()).thenReturn(Color.Diamond);
        Mockito.lenient().when(mockCardAceOfSpade.getValueOrdinal()).thenReturn(12);
        Mockito.lenient().when(mockCardAceOfSpade.getColor()).thenReturn(Color.Spade);
        Mockito.lenient().when(mockCardAceOfClub.getValueOrdinal()).thenReturn(12);
        Mockito.lenient().when(mockCardAceOfClub.getColor()).thenReturn(Color.Club);
        Mockito.lenient().when(mockCardAceOfClub.getColor()).thenReturn(Color.Club);
    }

    @Nested
    class TwoPairAbsent {
        @Test
        void checkWith7Cards_AllDifferent() {
            boolean isTwoPair = handCalculator.checkTwoPair(Arrays.asList(
                    mockCardTwoOfDiamond,
                    mockCardThreeOfDiamond,
                    mockCardFourOfDiamond,
                    mockCardFiveOfDiamond,
                    mockCardKingOfHeart,
                    mockCardAceOfSpade,
                    mockCardQueenOfClub
            ));
            assertFalse(isTwoPair);
        }

        @Test
        void checkWith7Cards_OnlyOnePair() {
            boolean isTwoPair = handCalculator.checkTwoPair(Arrays.asList(
                    mockCardTwoOfDiamond,
                    mockCardAceOfDiamond,
                    mockCardFourOfDiamond,
                    mockCardFiveOfDiamond,
                    mockCardQueenOfClub,
                    mockCardAceOfHeart,
                    mockCardKingOfSpade
            ));
            assertFalse(isTwoPair);
        }

        @Test
        void checkWith7Cards_ThreeOfKind() {
            boolean isTwoPair = handCalculator.checkTwoPair(Arrays.asList(
                    mockCardTwoOfDiamond,
                    mockCardThreeOfDiamond,
                    mockCardAceOfDiamond,
                    mockCardQueenOfClub,
                    mockCardAceOfHeart,
                    mockCardKingOfSpade,
                    mockCardAceOfSpade
            ));
            assertFalse(isTwoPair);
        }

        @Test
        void checkWith7Cards_FourOfKind() {
            boolean isTwoPair = handCalculator.checkTwoPair(Arrays.asList(
                    mockCardTwoOfDiamond,
                    mockCardAceOfClub,
                    mockCardAceOfDiamond,
                    mockCardQueenOfClub,
                    mockCardAceOfHeart,
                    mockCardKingOfSpade,
                    mockCardAceOfSpade
            ));
            assertFalse(isTwoPair);
        }

        // should maybe repeat all tis with 6, 5 and 4 cards?

        // cases with not enough cards - automatic false
        @Test
        void checkWith3Cards() {
            boolean isTwoPair = handCalculator.checkTwoPair(Arrays.asList(
                    mockCardTwoOfDiamond,
                    mockCardAceOfHeart,
                    mockCardKingOfSpade
            ));
            assertFalse(isTwoPair);
        }

        @Test
        void checkWith2Cards() {
            boolean isTwoPair = handCalculator.checkTwoPair(Arrays.asList(
                    mockCardTwoOfDiamond,
                    mockCardKingOfSpade
            ));
            assertFalse(isTwoPair);
        }

        @Test
        void checkWith1Card() {
            boolean isTwoPair = handCalculator.checkTwoPair(Arrays.asList(
                    mockCardKingOfSpade
            ));
            assertFalse(isTwoPair);
        }
    }

    @Nested
    class TwoPairPresent {
        @Test
        void checkWith7Cards_TwoPairAtTheBeginning() {
            boolean isTwoPair = handCalculator.checkTwoPair(Arrays.asList(
                    mockCardAceOfDiamond,
                    mockCardAceOfSpade,
                    mockCardKingOfSpade,
                    mockCardKingOfHeart,
                    mockCardQueenOfClub,
                    mockCardTwoOfDiamond,
                    mockCardThreeOfDiamond
            ));
            assertTrue(isTwoPair);
        }

        @Test
        void checkWith7Cards_TwoPairAtTheEnd() {
            boolean isTwoPair = handCalculator.checkTwoPair(Arrays.asList(
                    mockCardThreeOfDiamond,
                    mockCardTwoOfDiamond,
                    mockCardQueenOfClub,
                    mockCardKingOfSpade,
                    mockCardKingOfHeart,
                    mockCardAceOfSpade,
                    mockCardAceOfHeart
            ));
            assertTrue(isTwoPair);
        }

        @Test
        void checkWith7Cards_TwoPairSpread() {
            boolean isTwoPair = handCalculator.checkTwoPair(Arrays.asList(
                    mockCardAceOfDiamond,
                    mockCardKingOfSpade,
                    mockCardQueenOfClub,
                    mockCardThreeOfDiamond,
                    mockCardTwoOfDiamond,
                    mockCardAceOfSpade,
                    mockCardKingOfHeart
            ));
            assertTrue(isTwoPair);
        }

        @Test
        void checkWith6Cards_TwoPairAtTheBeginning() {
            boolean isTwoPair = handCalculator.checkTwoPair(Arrays.asList(
                    mockCardAceOfDiamond,
                    mockCardAceOfSpade,
                    mockCardKingOfHeart,
                    mockCardKingOfSpade,
                    mockCardQueenOfClub,
                    mockCardTwoOfDiamond
            ));
            assertTrue(isTwoPair);
        }

        @Test
        void checkWith5Cards_TwoPair() {
            boolean isTwoPair = handCalculator.checkTwoPair(Arrays.asList(
                    mockCardAceOfDiamond,
                    mockCardAceOfSpade,
                    mockCardKingOfSpade,
                    mockCardKingOfHeart,
                    mockCardQueenOfClub
            ));
            assertTrue(isTwoPair);
        }

        @Test
        void checkWith4Cards_TwoPair() {
            boolean isTwoPair = handCalculator.checkTwoPair(Arrays.asList(
                    mockCardAceOfDiamond,
                    mockCardKingOfSpade,
                    mockCardAceOfSpade,
                    mockCardKingOfHeart
            ));
            assertTrue(isTwoPair);
        }
    }
}