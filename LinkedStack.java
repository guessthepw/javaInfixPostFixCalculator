package project2;

/**
 * <b>Title:</b> LinkedStack - Calculator Project #2 <br>
 * <p>
 * Description: This ArrayStack class represents a last-in-first-out (LIFO)
 * stack of objects. The usual push and pop operations are provided, as well as
 * a method to peek at the top item on the stack, and methods to test whether
 * the stack is empty or full. When a stack is first created, it contains no
 * items.
 * </p>
 * <b>Filename:</b> ArrayStack.java<br>
 * <b>Date Written:</b> March 09, 2020<br>
 * <b>Due Date:</b> March 09, 2020<br>
 * 
 * @author John Herbener
 */

public class LinkedStack<T> implements Stack<T> {
	/**
	 * The array into which the objects of the stack are stored.
	 */
	private T[] data;
	/**
	 * The number of objects in this stack.
	 */
	private int numItems;
	/**
	 * The default capacity of this stack.
	 */
	private final int DEFAULT_SIZE = 100;

	/**
	 * Constructs a new Stack with capacity for 100 objects
	 */
	public LinkedStack() {
		data = (T[]) new Object[DEFAULT_SIZE];
		numItems = 0;
	}

	/**
	 * Constructs a new Stack with capacity specified by user
	 * 
	 * @param capacity the size of the Stack
	 */
	public LinkedStack(int capacity) {
		if (capacity < 1)
			data = (T[]) new Object[DEFAULT_SIZE];
		else
			data = (T[]) new Object[capacity];
		numItems = 0;
	}

	/**
	 * Pushes an object onto the top of this stack.
	 * 
	 * @param item The object to be stored onto the stack.
	 * @throws StackFullException - if this stack is full
	 */
	public void push(T item) throws StackFullException {
		if (isFull())
			throw new StackFullException("Stack full Exception...");
		data[numItems] = item;
		numItems++;
	}

	/**
	 * Removes and returns the object at the top of this stack.
	 * 
	 * @return The object at the top of the stack.
	 * @throws StackEmptyException - if this stack is empty
	 */
	public T pop() throws StackEmptyException {
		if (isEmpty())
			throw new StackEmptyException("Stack empty Exception...");
		numItems--;
		T item = data[numItems];
		return item;
	}

	/**
	 * Returns the object at the top of this stack without removing it.
	 * 
	 * @return The object at the top of the stack.
	 * @throws StackEmptyException - if this stack is empty
	 */
	public T peek() throws StackEmptyException {
		if (isEmpty())
			throw new StackEmptyException("Stack empty Exception...");
		return data[numItems - 1];
	}

	/**
	 * Checks if the stack is empty
	 * 
	 * @return true if empty false otherwise
	 */
	public boolean isEmpty() {
		return numItems == 0;
	}

	/**
	 * empties the stack of all objects
	 */
	public void makeEmpty() {
		for (int i = 0; i < numItems; i++)
			this.pop();
		numItems = 0;
	}

	/**
	 * Checks if the stack is full
	 * 
	 * @return true if full false otherwise
	 */
	public boolean isFull() {
		return numItems == data.length;
	}

	/**
	 * Returns the number of objects on the stack.
	 * 
	 * @return The number of objects on the stack.
	 */
	public int getSize() {
		return numItems;
	}

	/**
	 * Returns the current state of this stack.
	 * 
	 * @return String representation of this stack.
	 */
	public String toString() {
		String str = "";
		for (int i = numItems - 1; i >= 0; i--) {
			str += data[i] + "\n";
		}
		return str;
	}
}