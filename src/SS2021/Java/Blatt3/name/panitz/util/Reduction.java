package SS2021.Java.Blatt3.name.panitz.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;




public class Reduction{

  static public int count(Iterable<String> xs){
    int result = 0;
    for (String x : xs) {result = result + 1;}
    return result;
  }

  public static int chrNo(Iterable<String> xs){
    int result = 0;
    for (String x : xs) {result = result + x.length();}
    return result;
  }

  public static String ap(Iterable<String> xs){
    String result = " ";
    for (String x : xs) {result = result + " " + x;}
    return result;
  }

  public static String lo(Iterable<String> xs){
    String result = "";
    for (String x : xs) {result= result.length() > x.length()?result:x;}
    return result;
  }

  public static boolean co(String y,Iterable<String> xs){
    boolean result = false;
    for (String x : xs) {result = result || x.equals(y);}
    return result;
  }


public static<E,R> R fold(Iterable<E> xs,R result,BiFunction<R,E,R> o){
  for (E x : xs) {result = o.apply(result,x);}
  return result;
}


  public static int countF(Iterable<String> xs){
    return fold(xs, 0, (result,x) -> result + 1);
  }

  public static int chrNoF(Iterable<String> xs) {
    return fold(xs,0,(result,x) -> result + x.length());
  }

  public static String apF(Iterable<String> xs) {
    return fold( xs, " ", (result,x) ->result + " " + x);
  }

  public static String loF(Iterable<String> xs) {
    return fold( xs, "", (result,x)->result.length()>x.length()?result:x);
  }

  public static boolean coF(String y,Iterable<String> xs) {
    return fold( xs, false, (result,x)-> result || x.equals(y));
  }





  public static int sum(Iterable<Integer> xs) {
    return fold(xs,0,(result,x) -> result + x);
  }

  public static long product(Iterable<Long> xs) {
    return fold(xs, 1L, (result, x) -> result*x);
  }

  public static long maximum(Iterable<Long> xs) {
    return fold(xs, Long.MIN_VALUE, (result, x) -> result > x ? result:x);
  }

  /*Aufgabe 2*/
  public static <E> 
  boolean exists(Iterable<E> xs, Predicate<? super E> p) {
    return fold(xs, false, (result, x) -> result || p.test(x));
  }

  public static <E> 
  boolean all(Iterable<E> xs, Predicate<? super E> p) {
    return fold(xs,true,(result,x)->result && p.test(x)
    );
  }

  public static <E> 
  Set<E> collect(Iterable<E> xs, Predicate<? super E> p) {
    return fold(xs, new HashSet<>(), (result, current) -> {
      if (p.test(current)) result.add(current);
      return result;
    });
  }

  /*aufgabe 3*/
  public static List<Character> toList(String str) {
    return str.chars()
        .mapToObj(c -> (char) c).collect(Collectors.toList());
  }

  public static int readBinary(String str) {
    return readBinary(toList(str));
  }

  public static int readBinary(Iterable<Character> xs) {
    return fold(xs,0,(result,x)-> result*2+(x-'0'));
  }



  public static record Pair<A,B>(A fst,B snd){}



  public static int romanDigit(char c){
    return switch(c){
      case 'I' -> 1;
      case 'V' -> 5;
      case 'X' -> 10;
      case 'L' -> 50;
      case 'C' -> 100;
      case 'D' -> 500;
      case 'M' -> 1000;
      default ->
          throw new RuntimeException("Illegal roman digit: "+c);
    };
  }



  public static int readRoman(String str) {
    return readRoman(toList(str)).fst();
  }

  public static 
  Pair<Integer,Integer> readRoman(Iterable<Character> xs) {
    return fold(xs, new Pair<>(0, 0), (result, x) -> {
      var xAsInt = romanDigit(x);
      return xAsInt > result.snd()
              ? new Pair<>(result.fst + (xAsInt - 2 * result.snd()), xAsInt)
              : new Pair<>(result.fst() + xAsInt, xAsInt);
    });
  }

}
