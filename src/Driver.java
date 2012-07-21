import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Driver
{
	public static void main(String[] args) throws Exception
	{
		boolean doingGrayscale = true;
		int numMatrices = 1;
		int depth = (int) (Math.random() * 5);
		int width = 301;
		int height = 301;
		// Command-line input

		if (args.length > 0)
		{
			try
			{
				depth = Integer.parseInt(args[0]);
				width = Integer.parseInt(args[1]);
				height = Integer.parseInt(args[2]);
				doingGrayscale = Boolean.parseBoolean(args[3]);
				if (!doingGrayscale)
					numMatrices = 3;
			}
			catch (NumberFormatException e)
			{
			}
		}

		// Graphic input

		System.out.println((doingGrayscale) ? "Making equation..." : "Making equations...");
		ExpressionGenerator[] e = new ExpressionGenerator[numMatrices];
		for (int a = 0; a < numMatrices; a++)
			e[a] = new ExpressionGenerator(depth);
		// Expression grayscaleTest = e[0].grayscaleTest();
		String[] equation = new String[numMatrices];
		for (int a = 0; a < numMatrices; a++)
			equation[a] = e[a].toString();
		for (String s : equation)
			System.out.println(s);

		System.out.println("Making raw matrix...");
		double xinterval = 2.0 / (width - 1);// the minus one is so that the end result is [-1,1] and not [-1,1)
		double yinterval = 2.0 / (height - 1);

		double[][][] raw = new double[numMatrices][height][width];

		int i, j, k;
		for (k = 0; k < numMatrices; k++)
		{
			String color = "";
			switch (k)
			{
				case 0:
					color = "red";
					break;
				case 1:
					color = "green";
					break;
				case 2:
					color = "blue";
					break;
				default:
					throw new Exception("Broken color picker in debugging output");
			}
			if (numMatrices == 1)
				color = "gray";
			for (j = 0; j < height; j++)
			{
				if (j % 10 == 0)
					System.out.println("Making column " + (j + 1) + " out of " + height + " in the " + color + " raw matrix...");
				for (i = 0; i < width; i++)
					raw[k][j][i] = e[k].evaluateExpression(i * xinterval - 1.0, j * yinterval - 1.0);
				// raw[k][j][i] = e[k].evaluateExpression(grayscaleTest, i * xinterval - 1.0, j * yinterval - 1.0);
			}
		}

		System.out.println("Making picture matrix...");
		int[][][] colorvals = new int[numMatrices][height][width];
		for (k = 0; k < numMatrices; k++)
		{
			String color = "";
			switch (k)
			{
				case 0:
					color = "red";
					break;
				case 1:
					color = "green";
					break;
				case 2:
					color = "blue";
					break;
				default:
					throw new Exception("Broken color picker in debugging output");
			}
			if (numMatrices == 1)
				color = "gray";
			System.out.println("Working on the " + color + " matrix...");
			for (j = 0; j < height; j++)
			{
				if (j % 10 == 0)
					System.out.println("Making column " + (j + 1) + " out of " + height + " in the " + color + " picture matrix...");
				for (i = 0; i < width; i++)
					colorvals[k][j][i] = (int) ((raw[k][j][i] + 1.0) * 255 / 2.0);
			}
		}

		System.out.println("Making picture...");
		String beginningOfEquation = (equation[0].length() < 10) ? equation[0] : equation[0].substring(0, 10);
		String typeOfColor = (doingGrayscale) ? "grayscale" : "color";
		String filename = System.nanoTime() + "-" + typeOfColor + "-width" + width + "-height" + height + "-beg-of-equation" + beginningOfEquation;
		String filetype = (doingGrayscale) ? ".pgm" : ".ppm";
		PrintWriter printer = new PrintWriter(new BufferedWriter(new FileWriter(filename + filetype)));
		if (doingGrayscale)
			printer.println("P2");
		else
			printer.println("P3");
		printer.println(width + " " + height);
		printer.println(255);

		for (j = 0; j < height; j++)
		{
			for (i = 0; i < width; i++)
			{
				for (k = 0; k < numMatrices; k++)
					printer.print(colorvals[k][j][i] + " ");
			}
			printer.println();
		}

		for (String s : equation)
			printer.println("# " + s);

		printer.flush();
		printer.close();
	}
}
