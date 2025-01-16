package bstmap;

import com.sun.jdi.Value;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>,V> implements Map61B<K,V> {

    private Node root;
    private int size = 0;

    private class Node {
        private K key;           // sorted by key
        private V val;         // associated data
        private Node left, right;// left and right subtrees
        private int pos = 0;

        public Node(K key, V val, int size) {
            this.key = key;
            this.val = val;
        }
    }
    public BSTMap() {
    }

    @Override
    public void clear() {
        size = 0;
        root = null;
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    @Override
    public V get(K key) {
        return getValue(root,key);
    }

    private V getValue(Node N,K key) {
        if (key == null) throw new IllegalArgumentException("calls get() with a null key");
        if (N == null) return null;
        int cmp = key.compareTo(N.key);
        if      (cmp < 0) return getValue(N.left, key);
        else if (cmp > 0) return getValue(N.right, key);
        else return N.val;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        if (key == null) throw new IllegalArgumentException("calls put() with a null key");
        if (value == null) {
            V def = getDefaultNonNullValue();
            root = put(root,key,def);
            size = size + 1;
            return;
        }
        size = size + 1;
        root = put(root, key, value);
    }

    private Node put(Node x, K key, V val) {
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = put(x.left,  key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else              x.val   = val;
        return x;
    }

    private V getDefaultNonNullValue() {
        // Replace with the default value for your use case
        // For example, return an empty string for String, 0 for Integer, or a placeholder object
        return (V) "DEFAULT"; // Cast to V to handle generic types
    }

    public void printInOrder() {
        printNode(root);
    }

    private void printNode(Node n) {
        if (n == null) {
            return;
        } else {
            printNode(n.left);
            System.out.print(n.key + ", " + n.val);
            System.out.print(" -> ");
            printNode(n.right);
        }
    }



    @Override
    public Set keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator iterator() {
        throw new UnsupportedOperationException();
    }
}
