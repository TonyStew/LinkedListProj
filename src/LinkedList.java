import java.util.*;
import java.util.function.Consumer;

/**
 * LinkedList
 *
 * brief description of the program
 *
 * @author Anthony Stahura, L18
 *
 * @version 4/23/2018 
 *
 */

public class LinkedList<T> extends AbstractSequentialList<T> implements Collection<T>{
    private Node head;
    private int size;

    public LinkedList(){
        size = 0;
    }

    public LinkedList(Collection<T> c){
        size = 0;
        addAll(c);
    }

    public LinkedList(T[] a){
        size = 0;
        addAll(Arrays.asList(a));
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public int indexOf(Object o) {
        int index = 0;
        for(T t: this) {
            if(t.equals(o)) return index;
            index++;
        }
        return -1;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if(hasNext()) {
                    T t = (T) node.t;
                    node = node.next;
                    return t;
                }
                else throw new NoSuchElementException();
            }
        };
    }

    @Override
    public ListIterator listIterator(int i) {
        return null;
    }

    @Override
    public Object[] toArray() {
        Object[] temp = new Object[size];
        int index = 0;
        for(T t: this) {
            temp[index] = t;
            index++;
        }
        return temp;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if(a.length >= size) {
            int index = 0;
            for(T t: this) {
                a[index] = (T1) t;
                index++;
            }
            for(int i = index; index < a.length; i++){
                a[i] = null;
            }
            return a;
        }
        else return (T1[]) toArray();
    }

    @Override
    public boolean add(T t) {
        try{
            if(head == null) add(0, t);
            else{
                Node node;
                for (node = head; node.next != null; node = node.next);
                node.next = new Node(t);
                size++;
            }
        } catch (IndexOutOfBoundsException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public void add(int index, T t) {
        if(head == null){
            if(index == 0) {
                head = new Node(t);
                size++;
                return;
            }
            else throw new IndexOutOfBoundsException();
        }
        if(index == 0){
            head = new Node(t, head);
            size++;
            return;
        }
        int count = 0;
        for (Node node = head; node != null; node = node.next) {
            if(count == index){
                node.next = new Node(t, node.next);
                size++;
                return;
            }
            count++;
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    public boolean remove(Object o) {
        if(head == null) return false;
        if(head.t.equals(o)){
            head = head.next;
            size--;
            return true;
        }
        Node node;
        for (Node previous = head; previous.next != null; previous =  previous.next) {
            node = previous.next;
            if(node.t.equals(o)){
                if(node != null) previous.next = node.next;
                else previous.next = null;
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public T remove(int index){
        if(index == 0){
            T t = (T) head.t;
            head = head.next;
            size--;
            return t;
        }
        Node node;
        int i = 1;
        for (Node previous = head; previous != null; previous =  previous.next) {
            node = previous.next;
            if(index == i){
                T t = (T) node.t;
                if(node != null) previous.next = node.next;
                else previous.next = null;
                size--;
                return t;
            }
            i++;
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for(Object o : c){
            if(!contains(o)) return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        c.forEach(t -> add(t));
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        c.forEach(t -> remove(t));
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        for(Node node = head; node != null; node = node.next) {
            if(!c.contains(node.t)) remove(node.t);
        }
        return true;
    }

    @Override
    public void clear() {
        size = 0;
        head = null;
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        for(T t: this) {
            action.accept(t);
        }
    }

    @Override
    public T get(int index) {
        int i = 0;
        for(T t: this) {
            if(i == index) return (T) t;
            i++;
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    public T set(int index, T t){
        int i = 0;
        for(Node node = head; node != null; node = node.next) {
            if(i == index){
                node.t = t;
                return t;
            }
            i++;
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    public String toString() {
        if(head == null) return null;
        String out = "[";
        for (Node node = head; node != null; node = node.next) {
            out += node.t + ", ";
        }
        out = out.substring(0, out.length() - 2) + "]";
        return out;
    }

    @Override
    public boolean equals(Object o){
        if(o == null || !(o instanceof LinkedList) || size != ((LinkedList)o).size()) return false;
        for(int i = 0; i < size; i++){
            if(!get(i).equals(((LinkedList)o).get(i))) return false;
        }
        return true;
    }

    public boolean equalsIgnoreOrder(Object o){
        if(o == null || !(o instanceof LinkedList) || size != ((LinkedList)o).size()) return false;
        boolean found = false;
        for(T t1: this){
            for(Object obj: ((LinkedList)o)){
                if(t1.equals(obj)) found = true;
            }
            if(!found) return false;
        }
        return true;
    }

    private class Node<T>{
        T t;
        Node next;

        public Node(T t){
            this(t, null);
        }

        public Node(T t, Node next){
            this.t = t;
            this.next = next;
        }
    }
}