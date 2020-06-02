import java.util.ArrayList;

public class Player {
    
    private ArrayList<Card> hand = new ArrayList<>();
    private double funds;
    private String name;

    public Player(String name) {
        this.name = name;
        funds = 200.00;
    }

    public void addCard(Card card) {
        this.hand.add(card);
    }

    public String showHand() {
        String output = "";
        int i = 1;
        for(Card ele : hand)
        {
            output += "\n" + i + ": " + ele;
            i++;
        }
        
        return output;
    }
}