package mealp;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class foodInfo {
	String name[];
	int calories[];
	String servSize[];
	double rating[][];
	
	public foodInfo() throws FileNotFoundException
	{
		Scanner scanner = new Scanner(new File("/Users/Sophie/NetBeansProjects/projectTest2/src/mealp/food.csv"));
		int line=0;
		while(scanner.hasNextLine())
		{
			line++;
			scanner.nextLine();
		}
		name=new String[line];
		calories=new int[line];
		servSize=new String[line];
		rating=new double[3][10];
		scanner.close();
		scanner = new Scanner(new File("/Users/Sophie/NetBeansProjects/projectTest2/src/mealp/food.csv"));
		scanner.useDelimiter(",");
		int count=0;
		while(scanner.hasNext())
		{
			name[count]=scanner.next();
			calories[count]=Integer.parseInt(scanner.next());
			servSize[count]=scanner.next();
			rating[0][count]=Double.parseDouble(scanner.next());
			count++;
		}
		scanner.close();
		scanner = new Scanner(new File("/Users/Sophie/NetBeansProjects/projectTest2/src/mealp/rate.csv"));
		scanner.useDelimiter(",");
		count=0;
		while(scanner.hasNextLine())
		{
			String arr[]=scanner.nextLine().split(",");
			for(int i=0;i<arr.length;i++)
				rating[count][i]=Double.parseDouble(arr[i]);
			count++;
		}
		
	}
	
	public String[] getFoods()
	{
		return name;
	}
	
	public void updateRating(double arr[][]) throws IOException
	{
		File cvs=new File("\\Users\\Sophie\\NetBeansProjects\\projectTest2\\src\\mealp\\rate.csv");
		cvs.delete();
		cvs=new File("\\Users\\Sophie\\NetBeansProjects\\projectTest2\\src\\mealp\\rate.csv");
		FileWriter f=new FileWriter(cvs);
		for(double[] x:rating)
		{
			String temp="";
			for(double z:x)
			{
				temp+=x.toString()+",";
			}
			f.write(temp+"\n");
		}
		f.close();
	}
	
	public double[][] getRatings()
	{
		return rating;		
	}
	public static void main() throws FileNotFoundException
	{
		foodInfo x = new foodInfo();
	}
}
