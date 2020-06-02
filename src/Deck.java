import java.util.*;
/**
 * Write a description of class Deck here.
 *
 * @author Meng Yang
 * @version 1.0.0
 */
public class Deck
{
    private ArrayList<Card> deck;
    private int topCardPos;

    public Deck()
    {
        this.deck = new ArrayList<>();
        populateDeck();
        shuffle();
    }

    public void populateDeck() {
        for(Suits suit : Suits.values()){
            for(Values value : Values.values()) {
                deck.add(new Card(suit, value));
            }
        }
    }

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

    public void removeCard(int i) {
        this.deck.remove(i);
    }

    public Card getCard(int i) {
        return this.deck.get(i);
    }

    public Card getTopCard() {
        int index = topCardPos;
        topCardPos++;
        return this.getCard(index);
    }    

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
