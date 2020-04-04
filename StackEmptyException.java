package project2;
/**
 * <b>Title:</b> StackEmptyException <br>
 * <p>Description: exception to throw when the stack is empty </p>
 * <b>Filename:</b> StackEmptyException.java<br>
 * <b>Date Written:</b> March 09, 2020<br>
 * <b>Due Date:</b> March 09, 2020<br>
 * @author John Herbener 
 */

public class StackEmptyException extends RuntimeException {
	/**
	 * Constructs a new StackEmptyException with a default error message string.
	 */
	public StackEmptyException(){
		super("Exception : Stack is empty");
	}
	/**
	 * Constructs a new StackEmptyException with the parameter as the error message string.
	 * @param msg The string passed as the error message string.
	 */
	public StackEmptyException(String msg){
		super(msg);
	}
}