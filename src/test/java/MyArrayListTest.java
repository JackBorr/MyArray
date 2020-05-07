
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
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
    assertEquals("add", myArray.get(1));
  }

  @Test
  void test_add_method_with_object_param() {
    String[] table = {"raz", "dwa", "trzy"};
    MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
    int index = myArray.size();
    myArray.add("add");
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

}
