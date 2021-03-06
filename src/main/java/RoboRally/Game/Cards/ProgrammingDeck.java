package RoboRally.Game.Cards;


import java.util.*;

public class ProgrammingDeck {
    public static final int MAX_HAND = 5;
    public static final int MAX_SELECT = 9;
    private final Stack<ProgramCard> programCardDeck;
    private final List<ProgramCard> thrownCards;

    public ProgrammingDeck(){
        this.programCardDeck = ProgramCard.getNewDeck();
        this.thrownCards = new LinkedList<>();
    }

    public int Size() { return programCardDeck.size(); }

    public ProgramCard drawCard() { return programCardDeck.pop(); }

    public void throwCards(Collection<ProgramCard> c) { thrownCards.addAll(c); }

    public void shuffle() { Collections.shuffle(getDeck()); }

    private Stack<ProgramCard> getDeck() { return this.programCardDeck; }
}