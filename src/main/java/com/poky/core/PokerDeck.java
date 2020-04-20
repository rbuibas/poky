package com.poky.core;

import com.poky.core.PokerCardProperties.Color;
import com.poky.core.PokerCardProperties.Value;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.poky.util.DeckUtils.deckToPrettyString;

/**
 * The playing deck
 */

public class PokerDeck implements Deck<Card> {

    protected final Logger log = LogManager.getLogger(this.getClass());
    /**
     * Maximum number of cards in deck.
     */
    public static final int CARDS_IN_STARTING_DECK = 52;
    /**
     * Upper limit: head's up game with "run it 6 times". Do the math.
     */
    public static final int MAX_BURNT_CARDS = 18;
    /**
     * Upper limit: 10 player game with "run it 4 times". Do the math.
     */
    public static final int MAX_DEALT_CARDS = 40;
    /**
     * The collection holding the live cards.
     * All the cards are live cards at the moment of deck initialization.
     */
    private Deque<Card> liveCards; // cannot be final because of the shuffle
    private final List<Card> burntCards;
    /**
     * The collection holding all the dealt cards.
     * There is not differentiation between cards dealt to the player
     * and the cards dealt on the board.
     * TODO: consider making a difference
     */
    private final List<Card> dealtCards;
    /**
     * Flag locking the deck, i.e. blocking shuffling and resetting of the deck.
     * For example: if a game gas started shuffling should not be permitted.
     * TODO: consider who should have access to this
     */
    private boolean deckLocked;

    public PokerDeck() {
        /*
        TODO: check at what load factor the list will increase capacity
         */
        liveCards = new ArrayDeque<>(CARDS_IN_STARTING_DECK);
        burntCards = new ArrayList<>(MAX_BURNT_CARDS);
        dealtCards = new ArrayList<>(MAX_DEALT_CARDS);
        initialize();
    }

    /**
     * Sets up the deck by creating {@link #CARDS_IN_STARTING_DECK} cards,
     * assigning them values and colors.
     */
    @Override
    public void initialize() {
        log.info("Initializing Deck");
        Color[] colors = Color.values();
        Value[] values = Value.values();

        for (Color color : colors) {
            for (int j = 0; j < values.length; j++) {
                liveCards.add(new PokerCard(color, Value.getValue(j)));
            }
        }
    }

    /**
     * Reset the deck by making all the cards live again.
     * The order is not important at this point.
     */
    @Override
    public void reset() {
        log.info("Resetting Deck");
        if (isDeckLocked()) {
            log.warn("Attempting to reset a locked deck");
            return;
        }
        liveCards.addAll(burntCards);
        burntCards.clear();
        liveCards.addAll(dealtCards);
        dealtCards.clear();
    }

    /**
     * Shuffle only the {@link #liveCards}.
     * If the live cards are incomplete, i.e. size of {@link #liveCards} is less than {@link #CARDS_IN_STARTING_DECK},
     * it should be verified that the shuffle is being done because of a misdeal.
     */
    @Override
    public void shuffle() {
        log.info("Shuffling Deck");
        if (isDeckLocked()) {
            log.warn("Attempting to shuffle a locked deck");
            return;
        }
        if (liveCards.size() < 52) {
            log.warn("Shuffling after a deal (acceptable if misdeal happened)");
        }
        // TODO: implement Deque shuffle
        List<Card> tempList = new ArrayList<>(liveCards);
        Collections.shuffle(tempList, new Random());
        liveCards = new ArrayDeque<>(tempList);
    }


    /**
     * Draw next card from the deck.
     * The retrieval is <b>destructive</b>, i.e. the card will be removed from the live collection and added
     * to the dealt collection.
     * The Deck is shuffled in advanced, meaning the draw is not random.
     *
     * @return a Card from the deck.
     */
    @Override
    public Card dealCard() throws NoSuchElementException {
        log.info("Drawing card");
        Card dealtCard;
        try {
            dealtCard = liveCards.pop();
            dealtCards.add(dealtCard);
        } catch (NoSuchElementException e) {
            log.error("Attempted dealing from an empty deck");
            throw e;
        }
        return dealtCard;
    }

    public Card burnCard() {
        log.info("Burning card");
        Card burntCard = liveCards.pop();
        burntCards.add(burntCard);
        return burntCard;
    }

    @Override
    public String toString() {
        return deckToPrettyString(this);
    }

    /**
     * Get all the live existing cards in the deck,
     * i.e. the cards that haven't been dealt or burned.
     * The retrieval is <b>not destructive</b>.
     * This method does not give access to the list, it returns a clone.
     * The {@link #liveCards} will be returned as a List rather than a Deque.
     *
     * @return live cards
     */
    List<Card> getLiveCards() {
        return new ArrayList<>(liveCards);
    }

    /**
     * Get all dealt cards in the deck.
     * The retrieval is <b>not destructive</b>.
     * This method does not give access to the list, it returns a clone.
     *
     * @return all dealt cards
     */
    List<Card> getDealtCards() {
        return new ArrayList<>(dealtCards);
    }

    /**
     * Get all the burnt cards, i.e. cards not live and not dealt.
     * The retrieval is <b>not destructive</b>.
     * This method does not give access to the list, it returns a clone.
     *
     * @return all burnt cards
     */
    List<Card> getBurntCards() {
        return new ArrayList<>(burntCards);
    }

    /**
     * Get all the existing cards in the deck:
     * <ul>
     *     <li>Live cards</li>
     *     <li>Dealt cards</li>
     *     <li>Burned cards</li>
     * </ul>
     * The retrieval is <b>not destructive</b>.
     *
     * @return all the existing cards in the deck.
     */
    public List<Card> getAllCards() {
        log.info("Getting all cards from Deck");
        return Stream.of(liveCards, dealtCards, burntCards)
                .flatMap(Collection::parallelStream)
                .collect(Collectors.toList());
    }

    public boolean isDeckLocked() {
        return deckLocked;
    }

    public void setDeckLocked(boolean deckLock) {
        this.deckLocked = deckLock;
    }
}
