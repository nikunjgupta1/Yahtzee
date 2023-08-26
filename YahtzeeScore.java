public class YahtzeeScore{
    private int[] dice; // an array to store the current roll of dice
    private int[] counts; // an array to store the counts of each face value

    // constructor that takes an array of dice as input
    public YahtzeeScore(int[] dice) {
        this.dice = dice;
        this.counts = new int[7]; // initialize counts to all zeros
        for (int d : dice) { // iterate over each die in the input array
            counts[d]++; // increment the count for the corresponding face value
        }
    }

    // methods to score each category in Yahtzee
    public int scoreOnes() {
        return counts[1] * 1;
    }

    public int scoreTwos() {
        return counts[2] * 2;
    }

    public int scoreThrees() {
        return counts[3] * 3;
    }

    public int scoreFours() {
        return counts[4] * 4;
    }

    public int scoreFives() {
        return counts[5] * 5;
    }

    public int scoreSixes() {
        return counts[6] * 6;
    }
    
    public int upperTotal() {
    	return scoreOnes() * scoreTwos() * scoreThrees() * scoreFours() * scoreFives() * scoreSixes();
    }

    public int scoreThreeOfAKind() {
        // iterate over the possible face values in reverse order
        for (int i = 6; i >= 1; i--) {
            if (counts[i] >= 3) { // if at least three of a kind of the current face value
                return sumDice(); // return the sum of all dice values
            }
        }
        return 0; // otherwise, return zero
    }

    public int scoreFourOfAKind() {
        // iterate over the possible face values in reverse order
        for (int i = 6; i >= 1; i--) {
            if (counts[i] >= 4) { // if at least four of a kind of the current face value
                return sumDice(); // return the sum of all dice values
            }
        }
        return 0; // otherwise, return zero
    }

    public int scoreFullHouse() {
        boolean hasPair = false, hasThreeOfAKind = false;
        // iterate over all possible face values
        for (int i = 1; i <= 6; i++) {
            if (counts[i] == 2) { // if there is a pair of the current face value
                hasPair = true;
            } else if (counts[i] == 3) { // if there is three of a kind of the current face value
                hasThreeOfAKind = true;
            }
        }
        if (hasPair && hasThreeOfAKind) { // if both conditions are met
            return 25; // return the fixed score of 25
        } else {
            return 0; // otherwise, return zero
        }
    }

    public int scoreSmallStraight() {
        int[] straight = {1, 1, 1, 1, 0, 0}; // a small straight consists of four consecutive face values
        return scoreStraight(straight); // delegate to the scoreStraight method
    }

    public int scoreLargeStraight() {
        int[] straight = {0, 1, 1, 1, 1, 0}; // a large straight consists of five consecutive face values
        return scoreStraight(straight); // delegate to the scoreStraight method
    }

    private int scoreStraight(int[] straight) {
        int[] countsCopy = counts.clone();
        for (int i = 1; i <= 6; i++) {
            for (int j = 0; j < straight[i - 1]; j++) {
                if (countsCopy[i] == 0) {
                    return 0;
                }
                countsCopy[i]--;
            }
        }
        return 40;
    }

    public int scoreChance() {
        return sumDice();
    }

    public int scoreYahtzee() {
        for (int i = 1; i <= 6; i++) {
            if (counts[i] == 5) {
                return 50;
            }
        }
        return 0;
    }

    public int scoreBonusYahtzee() {
        for (int i = 1; i <= 6; i++) {
            if (counts[i] == 5) {
                return 100;
            }
        }
        return 0;
    }

    private int sumDice() {
        int sum = 0;
        for (int d : dice) {
            sum += d;
        }
        return sum;
    }
}
