package deque;


import java.util.Comparator;

public class randomtest {
    public static void main(String[] args) {
        // Comparator for integers
        Comparator<Integer> intComparator = Integer::compare;

        // Create a MaxArrayDeque for integers
        MaxArrayDeque<Integer> intDeque = new MaxArrayDeque<>(intComparator);

        // Add elements to the deque
        intDeque.addLast(10);
        intDeque.addLast(20);
        intDeque.addLast(15);

        // Print the deque
        System.out.println("Integer deque: ");
        intDeque.printDeque();

        // Find the maximum element using the default comparator
        System.out.println("Max (default comparator): " + intDeque.max());

        // Custom comparator for finding the minimum element
        Comparator<Integer> minComparator = (a, b) -> Integer.compare(b, a);
        System.out.println("Max (custom comparator for min): " + intDeque.max(minComparator));

        // Test with strings
        Comparator<String> stringComparator = String::compareTo;

        MaxArrayDeque<String> stringDeque = new MaxArrayDeque<>(stringComparator);

        // Add elements to the deque
        stringDeque.addLast("apple");
        stringDeque.addLast("banana");
        stringDeque.addLast("cherry");

        // Print the deque
        System.out.println("String deque: ");
        stringDeque.printDeque();

        // Find the maximum element using the default comparator
        System.out.println("Max (default comparator): " + stringDeque.max());

        // Custom comparator to find the shortest string
        Comparator<String> shortestStringComparator = (a, b) -> Integer.compare(a.length(), b.length());
        System.out.println("Max (custom comparator for shortest string): " + stringDeque.max(shortestStringComparator));
    }
}
