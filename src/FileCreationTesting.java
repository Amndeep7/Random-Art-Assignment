import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class FileCreationTesting
{
	public static void main(String[] args)
	{
		PrintWriter printer = null;
		try
          {
			File file = new File(System.getProperty("user.home")+"/TestingThisThingHere/");
			file.mkdirs();
	          printer = new PrintWriter(new BufferedWriter(new FileWriter(file.getPath()+"/output.txt")));
          }
          catch (IOException e)
          {
	          e.printStackTrace();
	          System.exit(1);
          }
		printer.println("Hello, World!");
		printer.flush();
		printer.close();
	}

}
