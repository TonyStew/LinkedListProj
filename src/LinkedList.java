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

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    public int indexOf(Object o) {
        int index = 0;
        for(Node node = head; node != null; node = node.next) {
            if(node.equals(o)) return index;
            index++;
        }
        return -1;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node node = head;

            @Override
            public boolean hasNext() {
                return node.next != null;
            }

            @Override
            public T next() {
                if(hasNext()) {
                    T t = (T) node.next.t;
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
        for(Node node = head; node != null; node = node.next) {
            temp[index] = node.t;
            index++;
        }
        return temp;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if(a.length >= size) {
            int index = 0;
            for(Node node = head; node != null; node = node.next) {
                a[index] = (T1) node.t;
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
            else add(size - 1, t);
        } catch (IndexOutOfBoundsException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void add(int index, T t) {
        if(head == null){
            if(index == 0) {
                head = new Node(t);
                size++;
                return;
            }
            else throw new IndexOutOfBoundsException();
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
        Node node;
        for (Node previous = head; previous != null; previous =  previous.next) {
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
        Node node;
        int i = 0;
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
        return null;
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
            Node finalNode = node; //TODO figure out why this line is needed
            c.forEach(t ->{ if(!t.equals(finalNode)) remove(finalNode); });
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
        for(Node node = head; node != null; node = node.next) {
            action.accept((T) node.t);
        }
    }

    public T get(int index) {
        int i = 0;
        for(Node node = head; node != null; node = node.next) {
            if(i == index) return (T) node.t;
            i++;
        }
        throw new IndexOutOfBoundsException();
    }

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
        String out = "(";
        for (Node node = head; node != null; node = node.next) {
            out += node.t + ", ";
        }
        out = out.substring(0, out.length() - 2) + ")";
        return out;
    }

    private class Node<T>{
        private T t;
        private Node next;

        public Node(T t){
            this(t, null);
        }

        public Node(T t, Node next){
            this.t = t;
            this.next = next;
        }
    }
}