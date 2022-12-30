package SS2021.Java.Blatt1;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Consumer;
import java.util.Comparator;




public class AL<E>  {



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
    if (isEmpty()) throw new NoSuchElementException("nicht gefunden");
    return this.get(length()-1);
  }




  public AL<E> append(AL<E> that){
    AL<E> rs = nil();
    for (int i=0; i<this.size; i++){
      rs.add(this.get(i));
    }
    for (int i=0; i< that.size; i++){
      rs.add(that.get(i));
    }
    return rs;
  }




  public void addAll(AL<E> that){
    for (int i = 0; i < that.size; i++) {
      this.add(that.get(i));
    }
  }




  public AL<E> drop(int i){
    AL<E> rs = nil();
    if (i>size){
      return nil();
    }
    if (i<0){
      return this;
    }
    for (int x=i; x<this.size; x++){
      rs.add(this.get(x));
    }
    return rs;
  }


  public static void main(String[] args) {
    AL<Integer> ls = of(1, 2, 3, 4, 5);
    System.out.println(ls.drop(3));
  }




  public AL<E> tail(){return drop(1);}


    
  public AL<E> take(int i){
    AL<E> rs = nil();
    if (i>this.size) {
      return this;
    }
    if (i<=0) {
      return nil();
    }
    for (int k=0; k<i; k++) {
      rs.add(this.get(k));
    }
    return rs;
  }




  public AL<E> sublist(int from, int length) {
    return drop(from).take(length);
    //return nil();
  }



  public AL<E> reverse(){
    AL<E> rs = nil();
    if (isEmpty()) {
      return nil();
    }
    for (int i=size-1; i>0; i--) {
      rs.add(get(i));
    }
    return rs;
  }



  public AL<E> intersperse(E e){
    AL<E> rs = nil();
    boolean first = true;

    for (int i=0; i<size; i++) {
      if (first){
        first=false;
      }
      else {
        rs.add(e);
      }
      rs.add(get(i));
    }
    return rs;
  }



  public boolean isPrefixOf(AL<E> that) {
    if (this.size > that.size) {
      return false;
    }
    for (int i = 0; i < this.size; i++) {
      if (!this.get(i).equals(that.get(i))) {
        return false;
      }
    }
    return true;
  }



  public boolean isSuffixOf(AL<E> that){
    this.reverse();
    that.reverse();
    return this.isPrefixOf(that);
  }



  public boolean isInfixOf(AL<E> that){
    if (that.isEmpty()) {
      return false;
    }
    for (int i = 0; i < that.size ; i++) {
      if (this.isPrefixOf(that.drop(i).take(this.size)))
        return true;
    }
    return false;
  }



  public AL<E> rotate(){
    AL<E> rs = nil();
    for (int i=1; i<this.size; i++) {
      rs.add(get(i));
    }
    rs.add(get(0));
    return rs;
  }



  public AL<AL<E>> tails(){
    return of(nil());
  }


  
  public void forEach(Consumer<? super E> con) {
    for (int i = 0; i < this.size; i++) {
      con.accept(this.get(i));
    }
  }




  public boolean containsWith(Predicate< ? super E> p) {
    for (int i = 0; i < size; i++) {
      if (p.test(get(i)))
        return true;
    }
    return false;
  }





  public boolean contains(E el) {
    return containsWith(x -> x.equals(el));
  }
  



  public AL<E> dropWhile(Predicate< ? super E> p){
    if (this.isEmpty()) {
      return nil();
    }
    for (int i=0; i<this.size; i++) {
      if (!p.test(this.get(i))) {
        return (drop(i));
      }
    }
    return nil();
  }



  public AL<E> takeWhile(Predicate< ? super E> p){
    AL<E> rs = nil();
    if (this.isEmpty()) {
      return nil();
    }
    for (int i=0; i<this.size; i++) {
      if (p.test(this.get(i))) {
        rs.add(this.get(i));
      }
    }
    return rs;
  }



  public AL<E> filter(Predicate<? super E> p){
    AL<E> rs = nil();
    for (int i = 0; i < this.size; i++) {
      if (p.test(this.get(i))) {
        rs.add(get(i));
      }
    }
    return rs;
  }



  public <R> AL<R> map(Function<? super E, ? extends R> f){
    AL<R> rs = nil();
    if (this.isEmpty()) {
      return nil();
    }
    for (int i = 0; i < this.size; i++) {
      rs.add(f.apply(this.get(i)));
    }
    return rs;
  }



  static public record Pair<A,B>(A fst,B snd){
    /*@Override*/ public String toString(){return "("+fst()+", "+snd()+")";}
  }    



  public <B> AL<Pair<E,B>> zip(AL<B> that) {
    AL<Pair<E,B>> rs = nil();
    if (this.isEmpty() || that.isEmpty()) {
      return nil();
    }
    if (this.size>that.size) {
      for (int i=0; i< that.size; i++) {
        rs.add(new Pair<>(this.get(i), that.get(i)));
      }
    }
    else {
      for (int i=0; i<this.size; i++) {
        rs.add(new Pair<>(this.get(i), that.get(i)));
      }
    }
    return rs;
  }




  public Pair<AL<E>,AL<E>> span(Predicate<? super E> p){
    if (this.isEmpty()) {
      return new Pair<>(nil(),nil());
    }
    return new Pair<>(this.takeWhile(p), this.dropWhile(p));
  }




  public Pair<AL<E>,AL<E>> partition(Predicate<? super E> p){
    return new Pair<>(nil(),nil());
  }




  public boolean isSorted(Comparator<? super E> cmp){
    if (this.isEmpty()) {
      return true;
    }
    for (int i=0; i<this.size-1; i++) {
      if (cmp.compare(this.get(i), this.get(i+1))>0) {
        return false;
      }
    }
    return true;
  }




  public AL<E> qsort(Comparator<? super E> cmp){
    return nil();
  }
}