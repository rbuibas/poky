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
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Testing Hand Calculation")
@ExtendWith(MockitoExtension.class)
public class FourOfKindCalculationTest {

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
    Card mockCardAceOfClub;
    @Mock
    Card mockCardAceOfDiamond;
    @Mock
    Card mockCardAceOfSpade;

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
        Mockito.lenient().when(mockCardAceOfClub.getValueOrdinal()).thenReturn(12);
        Mockito.lenient().when(mockCardAceOfClub.getColor()).thenReturn(Color.Club);
        Mockito.lenient().when(mockCardAceOfHeart.getValueOrdinal()).thenReturn(12);
        Mockito.lenient().when(mockCardAceOfHeart.getColor()).thenReturn(Color.Heart);
        Mockito.lenient().when(mockCardAceOfDiamond.getValueOrdinal()).thenReturn(12);
        Mockito.lenient().when(mockCardAceOfDiamond.getColor()).thenReturn(Color.Diamond);
        Mockito.lenient().when(mockCardAceOfSpade.getValueOrdinal()).thenReturn(12);
        Mockito.lenient().when(mockCardAceOfSpade.getColor()).thenReturn(Color.Spade);
    }

    @Nested
    class FourOfKindAbsent {
        @Test
        void checkWith7Cards_OneShortOfFourOfKind() {
            boolean isFourOfKind = handCalculator.checkFourOfKind(Arrays.asList(
                    mockCardTwoOfDiamond,
                    mockCardAceOfClub,
                    mockCardAceOfHeart,
                    mockCardThreeOfDiamond,
                    mockCardKingOfHeart,
                    mockCardAceOfSpade,
                    mockCardKingOfSpade
            ));
            assertFalse(isFourOfKind);
        }

        @Test
        void checkWith7Cards_TwoShortOfFourOfKind() {
            boolean isFourOfKind = handCalculator.checkFourOfKind(Arrays.asList(
                    mockCardTwoOfDiamond,
                    mockCardThreeOfDiamond,
                    mockCardFourOfDiamond,
                    mockCardFiveOfDiamond,
                    mockCardAceOfClub,
                    mockCardAceOfHeart,
                    mockCardKingOfSpade
            ));
            assertFalse(isFourOfKind);
        }

        @Test
        void checkWith6Cards_OneShortOfFourOfKind() {
            boolean isFourOfKind = handCalculator.checkFourOfKind(Arrays.asList(
                    mockCardTwoOfDiamond,
                    mockCardThreeOfDiamond,
                    mockCardAceOfDiamond,
                    mockCardAceOfClub,
                    mockCardAceOfHeart,
                    mockCardKingOfSpade
            ));
            assertFalse(isFourOfKind);
        }

        @Test
        void checkWith5Cards_TwoPair() {
            boolean isFourOfKind = handCalculator.checkFourOfKind(Arrays.asList(
                    mockCardAceOfClub,
                    mockCardAceOfHeart,
                    mockCardKingOfHeart,
                    mockCardKingOfSpade,
                    mockCardTwoOfDiamond
            ));
            assertFalse(isFourOfKind);
        }

        @Test
        void checkWith4Cards() {
            boolean isFourOfKind = handCalculator.checkFourOfKind(Arrays.asList(
                    mockCardThreeOfDiamond,
                    mockCardAceOfHeart,
                    mockCardTwoOfDiamond,
                    mockCardKingOfSpade
            ));
            assertFalse(isFourOfKind);
        }

        // cases with not enough cards - automatic false
        @Test
        void checkWith3Cards() {
            boolean isFourOfKind = handCalculator.checkFourOfKind(Arrays.asList(
                    mockCardTwoOfDiamond,
                    mockCardAceOfHeart,
                    mockCardKingOfSpade
            ));
            assertFalse(isFourOfKind);
        }

        @Test
        void checkWith2Cards() {
            boolean isFourOfKind = handCalculator.checkFourOfKind(Arrays.asList(
                    mockCardTwoOfDiamond,
                    mockCardKingOfSpade
            ));
            assertFalse(isFourOfKind);
        }

        @Test
        void checkWith1Card() {
            boolean isFourOfKind = handCalculator.checkFourOfKind(Collections.singletonList(
                    mockCardKingOfSpade
            ));
            assertFalse(isFourOfKind);
        }
    }

    @Nested
    class FourOfKindPresent {
        @Test
        void checkWith7Cards_FourOfKindAtTheBeginning() {
            boolean isFourOfKind = handCalculator.checkFourOfKind(Arrays.asList(
                    mockCardAceOfDiamond,
                    mockCardAceOfSpade,
                    mockCardAceOfClub,
                    mockCardAceOfHeart,
                    mockCardKingOfSpade,
                    mockCardTwoOfDiamond,
                    mockCardThreeOfDiamond
            ));
            assertTrue(isFourOfKind);
        }

        @Test
        void checkWith7Cards_FourOfKindAtTheEnd() {
            boolean isFourOfKind = handCalculator.checkFourOfKind(Arrays.asList(
                    mockCardKingOfSpade,
                    mockCardThreeOfDiamond,
                    mockCardTwoOfDiamond,
                    mockCardAceOfDiamond,
                    mockCardAceOfSpade,
                    mockCardAceOfClub,
                    mockCardAceOfHeart
            ));
            assertTrue(isFourOfKind);
        }

        @Test
        void checkWith7Cards_FourOfKindSpread() {
            boolean isFourOfKind = handCalculator.checkFourOfKind(Arrays.asList(
                    mockCardAceOfDiamond,
                    mockCardKingOfSpade,
                    mockCardAceOfClub,
                    mockCardThreeOfDiamond,
                    mockCardTwoOfDiamond,
                    mockCardAceOfSpade,
                    mockCardAceOfHeart
            ));
            assertTrue(isFourOfKind);
        }

        @Test
        void checkWith7Cards_FourOfKindInTheMiddle() {
            boolean isFourOfKind = handCalculator.checkFourOfKind(Arrays.asList(
                    mockCardThreeOfDiamond,
                    mockCardTwoOfDiamond,
                    mockCardAceOfDiamond,
                    mockCardAceOfSpade,
                    mockCardAceOfHeart,
                    mockCardAceOfClub,
                    mockCardKingOfSpade
            ));
            assertTrue(isFourOfKind);
        }

        @Test
        void checkWith6Cards_FourOfKindAtTheBeginning() {
            boolean isFourOfKind = handCalculator.checkFourOfKind(Arrays.asList(
                    mockCardAceOfDiamond,
                    mockCardAceOfSpade,
                    mockCardAceOfClub,
                    mockCardAceOfHeart,
                    mockCardKingOfSpade,
                    mockCardTwoOfDiamond
            ));
            assertTrue(isFourOfKind);
        }

        @Test
        void checkWith5Cards_FourOfKind() {
            boolean isFourOfKind = handCalculator.checkFourOfKind(Arrays.asList(
                    mockCardAceOfDiamond,
                    mockCardAceOfSpade,
                    mockCardAceOfClub,
                    mockCardAceOfHeart,
                    mockCardKingOfSpade
            ));
            assertTrue(isFourOfKind);
        }

        @Test
        void checkWith4Cards_FourOfKind() {
            boolean isFourOfKind = handCalculator.checkFourOfKind(Arrays.asList(
                    mockCardAceOfDiamond,
                    mockCardAceOfSpade,
                    mockCardAceOfClub,
                    mockCardAceOfHeart
            ));
            assertTrue(isFourOfKind);
        }
    }
}