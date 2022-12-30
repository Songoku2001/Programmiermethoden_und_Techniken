
package SS2021.Java.Blatt2.name.panitz.util;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.function.Function;
import java.util.function.Predicate;


public interface Iteratoren {

public static class IntRange implements Iterable<Integer>{
  int from;
  int to;
  int step;
  boolean infinite;
  public IntRange(int from,  int to,  int step){
    this.from=from;
    this.to=to;
    this.step=step;
    infinite=false;
  }
  public IntRange(int from,  int to) {
    this(from, to,1);
  }
  public IntRange(int from){
    this(from, 122);
    infinite=true;
  }

  public IntRange(){
    this(1);
  }

  @Override
  public Iterator<Integer> iterator() {
    return new IntRangerIter(from);
  }
  private class IntRangerIter implements Iterator<Integer> {
    int from;

    IntRangerIter(int from) {
      this.from=from;
    }

    @Override
    public boolean hasNext() {
      return infinite || (from<=to && step>=0) || (from <=to && step<=0);
    }

    @Override
    public Integer next() {
      int result = from;
      from = from + step;
      return result;
    }
  }
}


public static class Fib implements Iterable<BigInteger> {
  @Override public Iterator<BigInteger> iterator(){
    return new Iterator<BigInteger>() {
      @Override
      public boolean hasNext() {
        return true;
      }
      BigInteger eins = BigInteger.ZERO;
      BigInteger zwei = BigInteger.ONE;

      public BigInteger next() {
        BigInteger tmp = eins;
        eins = zwei;
        zwei = tmp.add(zwei);
        return tmp;
      }
    };
  }

  public static void main(String[] args){
    new Fib().forEach(System.out::println);
  }
}



public static class ArrayIterable<A> implements Iterable<A>{
  A[] as;
  public ArrayIterable(A[] as){
    this.as = as;
  }

  @Override
  public Iterator<A> iterator() {
    return new Iterator<A>() {
      int i=0;
      @Override
      public boolean hasNext() {
        return i<as.length;
      }

      @Override
      public A next() {
        A result = as[i];
        i++;
        return result;
      }
    };
  }
}


public static class  IterableString implements Iterable<Character> {
  String str;
  public IterableString(String str){
    this.str = str;
  }

  @Override
  public Iterator<Character> iterator() {
    return new Iterator<Character>() {

      int i = 0;
      @Override
      public boolean hasNext() {
        return i<str.length();
      }

      @Override
      public Character next() {
       Character tmp = str.charAt(i);
       i++;
       return tmp;
      }
    };
  }

  public static void main(String[] args){
    for (char c:new IterableString("Hello world!")){
      System.out.println(c);
    }
  }
}



public static class Lines implements Iterable<String> {
  String str;
  static String NEW_LINE = System.getProperty("line.separator");

  public Lines(String str){
    this.str=str;
  }
  int laenge =0;

  @Override public Iterator<String> iterator() {
    return new Iterator<String>() {
      @Override
      public boolean hasNext() {
        return str.contains(NEW_LINE);

      }

      @Override
      public String next() {
        if (str.contains(NEW_LINE)) {
          laenge = str.indexOf(NEW_LINE);
          System.out.println(laenge);
        }
        return str.substring(laenge);
      }
    };
  }
  public static void main(String[] args) {
    for (String s:new Lines("hallo"+NEW_LINE+"welt!"))
      System.out.println(s);
  }
}



public static class Words implements Iterable<String>{
  String text;
  public Words(String text){
    this.text = text;
  }

  @Override
  public Iterator<String> iterator() {
    return new Iterator<String>() {

      int i=0;
      String[] split = text.trim().split("\\s+");
      @Override
      public boolean hasNext() {
        return i < split.length && (!split[0].isEmpty());
      }

      @Override
      public String next() {
        var tmp = split[i];
        i++;
        return tmp;
      }
    };
  }
}



public class IndexIterable<A> implements Iterable<A> {
  Function<Long, A> f;

  public IndexIterable(Function<Long, A> f) {
    this.f=f;
  }

  @Override
  public Iterator<A> iterator() {
    return new Iterator<A>() {
      long i=1;
      @Override
      public boolean hasNext() {
        return true;
      }

      @Override
      public A next() {
        A tmp = f.apply(i);
        i++;
        return tmp;
      }
    };
  }
}



public static class GenerationIterable<A> implements Iterable<A> {
  A a;
  Function<A,A> f;
  public GenerationIterable(A a, Function<A,A> f){
    this.a = a;
    this.f = f;
  }
  int i=0;

  @Override
  public Iterator<A> iterator() {
    return new Iterator<A>() {
      @Override
      public boolean hasNext() {
        return true;
      }

      @Override
      public A next() {
        if (i%2!=0) {
          i++;
          return f.apply(a);
        }
        i++;
        return a;
      }
    };
  }
}



public static class OddIterable extends GenerationIterable<Long>{
  public OddIterable() {
    super(1L, x->x+2);
  }
}


public static record Limit<A>(Iterable<A> itA, long n)
                                     implements Iterable<A>{
  public Iterator<A> iterator(){
    return new Iterator<>(){
      Iterator<A> it = itA.iterator();
      int i = 0;
      @Override
      public boolean hasNext() {
        i++;
        return i<=n && it.hasNext();
      }

      @Override
      public A next() {
        return it.next();
      }
    };
  }
}


public static record Maperable<A,R>(Iterable<A> itA, Function<A,R> f)
                                                 implements Iterable<R>{
  public Iterator<R> iterator(){
    return new Iterator<>(){
      Iterator<A> it = itA.iterator();

      @Override
      public boolean hasNext() {
        return false;
      }

      @Override
      public R next() {
        return null;
      }

    };
  }
}



public static record Filterable<A>(Iterable<A> itA, Predicate<A> p)
                                       implements Iterable<A>{
  public Iterator<A> iterator(){
    return new MyIterator();
  }
  private class MyIterator implements Iterator<A>{
    Iterator<A> it = itA.iterator();
    A theNext = null; 
    MyIterator(){getTheNext();}
    void getTheNext(){
      p.test(theNext);

    }
    public boolean hasNext(){return theNext!=null;}
    public A next(){
      var result = theNext;
      getTheNext();
      return result;}
  }
}
}
