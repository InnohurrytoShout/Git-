package guessme;

/**
 * A LinkedList-based implementation of the Guess-A-Number game
 */
public class LinkedListGame {

	// TODO: declare data members as necessary

	private LLIntegerNode priorGuessHead;
	private	LLIntegerNode priorGuessTail;
	private LLIntegerNode numberHead;
	private int guess;
	private int count;
	private boolean over;

	/********************************************************
	 * NOTE: for this project you must use linked lists
	 * implemented by yourself. You are NOT ALLOWED to use
	 * Java arrays of any type, or any class in the java.util
	 * package (such as ArrayList).
	 *******************************************************/	 
	
	/********************************************************
	 * NOTE: you are allowed to add new methods if necessary,
	 * but DO NOT remove any provided method, and do NOT add
	 * new files (as they will be ignored by the autograder).
	 *******************************************************/
	
	// LinkedListGame constructor method
	public LinkedListGame() {
		// TODO
		reset();
	}
	
	// Resets data members and game state so we can play again
	public void reset() {
		// TODO
		this.count = 0;
		this.guess = 1000;
		this.priorGuessHead = null;
		this.priorGuessTail = null;
		this.over = false;
		this.numberHead = new LLIntegerNode(1000);
		LLIntegerNode tempNumber = numberHead;
		for(int i = 1; i < 9000; i++) {
			LLIntegerNode element = new LLIntegerNode(guess + i);
			tempNumber.setLink(element);
			tempNumber = element;
		}
		
	}
	
	// Returns true if n is a prior guess; false otherwise.
	public boolean isPriorGuess(int n) {
		// TODO
		if(priorGuessHead == null)	return false;
		LLIntegerNode temp = priorGuessHead;
		while(temp != null) {
			if(temp.getInfo() == n) {
				return true;
			}
			temp = temp.getLink();
		}
		return false;
		
	}
	
	// Returns the number of guesses so far.
	public int numGuesses() {
		// TODO
		return this.count;
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
	public static int numMatches(int a, int b) {
		// TODO
		int count = 0;
		if (a / 1000 == b / 1000)				count++;
		if ((a / 100) % 10 == (b / 100) % 10)	count++;
		if ((a % 100) / 10 == (b % 100) / 10)	count++;
		if (a % 10 == b % 10)					count++;
		
		return count;
	}
	
	/**
	 * Returns true if the game is over; false otherwise.
	 * The game is over if the number has been correctly guessed
	 * or if no candidate is left.
	 */
	public boolean isOver() {
		// TODO
		return this.over;
	}
	
	/**
	 * Returns the guess number and adds it to the list of prior guesses.
	 * The insertion should occur at the end of the prior guesses list,
	 * so that the order of the nodes follow the order of prior guesses.
	 */	
	public int getGuess() {
		// TODO: add guess to the list of prior guesses.
		if(this.priorGuessHead == null)	{
			guess = 1000;
			this.priorGuessHead = new LLIntegerNode(guess);
			this.priorGuessTail = this.priorGuessHead;
		}else {
			LLIntegerNode temp = new LLIntegerNode(guess);
			this.priorGuessTail.setLink(temp);
			this.priorGuessTail = temp;
		}
		this.count ++;
		return this.guess;
		
	}
	
	/**
	 * Updates guess based on the number of matches of the previous guess.
	 * If nmatches is 4, the previous guess is correct and the game is over.
	 * Check project description for implementation details.
	 * 
	 * Returns true if the update has no error; false if no candidate 
	 * is left (indicating a state of error);
	 */
	public boolean updateGuess(int nmatches) {
		// TODO
		if (nmatches == 4){
			over = true;
			return true;
		}
		
		LLIntegerNode update = this.numberHead;
		LLIntegerNode updateHead = null;
		LLIntegerNode updateTail = null;
		
		while((update != null)) {
			if(numMatches( update.getInfo(), guess) == nmatches) {
				LLIntegerNode rightNum = new LLIntegerNode(update.getInfo());
				if(updateTail == null) {
					updateHead = rightNum;
					updateTail = rightNum;
				}else {
					updateTail.setLink(rightNum);
					updateTail = updateTail.getLink();
				}
			}
			update = update.getLink();
		}
		this.numberHead = updateHead;
		if(updateHead != null)	guess = updateHead.getInfo();
		if(updateHead == null)	
			return false;
		else
			return true;		
			
		/*	if(update.getInfo() != -1) {
				if(nmatches != numMatches(guess, i)) {
					update.setInfo(-1);
				}
			}
			update = update.getLink();
		}
		
		update = this.numberHead;
		
		for(int i = 1000; i<= 9999; i++) {
			if(update.getInfo() != -1) {
				guess= i;
				return true;
			}
		}
		
		return false;*/
	}
	
	// Returns the head of the prior guesses list.
	// Returns null if there hasn't been any prior guess
	public LLIntegerNode priorGuesses() {
		// TODO
		return priorGuessHead;
	
	}
	
	/**
	 * Returns the list of prior guesses as a String. For example,
	 * if the prior guesses are 1000, 2111, 3222, in that order,
	 * the returned string should be "1000, 2111, 3222", in the same order,
	 * with every two numbers separated by a comma and space, except the
	 * last number (which should not be followed by either comma or space).
	 *
	 * Returns an empty string if here hasn't been any prior guess
	 */
	public String priorGuessesString() {
		// TODO
		LLIntegerNode tstring = this.priorGuessHead;
		String result = "";
		if(tstring != null) {
			while(tstring.getLink() != null) {
				result= result + Integer.toString(tstring.getInfo()) + ", ";
				tstring = tstring.getLink();
			}
			result = result + Integer.toString(tstring.getInfo());
		}
		/*if(this.priorGuessHead == null)	return result;
		
		
		while(tstring.getLink() != null) {
			result += tstring.getInfo() + ", ";
			tstring = tstring.getLink();
		}
		if(!result.equals("")) result = result.substring(0, result.length() -2);*/
		return result;
	}
	
}
