import java.util.ArrayList;
/**
 * Player class holds properties that allows a player to play blackjack.
 * 
 * @author GoalDiggers
 * @version 1.0.0
 */

public class Player {
    
    private ArrayList<Card> hand = new ArrayList<>();
    private ArrayList<Card> hand2 = new ArrayList<>();
    private int funds;
    private String name;

    /**
     * Class constructor
     * @param name the name of the player
     */
    public Player(String name) {
        this.name = name;
        funds = 200;
    }

    
    /** 
     * Returns the hand array of Cards
     * @return ArrayList
     */
    public ArrayList<Card> getHand() {
        return this.hand;
    }

    public ArrayList<Card> getHandTwo() {
        return this.hand2;
    }

    /** 
     * Returns the funds of the player
     * @return int
     */
    public int getFunds() {
        return funds;
    }

    
    /** 
     * Adds amount to the player funds
     * @param num amount to be added to funds
     */
    public void addFunds(int num) {
        this.funds += num;
    }

    
    /** 
     * Removes amount from the player funds
     * @param num amount to be removed
     */
    public void removeFunds(int num) {
        this.funds -= num;
    }

    
    /** 
     * Adds a card to the player hand array
     * @param card card to be added to player's hand
     */
    public void addCard(Card card) {
        this.hand.add(card);
    }

    public void addCardToHandTwo(Card card) {
        this.hand2.add(card);
    }

    /**
     * Clears the player hands
     */
    public void discardHand() {
        this.hand.clear();
        this.hand2.clear();
    }

    
    /** 
     * Returns the total value of the cards in the player's hand
     * @return int
     */
    public int cardValue() {
        int total = 0;
        int totalAces = 0;

        //loops through the hand array and adds to the total
        for(Card card : hand) {
            switch(card.getValue()) {
                case ACE: totalAces += 1; break;
                case TWO: total += 2; break;
                case THREE: total += 3; break;
                case FOUR: total += 4; break;
                case FIVE: total += 5; break;
                case SIX: total += 6; break; 
                case SEVEN: total += 7; break;
                case EIGHT: total += 8; break;
                case NINE: total += 9; break;
                case TEN: 
                case JACK:
                case QUEEN:
                case KING: total += 10; break;
            }
        }

        //calculate the value of aces bases on hand total
        for(int i = 0; i < totalAces; i++) {
            if(total > 10) {
                total += 1;
            } else {
                total += 11;
            }
        }

        return total;
        
    }

    public int cardValueHandTwo() {
        int total = 0;
        int totalAces = 0;

        //loops through the hand array and adds to the total
        for(Card card : hand2) {
            switch(card.getValue()) {
                case ACE: totalAces += 1; break;
                case TWO: total += 2; break;
                case THREE: total += 3; break;
                case FOUR: total += 4; break;
                case FIVE: total += 5; break;
                case SIX: total += 6; break; 
                case SEVEN: total += 7; break;
                case EIGHT: total += 8; break;
                case NINE: total += 9; break;
                case TEN: 
                case JACK:
                case QUEEN:
                case KING: total += 10; break;
            }
        }

        //calculate the value of aces bases on hand total
        for(int i = 0; i < totalAces; i++) {
            if(total > 10) {
                total += 1;
            } else {
                total += 11;
            }
        }

        return total;
        
    }

    
    /** 
     * Returns a specific card from the hand array
     * @param i index to specify which card to return
     * @return Card
     */
    public Card getCard(int i) {
        return this.hand.get(i);
    }

    public Card getCardHandTwo(int i) {
        return this.hand.get(i);
    }

    
    /** 
     * Returns a textual representation of a hand of cards
     * @return String
     */
    public String showHand() {
        String output = "" + this.name + ":";
        //int i = 1;
        for(Card ele : hand)
        {
            output += "\n\t" + ele;
            //i++;
        }
        
        return output;
    }

    public String showHandTwo() {
        String output = "" + this.name + ":";
        //int i = 1;
        for(Card ele : hand2)
        {
            output += "\n\t" + ele;
            //i++;
        }
        
        return output;
    }
}