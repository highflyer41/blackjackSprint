/**
 * Card class defines properties of a playing card.
 *
 * @author GoalDiggers
 * @version 1.0.0
 */

public class Card
{
    private Suits suit;
    private Values value;
    
    /**
     * Class constructor
     * @param suit a value of enum class Suits
     * @param value a value of enum class Values
     */
    public Card(Suits suit, Values value)
    {
        this.suit = suit; 
        this.value = value; 
    }

    
    /** 
     * Returns the value of the Card
     * @return Values 
     */
    public Values getValue() {
        return value;
    }

    
    /** 
     * Returns a textual representation of a Card
     * @return String
     */
    public String toString()
    {
        return this.value.toString() + " of " + this.suit.toString();
    }

}
