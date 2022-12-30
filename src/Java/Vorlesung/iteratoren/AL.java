package Java.Vorlesung.iteratoren;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Consumer;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.stream.*;

public class AL<E>  implements Iterable<E>{
  @Override public Iterator<E> iterator(){
    return new ALIterator<>(this);
  }
  static private class ALSpliterator<E> implements  Spliterator<E>{
    int from = 0;
    int to = 0;
    AL<E> list;
    ALSpliterator(AL<E> list){
      this.list=list;
      to=list.length();
    }
    public boolean tryAdvance(Consumer<? super E> action){
      if (from >= to)return false;
      var x = list.get(from);
      action.accept(x);
      from++;
      return true;
    }
    public long estimateSize(){return to-from;}

    public int characteristics(){
      return Spliterator.SIZED
              | Spliterator.SUBSIZED
              | Spliterator.NONNULL;
    }

    public Spliterator<E> trySplit(){
      if (to-from<4) return null;
      var mitte = (from+to)/2;
      var result = new ALSpliterator(list);
      result.from = from;
      result.to = mitte;
      this.from = mitte;
      return result;
    }

  }
  public Stream<E> stream(){
    return StreamSupport.stream(new ALSpliterator<>(this),false);
  }
  public Stream<E> parallelStream(){
    return StreamSupport.stream(new ALSpliterator<>(this),true);
  }
  static class ALIterator<E> implements  Iterator<E>{
    int i = 0;
    AL<E> list;
    ALIterator(AL<E> list){this.list=list;}
    @Override public boolean hasNext(){
      return i < list.length();
    }
    @Override public E next(){
      var x = list.get(i);
      i++;
      return x;
    }
  }


  private int size = 0;
  private Object[]store = new Object[10];



  public boolean isEmpty(){return size==0;}



  public int length(){
    return size;
  }



  public E get(int i){
    if (i>=size||i<0) throw new IndexOutOfBoundsException();
    return (E)store[i];
  }



  public E head(){return get(0);}



  public static <E>AL<E> nil(){return new AL<>();}



  public void add(E e){
    if (size>=store.length) enlargeStore();
    store[size++] = e;
  }



  private void enlargeStore(){
    Object[]newStore = new Object[store.length+10];
    for (int i=0;i<size;i++) newStore[i]=store[i];
    store=newStore;
  }




  public static <E>AL<E> of(E...es){
    AL<E> r = nil();
    for (var e:es) r.add(e);
    return r;
  }




  @Override public String toString(){
    StringBuffer result = new StringBuffer("[");
    boolean first = true;
    for (var i=0;i<size;i++){
      if (first) first = false;else result.append(", ");
      result.append(store[i]);
    }
    result.append("]");
    return result.toString();
  }



  @Override public boolean equals(Object o){
    if (o.getClass()!=AL.class) return false;
    var that = (AL<E>)o;
    if (this.length()!=that.length()) return false;
    for (int i=0;i<size;i++){
      if (!this.get(i).equals(that.get(i)))return false;
    }
    return true;
  }



  public E last(){
    return get(size-1);
  }



  public AL<E> append(AL<E> that){
    AL<E> rs = nil();
    for (int i = 0; i<size;i++) rs.add(get(i));
    for (int i = 0; i<that.length();i++) rs.add(that.get(i));
    return rs;
  }





  public void addAll(AL<E> that){
    for (int i = 0; i<that.length();i++) add(that.get(i));
  }




  public AL<E> drop(int i){
    AL<E> rs = nil();
    for (;i>=0&&i<size;i++)rs.add(get(i));
    return rs;
  }



  public AL<E> tail(){return drop(1);}



  public AL<E> take(int i){
    AL<E> rs = nil();
    for (var j=0;j<size&&j<i;j++)rs.add(get(j));
    return rs;
  }




  public AL<E> sublist(int from, int length) {
    return drop(from).take(length);
  }



  public AL<E> reverse(){
    AL<E> rs = nil();
    for (var i=size-1;i>=0;i--)rs.add(get(i));
    return rs;
  }



  public AL<E> intersperse(E e){
    AL<E> rs = nil();
    var first  = true;
    for (var i=0;i<size;i++){
      if (first) first=false; else rs.add(e);
      rs.add(get(i));
    }
    return rs;
  }



  public boolean isPrefixOf(AL<E> that){
    if (this.length()>that.length()) return false;
    for (var i=0;i<this.length();i++) if (!this.get(i).equals(that.get(i))) return false;
    return true;
  }



  public boolean isSuffixOf(AL<E> that){
    return this.reverse().isPrefixOf(that.reverse());
  }



  public boolean isInfixOf(AL<E> that){
    if (this.length()>that.length()) return false;

    for (var j=0;j<=that.length()-this.length();j++){
      for (var i=0;i<this.length();i++){
        if (!this.get(i).equals(that.get(i+j)))
          break;
      }
      return true;
    }
    return false;
  }



  public AL<E> rotate(){
    AL<E> rs = nil();
    if (length()>0){
      for (var i=1;i<this.length();i++) rs.add(get(i));
      rs.add(get(0));

    }
    return rs;
  }



  public AL<AL<E>> tails(){
    if (isEmpty()) return of(nil());
    AL<AL<E>> rs = nil();
    for (int i=0;i<=size;i++){
      AL<E> ts = nil();
      for (int j=i;j<size;j++){
        ts.add(get(j));
      }
      rs.add(ts);
    }
    return rs;
  }



  public void forEach(Consumer<? super E> con) {
    for (int i=0;i<size;i++)
      con.accept(get(i));
  }




  public boolean containsWith(Predicate< ? super E> p) {
    for (int i=0;i<size;i++) if (p.test(get(i)))return true;
    return false;
  }




  public boolean contains(E el) {
    return containsWith(x->x.equals(el));
  }




  public AL<E> dropWhile(Predicate< ? super E> p){
    var drop=true;
    AL<E> rs = nil();
    for (var i=0;i<size;i++){
      if(drop&&!p.test(get(i))) drop=false;
      if (!drop) rs.add(get(i));
    }
    return rs;
  }



  public AL<E> takeWhile(Predicate< ? super E> p){
    AL<E> rs = nil();
    for (var i=0;i<size;i++){
      if(!p.test(get(i))) break;
      rs.add(get(i));
    }
    return rs;
  }



  public AL<E> filter(Predicate<? super E> p){
    AL<E> rs = nil();
    for (var i=0;i<size;i++){
      if(!p.test(get(i))) continue;
      rs.add(get(i));
    }
    return rs;
  }



  public <R> AL<R> map(Function<? super E, ? extends R> f){
    AL<R> rs = nil();
    for (var i=0;i<size;i++){
      rs.add(f.apply(get(i)));
    }
    return rs;

  }



  static public record Pair<A,B>(A fst,B snd){
    @Override public String toString(){return "("+fst()+", "+snd()+")";}
  }




  public <B> AL<Pair<E,B>> zip(AL<B> that){
    AL<Pair<E,B>> rs = nil();
    for (var i=0;i<this.length()&&i<that.length();i++)
      rs.add(new Pair<>(this.get(i),that.get(i)));
    return rs;
  }





  public Pair<AL<E>,AL<E>> span(Predicate<? super E> p){
    return new Pair<>(takeWhile(p),dropWhile(p));
  }





  public Pair<AL<E>,AL<E>> partition(Predicate<? super E> p){
    AL<E> pos = nil();
    AL<E> neg = nil();
    for (var i=0;i<size;i++)
      if (p.test(get(i))) pos.add(get(i)); else neg.add(get(i));
    return new Pair<>(pos,neg);
  }





  public boolean isSorted(Comparator<? super E> cmp){
    for(var i=1;i<size;i++){
      if (cmp.compare(get(i-1),get(i))>0)return false;
    }
    return true;
  }





  public AL<E> qsort(Comparator<? super E> cmp){
    if (isEmpty()) return nil();
    var p = drop(1).partition(x->cmp.compare(get(0),x)>=0);
    return p.fst().qsort(cmp).append(of(get(0)).append(p.snd().qsort(cmp)));
  }




}