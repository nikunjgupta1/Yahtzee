
import java.util.Scanner;

public class YahtzeeHand

{// an array of 5 dice objects
	private Die[] dice = new Die[5];

	// constructor that creates the 5 dice objects
	public YahtzeeHand() {
	    for (int i = 0; i < 5; i++) {
	        dice[i] = new Die();
	    }
	}

	// returns an array of integers with the current values of the dice
	public int[] getDice() {
	    int[] values = new int[5];
	    for (int i = 0; i < 5; i++) {
	        // get the value of the ith die and store it in the array of values
	        values[i] = dice[i].getValue();
	    }
	    return values;
	}

	// rolls all 5 dice
	public void rollAll() {
	    for (int i = 0; i < 5; i++) {
	        // roll the ith die
	        dice[i].roll();
	    }
	}

	// rolls a specific die by its number
	public void roll(int number) {
	    // roll the die with the specified number, which is 1-based
	    dice[number - 1].roll();
	}

	// returns the value of a specific die by its number
	public int get(int number) {
	    // get the value of the die with the specified number, which is 1-based
	    return dice[number - 1].getValue();
	}

	// lets the user choose which dice to keep and rolls the rest
	public void changeHand() {
	    Scanner scanner = new Scanner(System.in);
	    System.out.println("Enter die number(s) to keep (separated by a space), Enter one space if you want to reroll all numbers:");
	    String input = scanner.nextLine();
	    String[] tokens = input.split(" ");
	    boolean[] keep = new boolean[5];
	    for (int j = 0; j < tokens.length; j++) {
	        // set the keep for the die with the specified number, which is 1-based
	        int dieNumber = Integer.parseInt(tokens[j]);
	        keep[dieNumber - 1] = true;
	    }
	    for (int i = 0; i < 5; i++) {
	        // roll the ith die if it's not being kept
	        if (!keep[i]) {
	            dice[i].roll();
	        }
	    }
	}

	// returns a string representation of the 5 dice
	public String showDice() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("| Dice |");
	    for (int i = 1; i <= 5; i++) {
	        // append the column header for the ith die
	        sb.append(" " + i + " |");
	    }
	    sb.append("\n+------+");
	    for (int i = 1; i <= 5; i++) {
	        // append the separator for the ith column
	        sb.append("---+");
	    }
	    sb.append("\n| Face |");
	    for (int i = 0; i < 5; i++) {
	        // append the value of the ith die
	        sb.append(" " + dice[i].getValue() + " |");
	    }
	    sb.append("\n");
	    return sb.toString();
	}

}
