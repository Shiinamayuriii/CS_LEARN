package deque;

public class testArrayDeque {
    public static void main(String[] args) {
        ArrayDeque<Integer> a = new ArrayDeque<>();
        a.addFirst(1);
        a.addFirst(2);
        a.addFirst(3);
        a.addFirst(4);
        a.addFirst(5);
        a.addFirst(6);
        a.addFirst(7);
        a.addFirst(8);
        a.addLast(9);
        a.addLast(10);
        a.addFirst(0);
        int removed = a.removeFirst();

        int removed2 = a.removeLast();
        System.out.println(removed);
        System.out.println(removed2);
        System.out.println(a.get(1));


        for(int i:a){
            System.out.print(i + " ");
        }
        System.out.println();


        ArrayDeque<Integer> a1 = new ArrayDeque<>();
        for(int i = 0;i<=100;i++){
            a1.addFirst(i);
        }
        for(int i = 0;i<=90;i++){
            a1.removeFirst();
        }
    }
}
