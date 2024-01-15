import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GPACalc 
{
	
	class Entry
	{
		private String name;
		private int credits;
		private char grade;
		public Entry(String name, int credits, char grade)
		{
			this.name = name;
			this.credits = credits;
			this.grade = grade;
		}
		public String getName()
		{
			return this.name;
		}
		public int getCredits()
		{
			return this.credits;
		}
		public char getGrade()
		{
			return this.grade;
		}
	}
	public ArrayList<Entry> courses;
	
	public GPACalc()
	{
		this.courses = new ArrayList<Entry>();
	}
	public void readFromFile(String filename)
	{
		try
		{
			File gpaFile = new File(filename);
			Scanner reader = new Scanner(gpaFile);
			reader.nextLine();
			while(reader.hasNextLine())
			{
				String line = reader.nextLine();
				String[]parts = line.split(",");
				char[] grade = parts[2].toCharArray();
				int credit = Integer.parseInt(parts[1]);
				Entry temp = new Entry(parts[0], credit, grade[0]);
				System.out.println(line);
				this.courses.add(temp);
			}
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Error opening file" + filename);
		}
	}
	public double calculate()
	{
		double total_credits = 0;
		double cg_product = 0;
		for(int i = 0; i < this.courses.size(); i++)
		{
			total_credits += this.courses.get(i).getCredits();
			cg_product += getGradeVal(this.courses.get(i).getGrade())*this.courses.get(i).getCredits();;
		}
		return cg_product/total_credits; 
	}
	public double calculateTC()
	{
		double total_credits = 0;
		for(int i = 0; i < this.courses.size(); i++)
		{
			total_credits += this.courses.get(i).getCredits();
		}
		return total_credits; 
	}
	public double calculateCG()
	{
		double cg_product = 0;
		for(int i = 0; i < this.courses.size(); i++)
		{
			cg_product += getGradeVal(this.courses.get(i).getGrade())*this.courses.get(i).getCredits();;
		}
		return cg_product; 
	}
	public int getGradeVal(char val)
	{
		if(val == 'A')
		{
			return 4;
		}
		else if(val == 'B')
		{
			return 3;
		}
		else if(val == 'C')
		{
			return 2;
		}
		else if(val == 'D')
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}
	public static void main(String[]args)
	{
		GPACalc calc = new GPACalc();
		String fn = "C:\\Users\\Owner\\eclipse-workspace\\ThetaAI\\src\\majorclasses.csv";
				//majorclasses.csv";
		//AllCourses.csv
		calc.readFromFile(fn);
		System.out.println(calc.calculate());
	}
}
