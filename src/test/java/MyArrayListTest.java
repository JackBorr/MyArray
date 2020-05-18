
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import org.junit.jupiter.api.Test;

class MyArrayListTest {

  @Test
  void testSize() {
    // given
    String[] table = {"raz", "dwa", "trzy"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));

    // when
    int size = myArray.size();

    // then
    assertEquals(table.length, size);
  }

  @Test
  void testIsEmptyEmptyArray() {
    // given
    MyArrayList<String> myArray = new MyArrayList<String>();

    // when
    boolean isEmpty = myArray.isEmpty();

    // then
    assertTrue(isEmpty);
  }

  @Test
  void testIsEmptyNotEmptyArray() {
    // given
    String[] table = {"raz", "dwa", "trzy"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));

    // when
    boolean isEmpty = myArray.isEmpty();

    // then
    assertFalse(isEmpty);
  }

  @Test
  void testContainsNotContainObject() {
    // given
    String[] table = {"raz", "dwa", "trzy"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));

    // when
    boolean contain = myArray.contains("test");

    // then
    assertFalse(contain);
  }

  @Test
  void testContainsMethodContainCbject() {
    // given
    String[] table = {"raz", "dwa", "trzy"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));

    // when
    boolean contain = myArray.contains("dwa");

    // then
    assertTrue(contain);
  }

  @Test
  void testGetMethod() {
    // given
    String[] table = {"raz", "dwa", "trzy"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));

    // when
    String element = myArray.get(1);

    // then
    assertEquals("dwa", element);
    assertThrows(IndexOutOfBoundsException.class, () -> myArray.get(-1));
    assertThrows(IndexOutOfBoundsException.class, () -> myArray.get(3));
  }

  @Test
  void testAddMethodWithIdAndObjectParams() {
    // given
    String[] table = {"raz", "dwa", "trzy"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));

    // when
    myArray.add(1, "add");

    // then
    assertEquals(4, myArray.size());
    assertEquals("add", myArray.get(1));
    assertThrows(IndexOutOfBoundsException.class, () -> myArray.add(-1, "test"));
    assertThrows(IndexOutOfBoundsException.class, () -> myArray.add(8, "test"));
  }

  @Test
  void testAddMethodWithObjectParam() {
    // given
    String[] table = {"raz", "dwa", "trzy"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));

    // when
    int index = myArray.size();
    myArray.add("add");

    // then
    assertEquals(4, myArray.size());
    assertEquals("add", myArray.get(index));
  }

  @Test
  void testSublistFromIndexAndToIndexHasTheSameValue() {
    // given
    String[] table = {"raz", "dwa", "trzy"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));

    // when
    List<String> elements = myArray.subList(1, 1);

    // then
    assertNull(elements);
  }

  @Test
  void testSublistFromIndexBiggerThanToIndex() {
    // given
    String[] table = {"raz", "dwa", "trzy"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));

    // when + then
    assertThrows(IllegalArgumentException.class, () -> {
      myArray.subList(2, 1);
    });
  }

  @Test
  void testSublistFromIndexLessThanZero() {
    // given
    String[] table = {"raz", "dwa", "trzy"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));

    // when + then
    assertThrows(IndexOutOfBoundsException.class, () -> {
      myArray.subList(-8, 1);
    });
  }

  @Test
  void testSublistToIndexBiggerThanSize() {
    // given
    String[] table = {"raz", "dwa", "trzy"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));

    // when + then
    assertThrows(IndexOutOfBoundsException.class, () -> {
      myArray.subList(1, 15);
    });
  }

  @Test
  void testSublistFromIndexLessThanToIndex() {
    // given
    String[] table = {"raz", "dwa", "trzy"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
    String[] subTable = {"dwa", "trzy"};
    MyArrayList<String> subMyArray = new MyArrayList<String>(Arrays.asList(subTable));

    // when
    List<String> createSub = myArray.subList(1, 3);

    // then
    assertEquals(subMyArray, createSub);
  }

  @Test
  void testEqualsReturnTrue() {
    // given
    String[] table = {"raz", "dwa", "trzy"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
    MyArrayList<String> myArray1 = new MyArrayList<String>(Arrays.asList(table));
    List<String> myArray3 = myArray;

    // when + then
    assertEquals(myArray, myArray1);
    assertEquals(myArray1, myArray);
    assertEquals(myArray, myArray3);
  }

  @Test
  void testEqualsReturnFalse() {
    // given
    String[] table = {"raz", "dwa", "trzy"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
    MyArrayList<String> myArray1 = new MyArrayList<String>(Arrays.asList(table));
    MyArrayList<String> myArray2 = new MyArrayList<String>(Arrays.asList(table));

    // when
    myArray.add("cztery");
    myArray2.set(0, "raz_raz");

    // then
    assertNotEquals(myArray, myArray1);
    assertNotEquals(myArray1, myArray);
    assertNotEquals(myArray, null);
    assertNotEquals(myArray, new String("test"));
    assertNotEquals(myArray1, myArray2);
  }

  @Test
  void testHashCode() {
    // given
    String[] table = {"raz", "dwa", "trzy"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
    MyArrayList<String> myArray1 = new MyArrayList<String>(Arrays.asList(table));

    // when
    boolean isHashCodesEqual = myArray.hashCode() == myArray1.hashCode();

    // then
    assertEquals(myArray.equals(myArray1), isHashCodesEqual);
  }

  @Test
  void testIndexOf() {
    // given
    String[] table = {"raz", "dwa", "trzy"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));

    // when
    int index1 = myArray.indexOf("trzy");
    int index2 = myArray.indexOf("pięc");
    int index3 = myArray.indexOf(null);

    // then
    assertEquals(2, index1);
    assertEquals(-1, index2);
    assertEquals(-1, index3);
  }

  @Test
  void testLastIndexOf() {
    // given
    String[] table = {"raz", "dwa", "trzy", "dwa", "sześć"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));

    // when
    int index1 = myArray.lastIndexOf("dwa");
    int index2 = myArray.lastIndexOf("pięc");
    int index3 = myArray.lastIndexOf(null);

    // then
    assertEquals(3, index1);
    assertEquals(-1, index2);
    assertEquals(-1, index3);
  }

  @Test
  void testRemoveIndexParam() {
    // given
    String[] table = {"raz", "dwa", "trzy"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
    String[] subTable = {"raz", "dwa"};
    MyArrayList<String> subMyArray = new MyArrayList<String>(Arrays.asList(subTable));

    // when
    myArray.remove(2);

    // then
    assertEquals(subMyArray, myArray);
    assertThrows(IndexOutOfBoundsException.class, () -> myArray.remove(-1));
    assertThrows(IndexOutOfBoundsException.class, () -> myArray.remove(8));
  }

  @Test
  void testRemoveObjectParam() {
    // given
    String[] table = {"raz", "dwa", "trzy"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
    String[] subTable = {"raz", "trzy"};
    MyArrayList<String> subMyArray = new MyArrayList<String>(Arrays.asList(subTable));

    // when
    myArray.remove("dwa");

    // then
    assertEquals(subMyArray, myArray);
    assertFalse(myArray.remove("pięć"));
  }

  @Test
  void testRemoveAll() {
    // given
    String[] table = {"raz", "dwa", "trzy", "cztery"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
    String[] subTable = {"dwa", "trzy"};
    MyArrayList<String> subMyArray = new MyArrayList<String>(Arrays.asList(subTable));
    String[] tableToRemove = {"raz", "pięć", "sześć", "cztery"};

    // when
    myArray.removeAll(Arrays.asList(tableToRemove));

    // then
    assertEquals(subMyArray, myArray);
  }

  @Test
  void testContainAll() {
    String[] table = {"raz", "dwa", "trzy", "cztery"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
    String[] subTable = {"dwa", "trzy", "trzy", "raz"};
    MyArrayList<String> subMyArray = new MyArrayList<String>(Arrays.asList(subTable));

    // when
    boolean isContainAll = myArray.containsAll(subMyArray);

    // then
    assertTrue(isContainAll);
  }

  @Test
  void testAddAllParamIndexAndList() {
    // given
    String[] table = {"raz", "dwa", "trzy", "cztery"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
    String[] addTable = {"raz", "dwa", "trzy", "raz", "dwa", "cztery"};
    MyArrayList<String> subMyArray = new MyArrayList<String>(Arrays.asList(addTable));
    String[] tableToAdd = {"raz", "dwa"};

    // when
    myArray.addAll(3, Arrays.asList(tableToAdd));
    int indexUnderZero = -1;
    int indexBiggerThanSize = 7;

    // then
    assertEquals(subMyArray, myArray);
    assertThrows(IndexOutOfBoundsException.class, () -> myArray.addAll(indexUnderZero, subMyArray));
    assertThrows(IndexOutOfBoundsException.class, () -> myArray.addAll(indexBiggerThanSize, subMyArray));
  }

  @Test
  void testAddAllParamList() {
    // given
    String[] table = {"raz", "dwa", "trzy", "cztery"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
    String[] addTable = {"raz", "dwa", "trzy", "cztery", "raz", "dwa"};
    MyArrayList<String> subMyArray = new MyArrayList<String>(Arrays.asList(addTable));
    String[] tableToAdd = {"raz", "dwa"};

    // when
    myArray.addAll(Arrays.asList(tableToAdd));

    // then
    assertEquals(subMyArray, myArray);
  }

  @Test
  void testSort() {
    // given
    String[] table = {"raz", "dwa", "trzy", "cztery"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
    String[] subTable = {"cztery", "dwa", "raz", "trzy"};
    MyArrayList<String> subMyArray = new MyArrayList<String>(Arrays.asList(subTable));

    // when
    myArray.sort(String::compareTo);

    // then
    assertEquals(myArray, subMyArray);
  }

  @Test
  void testReplaceAll() {
    // given
    String[] table = {"raz", "dwa", "trzy", "cztery"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
    String[] subTable = {"RAZ", "DWA", "TRZY", "CZTERY"};
    MyArrayList<String> subMyArray = new MyArrayList<String>(Arrays.asList(subTable));

    // when
    myArray.replaceAll(s -> s.toUpperCase());

    // then
    assertEquals(subMyArray, myArray);
  }

  @Test
  void testRetainAll() {
    // given
    String[] table = {"raz", "dwa", "trzy", "cztery", "dwa"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
    String[] retainTable = {"raz", "dwa", "dwa"};
    MyArrayList<String> retainMyArray = new MyArrayList<String>(Arrays.asList(retainTable));
    String[] tableToAdd = {"raz", "dwa", "pięć"};

    // when
    myArray.retainAll(Arrays.asList(tableToAdd));

    // then
    assertEquals(retainMyArray, myArray);
  }

  @Test
  void testToArrayReturnTArray() {
    // given
    MyArrayList<String> myArray = new MyArrayList<String>();
    String[] table = {"raz", "dwa"};

    // when
    myArray.add("raz");
    myArray.add("dwa");
    Object[] createTable = myArray.toArray();

    // then
    assertTrue(Arrays.equals(table, createTable));
  }

  @Test
  void testToArrayReturnT1Array() {
    // given
    String[] table = {"raz", "dwa", "trzy", "cztery"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));

    // when
    String[] createTable = myArray.toArray(new String[4]);

    // then
    assertTrue(Arrays.equals(table, createTable));
  }

  @Test
  void testIteratorHasNextEmptyCollection() {
    // given
    MyArrayList<String> myArray = new MyArrayList<String>();
    Iterator<String> iterator = myArray.iterator();

    // when
    boolean isHasNext = iterator.hasNext();

    // then
    assertFalse(isHasNext);
  }

  @Test
  void testIteratorHasNextNoEmptyCollection() {
    // given
    String[] table = {"raz", "dwa", "trzy", "cztery"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
    Iterator<String> iterator = myArray.iterator();

    // when
    boolean isHasNext = iterator.hasNext();

    // then
    assertTrue(isHasNext);
  }

  @Test
  void testIteratorHasNextNoEmptyCollectionOneNullElement() {
    // given
    String[] table = {"raz", "dwa", null};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
    Iterator<String> iterator = myArray.iterator();

    // when
    iterator.next();
    iterator.next();
    boolean isHasNext = iterator.hasNext();

    // then
    assertFalse(isHasNext);
  }

  @Test
  void testIteratorNextNoEmptyCollection() {
    // given
    String[] table = {"raz"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
    Iterator<String> iterator = myArray.iterator();

    // when + then
    assertTrue(iterator.hasNext());
    assertEquals("raz", iterator.next());
    assertFalse(iterator.hasNext());
  }

  @Test
  void testIteratorWithIndexHasNextEmptyCollection() {
    // given
    MyArrayList<String> myArray = new MyArrayList<String>();
    ListIterator<String> iterator = myArray.listIterator(0);

    // when
    boolean isHasNext = iterator.hasNext();

    // then
    assertFalse(isHasNext);
  }

  @Test
  void testListIteratorWithIndexHasNextNoEmptyCollection() {
    // given
    String[] table = {"raz", "dwa", "trzy", "cztery"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
    ListIterator<String> iterator = myArray.listIterator();

    // when
    boolean isHasNext = iterator.hasNext();

    // then
    assertTrue(isHasNext);
    assertEquals("raz", iterator.next());
  }

  @Test
  void testIteratorWithIndexHasNextNoEmptyCollection() {
    // given
    String[] table = {"raz", "dwa", "trzy", "cztery"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
    ListIterator<String> iterator = myArray.listIterator(2);

    // when
    boolean isHasNext = iterator.hasNext();

    // then
    assertTrue(isHasNext);
    assertThrows(IndexOutOfBoundsException.class, () -> myArray.listIterator(-1));
    assertThrows(IndexOutOfBoundsException.class, () -> myArray.listIterator(5));
  }

  @Test
  void testIteratorWithIndexHasNextNoEmptyCollectionOneNullElement() {
    // given
    String[] table = {"raz", "dwa", "trzy", "cztery", null};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
    ListIterator<String> iterator = myArray.listIterator(4);

    // when
    boolean isHasNext = iterator.hasNext();

    // then
    assertFalse(isHasNext);
  }

  @Test
  void testIteratorWithIndexHasPreviousEmptyCollection() {
    // given
    MyArrayList<String> myArray = new MyArrayList<String>();
    ListIterator<String> iterator = myArray.listIterator(0);

    // when
    boolean isHasPrevious = iterator.hasPrevious();

    // then
    assertFalse(isHasPrevious);
  }

  @Test
  void testIteratorWithIndexHasPreviousNoEmptyCollection() {
    // given
    String[] table = {"raz", "dwa", "trzy", "cztery"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
    ListIterator<String> iterator = myArray.listIterator(1);

    // when
    boolean isHasPrevious = iterator.hasPrevious();

    // then
    assertTrue(isHasPrevious);
  }

  @Test
  void testIteratorWithIndexHasPreviousNoEmptyCollectionOneNullElement() {
    // given
    String[] table = {null, "raz", "dwa", "trzy", "cztery"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
    ListIterator<String> iterator = myArray.listIterator(1);

    // when
    boolean isHasPrevious = iterator.hasPrevious();

    // then
    assertFalse(isHasPrevious);
  }

  @Test
  void testIteratorWithIndexNextNoEmptyCollection() {
    // given
    String[] table = {"raz", "dwa", "trzy", "cztery"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
    ListIterator<String> iterator = myArray.listIterator(1);

    // when
    String nextString = iterator.next();

    // then
    assertEquals("dwa", nextString);
  }

  @Test
  void testIteratorWithIndexPreviousNoEmptyCollection() {
    // given
    String[] table = {"raz", "dwa", "trzy", "cztery"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
    ListIterator<String> iterator = myArray.listIterator(1);

    // when
    String previousString = iterator.previous();

    // then
    assertEquals("raz", previousString);
  }

  @Test
  void testIteratorWithIndexNextIndex() {
    // given
    String[] table = {"raz", "dwa", "trzy", "cztery"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
    ListIterator<String> iterator = myArray.listIterator(1);

    // when
    iterator.next();
    int nextIndex = iterator.nextIndex();

    // then
    assertEquals(2, nextIndex);
  }

  @Test
  void testIteratorWithIndexPreviousNextIndex() {
    // given
    String[] table = {"raz", "dwa", "trzy", "cztery"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
    ListIterator<String> iterator = myArray.listIterator(2);

    // when
    iterator.previous();
    int previousIndex = iterator.previousIndex();

    // then
    assertEquals(0, previousIndex);
  }

  @Test
  void testIteratorWithIndexAdd() {
    // given
    String[] table = {"raz", "dwa", "trzy"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
    ListIterator<String> iterator = myArray.listIterator(1);

    // when
    iterator.add("add");

    // then
    assertEquals(4, myArray.size());
    assertEquals("add", myArray.get(1));
  }

  @Test
  void testIteratorWithIndexSet() {
    // given
    String[] table = {"raz", "dwa", "trzy"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
    ListIterator<String> iterator = myArray.listIterator(1);

    // when
    iterator.set("add");

    // then
    assertEquals("add", myArray.get(1));
  }

  @Test
  void testIteratorWithIndexRemove() {
    // given
    String[] table = {"raz", "dwa", "trzy"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
    String[] subTable = {"raz", "trzy"};
    MyArrayList<String> subMyArray = new MyArrayList<String>(Arrays.asList(subTable));
    ListIterator<String> iterator = myArray.listIterator(1);

    // when
    iterator.remove();

    // then
    assertEquals(subMyArray, myArray);
  }

  @Test
  void testClear() {
    // given
    String[] table = {"raz", "dwa", "trzy"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));

    // when
    myArray.clear();

    // then
    assertEquals(0, myArray.size());
  }

}
