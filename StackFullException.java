package project2;
/**
 * <b>Title:</b> StackFullException <br>
 * <p>Description: exception to throw when the stack is full </p>
 * <b>Filename:</b> StackEmptyException.java<br>
 * <b>Date Written:</b> March 09, 2020<br>
 * <b>Due Date:</b> March 09, 2020<br>
 * @author John Herbener 
 */
public class StackFullException extends RuntimeException {
	/**
	 * Constructs a new StackFullException with a default error message string.
	 */
	public StackFullException(){
		super("Exception : Stack is full");
	}
	/**
	 * Constructs a new StackFullException with the parameter as the error message string.
	 * @param msg The string passed as the error message string.
	 */
	public StackFullException(String msg){
		super(msg);
	}
}