package parser;

public class CalculationVisitor extends CalculatorBaseVisitor<Double> {
	/**
	 * I used double for the numbers.
	 *
	 * @return Double
	 */
	@Override
	public Double visitNumber(CalculatorParser.NumberContext ctx) {
		return Double.parseDouble(ctx.NUMBER().getText());
	}

	/**
	 * Parentheses are used to change the order of the wrapped parts of expression.
	 *
	 * @return Double
	 */
	@Override
	public Double visitParentheses(CalculatorParser.ParenthesesContext ctx) {
		return this.visit(ctx.inner);
	}

	/**
	 * @return Double
	 */
	@Override
	public Double visitPower(CalculatorParser.PowerContext ctx) {
		return Math.pow(this.visit(ctx.left), this.visit(ctx.right));
	}

	/**
	 * @return Double
	 */
	@Override
	public Double visitMultiplication(CalculatorParser.MultiplicationContext ctx) {
		return this.visit(ctx.left) * this.visit(ctx.right);
	}

	/**
	 * @return Double
	 */
	@Override
	public Double visitDivision(CalculatorParser.DivisionContext ctx) {
		return this.visit(ctx.left) / this.visit(ctx.right);
	}

	/**
	 * @return Double
	 */
	@Override
	public Double visitAddition(CalculatorParser.AdditionContext ctx) {
		return this.visit(ctx.left) + this.visit(ctx.right);
	}

	/**
	 * @return Double
	 */
	@Override
	public Double visitSubtraction(CalculatorParser.SubtractionContext ctx) {
		return this.visit(ctx.left) - this.visit(ctx.right);
	}
}
