/*
 * Copyright (c) 2012 Amndeep Singh Mann <Amndeep.dev@gmail.com> Please read License.txt for full license information.
 */

package randomartassignment.expression;

/**
 * Power represents the exponentiation symbol, generally seen as "^".
 * 
 * @version 1.0 23 July 2012
 * @author Amndeep Singh Mann
 */
public class Power extends ExpressionParallel
{
	/**
	 * A constructor.
	 */
	public Power()
	{
		super();
	}

	/**
	 * A constructor that accepts two predetermined arguments.
	 * 
	 * @param ex
	 *             A <code>ExpressionParallel</code> argument that is a valid subexpression.
	 * @param ex2
	 *             A <code>ExpressionParallel</code> argument that is a valid subexpression.
	 */
	public Power(ExpressionParallel ex, ExpressionParallel ex2)
	{
		super(ex, ex2);
	}

	/**
	 * The solution to this expression when provided with values for variables. In this case, the first argument to the power of the absolute value of the second argument. The absolute
	 * value is necessary so that the resulting value ends up staying within the boundaries of [-1.0, 1.0]. This is because using a negative exponent turns the expression into a radical,
	 * which has the potential to return values that are outside of the necessary range when given values that are less than one. Also, radicals require positive values to be given to them.
	 * 
	 * @see randomartassignment.expression.ExpressionParallel#evaluate(double, double)
	 */
	@Override
	public double evaluate(double x, double y)
	{
		return Math.pow(expr.get(0).evaluate(x, y), Math.abs(expr.get(1).evaluate(x, y)));
	}

	/**
	 * Gets a value that help determines the number of arguments it needs when creating subexpressions for this expression.
	 * 
	 * @return An integer that is equal to 2 (for two argument).
	 * 
	 * @see randomartassignment.expression.ExpressionParallel#howManyArguments()
	 */
	@Override
	public int howManyArguments()
	{
		return 2;
	}

	/**
	 * A representation of this expression that consists of the two expressions being wrapped in parenthesis and the ^ symbol placed between them.
	 * 
	 * @see randomartassignment.expression.ExpressionParallel#toString()
	 */
	@Override
	public String toString()
	{
		return "(" + expr.get(0) + " ^ " + expr.get(1) + ")";
	}

}
