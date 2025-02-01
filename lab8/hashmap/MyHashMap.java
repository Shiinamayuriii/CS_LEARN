package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author Mengyu
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    private int capacity;
    private int size;
    private static double LOAD_FACTOR = 0.75;
    private Collection<Node>[] buckets;

    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /** Constructors */
    public MyHashMap() {
        size = 0;
        capacity = 16;
        buckets = createTable(capacity);
    }

    public MyHashMap(int initialSize) {
        capacity = initialSize;
        size = 0;
        buckets = createTable(initialSize);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        capacity = initialSize;
        size = 0;
        buckets = createTable(initialSize);
        LOAD_FACTOR = maxLoad;
    }

    @Override
    public void clear() {
        size = 0;
        capacity = 16;
        buckets = createTable(capacity);
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    @Override
    public V get(K key) {
        int pos = pos(key);
        if (buckets[pos] == null) {
            return null;
        }
        for (Node node : buckets[pos]) {
            if (node.key.equals(key)) {
                return node.value;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        Node node = createNode(key, value);
        int pos = pos(key);
        if (buckets[pos] == null) {
            buckets[pos] = createBucket();
        }
        for (Node Node : buckets[pos]) {
            if (Node.key.equals(key)) {
                Node.value = value;
                return;
            }
        }
        buckets[pos].add(node);
        size++;
    }

    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        for (K key : this) {
            set.add(key);
        }
        return set;
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<K> {

        private int currentBucket = 0;
        private Iterator<Node> iterator;
        private int tracker = 0;

        MyIterator() {
            getfirst();
            iterator = buckets[currentBucket].iterator();
        }

        private void getfirst() {
            while (currentBucket < capacity && buckets[currentBucket] == null) {
                currentBucket++;
            }
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext() || tracker < size;
        }

        @Override
        public K next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (!(iterator.hasNext())) {
                currentBucket++;
                getfirst();
                iterator = buckets[currentBucket].iterator();
            }
            K key = iterator.next().key;
            tracker++;
            return key;
        }
    }

    private int pos(K key) {
        return Math.abs(key.hashCode() % capacity);
    }


    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new HashSet<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        return new Collection[tableSize];
    }


    // Your code won't compile until you do so!

}
