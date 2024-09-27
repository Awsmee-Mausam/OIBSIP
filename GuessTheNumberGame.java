import java.util.Random;
import javax.swing.JOptionPane;

public class GuessTheNumberGame {
    public static void main(String[] args) {
        
        // Generate a random number between 1 and 100
        Random random = new Random();
        int randomNumber = random.nextInt(100) + 1;
        
        // Initialize the number of attempts and score
        int attempts = 0;
        int score = 100;
        
        // Keep playing until the user guesses the number or runs out of attempts
        while (true) {
            // Prompt the user to enter a guess
            String guessString = JOptionPane.showInputDialog("Guess a number between 1 and 100:");
            int guess = Integer.parseInt(guessString);
            
            // Check if the guess is correct
            if (guess == randomNumber) {
                JOptionPane.showMessageDialog(null, "Congratulations, you guessed the number in " + attempts + " attempts!\nYour score is " + score);
                break;
            }
            // Check if the guess is too high
            else if (guess > randomNumber) {
                JOptionPane.showMessageDialog(null, "Too high, try again!");
                attempts++;
                score -= 10;
            }
            // Otherwise, the guess is too low
            else {
                JOptionPane.showMessageDialog(null, "Too low, try again!");
                attempts++;
                score -= 10;
            }
            
            // Check if the user has run out of attempts
            if (attempts >= 10) {
                JOptionPane.showMessageDialog(null, "You have run out of attempts!\nThe number was " + randomNumber + "\nBetter luck next time!\nYour score is " + score);
                break;
            }
        }
    }
}
