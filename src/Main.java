import java.util.Collections;

/**
 * Main
 *
 * brief description of the program
 *
 * @author Anthony Stahura, L18
 *
 * @version 4/24/2018 
 *
 */

public class Main {
    public static void main(String[] args) {
        LinkedList<Integer> list =  new LinkedList<>();
        list.add(101);
        list.add(12);
        list.add(112);
        System.out.println(list);
//        Collections.sort(list);
        for(int i : list){
            System.out.println(i);
        }
        System.out.println(list.size());
    }
}