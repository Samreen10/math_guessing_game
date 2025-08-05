import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This level has a medium difficulty as it is the second level. There will be
 * feedback given back to the user no matter if they get the question right or 
 * wrong. They will also get unlimited mistakes to encourage learning and 
 * staying determined
 * 
 * Maahi Madhavan
 * 08/04/2025
 */
import java.util.Scanner;

public class Level2Game {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int rounds = 3;
        int score = 0;

        System.out.println("Good job! You've made it to Level 2!");
        System.out.println("You'll solve " + rounds + " algebra questions.");
        System.out.println("Remember to type your answer as an integer!\n");

        // Round 1
        boolean correct = false;
        while (!correct) {
            System.out.println("Question 1: What are the zero(s) of this equation? x^2 + 2x + 1");
            System.out.print("Your answer: ");
            if (scanner.hasNextInt()) {
                int userAnswer = scanner.nextInt();
                if (userAnswer == -1) {
                    System.out.println("Correct! Great job staying focused!");
                    score += 5;
                    correct = true;
                } else {
                    System.out.println("Nice try! Keep going, you're doing great!");
                    score -= 2;
                    if (score < 0) score = 0;
                }
            } else {
                System.out.println("Please enter a valid integer.");
                scanner.next(); // clear the invalid input
            }
        }

        // Round 2
        correct = false;
        while (!correct) {
            System.out.println("Question 2: Solve for x: x^2 - 9 = 0");
            System.out.print("Your answer: ");
            if (scanner.hasNextInt()) {
                int userAnswer = scanner.nextInt();
                if (userAnswer == 3 || userAnswer == -3) {
                    System.out.println("Correct! You're learning fast!");
                    score += 5;
                    correct = true;
                } else {
                    System.out.println("That's okay! Keep trying!");
                    score -= 2;
                    if (score < 0) score = 0;
                }
            } else {
                System.out.println("Please enter a valid integer.");
                scanner.next();
            }
        }

        // Round 3
        correct = false;
        while (!correct) {
            System.out.println("Question 3: Solve for x: x^2 - 4x + 3 = 0");
            System.out.print("Your answer: ");
            if (scanner.hasNextInt()) {
                int userAnswer = scanner.nextInt();
                if (userAnswer == 1 || userAnswer == 3) {
                    System.out.println("You're on fire! Great work!");
                    score += 5;
                    correct = true;
                } else {
                    System.out.println("No worries! You got this!");
                    score -= 2;
                    if (score < 0) score = 0;
                }
            } else {
                System.out.println("Please enter a valid integer.");
                scanner.next();
            }
        }

        // Final score output
        System.out.println("\nAll done! Awesome effort!");
        System.out.println("Your score: " + score + " out of " + (rounds * 5));
        scanner.close();
    }
}

import greenfoot.*;

/**
* QuestionManager handles all question logic for Level 3.
* It shows one quadratic equation at a time and accepts only
* the POSITIVE integer solution from the player.
*
* Features:
* - Keyboard input (digits and backspace)
* - Unlimited tries
* - Feedback messages and scoring
* - Final score display at the end
*
* Samreen Khosla
* 08/04/2025
*/

public class QuestionManager extends Actor {
private String input = "";
private int score = 0;
private int questionNumber = 0;

// Questions with exactly one correct positive root
private String[] questions = {
    "Solve: x^2 - 5x + 6 = 0 (Enter only the positive solution)",
    "Solve: x^2 - 7x + 12 = 0 (Enter only the positive solution)",
    "Solve: x^2 - 9x + 20 = 0 (Enter only the positive solution)"
};

private int[][] correctAnswers = {
    {3},  // Only accepting positive root
    {4},
    {5}
};

public QuestionManager() {
    // Show instructions and the first question right away
    updateDisplay("Level 3: Type only the positive answer and press Enter.\n\n"
        + questions[questionNumber] + "\nAnswer: ");
}

public void act() {
    checkKeyPress();
}

private void checkKeyPress() {
    String key = Greenfoot.getKey();
    if (key != null) {
        if (key.equals("enter")) {
            tryAnswer();
        } else if (key.equals("backspace") && input.length() > 0) {
            input = input.substring(0, input.length() - 1);
            updateDisplay(questions[questionNumber] + "\nAnswer: " + input);
        } else if (key.matches("\\d")) {  // Only allow digits (no negative sign)
            input += key;
            updateDisplay(questions[questionNumber] + "\nAnswer: " + input);
        }
    }
}

private void tryAnswer() {
    try {
        int guess = Integer.parseInt(input);
        int[] correct = correctAnswers[questionNumber];
        if (guess == correct[0]) {
            score += 5;
            questionNumber++;
            if (questionNumber >= questions.length) {
                updateDisplay("Great job! Final score: " + score);
                Greenfoot.stop();
            } else {
                input = "";
                updateDisplay("Correct! Next:\n" + questions[questionNumber] + "\nAnswer: ");
            }
        } else {
            score = Math.max(0, score - 2);
            input = "";
            updateDisplay("Oops! Try again.\n" + questions[questionNumber] + "\nAnswer: ");
        }
    } catch (NumberFormatException e) {
        input = "";
        updateDisplay("Invalid input. Please enter numbers only.");
    }
}

private void updateDisplay(String text) {
    GreenfootImage img = new GreenfootImage(text, 24, Color.BLACK, Color.WHITE);
    setImage(img);
}
}
