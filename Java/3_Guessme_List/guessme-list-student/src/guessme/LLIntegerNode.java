package guessme;

/**
 * This class defines a linked list node storing an integer.
 * Use primitive type int (do not use wrapper class Integer)
 * You must provide the following methods:
 * - a constructor
 * - a setInfo method and a getInfo method
 * - a setLink method and a getLink method
 */
public class LLIntegerNode {
	// TODO
	
	 private int contents;
	 private LLIntegerNode link;
	
	public LLIntegerNode( int integer) {
		this.contents = integer;
		this.link = null;
	}
	
	public int getInfo(){
		return contents;
	}
	
	public LLIntegerNode getLink() {
		return link;
	}
	
	public void setInfo(int integer) {
		contents = integer;
	}
	
	public void setLink(LLIntegerNode link) {
		this.link = link;
	}
	
	
	
	
}

