package project2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;

/**
 * <b>Title:</b> Project 2: Calculator Infix to Postfix <br>
 * <p>
 * Description: Take a input as infix and convert it to postfix then evaluate
 * and display the results
 * </p>
 * <b>Filename:</b> CalculatorFrame.java<br>
 * <b>Date Written:</b> March 09, 2020<br>
 * <b>Due Date:</b> March 09, 2020<br>
 * 
 * @author John Herbener
 */

@SuppressWarnings("serial")
class CalculatorFrame extends JFrame implements ActionListener {

	JTextField jtfInfix = new JTextField(21); // for infix
	JTextField jtfPostfix = new JTextField(); // for postix
	JTextField result = new JTextField("0"); // for result

	JButton[][] calcButton = new JButton[4][5];

	JPanel calcPanel = new JPanel();
	JPanel topPanel = new JPanel();

	/**
	 * Name: Default Calculator Frame Constructor Description: creates a new
	 * calculator frame object
	 * 
	 */

	public CalculatorFrame() {
		String[][] buttonText = new String[][] { { "7", "8", "9", "/", "C" }, { "4", "5", "6", "*", "B" },
				{ "1", "2", "3", "-", "R" }, { "0", "(", ")", "+", "=" } };

		this.setTitle("CSC130 Calculator");
		this.setLayout(new BorderLayout(2, 1));

		jtfInfix.setHorizontalAlignment(JTextField.RIGHT);
		jtfPostfix.setHorizontalAlignment(JTextField.RIGHT);
		result.setHorizontalAlignment(JTextField.RIGHT);
		jtfPostfix.setEnabled(false);
		result.setEnabled(false);
		// jtfInfix.setEditable(false); // hide text caret

		// set the font size to 34 for the text fields
		Font textFieldFont = new Font(jtfPostfix.getFont().getName(), jtfPostfix.getFont().getStyle(), 24);
		jtfInfix.setFont(textFieldFont);
		jtfPostfix.setFont(textFieldFont);
		result.setFont(textFieldFont);

		topPanel.setLayout(new GridLayout(3, 1));
		topPanel.add(jtfInfix);
		topPanel.add(jtfPostfix);
		topPanel.add(result);

		calcPanel.setLayout(new GridLayout(4, 5, 3, 3));

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 5; j++) {
				calcButton[i][j] = new JButton("" + buttonText[i][j]);
				calcButton[i][j].setForeground(Color.blue);
				calcButton[i][j].setFont(new Font("sansserif", Font.BOLD, 42));
				calcButton[i][j].addActionListener(this);
				calcButton[i][j].setBorder(BorderFactory.createRaisedBevelBorder());
				calcPanel.add(calcButton[i][j]);
			}
		}
		this.add(topPanel, BorderLayout.NORTH);
		this.add(calcPanel, BorderLayout.CENTER);
	}

	/**
	 * Name: ActionPerformed Description:
	 * 
	 * @param ActionEvent e
	 * 
	 */
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 5; j++) {
				if (e.getSource() == calcButton[i][j]) {
					// clear
					if (i == 0 && j == 4) {
						jtfInfix.setText(null);
						jtfPostfix.setText(null);
						result.setText("0");
					}
					// backspace
					else if (i == 1 && j == 4) {
						if (jtfInfix.getDocument().getLength() > 0)
							try {
								jtfInfix.setText(jtfInfix.getText(0, jtfInfix.getDocument().getLength() - 1));
							} catch (BadLocationException e1) {
								e1.printStackTrace();
							}

					}
					// number or operator
					else if (j < 4) {
						jtfInfix.setText(jtfInfix.getText() + calcButton[i][j].getText());
					}
					// = button pressed
					else if (i == 3 && j == 4) {
						// erase contents of the postfix textfield
						jtfPostfix.setText(null);
						// update the postfix textfield with the String returned
						jtfPostfix.setText(infixToPostfix());
						// update the result textfield with the result of the computation
						result.setText("" + calculate());
					}
				}
			}
		}
	}

	/**
	 * Name: isOperator Description: returns true if is an operator
	 * 
	 * @param String s - single string character to check if is an operator
	 * @return - true if is +-/* false otherwise
	 */
	public boolean isOperator(String s) {
		if (s.equals("-") || s.equals("+") || s.equals("*") || s.equals("/"))
			return true;
		else
			return false;
	}

	/**
	 * Name: infixToPostfix Description: converts infix expression to postfix and
	 * returns the result
	 * 
	 * @return the postfix equivalent to the input
	 */
	public String infixToPostfix() {
		String expression = jtfInfix.getText();
		String delims = "+-*/() ";
		String result = "";

		LinkedStack<String> theStack = new LinkedStack<>();
		StringTokenizer strToken = new StringTokenizer(expression, delims, true);

		while (strToken.hasMoreTokens()) {
			String token = strToken.nextToken();
			if (!isOperator(token) && !token.equals(")") && !token.equals("(")) {
				result += " " + token;
			} else if (token.equals("(")) {
				theStack.push(token);
			} else if (token.equals(")")) {
				while (!theStack.isEmpty() && !theStack.peek().equals("(")) {
					result += " " + theStack.pop();
				}
				theStack.pop();
			} else if (isOperator(token)) {
				while (!theStack.isEmpty() && (theStack.peek().equals("*") || theStack.peek().equals("/")))
					result += " " + theStack.pop();
				theStack.push(token);
			}
		}
		while (!theStack.isEmpty()) {
			result += " " + theStack.pop();
		}
		return result;
	}

	/**
	 * Name: calculate Description: evaluates the postfix express
	 * 
	 * @return the value that expression evaluates to.
	 */
	public String calculate() {
		String delims = "+-*/() ";
		StringTokenizer postfixTokens = new StringTokenizer(infixToPostfix(), delims, true);
		LinkedStack<String> theStack = new LinkedStack<>();

		while (postfixTokens.hasMoreTokens()) {
			String token = postfixTokens.nextToken();
			if (token.equals(" "))
				continue;
			else if (!isOperator(token))
				theStack.push(token);
			else {
				int o1 = Integer.parseInt(theStack.pop());
				int o2 = Integer.parseInt(theStack.pop());

				if (token.equals("*")) {
					theStack.push(Integer.toString(o1 * o2));
				} else if (token.equals("/")) {
					theStack.push(Integer.toString(o1 / o2));
				} else if (token.equals("+")) {
					theStack.push(Integer.toString(o1 + o2));
				} else if (token.equals("-")) {
					theStack.push(Integer.toString(o1 - o2));
				}
			}
		}
		return theStack.pop();

	}

	static final int MAX_WIDTH = 398, MAX_HEIGHT = 440;

	/**
	 * Name: main method Description: creates new calculator frame object.
	 * 
	 */
	public static void main(String arg[]) {
		JFrame frame = new CalculatorFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(MAX_WIDTH, MAX_HEIGHT);
		frame.setBackground(Color.white);
		frame.setResizable(false);
		frame.setVisible(true);
	}
}
