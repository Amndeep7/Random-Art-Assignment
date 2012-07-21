import java.util.LinkedList;

public abstract class Expression
{
	LinkedList<Expression> expr;

	public Expression()
	{
		expr = new LinkedList<Expression>();
	}
	
	public Expression(Expression ... ex)
	{
		this();
		addArguments(ex);
	}

	public void addArgument(Expression ex)
	{
		expr.add(ex);
	}
	
	public void addArguments(Expression ... ex)
	{
		for(Expression e : ex)
			expr.add(e);
	}
	
	public Expression getArgument(int index)
	{
		return expr.get(index);
	}

	public abstract double evaluate();

	public abstract int howManyArguments();

	public abstract String toString();

	public static class Variable extends Expression
	{
		String name;

		double self;

		public Variable(String n)
		{
			name = n;
			self = 0;
		}

		public void changeSelf(double s)
		{
			self = s;
		}

		public double evaluate()
		{
			return self;
		}

		public int howManyArguments()
		{
			return 0;
		}

		public String toString()
		{
			return name;
		}
	}

	public static class Multiply extends Expression
	{
		public Multiply()
		{
			super();
		}
		public Multiply(Expression ... ex)
		{
			super(ex);
		}
		
		public double evaluate()
		{
			double product = 1;
			for (Expression e : expr)
				product *= e.evaluate();
			return product;
		}

		public int howManyArguments()
		{
			return 2;
		}

		public String toString()
		{
			String ret = "";
			for (Expression e : expr)
				ret += e.toString() + " * ";
			ret = ret.substring(0, ret.length() - 3);
			return ret;
		}
	}

	public static class Average extends Expression
	{
		public Average()
		{
			super();
		}
		public Average(Expression ... ex)
		{
			super(ex);
		}
		
		public double evaluate()
		{
			double total = 0;
			for (Expression e : expr)
				total += e.evaluate();
			total /= expr.size();
			return total;
		}

		public int howManyArguments()
		{
			return 2;
		}

		public String toString()
		{
			String ret = "avg(";
			for (Expression e : expr)
				ret += e.toString() + ", ";
			ret = ret.substring(0, ret.length() - 2) + ")";
			return ret;
		}
	}

	public static class Sin extends Expression
	{
		public Sin()
		{
			super();
		}
		public Sin(Expression ... ex)
		{
			super(ex);
		}
		
		public double evaluate()
		{
			return Math.sin(Math.PI * expr.getFirst().evaluate());
		}

		public int howManyArguments()
		{
			return 1;
		}

		public String toString()
		{
			return "sin(pi * " + expr.getFirst().toString() + ")";
		}
	}

	public static class Cos extends Expression
	{
		public Cos()
		{
			super();
		}
		public Cos(Expression ... ex)
		{
			super(ex);
		}
		
		public double evaluate()
		{
			return Math.cos(Math.PI * expr.getFirst().evaluate());
		}

		public int howManyArguments()
		{
			return 1;
		}

		public String toString()
		{
			return "cos(pi * " + expr.getFirst().toString() + ")";
		}
	}
}