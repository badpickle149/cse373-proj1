package datastructures.dictionaries;

import misc.exceptions.NotYetImplementedException;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @see IDictionary
 */
public class ArrayDictionary<K, V> implements IDictionary<K, V> {
    /*
    Warning:
    You may not rename this field or change its type.
    We will be inspecting it in our secret tests.

    Note: The field below intentionally omits the "private" keyword. By leaving off a specific
    access modifier like "public" or "private" it becomes package-private, which means anything in
    the same package can access it. Since our tests are in the same package, they will be able
    to test this property directly.
     */
    Pair<K, V>[] pairs;

    // You may add extra fields or helper methods though!
    private int size;

    public ArrayDictionary() {
        this.pairs = makeArrayOfPairs(10);
        for (int i = 0; i < 10; i++) {
            this.pairs[i] = null;
        }
        this.size = 0;
    }

    /**
     * This method will return a new, empty array of the given size that can contain `Pair<K, V>`
     * objects.
     *
     * Note that each element in the array will initially be null.
     */
    @SuppressWarnings("unchecked")
    private Pair<K, V>[] makeArrayOfPairs(int arraySize) {
        /*
        It turns out that creating arrays of generic objects in Java is complicated due to something
        known as "type erasure."

        We've given you this helper method to help simplify this part of your assignment. Use this
        helper method as appropriate when implementing the rest of this class.

        You are not required to understand how this method works, what type erasure is, or how
         arrays and generics interact. Do not modify this method in any way.
        */
        return (Pair<K, V>[]) (new Pair[arraySize]);
    }

    private void copyAndDoublePairs() {
        if (this.size > 0) {
            if (this.pairs[this.pairs.length - 1] != null) {
                // if the last element in the array has a key that's not null double the capacity
                if (this.pairs[this.pairs.length - 1].key != null) {
                    Pair<K, V>[] newPairs = makeArrayOfPairs(this.pairs.length*2);
                    for (int i = 0; i < newPairs.length; i++) {
                        if (i < this.size) {
                            newPairs[i] = new Pair<K,V>(this.pairs[i].key, this.pairs[i].value);
                        } else {
                            newPairs[i] = null;
                        }
                    }
                    this.pairs = newPairs;
                }
            }
        }

    }

    @Override
    public V get(K key) {
        if (key != null) {
            for (int i = 0; i < this.size; i++) {
                if (this.pairs[i].key != null) {
                    if (this.pairs[i].key.equals(key)) {
                        return this.pairs[i].value;
                    }
                }
            }
        } else {
            for (int i = 0; i < this.size; i++) {
                if (this.pairs[i].key == null) {
                    return this.pairs[i].value;
                }
            }
        }
        throw new NoSuchKeyException();
    }

    @Override
    public V put(K key, V value) {
        copyAndDoublePairs();

        if (containsKey(key)) {
            for (int i = 0; i < this.size; i++) {
                Pair<K, V> curr = this.pairs[i];
                if (key != null) {
                    if (curr.key.equals(key)) {
                        V oldValue = curr.value;
                        curr.value = value;
                        return oldValue;
                    }
                } else {
                    if (curr.key == null) {
                        V oldValue = curr.value;
                        curr.value = value;
                        return oldValue;
                    }
                }

            }
        } else {
            this.pairs[this.size] = new Pair<K,V>(key, value);
        }
        this.size++;
        return null;
    }

    @Override
    public V remove(K key) {
        if (containsKey(key)) {
            // lookup value to be removed
            // replace that value with the last element in the dict array
            if (key != null) {
                for (int i = 0; i < this.size; i++) {
                    Pair<K,V> curr = this.pairs[i];
                    if (curr.key != null) {
                        if (curr.key.equals(key)) {
                            return switchLast(i);
                        }
                    }
                }
            } else {
                for (int i = 0; i < this.size; i++) {
                    Pair<K,V> curr = this.pairs[i];
                    if (curr.key == null) {
                        return switchLast(i);
                    }
                }
            }
        }
        return null;
    }

    // i -> index to be overridden
    private V switchLast(int i) {
        Pair<K,V> curr = this.pairs[i];
        V oldValue = curr.value;
        Pair<K,V> lastPair = this.pairs[this.size - 1];
        this.pairs[i].key = lastPair.key;
        this.pairs[i].value = lastPair.value;
        lastPair = null;
        this.size--;
        return oldValue;
    }

    @Override
    public boolean containsKey(K key) {
        if (key != null) {
            for (int i = 0; i < this.size; i++) {
                Pair<K,V> curr = this.pairs[i];
                if (curr.key != null) {
                    if (curr.key.equals(key)) {
                        return true;
                    }
                }
            }
        } else {
            for (int i = 0; i < this.size; i++) {
                Pair<K,V> curr = this.pairs[i];
                if (curr.key == null) {
                    if (curr.value != null) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Iterator<KVPair<K, V>> iterator() {
        KVPair<K,V>[] pairs = new KVPair[this.size];
        for (int i = 0; i < pairs.length; i++) {
            pairs[i] = new KVPair<K,V>(this.pairs[i].key, this.pairs[i].value);
        }
        return new ArrayDictionaryIterator<K,V>(pairs);
    }

    @Override
    public String toString() {
        //return super.toString();

        /*
        After you've implemented the iterator, comment out the line above and uncomment the line
        below to get a better string representation for objects in assertion errors and in the
        debugger.
        */

        return IDictionary.toString(this);
    }

    private static class Pair<K, V> {
        public K key;
        public V value;

        // You may add constructors and methods to this class as necessary.
        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return String.format("%s=%s", this.key, this.value);
        }
    }

    private static class ArrayDictionaryIterator<K, V> implements Iterator<KVPair<K, V>> {
        // You'll need to add some fields
        private int currIndex;
        private KVPair<K,V>[] pairs;

        public ArrayDictionaryIterator(KVPair<K,V>[] pairs) {
            this.currIndex = 0;
            this.pairs = pairs;
        }

        @Override
        public boolean hasNext() {
            if (currIndex < pairs.length) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        public KVPair<K, V> next() {
            if (currIndex >= pairs.length) {
                throw new NoSuchElementException();
            }

            KVPair<K,V> next = this.pairs[currIndex];
            this.currIndex++;
            return next;
        }
    }
}
