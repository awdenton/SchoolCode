package mealp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class mealInfo {
	
	String meals[][][];
	double feedback[][][];
	
	public mealInfo() throws FileNotFoundException
	{
		Scanner scanner = new Scanner(new File("/Users/Sophie/NetBeansProjects/projectTest2/src/mealp/meal.csv"));
		//scanner.useDelimiter(",");
		//int count=0;
		meals=new String[7][3][0];
		ArrayList<String> temp = new ArrayList();
		int day=0;
		int meal=0;
		while(scanner.hasNextLine())
		{
			meals[day][meal]=scanner.nextLine().split(",");
			meal++;
			if(meal==3)
			{
				meal=0;
				day++;
			}
		}
		scanner.close();
		scanner = new Scanner(new File("/Users/Sophie/NetBeansProjects/projectTest2/src/mealp/feedback.csv"));
		feedback=new double[3][7][3];
		int count=0;
		while(scanner.hasNextLine())
		{
			String temp1[]= scanner.nextLine().split(",");
			double rat[]=new double[21];
			for(int i=0;i<7;i++)
			{
				for(int j=0;j<3;j++)
				{
					feedback[count][i][j]=Double.parseDouble(temp1[i*3+j]);
				}
			}
			
		}
		//System.out.println(feedback[2][2][2]);
	}
	
	public String[] getMealPlan (String day, String meal)
	{
		int m=0;
		int d=0;
		switch(meal){
		case "breakfast":m=0;
			break;
		case "lunch":m=1;
			break;
		case "dinner":m=2;
			break;
		}
		switch(day){
		case "sunday":d=0;
			break;
		case "monday":d=1;
			break;
		case "tuesday":d=2;
			break;
		case "wednesday":d=3;
			break;
		case "thursday":d=4;
			break;
		case "friday":d=5;
			break;
		case "saturday":d=6;
			break;
		}
		return meals[d][m];
	}
	
	public void setMealPlan (String[][][] meal) throws IOException
	{
		File cvs=new File("/Users/Sophie/NetBeansProjects/projectTest2/src/mealp/meal.csv");
		cvs.delete();
		cvs=new File("/Users/Sophie/NetBeansProjects/projectTest2/src/mealp/meal.csv");
		FileWriter f=new FileWriter(cvs);
		for(int x=0;x<7;x++)
		{
			for(int y=0;y<3;y++)
			{
				String temp="";
				for(String z:meal[x][y])
				{
					temp+=z+",";
				}
				f.write(temp+"\n");
			}
		}
		f.close();
	}
	
	public double getFeedback(String day, String meal)
	{
		return 0;
	}
	
	public void setFeedback(double feed[][][]) throws IOException
	{
		File cvs=new File("/Users/Sophie/NetBeansProjects/projectTest2/src/mealp/feedback.csv");
		cvs.delete();
		cvs=new File("/Users/Sophie/NetBeansProjects/projectTest2/src/mealp/feedback.csv");
		FileWriter f=new FileWriter(cvs);
		for(int w=0;w<3;w++)
		{
			String temp="";
			for(int x=0;x<7;x++)
			{
				for(int y=0;y<3;y++)
				{
					temp+=feed[w][x][y]+",";				
				}
			}
			f.write(temp+"\n");
		}
		f.close();
		
	}
}
