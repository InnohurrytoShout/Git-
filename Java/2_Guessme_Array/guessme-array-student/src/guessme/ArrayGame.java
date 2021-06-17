package guessme;

/**
 * An Array-based implementation of the Guess-A-Number game
 */
public class ArrayGame {

	// stores the next number to guess
	private int guess;
	
	// TODO: declare additional data members, such as arrays that store
	// prior guesses, eliminated candidates etc.
	
	public int[] priorGuess;
	public boolean[] jackpot;
	boolean over;
	int maxSame;
	int lastguess;
	int mag;
	// NOTE: only primitive type arrays are allowed, such as int[], boolean[] etc.
	// You MAY NOT use any Collection type (such as ArrayList) provided by Java.
	
	/********************************************************
	 * NOTE: you are allowed to add new methods if necessary,
	 * but DO NOT remove any provided method, otherwise your
	 * code will fail the JUnit tests!
	 * Also DO NOT create any new Java files, as they will
	 * be ignored by the autograder!
	 *******************************************************/
	
	// ArrayGame constructor method
	public ArrayGame() {
		// TODO
		priorGuess = new int[0];
		guess = 1000;
		over = false;
		jackpot = new boolean[9000];
		
	}
	
	// Resets data members and game state so we can play again
	public void reset() {
		// TODO
		guess = 1000;
		over = false;
		jackpot = new boolean[9000];
		priorGuess = new int[0];
			
	}
	
	// Returns true if n is a prior guess; false otherwise.
	public boolean isPriorGuess(int n) {
		// TODO
		if (n == priorGuess[priorGuess.length - 1])
			return true;
		
		if (priorGuess.length == 0 && n == 1000)
			return true;
		
		return false;
		
	}
	
	// Returns the number of guesses so far.
	public int numGuesses() {
		// TODO
		//if (priorGuess.length == 0)) return 0;
		return priorGuess.length;
	}
	
	/**
	 * Returns the number of matches between integers a and b.
	 * You can assume that both are 4-digits long (i.e. between 1000 and 9999).
	 * The return value must be between 0 and 4.
	 * 
	 * A match is the same digit at the same location. For example:
	 *   1234 and 4321 have 0 match;
	 *   1234 and 1114 have 2 matches (1 and 4);
	 *   1000 and 9000 have 3 matches (three 0's).
	 */
	public static int numMatches(int a, int b) { // DO NOT remove the static qualifier
		// TODO
		int[] Senn = new int[2];		int[] Hyaku =new int[2];
		int[] Jyuu = new int[2];		int[] Kaku = new int[2];
		int Onaji = 0;
		Senn[0]= a / 1000;				Senn[1]= b / 1000;
		Hyaku[0]= (a / 100) % 10;		Hyaku[1]= (b / 100) % 10;
		Jyuu[0]= (a % 100) / 10;		Jyuu[1]= (b % 100) / 10;
		Kaku[0]= a % 10;				Kaku[1]= b % 10;
		if (Senn[0] == Senn[1]) 
			Onaji = Onaji + 1;
		
		if (Hyaku[0] == Hyaku[1])
			Onaji = Onaji + 1;
		
		if (Jyuu[0] == Jyuu[1])
			Onaji = Onaji + 1;
		
		if (Kaku[0] == Kaku[1])
			Onaji = Onaji + 1;
		
		return Onaji;
	}
	
	/**
	 * Returns true if the game is over; false otherwise.
	 * The game is over if the number has been correctly guessed
	 * or if all candidates have been eliminated.
	 */
	public boolean isOver() {
		// TODO
		if (over)	return true;
		return false;
	}
	
	// Returns the guess number and adds it to the list of prior guesses.
	public int getGuess() {
		// TODO: add guess to the list of prior guesses.
		int[] tempArray = priorGuess;
		priorGuess = new int[priorGuess.length + 1];
		System.arraycopy(tempArray, 0, priorGuess, 0, tempArray.length);
		priorGuess[priorGuess.length - 1] = guess;		
		return guess;
	}
	
	/**
	 * Updates guess based on the number of matches of the previous guess.
	 * If nmatches is 4, the previous guess is correct and the game is over.
	 * Check project description for implementation details.
	 * 
	 * Returns true if the update has no error; false if all candidates
	 * have been eliminated (indicating a state of error);
	 */
	
	public boolean updateGuess(int nmatches) {
		// TODO
		if (nmatches == 4 )	{
			over = true;
			return true;
		}
		
		
		for (int i = 0; i < jackpot.length; ++i) {
			if (nmatches != numMatches(guess, i + 1000 )) {
				jackpot[i] = true;
			}
		}
		
		for (int i = 0; i < jackpot.length; ++i) {
			if (jackpot[i] == false) {
				guess = i + 1000;
				return true;
			}
		}
		return false;
	}
	
	// Returns the list of guesses so far as an integer array.
	// The size of the array must be the number of prior guesses.
	// Returns null if there has been no prior guess
	public int[] priorGuesses() {
		// TODO
		if (priorGuess.length == 0)	{
			return null;
		}else {
			return priorGuess;
		}
	}
}
