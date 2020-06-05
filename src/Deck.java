import java.util.*;
/**
 * This class holds properties to define a deck of cards.
 *
 * @author GoalDiggers
 * @version 1.0.0
 */

public class Deck
{
    private ArrayList<Card> deck;
    private int topCardPos;

    /**
     * Class constructor
     */
    public Deck()
    {
        this.deck = new ArrayList<>();
        populateDeck();
        shuffle();
    }

    /**
     * Generates cards using enums and fills the deck array with cards
     */
    public void populateDeck() {
        for(Suits suit : Suits.values()){
            for(Values value : Values.values()) {
                deck.add(new Card(suit, value));
            }
        }
    }

    /**
     * Shuffles the deck using random selection sort
     */
    public void shuffle()
    {
        Random r = new Random();
        Card temp;
        for(int k = 0; k < deck.size(); k++)
        {
            int index = r.nextInt(52);
            temp = deck.get(k);
            deck.set(k, deck.get(index));
            deck.set(index, temp);
        }

        topCardPos = 0;
    }

    
    /** 
     * Removes a card from the deck array
     * @param i index to specify which card is removed
     */
    public void removeCard(int i) {
        this.deck.remove(i);
    }

    
    /** 
     * Returns a card from the deck array
     * @param i index to specify which card is returned
     * @return Card
     */
    public Card getCard(int i) {
        return this.deck.get(i);
    }

    
    /** 
     * Returns the card at the top of the deck and shifts
     * index forward one position
     * @return Card
     */
    public Card getTopCard() {
        int index = topCardPos;
        topCardPos++;
        return this.getCard(index);
    }    

    
    /** 
     * Returns a textual representation of a deck
     * @return String
     */
    public String toString()
    {
        String output = "";
        int i = 1;
        for(Card ele : deck)
        {
            output += "\n" + i + ": " + ele;
            i++;
        }
        
        return output;
    }
}
