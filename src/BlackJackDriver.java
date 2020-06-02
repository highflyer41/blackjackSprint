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
        boolean isBlackjack = false;
        boolean playerBust = false;
        boolean dealerBust = false;
        int betAmount = 0;
        System.out.println("Welcome to Blackjack!");

        Deck deck = new Deck();
        //System.out.println(deck);
        Player player1 = new Player("Meng");
        Player dealer = new Player("Dealer");

        while(player1.getFunds() > 0) {
            System.out.println("You have $" + player1.getFunds() + ".");
            System.out.print("How much do you want to wager: ");
            betAmount = in.nextInt();

            player1.addCard(deck.getTopCard());
            dealer.addCard(deck.getTopCard());
            player1.addCard(deck.getTopCard());
            dealer.addCard(deck.getTopCard());

            System.out.println(player1.showHand());
            System.out.println("Player Hand Total: " + player1.cardValue());
            System.out.println(dealer.showHand());
            System.out.println("Dealer Hand Total: " + dealer.cardValue());

            do {
                if(player1.cardValue() == 21) {
                    isBlackjack = true;
                    System.out.println("BLACKJACK!");
                } else {
                    System.out.println("Would you like to hit (y/n):");
                    char choice = in.next().charAt(0);
                    if(choice == 'y') {
                        player1.addCard(deck.getTopCard());
                        System.out.println(player1.showHand());
                        System.out.println("Player Hand Total: " + player1.cardValue());
                        if(player1.cardValue() > 21) {
                            playerBust = true;
                            System.out.println("Player BUST!---------------------");
                            break;
                        } else if (player1.cardValue() == 21) {
                            isBlackjack = true;
                            System.out.println("BLACKJACK!");
                        }
                    } else {
                        break;
                    }
                } 
            } while (!isBlackjack);

            do {
                if(dealer.cardValue() == 21) {
                    isBlackjack = true;
                    System.out.println("BLACKJACK!");
                } else if(dealer.cardValue() < 17) {
                    System.out.println("Dealer HITS!");
                    
                    dealer.addCard(deck.getTopCard());
                    System.out.println(dealer.showHand());
                    System.out.println("Dealer Hand Total: " + dealer.cardValue());
                    if(dealer.cardValue() > 21) {
                        dealerBust = true;
                        System.out.println("Dealer BUST!---------------------");
                        break;
                    } else if (dealer.cardValue() == 21) {
                        isBlackjack = true;
                        System.out.println("BLACKJACK!");
                    } 
                } else {
                    System.out.println("Dealer STAYS!");
                    break;
                } 
            } while (!isBlackjack);
            
            if(playerBust) {
                System.out.println("YOU LOSE!");
                player1.removeFunds(betAmount);
            } else if(dealerBust) {
                System.out.println("YOU WIN!");
                player1.addFunds(betAmount);
            } else {
                if(player1.cardValue() == dealer.cardValue()) {
                    System.out.println("PUSH! (DRAW!)");
                } else if (player1.cardValue() < dealer.cardValue()) {
                    System.out.println("YOU LOSE!");
                    player1.removeFunds(betAmount);
                } else {
                    System.out.println("YOU WON!");
                    player1.addFunds(betAmount);
                } 
            }

            player1.discardHand();
            dealer.discardHand();

        }//main game loop

        System.out.println("You are out of money. Game Over.");

    }//Main
}//Class
