package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private final Comparator<T> comparator;
    public MaxArrayDeque(Comparator<T> c) {
        this.comparator = c;
    }

    public T max(){
        return getT(comparator);
    }

    public T max(Comparator<T> c) {
        return getT(c);
    }

    private T getT(Comparator<T> c) {
        if(isEmpty()){
            return null;
        }
        T max = get(0);
        for(int i=1; i<size(); i++){
            T currentItem = get(i);
            if(c.compare(currentItem, max) > 0){
                max = get(i);
            }
        }
        return max;
    }


}
