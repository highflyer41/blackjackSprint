
/**
 * Write a description of class Card here.
 *
 * @author Meng Yang
 * @version 1.0.0
 */
public class Card
{
    private String face;
    private String suit;
    private int value;
    
    public Card()
    {
        face = suit = null;
        value = 0;
    }
    
    public Card(String inFace, String inSuit, int inValue)
    {
        face = inFace;
        suit = inSuit;
        value = inValue;
    }
    
    public Card(Card other)
    {
        this.face = other.face;
        this.suit = other.suit;
        this.value = other.value;
    }
    
    public void setValue(int in)
    {
        value = in;
    }
    
    public String getSuit()
    {
        return suit;
    }
    
    public String getFace()
    {
        return face;
    }
    
    public int getValue()
    {
        return value;
    }
    
    public int compareTo(Card other)
    {
        return this.value - other.value;
    }
    
    public boolean equals(Card other)
    {
        return this.face.equals(other.face) && this.suit.equals(other.suit);
    }
    
    public String toString()
    {
        return "Suit: " + suit +
               "\nFace: " + face +
               "\nValue: " + value + "\n";
    }
}
