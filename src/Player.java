import java.util.ArrayList;
/**
 * Player class holds properties that allows a player to play blackjack.
 * 
 * @author GoalDiggers
 * @version 1.0.0
 */

public class Player {
    
    private ArrayList<Card> hand = new ArrayList<>();
    private int funds;
    private String name;

    //Constructor
    public Player(String name) {
        this.name = name;
        funds = 200;
    }

    public ArrayList<Card> getHand() {
        return this.hand;
    }

    public int getFunds() {
        return funds;
    }

    public void addFunds(int num) {
        this.funds += num;
    }

    public void removeFunds(int num) {
        this.funds -= num;
    }

    public void addCard(Card card) {
        this.hand.add(card);
    }

    public void discardHand() {
        this.hand.clear();
    }

    //method to calculate the hand value and return it
    public int cardValue() {
        int total = 0;
        int totalAces = 0;

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

    public Card getCard(int i) {
        return this.hand.get(i);
    }

    //display hand method
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
}