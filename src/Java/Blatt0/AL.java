package Java.Blatt0;

import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class AL<E> implements List<E> {
  protected E[] store = (E[]) new Object[5];
  protected int size = 0;

  public AL(E... es) {
    for (E e : es)
      add(e);
  }

  private void mkNewStore() {
    E[] newStore = (E[]) new Object[size + 5];
    for (int i = 0; i < store.length; i++)
      newStore[i] = store[i];
    store = newStore;
  }

  public void add(E e) {
    if (store.length <= size)
      mkNewStore();
    store[size++] = e;
  }

  @Override
  public void addAll(List<E> cs) {

  }

  @Override
  public void remove(int i) {

  }

  @Override
  public void insert(int i, E e) {

  }

  @Override
  public boolean contains(E e) {
    return false;
  }

  @Override
  public boolean containsWith(Predicate<E> pred) {
    return false;
  }

  @Override
  public void reverse() {

  }

  @Override
  public void forEach(Consumer<? super E> consumer) {

  }

  @Override
  public boolean startsWith(List<E> that) {
    return false;
  }

  @Override
  public boolean endsWith(List<E> that) {
    return false;
  }

  @Override
  public List<E> sublist(int i, int j) {
    return null;
  }

  @Override
  public void sortBy(Comparator<? super E> cmp) {

  }

  public int size() {
    return size;
  }

  public E get(int i) {
    return store[i];
  }
}