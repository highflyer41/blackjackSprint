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
        System.out.println("Welcome to Blackjack!");

        Deck deck = new Deck();
        Player player1 = new Player("Joe Exotic");
        Player dealer = new Player("Dealer");

        player1.addCard(deck.getTopCard());
        dealer.addCard(deck.getTopCard());
        player1.addCard(deck.getTopCard());
        dealer.addCard(deck.getTopCard());



        System.out.println(player1.showHand());
        System.out.println(dealer.showHand());
    }
}
