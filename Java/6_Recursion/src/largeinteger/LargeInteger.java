package largeinteger;

import largeinteger.LLNode;

/** The LargeInteger class
 *  This class represents a large, non-negative integer using a linked list.
 *  Each node stores a single digit. The nodes represent all digits in *reverse* order:
 *  the least significant digit is the first node, and the most significant the last node.
 *  For example, 135642 is represented as 2->4->6->5->3->1 in that order.
 */
public class LargeInteger {
	private LLNode<Integer> head;	// head of the list
	private int size;				// size (i.e. number of digits)
	
	// Returns size
	public int size() { return size; }
	// Returns the linked list (used only for JUnit test purpose)
	public LLNode<Integer> getList() { return head; }
	
	public LargeInteger() {
		head = null; size = 0;
	}
	
	/** Constructor that takes a String as input and constructs the linked list.
	 *  You can assume that the input is guaranteed to be valid: i.e. every character
	 *  in the string is between '0' and '9', and the first character is never '0'
	 *  (unless '0' is the only character in the string). You can use input.charAt(i)-'0'
	 *  to convert the character at index i to the integer value of that digit.
	 *  Remember: the list nodes must be in reverse order as the characters in the string.
	 */
	public LargeInteger(String input) {
		// TODO
		for(int i = 0; i < input.length(); i++){
			Integer temp = input.charAt(i) - '0';
			LLNode<Integer> newNode = new LLNode<>();
			newNode.data = temp;
			newNode.link = this.head;
			this.head = newNode;
			size++;
		}
	}
	
	/** Divide *this* large integer by 10 and return this.
	 *  Assume integer division: for example, 23/10 = 2, 8/10 = 0 and so on.
	 */
	public LargeInteger divide10() {
		// TODO
		if(size() < 2){
			this.head.data = 0;
		}else{
			this.head = this.head.link;
			this.size--;
		}

		return this;
	}
	
	/** Multiply *this* large integer by 10 and return this.
	 *  For example, 23*10 = 230, 0*10 = 0 etc.
	 */
	public LargeInteger multiply10() {
		// TODO
		if(head.link == null && head.data == 0){return this;}
		LLNode<Integer> newNode = new LLNode<>();
		newNode.data = 0;
		newNode.link = this.head;
		this.head = newNode;
		this.size++;
		return this;
	}
	
	/** Returns a *new* LargeInteger object representing the sum of this large integer
	 *  and another one (given by that). Your code must correctly handle cases such as
	 *  the two input integers have different sizes (e.g. 2+1000=1002), or there is a
	 *  carry over at the highest digit (e.g. 9999+2=10001).
	 */
	public LargeInteger add(LargeInteger that) {
		// TODO

		LargeInteger large = new LargeInteger();
		LargeInteger small = new LargeInteger();
		if(this.size >= that.size){						//Judge whether this or that has a larger size
			large = this;
			large.size = this.size;
			small = that;
			small.size = that.size;
		}else{
			large = that;
			large.size = that.size;
			small = this;
			small.size = that.size;
		}
		LargeInteger temp = new LargeInteger();
		temp.head = new LLNode<>();
		LLNode<Integer> temp1 = temp.head;
		int realsize = large.size;
		int templargesize = large.size;
		int tempsmallsize = small.size;
		int plusone = 0;
		LLNode<Integer> templarge = large.head;
		LLNode<Integer> tempsmall = small.head;

		for(int i = 0; i < realsize; i++) {

			if (tempsmall != null) {

				temp1.data = plusone + templarge.data + tempsmall.data;
				tempsmall = tempsmall.link;
				tempsmallsize--;

			} else {

				temp1.data = plusone + templarge.data;

			}

			templarge = templarge.link;
			templargesize--;
			temp.size++;

			if (templargesize == 0 && temp1.data >= 10){temp.size++;}
			if (temp1.data >= 10) {// if the sum of two final digit is larger than 10

				temp1.data -= 10;                        //set the current digit minus 10
				plusone = 1;
				LLNode<Integer> newTemp = new LLNode<>();//create a new node
				newTemp.data = 1;
				temp1.link = newTemp;                    //insert the node to the tail
				temp1 = newTemp;

			} else if ( templargesize != 0 && temp1.data < 10) {                                        //if the sum of two digit is less than 10;

				plusone = 0;
				LLNode<Integer> newTemp = new LLNode<>();
				temp1.link = newTemp;                    //insert the node to the tail
				temp1 = newTemp;

			}


		}
		return temp;
	}
	
	/** Returns a new LargeInteger object representing the result of multiplying
	 *  this large integer with a non-negative integer x. You can assume x is either
	 *  a positive integer or 0. Hint: you can use a loop and call the 'add' method
	 *  above to accomplish the 'multiply'.
	 */
	public LargeInteger multiply(int x) {
		// TODO
		LargeInteger newOne = new LargeInteger();
		if(x == 0){
			newOne.head = new LLNode<>();
			newOne.head.data = 0;
			newOne.head.link = null;
			newOne.size++;
		}
		if(x > 0) {
			for (int i = 0; i < x; i++) {
				newOne = newOne.add(this);
			}
		}
		return newOne;
	}

	/** Recursive method that converts the list referenced by curr_node back to
	 *  a string representing the integer. Think about what's the base case and
	 *  what it should return. Then think about what it should return in non-base case.
	 *  Hint: refer to the 'printing a list backwards' example we covered in lectures.
	 */
	private String toString(LLNode<Integer> curr_node) {
		// TODO
		LLNode<Integer> temp = curr_node;
		if(temp.link == null){
			return String.valueOf(temp.data);
		}
		return toString(temp.link) + temp.data;

	}
	
	/** Convert this list back to a string representing the large integer.
	 *  This is a public method that jump-starts the call to the recursive method above.
	 */
	public String toString() {
		return toString(head);
	}
	
	// Recursive method to compute factorial
	public static LargeInteger factorial(int n) {
		if(n==0) return new LargeInteger("1");
		return factorial(n-1).multiply(n);
	}
	
	// Recursive method to compute power
	public static LargeInteger pow(int x, int y) {
		if(y==0) return new LargeInteger("1");
		return pow(x, y-1).multiply(x);
	}
}
