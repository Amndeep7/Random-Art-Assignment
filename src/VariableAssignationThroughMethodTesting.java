
public class VariableAssignationThroughMethodTesting
{
	static int aVal, bVal;
	public static void change(int a, int b)
	{
		b = a+2;
	}
	public static void change2()
	{
		bVal = aVal+2;
	}
	public static void main(String[] args)
	{
		//Does not work
		int aValue = 2;
		int bValue = 0;
		System.out.println(aValue + " " + bValue);
		change(aValue, bValue);
		System.out.println(aValue + " " + bValue);
		
		//Does work
		aVal = 2;
		bVal = 0;
		System.out.println(aVal + " " + bVal);
		change2();
		System.out.println(aVal + " " + bVal);
	}
}
