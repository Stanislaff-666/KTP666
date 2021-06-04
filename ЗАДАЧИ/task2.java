import static java.lang.System.*;
import java.util.Locale;
import java.util.regex.*;

public class task2{
	public static void main(String[] args) {
		out.println("number 1");
		out.println(oppositeHouse(6, 3));
		out.println("number 2");
		out.println(nameShuffle("Donald Trump"));
		out.println(nameShuffle("Rosie O'Donnell"));
		out.println("number 3");
		out.println(discount(89, 20));
		out.println("number 4");
		out.println(differenceMaxMin(new int[]{44, 32, 86, 19}));
		out.println("number 5");
		out.println(equal(3, 4, 3));
		out.println("number 6");
		out.println(reverse("Hello World!"));
		out.println("number 7");
		out.println(programmers(147, 33, 526));
		out.println("number 8");
		out.println(getXO("ooxx"));
		out.println(getXO("ooXm"));
		out.println(getXO("zpzpzpzpz"));
		out.println("number 9");
		out.println(bomb("There is a bomb."));
		out.println("number 10");
		out.println(sameAscii("aa", "aa"));
		out.println(sameAscii("EdAbIt", "EDABIT"));
	}

	/* *1) Теша шел по прямой улице, по обеим сторонам которой стояло ровно n
	одинаковых домов. Она заметила, что четные дома увеличиваются справа, а нечетные уменьшаются слева.
	Создайте функцию, которая принимает номер дома и длину улицы n и возвращает номер
	дома на противоположной стороне* */
	public static int oppositeHouse(int num, int leng) //num-номер дома; leng-длина улицы
	{
		if (num%2==1)
		{
			for(int i=0, m=1; i<leng; i++, m=m+2)
			{
				if (m==num)
				{
					num=i;
					break;
				}
			}
			//out.println(num);
			for(int i=leng-1, m=0; i>=0; i--, m=m+2)
			{
				//out.println(m);
				if(i==num)
				{
					num=m;
					break;
				}
			}
		}
		else
		{
			for(int i=0, m=2; i<leng; i++, m=m+2)
			{
				if (m==num)
				{
					num=i;
					break;
				}

			}
			for(int i=leng-1, m=1; i>=0; i--, m=m+2)
			{
				if(i==num)
				{
					num=m;
					break;
				}
			}
		}
		return num;
	}
	/* ************************************************************* */

	/* *2)Создайте метод, который принимает строку (имя и фамилию человека) и
	возвращает строку с заменой имени и фамилии.* */
	public static String nameShuffle(String name)
	{
		String[] n=name.split(" ");
		return n[1]+" "+n[0];
	}
	/* ************************************************************* */

	/* *3)Создайте функцию, которая принимает два аргумента: исходную цену и процент
	скидки в виде целых чисел и возвращает конечную цену после скидки.* */
	public static double discount(int price, int procent)
	{
		return (double)(price/100.0)*(100-procent);
	}
	/* ************************************************************* */

	/* *4)Создайте функцию, которая принимает массив и возвращает разницу между
	наибольшим и наименьшим числами.* */
	public static int differenceMaxMin(int[] mass)
	{
		int max=0;
		int min=0;
		int f1=1;
		int f2=1;
		for(int i=0; i<mass.length; i++)
		{
			if (max<mass[i] || f1==1)
			{
				max=mass[i];
				f1=0;
			}
			if (min>mass[i] || f2==1)
			{
				min=mass[i];
				f2=0;
			}

		}
		return max-min;
	}
	/* ************************************************************* */

	/* *5)Создайте функцию, которая принимает три целочисленных аргумента (a, b, c) и
	возвращает количество целых чисел, имеющих одинаковое значение.* */
	public static int equal(int... mass)
	{
		int l=0;
		//int[] mass={a, b, c};
		for(int i=0; i<mass.length; i++)
		{
			for(int j=0; j<mass.length;j++)
			{
				if (i==j)
					continue;
				else if (mass[i]==mass[j])
					l++;
			}
		}
		return l;
	}
	/* ************************************************************* */

	/* *6)Создайте метод, который принимает строку в качестве аргумента и возвращает ее в
	обратном порядке.* */
	public static String reverse(String str)
	{
		String reverse_str="";
		for (int i=(str.length())-1; i>=0; i--) 
		{
			reverse_str=reverse_str+ str.charAt(i);
		}
		return reverse_str;
	}
	/* ************************************************************* */

	/* *7)Вы наняли трех программистов и (надеюсь) платите им. Создайте функцию, 
	которая принимает три числа (почасовая заработная плата каждого программиста)
	и возвращает разницу между самым высокооплачиваемым программистом и самым
	низкооплачиваемым* */
	public static int programmers(int pr1, int pr2, int pr3)
	{
		int max=pr1;
		int min=pr1;
		//нахождение максимума
		if (pr2>max)
			max=pr2;
		if(pr3>max)
			max=pr3;
		//
		//нахождение минимума
		if(min>pr2)
			min=pr2;
		if (min>pr3)
			min=pr3;
		//
		return max -min;
	}
	/* ************************************************************* */

	/* *8)Создайте функцию, которая принимает строку, проверяет, имеет ли она одинаковое
	количество x и o и возвращает либо true, либо false.* */
	public static boolean getXO(String str)
	{
		int x=0;
		int o=0;
		for (int i=0; i<str.length(); i++) 
		{
			if('x'==str.charAt(i) || 'X'==str.charAt(i))
				x++;
			else if('o'==str.charAt(i) || 'O'==str.charAt(i))
				o++;
		}
		return (x==o || (x==0 && o==0)) ? true:false;
	}
	/* ************************************************************* */

	/* *.* */
	public static String bomb(String str)
	{
		str=str.toLowerCase();
		str.contains("bomb |bomb!|bomb.");
		if(str.contains("bomb ") || str.contains("bomb!")|| str.contains("bomb.") || str.contains("bomb?") || str.contains("bomb,"))
			return "DUCK!";
		return "Relax, there's no bomb.";
	}
	/* ************************************************************* */

	/* *Возвращает true, если сумма значений ASCII первой строки совпадает с суммой
	значений ASCII второй строки, в противном случае возвращает false.* */
	public static boolean sameAscii(String a, String b)
	{
		int ascii_a=0;
		int ascii_b=0;
		for (int i=0; i<a.length();i++) 
		{
			ascii_a+=a.charAt(i);
		}
		for (int i=0; i<b.length();i++) 
		{
			ascii_b+=b.charAt(i);
		}
		return ascii_a==ascii_b ?true:false;
	}
	/* ************************************************************* */
}