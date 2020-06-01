import java.util.*;
/**
 * Write a description of class BlackJackDriver here.
 *
 * @author Meng Yang
 * @version 1.0.0
 */
public class BlackJackDriver
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        Deck deck1 = new Deck();
        Card[] myHand = new Card[5];
        Card[] compHand = new Card[5];
        Random r = new Random();
        int index = 0;
        char choice = 'n';
        
        myHand[0] = deck1.getCard(index);
        myHand[1] = deck1.getCard(++index);

        compHand[0] = deck1.getCard(++index);
        compHand[1] = deck1.getCard(++index);
        
        System.out.println("My Hand: " + "\n" + myHand[0] + myHand[1]);
        System.out.println("CPU Hand: " + "\n" + compHand[0] + compHand[1]);
        
        System.out.println("Hit: (y/n)");
        choice = in.next().charAt(0);
        if(choice == 'y')
        {    myHand[2] = deck1.getCard(++index);
            System.out.println("My Hand: " + "\n" + myHand[0] + myHand[1] + myHand[2]);
        }
    }
}
