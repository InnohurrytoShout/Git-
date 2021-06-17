package evaluator;

import parser.ArithParser;
import stack.LinkedStack;

public class InfixEvaluator extends Evaluator {
	
	private LinkedStack<String> operators = new LinkedStack<String>();
	private LinkedStack<Integer> operands  = new LinkedStack<Integer>();
	
	/** return stack object (for testing purpose) */
	public LinkedStack<String> getOperatorStack() { return operators; }
	public LinkedStack<Integer> getOperandStack()  { return operands;  }
	
	
	/** This method performs one step of evaluation of a infix expression.
	 *  The input is a token. Follow the infix evaluation algorithm
	 *  to implement this method. If the expression is invalid, throw an
	 *  exception with the corresponding exception message.
	 */	
	public void evaluate_step(String token) throws Exception {
		if(isOperand(token)) {
			operands.push(Integer.parseInt(token));
			// TODO: What do we do if the token is an operand?
		} else {
			if (token.equals("(") || token.equals(")")) {

				if (token.equals("(")) {

					operators.push(token);

				} else { 

					process_operator(1);

				}

			} else {

				if (precedence(token) <= 1 || token.equals("%")) {

					throw new Exception("invalid operator");

				} else {

					if (operators.isEmpty()) {

						operators.push(token);

					} else {

						if (precedence(token) > precedence(operators.top())) {

							operators.push(token);

						} else {

							if (operators.top().equals("!") && token.equals("!")) {

								operators.pop();

							} else if (operators.top().equals("!")) {

								process_operator(0);

								if (operators.isEmpty() || precedence(token) > precedence(operators.top())) {

									operators.push(token);

								} else {

									process_operator(0);

									operators.push(token);

								}

							} else {

								process_operator(0);

								operators.push(token);

							}

						}

					}

				}

			}

			}
			/* TODO: What do we do if the token is an operator?
			   If the expression is invalid, make sure you throw
			   an exception with the correct message.
			   
			   You can call precedence(token) to get the precedence
			   value of an operator. It's already defined in 
			   the Evaluator class.
			 */ 
	}	
	
	/** This method evaluates an infix expression (defined by expr)
	 *  and returns the evaluation result. It throws an Exception object
	 *  if the infix expression is invalid.
	 */
	public void process_operator(int right) throws Exception{
		Integer operand2, operand1 = 0, result = 0;
		boolean operatorleft = false; 
		//operatorleft2 = true; 
		char op = operators.pop().charAt(0);
		if(operands.isEmpty()) {throw new Exception("too few operands");}
		else {operand2 = operands.pop();}
		if( op != '!' && op != '(') {
			if(operands.isEmpty()) {throw new Exception("too few operands");}
			operand1 = operands.pop();
		}
		switch(op)
		{
		case '(':
			if(operand2 == null) {throw new Exception("too few operands");}
			result = operand2;
			operatorleft = true;
			break;
		case '!':
			result = -operand2;
			break;
		case '+':
			result = operand1 + operand2;
			break;
		case '-':
			result = operand1 - operand2;
			break;
		case '*':
			result = operand1 * operand2;
			break;
		case '/':
			if(operand2 == 0) throw new Exception("division by zero");
			else result = operand1 / operand2;
			break;
		}
		operands.push(result);
		if (!operators.isEmpty()) {

			if (!operators.isEmpty() && !operators.top().equals("(") && right == 1 && !operatorleft) {
				process_operator(right);
			}
			if (!operators.isEmpty() && operators.top().equals("(") && right == 1 && !operatorleft) {
				operators.pop();
			}
		} else {
			if(!operatorleft && right == 1) {
				throw new Exception("missing (");
			}
		}
	}
	
	public Integer evaluate(String expr) throws Exception {
		
		for(String token : ArithParser.parse(expr)) {
			evaluate_step(token);
		}
		
		/* TODO: what do we do after all tokens have been processed? */
		while(!operators.isEmpty() && !operands.isEmpty()) {process_operator(0);}
		// The operand stack should have exactly one operand after the evaluation is done
		if(operands.size()>1)
			throw new Exception("too many operands");
		else if(operands.size()<1)
			throw new Exception("too few operands");
		
		return operands.pop();
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(new InfixEvaluator().evaluate("5+(5+2*(5+9))/!8"));
	}
}
