
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.UnaryOperator;

public class MyArrayList<T> implements List<T> {

  private T[] tempTable;
  private int size;

  public MyArrayList() {
    tempTable = (T[]) new Object[0];
    size = 0;
  }

  public MyArrayList(Collection<? extends T> c) {

    tempTable = (T[]) c.toArray();
    size = tempTable.length;
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public boolean contains(final Object o) {
    for (T element : tempTable) {
      if (element.equals(o)) {
        return true;
      }
    }
    return false;
  }

  public Object[] toArray() {
    return Arrays.copyOf(tempTable, size);
  }

  public <T1> T1[] toArray(final T1[] a) {
    return null;
  }

  public boolean add(final T t) {
    add(size, t);
    return true;
  }

  public void add(final int index, final T element) {
    Object[] createTable = new Object[size + 1];
    System.arraycopy(tempTable, 0, createTable, 0, index);
    createTable[index] = element;
    System.arraycopy(tempTable, index, createTable, index + 1, size - index);
    size++;
    tempTable = (T[]) createTable;
  }

  public boolean remove(final Object o) {
    if (!(indexOf(o)==-1)){
      this.remove(indexOf(o));
      size--;
      return true;
    } else {
    return false;
    }
  }

  public T remove(final int index) {
    T toRemove = tempTable[index];
    Object[] createTable = new Object[size - 1];
    System.arraycopy(tempTable, 0, createTable, 0, index);
    System.arraycopy(tempTable, index+1, createTable, index, (size - 1) - index);
    size--;
    tempTable = (T[]) createTable;
    return toRemove;
  }

  public boolean removeAll(final Collection<?> c) {
    Objects.requireNonNull(c);
    boolean result = false;
    for (Object o : c) {
      while (this.contains(o)) {
        this.remove(o);
        result = true;
      }
    }
    size = size - c.size();
    return result;
  }

  public boolean containsAll(final Collection<?> c) {
    Objects.requireNonNull(c);
    int index = 0;
    for (Object o : c) {
      if (this.contains(o)) {
        index++;
      }
    }

    return index>=c.size();
  }

  public boolean addAll(final Collection<? extends T> c) {
    return addAll(size, c);
  }

  public boolean addAll(final int index, final Collection<? extends T> c) {
    Objects.requireNonNull(c);
    if (index <0 || index>size){
      return false;
    }
    int i = index;
    for (Object o : c){
      this.add(i, (T)o);
      i++;
      size++;
    }
    return true;
  }

  public boolean retainAll(final Collection<?> c) {
    return false;
  }

  public void replaceAll(final UnaryOperator<T> operator) {

  }

  public void sort(final Comparator<? super T> c) {

  }

  public void clear() {
    tempTable = (T[]) new Object[0];
    size = 0;
  }

  public T get(final int index) {
    return tempTable[index];
  }

  public T set(final int index, final T element) {
    tempTable[index] = element;
    return element;
  }

  public int indexOf(final Object o) {
    if (o == null) {
      return -1;
    }
    for (int i=0; i<size; i++) {
      if(tempTable[i].equals(o)) {
        return i;
      };
    }
    return -1;
  }

  public int lastIndexOf(final Object o) {
    if (o == null) {
      return -1;
    }
    for (int i=size-1; i>=0; i--) {
      if(tempTable[i].equals(o)) {
        return i;
      };
    }
    return -1;
  }

  public List<T> subList(final int fromIndex, final int toIndex) {
    if (fromIndex > toIndex) {
      throw new IllegalArgumentException("fromIndex > toIndex");
    }
    if (fromIndex < 0) {
      throw new IndexOutOfBoundsException("fromIndex = " + fromIndex);
    }
    if (toIndex > size) {
      throw new IndexOutOfBoundsException("toIndex = " + toIndex);
    }
    if (fromIndex == toIndex) {
      return null;
    }

    T[] table = (T[]) new Object[toIndex - fromIndex];

    for (int i = fromIndex; i < toIndex; i++) {
      table[i - fromIndex] = tempTable[i];
    }
    return new MyArrayList<T>(Arrays.asList(table));
  }

  public Iterator<T> iterator() {

    Iterator<T> iterator = new Iterator<T>() {

      int index = 0;

      @Override
      public boolean hasNext() {
        if(index < size && tempTable[index]!=null){
          return true;
        }
        return false;
      }

      @Override
      public T next() {
        return tempTable[index];
      }
    };
    return iterator;
  }

  public ListIterator<T> listIterator() {
    return this.listIterator(0);
  }

  public ListIterator<T> listIterator(final int index) {

    ListIterator<T> listIterator = new ListIterator<T>() {

      int anInt = index;

      @Override
      public boolean hasNext() {
        if(anInt < size && tempTable[anInt]!=null){
          return true;
        }
        return false;
      }

      @Override
      public T next() {
        return tempTable[anInt++];
      }

      @Override
      public boolean hasPrevious() {
        if(anInt > 0 && tempTable[anInt]!=null){
          return true;
        }
        return false;
      }

      @Override
      public T previous() {
        return tempTable[--anInt];
      }

      @Override
      public int nextIndex() {
        return anInt;
      }

      @Override
      public int previousIndex() {
        return anInt -1;
      }

      @Override
      public void remove() {
        MyArrayList.this.remove(anInt);
      }

      @Override
      public void set(T t) {
        MyArrayList.this.set(anInt, t);
      }

      @Override
      public void add(T t) {
        MyArrayList.this.add(t);
      }
    };

    return listIterator;
  }

  public Spliterator<T> spliterator() {
    return null;
  }

  @Override
  public int hashCode() {
    return 17 * Arrays.hashCode(tempTable) + 31 * size;
  }

  @Override
  public boolean equals(final Object obj) {
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof MyArrayList)) {
      return false;
    }
    if (obj == this) {
      return true;
    }
    MyArrayList<T> other = (MyArrayList<T>) obj;
    if (other.size() != tempTable.length) {
      return false;
    }
    for (int i = 0; i < other.size(); i++) {
      if (!other.get(i).equals(tempTable[i])) {
        return false;
      }
    }
    return true;
  }

}
