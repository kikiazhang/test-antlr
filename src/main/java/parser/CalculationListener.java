package parser;

import java.util.Stack;

public class CalculationListener extends CalculatorBaseListener {
	/**
	 * I used a stack to track the numbers seen before.
	 *
	 * The right number in an operation is always the last one seen or calculated
	 * for that operation. Because a stack works via Last In First Out, the right
	 * number should be "popped" off first.
	 */
	private Stack<Double> stack = new Stack<>();

	@Override
	public void exitNumber(CalculatorParser.NumberContext ctx) {
		Double number = Double.parseDouble(ctx.NUMBER().getText());
		this.stack.push(number);
	}

	@Override
	public void exitPower(CalculatorParser.PowerContext ctx) {
		Double right = this.stack.pop();
		Double left = this.stack.pop();

		this.stack.push(Math.pow(left, right));
	}

	@Override
	public void exitMultiplication(CalculatorParser.MultiplicationContext ctx) {
		Double right = this.stack.pop();
		Double left = this.stack.pop();

		this.stack.push(left * right);
	}

	@Override
	public void exitDivision(CalculatorParser.DivisionContext ctx) {
		Double right = this.stack.pop();
		Double left = this.stack.pop();

		this.stack.push(left / right);
	}

	@Override
	public void exitAddition(CalculatorParser.AdditionContext ctx) {
		Double right = this.stack.pop();
		Double left = this.stack.pop();

		this.stack.push(left + right);
	}

	@Override
	public void exitSubtraction(CalculatorParser.SubtractionContext ctx) {
		Double right = this.stack.pop();
		Double left = this.stack.pop();

		this.stack.push(left - right);
	}

	/**
	 * The last value on the stack is the final result.
	 *
	 * @return Double
	 */
	public Double getResult() {
		return this.stack.pop();
	}
}
