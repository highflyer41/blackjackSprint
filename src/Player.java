import java.util.ArrayList;

public class Player {
    
    private ArrayList<Card> hand = new ArrayList<>();
    private int funds;
    private String name;

    public Player(String name) {
        this.name = name;
        funds = 200;
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

    public String showHand() {
        String output = "" + this.name + ":";
        int i = 1;
        for(Card ele : hand)
        {
            output += "\n" + i + ": " + ele;
            i++;
        }
        
        return output;
    }
}