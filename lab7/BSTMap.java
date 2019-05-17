import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private Node root;

    private class Node {

        /* (K, V) pair */
        private K key;
        private V value;

        /* Children of the Node */
        private Node left;
        private Node right;
        private int size; // Number of Node in subtrees.


        /* Node Constructor */
        Node(K k, V v, int s) {
            key = k;
            value = v;
            size = s;
        }

    }

    /* BSTMap Constructor */
    public BSTMap() {
        this.clear();
    }

    @Override
    public void clear() {
        root = null;
    }


    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Argument to contains() is null.");
        }
        return get(root, key) != null;
    }


    public V get(K key) {
        return get(root, key);
    }


    private V get(Node node, K key) {
        if (key == null) {
            throw new IllegalArgumentException("Calls get() with a null key.");
        }

        if (node == null) {
            return null;
        }

        int compare = node.key.compareTo(key);
        if (compare == 0) {
            return node.value;
        }
        else if (compare < 0) {
            return get(node.right, key);
        } else {
            return get(node.left, key);
        }

    }

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        }
        return node.size;
    }

    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Calls put() with a null key.");
        }
        root = put(root, key, value);
    }


    private Node put(Node node, K key, V value) {
        if (node == null) {
            return new Node(key, value, 1);
        }
        int compare = node.key.compareTo(key);
        if (compare == 0) {
            node.value = value;
        } else if (compare > 0) {
            node.left = put(node.left, key, value);
        } else {
            node.right = put(node.right, key, value);
        }
        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }

    public void printInOrder() {
        printInOrder(root);
    }

    private void printInOrder(Node node) {
        if (node == null) {
            return;
        }
        printInOrder(node.left);
        System.out.println("<" + node.key + "," + node.value + ">");
        printInOrder(node.right);
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
