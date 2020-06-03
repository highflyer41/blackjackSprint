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
        boolean doubleDown = false;
        int betAmount = 0;
        char playAgain = 'y';
        System.out.println("Welcome to Blackjack!");

        Deck deck = new Deck();
        //System.out.println(deck);
        Player player1 = new Player("Player");
        Player dealer = new Player("Dealer");

        while(player1.getFunds() > 0 && playAgain == 'y') {
            System.out.println("\nYou have $" + player1.getFunds() + ".");
            System.out.print("How much do you want to wager: ");
            betAmount = in.nextInt();

            player1.addCard(deck.getTopCard());
            dealer.addCard(deck.getTopCard());
            player1.addCard(deck.getTopCard());
            dealer.addCard(deck.getTopCard());

            System.out.println(player1.showHand());
            System.out.println("Player Hand Total Value: " + player1.cardValue());
            System.out.println("\nDealer's Hand: \n" + "1: " + dealer.getCard(0).toString() + "\n2: --HIDDEN CARD--");
            System.out.println("Dealer Hand Total Value: --HIDDEN--" );

            do {
                if(player1.cardValue() == 21) {
                    isBlackjack = true;
                    System.out.println(player1.showHand());
                    System.out.println("BLACKJACK!\n");
                } else if(!doubleDown) {
                    System.out.print("Would you like to double down (y/n): ");
                    char choice1 = in.next().charAt(0);
                    if(choice1 == 'y'){
                        doubleDown = true;
                        betAmount *= 2;
                        System.out.println("DOUBLE DOWN! Bet amount is doubled. Player HITS!");
                        player1.addCard(deck.getTopCard());
                        System.out.println(player1.showHand());
                        System.out.println("Player Hand Total: " + player1.cardValue());
                        if(player1.cardValue() > 21) {
                            playerBust = true;
                            System.out.println("Player BUST!---------------------\n");
                            break;
                        } else if (player1.cardValue() == 21) {
                            isBlackjack = true;
                            System.out.println("BLACKJACK!\n");
                        }
                    }
                } else if(doubleDown) {
                    System.out.print("\nWould you like to hit (y/n): ");
                    char choice = in.next().charAt(0);
                    if(choice == 'y') {
                        player1.addCard(deck.getTopCard());
                        System.out.println(player1.showHand());
                        System.out.println("Player Hand Total: " + player1.cardValue());
                        if(player1.cardValue() > 21) {
                            playerBust = true;
                            System.out.println("Player BUST!---------------------\n");
                            break;
                        } else if (player1.cardValue() == 21) {
                            isBlackjack = true;
                            System.out.println("BLACKJACK!\n");
                        }
                    } else {
                        break;
                    }
                } 
            } while (!isBlackjack);

            do {
                if(dealer.cardValue() == 21) {
                    isBlackjack = true;
                    System.out.println(dealer.showHand());
                    System.out.println("BLACKJACK!\n");
                } else if(dealer.cardValue() < 17) {
                    System.out.println("Dealer HITS!");
                    
                    dealer.addCard(deck.getTopCard());
                    System.out.println(dealer.showHand());
                    System.out.println("Dealer Hand Total: " + dealer.cardValue());
                    if(dealer.cardValue() > 21) {
                        dealerBust = true;
                        System.out.println("Dealer BUST!---------------------\n");
                        break;
                    } else if (dealer.cardValue() == 21) {
                        isBlackjack = true;
                        System.out.println("BLACKJACK!\n");
                    } 
                } else {
                    System.out.println(dealer.showHand());
                    System.out.println("Dealer STAYS!");
                    break;
                } 
            } while (!isBlackjack);
            
            if(playerBust) {
                System.out.println("YOU LOSE! You lost $" + betAmount);
                player1.removeFunds(betAmount);
            } else if(dealerBust) {
                System.out.println("YOU WON! You won $" + betAmount);
                player1.addFunds(betAmount);
            } else {
                if(player1.cardValue() == dealer.cardValue()) {
                    System.out.println("PUSH! (DRAW!)");
                } else if (player1.cardValue() < dealer.cardValue()) {
                    System.out.println("YOU LOSE! You lost $" + betAmount);
                    player1.removeFunds(betAmount);
                } else {
                    System.out.println("YOU WON! You won $" + betAmount);
                    player1.addFunds(betAmount);
                } 
            }

            player1.discardHand();
            dealer.discardHand();
            doubleDown = false;

            if(player1.getFunds() < 1) {
                System.out.println("YOU ARE OUT OF FUNDS!");
                break;
            } else {
                System.out.print("Round Over! Would you like to play again (y/n): ");
                playAgain = in.next().charAt(0); 
            }
            
            
        }//main game loop

        System.out.println("Game Over!");

    }//Main
}//Class
