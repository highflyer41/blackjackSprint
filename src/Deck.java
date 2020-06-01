import java.util.*;
/**
 * Write a description of class Deck here.
 *
 * @author Meng Yang
 * @version 1.0.0
 */
public class Deck
{
    private Card[] cards;
    private final int SIZE = 52;
    private final String[] SUITS = {"Spades","Hearts","Clubs","Diamonds"};
    private final String[] FACES = {"Ace", "2", "3","4","5","6","7","8","9","10",
                                    "Jack","Queen","King"};
    private final int[] VALUES = {1,2,3,4,5,6,7,8,9,10, 10, 10, 10};

    public Deck()
    {
        cards = new Card[SIZE];
        int b= 0;
        for(int m = 0; m < 4; m++)
        {
            for(int k = 0; k < 13; k++)
            {
                cards[b] =  new Card(FACES[k],SUITS[m],VALUES[k]);
                b++;
            }
        }
        
        shuffle();
    }

    public void shuffle()
    {
        Random r = new Random();
        Card temp;
        for(int k = 0; k < cards.length; k++)
        {
            int index = r.nextInt(52);
            temp = cards[k];
            cards[k] = cards[index];
            cards[index] = temp;
        }
    }

    public Card getCard(int in)
    {
        return cards[in];
    }

    public String toString()
    {
        StringBuilder output = new StringBuilder();
        
        for(int k = 0; k < cards.length; k++)
        {
            output = output.append(cards[k]);
        }
        
        return output.toString();
    }
}
