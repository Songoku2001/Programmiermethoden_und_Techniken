package Java.Blatt1;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Consumer;
import java.util.Comparator;

import java.util.NoSuchElementException;





public sealed interface LL<E> permits LL.Nil, LL.Cons {


  static public final record Nil<E>() implements LL<E> {
    @Override
    public String toString() {
      return "[]";
    }
  }


  static public final record Cons<E>(E hd, LL<E> tl) implements LL<E> {
    @Override
    public String toString() {
      return show();
    }
  }


  default boolean isEmpty() {
    return this instanceof Nil;
  }


  default E head() {
    if (this instanceof Cons<E> c) return c.hd();
    throw new NoSuchElementException("head on empty list");
  }


  default LL<E> tail() {
    if (this instanceof Cons<E> c) return c.tl();
    throw new NoSuchElementException("tail on empty list");
  }


  static final LL nil = new Nil<>();

  static <E> LL<E> nil() {
    return nil;
  }


  static <E> LL<E> cons(E hd, LL<E> tl) {
    return new Cons<>(hd, tl);
  }


  static <E> LL<E> of(E... es) {
    LL<E> r = nil();
    for (int i = es.length - 1; i >= 0; i--) r = cons(es[i], r);
    return r;
  }


  default String show() {
    StringBuffer result = new StringBuffer("[");
    boolean first = true;
    for (var it = this; !it.isEmpty(); it = it.tail()) {
      if (first) first = false;
      else result.append(", ");
      result.append(it.head());
    }
    result.append("]");
    return result.toString();
  }


  default int length() {
    if (isEmpty()) {
      return 0;
    }
    var laenge = tail().length();
    return laenge + 1;
  }


  default E last() {
    if (isEmpty()) {
      throw new NoSuchElementException("tail on empty list");
    }
    if (tail().isEmpty()) {
      return head();
    }
    return tail().last();
  }


  default LL<E> append(LL<E> that) {
    if (this instanceof Cons) {
      return cons(head(), tail().append(that));
    } else return that;
  }


  default LL<E> drop(int i) {
    if (isEmpty()) {
      return nil();
    }
    if (i < 1) {
      return this;
    }
    return tail().drop(i - 1);
  }


  default LL<E> take(int i) {
    if (i != 0 && !isEmpty()) {

      return cons(head(), tail().take(i - 1));
    }
    return nil();
  }


  default LL<E> sublist(int from, int length) {
    return this.drop(from).take(length);
  }


  default LL<E> reverse() {
    if (this.isEmpty()) {
      return nil();
    }
    return tail().reverse().append(cons(head(), nil()));
  }


  default LL<E> intersperse(E e) {
    if (this.isEmpty()) {
      return nil();
    }
    if (this.tail() instanceof Nil) {
      return this;
    }
    if (this instanceof Cons) {
      return (cons(head(), nil()).append(cons(e, tail().intersperse(e))));
    }
    return this;
  }


  default boolean isPrefixOf(LL<E> that) {
    if (that.length()>this.length()) {
      return false;
    }
    if (isEmpty()) {
      return true;
    }
    if (that.isEmpty()) {
      return false;
    }
    return this.head().equals(that.head()) && this.tail().isPrefixOf(that.tail());
  }


  default boolean isSuffixOf(LL<E> that) {
    if (isEmpty()) {
      return true;
    }
    if (that.isEmpty()) {
      return false;
    }
    this.reverse();
    that.reverse();
    return this.isPrefixOf(that.tail());
  }


  default boolean isInfixOf(LL<E> that) {
    if (this.isEmpty()) {
      return true;
    }
    if (that.isEmpty()) {
      return false;
    }
    if (!(this.head().equals(that.head()))) {
      return false;
    }
    return this.tail().isInfixOf(that.tail());
  }


  default E get(int i) {
    if (isEmpty()) {
      throw new IndexOutOfBoundsException();
    }
    if (i == 0) {
      return head();
    }
    return tail().get(i - 1);
  }


  default LL<E> rotate() {
    if (isEmpty()) {
      return nil();
    }
    return tail().append(of(head()));
  }


  default LL<LL<E>> tails() {
    if (isEmpty()) {
      return of(nil());
    }
    return cons(this, tail().tails());
  }

  /**
   * Aufgabe 2
   **/

  default void forEach(Consumer<? super E> con) {
    if (isEmpty()) {
      return;
    }
    con.accept(head());
    tail().forEach(con);
  }


  default boolean containsWith(Predicate<? super E> p) {
    if (isEmpty()) {
      return false;
    }
    if (p.test(head())) {
      return true;
    }
    return tail().containsWith(p);
  }


  default boolean contains(E el) {
    return containsWith(x -> x.equals(el));
  }


  default LL<E> dropWhile(Predicate<? super E> p) {
    if (isEmpty()) {
      return nil();
    }
    if (!p.test(head())) {
      return this;
    }
    return tail().dropWhile(p);
  }


  default LL<E> takeWhile(Predicate<? super E> p) {
    if (isEmpty()) {
      return nil();
    }
    if (p.test(head())) {
      return cons(head(), tail().takeWhile(p));
    }
    return tail().takeWhile(p);
  }


  default LL<E> filter(Predicate<? super E> p) {
    if (isEmpty()) {
      return nil();
    }
    if (p.test(head())) {
      return cons(head(), tail().filter(p));
    }
    return tail().filter(p);
  }


  default <R> LL<R> map(Function<? super E, ? extends R> f) {
    if (isEmpty()) {
      return nil();
    }
    return cons(f.apply(head()), tail().map(f));
  }

  /**
   * Aufgabe 3
   **/

  static public record Pair<A, B>(A fst, B snd) {
    @Override
    public String toString() {
      return "(" + fst() + ", " + snd() + ")";
    }
  }


  default <B> LL<Pair<E, B>> zip(LL<B> that) {
    return nil();
  }


  default Pair<LL<E>, LL<E>> span(Predicate<? super E> p) {
    return new Pair<>(nil(), nil());
  }


  default Pair<LL<E>, LL<E>> partition(Predicate<? super E> p) {
    if (this.isEmpty()) {
      return new Pair<>(nil(), nil());
    }
    return p.test(head()) ?
            new Pair<>(cons(head(), tail().partition(p).fst()), tail().partition(p).snd())
            :
            new Pair<>(tail().partition(p).fst(), cons(head(), tail().partition(p).snd()));
  }

  /**
   * Aufgabe 4
   **/


  default boolean isSorted(Comparator<? super E> cmp) {
    if (!(cmp.compare(this.head(), this.tail().head()) > 0)) {
      return false;
    }
    return tail().isSorted(cmp);
  }


  default LL<E> qsort(Comparator<? super E> cmp) {
    if (this.isEmpty()) {
      return nil();
    }
    var p = tail().partition(x -> cmp.compare(head(), x) >= 0);
    return p.fst().qsort(cmp).append(cons(head(), p.snd().qsort(cmp)));
  }
}