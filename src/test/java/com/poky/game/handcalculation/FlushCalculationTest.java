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
public class FlushCalculationTest {

    HandCalculator handCalculator;
    @Mock
    Card mockCardTwoOfDiamond;
    @Mock
    Card mockCardThreeOfDiamond;
    @Mock
    Card mockCardSevenOfClub;
    @Mock
    Card mockCardEightOfClub;
    @Mock
    Card mockCardNineOfClub;
    @Mock
    Card mockCardTenOfClub;
    @Mock
    Card mockCardJackOfClub;
    @Mock
    Card mockCardKingOfSpade;
    @Mock
    Card mockCardKingOfHeart;
    @Mock
    Card mockCardAceOfHeart;
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
        Mockito.lenient().when(mockCardSevenOfClub.getValueOrdinal()).thenReturn(5);
        Mockito.lenient().when(mockCardSevenOfClub.getColor()).thenReturn(Color.Club);
        Mockito.lenient().when(mockCardEightOfClub.getValueOrdinal()).thenReturn(6);
        Mockito.lenient().when(mockCardEightOfClub.getColor()).thenReturn(Color.Club);
        Mockito.lenient().when(mockCardNineOfClub.getValueOrdinal()).thenReturn(7);
        Mockito.lenient().when(mockCardNineOfClub.getColor()).thenReturn(Color.Club);
        Mockito.lenient().when(mockCardTenOfClub.getValueOrdinal()).thenReturn(8);
        Mockito.lenient().when(mockCardTenOfClub.getColor()).thenReturn(Color.Club);
        Mockito.lenient().when(mockCardJackOfClub.getValueOrdinal()).thenReturn(9);
        Mockito.lenient().when(mockCardJackOfClub.getColor()).thenReturn(Color.Club);
        Mockito.lenient().when(mockCardKingOfSpade.getValueOrdinal()).thenReturn(11);
        Mockito.lenient().when(mockCardKingOfSpade.getColor()).thenReturn(Color.Spade);
        Mockito.lenient().when(mockCardKingOfHeart.getValueOrdinal()).thenReturn(11);
        Mockito.lenient().when(mockCardKingOfHeart.getColor()).thenReturn(Color.Heart);
        Mockito.lenient().when(mockCardAceOfClub.getValueOrdinal()).thenReturn(12);
        Mockito.lenient().when(mockCardAceOfClub.getColor()).thenReturn(Color.Club);
        Mockito.lenient().when(mockCardAceOfHeart.getValueOrdinal()).thenReturn(12);
        Mockito.lenient().when(mockCardAceOfHeart.getColor()).thenReturn(Color.Heart);
    }

    @Nested
    class FlushAbsent {
        @Test
        void checkWith7Cards_OneShortOfFlush() {
            boolean isFlush = handCalculator.checkFlush(Arrays.asList(
                    mockCardTwoOfDiamond,
                    mockCardEightOfClub,
                    mockCardNineOfClub,
                    mockCardTenOfClub,
                    mockCardAceOfClub,
                    mockCardAceOfHeart,
                    mockCardKingOfSpade
            ));
            assertFalse(isFlush);
        }

        @Test
        void checkWith7Cards_TwoShortOfFlush() {
            boolean isFlush = handCalculator.checkFlush(Arrays.asList(
                    mockCardTwoOfDiamond,
                    mockCardEightOfClub,
                    mockCardNineOfClub,
                    mockCardThreeOfDiamond,
                    mockCardAceOfClub,
                    mockCardAceOfHeart,
                    mockCardKingOfSpade
            ));
            assertFalse(isFlush);
        }

        @Test
        void checkWith6Cards_OneShortOfFlush() {
            boolean isFlush = handCalculator.checkFlush(Arrays.asList(
                    mockCardEightOfClub,
                    mockCardNineOfClub,
                    mockCardTenOfClub,
                    mockCardAceOfClub,
                    mockCardAceOfHeart,
                    mockCardKingOfSpade
            ));
            assertFalse(isFlush);
        }

        @Test
        void checkWith5Cards_TwoPair() {
            boolean isFlush = handCalculator.checkFlush(Arrays.asList(
                    mockCardAceOfClub,
                    mockCardAceOfHeart,
                    mockCardKingOfHeart,
                    mockCardKingOfSpade,
                    mockCardTwoOfDiamond
            ));
            assertFalse(isFlush);
        }

        // cases with not enough cards - automatic false
        @Test
        void checkWith4Cards() {
            boolean isFlush = handCalculator.checkFlush(Arrays.asList(
                    mockCardNineOfClub,
                    mockCardAceOfHeart,
                    mockCardTwoOfDiamond,
                    mockCardKingOfSpade
            ));
            assertFalse(isFlush);
        }

        @Test
        void checkWith3Cards() {
            boolean isFlush = handCalculator.checkFlush(Arrays.asList(
                    mockCardNineOfClub,
                    mockCardAceOfHeart,
                    mockCardKingOfSpade
            ));
            assertFalse(isFlush);
        }

        @Test
        void checkWith2Cards() {
            boolean isFlush = handCalculator.checkFlush(Arrays.asList(
                    mockCardNineOfClub,
                    mockCardKingOfSpade
            ));
            assertFalse(isFlush);
        }

        @Test
        void checkWith1Card() {
            boolean isFlush = handCalculator.checkFlush(Collections.singletonList(
                    mockCardKingOfSpade
            ));
            assertFalse(isFlush);
        }
    }

    @Nested
    class FlushPresent {
        @Test
        void checkWith7Cards_FlushAtTheBeginning() {
            boolean isFlush = handCalculator.checkFlush(Arrays.asList(
                    mockCardSevenOfClub,
                    mockCardEightOfClub,
                    mockCardNineOfClub,
                    mockCardTenOfClub,
                    mockCardAceOfClub,
                    mockCardAceOfHeart,
                    mockCardKingOfSpade
            ));
            assertTrue(isFlush);
        }

        @Test
        void checkWith7Cards_FlushAtTheEnd() {
            boolean isFlush = handCalculator.checkFlush(Arrays.asList(
                    mockCardAceOfHeart,
                    mockCardKingOfSpade,
                    mockCardSevenOfClub,
                    mockCardEightOfClub,
                    mockCardNineOfClub,
                    mockCardAceOfClub,
                    mockCardJackOfClub
            ));
            assertTrue(isFlush);
        }

        @Test
        void checkWith7Cards_FlushSpread() {
            boolean isFlush = handCalculator.checkFlush(Arrays.asList(
                    mockCardEightOfClub,
                    mockCardAceOfHeart,
                    mockCardSevenOfClub,
                    mockCardAceOfClub,
                    mockCardJackOfClub,
                    mockCardKingOfSpade,
                    mockCardTenOfClub
            ));
            assertTrue(isFlush);
        }

        @Test
        void checkWith7Cards_FlushInTheMiddle() {
            boolean isFlush = handCalculator.checkFlush(Arrays.asList(
                    mockCardAceOfHeart,
                    mockCardSevenOfClub,
                    mockCardEightOfClub,
                    mockCardAceOfClub,
                    mockCardTenOfClub,
                    mockCardJackOfClub,
                    mockCardKingOfSpade
            ));
            assertTrue(isFlush);
        }

        @Test
        void checkWith6Cards_FlushAtTheBeginning() {
            boolean isFlush = handCalculator.checkFlush(Arrays.asList(
                    mockCardSevenOfClub,
                    mockCardAceOfClub,
                    mockCardNineOfClub,
                    mockCardTenOfClub,
                    mockCardJackOfClub,
                    mockCardKingOfSpade
            ));
            assertTrue(isFlush);
        }

        @Test
        void checkWith5Cards_Flush() {
            boolean isFlush = handCalculator.checkFlush(Arrays.asList(
                    mockCardSevenOfClub,
                    mockCardEightOfClub,
                    mockCardAceOfClub,
                    mockCardTenOfClub,
                    mockCardJackOfClub
            ));
            assertTrue(isFlush);
        }

        @Test
        void checkWith7Cards_Unsorted_FlushPresent_SpecialCase() {
            boolean isFlush = handCalculator.checkFlush(Arrays.asList(
                    mockCardEightOfClub,
                    mockCardSevenOfClub,
                    mockCardTenOfClub,
                    mockCardAceOfClub,
                    mockCardAceOfHeart,
                    mockCardJackOfClub,
                    mockCardKingOfSpade
            ));
            assertTrue(isFlush);
        }
    }
}