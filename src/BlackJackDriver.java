import java.util.*;
/**
 * Blackjack class that holds the main method for playing the game.
 *
 * @author GoalDiggers
 * @version 1.0.0
 */
public class BlackJackDriver
{
    public static void main(String[] args)
    {
        //local variables
        Scanner in = new Scanner(System.in);
        boolean isBlackjack = false;
        boolean playerBust = false;
        boolean dealerBust = false;
        boolean doubleDown = false;
        boolean fiveCard = false;
        int betAmount = 0;
        char playAgain = 'y';
        System.out.println("Welcome to Blackjack!");

        //instantiating deck and players
        Deck deck = new Deck();
        //System.out.println(deck);
        Player player1 = new Player("Player");
        Player dealer = new Player("Dealer");

        //main game loop while user has funds and wants to play again
        while(player1.getFunds() > 0 && playAgain == 'y') {
            System.out.println("\nYou have $" + player1.getFunds() + ".");
            System.out.print("How much do you want to wager: ");
            betAmount = in.nextInt();

            //initialize the player and dealer hands
            player1.addCard(deck.getTopCard());
            dealer.addCard(deck.getTopCard());
            player1.addCard(deck.getTopCard());
            dealer.addCard(deck.getTopCard());

            System.out.println("\n" + player1.showHand());
            System.out.println("\tPlayer Hand Total Value: " + player1.cardValue());
            
            //hides one card from the dealer's hand
            System.out.println("\nDealer's Hand: \n\t" + dealer.getCard(0).toString() + "\n\t--HIDDEN CARD--");
            System.out.println("\tDealer Hand Total Value: --HIDDEN--" );

            //loop to control flow for player turn
            do {
                //checks for player blackjack
                if(player1.cardValue() == 21) {
                    isBlackjack = true;
                    System.out.println("\n" + player1.showHand());
                    System.out.println("\tBLACKJACK!\n");
                } else {
                    //allows for double down only on the first turn
                    if(!doubleDown) {
                        doubleDown = true;
                        System.out.print("\nWould you like to double down (y/n): ");
                        char choice = in.next().charAt(0);
                        if(choice == 'y' && betAmount <= (player1.getFunds()/2)) {
                            betAmount *= 2;
                            System.out.println("DOUBLE DOWN! Bet amount is doubled. Player HITS!");
                            player1.addCard(deck.getTopCard());
                            System.out.println("\n" + player1.showHand());
                            System.out.println("\tPlayer Hand Total Value: " + player1.cardValue());
                            if(player1.cardValue() > 21) {
                                playerBust = true;
                                System.out.println("Player BUST!---------------------\n");
                                break;
                            } else if (player1.cardValue() == 21) {
                                isBlackjack = true;
                                System.out.println("\tBLACKJACK!\n");
                            }

                            break;
                        } else if(choice == 'y' && betAmount > (player1.getFunds()/2)) {
                            System.out.println("You do not have enough money to double down!");
                            continue;
                        } else {
                            continue;
                        }
                    } else {
                        //allows player to hit until they stay
                        System.out.print("Would you like to hit (y/n): ");
                        char choice = in.next().charAt(0);
                        if(choice == 'y') {
                            player1.addCard(deck.getTopCard());
                            System.out.println("\n" + player1.showHand());
                            System.out.println("\tPlayer Hand Total: " + player1.cardValue());
                            //checks for bust, blackjack, 5 card
                            if(player1.cardValue() > 21) {
                                playerBust = true;
                                System.out.println("Player BUST!---------------------\n");
                                break;
                            } else if (player1.cardValue() == 21) {
                                isBlackjack = true;
                                System.out.println("\tBLACKJACK!\n");
                            } else if(player1.getHand().size() == 5) {
                                fiveCard = true;
                                System.out.println("Player has 5 cards.");
                                break;
                            }

                        } else {
                            break;
                        }
                    }
                } 
            } while (!isBlackjack); //Player turn loop

            //loop to control flow for dealer
            do {
                if(dealer.cardValue() == 21) {
                    isBlackjack = true;
                    System.out.println("\n" + dealer.showHand());
                    System.out.println("\tBLACKJACK!\n");
                } else if(dealer.cardValue() < 17) {
                    //dealer has to hit until they have atleast 17
                    System.out.println("\nDealer HITS!");
                    dealer.addCard(deck.getTopCard());
                    System.out.println("\n" + dealer.showHand());
                    System.out.println("\tDealer Hand Total: " + dealer.cardValue());
                    if(dealer.cardValue() > 21) {
                        dealerBust = true;
                        System.out.println("Dealer BUST!---------------------\n");
                        break;
                    } else if (dealer.cardValue() == 21) {
                        isBlackjack = true;
                        System.out.println("\tBLACKJACK!\n");
                    } 
                } else {
                    System.out.println("\n" + dealer.showHand());
                    System.out.println("\tDealer Hand Total: " + dealer.cardValue());
                    System.out.println("\tDealer STAYS!");
                    break;
                } 
            } while (!isBlackjack); // Dealer turn loop
            
            //checks for win conditions
            if(playerBust) {
                System.out.println("YOU LOSE! You lost $" + betAmount);
                player1.removeFunds(betAmount);
            } else if(dealerBust) {
                System.out.println("YOU WON! You won $" + betAmount);
                player1.addFunds(betAmount);
            } else if(fiveCard) {
                System.out.println("YOU WON! You won $" + betAmount);
                player1.addFunds(betAmount);
            }else {
                if(player1.cardValue() == dealer.cardValue()) {
                    System.out.println("PUSH! (DRAW!)");
                } else if (player1.cardValue() < dealer.cardValue()) {
                    System.out.println("YOU LOSE! You lost $" + betAmount);
                    player1.removeFunds(betAmount);
                } else {
                    System.out.println("YOU WON! You won $" + betAmount);
                    player1.addFunds(betAmount);
                } 
            } //Check winner

            //reset local variables
            player1.discardHand();
            dealer.discardHand();
            doubleDown = false;
            isBlackjack = false;
            playerBust = false;
            dealerBust = false;
            fiveCard = false;

            //checks if player can keep playing
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
