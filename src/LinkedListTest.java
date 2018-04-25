import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * LinkedListTest
 *
 * brief description of the program
 *
 * @author Anthony Stahura, L18
 *
 * @version 4/24/2018 
 *
 */

public class LinkedListTest {

    @Test
    public void sizeWithArrayConstructorParam(){
        assertEquals(5, new LinkedList<>(new Object[5]).size());
    }

    @Test
    public void sizeWithAddRemoveAndRemoveAll(){
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.remove(1);
        list.remove(4);
        list.removeAll(new ArrayList<>(Arrays.asList(new Integer[]{1, 2, 3})));
        assertEquals(3, list.size());
    }

    @Test
    public void add(){
        LinkedList<Character> list = new LinkedList<>();
        list.add('c');
        list.add('l');
        assertEquals("Expected Character at index 0 to be \'c\'", (Character)'c', list.get(0));
        assertEquals("Expected Character at index 1 to be \'l\'", (Character)'l', list.get(1));
    }

    @Test
    public void addAtIndex(){
        LinkedList<String> list = new LinkedList<>();
        list.add(0, "a");
        list.add(0, "b");
        assertEquals("Expected String at index 0 to be \"b\"", "b", list.get(0));

    }

    @Test
    public void removeObjectParam(){
        LinkedList<String> list = new LinkedList<>(new String[]{"a", "b", "c", "d", "e"});
        list.remove("b");
        assertEquals(new LinkedList<>(new String[]{"a", "c", "d", "e"}), list);
    }

    @Test
    public void removeIndexParam(){
        LinkedList<String> list = new LinkedList<>(new String[]{"a", "b", "c", "d", "e"});
        list.remove(2);
        assertEquals(new LinkedList<>(new String[]{"a", "b", "d", "e"}), list);
    }

    @Test
    public void removeAll(){
        LinkedList<String> list = new LinkedList<>(new String[]{"a", "b", "c", "d", "e"});
        list.removeAll(new LinkedList<>(new String[]{"a", "c", "e"}));
        assertEquals(new LinkedList<>(new String[]{"b", "d"}), list);
    }

    @Test
    public void retainAll(){
        LinkedList<String> list = new LinkedList<>(new String[]{"a", "b", "c", "d", "e"});
        list.retainAll(new LinkedList<>(new String[]{"a", "c", "e"}));
        assertEquals(new LinkedList<>(new String[]{"a", "c", "e"}), list);
    }

    @Test
    public void addAll(){
        LinkedList<String> list = new LinkedList<>(new String[]{"a", "b", "c", "d", "e"});
        list.addAll(new LinkedList<>(new String[]{"a", "c", "f"}));
        assertEquals(new LinkedList<>(new String[]{"a", "b", "c", "d", "e", "a", "c", "f"}), list);
    }

    @Test
    public void containsAll(){
        LinkedList<String> list = new LinkedList<>(new String[]{"a", "b", "c", "d", "e"});
        assertEquals(list.containsAll(new LinkedList<>(new String[]{"a"})), true);
        assertEquals(list.containsAll(new LinkedList<>(new String[]{"a", "q"})), false);
        assertEquals(list.containsAll(new LinkedList<>(new String[]{"a", "c"})), true);
        assertEquals(list.containsAll(new LinkedList<>(new String[]{})), true);
    }

    @Test
    public void indexOf(){
        LinkedList<String> list = new LinkedList<>(new String[]{"a", "b", "c", "d", "e"});
        assertEquals(list.indexOf("a"), 0);
        assertEquals(list.indexOf("q"), -1);
        assertEquals(list.indexOf("b"), 1);
        assertEquals(list.indexOf("e"), 4);
    }

    @Test
    public void isEmpty(){
        assertEquals(new LinkedList<>().isEmpty(), true);
        assertEquals(new LinkedList<>(new Object[3]).isEmpty(), false);
    }
}