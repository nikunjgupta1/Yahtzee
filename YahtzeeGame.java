import java.util.Scanner;

public class YahtzeeGame {

	private int gameCount = 0;
	private int totalScore = 0;
	private boolean gameOver = false;
	private YahtzeeHand hand = new YahtzeeHand();
	private ScoreCard scoreCard = new ScoreCard();

	public void play() {
	    while (!gameOver) {
	        // play 13 hands
	        for (int i = 0; i < 13; i++) {
	            System.out.println("Hand " + (i + 1)); // create a new YahtzeeHand object
	            YahtzeeHand hand = new YahtzeeHand();

	            // print the dice in the initial hand
	            System.out.println(hand.showDice());

	            // change the hand
	            hand.changeHand();
	            // print the dice in the changed hand
	            System.out.println(hand.showDice());

	            // change the hand again
	            hand.changeHand();
	            // print the dice in the changed hand
	            System.out.println(hand.showDice());

	            // get the current state of the dice in the hand
	            int[] dice = hand.getDice();

	            // create a new YahtzeeScore object using the dice values
	            YahtzeeScore score = new YahtzeeScore(dice);
	            
				// print the score for each category
				System.out.println("Score:");
				System.out.printf("             1s: %d\n", score.scoreOnes());
				System.out.printf("             2s: %d\n", score.scoreTwos());
				System.out.printf("             3s: %d\n", score.scoreThrees());
				System.out.printf("             4s: %d\n", score.scoreFours());
				System.out.printf("             5s: %d\n", score.scoreFives());
				System.out.printf("             6s: %d\n", score.scoreSixes());
				System.out.printf("Three Of A Kind: %d\n", score.scoreThreeOfAKind());
				System.out.printf(" Four Of A Kind: %d\n", score.scoreFourOfAKind());
				System.out.printf("     Full House: %d\n", score.scoreFullHouse());
				System.out.printf(" Small Straight: %d\n", score.scoreSmallStraight());
				System.out.printf(" Large Straight: %d\n", score.scoreLargeStraight());
				System.out.printf("         Chance: %d\n", score.scoreChance());
				System.out.printf("        Yahtzee: %d\n", score.scoreYahtzee());
				System.out.printf("  Bonus Yahtzee: %d\n", score.scoreBonusYahtzee());
				// let the user choose a category
				int category = chooseCategory();

	            // print the score for the selected category and update the total score
	            int roundScore = 0;
	            switch (category) {
	                case 1:
	                    roundScore = score.scoreOnes();
	                    break;
	                case 2:
	                    roundScore = score.scoreTwos();
	                    break;
	                case 3:
	                    roundScore = score.scoreThrees();
	                    break;
	                case 4:
	                    roundScore = score.scoreFours();
	                    break;
	                case 5:
	                    roundScore = score.scoreFives();
	                    break;
	                case 6:
	                    roundScore = score.scoreSixes();
	                    break;
	                case 7:
	                    roundScore = score.scoreThreeOfAKind();
	                    break;
	                case 8:
	                    roundScore = score.scoreFourOfAKind();
	                    break;
	                case 9:
	                    roundScore = score.scoreFullHouse();
	                    break;
	                case 10:
	                    roundScore = score.scoreSmallStraight();
	                    break;
	                case 11:
	                    roundScore = score.scoreLargeStraight();
	                    break;
	                case 12:
	                    roundScore = score.scoreYahtzee();
	                    break;
	                case 13:
	                    roundScore = score.scoreChance();
	                    break;
	            }

	            totalScore += roundScore;
	            System.out.printf("Score: %d\n", totalScore);
	            // update the score card
	            scoreCard.setScore(category-1, gameCount, roundScore);
	        }
	        // print final score
	        System.out.println("Game over! Final score: " + totalScore);
	        // check if the user wants to play again
	        if (playAgain()) {
	            gameCount++;
	            totalScore = 0;
	            scoreCard = new ScoreCard();
	            hand = new YahtzeeHand();
	        } else {
	            gameOver = true;
	        }
	    }
	}


	private int chooseCategory() {
		Scanner scanner = new Scanner(System.in);
		int category = -1;
		while (category == -1) {
			System.out.println("Choose a category to score:");
			System.out.println("1. Ones");
			System.out.println("2. Twos");
			System.out.println("3. Threes");
			System.out.println("4. Fours");
			System.out.println("5. Fives");
			System.out.println("6. Sixes");
			System.out.println("7. Three of a kind");
			System.out.println("8. Four of a kind");
			System.out.println("9. Full house");
			System.out.println("10. Small straight");
			System.out.println("11. Large straight");
			System.out.println("12. Yahtzee");
			System.out.println("13. Chance");
			int input = scanner.nextInt();
			if (scoreCard.isAvailable(input, gameCount)) {
				category = input;
			} else {
				System.out.println("Category already used. Choose another category.");
			}
		}
		return category;
	}

	private boolean playAgain() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Play again? (y/n)");
		String input = scanner.nextLine();
		return input.equalsIgnoreCase("y");
	}

	public static void main(String[] args) {

		int gameCount = 0;
		Scanner scanner = new Scanner(System.in);
		while (gameCount < 3) {
			YahtzeeGame game = new YahtzeeGame();
			game.play();
			gameCount++;
			if (gameCount < 3) {
				System.out.println("Press enter to play again");
				scanner.nextLine();
			}
		}

	}

}
