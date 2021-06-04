import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;
import static java.lang.Math.*;
import static java.lang.System.*;

public class task3
{
	public static void main(String[] args) 
	{
		out.println(Arrays.deepToString(millionsRounding(new String[] {"aa", "13923452"},new String[] {"bb", "13923452"})));
		out.println(Arrays.toString(otherSides(1)));
		out.println(rps("paper", "rock"));
		out.println(warOfNumbers(1, 2, 3, 4));
		out.println(reverseCase("aAzZ"));
		out.println(inatorInator("aAzZA"));
		out.println(doesBrickFit(1, 2, 1, 1, 1));
		out.println(totalDistance(36.1, 8.6, 3, true));
		out.println(mean(new int[] {1, 0, 4, 5, 2, 4, 1, 2, 3, 3, 3}));
		out.println(parityAnalysis(12));
	}

	/* *1. Учитывая массив городов и населения, верните массив, в котором все население
	округлено до ближайшего миллиона.* */
	public static String[][] millionsRounding(String[]... a)
	{
		for(int i=0; i<a.length; i++)
		{
			BigDecimal r=new BigDecimal(a[i][1]);
			r=r.divide(new BigDecimal("1000000")).setScale(0, RoundingMode.HALF_UP);
			a[i][1]=r.toString()+"000000";
		}
		return a;
	}
	/* **************************************************************************** */

	/* *2. Учитывая самую короткую сторону треугольника 30° на 60° на 90°, вы должны
	найти другие 2 стороны (верните самую длинную сторону, сторону средней
	длины).* */
	public static double[] otherSides(double s)
	{
		double[] str=new double[2];
		str[0]= 2*s;//нахождение гипотенузы
		str[1]=s*sqrt(3);//находжение второго катета
		//округление до двух знаков после запятой
		str[0]=ceil(str[0]*100)/100;
		str[1]=ceil(str[1]*100)/100;
		return str;
	}
	/* **************************************************************************** */

	/* *3. Создайте функцию, имитирующую игру "камень, ножницы, бумага". Функция
	принимает входные данные обоих игроков (камень, ножницы или бумага), первый
	параметр от первого игрока, второй от второго игрока* */
	public static String rps(String p1, String p2)
	{
		if((p1.equals("paper") || p1.equals("rock") || p1.equals("scissors")) && (p2.equals("paper")  || p2.equals("rock")  || p2.equals("scissors")))
		{
			if (p1.equals(p2))
				return "TIE";
			else if((p1.equals("paper") && p2.equals("rock")) || (p1.equals("rock") && p2.equals("scissors")) || (p1.equals("scissors") && p2.equals("paper")))
				return "Player 1 wins";
			else
				return "Player 2 wins";
		}
		else
		{
			return "None";
		}	
	}
	/* **************************************************************************** */

	/* *4. идет великая война между четными и нечетными числами. Многие уже погибли в
	этой войне и ваша задача-положить этому конец. Вы должны определить, какая
	группа суммируется больше: четная или нечетная. Выигрывает большая группа.
	Создайте функцию, которая берет массив целых чисел, суммирует четные и нечетные
	числа отдельно, а затем возвращает разницу между суммой четных и нечетных чисел.* */
	public static int warOfNumbers(int... numbers)
	{
		int even=0;//четные
		int odd=0;//нечетные
		for (int num : numbers) 
		{
			if(num%2==0)
			{
				even=even+num;
			}
			else
			{
				odd=odd+num;
			}	
		}
		return abs(even-odd);
	}
	/* **************************************************************************** */

	/* *5.Учитывая строку, создайте функцию для обратного обращения. Все буквы в
	нижнем регистре должны быть прописными, и наоборот.* */
	public static String reverseCase(String str)
	{
		String reverse="";
		for(int i=0; i<str.length(); i++)
		{
			if(Character.isUpperCase(str.charAt(i)))
				reverse=reverse+(char)(str.charAt(i)+32);
			else
				reverse=reverse+(char)(str.charAt(i)-32);
		}
		return reverse;
	}
	/* **************************************************************************** */

	/* *6. Создайте функцию, которая принимает строку из одного слова и выполняет
	следующие действия:
	Конкатенирует inator до конца, если слово заканчивается согласным, в противном
	случае вместо него конкатенирует -inator
	Добавляет длину слова исходного слова в конец, снабженный '000'* */
	public static String inatorInator(String str)
	{
		int len=str.length();
		String a=""+str.charAt(len-1);
		if (a.matches("[aAeEiIoOuUyY]"))
			str=str+"-inator";
		else
			str=str+"inator";
		return str+" "+String.valueOf(len*1000);
	}
	/* **************************************************************************** */

	/* *7. Напишите функцию, которая принимает три измерения кирпича: высоту(a),
	ширину(b) и глубину(c) и возвращает true, если этот кирпич может поместиться в
	отверстие с шириной(w) и высотой(h)* */
	public static boolean doesBrickFit(int a,int b, int c, int w, int h)
	{
		if(((a>h || a>w) && (b>h || b>w)) || ((c>h || c>w) && (b>h || b>w)) || ((c>h || c>w) && (a>h || a>w)))
			return false;
		return true;
	}
	/* **************************************************************************** */

	/* *8. Напишите функцию, которая принимает топливо (литры), расход топлива
	(литры/100 км), пассажиров, кондиционер (логическое значение) и возвращает
	максимальное расстояние, которое может проехать автомобиль.
	топливо-это количество литров топлива в топливном баке.
	Расход топлива-это базовый расход топлива на 100 км (только с водителем внутри).
	Каждый дополнительный пассажир увеличивает базовый расход топлива на 5%.
	Если кондиционер включен, то его общий (не базовый) расход топлива увеличивается на
	10%.* */
	public static double totalDistance(double obtp, double cons, int pass, boolean cond)
	{
		cons=cons+cons*0.05*pass;
		if (cond)
			cons=cons+cons*0.1;
		return ceil((obtp/cons)*10000)/100;
	}
	/* **************************************************************************** */

	/* *9. Создайте функцию, которая принимает массив чисел и возвращает среднее
	значение (average) всех этих чисел* */
	public static double mean(int[] a)
	{
		int sum=0;
		for (int i : a) {
			sum=sum+i;
		}
		return ceil((((double) sum/a.length))*100)/100;
	}
	/* **************************************************************************** */

	/* *10. Создайте функцию, которая принимает число в качестве входных данных и
	возвращает true, если сумма его цифр имеет ту же четность, что и все число. В
	противном случае верните false.* */
	public static boolean parityAnalysis(int a)
	{
		int sum=0;
		int s=a;
		while(s/10>0) 
		{
			sum=sum+s%10;
			s=s/10;
		}
		sum=sum+s;
		out.println(sum);
		if(a%2==sum%2)
			return true;
		else
			return false;
	}
	/* **************************************************************************** */
}