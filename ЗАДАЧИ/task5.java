import static java.lang.System.*;
import java.util.Arrays;
import java.util.stream.Stream;
import java.util.stream.*;
import java.util.*;
import java.lang.Long;
import java.util.regex.*;
import java.time.*;
import java.text.*;

public class task5
{
	public static void main(String[] args) 
	{
		
		//1
		System.out.println("1 zadanie:\n"+sameLetterPattern("qwqqsq", "wewwwq"));
		//3
		System.out.println("3 zadanie:\n"+digitsCount(123));
		//4
		System.out.println("4 zadanie:\n"+totalPoints(new String[] {"trance", "recant"}, "recant"));
		//5
		System.out.println("5 zadanie:\n"+(longestRun(new int[]{1,2,3,2,1})));
		//6
		System.out.println("6 zadanie:\n"+takeDownAverage(new String[] {"53%", "79%"}));
		//7
		System.out.println("7 zadanie:\n"+rearrange("Tesh3 th5e 1I lov2e way6 she7 j4ust i8s"));
		//8
		System.out.println("8 zadanie:\n"+maxPossible(123, 654));
		//9
		//System.out.println("9 zadanie:\n"+timeDifference("New York", "December 31, 1970 13:40", "Beijing"));
		timeDifference();
		//10
		System.out.println("10 zadanie:\n"+isNew(123));*/
		timeDifference("Caracas","London");


	}
	/* **1.Создайте функцию, которая возвращает true, если две строки имеют один и тот же
	буквенный шаблон, и false в противном случае.** */
	public static boolean sameLetterPattern(String a, String b)
	{
		if(a.length()==b.length())
		{
			String[] arr= a.chars().mapToObj(s->String.valueOf((char) s)).distinct().toArray(String[]::new);
			String[] arr2= b.chars().mapToObj(s->String.valueOf((char) s)).distinct().toArray(String[]::new);
			if(arr.length != arr2.length)
				return false;
			HashMap<String, Integer> code=new HashMap<String, Integer>();
			HashMap<String, Integer> code2=new HashMap<String, Integer>();
			for (int i=0; i<arr.length; i++) 
			{
				code.put(arr[i], i);
				code2.put(arr2[i], i);
			}
			int f=0, s=0;
			for (int i=0; i<a.length(); i++) 
			{
				f=f+ code.get(Character.toString(a.charAt(i)))* (int) Math.pow(10, i);
				s=s+code2.get(Character.toString(b.charAt(i)))* (int) Math.pow(10, i);

			}
			System.out.println(String.valueOf(f)+" "+String.valueOf(s));
			return f==s;
		}
		else
			return false;
	}
	/* *************************************************************************** */

	    /* 2. Паутина определяется кольцами, пронумерованными от 0 до 4 от центра, и
       радиалами, помеченными по часовой стрелке сверху как A-H.
       
       Создайте функцию, которая принимает координаты паука и мухи и возвращает
       кратчайший путь для паука, чтобы добраться до мухи.
       
       Стоит отметить, что кратчайший путь должен быть рассчитан "геометрически", а не путем
       подсчета количества точек, через которые проходит этот путь. Мы могли бы это устроить:
       
       Угол между каждой парой радиалов одинаков (45 градусов).
       
       Расстояние между каждой парой колец всегда одинаково (скажем, "x"). */
	
	public static String spiderVsFly(String spider, String fly) {
	    /* 
	    
	       1. спуститься в центр и пойти по нужной ветке
	          это просто сумма номеров колец мухи и павука
	          
	       2. спуститься/подняться до нужного кольца и пойти по кольцу 
	          разница между кольцами павука и мухи + (разница между веток) * длина пути на кольце
	          
	          длина пути на кольце = 2 * x * sin(45 / 2) = x * 0.76536686473
	    */
	    
	    int sx = spider.charAt(0) - 65;
	    int sy = spider.charAt(1) - 48;
	    int fx = fly.charAt(0) - 65;
	    int fy = fly.charAt(1) - 48;
	    
	    double strategyDist1 = sy + fy;
	    double strategyDist2 = Math.abs(sy - fy) + ((sx + fx) % 8) * fy * 0.76536686473;
	    
	    String path = "";
	    
	    if (strategyDist1 <= strategyDist2) {
	        for (int i = 0; i < sy; i++) {
	            path += spider.charAt(0);
	            path += sy - i;
	            path += '-';
	        }
	        path += "A0-";
	        for (int i = 0; i < fy; i++) {
	            path += fly.charAt(0);
	            path += i + 1;
	            path += '-';
	        }
	    } else {
	        for (int i = 0; i < Math.abs(sy - fy); i++) {
	            path += spider.charAt(0);
	            if (sy > fy) path += sy - i;
	            else path += sy + i;
	            path += '-';
	        }
	        for (int i = 0; i <= (sx + fx) % 8; i++) {
	            path += (char)(65 + (sx + i) % 8);
	            path += fly.charAt(1);
	            path += '-';
	        }
	    }
	    
	    return path.substring(0, path.length() - 1);
	}
	/* *************************************************************************** */

	/* **3. Создайте функцию, которая будет рекурсивно подсчитывать количество цифр
	числа. Преобразование числа в строку не допускается, поэтому подход является
	рекурсивным** */
	public static int digitsCount(int a)
	{
		int b=0;
		if(a>0)
		{
			a=a/10;
			b=digitsCount(a);
			b=b+1;
		}
		return b;
	}
	/* *************************************************************************** */

	/* **4. В игроки пытаются набрать очки, формируя слова, используя буквы из 6-
	буквенного скремблированного слова. Они выигрывают раунд, если им удается
	успешно расшифровать слово из 6 букв.
	Создайте функцию, которая принимает в массив уже угаданных слов расшифрованное 6-
	буквенное слово и возвращает общее количество очков, набранных игроком в
	определенном раунде, используя следующую рубрику:
	3-буквенные слова-это 1 очко
	4-буквенные слова-это 2 очка
	5-буквенные слова-это 3 очка
	6-буквенные слова-это 4 очка + 50 пт бонуса (за расшифровку слова)
	Помните, что недопустимые слова (слова, которые не могут быть сформированы из 6-
	буквенных расшифрованных слов) считаются 0 очками.** */
	public static int totalPoints(String[] a, String str)
	{
		int rez=0;
		Map<String, Long> clovo=str.chars().mapToObj(s->String.valueOf((char) s)).collect(Collectors.groupingBy(x->x, Collectors.counting()));
		for (String vars : a) 
		{
			Map<String, Long> l=vars.chars().mapToObj(s->String.valueOf((char) s)).collect(Collectors.groupingBy(x->x, Collectors.counting()));
			long col=l.entrySet().stream().filter(x->clovo.containsKey(x.getKey()) && clovo.get(x.getKey())>=x.getValue()).count();
			if(col==vars.length())
			{
				rez=rez+((int) col-2);
				if(col==str.length()) {	rez=rez+50;	}
			}	
		}
		return rez;
	}
	/* *************************************************************************** */

	/* **5. Последовательный прогон-это список соседних последовательных целых чисел.
	Этот список может быть как увеличивающимся, так и уменьшающимся. Создайте
	функцию, которая принимает массив чисел и возвращает длину самого длинного
	последовательного запуска.** */
	public static int longestRun(int[] arr) 
	{
        int max = -1;
        int k=1;
        int l=1;
        for (int i = 0; i < arr.length-1; i++) {
            if (arr[i]==arr[i+1]-1){
                k=k+1;}
            else
            {
                k=1;
            }
            if (arr[i]==arr[i+1]+1){
                l=l+1;}
            else
            {
                l=1;
            }
            if (k>max)
                max=k;
            if (l>max) 
            {
            	max=l;
            }
        }
        return max;
    }
	/* *************************************************************************** */

	/* **6. Какой процент вы можете набрать на тесте, который в одиночку снижает средний
	балл по классу на 5%? Учитывая массив оценок ваших одноклассников, создайте
	функцию, которая возвращает ответ. Округлите до ближайшего процента.** */
	 public static int takeDownAverage(String[] arr) {
        int s=0;
        for(int i=0; i<arr.length; i++) {
            arr[i] = arr[i].substring(0, arr[i].length() - 1);
            s=s+Integer.parseInt(arr[i]);
        }
        s=s/arr.length-5*(arr.length+1);
        return s;
    }
	/* *************************************************************************** */

	/* **7. Учитывая предложение с числами, представляющими расположение слова,
	встроенного в каждое слово, верните отсортированное предложение.** */
	public static String rearrange(String s)
	{
		List<String> rm=new ArrayList<String>();
		Collections.addAll(rm, s.split(" "));
		StringBuilder rez=new StringBuilder();
		for(int i=0; i<s.split(" ").length; i++)
		{
			for (ListIterator<String> j=rm.listIterator(); j.hasNext(); ) 
			{
				String a=j.next();
				if(i==a.chars().mapToObj(x->(char) x).filter(m->Character.isDigit(m)).mapToInt(l->l-'0').findFirst().getAsInt()-1)
				{
					rez.append(a.replaceAll("\\d","")+" ");
					j.remove();
					break;
				}
			}
		}
		return rez.toString();
	}
	/* *************************************************************************** */

	/* *8. Напишите функцию, которая делает первое число как можно больше, меняя его
	цифры на цифры во втором числе.* */
	public static int maxPossible(int a1, int b1) {
        
        List<Integer> mas=new ArrayList<Integer>();
        mas=IntStream.of(b1).mapToObj(x->String.valueOf(x)).map(v->v.chars().mapToObj(z->String.valueOf((char) z))
        	.toArray(String[]::new)).flatMap(q->Arrays.stream(q)).mapToInt(l->Integer.parseInt(l)).mapToObj(u->u)
        .collect(Collectors.toList());
        int[] a=IntStream.of(a1).flatMap(x->String.valueOf(x).chars()).map(z->z-'0').toArray();
        Collections.sort(mas, Collections.reverseOrder());
        System.out.println(Arrays.toString(a));
        for (int i=0; i<a.length; i++) 
        {
        	if(a[i]<mas.get(0).intValue())
        	{
        		a[i]=mas.get(0).intValue();
        		mas.remove(0);
        	}     	
        }
        int rez=Arrays.stream(a).reduce(0,(s,b)->s+b);
        return Arrays.stream(a).reduce(0,(s,b)->(s*10)+b);
    }
	/* *************************************************************************** */

	/* *9. В этой задаче цель состоит в том, чтобы вычислить, сколько времени сейчас в двух
	разных городах. * */
	public static SimpleDateFormat parseDate = new SimpleDateFormat("MMMM d, yyyy HH:mm");
    public static SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-M-d HH:mm"); 
    
    public static String getGMT(String city) {
        if (city == "Los Angeles") return "GMT-08:00";
        if (city == "New York") return "GMT-05:00";
        if (city == "Caracas") return "GMT- 04:30";
        if (city == "Buenos Aires") return "GMT-03:00";
        if (city == "London") return "GMT00:00";
        if (city == "Rome") return "GMT+01:00";
        if (city == "Moscow") return "GMT+03:00";
        if (city == "Tehran") return "GMT+03:30";
        if (city == "New Delhi") return "GMT+05:30";
        if (city == "Beijing") return "GMT+08:00";
        if (city == "Canberra") return "GMT+10:00";
        return "GMT";
    }
    
    public static String timeDifference(String cityA, String timestamp, String cityB) {
        try {
            parseDate.setTimeZone(TimeZone.getTimeZone(getGMT(cityA)));
            formatDate.setTimeZone(TimeZone.getTimeZone(getGMT(cityB)));
            Date date = parseDate.parse(timestamp);
            return formatDate.format(date);
        } catch(Exception e) {}
        return null;
    }
	/* *10. Новое число-это число, которое не является перестановкой любого меньшего
	числа. 869-это не новое число, потому что это просто перестановка меньших чисел,
	689 и 698. 509-это новое число, потому что оно не может быть образовано
	перестановкой любого меньшего числа (ведущие нули не допускаются).* */
	public static boolean isNew(int  b1) {
        int b=b1;
        int k1=0;
        while (b>0) {
            b = b / 10;
            k1 = k1 + 1;
        }
        int [] arr=new int [k1];
        while (b1>0) {
            arr[k1-1]=b1%10;
            b1 = b1 / 10;
            k1 = k1 - 1;
        }
        for (int i=1; i<arr.length-1;i++)
            if (arr[i]>arr[i+1]&arr[i+1]!=0)
                return false;
        return true;
    }




}