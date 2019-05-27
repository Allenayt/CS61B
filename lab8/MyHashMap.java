import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;
import java.util.LinkedList;

public class MyHashMap<K extends Comparable<K>, V extends Comparable<V>> implements Map61B<K, V> {

    private int size, tableSize;
    private double lf;
    private LinkedList<K>[] keyArray;
    private LinkedList<V>[] valArray;
    private Set<K> keySet = new HashSet<>();

    public MyHashMap() {
        this(16, 0.75);
    }

    public MyHashMap(int initialSize) {
        this(initialSize, 0.75);
    }

    public MyHashMap(int initialSize, double loadFactor) {

        if(initialSize < 1 || loadFactor <= 0.0) {
            throw new IllegalArgumentException();
        }
        size = 0;
        tableSize = initialSize;
        lf = loadFactor;
        keyArray = (LinkedList<K>[]) new LinkedList[tableSize];
        valArray = (LinkedList<V>[]) new LinkedList[tableSize];
    }

    @Override
    public void clear() {
        size = 0;
        keyArray = new LinkedList[keyArray.length];
        valArray = new LinkedList[valArray.length];

    }

    @Override
    public boolean containsKey(K key) {
        int i = hash(key);
        if (keyArray[i] == null) {
            return false;
        }
        return keyArray[i].contains(key);
    }

    @Override
    public V get(K key) {
        if(key == null){
            throw new IllegalArgumentException();
        }
        if (!containsKey(key)){
            return null;
        } else {
            int i = hash(key);
            return valArray[i].get(keyArray[i].indexOf(key));
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        if(key == null){
            throw new IllegalArgumentException();
        }
        int i = hash(key);
        if(!containsKey(key)) {

            if (keyArray[i] == null){
                keyArray[i] = new LinkedList<K>();
                valArray[i] = new LinkedList<V>();
                keyArray[i].addLast(key);
                valArray[i].addLast(value);
                size += 1;
            } else {
                keyArray[i].addLast(key);
                valArray[i].addLast(value);
                size += 1;
            }
            if (((double) size/tableSize) >= lf)/*size >= lf * tableSize*/ {
                resize();
            }
        } else {
            valArray[i].set(keyArray[i].indexOf(key), value);
        }
    }

    @Override
    public Set<K> keySet() {
        for (int i = 0; i < keyArray.length; i++) {
            if (keyArray[i] != null) {
                int j = 0;
                while (j < keyArray[i].size()) {
                    keySet.add(keyArray[i].get(j));
                    j += 1;
                }
            }
        }
        return keySet;
    }

    @Override
    public Iterator<K> iterator() {
        return new hashMapIterator();
    }


    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }


    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % tableSize;
    }

    private int newhash(K key) {
        return (key.hashCode() & 0x7fffffff) % (tableSize * 2);
    }


    private void resize() {
        /* tableSize *= 2 should operate at the end because if we operate it at first and use the hash
        * instead of newhash function, the hash in the get(to get items in the old hashtable) will not get the
        * right item(the tableSize has mutiplied 2 at first.))*/
        LinkedList<K>[] newKeyArray = new LinkedList[tableSize * 2];
        LinkedList<V>[] newValArray = new LinkedList[tableSize * 2];
        for (K key : keySet()) {
            int i = newhash(key);
            if (newKeyArray[i] == null) {
                newKeyArray[i] = new LinkedList<>();
                newValArray[i] = new LinkedList<>();
                newKeyArray[i].addLast(key);
                newValArray[i].addLast(get(key));
            } else {
                newKeyArray[i].addLast(key);
                newValArray[i].addLast(get(key));
            }
        }
        tableSize *= 2;
        keyArray = newKeyArray;
        valArray = newValArray;
    }

    private class hashMapIterator implements Iterator<K> {
        int i = 0;
        @Override
        public boolean hasNext() {
            return i < keySet.size();
        }

        @Override
        public K next() {
            K[] keyArray = (K[]) keySet.toArray();
            K returnKey = keyArray[i];
            i += 1;
            return returnKey;
        }

    }
}
