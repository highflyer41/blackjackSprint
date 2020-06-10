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
        //suppress warning about not closing scanner.
        //System.in is handled by the JVM. Dont close it, the JVM will close it.
        @SuppressWarnings("resource")
        Scanner in = new Scanner(System.in);

        //local variables
        boolean isBlackjack = false;
        boolean isBlackjackHandTwo = false;
        boolean dealerBlackjack = false;
        boolean playerBust = false;
        boolean playerHandTwoBust = false;
        boolean dealerBust = false;
        boolean fiveCard = false;
        boolean fiveCardHandTwo = false;
        boolean isSplit = false;
        int betAmount = 0;
        int betAmountHandTwo = 0;
        char playAgain = 'y';
        System.out.println("Welcome to Blackjack!");

        //instantiating deck and players
        Deck deck = new Deck();
        Player player1 = new Player("Player");
        Player dealer = new Player("Dealer");

        //main game loop while user has funds and wants to play again
        while(player1.getFunds() > 0 && playAgain == 'y') {
            //checks that player can cover bet
            do{
                System.out.println("\nYou have $" + player1.getFunds() + ".");
                System.out.print("How much do you want to wager: ");
                betAmount = in.nextInt();

                if(betAmount > player1.getFunds())
                    System.out.println("You don't have enough money to make that bet. Try again!");
            } while (betAmount > player1.getFunds());

            //initialize the player and dealer hands
            player1.addCard(deck.getTopCard());
            dealer.addCard(deck.getTopCard());
            player1.addCard(deck.getTopCard());
            dealer.addCard(deck.getTopCard());

            //show player's hand
            System.out.println("\n" + player1.showHand());
            System.out.println("\tPlayer Hand Total Value: " + player1.cardValue());
            
            //hides one card from the dealer's hand
            System.out.println("\nDealer's Hand: \n\t" + dealer.getCard(0).toString() + "\n\t--HIDDEN CARD--");
            System.out.println("\tDealer Hand Total Value: --HIDDEN--" );

            //Start the player turn
            //checks for player blackjack
            if(player1.cardValue() == 21) {
                isBlackjack = true;
                System.out.println("\n" + player1.showHand());
                System.out.println("\tBLACKJACK!\n");
            } 
            
            //if player has same value cards, they can choose to split their hand
            if(player1.getCard(0).getValue() == player1.getCard(1).getValue()) {
                System.out.print("\nWould you like to split your hand (y/n): ");
                char choiceSplit = in.next().charAt(0);

                if(choiceSplit == 'y') {
                    isSplit = true;
                    System.out.println("Player splits hand! Each hand is worth half of bet amount!");
                    //the bet amount is split in half and each hand is worth half the original bet amount
                    betAmountHandTwo = betAmount / 2;
                    betAmount = betAmount - betAmountHandTwo;

                    //removes the second card from the original hand and it to the second hand
                    player1.addCardToHandTwo(player1.getHand().remove(1));
                    System.out.println("\n" + player1.showHand());
                    System.out.println("\tPlayer Hand Total Value: " + player1.cardValue());
                    System.out.println("\n" + player1.showHandTwo());
                    System.out.println("\tPlayer Hand Total Value: " + player1.cardValueHandTwo());
                    System.out.println("\nPlaying the first hand now!");

                    //loop for playing the first hand
                    while(!playerBust && !isBlackjack && !fiveCard) {
                        System.out.print("\nWould you like to hit (y/n): ");
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
                                break;
                            } else if(player1.getHand().size() == 5) {
                                fiveCard = true;
                                System.out.println("Player has 5 cards.");
                                break;
                            }

                        } else {
                            break;
                        }
                    }

                    System.out.println("\nPlaying the second hand now!");

                    //loop for playing the second hand
                    while(!playerHandTwoBust && !isBlackjackHandTwo && !fiveCardHandTwo) {
                        System.out.print("\nWould you like to hit (y/n): ");
                        char choice = in.next().charAt(0);

                        if(choice == 'y') {
                            player1.addCardToHandTwo(deck.getTopCard());
                            System.out.println("\n" + player1.showHandTwo());
                            System.out.println("\tPlayer Hand Total: " + player1.cardValueHandTwo());
                                
                            //checks for bust, blackjack, 5 card
                            if(player1.cardValueHandTwo() > 21) {
                                playerHandTwoBust = true;
                                System.out.println("Player BUST!---------------------\n");
                                break;
                            } else if (player1.cardValueHandTwo() == 21) {
                                isBlackjackHandTwo = true;
                                System.out.println("\tBLACKJACK!\n");
                                break;
                            } else if(player1.getHandTwo().size() == 5) {
                                fiveCardHandTwo = true;
                                System.out.println("Player has 5 cards.");
                                break;
                            }

                        } else {
                            break;
                        }
                    }
                }
            
            }
            
            if(!isSplit) {
                //allows for double down only on the first turn
                //only lets player double down if hand value is 9, 10, or 11
                if(player1.cardValue() >= 9 && player1.cardValue() <= 11) {
                    System.out.print("\nWould you like to double down (y/n): ");
                    char choiceDD = in.next().charAt(0);

                    if(choiceDD == 'y' && betAmount <= (player1.getFunds()/2)) {
                        betAmount *= 2; //amount is doubled
                        System.out.println("DOUBLE DOWN! Bet amount is doubled. Player HITS!");
                        player1.addCard(deck.getTopCard());
                        System.out.println("\n" + player1.showHand());
                        System.out.println("\tPlayer Hand Total Value: " + player1.cardValue());

                        //checks for bust, blackjack
                        if(player1.cardValue() > 21) {
                            playerBust = true;
                            System.out.println("Player BUST!---------------------\n");
                        } else if (player1.cardValue() == 21) {
                            isBlackjack = true;
                            System.out.println("\tBLACKJACK!\n");
                        }

                    } else if(choiceDD == 'y' && betAmount > (player1.getFunds()/2)) {
                        System.out.println("You do not have enough money to double down!");
                        //allows player to hit until they stay
                        while(!playerBust && !isBlackjack && !fiveCard) {
                            System.out.print("\nWould you like to hit (y/n): ");
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
                                    break;
                                } else if(player1.getHand().size() == 5) {
                                    fiveCard = true;
                                    System.out.println("Player has 5 cards.");
                                    break;
                                }

                            } else {
                                break;
                            }
                        }
                    } else {
                        //player does not double down
                        //allows player to hit until they stay
                        while(!playerBust && !isBlackjack && !fiveCard) {
                            System.out.print("\nWould you like to hit (y/n): ");
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
                                    break;
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

                } else {
                    //allows player to hit until they stay
                    while(!playerBust && !isBlackjack && !fiveCard) {
                        System.out.print("\nWould you like to hit (y/n): ");
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
                                break;
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
            } 
            

            //loop to control flow for dealer
            System.out.println("\n---------------Dealer Turn----------------");
            do {
                if(dealer.cardValue() == 21) {
                    dealerBlackjack = true;
                    System.out.println("\n" + dealer.showHand());
                    System.out.println("\tBLACKJACK!\n");
                } else if(dealer.cardValue() < 17) {
                    //dealer has to hit until they have atleast 17
                    System.out.println("\nDealer HITS!");
                    dealer.addCard(deck.getTopCard());
                    System.out.println("\n" + dealer.showHand());
                    System.out.println("\tDealer Hand Total: " + dealer.cardValue());

                    //checks for bust, blackjack
                    if(dealer.cardValue() > 21) {
                        dealerBust = true;
                        System.out.println("Dealer BUST!---------------------\n");
                        break;
                    } else if (dealer.cardValue() == 21) {
                        dealerBlackjack = true;
                        System.out.println("\tBLACKJACK!\n");
                    }

                } else {
                    //dealer has to stay at 17 and over
                    System.out.println("\n" + dealer.showHand());
                    System.out.println("\tDealer Hand Total: " + dealer.cardValue());
                    System.out.println("\tDealer STAYS!");
                    break;
                } 
            } while (!dealerBlackjack); // Dealer turn loop
            
            //checks for win conditions and add/remove bet amound from player funds
            System.out.println("\nHand Results:");
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
                    System.out.println("PUSH! (DRAW!)"); //tie game, no exchange of funds
                } else if (player1.cardValue() < dealer.cardValue()) {
                    System.out.println("YOU LOSE! You lost $" + betAmount);
                    player1.removeFunds(betAmount);
                } else {
                    System.out.println("YOU WON! You won $" + betAmount);
                    player1.addFunds(betAmount);
                } 
            } //Check winner

            //checks win condition only when a split hand is played
            if(isSplit) {
                System.out.println("\nSecond Hand Results:");
                if(playerHandTwoBust) {
                    System.out.println("YOU LOSE! You lost $" + betAmountHandTwo);
                    player1.removeFunds(betAmountHandTwo);
                } else if(dealerBust) {
                    System.out.println("YOU WON! You won $" + betAmountHandTwo);
                    player1.addFunds(betAmountHandTwo);
                } else if(fiveCardHandTwo) {
                    System.out.println("YOU WON! You won $" + betAmountHandTwo);
                    player1.addFunds(betAmountHandTwo);
                }else {
                    if(player1.cardValueHandTwo() == dealer.cardValue()) {
                        System.out.println("PUSH! (DRAW!)"); //tie game, no exchange of funds
                    } else if (player1.cardValueHandTwo() < dealer.cardValue()) {
                        System.out.println("YOU LOSE! You lost $" + betAmountHandTwo);
                        player1.removeFunds(betAmountHandTwo);
                    } else {
                        System.out.println("YOU WON! You won $" + betAmountHandTwo);
                        player1.addFunds(betAmountHandTwo);
                    } 
                }
            }
            

            //reset local variables and clears hand
            player1.discardHand();
            dealer.discardHand();
            isBlackjack = false;
            isBlackjackHandTwo = false;
            dealerBlackjack = false;
            playerBust = false;
            playerHandTwoBust = false;
            dealerBust = false;
            fiveCard = false;
            fiveCardHandTwo = false;
            isSplit = false;

            //shuffles the deck again
            deck.shuffle();

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
