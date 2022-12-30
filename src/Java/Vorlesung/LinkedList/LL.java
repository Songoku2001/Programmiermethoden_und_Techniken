package Java.Vorlesung.LinkedList;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Consumer;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;


public sealed interface LL<E> extends Iterable<E>
        permits LL.Nil, LL.Cons{

  @Override default Iterator<E> iterator(){
    return new LLIterator<>(this);
  }
  static class LLIterator<E> implements Iterator<E>{
    LL<E> it;
    LLIterator(LL<E> it){this.it = it;}

    @Override public boolean hasNext(){
      return !it.isEmpty();
    }
    @Override public E next(){
      var x = it.head();
      it = it.tail();
      return x;
    }
  }


  static public final record Nil<E>() implements LL<E>{
    @Override public String toString(){return "[]";}
  }

  static public final record Cons<E>(E hd,LL<E> tl) implements LL<E>{
    @Override public String toString(){return show();}
  }

  default boolean isEmpty(){return this instanceof Nil;}

  default E head(){
    if (this instanceof Cons<E> c) return c.hd();
    throw new NoSuchElementException("head on empty list");
  }

  default LL<E> tail(){
    if (this instanceof Cons<E> c) return c.tl();
    throw new NoSuchElementException("tail on empty list");
  }

  static final LL nil = new Nil<>();
  static <E>LL<E> nil(){return nil;}

  static <E>LL<E> cons(E hd,LL<E> tl){return new Cons<>(hd,tl);}

  static <E>LL<E> of(E...es){
    LL<E> r = nil();
    for (int i=es.length-1;i>=0;i--) r = cons(es[i],r);
    return r;
  }

  default String show(){
    StringBuffer result = new StringBuffer("[");
    boolean first = true;
    for (var it = this;!it.isEmpty();it=it.tail()){
      if (first) first = false;else result.append(", ");
      result.append(it.head());
    }
    result.append("]");
    return result.toString();
  }

  default int length(){
    if (isEmpty()) return 0;
    return 1+tail().length();
  }

  default E last(){
    if (tail().isEmpty()) return head();
    return tail().last();
  }

  default LL<E> append(LL<E> that){
    if (isEmpty()) return that;
    return cons(head(),tail().append(that));
  }

  default LL<E> drop(int i){
    if (i==0 || isEmpty()) return this;
    return tail().drop(i-1);
  }

  default LL<E> take(int i){
    if (isEmpty()||i<=0) return nil();
    return cons(head(),tail().take(i-1));
  }

  default LL<E> sublist(int from, int length) {
    return drop(from).take(length);
  }

  default LL<E> reverse(){
    if (isEmpty()) return nil();
    return tail().reverse().append(cons(head(),nil()));
  }

  default LL<E> intersperse(E e){
    if (isEmpty()||tail().isEmpty()) return this;
    return cons(head(),cons(e,tail().intersperse(e)));
  }

  default boolean isPrefixOf(LL<E> that){
    if (isEmpty()) return true;
    if (that.isEmpty()) return false;
    return head().equals(that.head())&&tail().isPrefixOf(that.tail());
  }

  default boolean isSuffixOf(LL<E> that){
    return this.reverse().isPrefixOf(that.reverse());
  }

  default boolean isInfixOf(LL<E> that){
    return isPrefixOf(that)||
            (!that.isEmpty()&&isInfixOf(that.tail()));
  }

  default E get(int i){
    if (isEmpty()||i<0) throw new IndexOutOfBoundsException();
    if (i==0) return head();
    return tail().get(i-1);
  }

  default LL<E> rotate(){return tail().append(of(head()));}

  default LL<LL<E>> tails(){
    if (isEmpty()) return of(nil());
    return cons(this,tail().tails());
  }

  default void forEach(Consumer<? super E> con) {
    if (isEmpty()) return;
    con.accept(head());
    tail().forEach(con);
  }

  default boolean containsWith(Predicate< ? super E> p) {
    if (isEmpty()) return false;
    return p.test(head())||tail().containsWith(p);
  }

  default boolean contains(E el) {
    return containsWith(x->x.equals(el));
  }

  default LL<E> dropWhile(Predicate< ? super E> p){
    if (!isEmpty() && p.test(head()))
      return tail().dropWhile(p);
    return this;
  }

  default LL<E> takeWhile(Predicate< ? super E> p){
    if (!isEmpty() && p.test(head()))
      return cons(head(),tail().takeWhile(p));
    return nil();
  }

  default LL<E> filter(Predicate<? super E> p){
    if (isEmpty()) return nil();
    if (p.test(head())) return cons(head(), tail().filter(p));
    return tail().filter(p);
  }

  default <R> LL<R> map(Function<? super E, ? extends R> f){
    if (!isEmpty())
      return cons(f.apply(head()), tail().map(f));
    return nil();
  }

  static public record Pair<A,B>(A fst,B snd){
    @Override public String toString(){return "("+fst()+", "+snd()+")";}
  }

  default <B> LL<Pair<E,B>> zip(LL<B> that){
    if (isEmpty()||that.isEmpty()) return nil();
    return cons(new Pair<>(head(),that.head()),tail().zip(that.tail()));
  }

  default Pair<LL<E>,LL<E>> span(Predicate<? super E> p){
    if (isEmpty()) return new Pair<>(nil(),nil());
    if (!p.test(head())) return new Pair<>(nil(),this);
    var r = tail().span(p);
    return new Pair<>(cons(head(),r.fst()),r.snd());
  }

  default Pair<LL<E>,LL<E>> partition(Predicate<? super E> p){
    if (isEmpty()) return new Pair<>(nil(),nil());
    var r = tail().partition(p);
    return p.test(head())
            ?new Pair<>(cons(head(),r.fst()),r.snd())
            :new Pair<>(r.fst(),cons(head(),r.snd()))
            ;
  }

  default boolean isSorted(Comparator<? super E> cmp){
    return isEmpty()
            ||tail().isEmpty()
            ||cmp.compare(head(),tail().head())<=0 && tail().isSorted(cmp);
  }

  default LL<E> qsort(Comparator<? super E> cmp){
    if (isEmpty()) return nil();
    var p = tail().partition(x->cmp.compare(head(),x)>=0);
    return p.fst().qsort(cmp).append(cons(head(),p.snd().qsort(cmp)));
  }
}
