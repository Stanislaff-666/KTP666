import static java.lang.System.*;
import java.util.Arrays;
import java.util.stream.Stream;
import java.util.stream.*;
import java.util.*;
import java.lang.Long;
public class task6
{
	public static void main(String[] args) 
	{
		System.out.println(hiddenAnagram("d e b90it->?$ (c)a r...d,,#~", "bad credit") );
        System.out.println(Arrays.deepToString(collect("intercontinentalisationalism", 6) ));
        System.out.println(nicoCipher("mubashirhassan", "crazy"));
        System.out.println(Arrays.toString(twoProduct(new int[]{1, 2, 3, 9, 4, 5, 15, 3}, 45 )));
        //System.out.println(Arrays.toString(isExact(40320)));
        System.out.println(fractions("3.(142857)"));
        System.out.println(pilish_string("CANIMAKEAGUESSNOW"));
        System.out.println(Arrays.deepToString(sumsUp(new int []{1, 6, 5, 4, 8, 2, 3, 7, 8})));
        System.out.println(isValid("abcdefghhgfedecba"));
		
	}

	/* *задание 1: Создайте функцию, которая принимает две строки. Первая строка содержит
    предложение, содержащее буквы второй строки в последовательной
    последовательности, но в другом порядке. Скрытая анаграмма должна содержать
    все буквы, включая дубликаты, из второй строки в любом порядке и не должна
    содержать никаких других букв алфавита.
    Напишите функцию, чтобы найти анаграмму второй строки, вложенную где-то в
    первую строку. Вы должны игнорировать регистр символов, любые пробелы и
    знаки препинания и возвращать анаграмму в виде строчной строки без пробелов
    или знаков препинания.* */
    public static String hiddenAnagram (String a, String b){
        StringBuilder a1=new StringBuilder("");
        String z;
 b=b.replaceAll(" ", "");
        char []c=b.toCharArray();
        Arrays.sort(c);
        for (int i=0; i<a.length(); i++)
            if (Character.isLetter(a.charAt(i)))
                a1.append(a.charAt(i));
        for (int i=0; i<a1.length()-b.length()+1;i++)
        {
            z=a1.substring(i, i+b.length());
            char []r = z.toCharArray();
            Arrays.sort(r);
            if (Arrays.equals(r, c))
                return z;
        }
        return  "noutfond";
    }
    /* ***************************************************** */

	
	/*2. **Напишите функцию, которая возвращает массив строк, заполненных из срезов
символов n-длины данного слова (срез за другим, в то время как n-длина
применяется к слову).** */
	public static String[] collect(String str, int len)
	{
		if (str.length()>len) 
		{
			String[] a=Arrays.copyOf(collect(str.substring(len), len), collect(str.substring(len), len).length+1);
			//String[] rez=new String[a.length+1];
			a[a.length-1]=str.substring(0, len);
			return a;
		}
		else
			return new String[] {};
	}
	/* ***************************************************** */


	/* **3. В шифре Nico кодирование осуществляется путем создания цифрового ключа и
	присвоения каждой буквенной позиции сообщения с помощью предоставленного
	ключа.** */
	public static String nicoCipher(String message, String key)
	{
		key=key.toLowerCase();
		char[] keys=key.toCharArray();
		Arrays.sort(keys);
		int[] intkey=new int[keys.length];
		for (int i=0; i<keys.length; i++) 
		{
			for (int j=0; j<keys.length; j++) 
			{
				if(key.charAt(j)==keys[i])
				{
					intkey[i]=j;
					break;
				}
			}
		}
		StringBuilder messages=new StringBuilder(message);
		while(messages.length()%keys.length !=0)
		{
			messages.append('_');
		}
		StringBuilder rez=new StringBuilder("");
		for (int i=0; i<messages.length(); i=i+keys.length) 
		{
			String a=messages.substring(i, i+(keys.length));
			for(int k=0; k<intkey.length;k++)
			{
				rez.append(a.charAt(intkey[k]));
			}
		}
		return new String(rez);
	}
	/* *********************************************************************** */

	/* *задание 4: Создайте метод, который принимает массив arr и число n и возвращает массив из
    двух целых чисел из arr, произведение которых равно числу n следующего вида:
    [value_at_lower_index, value_at_higher_index]
    Убедитесь, что вы возвращаете массив из двух целых чисел, который точно делит n
    (произведение n) и что индексы совпадают с указанным выше форматом. Таким образом,
    сортировка значений основана на восходящих индексах.* */
    public static int[] twoProduct (int[] a, int b){
        int []c=new int[2];
        for (int i=a.length-2; i>0; i--)
            for (int j=a.length-1; j>i+1; j--)
            {
                if (a[i]*a[j]==b){
                    c[0]=a[i];
                    c[1]=a[j];
                    return c;}
                }
        return c;
    }
    /* ***************************************************** */

    /* 5. Создайте рекурсивную функцию, которая проверяет, является ли число точной
       верхней границей факториала n. Если это так, верните массив точной
       факториальной границы и n, или иначе, пустой массив.
       
       Примечание:
       - Ожидается, что вы решите эту задачу с помощью рекурсии. */
    
    public static int[] isExact(int f, int m, int n) {
        if (f < n)
            return isExact(f*(m+1), m+1, n);
        return new int[] {f, m};
    }
       
    public static int[] isExact(int n) {
        int[] res = isExact(1, 1, n);
        if (res[0] == n) return res;
        return new int[] {};
    }
    /* ************************************************ */

	/* **6. Деление на дробь часто приводит к бесконечно повторяющейся десятичной дроби** */
	static public String fractions(String txt)
	{
		int i=0;
		StringBuilder zamenael1=new StringBuilder();
		while (txt.charAt(i)!='.') 
		{
			zamenael1.append(txt.charAt(i));
			i++;
		}
		i++;
		StringBuilder zamenael2=new StringBuilder();
		StringBuilder zamenael3=new StringBuilder();
		while (txt.charAt(i)!='(') 
		{
			zamenael2.append(txt.charAt(i));
			zamenael3.append(txt.charAt(i));
			i++;
		}
		int j=++i;
		while (txt.charAt(i)!=')') 
		{
			zamenael2.append(txt.charAt(i));
			i++;
		}
		j=i-j;
		int d=0;
		for (int k=0; k<j; k++) 
		{
			d=d*10+9;
		}
		if (zamenael3.length()>0) 
		{
			zamenael2.replace(0,zamenael2.length()-1 ,String.valueOf(Integer.parseInt(zamenael2.toString())-Integer.parseInt(zamenael3.toString())));
			d=d*(int) Math.pow(10,zamenael3.length());
		}
		int zn=Integer.parseInt(zamenael2.toString())+Integer.parseInt(zamenael1.toString())*d;
		for (int k=2;k<d ; k++) 
		{
			if (zn%k==0 &&d%k==0) 
			{
				zn=zn/k;
				d=d/k;
			}
		}
		return String.valueOf(zn)+'/'+String.valueOf(d);
	}
	/* *********************************************************************** */

	/* *задание 7: В этой задаче преобразуйте строку в серию слов (или последовательности
    символов), разделенных одним пробелом, причем каждое слово имеет одинаковую
    длину, заданную первыми 15 цифрами десятичного представления числа Пи:
    3.14159265358979
    Если строка содержит больше символов, чем общее количество, заданное суммой цифр
    Пи, неиспользуемые символы отбрасываются, и вы будете использовать только те,
    которые необходимы для формирования 15 слов.* */

    public static StringBuilder pilish_string (String txt){
        int []c= new int[]{3,1,4,1,5,9,2,6,5,3,5,8,9,7,9};
        StringBuilder text= new StringBuilder("");
        int i=0;
        int j=0;
        while (i+c[j]<txt.length()&&j<c.length)
        {
            text.append(' '+txt.substring(i, i+c[j])) ;
            i=i+c[j];
            j=j+1;
        }
        if (i<txt.length())
        {text.append(' '+txt.substring(i, txt.length()-1));
        i=c[j]-txt.length()+i;
        for (int k=0; k<=i; k++)
            text.append(txt.charAt(txt.length()-1));
        }
        return text;
    }
    /* ***************************************************** */

     /* 8. Создайте функцию для генерации всех непоследовательных двоичных строк, где
       непоследовательные определяется как строка, в которой нет последовательных
       строк, и где n определяет длину каждой двоичной строки. */
       
    public static String generateNonconsecutive(int n) {
        String res = "";
        String format = "%" + n + 's';
        int count = 2 << (n-1);
        nextNumber:
        for (int i = 0; i < count; i++) {
            String num = String.format(format, Integer.toBinaryString(i)).replace(' ', '0');
            for (int j = 0; j < n - 1; j++)
                if (num.charAt(j) == '1' && num.charAt(j+1) == '1')
                    continue nextNumber;
            res += num + ' ';
        }
        return res.substring(0, res.length() - 1);
    }
    /* **************************************************** */

    /* *9. Шерлок считает строку действительной, если все символы строки встречаются
    одинаковое количество раз. Также допустимо, если он может удалить только 1
    символ из 1 индекса в строке, а остальные символы будут встречаться одинаковое
    количество раз. Для данной строки str определите, действительна ли она. Если да,
    верните «ДА», в противном случае верните «НЕТ».* */
    public static String isValid(String... str)
    {
        Set<Character> gl= Stream.of('a','e','y','u','i','o', 'q','w','r','t','p','s','d','f','g','h','j','k','l','z','x','c','v','b','n','m').collect(Collectors.toSet());
        Map<String, Long> rez=Arrays.stream(str).map(a->a.toLowerCase()).map(s->s.chars().mapToObj(c->(char)c).toArray(Character[]::new)).flatMap(b->Arrays.stream(b)).filter(c->gl.contains(c)).collect(Collectors.groupingBy(ch-> String.valueOf(ch), Collectors.counting()));
        Set<String> rezz= rez.keySet();
        Long desiredObject=Collections.max(rez.values());
        Long desiredObject1=Collections.min(rez.values());
        System.out.println(desiredObject);
        System.out.println(desiredObject1);
        if (desiredObject.equals(desiredObject1))
            return "YES";
        else
        {
            for (String key : rezz)
            if (desiredObject.equals(rez.get(key)))
                rez.replace(key, desiredObject-1);
            desiredObject=Collections.max(rez.values());
            if (desiredObject.equals(desiredObject1))
                return "YES";
            else
                return "NO";
        }
    }
    /* ***************************************************** */

	/* **10. Создайте функцию, которая получает каждую пару чисел из массива, который
	суммирует до восьми, и возвращает его как массив пар (отсортированный по
	возрастанию). ** */
	public static int[][] sumsUp(int[] arr)
	{
		List<int[]> a=new ArrayList<int[]>();
		for (int i=0; i<arr.length-1; i++) 
		{
			for (int j=i+1; j<arr.length; j++) 
			{
				if (arr[i]+arr[j]==8) 
				{
					int[] a1={arr[i], arr[j]};
					Arrays.sort(a1);
					a.add(a1);				
				}
			}	
		}
		return a.toArray(new int[0][0]);
	}
	/* ***************************************************** */
}