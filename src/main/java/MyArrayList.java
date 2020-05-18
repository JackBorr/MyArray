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

  private T[] dataTable;
  private int size;

  public MyArrayList() {
    clear();
  }

  public MyArrayList(Collection<? extends T> collection) {
    dataTable = (T[]) collection.toArray();
    size = dataTable.length;
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public boolean contains(final Object object) {
    for (T element : dataTable) {
      if (element.equals(object)) {
        return true;
      }
    }
    return false;
  }

  public Object[] toArray() {
    return Arrays.copyOf(dataTable, size);
  }

  public <T1> T1[] toArray(final T1[] t1Table) {
    if (t1Table.length < size) {
      return (T1[]) Arrays.copyOf(dataTable, size, t1Table.getClass());
    }
    System.arraycopy(dataTable, 0, t1Table, 0, size);
    if (t1Table.length > size) {
      t1Table[size] = null;
    }
    return t1Table;
  }

  public boolean add(final T element) {
    add(size, element);
    return true;
  }

  public void add(final int index, final T element) {
    if (index < 0 || index > size) {
      throw new IndexOutOfBoundsException("index: " + index);
    }
    Object[] createTable = new Object[size + 1];
    System.arraycopy(dataTable, 0, createTable, 0, index);
    createTable[index] = element;
    System.arraycopy(dataTable, index, createTable, index + 1, size - index);
    size++;
    dataTable = (T[]) createTable;
  }

  public boolean remove(final Object object) {
    int index = indexOf(object);
    if (!(index == -1)) {
      remove(index);
      return true;
    } else {
      return false;
    }
  }

  public T remove(final int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("index: " + index);
    }
    Object[] createTable = new Object[size - 1];
    System.arraycopy(dataTable, 0, createTable, 0, index);
    System.arraycopy(dataTable, index + 1, createTable, index, (size - 1) - index);
    size--;
    T toRemove = dataTable[index];
    dataTable = (T[]) createTable;
    return toRemove;
  }

  public boolean removeAll(final Collection<?> collection) {
    Objects.requireNonNull(collection);
    boolean result = false;
    int index;
    for (Object object : collection) {
      for (int i = 0; i < size; i++) {
        if (object.equals(dataTable[i])) {
          dataTable[i] = null;
          result = true;
        }
      }
    }
    dataTable = clean(dataTable);
    size = dataTable.length;
    return result;
  }

  public boolean containsAll(final Collection<?> collection) {
    Objects.requireNonNull(collection);
    int index = 0;
    List<Object> list = (List<Object>) collection;
    for (Object object : list) {
      for (int i = 0; i < size; i++) {
        if (object.equals(dataTable[i])) {
          index++;
          break;
        }
      }
    }

    return index >= collection.size();
  }

  public boolean addAll(final Collection<? extends T> collection) {
    return addAll(size, collection);
  }

  public boolean addAll(final int index, final Collection<? extends T> collection) {
    if (index < 0 || index > size) {
      throw new IndexOutOfBoundsException("index: " + index);
    }
    Objects.requireNonNull(collection);
    int i = index;
    for (Object object : collection) {
      this.add(i, (T) object);
      i++;
    }
    return true;
  }

  public boolean retainAll(final Collection<?> collection) {
    Objects.requireNonNull(collection);
    boolean result = false;
    for (Object element : dataTable) {
      if (collection.contains(element)) {
        continue;
      } else {
        this.remove(element);
        result = true;
      }
    }
    return result;
  }

  public void replaceAll(final UnaryOperator<T> operator) {
    for (int i = 0; i < size; i++) {
      dataTable[i] = operator.apply(dataTable[i]);
    }
  }

  public void sort(final Comparator<? super T> comparator) {
    Arrays.sort(dataTable, comparator);
  }

  public void clear() {
    dataTable = (T[]) new Object[100];
    size = 0;
  }

  public T get(final int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("index: " + index);
    }
    return dataTable[index];
  }

  public T set(final int index, final T element) {
    dataTable[index] = element;
    return element;
  }

  public int indexOf(final Object object) {
    if (object == null) {
      return -1;
    }
    for (int i = 0; i < size; i++) {
      if (dataTable[i].equals(object)) {
        return i;
      }
    }
    return -1;
  }

  public int lastIndexOf(final Object object) {
    if (object == null) {
      return -1;
    }
    for (int i = size - 1; i >= 0; i--) {
      if (dataTable[i].equals(object)) {
        return i;
      }
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
      table[i - fromIndex] = dataTable[i];
    }
    return new MyArrayList<T>(Arrays.asList(table));
  }

  public Iterator<T> iterator() {
    return new MyIterator<T>(this);
  }

  public ListIterator<T> listIterator() {
    return listIterator(0);
  }

  public ListIterator<T> listIterator(final int index) {
    if (index < 0 || index > size) {
      throw new IndexOutOfBoundsException("index: " + index);
    }
    return new MyListIterator<T>(index, this);
  }

  public Spliterator<T> spliterator() {
    return Arrays.spliterator(dataTable);
  }

  @Override
  public int hashCode() {
    return 17 * Arrays.hashCode(dataTable) + 31 * size;
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
    List<T> other = (List<T>) obj;
    if (other.size() != dataTable.length) {
      return false;
    }
    for (int i = 0; i < other.size(); i++) {
      if (!other.get(i).equals(dataTable[i])) {
        return false;
      }
    }
    return true;
  }

  private T[] clean(final T[] table) {
    int readIndex;
    int writeIndex;
    final int sizeTable = readIndex = writeIndex = table.length;
    while (readIndex > 0) {
      final T s = table[--readIndex];
      if (Objects.nonNull(s)) {
        table[--writeIndex] = s;
      }
    }
    return Arrays.copyOfRange(table, writeIndex, sizeTable);
  }

  class MyIterator<T> implements Iterator<T> {

    private final List<T> list;
    private int index = 0;

    MyIterator(List<T> list) {
      this.list = list;
    }

    @Override
    public boolean hasNext() {
      return (index < list.size()) && (list.get(index) != null);
    }

    @Override
    public T next() {
      return list.get(index++);
    }
  }

  class MyListIterator<T> implements ListIterator<T> {

    private final List<T> list;
    private int index;

    MyListIterator(int index, List<T> list) {
      this.index = index;
      this.list = list;
    }

    @Override
    public boolean hasNext() {
      return (index < list.size()) && (list.get(index) != null);
    }

    @Override
    public T next() {
      return list.get(index++);
    }

    @Override
    public boolean hasPrevious() {
      return (index > 0) && (list.get(index - 1) != null);
    }

    @Override
    public T previous() {
      return list.get(--index);
    }

    @Override
    public int nextIndex() {
      return index;
    }

    @Override
    public int previousIndex() {
      return index - 1;
    }

    @Override
    public void remove() {
      list.remove(index);
    }

    @Override
    public void set(T t) {
      list.set(index, t);
    }

    @Override
    public void add(T t) {
      list.add(index, t);
    }
  }

}
