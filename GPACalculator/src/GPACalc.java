import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GPACalc
{

	class Entry
	{
		private String name;
		private double credits;
		private String grade;
		public Entry(String name, double credits, String grade)
		{
			this.name = name;
			this.credits = credits;
			this.grade = grade;
		}
		public String getName()
		{
			return this.name;
		}
		public double getCredits()
		{
			return this.credits;
		}
		public String getGrade()
		{
			return this.grade;
		}
	}
	public ArrayList<Entry> courses;

	public GPACalc()
	{
		this.courses = new ArrayList<>();
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
				String grade = parts[2];
				double credit = Double.parseDouble(parts[1]);
				Entry temp = new Entry(parts[0], credit, grade);
				System.out.println(line);
				this.courses.add(temp);
			}
			reader.close();
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
		for (Entry element : this.courses) {
			total_credits += element.getCredits();
			cg_product += getGradeVal(element.getGrade())*element.getCredits();
		}
		return cg_product/total_credits;
	}
	public double calculateTC()
	{
		double total_credits = 0;
		for (Entry element : this.courses) {
			total_credits += element.getCredits();
		}
		return total_credits;
	}
	public double calculateCG()
	{
		double cg_product = 0;
		for (Entry element : this.courses) {
			cg_product += getGradeVal(element.getGrade())*element.getCredits();
		}
		return cg_product;
	}
	public double getGradeVal(String val)
	{
		if(val.equals("A"))
		{
			return 4;
		}
		else if(val.equals("-A"))
		{
			return 3.7;
		}
		else if(val.equals("+B"))
		{
			return 3.3;
		}
		else if(val.equals("B"))
		{
			return 3;
		}
		else if(val.equals("-B"))
		{
			return 2.7;
		}
		else if(val.equals("+C"))
		{
			return 2.3;
		}
		else if(val.equals("C"))
		{
			return 2;
		}
		else if(val.equals("+D"))
		{
			return 1.3;
		}
		else if(val.equals("D"))
		{
			return 1;
		}
		else if(val.equals("-D"))
		{
			return 0.7;
		}
		else
		{
			return 0;
		}
	}
	public static void main(String[]args)
	{
		GPACalc calc = new GPACalc();
		String fn = "C:\\Users\\chr1s\\git\\git\\GPACalculator\\GPACalculator\\src\\majorclasses.csv";
				//majorclasses.csv";
		//AllCourses.csv
		calc.readFromFile(fn);
		System.out.println(calc.calculate());
	}
}
