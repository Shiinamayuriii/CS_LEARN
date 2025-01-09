package deque;

import java.util.ArrayList;
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

    public ArrayDeque(int Def_capacity) {
        items = (T[]) new Object[Def_capacity];
        size = 0;
        head = 0;
        tail = 1;
    }

    @Override
    public void addFirst(T item){
        resize_check();
        items[head] = item;
        head = (head - 1 + items.length) % items.length;
        size++;
    }
    @Override
    public void addLast(T item){
        resize_check();
        items[tail] = item;
        tail = (tail + 1 + items.length) % items.length;
        size++;
    }


    private void resize_check(){
        if(size == items.length){
            capacity *= 2;
            T[] items_new = (T[]) new Object[capacity];
            for(int i =1;i<=items.length;i++){
                items_new[i] = items[(head + i) % items.length];
            }
            items = items_new;
            head = 0;
            tail = size+1;
        }
    }

    private void remove_resize(){
        if(items.length >16 && size <=items.length/4){
            capacity /= 2;
            T[] items_new = (T[]) new Object[capacity];
            for(int i =1;i<=size;i++){
                items_new[i] = items[(head + i) % items.length];
            }
            items = items_new;
            head = 0;
            tail = size+1;

        }
    }

    @Override
    public int size(){ return size; }

    @Override
    public void printDeque(){
        if (isEmpty()) {
            System.out.println("List is empty");
            return;
        }
        for(int i = 1;i<=size;i++){
            System.out.print(items[(head + i) % items.length] + " ");
        }
        System.out.println();
    }

    @Override
    public T removeFirst(){
        if (isEmpty()) {
            return null;
        }
        remove_resize();
        T returned = items[(head+1) % items.length];
        items[(head+1) % items.length] = null;
        head = (head + 1) % items.length;
        size--;
        return returned;
    }

    @Override
    public T removeLast(){
        if (isEmpty()) {
            return null;
        }
        remove_resize();
        T returned = items[(tail-1) % items.length];
        items[(tail-1) % items.length] = null;
        tail = (tail-1) % items.length;
        size--;
        return returned;
    }

    @Override
    public T get(int index){
        if(isEmpty()){
            return null;
        }
        if (index < 0 || index >= size) {
            return null;
        }
        return items[(head + index +1) % items.length];
    }

    @Override
    public Iterator<T> iterator(){
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T>{
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
    public boolean equals(Object o){
        if(o == null){ return false; }
        if(o == this){ return true; }
        if(!(o instanceof ArrayDeque)){ return false; }
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
