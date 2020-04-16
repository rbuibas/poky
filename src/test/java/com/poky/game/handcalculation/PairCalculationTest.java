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
public class PairCalculationTest {

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
    }

    @Nested
    class PairAbsent {
        @Test
        void checkWith7Cards_AllDifferent() {
            boolean isPair = handCalculator.checkPair(Arrays.asList(
                    mockCardTwoOfDiamond,
                    mockCardThreeOfDiamond,
                    mockCardFourOfDiamond,
                    mockCardFiveOfDiamond,
                    mockCardKingOfHeart,
                    mockCardAceOfSpade,
                    mockCardQueenOfClub
            ));
            assertFalse(isPair);
        }

        @Test
        void checkWith7Cards_TwoPair() {
            boolean isPair = handCalculator.checkPair(Arrays.asList(
                    mockCardKingOfHeart,
                    mockCardAceOfDiamond,
                    mockCardFourOfDiamond,
                    mockCardFiveOfDiamond,
                    mockCardQueenOfClub,
                    mockCardAceOfHeart,
                    mockCardKingOfSpade
            ));
            assertFalse(isPair);
        }

        @Test
        void checkWith7Cards_ThreeOfKind() {
            boolean isPair = handCalculator.checkPair(Arrays.asList(
                    mockCardTwoOfDiamond,
                    mockCardThreeOfDiamond,
                    mockCardAceOfDiamond,
                    mockCardQueenOfClub,
                    mockCardAceOfHeart,
                    mockCardKingOfSpade,
                    mockCardAceOfSpade
            ));
            assertFalse(isPair);
        }

        @Test
        void checkWith7Cards_FourOfKind() {
            boolean isPair = handCalculator.checkPair(Arrays.asList(
                    mockCardTwoOfDiamond,
                    mockCardAceOfClub,
                    mockCardAceOfDiamond,
                    mockCardQueenOfClub,
                    mockCardAceOfHeart,
                    mockCardKingOfSpade,
                    mockCardAceOfSpade
            ));
            assertFalse(isPair);
        }

        @Test
        void checkWith6Cards() {
            boolean isPair = handCalculator.checkPair(Arrays.asList(
                    mockCardTwoOfDiamond,
                    mockCardAceOfHeart,
                    mockCardQueenOfClub,
                    mockCardThreeOfDiamond,
                    mockCardFiveOfDiamond,
                    mockCardKingOfSpade
            ));
            assertFalse(isPair);
        }

        @Test
        void checkWith5Cards() {
            boolean isPair = handCalculator.checkPair(Arrays.asList(
                    mockCardTwoOfDiamond,
                    mockCardAceOfHeart,
                    mockCardThreeOfDiamond,
                    mockCardQueenOfClub,
                    mockCardKingOfSpade
            ));
            assertFalse(isPair);
        }

        @Test
        void checkWith4Cards() {
            boolean isPair = handCalculator.checkPair(Arrays.asList(
                    mockCardTwoOfDiamond,
                    mockCardAceOfHeart,
                    mockCardThreeOfDiamond,
                    mockCardKingOfSpade
            ));
            assertFalse(isPair);
        }

        @Test
        void checkWith3Cards() {
            boolean isPair = handCalculator.checkPair(Arrays.asList(
                    mockCardTwoOfDiamond,
                    mockCardAceOfHeart,
                    mockCardKingOfSpade
            ));
            assertFalse(isPair);
        }

        @Test
        void checkWith2Cards() {
            boolean isPair = handCalculator.checkPair(Arrays.asList(
                    mockCardTwoOfDiamond,
                    mockCardKingOfSpade
            ));
            assertFalse(isPair);
        }

        // cases with not enough cards - automatic false
        @Test
        void checkWith1Card() {
            boolean isPair = handCalculator.checkPair(Collections.singletonList(
                    mockCardKingOfSpade
            ));
            assertFalse(isPair);
        }
    }

    @Nested
    class PairPresent {
        @Test
        void checkWith7Cards_PairAtTheBeginning() {
            boolean isPair = handCalculator.checkPair(Arrays.asList(
                    mockCardAceOfDiamond,
                    mockCardAceOfSpade,
                    mockCardKingOfSpade,
                    mockCardFiveOfDiamond,
                    mockCardQueenOfClub,
                    mockCardTwoOfDiamond,
                    mockCardThreeOfDiamond
            ));
            assertTrue(isPair);
        }

        @Test
        void checkWith7Cards_PairAtTheEnd() {
            boolean isPair = handCalculator.checkPair(Arrays.asList(
                    mockCardThreeOfDiamond,
                    mockCardTwoOfDiamond,
                    mockCardQueenOfClub,
                    mockCardFiveOfDiamond,
                    mockCardKingOfHeart,
                    mockCardAceOfSpade,
                    mockCardAceOfHeart
            ));
            assertTrue(isPair);
        }

        @Test
        void checkWith7Cards_PairSpread() {
            boolean isPair = handCalculator.checkPair(Arrays.asList(
                    mockCardAceOfDiamond,
                    mockCardKingOfSpade,
                    mockCardQueenOfClub,
                    mockCardThreeOfDiamond,
                    mockCardTwoOfDiamond,
                    mockCardAceOfSpade,
                    mockCardFiveOfDiamond
            ));
            assertTrue(isPair);
        }

        @Test
        void checkWith6Cards_PairAtTheBeginning() {
            boolean isPair = handCalculator.checkPair(Arrays.asList(
                    mockCardAceOfDiamond,
                    mockCardAceOfSpade,
                    mockCardKingOfHeart,
                    mockCardFiveOfDiamond,
                    mockCardQueenOfClub,
                    mockCardTwoOfDiamond
            ));
            assertTrue(isPair);
        }

        @Test
        void checkWith5Cards_Pair() {
            boolean isPair = handCalculator.checkPair(Arrays.asList(
                    mockCardAceOfDiamond,
                    mockCardFiveOfDiamond,
                    mockCardKingOfSpade,
                    mockCardKingOfHeart,
                    mockCardQueenOfClub
            ));
            assertTrue(isPair);
        }

        @Test
        void checkWith4Cards_Pair() {
            boolean isPair = handCalculator.checkPair(Arrays.asList(
                    mockCardAceOfDiamond,
                    mockCardKingOfSpade,
                    mockCardThreeOfDiamond,
                    mockCardKingOfHeart
            ));
            assertTrue(isPair);
        }

        @Test
        void checkWith3Cards_Pair() {
            boolean isPair = handCalculator.checkPair(Arrays.asList(
                    mockCardKingOfSpade,
                    mockCardThreeOfDiamond,
                    mockCardKingOfHeart
            ));
            assertTrue(isPair);
        }

        @Test
        void checkWith2Cards_Pair() {
            boolean isPair = handCalculator.checkPair(Arrays.asList(
                    mockCardKingOfSpade,
                    mockCardKingOfHeart
            ));
            assertTrue(isPair);
        }
    }
}