import org.junit.jupiter.api.Test;
import java.util.Arrays;


import static org.junit.jupiter.api.Assertions.*;

class MyArrayListTest {

    @Test
    void test_size() {
        String[] table ={"raz", "dwa", "trzy"};
        MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
        assertEquals(table.length, myArray.size());
    }

    @Test
    void test_isEmpty_empty_array(){
        MyArrayList<String> myArray = new MyArrayList<String>();
        assertTrue(myArray.isEmpty());
    }

    @Test
    void test_isEmpty_not_empty_array(){
        String[] table ={"raz", "dwa", "trzy"};
        MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
        assertFalse(myArray.isEmpty());
    }

    @Test
    void test_contains_not_contain_object(){
        String[] table ={"raz", "dwa", "trzy"};
        MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
        assertFalse(myArray.contains("test"));
    }

    @Test
    void test_contains_method_contain_object(){
        String[] table ={"raz", "dwa", "trzy"};
        MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
        assertTrue(myArray.contains("dwa"));
    }
    @Test
    void test_get_method(){
        String[] table ={"raz", "dwa", "trzy"};
        MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
        assertEquals("dwa", myArray.get(1));
    }

    @Test
    void test_add_method_with_id_and_object_params(){
        String[] table ={"raz", "dwa", "trzy"};
        MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
        myArray.add(1, "add");
        assertEquals("add", myArray.get(1));
    }

    @Test
    void test_add_method_with_object_param(){
        String[] table ={"raz", "dwa", "trzy"};
        MyArrayList<String> myArray = new MyArrayList<String>(Arrays.asList(table));
        int index = myArray.size();
        myArray.add("add");
        assertEquals("add", myArray.get(index));
    }
}