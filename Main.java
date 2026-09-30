import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String secretWord = "computer";
        int attempts = 0;
        final int MAX_ATTEMPTS = 5;

        char[] letters = new char[secretWord.length()];
        String usedLetters = "";

        System.out.println("""
        ========================
                HANGMAN
        ========================

          Welcome to Hangman!

            Guess the word.
        """);

        // Initialize the hidden word
        for (int i = 0; i < secretWord.length(); i++) {
            letters[i] = '_';
        }

        while (attempts < MAX_ATTEMPTS) {

            // Show the current progress
            System.out.print("\nWord: ");

            for (int i = 0; i < letters.length; i++) {
                System.out.print(letters[i] + " ");
            }

            System.out.println();
            System.out.println("Attempts: " + attempts + "/" + MAX_ATTEMPTS);

            if (!usedLetters.isEmpty()) {
                System.out.println("Used letters: " + usedLetters);
            }

            System.out.print("Type a letter: ");

            String input = scanner.nextLine().trim().toLowerCase();

            // Validate input
            if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
                System.out.println("Please enter one valid letter.");
                continue;
            }

            char letter = input.charAt(0);

            // Check if the letter was already used
            if (usedLetters.indexOf(letter) != -1) {
                System.out.println("You already tried that letter.");
                continue;
            }

            usedLetters += letter + " ";

            boolean found = false;

            // Check if the letter exists in the secret word
            for (int j = 0; j < secretWord.length(); j++) {

                if (letter == secretWord.charAt(j)) {
                    letters[j] = letter;
                    found = true;
                }
            }

            if (!found) {
                attempts++;
                System.out.println("Wrong letter!");
            } else {
                System.out.println("Good guess!");
            }

            // Check if the player has won
            boolean won = true;

            for (int i = 0; i < letters.length; i++) {
                if (letters[i] == '_') {
                    won = false;
                    break;
                }
            }

            if (won) {
                System.out.println("\n========================");
                System.out.println("       YOU WON!");
                System.out.println("========================");
                System.out.println("The word was: " + secretWord);
                break;
            }
        }

        
        if (attempts == MAX_ATTEMPTS) {
            System.out.println("\n========================");
            System.out.println("       GAME OVER");
            System.out.println("========================");
            System.out.println("The word was: " + secretWord);
        }

        scanner.close();
    }
}