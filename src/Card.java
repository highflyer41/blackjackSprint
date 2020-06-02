
/**
 * Write a description of class Card here.
 *
 * @author Meng Yang
 * @version 1.0.0
 */
public class Card
{
    private Suits suit;
    private Values value;
    
    public Card(Suits suit, Values value)
    {
        this.suit = suit;
        this.value = value;
    }

    public Values getValue() {
        return value;
    }

    public String toString()
    {
        return this.value.toString() + " of " + this.suit.toString();
    }

}
