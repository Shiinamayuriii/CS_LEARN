package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Iterable<T>, Deque<T> {
    private T[] items;
    private int size;
    private int head;
    private int tail;
    private int capacity = 8;

    public ArrayDeque() {
        items = (T[]) new Object[capacity];
        size = 0;
        head = 0;
        tail = 1;
    }

    public void resize_for_guitar(int i){
        capacity = i;
        items = (T[]) new Object[capacity];
    }

    @Override
    public void addFirst(T item) {
        resizeCheck();
        head = (head - 1 + items.length) % items.length;
        items[head] = item;
        size++;
    }
    @Override
    public void addLast(T item) {
        resizeCheck();
        items[tail] = item;
        tail = (tail + 1) % items.length;
        size++;
    }

    private void resizeCheck() {
        if (size == items.length) {
            resize(items.length * 2);
        }
    }

    private void downsizeCheck() {
        if (items.length > 16 && size < items.length / 4) {
            resize(items.length / 2);
        }
    }

    private void resize(int newCapacity) {
        T[] newItems = (T[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newItems[i] = items[(head + i) % items.length];
        }
        items = newItems;
        head = 0;
        tail = size;
    }

    @Override
    public int size() { return size; }

    @Override
    public void printDeque() {
        for(int i = 0;i<size;i++){
            System.out.print(items[(head + i) % items.length] + " ");
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T removed = items[head];
        items[head] = null;
        head = (head + 1) % items.length;
        size--;
        downsizeCheck();
        return removed;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        tail = (tail - 1 + items.length) % items.length;
        T removed = items[tail];
        items[tail] = null;
        size--;
        downsizeCheck();
        return removed;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return items[(head + index) % items.length];
    }

    @Override
    public Iterator<T> iterator(){
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int Pos;

        public ArrayDequeIterator(){
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
    public boolean equals(Object o) {
        if(o == null){ return false; }
        if(o == this){ return true; }
        if(!(o instanceof Deque)){ return false; }
        ArrayDeque<T> other = (ArrayDeque<T>) o;
        if(this.size != other.size){ return false; }
        int index = 0;
        for(T item: this){
            if(!other.get(index).equals(item)){ return false; }
            index += 1;
        }
        return true;
    }

}
