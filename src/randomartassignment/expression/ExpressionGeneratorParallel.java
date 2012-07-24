/*
 * Copyright (c) 2012 Amndeep Singh Mann <Amndeep.dev@gmail.com> Please read License.txt for full license information.
 */

package randomartassignment.expression;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * ExpressionGeneratorParallel is essentially a wrapper around ExpressionParallel in that it represents a whole expression along with associated methods that help create it and evaluate it.
 * It has been designed to work in a parallel fashion. In order to use any subclasses of ExpressionParallel, make sure to adjust this class.
 * 
 * @author Amndeep Singh Mann
 * @version 1.0 20 July 2012
 */

public class ExpressionGeneratorParallel
{
	/* In order to use any extensions designed for ExpressionParallel, make sure to add it to the enum. */

	/**
	 * A list of all expressions known to ExpressionGeneratorParallel.
	 * 
	 * @author Amndeep Singh Mann
	 * @version 1.0 20 July 2012
	 */
	public enum ExpressionType
	{
		/**
		 * A representation of a variable.
		 */
		VARIABLE
		{
			ExpressionParallel create()
			{
				return RANDOM.nextBoolean() ? new ExpressionParallel.Variable("X") : new ExpressionParallel.Variable("Y");
			}
		},

		/**
		 * A representation of the multiplication operator.
		 */
		MULTIPLY
		{
			ExpressionParallel create()
			{
				return new ExpressionParallel.Multiply();
			}
		},

		/**
		 * A representation of the averaging operator.
		 */
		AVERAGE
		{
			ExpressionParallel create()
			{
				return new ExpressionParallel.Average();
			}
		},

		/**
		 * A representation of the sine operation.
		 */
		SIN
		{
			ExpressionParallel create()
			{
				return new ExpressionParallel.Sin();
			}
		},

		/**
		 * A representation of the cosine operation.
		 */
		COS
		{
			ExpressionParallel create()
			{
				return new ExpressionParallel.Cos();
			}
		};

		/**
		 * An unmodifiable list that contains the total list of expressions.
		 */
		private static final List<ExpressionType> EXPRESSIONS = Collections.unmodifiableList(Arrays.asList(values()));

		/**
		 * The size of the list of expressions.
		 */
		private static final int SIZE = EXPRESSIONS.size();

		/**
		 * Creates a random expression selected from the list, <code>EXPRESSIONS</code>.
		 * 
		 * @return An <code>ExpressionParallel</code> that has no preset arguments.
		 */
		public static ExpressionParallel generateRandomExpression()
		{
			return EXPRESSIONS.get(RANDOM.nextInt(SIZE)).create();
		}

		/**
		 * The actual <code>ExpressionParallel</code> is created here - it is dependent on the actual method definition as made within the enum definition.
		 * 
		 * @return An <code>ExpressionParallel</code> that has no preset arguments.
		 */
		abstract ExpressionParallel create();
	}

	/**
	 * The root expression upon which all of the other expressions are built.
	 */
	private ExpressionParallel root;

	/**
	 * This object returns random values that help determine in what order expressions are created. Please use it when randomness is required so as to maintain unique but reproducible
	 * creations.
	 */
	static final Random RANDOM = new Random();// Can possibly add an option to give an argument so that you can repeat equations

	/**
	 * A constructor that accepts a value for the maximum depth of the expression - keep in mind that it does not necessarily always reach it.
	 * 
	 * @param expressionDepth
	 *             An integer that represents the maximum possible depth of the expression, it must be between 0 and less than the maximum amount of calls to the stack that are allowed by
	 *             the Java implementation.
	 */
	public ExpressionGeneratorParallel(int expressionDepth)
	{
		root = createExpression(expressionDepth);
	}

	/**
	 * A constructor that accepts a pre-made expression.
	 * 
	 * @param ex
	 *             A fully filled <code>ExpressionParallel</code> tree.
	 */
	public ExpressionGeneratorParallel(ExpressionParallel ex)
	{
		root = ex;
	}

	/**
	 * A test expression that has a predetermined output (shown in "Grayscale check.png").
	 */
	public static void grayscaleTest()
	{
		// sin(pi * sin(pi * sin(pi * (sin(pi * sin(pi * sin(pi * sin(pi * cos(pi * y))))) * cos(pi * sin(pi * cos(pi * avg(sin(pi * y), (x * x)))))))))

		new ExpressionParallel.Sin(new ExpressionParallel.Sin(new ExpressionParallel.Sin(new ExpressionParallel.Multiply(new ExpressionParallel.Sin(new ExpressionParallel.Sin(
		          new ExpressionParallel.Sin(new ExpressionParallel.Sin(new ExpressionParallel.Cos(new ExpressionParallel.Variable("Y")))))), new ExpressionParallel.Cos(
		          new ExpressionParallel.Sin(new ExpressionParallel.Cos(new ExpressionParallel.Average(new ExpressionParallel.Sin(new ExpressionParallel.Variable("Y")),
		                    new ExpressionParallel.Multiply(new ExpressionParallel.Variable("X"), new ExpressionParallel.Variable("X"))))))))));
	}

	/**
	 * Generates a full expression.
	 * 
	 * @param depth
	 *             An integer that is the maximum possible depth that this expression will go, though it may not necessarily reach it.
	 * @return A full expression with subexpressions that have their own subexpressions and so on, with all nodes ending with one or more variables.
	 */
	private ExpressionParallel createExpression(int depth)
	{
		/* If it has hit the maximum depth, make a variable to end the recursion. */
		if(depth == 0)
		{
			return RANDOM.nextBoolean() ? new ExpressionParallel.Variable("X") : new ExpressionParallel.Variable("Y");
		}

		/* Otherwise, reduce the remaining depth and return a full expression, which may be a subexpression for a higher call. */

		depth--;

		/*
		 * If this happens to have a returned value of 0, then it skips going through making subexpressions and just returns itself, otherwise it generates the correct number of arguments,
		 * adds them to itself, and then returns itself.
		 */
		ExpressionParallel e = ExpressionType.generateRandomExpression();

		if(e.howManyArguments() == 1)
		{
			e.addArgument(createExpression(depth));
		}
		else if(e.howManyArguments() == 2)
		{
			e.addArguments(createExpression(depth), createExpression(depth));
		}
		else if(e.howManyArguments() == -1)
		{
			int numExpressions = RANDOM.nextInt(3) + 2;// the plus 2 here is mandatory for it to work, the maximum value can be whatever you want
			for(int i = 0; i < numExpressions; i++)
			{
				e.addArgument(createExpression(depth));
			}
		}

		return e;
	}

	/**
	 * Evaluates the root, using these arguments as the substitutes for the variables in the root.
	 * 
	 * @param setX
	 *             A value between [-1.0, 1.0] that represents a pixel's x-position.
	 * @param setY
	 *             A value between [-1.0, 1.0] that represents a pixel's y-position.
	 * @return A double between [-1.0, 1.0].
	 */
	public double evaluateExpression(double setX, double setY)
	{
		return root.evaluate(setX, setY);
	}

	/**
	 * A representation of the root that is mathematically correct - i.e. using parenthesis to denote precedence and order of operation use and to denote the location of arguments for
	 * functions.
	 * 
	 * @return A string that represents the root.
	 */
	public String toString()
	{
		return root.toString();
	}
}
