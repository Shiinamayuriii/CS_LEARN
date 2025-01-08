package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Iterable<T> {

    // Node class
    private class Node {
        T data;
        Node next;
        Node prev;

        Node(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    // Sentinel node
    private Node sentinel;
    private int size;

    // Constructor
    public LinkedListDeque() {
        sentinel = new Node(null); // Sentinel node with no data
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    // Add an element to the front of the list
    public void addFirst(T item) {
        Node newNode = new Node(item);
        newNode.prev = sentinel;
        newNode.next = sentinel.next;
        if(this.size == 0) {
            sentinel.prev = newNode;
        }
        else{
            sentinel.next.prev = newNode;
        }
        sentinel.next = newNode;
        size++;
    }

    // Add an element to the end of the list
    public void addLast(T item) {
        Node newNode = new Node(item);
        newNode.prev = sentinel.prev;
        newNode.next = sentinel;
        if(this.size == 0) {
            sentinel.next = newNode;
        }
        else{
            sentinel.prev.next = newNode;
        }
        sentinel.prev = newNode;
        size++;
    }

    // Remove the first element
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Node firstNode = sentinel.next;
        firstNode.next.prev = sentinel;
        sentinel.next = firstNode.next;
        size--;
        return firstNode.data;
    }

    // Remove the last element
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        Node lastNode = sentinel.prev;
        sentinel.prev = lastNode.prev;
        sentinel.next = lastNode.next;
        lastNode.prev.next = sentinel;
        // Traverse to the second-to-last node
         // Link back to sentinel
        size--;
        return lastNode.data;
    }

    // Get the size of the list
    public int size() {
        return size;
    }

    // Check if the list is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Print the list elements
    public void printDeque() {
        if (isEmpty()) {
            System.out.println("List is empty");
            return;
        }

        Node current = sentinel.next;
        while (current != sentinel) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    public T get(int index){
        if(isEmpty()){
            return null;
        }
        if (index < 0 || index >= size) {
            return null;
        }
        Node returnedNode = sentinel.next;
        int i = 0;
        while(i < index){
            returnedNode = returnedNode.next;
            i+=1;
        }
        return returnedNode.data;
    }

    public T getRecursive(int index){
        if(isEmpty()){
            return null;
        }
        if (index < 0 || index >= size) {
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);
    }

    private T getRecursiveHelper(Node N,int index){
        if(index == 0){
            return N.data;
        }
        return getRecursiveHelper(N.next, index-1);
    }

    public Iterator<T> iterator(){
        return new LinkedListIterator();
    }

    public class LinkedListIterator implements Iterator<T>{
        private int Pos;
        public LinkedListIterator(){
            Pos = 0;
        }

        @Override
        public boolean hasNext() {
            return Pos < size;
        }

        @Override
        public T next() {
            T returnitem = get(Pos);
            Pos += 1;
            return returnitem;
        }

    }
    @Override
    public boolean equals(Object o){
        if(o == null){ return false; }
        if(o == this){ return true; }
        if(!(o instanceof LinkedListDeque)){ return false; }
        LinkedListDeque<T> other = (LinkedListDeque<T>) o;
        if(this.size != other.size){ return false; }
        int index = 0;
        for(T item: this){
            if(!other.get(index).equals(item)){ return false; }
            index += 1;
        }
        return true;
    }
}