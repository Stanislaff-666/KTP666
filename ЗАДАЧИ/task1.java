import static java.lang.System.*;
import java.util.Scanner;
public class task1{
	public static void main(String[] args) 
	{
		out.println("1. Vvod 2:");
		String v=vvod(); // Ввод чисел
		String[] s=v.split(" ");
		int a=convertint(s[0]);
		int b=convertint(s[1]);
		out.println("Remainder when dividing "+a+" and "+ b+"= "+ remainder(a, b));//Вывод результата остатка от деления

		out.println("2. Vvod 2:");
		v=vvod();// ввод стороны и высоты
		s=v.split(" ");
		a=convertint(s[0]);
		b=convertint(s[1]);
		out.println("Area of a triangle where side= "+a+" height= "+ b+" S= "+ triArea(a, b));//вывод площади треугольника

		out.println("3. Vvod 3:");
		v=vvod();//ввод количества животных
		s=v.split(" ");
		a=convertint(s[0]);
		b=convertint(s[1]);
		int c=convertint(s[2]);
		out.println("Number of legs "+ animals(a, b, c));//вывод количества ног

		out.println("4. Vvod 3:");
		v=vvod();//ввод данных
		s=v.split(" ");
		a=convertint(s[0]);
		b=convertint(s[1]);
		c=convertint(s[2]);
		out.println(a+"*"+ b+ ">"+ c+ "  "+ profitanleGamble(a, b, c));//вывод результата

		out.println("5. Vvod 3:");
		v=vvod();//ввод данных
		s=v.split(" ");
		c=convertint(s[0]);
		a=convertint(s[1]);
		b=convertint(s[2]);
		out.println(a+"?"+ b+ "="+ c+ "  "+ operantion(c, a, b));//вывод результата

		out.println("6. Vvod char:");
		v=vvod();//ввод данных
		out.println(v.charAt(0)+ "= "+ ctoa(v.charAt(0)));//вывод результата

		out.println("7. Vvod 3 1:");
		v=vvod();//ввод данных
		s=v.split(" ");
		a=convertint(s[0]);
		out.println("Summa= "+ addUpTo(a));//вывод результата

		out.println("8. Vvod 2:");
		v=vvod();//ввод данных
		s=v.split(" ");
		a=convertint(s[0]);
		b=convertint(s[1]);
		out.println("= "+ nextEdge(a, b));//вывод результата

		out.println("9. Vvod mass:");
		v=vvod();//ввод данных
		s=v.split(" ");
		int[] mass=convertmass(s);
		out.println("= "+ sumOfCubes(mass));//вывод результата

		out.println("10. Vvod 3:");
		v=vvod();//ввод данных
		s=v.split(" ");
		a=convertint(s[0]);
		b=convertint(s[1]);
		c=convertint(s[2]);
		out.println(a+"^"+ b+ "%"+ c+ "==0  "+ abcmath(a, b, c));//вывод результата

	}
	public static int[] convertmass(String[] a)
	{
		int[] mass=new int[a.length];
		for(int i=0; i<a.length; i++)
		{
			mass[i]=Integer.parseInt(a[i]);
		}
		return mass;
	}
	public static int convertint(String l)
	{
		return Integer.parseInt(l);
	}

	public static String vvod()
	{
		Scanner m=new Scanner(System.in);
		return m.nextLine();
	}

	public static int remainder(int a, int b) //задание 1
	{
		return a%b;
	}
	public static int triArea(int a, int b)//задание 2
	{
		return (a*b)/2;
	}
	public static int animals(int chickens, int cows, int pigs)//задание 3
	{
		return (chickens*2)+(cows*4)+(pigs*4);
	}
	public static boolean profitanleGamble(double prob, double prize, double pay)//задание 4
	{
		return (prob*prize)>pay ? true : false;
	}
	public static String operantion(int N, int a, int b)//задание 5
	{
		if((a+b)==N)
			return "added";
		else if (Math.abs(a-b)==N)
			return "subtracted";
		else if((a/b)==N)
			return "divide";
		else if((a*b)==N)
			return "multiply";
		else
			return "None";
	}

	public static int ctoa(char a)//задание 6
	{
		return a;
	}

	public static int addUpTo(int a)//задание 7
	{
		int sum=0;
		while(a>0)
		{
			sum=sum+a;
			a--;
		}
		return sum;
	}
	public static int nextEdge(int a, int b)//задание 8
	{
		return (a+b)-1;
	}
	public static int sumOfCubes(int[] a)//задание 9
	{
		int sum=0;
		for (int x:a)
		{
			sum=sum+(x*x*x);
		}
		return sum;
	}
	public static boolean abcmath(int a, int b, int c)//задание 10
	{
		return (Math.pow(a, b))%c==0 ? true : false;
	}
}
