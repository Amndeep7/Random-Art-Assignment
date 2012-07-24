/*
 * Copyright (c) 2012 Amndeep Singh Mann <Amndeep.dev@gmail.com> Please read License.txt for full license information.
 */

package randomartassignment.expression;

/**
 * AbsoluteValue represents the function "abs" which denotes the distance from 0 to the value in question.
 * 
 * @version 1.0 23 July 2012
 * @author Amndeep Singh Mann
 */
public class AbsoluteValue extends ExpressionParallel
{
	/**
	 * A constructor.
	 */
	public AbsoluteValue()
	{
		super();
	}

	/**
	 * A constructor that accepts a predetermined argument.
	 * 
	 * @param ex
	 *             A <code>ExpressionParallel</code> argument that is a valid subexpression.
	 */
	public AbsoluteValue(ExpressionParallel ex)
	{
		super(ex);
	}

	/**
	 * The solution to this expression when provided with values for variables. In this case, the function "abs" applied to this value's argument.
	 * 
	 * @see randomartassignment.expression.ExpressionParallel#evaluate(double, double)
	 */
	@Override
	public double evaluate(double x, double y)
	{
		return Math.abs(expr.getFirst().evaluate(x, y));
	}

	/**
	 * Gets a value that help determines the number of arguments it needs when creating subexpressions for this expression.
	 * 
	 * @return An integer that is equal to 1 (for one argument).
	 * 
	 * @see randomartassignment.expression.ExpressionParallel#howManyArguments()
	 */
	@Override
	public int howManyArguments()
	{
		return 1;
	}

	/**
	 * A representation of this expression that consists of the function call with the proper use of parenthesis.
	 * 
	 * @see randomartassignment.expression.ExpressionParallel#toString()
	 */
	@Override
	public String toString()
	{
		return "abs(" + expr.getFirst().toString() + ")";
	}

}
