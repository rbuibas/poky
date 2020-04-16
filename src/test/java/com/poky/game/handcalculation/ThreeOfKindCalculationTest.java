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
public class ThreeOfKindCalculationTest {

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
    class ThreeOfKindAbsent {
        @Test
        void checkWith7Cards_OneShortOfThreeOfKind() {
            boolean isThreeOfKind = handCalculator.checkThreeOfKind(Arrays.asList(
                    mockCardTwoOfDiamond,
                    mockCardFourOfDiamond,
                    mockCardAceOfHeart,
                    mockCardThreeOfDiamond,
                    mockCardKingOfHeart,
                    mockCardAceOfSpade,
                    mockCardKingOfSpade
            ));
            assertFalse(isThreeOfKind);
        }

        @Test
        void checkWith6Cards_OneShortOfThreeOfKind() {
            boolean isThreeOfKind = handCalculator.checkThreeOfKind(Arrays.asList(
                    mockCardTwoOfDiamond,
                    mockCardThreeOfDiamond,
                    mockCardFourOfDiamond,
                    mockCardAceOfClub,
                    mockCardAceOfHeart,
                    mockCardKingOfSpade
            ));
            assertFalse(isThreeOfKind);
        }

        @Test
        void checkWith5Cards_TwoPair() {
            boolean isThreeOfKind = handCalculator.checkThreeOfKind(Arrays.asList(
                    mockCardAceOfClub,
                    mockCardAceOfHeart,
                    mockCardKingOfHeart,
                    mockCardKingOfSpade,
                    mockCardTwoOfDiamond
            ));
            assertFalse(isThreeOfKind);
        }

        @Test
        void checkWith4Cards() {
            boolean isThreeOfKind = handCalculator.checkThreeOfKind(Arrays.asList(
                    mockCardThreeOfDiamond,
                    mockCardAceOfHeart,
                    mockCardTwoOfDiamond,
                    mockCardKingOfSpade
            ));
            assertFalse(isThreeOfKind);
        }

        @Test
        void checkWith3Cards() {
            boolean isThreeOfKind = handCalculator.checkThreeOfKind(Arrays.asList(
                    mockCardTwoOfDiamond,
                    mockCardAceOfHeart,
                    mockCardKingOfSpade
            ));
            assertFalse(isThreeOfKind);
        }

        // cases with not enough cards - automatic false
        @Test
        void checkWith2Cards() {
            boolean isThreeOfKind = handCalculator.checkThreeOfKind(Arrays.asList(
                    mockCardTwoOfDiamond,
                    mockCardKingOfSpade
            ));
            assertFalse(isThreeOfKind);
        }

        @Test
        void checkWith1Card() {
            boolean isThreeOfKind = handCalculator.checkThreeOfKind(Collections.singletonList(
                    mockCardKingOfSpade
            ));
            assertFalse(isThreeOfKind);
        }
    }

    @Nested
    class ThreeOfKindPresent {
        @Test
        void checkWith7Cards_ThreeOfKindAtTheBeginning() {
            boolean isThreeOfKind = handCalculator.checkThreeOfKind(Arrays.asList(
                    mockCardAceOfDiamond,
                    mockCardAceOfSpade,
                    mockCardAceOfClub,
                    mockCardKingOfHeart,
                    mockCardKingOfSpade,
                    mockCardTwoOfDiamond,
                    mockCardThreeOfDiamond
            ));
            assertTrue(isThreeOfKind);
        }

        @Test
        void checkWith7Cards_ThreeOfKindAtTheEnd() {
            boolean isThreeOfKind = handCalculator.checkThreeOfKind(Arrays.asList(
                    mockCardKingOfSpade,
                    mockCardThreeOfDiamond,
                    mockCardTwoOfDiamond,
                    mockCardKingOfHeart,
                    mockCardAceOfSpade,
                    mockCardAceOfClub,
                    mockCardAceOfHeart
            ));
            assertTrue(isThreeOfKind);
        }

        @Test
        void checkWith7Cards_ThreeOfKindSpread() {
            boolean isThreeOfKind = handCalculator.checkThreeOfKind(Arrays.asList(
                    mockCardAceOfDiamond,
                    mockCardKingOfSpade,
                    mockCardAceOfClub,
                    mockCardThreeOfDiamond,
                    mockCardTwoOfDiamond,
                    mockCardAceOfSpade,
                    mockCardKingOfHeart
            ));
            assertTrue(isThreeOfKind);
        }

        @Test
        void checkWith7Cards_ThreeOfKindInTheMiddle() {
            boolean isThreeOfKind = handCalculator.checkThreeOfKind(Arrays.asList(
                    mockCardThreeOfDiamond,
                    mockCardTwoOfDiamond,
                    mockCardAceOfDiamond,
                    mockCardAceOfSpade,
                    mockCardAceOfHeart,
                    mockCardKingOfHeart,
                    mockCardKingOfSpade
            ));
            assertTrue(isThreeOfKind);
        }

        @Test
        void checkWith6Cards_ThreeOfKindAtTheBeginning() {
            boolean isThreeOfKind = handCalculator.checkThreeOfKind(Arrays.asList(
                    mockCardAceOfDiamond,
                    mockCardAceOfSpade,
                    mockCardAceOfClub,
                    mockCardKingOfHeart,
                    mockCardKingOfSpade,
                    mockCardTwoOfDiamond
            ));
            assertTrue(isThreeOfKind);
        }

        @Test
        void checkWith5Cards_ThreeOfKind() {
            boolean isThreeOfKind = handCalculator.checkThreeOfKind(Arrays.asList(
                    mockCardAceOfDiamond,
                    mockCardAceOfSpade,
                    mockCardAceOfClub,
                    mockCardAceOfHeart,
                    mockCardKingOfSpade
            ));
            assertTrue(isThreeOfKind);
        }

        @Test
        void checkWith4Cards_ThreeOfKind() {
            boolean isThreeOfKind = handCalculator.checkThreeOfKind(Arrays.asList(
                    mockCardAceOfDiamond,
                    mockCardAceOfSpade,
                    mockCardAceOfClub,
                    mockCardKingOfSpade
            ));
            assertTrue(isThreeOfKind);
        }

        @Test
        void checkWith3Cards_ThreeOfKind() {
            boolean isThreeOfKind = handCalculator.checkThreeOfKind(Arrays.asList(
                    mockCardAceOfDiamond,
                    mockCardAceOfSpade,
                    mockCardAceOfClub
            ));
            assertTrue(isThreeOfKind);
        }
    }
}