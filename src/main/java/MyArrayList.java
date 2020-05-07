import java.lang.reflect.Array;
import java.util.*;
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
        if(size==0) {
            return true;
        }
        return false;
    }

    public boolean contains(final Object o) {
        for(T element : tempTable) {
            if(element.equals(o)) {
                return true;
            }
        }
        return false;
    }

    public Iterator<T> iterator() {
        return null;
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

    public boolean remove(final Object o) {
        return false;
    }

    public boolean containsAll(final Collection<?> c) {
        return false;
    }

    public boolean addAll(final Collection<? extends T> c) {
        return false;
    }

    public boolean addAll(final int index, final Collection<? extends T> c) {
        return false;
    }

    public boolean removeAll(final Collection<?> c) {
        return false;

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
        tempTable[index]=element;
        return element;
    }

    public void add(final int index, final T element) {
        Object[] createTable = new Object[size+1];
        System.arraycopy(tempTable, 0, createTable, 0, index);
        createTable[index] = element;
        System.arraycopy(tempTable, index, createTable, index+1, size-index);
        size++;
        tempTable = (T[]) createTable;
    }

    public T remove(final int index) {
        return null;
    }

    public int indexOf(final Object o) {
        return 0;
    }

    public int lastIndexOf(final Object o) {
        return 0;
    }

    public ListIterator<T> listIterator() {
        return null;
    }

    public ListIterator<T> listIterator(final int index) {
        return null;
    }

    public List<T> subList(final int fromIndex, final int toIndex) {
        if (fromIndex>toIndex){
            throw new IllegalArgumentException("fromIndex > toIndex");
        }
        if (fromIndex < 0)
            throw new IndexOutOfBoundsException("fromIndex = " + fromIndex);
        if (toIndex > size)
            throw new IndexOutOfBoundsException("toIndex = " + toIndex);
        if(fromIndex==toIndex){
            return null;
        }

        T[] table = (T[]) new Object[toIndex-fromIndex];

        for(int i=fromIndex; i<toIndex; i++){
           table[i-fromIndex]=tempTable[i];
        }
        return new MyArrayList<T>(Arrays.asList(table));
    }

    public Spliterator<T> spliterator() {
        return null;
    }
}
