import java.sql.SQLOutput;
import java.util.Random;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        //Setting up variables for what to do when the game ends
        boolean game = true;
        String endgamechoice = "";
        
        //Setting up variables to track player's/computer's wins, draws, and # of rounds
        int rounds = 0;
        int playerwins = 0;
        int compwins = 0;
        int draws = 0;
        //Setting up variable to see who wins
        int win = 2;
        //Setting up scanner for taking player inputs
        Scanner scan = new Scanner(System.in);
        //Setting up random number generator
        Random ron = new Random();
        //Game continues to run until user chooses to end it
        while (game == true){
            String[] options = {"rock", "paper", "scissors"};
            //Setting up variable to hold player's choice
            String choice;
            //Asks the player for a choice until they enter a valid option
            do{
                System.out.println("Choose rock, paper, or scissors: ");
                choice = scan.next().toLowerCase();
            }
            while (!choice.equals("rock") && !choice.equals("paper") && !choice.equals("scissors"));

            //The computer chooses a number randomly that corresponds to a choice in RPS
            int compnum = ron.nextInt(3);
            String compchoice = "";
            compchoice = options[compnum];
            System.out.println("/////");
            
            //Prints the choice of the computer and the user
            System.out.println("You chose: " + choice);
            System.out.println("The computer chose: "+ compchoice);

            //Comparing the choices of the user and the computer to see who wins
            if (compchoice.equals(choice)){
                win = 2;
            }
            else if (compchoice.equals("rock") && choice.equals("paper")){
                win = 1;
            }
            else if (compchoice.equals("paper") && choice.equals("scissors")){
                win = 1;
            }
            else if (compchoice.equals("scissors") && choice.equals("rock")){
                win = 1;
            }
            else{
                win = 0;
            }
            //Printing the results and adding wins/draws/rounds
            switch (win){
                case 0:
                    System.out.println("You lose");
                    compwins ++;
                    break;
                case 1:
                    System.out.println("You win");
                    playerwins ++;
                    break;
                case 2:
                    System.out.println("It's a draw");
                    draws ++;
                    break;
            }
            rounds ++;
            System.out.println("/////");
            //Printing the stats
            System.out.println("Number of rounds: " + rounds);
            System.out.println("Your wins: "+playerwins);
            System.out.println("Computer wins: " + compwins);
            System.out.println("Draws: " + draws);
            System.out.println("/////");
            //asking the player to choose to continue the game or not
            //if the user enters something that's not yes or no they will be asked to do so
            do{
                System.out.println("Continue? yes/no: ");
                endgamechoice = scan.next().toLowerCase();
            }
            while (!endgamechoice.equals("yes") && !endgamechoice.equals("no"));
            //if no, game ends
            if (endgamechoice.equals("no")){
                game = false;
            }
            System.out.println("/////");
        }
    }
}