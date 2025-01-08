package deque;

import org.junit.Test;

import static org.junit.Assert.*;

public class testLinkedDeque {
    @Test
    public void testadd1(){
        LinkedListDeque<Integer> lis = new LinkedListDeque<>();
        lis.addFirst(1);
        lis.addFirst(2);
        lis.addFirst(3);
        lis.addLast(2);
        lis.addLast(3);
        int removed = lis.removeLast();
        assertEquals(lis.size(),4);
        assertEquals(removed,3);
    }
    @Test
    public void testadd2(){
        LinkedListDeque<Integer> lis1 = new LinkedListDeque<>();
        lis1.addFirst(3);
        lis1.addFirst(2);
        lis1.addFirst(1);
        lis1.addLast(4);
        lis1.addLast(5);
        assertEquals(lis1.size(),5);
        int removed = lis1.removeFirst();
        assertEquals(removed,1);
        int num = lis1.get(2);
        assertEquals(num,4);
        lis1.printDeque();
    }

    public static void main(String[] args) {
        LinkedListDeque<Integer> lis = new LinkedListDeque<>();
        lis.addFirst(1);
        lis.addFirst(2);
        lis.addFirst(3);
        lis.addLast(2);
        lis.addLast(3);


        for(int i:lis){
            System.out.println(i);
        }

        LinkedListDeque<Integer> lis1 = new LinkedListDeque<>();
        System.out.println(lis1.isEmpty());
    }

    @Test
    public void testadd3(){
        LinkedListDeque<Integer> lis1 = new LinkedListDeque<>();
        lis1.addFirst(1);
        lis1.addFirst(2);
        lis1.addFirst(3);
        lis1.addFirst(4);
        lis1.addFirst(5);

        LinkedListDeque<Integer> lis2 = new LinkedListDeque<>();
        lis2.addFirst(1);
        lis2.addFirst(2);
        lis2.addFirst(3);
        lis2.addFirst(4);
        lis2.addFirst(5);

        System.out.println(lis1.getRecursive(0));
    }
}
