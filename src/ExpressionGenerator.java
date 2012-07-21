public class ExpressionGenerator
{
	public static final int VARIABLE = 0;
	public static final int MULTIPLY = 1;
	public static final int AVERAGE = 2;
	public static final int SIN = 3;
	public static final int COS = 4;

	Expression.Variable x, y;

	Expression root;

	public ExpressionGenerator(int expressionDepth) throws Exception
	{
		x = new Expression.Variable("X");
		y = new Expression.Variable("Y");

		root = createExpression(expressionDepth);
	}

	private Expression generateExpression() throws Exception
	{
		int expr = (int) (Math.random() * 5);
		switch (expr)
		{
			case VARIABLE:
				if (Math.random() < 0.5)
					return x;
				else
					return y;
			case MULTIPLY:
				return new Expression.Multiply();
			case AVERAGE:
				return new Expression.Average();
			case SIN:
				return new Expression.Sin();
			case COS:
				return new Expression.Cos();
			default:
				throw new Exception("Broken generation of expression");
		}
	}

	private Expression createExpression(int depth) throws Exception
	{
		if (depth == 0)
		{
			return (Math.random() < 0.5) ? x : y;
		}

		depth--;

		Expression e = generateExpression();

		if (e.howManyArguments() == 1)
		{
			e.addArgument(createExpression(depth));
		}
		else if (e.howManyArguments() == 2)
		{
			int numExpressions = (int) (Math.random() * 3 + 2);
			for (int i = 0; i < numExpressions; i++)
				e.addArgument(createExpression(depth));
		}

		return e;
	}

	public double evaluateExpression(double setX, double setY)
	{
		return evaluateExpression(root, setX, setY);
	}

	public double evaluateExpression(Expression e, double setX, double setY)
	{
		x.changeSelf(setX);
		y.changeSelf(setY);
		return e.evaluate();
	}

	public String toString()
	{
		return root.toString();
	}

	public Expression grayscaleTest()
	{
		// sin(pi * sin(pi * sin(pi * (sin(pi * sin(pi * sin(pi * sin(pi * cos(pi * y))))) * cos(pi * sin(pi * cos(pi * avg(sin(pi * y), (x * x)))))))))

		return new Expression.Sin(new Expression.Sin(new Expression.Sin(new Expression.Multiply(new Expression.Sin(new Expression.Sin(new Expression.Sin(new Expression.Sin(
		          new Expression.Cos(y))))), new Expression.Cos(new Expression.Sin(new Expression.Cos(new Expression.Average(new Expression.Sin(y), new Expression.Multiply(x, x)))))))));
	}
}
