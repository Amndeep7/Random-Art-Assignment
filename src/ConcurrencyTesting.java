
public class ConcurrencyTesting
{
	static double value;
	public static class ConcurrencyTest implements Runnable
	{
		double val;
		int depth;
		static int addition;
		public ConcurrencyTest(double a)
		{
			val = a;
			depth = 5;
			System.out.println("constructor " + val + " " + depth + " " + addition + " ");
		}
		
		public double change(int d, double v)
		{
			System.out.println("change " + d + " " + v + " " + addition);
			if(d == 0)
				return val+addition;
			d--;
			return v * change(d, v);
		}
		
		public void setDepth(int d)
		{
			System.out.println("1 depth " + depth + " d " + d);
			depth = d;
			System.out.println("2 depth " + depth + " d " + d);
		}
		
		public static void setAddition(int a)
		{
			System.out.println("1 addition " + addition + " a " + a);
			addition = a;
			System.out.println("2 addition " + addition + " a " + a);
		}
		
		public void run()
		{
			System.out.println("1 run " + depth + " " + value);
			value = change(depth, val);
			System.out.println("2 run " + depth + " " + value);
		}
	}

	public static void main(String[] args)
	{
		value = -1;
		
		System.out.println("value " + value);

		ConcurrencyTest.setAddition(3);
		
		ConcurrencyTest test = new ConcurrencyTest(2);
		test.setDepth(1);
		Thread thread = new Thread(test);
		thread.start();
		
		try
          {
	          thread.join();
          }
          catch (InterruptedException e)
          {
	          e.printStackTrace();
          }
		System.out.println("value " + value);
	}

}
