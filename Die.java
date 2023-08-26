import java.util.Random; // Importing the Random class.

public class Die { // Defining a public class called Die.

    private int value; // Declaring a private instance variable called value.

    public Die() { // Defining a constructor for the Die class.
        roll(); // Assigning a random value to the value variable.
    }

    public int roll() { // Defining a method that generates a random number between 1 and 6.
        Random rand = new Random();
        value = rand.nextInt(6) + 1;
        return value;
    }

    public int getValue() { // Defining a method that returns the current value of the die.
        return value;
    }
}
