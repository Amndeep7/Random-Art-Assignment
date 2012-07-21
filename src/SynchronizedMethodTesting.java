import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class SynchronizedMethodTesting
{
	static int counts;
	public static class WasteTime implements Runnable
	{
		public void run()
		{
			try
               {
	               Thread.sleep(1000);
               }
               catch (InterruptedException e)
               {
               }
			int max = (int)(Math.random()*10000);
			for(int x = 0; x < max; x++)
			{
				Math.sqrt((int)(Math.random()*1000));
			}
			updateCounter();
		}
	}
	public static synchronized void updateCounter()
	{
		counts++;
	}
	public static void main(String[] args)
	{
		System.out.println("Starting...");
		counts = 0;
		int maximum = 1000;
		ExecutorService pool = Executors.newCachedThreadPool();
		for(int x = 0; x < maximum; x++)
		{
			if(x%100 == 0)
				System.out.println("Setting up " + x + " out of " + maximum);
			pool.execute(new WasteTime());
		}
		
		System.out.println("Finishing up...");
		pool.shutdown();
		while(!pool.isTerminated())
		{
			System.out.println("Still finishing up - " + (int)(((counts/((double)maximum))*100)) + "%");
			try
               {
	               Thread.sleep(200);
               }
               catch (InterruptedException e)
               {
               }
		}
		System.out.println("Done");
	}

}
