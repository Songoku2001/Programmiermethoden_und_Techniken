package SS2021.Java.Blatt3.name.panitz.util;

import java.math.BigInteger;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;


public interface Strom{


  static long readBinary(String x){
    return x.chars().reduce( 0,(result,z) ->
            result * 2 + (z-'0'));
  }


  static long quersumme(long x){
    return LongStream
      .iterate(x,(y)->y/10) //x wird zu y
      .takeWhile(y->y>0)
      .reduce(0, (result, z) -> result+z%10);
  }


  static long factorial(int x){
    return LongStream
      .iterate(1L,(y)->y+1L)
      .limit(x)
      .reduce(1L, (result, y) -> result*y);
  }


  static String asBinary(long x){
    return (x==0) ? "0"
      :LongStream
      .iterate(x,(y)->y/2)
      .takeWhile(y->y>0)
      .mapToObj(y->y)
    .reduce("",(result,current) ->
            current%2L+result,(String y, String z) -> y+z);
  }

  static String convertToBase(int b,long x){
    return (x==0) ? "0"
            :LongStream
            .iterate(x,(y)->y/b)
            .takeWhile(y->y>0)
            .mapToObj(y->y)
            .reduce("",(result,current) ->
                    current%b+result,(String st, String st2) -> st+st2);
  }


  static String digits="0123456789ABFDEFGHIJKLMNOPQRSTUVWXYZ";    
  static char toDigit(int x){
    return digits.charAt(x);
  }


  static long readFromBase(int b,String x){
    return x.chars().reduce( 0,(result,z) -> {

            if(Character.isDigit(z))
              return result * b + (z-'0');
            else {
              return result * b + (z+10-'A');
            }
    });
  }

  public static record TwoLong(long i1, long i2){}


  static public Stream<TwoLong> fibPairs(){
    return Stream.iterate(new TwoLong(0, 1), result -> new TwoLong(result.i2, result.i1+result.i2));
  }


  static public Stream<Long> fibs(){
    return fibPairs().map(x -> x.i1);
  }


  static public Stream<Long> fibs100(){
    return fibs().limit(100);
    //return fibPairs().limit(100).map(x->x.i1);
  }  



  static public long fib(int n){
    return fibs().limit(n).collect(Collectors.toList()).get(n-1);
  }

  //public static void main(String[] args) {
    //fibPairs().limit(10).forEach(System.out::println);
    //fibs().limit(10).forEach(System.out::println);
    //fibs100().forEach(System.out::println);
    //System.out.println(fib(1));
  //}

  static public record TwoBig(BigInteger i1, BigInteger i2){}

  static public Stream<TwoBig> facPairs(){
    return Stream.iterate(new TwoBig(BigInteger.ONE, BigInteger.ONE),result ->
            new TwoBig(result.i1.add(BigInteger.ONE),result.i2.multiply(result.i1.add(BigInteger.ONE))));
  }

  public static void main(String[] args) {
    //facPairs().limit(6).forEach(System.out::println);
    //System.out.println(fac(42));
  }


  static public Stream<BigInteger> facs(){
    return facPairs().map(x->x.i2);
  }

  static public BigInteger fac(int n){
    return facs().limit(n).collect(Collectors.toList()).get(n-1);
  }

  
  static public class SpliterateString 
                    implements Spliterator<Character> {
    int i = 0;
    int end;
    String s;

    public SpliterateString(String s) {
      this(0, s.length() - 1, s);
    }

    public SpliterateString(int i, int end, String s) {
      this.i = i;this.end = end;this.s = s;
    }

    @Override
    public boolean tryAdvance(Consumer<? super Character> action) {
      if (i>end) {
        return false;
      }
      action.accept(s.charAt(i++)); // NEXT UND HASNEXT in EINEM
      return true;
    }

    @Override
    public Spliterator<Character> trySplit() {
      return null;
    }

    @Override
    public long estimateSize() {
      return end - i;
    }

    @Override
    public int characteristics() {
      return 0;
    }
  }
  


}
