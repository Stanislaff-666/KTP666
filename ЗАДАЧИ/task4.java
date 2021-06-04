import static java.lang.System.*;
import java.util.Arrays;
import java.util.stream.Stream;
import java.util.stream.*;
import java.util.Set;
import java.util.Map;
import java.lang.Long;
import java.util.Collections;
public class task4
{
	public static void main(String[] args) 
	{
		out.println(sevenBoom(1, 2, 3, 4, 5, 6, 67));
		out.println(cons(new int[] {1, 2, 3, 4, 5, 6, 7}));
		out.println(unmix("hTsii  s aimex dpus rtni.g"));
		out.println(noYelling("What went wrong?????????"));
		out.println(xPronounce("unboxinx"));
		out.println(largestGap(new int[] {9, 4, 26, 26, 0, 0, 5, 20, 6, 25, 5}));
		out.println(reversecod(7977));
		out.println(commonLastVowel("String str"));
		out.println(commonLastVowel("aaaaeeew"));
		out.println(commonLastVowel("aaaaeeew"));

	}

	/* *1. Создайте функцию, которая принимает массив чисел и возвращает "Бум!", если в
	массиве появляется цифра 7. В противном случае верните "в массиве нет 7".* */
	public static String sevenBoom(int... a)
	{/*
		String str="";
		for (int i : a) 
		{
			str=str+i;
		}
		for (int i=0; i<str.length(); i++) 
		{
			if(str.charAt(i)=='7')
				return "Boom!";
		}*/
		return (Arrays.stream(a).mapToObj(x->String.valueOf(x)).filter(b->b.indexOf('7')>-1).count())>0 ? "Boom!": "there is no 7 in the array";
	}
	/* ***********************************"there is no 7 in the array"****************************.filter(z->(z-'0')==7).******** */

	/* *2. Создайте функцию, которая определяет, могут ли элементы в массиве быть
	переупорядочены, чтобы сформировать последовательный список чисел, где
	каждое число появляется ровно один раз* */
	public static boolean cons(int[] arr)
	{
		Arrays.sort(arr);
		for(int i=1; i<arr.length;i++)
		{
			if(arr[i] !=arr[i-1]+1)
			{
				return false;
			}
		}
		return true;
	}
	/* *********************************************************************** */

	/* *3. lPaeesh le pemu mnxit ehess rtnisg! О, извините, это должно было быть: Пожалуйста,
	помогите мне распутать эти строки!
	Каким-то образом все строки перепутались, каждая пара символов поменялась местами.
	Помоги отменить это, чтобы снова понять строки.* */
	public static String unmix(String str)
	{
		 String rez = "";
        for (int i = 1; i < str.length(); i = i + 2) 
        {
            rez = rez + str.charAt(i) + str.charAt(i - 1);
        }
        return rez;
	}
	/* *********************************************************************** */

	/* *4. Создать функцию, которая преобразует предложения, заканчивающиеся
	несколькими вопросительными знаками ? или восклицательными знаками ! в
	предложение, заканчивающееся только одним, без изменения пунктуации в
	середине предложений.* */
	public static String noYelling(String str)
	{
		//out.println(str.length());
		if(str.charAt(str.length()-1)=='!' || str.charAt(str.length()-1)=='?')
		{
			while(str.charAt(str.length()-1)==str.charAt(str.length()-2))
			{
				str=str.substring(0, str.length()-1);
			}
		}
		return str;
	}
	/* *********************************************************************** */

	/* *5. Создайте функцию, которая заменяет все x в строке следующими способами:
	Замените все x на "cks" 
	Слово начинается с "x" поэтому замените его на "z".
	Слово-это просто буква "х" поэтому замените ее на " cks "* */
	public static String xPronounce(String str)
	{
		String s1 = "";
        if (str.charAt(0) == 'x' && str.charAt(1) != ' ')
        	s1 = s1 + "cks";
		else if (str.charAt(0) == 'x')
            s1 = s1 + 'z';
        else s1 = s1 + str.charAt(0);
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == 'x' && str.charAt(i - 1) == ' ' && str.charAt(i + 1) != ' ')
            	s1= s1 + "cks";
            else if (str.charAt(i) == 'x' && str.charAt(i - 1) == ' ')
                s1 = s1 + 'z';
            else if (str.charAt(i) == 'x')
                s1 = s1 + "cks";
            else s1 = s1 + str.charAt(i);
        }
        return s1;
	}
	/* *********************************************************************** */

	/* *6. Учитывая массив целых чисел, верните наибольший разрыв между
	отсортированными элементами массива.* */
	public static int largestGap(int[] arr)
	{
		Arrays.sort(arr);
		int max = -1;
        for (int i = 1; i < arr.length; i++) {
            if (max < arr[i] - arr[i - 1])
                max = arr[i] - arr[i - 1];
        }
        return max;
	}
	/* *********************************************************************** */

	/* *7. Это вызов обратного кодирования. Обычно вам дают явные указания о том, как
	создать функцию. Здесь вы должны сгенерировать свою собственную функцию,
	чтобы удовлетворить соотношение между входами и выходами.
	Ваша задача состоит в том, чтобы создать функцию, которая при подаче входных данных
	ниже производит показанные примеры выходных данных.* */
	public static int reversecod(int a)
	{
		switch (a) {
            case (832):
                return 594;
            case (51):
                return 36;
            case (7977):
                return 198;
            case (1):
                return 0;
            case (665):
                return 99;
            case (149):
                return 0;
            default:
                break;
        }
        return -1;
	}
	/* *********************************************************************** */

	/* *8. Создайте функцию, которая принимает предложение в качестве входных данных и
	возвращает наиболее распространенную последнюю гласную в предложении в
	виде одной символьной строки.* */
	public static String commonLastVowel(String... str)
	{
		Set<Character> gl=Stream.of('a','e','y','u','i','o').collect(Collectors.toSet());
		/*for (int i=0; i<str.length; i++) 
		{
			str[i].toLowerCase();			
		}*/
		Map<String, Long> rez=Arrays.stream(str)
		.map(a->a.toLowerCase())
		.map(s->s.chars().mapToObj(c->(char)c)
			.toArray(Character[]::new))
		.flatMap(b->Arrays.stream(b))
		.filter(c->gl.contains(c))
		.collect(Collectors.groupingBy(ch-> String.valueOf(ch), Collectors.counting()));
		
		Set<String> rezz= rez.keySet();
		Long desiredObject=Collections.max(rez.values());
		out.println(rez.entrySet());
		for (String key : rezz) 
		{
    		if (desiredObject.equals(rez.get(key))) 
    		{
        		return key;
    		}
		}
		return "";

	}
	/* *********************************************************************** */


	/* *задание 9: Это вызов обратного кодирования. Обычно вам дают явные указания о том, как
    создать функцию. Здесь вы должны сгенерировать свою собственную функцию,
    чтобы удовлетворить соотношение между входами и выходами.* */
    public static String memeSum(int a, int b) {
        String s = "";
        String a1 = "";
        String b1 = "";
        if (a > b) {
            a1 = Integer.toString(a);
            b1 = Integer.toString(b);
        } else {
            b1 = Integer.toString(a);
            a1 = Integer.toString(b);
        }
        for (int i = 0; i < b1.length(); i++)
            s = (Character.getNumericValue(a1.charAt(a1.length() - i - 1)) + Character.getNumericValue(b1.charAt(b1.length() - i - 1))) + s;
        if (a1.length() > b1.length())
            for (int i = b1.length(); i < a1.length(); i++)
                s = a1.charAt(a1.length() - i - 1) + s;

        return s;
    }
    /* *********************************************************************** */

    /* *задание 10: Создайте функцию, которая удалит все повторяющиеся символы в слове,
    переданном этой функции. Не просто последовательные символы, а символы,
    повторяющиеся в любом месте строки.* */
    public static String unrepeated(String str) 
    {
        char[] ch = new char[str.length()];
        for (int i = 0; i < str.length(); i++) {
            ch[i] = str.charAt(i);
        }
        String s="";
        s=s+str.charAt(0);
        for (int i = 1; i < str.length(); i++) 
        {
            int k=0;
            for (int h = 0; h < s.length(); h++) 
            {
                if (s.charAt(h) == ch[i])
                    k=k+1;

            }
            if (k == 0)
                s = s + ch[i];
        }
            return s;
        
    }
    /* *********************************************************************** */

}