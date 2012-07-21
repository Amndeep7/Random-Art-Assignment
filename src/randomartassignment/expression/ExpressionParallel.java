/*
 * @ExpressionParallel 1.0 7/16/2012
 * 
 * Copyright (c) 2012 Amndeep Singh Mann <Amndeep.vass@gmail.com> Please read License.txt for full license information.
 */

package randomartassignment.expression;

import java.util.LinkedList;

/**
 * <code>ExpressionParallel</code> represents a mathematical "expression," by which is meant either a representation of a variable, a mathematical operator, or a mathematical function (such
 * as those of trigonometry). It has been designed to work in a parallel fashion. Subclass this class in order to define more specific types of each.
 * 
 * @author Amndeep Singh Mann
 * @version 1.0 20 July 2012
 */
public abstract class ExpressionParallel
{
	/**
	 * Keeps track of all of the arguments, otherwise referred to as subexpressions, for this expression.
	 */
	protected LinkedList<ExpressionParallel> expr;

	/**
	 * Class constructor.
	 */
	public ExpressionParallel()
	{
		expr = new LinkedList<ExpressionParallel>();
	}

	/**
	 * Class constructor specifying subexpressions for this expression.
	 * 
	 * @param ex
	 *             Any number of <code>ExpressionParallel</code> arguments which are valid subexpressions.
	 */
	public ExpressionParallel(ExpressionParallel ... ex)
	{
		this();
		addArguments(ex);
	}

	/**
	 * Adds an <code>ExpressionParallel</code> subexpression to the list of expressions that are being kept track of.
	 * 
	 * @param ex
	 *             A <code>ExpressionParallel</code> subexpression.
	 */
	public void addArgument(ExpressionParallel ex)
	{
		expr.add(ex);
	}

	/**
	 * Adds any number of <code>ExpressionParallel</code> subexpressions to the list of expressions that are being kept track of.
	 * 
	 * @param ex
	 *             Any number of <code>ExpressionParallel</code> arguments which are valid subexpressions.
	 */
	public void addArguments(ExpressionParallel ... ex)
	{
		for(ExpressionParallel e : ex)
		{
			expr.add(e);
		}
	}

	/**
	 * Returns the <code>ExpressionParallel</code> argument specified by the index from the list holding the subexpressions for this expression.
	 * 
	 * @param index
	 *             The index of the argument desired.
	 * @return It returns an <code>ExpressionParallel</code> subexpression.
	 * @throws IndexOutOfBoundsException
	 *              If the index is less than 0 or greater than or equal to the current size of the list.
	 */
	public ExpressionParallel getArgument(int index)
	{
		return expr.get(index);
	}

	/**
	 * The solution to this expression when provided with values for variables.
	 * 
	 * @param x
	 *             A value between [-1.0, 1.0] that represents a pixel's x-position.
	 * @param y
	 *             A value between [-1.0, 1.0] that represents a pixel's y-position.
	 * @return A double between [-1.0, 1.0].
	 */
	public abstract double evaluate(double x, double y);

	/**
	 * Gets a value that help determines the number of arguments it needs when creating subexpressions for this expression.
	 * 
	 * @return An integer that is equal to 0 (for no arguments), 1 (for one argument), or 2 (for any number of arguments, the minimum of which is two).
	 */
	public abstract int howManyArguments();

	/**
	 * A representation of this expression that is mathematically correct - i.e. using parenthesis to denote precedence and order of operation use and to denote the location of arguments
	 * for functions.
	 * 
	 * @return A string that represents this expression.
	 */
	public abstract String toString();

	/**
	 * Variable represents a mathematical variable - to be replaced with actual values when evaluated as part of a larger expression. It has been designed to work in a parallel fashion.
	 * 
	 * @version 1.0 20 July 2012
	 * @author Amndeep Singh Mann
	 */
	public static class Variable extends ExpressionParallel
	{
		/**
		 * Used to determine which type of variable it is, i.e. "X" or "Y".
		 */
		protected String name;

		/**
		 * A constructor.
		 * 
		 * @param n
		 *             A string that represents the name - should be either "X" or "Y".
		 */
		public Variable(String n)
		{
			super();
			name = n;
		}

		/**
		 * The solution to this expression when provided with values for variables. In this case, one of the arguments depending on the name of the variable.
		 * 
		 * @param x
		 *             A value between [-1.0, 1.0] that represents a pixel's x-position.
		 * @param y
		 *             A value between [-1.0, 1.0] that represents a pixel's y-position.
		 * @return A double between [-1.0, 1.0].
		 */
		public double evaluate(double x, double y)
		{
			return (name.equals("X")) ? x : y;
		}

		/**
		 * Gets a value that help determines the number of arguments it needs when creating subexpressions for this expression.
		 * 
		 * @return An integer that is equal to 0 (for no arguments).
		 */
		public int howManyArguments()
		{
			return 0;
		}

		/**
		 * A representation of this expression, it is either "X" or "Y".
		 * 
		 * @return A string that represents this expression.
		 */
		public String toString()
		{
			return name;
		}
	}

	/**
	 * Multiply represents the operator *. It has been designed to work in a parallel fashion.
	 * 
	 * @version 1.0 20 July 2012
	 * @author Amndeep Singh Mann
	 */

	public static class Multiply extends ExpressionParallel
	{
		/**
		 * A constructor.
		 */
		public Multiply()
		{
			super();
		}

		/**
		 * A constructor that accepts predetermined arguments.
		 * 
		 * @param ex
		 *             Any number of <code>ExpressionParallel</code> arguments which are valid subexpressions.
		 */
		public Multiply(ExpressionParallel ... ex)
		{
			super(ex);
		}

		/**
		 * The solution to this expression when provided with values for variables. In this case, all of its subexpressions multiplied together.
		 * 
		 * @param x
		 *             A value between [-1.0, 1.0] that represents a pixel's x-position.
		 * @param y
		 *             A value between [-1.0, 1.0] that represents a pixel's y-position.
		 * @return A double between [-1.0, 1.0].
		 */
		public double evaluate(double x, double y)
		{
			double product = 1;
			for(ExpressionParallel e : expr)
			{
				product *= e.evaluate(x, y);
			}
			return product;
		}

		/**
		 * Gets a value that help determines the number of arguments it needs when creating subexpressions for this expression.
		 * 
		 * @return An integer that is equal to 2 (for any number of arguments, the minimum of which is two).
		 */
		public int howManyArguments()
		{
			return 2;
		}

		/**
		 * A representation of this expression that consists each subexpression separated with spaces and "*"
		 * 
		 * @return A string that represents this expression.
		 */
		public String toString()
		{
			String ret = "";
			for(ExpressionParallel e : expr)
			{
				ret += e.toString() + " * ";
			}
			ret = ret.substring(0, ret.length() - 3);
			return ret;
		}
	}

	/**
	 * Average represents the operation of averaging (i.e.<!-- --> the mean). It has been designed to work in a parallel fashion.
	 * 
	 * @version 1.0 20 July 2012
	 * @author Amndeep Singh Mann
	 */
	public static class Average extends ExpressionParallel
	{
		/**
		 * A constructor.
		 */
		public Average()
		{
			super();
		}

		/**
		 * A constructor that accepts predetermined arguments.
		 * 
		 * @param ex
		 *             Any number of <code>ExpressionParallel</code> arguments which are valid subexpressions.
		 */
		public Average(ExpressionParallel ... ex)
		{
			super(ex);
		}

		/**
		 * The solution to this expression when provided with values for variables. In this case, all of its subexpressions are added together, the sum is then divided by the total number
		 * of arguments.
		 * 
		 * @param x
		 *             A value between [-1.0, 1.0] that represents a pixel's x-position.
		 * @param y
		 *             A value between [-1.0, 1.0] that represents a pixel's y-position.
		 * @return A double between [-1.0, 1.0].
		 */
		public double evaluate(double x, double y)
		{
			double total = 0;
			for(ExpressionParallel e : expr)
			{
				total += e.evaluate(x, y);
			}
			total /= expr.size();
			return total;
		}

		/**
		 * Gets a value that help determines the number of arguments it needs when creating subexpressions for this expression.
		 * 
		 * @return An integer that is equal to 2 (for any number of arguments, the minimum of which is two).
		 */
		public int howManyArguments()
		{
			return 2;
		}

		/**
		 * A representation of this expression that consists of the function name and then with each subexpression within a set of parenthesis, separated with commas and spaces.
		 * 
		 * @return A string that represents this expression.
		 */
		public String toString()
		{
			String ret = "avg(";
			for(ExpressionParallel e : expr)
			{
				ret += e.toString() + ", ";
			}
			ret = ret.substring(0, ret.length() - 2) + ")";
			return ret;
		}
	}

	/**
	 * Sin represents the trigonometric operator sine. It has been designed to work in a parallel fashion.
	 * 
	 * @version 1.0 20 July 2012
	 * @author Amndeep Singh Mann
	 */
	public static class Sin extends ExpressionParallel
	{
		/**
		 * A constructor.
		 */
		public Sin()
		{
			super();
		}

		/**
		 * A constructor that accepts a predetermined argument.
		 * 
		 * @param ex
		 *             Any <code>ExpressionParallel</code> argument which is a valid subexpression.
		 */
		public Sin(ExpressionParallel ex)
		{
			super(ex);
		}

		/**
		 * The solution to this expression when provided with values for variables. In this case, the sin of pi times this values argument is returned.
		 * 
		 * @param x
		 *             A value between [-1.0, 1.0] that represents a pixel's x-position.
		 * @param y
		 *             A value between [-1.0, 1.0] that represents a pixel's y-position.
		 * @return A double between [-1.0, 1.0].
		 */
		public double evaluate(double x, double y)
		{
			return Math.sin(Math.PI * expr.getFirst().evaluate(x, y));
		}

		/**
		 * Gets a value that help determines the number of arguments it needs when creating subexpressions for this expression.
		 * 
		 * @return An integer that is equal to 1 (for one argument).
		 */
		public int howManyArguments()
		{
			return 1;
		}

		/**
		 * A representation of an expression that is mathematically correct - i.e. using parenthesis to denote precedence and order of operation use and to denote the location of arguments
		 * for functions.
		 * 
		 * @return A string that represents this expression.
		 */
		public String toString()
		{
			return "sin(pi * " + expr.getFirst().toString() + ")";
		}
	}

	/**
	 * Cos represents the trigonometric operation cosine. It has been designed to work in a parallel fashion.
	 * 
	 * @version 1.0 20 July 2012
	 * @author Amndeep Singh Mann
	 */
	public static class Cos extends ExpressionParallel
	{
		/**
		 * A constructor.
		 */
		public Cos()
		{
			super();
		}

		/**
		 * A constructor that accepts a predetermined argument.
		 * 
		 * @param ex
		 *             Any <code>ExpressionParallel</code> argument which is a valid subexpression.
		 */
		public Cos(ExpressionParallel ex)
		{
			super(ex);
		}

		/**
		 * The solution to this expression when provided with values for variables. In this case, the cos of pi times this values argument is returned.
		 * 
		 * @param x
		 *             A value between [-1.0, 1.0] that represents a pixel's x-position.
		 * @param y
		 *             A value between [-1.0, 1.0] that represents a pixel's y-position.
		 * @return A double between [-1.0, 1.0].
		 */
		public double evaluate(double x, double y)
		{
			return Math.cos(Math.PI * expr.getFirst().evaluate(x, y));
		}

		/**
		 * Gets a value that help determines the number of arguments it needs when creating subexpressions for this expression.
		 * 
		 * @return An integer that is equal to 1 (for one argument).
		 */
		public int howManyArguments()
		{
			return 1;
		}

		/**
		 * A representation of an expression that is mathematically correct - i.e. using parenthesis to denote precedence and order of operation use and to denote the location of arguments
		 * for functions.
		 * 
		 * @return A string that represents this expression.
		 */
		public String toString()
		{
			return "cos(pi * " + expr.getFirst().toString() + ")";
		}
	}
}
