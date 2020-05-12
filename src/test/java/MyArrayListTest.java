
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;
import org.junit.jupiter.api.Test;

class MyArrayListTest {

  @Test
  void test_size() {
    String[] table = {"raz", "dwa", "trzy"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
    assertEquals(table.length, myArray.size());
  }

  @Test
  void test_isEmpty_empty_array() {
    MyArrayList<String> myArray = new MyArrayList<String>();
    assertTrue(myArray.isEmpty());
  }

  @Test
  void test_isEmpty_not_empty_array() {
    String[] table = {"raz", "dwa", "trzy"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
    assertFalse(myArray.isEmpty());
  }

  @Test
  void test_contains_not_contain_object() {
    String[] table = {"raz", "dwa", "trzy"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
    assertFalse(myArray.contains("test"));
  }

  @Test
  void test_contains_method_contain_object() {
    String[] table = {"raz", "dwa", "trzy"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
    assertTrue(myArray.contains("dwa"));
  }

  @Test
  void test_get_method() {
    String[] table = {"raz", "dwa", "trzy"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
    assertEquals("dwa", myArray.get(1));
  }

  @Test
  void test_add_method_with_id_and_object_params() {
    String[] table = {"raz", "dwa", "trzy"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
    myArray.add(1, "add");
    assertEquals(4, myArray.size());
    assertEquals("add", myArray.get(1));
  }

  @Test
  void test_add_method_with_object_param() {
    String[] table = {"raz", "dwa", "trzy"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
    int index = myArray.size();
    myArray.add("add");
    assertEquals(4, myArray.size());
    assertEquals("add", myArray.get(index));
  }

  @Test
  void test_sublist_fromIndex_and_toIndex_has_the_same_value() {
    String[] table = {"raz", "dwa", "trzy"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
    assertNull(myArray.subList(1, 1));
  }

  @Test
  void test_sublist_fromIndex_bigger_than_toIndex() {
    String[] table = {"raz", "dwa", "trzy"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
    try {
      myArray.subList(2, 1);
    } catch (IllegalArgumentException e) {
      assertEquals("fromIndex > toIndex", e.getMessage());
    }
  }

  @Test
  void test_sublist_fromIndex_less_than_zero() {
    String[] table = {"raz", "dwa", "trzy"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
    try {
      myArray.subList(-8, 1);
    } catch (IndexOutOfBoundsException e) {
      assertEquals("fromIndex = -8", e.getMessage());
    }
  }

  @Test
  void test_sublist_toIndex_bigger_than_size() {
    String[] table = {"raz", "dwa", "trzy"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
    try {
      myArray.subList(1, 15);
    } catch (IndexOutOfBoundsException e) {
      assertEquals("toIndex = 15", e.getMessage());
    }
  }

  @Test
  void test_sublist_fromIndex_less_than_toIndex() {
    String[] table = {"raz", "dwa", "trzy"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
    String[] subTable = {"dwa", "trzy"};
    MyArrayList<String> subMyArray = new MyArrayList<String>(Arrays.asList(subTable));
    assertEquals(subMyArray, myArray.subList(1, 3));
  }

  @Test
  void test_equals() {
    String[] table = {"raz", "dwa", "trzy"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
    MyArrayList<String> myArray1 = new MyArrayList<String>(Arrays.asList(table));
    assertEquals(myArray, myArray1);
    assertEquals(myArray1, myArray);
    myArray1.add("cztery");
    assertNotEquals(myArray, myArray1);
    assertNotEquals(myArray1, myArray);
  }

  @Test
  void test_HashCode() {
    String[] table = {"raz", "dwa", "trzy"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
    MyArrayList<String> myArray1 = new MyArrayList<String>(Arrays.asList(table));
    assertEquals(myArray.equals(myArray1), myArray.hashCode() == myArray1.hashCode());
  }

  @Test
  void test_indexOf() {
    String[] table = {"raz", "dwa", "trzy"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
    assertEquals(2, myArray.indexOf("trzy"));
    assertEquals(-1, myArray.indexOf("pięc"));
  }

  @Test
  void test_remove_index_param() {
    String[] table = {"raz", "dwa", "trzy"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
    String[] subTable = {"raz", "dwa"};
    MyArrayList<String> subMyArray = new MyArrayList<String>(Arrays.asList(subTable));
    myArray.remove(2);
    assertEquals(subMyArray, myArray);
  }

  @Test
  void test_remove_object_param() {
    String[] table = {"raz", "dwa", "trzy"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
    String[] subTable = {"raz", "trzy"};
    MyArrayList<String> subMyArray = new MyArrayList<String>(Arrays.asList(subTable));
    myArray.remove("dwa");
    assertEquals(subMyArray, myArray);
  }

  @Test
  void test_removeAll() {
    String[] table = {"raz", "dwa", "trzy", "cztery"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
    String[] subTable = {"dwa", "trzy"};
    MyArrayList<String> subMyArray = new MyArrayList<String>(Arrays.asList(subTable));
    String[] tableToRemove = {"raz", "pięć", "sześć", "cztery"};
    myArray.removeAll(Arrays.asList(tableToRemove));
    assertEquals(subMyArray, myArray);
  }

  @Test
  void test_containAll() {
    String[] table = {"raz", "dwa", "trzy", "cztery"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
    String[] subTable = {"dwa", "trzy", "trzy", "raz"};
    MyArrayList<String> subMyArray = new MyArrayList<String>(Arrays.asList(subTable));
    assertTrue(myArray.containsAll(subMyArray));
  }

  @Test
  void test_addAll_param_index_and_list() {
    String[] table = {"raz", "dwa", "trzy", "cztery"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
    String[] addTable = {"raz", "dwa", "trzy", "raz", "dwa", "cztery"};
    MyArrayList<String> subMyArray = new MyArrayList<String>(Arrays.asList(addTable));
    String[] tableToAdd = {"raz", "dwa"};
    myArray.addAll(3, Arrays.asList(tableToAdd));
    assertEquals(subMyArray, myArray);
  }

  @Test
  void test_addAll_param_list() {
    String[] table = {"raz", "dwa", "trzy", "cztery"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
    String[] addTable = {"raz", "dwa", "trzy", "cztery", "raz", "dwa"};
    MyArrayList<String> subMyArray = new MyArrayList<String>(Arrays.asList(addTable));
    String[] tableToAdd = {"raz", "dwa"};
    myArray.addAll(Arrays.asList(tableToAdd));
    assertEquals(subMyArray, myArray);
  }

  @Test
  void test_sort() {
    String[] table = {"raz", "dwa", "trzy", "cztery"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
    String[] subTable = {"cztery", "dwa", "raz", "trzy"};
    MyArrayList<String> subMyArray = new MyArrayList<String>(Arrays.asList(subTable));
    myArray.sort(String::compareTo);
    assertEquals(myArray, subMyArray);
  }

  @Test
  void test_replaceAll() {
    String[] table = {"raz", "dwa", "trzy", "cztery"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
    String[] subTable = {"RAZ", "DWA", "TRZY", "CZTERY"};
    MyArrayList<String> subMyArray = new MyArrayList<String>(Arrays.asList(subTable));
    myArray.replaceAll(s -> s.toUpperCase());
    assertEquals(subMyArray, myArray);
  }

  @Test
  void test_retainAll() {
    String[] table = {"raz", "dwa", "trzy", "cztery", "dwa"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
    String[] retainTable = {"raz", "dwa", "dwa"};
    MyArrayList<String> retainMyArray = new MyArrayList<String>(Arrays.asList(retainTable));
    String[] tableToAdd = {"raz", "dwa", "pięć"};
    myArray.retainAll(Arrays.asList(tableToAdd));
    assertEquals(retainMyArray, myArray);
  }

  @Test
  void test_toArray_return_T1_array() {
    String[] table = {"raz", "dwa", "trzy", "cztery"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
    String[] createTable = myArray.toArray(new String[4]);
    assertTrue(Arrays.equals(table, createTable));
  }

  @Test
  void test_iterator_hasNext_empty_Collection() {
    MyArrayList<String> myArray = new MyArrayList<String>();
    Iterator<String> iterator = myArray.iterator();
    assertFalse(iterator.hasNext());
  }

  @Test
  void test_iterator_hasNext_noempty_Collection() {
    String[] table = {"raz", "dwa", "trzy", "cztery"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
    Iterator<String> iterator = myArray.iterator();
    assertTrue(iterator.hasNext());
  }

  @Test
  void test_iterator_next_no_empty_Collection() {
    String[] table = {"raz"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
    Iterator<String> iterator = myArray.iterator();
    assertTrue(iterator.hasNext());
    assertEquals("raz", iterator.next());
    assertFalse(iterator.hasNext());
  }

  @Test
  void test_iterator_with_index_hasNext_empty_Collection() {
    MyArrayList<String> myArray = new MyArrayList<String>();
    ListIterator<String> iterator = myArray.listIterator(0);
    assertFalse(iterator.hasNext());
  }

  @Test
  void test_iterator_with_index_hasNext_no_empty_Collection() {
    String[] table = {"raz", "dwa", "trzy", "cztery"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
    ListIterator<String> iterator = myArray.listIterator(2);
    assertTrue(iterator.hasNext());
  }

  @Test
  void test_iterator_with_index_hasPrevious_empty_Collection() {
    MyArrayList<String> myArray = new MyArrayList<String>();
    ListIterator<String> iterator = myArray.listIterator(0);
    assertFalse(iterator.hasPrevious());
  }

  @Test
  void test_iterator_with_index_hasPrevious_no_empty_Collection() {
    String[] table = {"raz", "dwa", "trzy", "cztery"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
    ListIterator<String> iterator = myArray.listIterator(1);
    assertTrue(iterator.hasPrevious());
  }

  @Test
  void test_iterator_with_index_next_no_empty_Collection() {
    String[] table = {"raz", "dwa", "trzy", "cztery"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
    ListIterator<String> iterator = myArray.listIterator(1);
    assertEquals("dwa", iterator.next());
  }

  @Test
  void test_iterator_with_index_previous_no_empty_Collection() {
    String[] table = {"raz", "dwa", "trzy", "cztery"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
    ListIterator<String> iterator = myArray.listIterator(1);
    assertEquals("raz", iterator.previous());
  }

  @Test
  void test_iterator_with_index_next_index() {
    String[] table = {"raz", "dwa", "trzy", "cztery"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
    ListIterator<String> iterator = myArray.listIterator(1);
    iterator.next();
    assertEquals(2, iterator.nextIndex());
  }

  @Test
  void test_iterator_with_index_previous_next_index() {
    String[] table = {"raz", "dwa", "trzy", "cztery"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
    ListIterator<String> iterator = myArray.listIterator(2);
    iterator.previous();
    assertEquals(0, iterator.previousIndex());
  }

  @Test
  void test_iterator_with_index_add() {
    String[] table = {"raz", "dwa", "trzy"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
    ListIterator<String> iterator = myArray.listIterator(1);
    iterator.add("add");
    assertEquals(4, myArray.size());
    assertEquals("add", myArray.get(1));
  }

  @Test
  void test_iterator_with_index_set() {
    String[] table = {"raz", "dwa", "trzy"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
    ListIterator<String> iterator = myArray.listIterator(1);
    iterator.set("add");
    assertEquals("add", myArray.get(1));
  }

  @Test
  void test_iterator_with_index_remove() {
    String[] table = {"raz", "dwa", "trzy"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
    String[] subTable = {"raz", "trzy"};
    MyArrayList<String> subMyArray = new MyArrayList<String>(Arrays.asList(subTable));
    ListIterator<String> iterator = myArray.listIterator(1);
    iterator.remove();
    assertEquals(subMyArray, myArray);
  }

}
